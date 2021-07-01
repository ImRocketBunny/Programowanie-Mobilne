package com.example.prm_02

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.location.Criteria
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import com.example.prm_02.databinding.ActivityMainAddBinding
import com.example.prm_02.model.Fotka
import com.example.prm_02.model.FotkaDTO
import com.google.android.gms.location.*
import java.io.File
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.concurrent.thread


class MainActivityAdd : AppCompatActivity() {
    val binding by lazy { ActivityMainAddBinding.inflate(layoutInflater) }
    val locman by lazy { getSystemService(LocationManager::class.java) }
    val locClient by lazy { LocationServices.getFusedLocationProviderClient(this) }
    val sesja = mutableListOf<String>()
    var nazwa=""
    var verify=""
    //private var locationManager : LocationManager? = null

    //private lateinit var fusedLocationClient: FusedLocationProviderClient

    val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?:return

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setContentView(binding.root)
        //locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
        //verify = binding.imageView.toString()
        binding.addButton.setEnabled(false);
        binding.snapButton.setOnClickListener{
            nazwa = SimpleDateFormat("ddMMyyyy_HHmmss").format(Date())+".jpeg"
            val filePathFromUri = filesDir.resolve(nazwa)
            sesja.add(nazwa)
            binding.addButton.setEnabled(true);
            val uri = FileProvider.getUriForFile(this,"pl.sewerynKruk.FileProvider",filePathFromUri)
            val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE).let{
                it.putExtra(MediaStore.EXTRA_OUTPUT, uri)

            }
            startActivityForResult(intentCamera,1)

        }
    }

    override fun onResume() {
        super.onResume()
        try {
            val directory: File = File("/data/data/com.example.prm_02/files",nazwa)
            val bitmap = BitmapFactory.decodeStream(FileInputStream(directory))
            binding.imageView.setImageBitmap(bitmap)
        }catch (e: Exception){
            println(e)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSave(view: View){

        var miejscowka = ""
        sesja.remove(nazwa)
        val czas = LocalDate.now()
        miejscowka = intent.getStringExtra("lat").toString()+"_"+intent.getStringExtra("lon").toString()
        println("hello?")
        if(miejscowka==""){
            Toast.makeText(this, "cos z lokacja nie tak", Toast.LENGTH_LONG).show()
            return
        }
        try {
            for (pic in sesja){
                val direct = File("/data/data/com.example.prm_02/files",pic)
                direct.delete()
            }
            sesja.clear()
        }catch (e: Exception){
            println(e)
        }
        val direct = File("/data/data/com.example.prm_02/files",nazwa)
        val bitmap = BitmapFactory.decodeStream(FileInputStream(direct))
        val bitmapLater = drawText(bitmap,czas.toString(),intent.getStringExtra("adress"))
        val fos = openFileOutput(nazwa, MODE_PRIVATE)
        if (bitmapLater != null) {
            bitmapLater.compress(Bitmap.CompressFormat.JPEG, 100, fos)

        }else{
            return
        }
        fos.close()
        val note = binding.notatka.text.toString()
        if(note.isEmpty()){
            Toast.makeText(this, "nie ma notatki", Toast.LENGTH_LONG).show()
            return
        }
        if(note.length>500){
            Toast.makeText(this, "opis za dlugi", Toast.LENGTH_LONG).show()
            return
        }
        val fota = binding.imageView.drawable
        val fotka = Fotka(miejscowka,czas, note, fota)
        Shared.fotkaList.add(fotka)
        Shared.listaZdjec.add(Zdjecie(nazwa,fota.toBitmap()))
        val fotk2 = FotkaDTO(
            nameFoto = nazwa,
            opis = note,
            data = czas.toString(),
            miejsce = miejscowka)
        thread {
            Shared.db?.fotka?.save(fotk2)
        }
        finish()
    }

    fun onBack(view: View){
        try {
            for (pic in sesja){
                val direct = File("/data/data/com.example.prm_02/files",pic)
                direct.delete()
            }
            sesja.clear()
        }catch (e: Exception){
            println(e)
        }
        finish()
    }
    fun drawText(bitmap: Bitmap, text: String?, text2: String?): Bitmap? {
        val tmp = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(tmp)
        val paint = Paint()
        val r1 = intent.getStringExtra("r")?.toInt()
        val g1 = intent.getStringExtra("g")?.toInt()
        val b1 = intent.getStringExtra("g")?.toInt()
        val size1 = intent.getStringExtra("size")?.toInt()
        if(r1!=null&&g1!=null&&b1!=null&&size1!=null){
            paint.textSize = size1.toFloat()
            paint.color = Color.rgb(r1,g1,b1)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
            canvas.drawBitmap(tmp, 0f, 0f, paint)
            canvas.drawText(text!!, 1f, size1.toFloat(), paint)
            canvas.drawText(text2!!, 1f, (size1.toFloat()+size1.toFloat()), paint)
        }else{

        }

        return tmp
    }






}


