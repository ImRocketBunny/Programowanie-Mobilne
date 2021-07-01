package com.example.prm_02

//import com.example.prm_02.model.Zdjecie
import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.prm_02.databinding.ActivityMainBinding
import com.example.prm_02.model.Fotka
import com.google.android.gms.location.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.time.LocalDate
import java.util.*
import kotlin.concurrent.thread
private const val REQ_LOCATION_PERMISSION = 1
class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //val locman by lazy { getSystemService(LocationManager::class.java) }
    lateinit var clientLokejszyn: FusedLocationProviderClient
    val operacjaAdapter by lazy { ListAdapter() }
    var lat = ""
    var lon = ""
    var adr = ""
    var country = ""
    var r = 0
    var g = 0
    var b = 0
    var size = 72
    var radius = 1
    lateinit var client: GeofencingClient
    //lateinit var geoRequest: GeofencingRequest
    var listaGeofence = mutableListOf<Geofence>()
    private val geofencePendingIntent: PendingIntent by lazy {
        val intent = Intent(this, Notifier::class.java)
        PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        client = LocationServices.getGeofencingClient(this)


        super.onCreate(savedInstanceState)
        checkPermission()
        clientLokejszyn = LocationServices.getFusedLocationProviderClient(this)
        Shared.db = Room.databaseBuilder(this, AppDataBase::class.java, "fotkaDB").build()
        setContentView(binding.root)
        getLastKnownLocation()
        setupFotkaList()
    }

    fun setupFotkaList() {
        binding.listaFot.apply {
            adapter = operacjaAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        listaGeofence.clear()
        try {
            photoGet()

        } catch (e: Exception) {
            println(e)
        }
        getSettings()
//        val pi = PendingIntent.getBroadcast(applicationContext,1,
//            Intent(),PendingIntent.FLAG_UPDATE_CURRENT
//        )
        //operacjaAdapter.fotki=Shared.fotkaList
        thread {
            //Shared.db?.fotka?.deleteAll()
            Shared.db?.fotka?.getAll()?.let {
                println(it.size)
                val newList = it.mapNotNull {
                    val nazwa = it.nameFoto
                    val fotka = Shared.listaZdjec.filter { it.string == nazwa }[0].Bbitmapa
                    try {
                        val lotlob = it.miejsce.split("_")
                        val geofence = Geofence.Builder()
                            .setCircularRegion(
                                lotlob[0].toDouble(),
                                lotlob[1].toDouble(),
                                radius.toFloat() * 1000f
                            )
                            .setRequestId(it.id.toString())
                            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
                            .setExpirationDuration(Geofence.NEVER_EXPIRE)
                            .build()
                        listaGeofence.add(geofence)
                        //println("fence" + it.id)
                    } catch (e: Exception) {
                        //println("nie ma fence")
                    }

                    Fotka(
                        it.miejsce,
                        LocalDate.parse(it.data),
                        it.opis,
                        fotka.toDrawable(resources)
                    )

                }
                operacjaAdapter.fotki = newList
                Shared.fotkaList = newList as MutableList<Fotka>


            }
            val pi = PendingIntent.getBroadcast(
                applicationContext,
                1,
                Intent(this, Notifier::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val req = GeofencingRequest.Builder()
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .addGeofences(listaGeofence)
                .build()
            client.addGeofences(
                req,
                geofencePendingIntent
            )

            //println(listaGeofence.size)
        }


    }

    fun goAddFoto(view: View) {
        val intent = Intent(this, MainActivityAdd::class.java)
        intent.putExtra("r", r.toString())
        intent.putExtra("g", g.toString())
        intent.putExtra("b", b.toString())
        intent.putExtra("size", size.toString())
        intent.putExtra("lat", lat)
        intent.putExtra("lon", lon)
        intent.putExtra("adress", adr)
        intent.putExtra("country", country)
        startActivity(intent)
    }

    fun goSettings(view: View) {
        val intent = Intent(this, MainActivitySettings::class.java)
        intent.putExtra("r", r.toString())
        intent.putExtra("g", g.toString())
        intent.putExtra("b", b.toString())
        intent.putExtra("size", size.toString())
        intent.putExtra("radius", radius.toString())
        startActivity(intent)

    }

    fun getSettings() {
        try {
            val settings = File("/data/data/com.example.prm_02", "settings.txt")
            val ustawienia = settings.readText().split("\n")
            r = ustawienia.get(0).toInt()
            g = ustawienia.get(1).toInt()
            b = ustawienia.get(2).toInt()
            size = ustawienia.get(3).toInt()
            radius = ustawienia.get(4).toInt()
            println(ustawienia.size)
        } catch (e: Exception) {
            val defSettings = File("/data/data/com.example.prm_02", "settings.txt")
            val writer = FileWriter(defSettings)
            writer.append("0" + "\n" + "0" + "\n" + "0" + "\n" + "72" + "\n" + "1")
            writer.flush()
            writer.close()
            println("reset ustawien")
        }
    }

    fun checkPermission() {
        val permissionStatus = checkSelfPermission(ACCESS_FINE_LOCATION)
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(ACCESS_FINE_LOCATION), REQ_LOCATION_PERMISSION)
        }
        val permissionStatus2 = checkSelfPermission(ACCESS_COARSE_LOCATION)
        if (permissionStatus2 != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(ACCESS_COARSE_LOCATION), REQ_LOCATION_PERMISSION)
        }
        val permissionStatus3 = checkSelfPermission(CAMERA)
        if (permissionStatus3 != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(CAMERA), REQ_LOCATION_PERMISSION)
        }
        val permissionStatus4 = checkSelfPermission(WRITE_EXTERNAL_STORAGE)
        if(permissionStatus4!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(WRITE_EXTERNAL_STORAGE), REQ_LOCATION_PERMISSION)
        }
        val permissionStatus5 = checkSelfPermission(READ_EXTERNAL_STORAGE)
        if(permissionStatus5!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(READ_EXTERNAL_STORAGE), REQ_LOCATION_PERMISSION)
        }
    }





    @SuppressLint("MissingPermission")
    fun getLastKnownLocation(){
        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 1000L
        locationRequest.fastestInterval = 200L

        var locCallback = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult ?:return
                for (location in locationResult.locations){
                    val geocoder = Geocoder(this@MainActivity, Locale.getDefault())
                    val adress = geocoder.getFromLocation(location.latitude,location.longitude,1)
                        lat = location.latitude.toString()
                        lon = location.longitude.toString()
                    adr = adress[0].getAddressLine(0)
                    country = adress[0].countryName
                }
            }
        }
        clientLokejszyn.requestLocationUpdates(locationRequest, locCallback, Looper.myLooper())
    }
}

