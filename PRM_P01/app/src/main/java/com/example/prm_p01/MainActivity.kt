package com.example.prm_p01

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prm_p01.Shared.operacjaLIst
import com.example.prm_p01.adapter.OperacjaAdapter
import com.example.prm_p01.databinding.ActivityMainBinding
import com.example.prm_p01.databinding.OperacjaListBinding


class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val operacjaAdapter by lazy { OperacjaAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(binding.root)
        setupOperacjaList().also {

        }


    }

    fun setupOperacjaList(){
        binding.operacjaList.apply {
            adapter=operacjaAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        operacjaAdapter.operacje=Shared.operacjaLIst
        binding.saldo.text="Twoje saldo miesiaca wynosi: "+Shared.funkcja(operacjaLIst).toString()+"zł."

    }

    fun clicked(view: View) {
        val intent = Intent(this, AddMainActivity::class.java)
        startActivity(intent)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun goEdit(view: View){
        if(operacjaAdapter.selectedItem()==null){
            Toast.makeText(this, "Zaznacz item", Toast.LENGTH_LONG).show()
            return
        }
        val intent = Intent(this, AddMainActivity::class.java)
        var zmienna = operacjaAdapter.selectedItem()

        intent.putExtra("index", zmienna.toString())
        intent.putExtra("typ", zmienna?.let { Shared.operacjaLIst.get(it).typ })
        intent.putExtra("miejsce", zmienna?.let { Shared.operacjaLIst.get(it).miejsce})
        intent.putExtra("kategoria", zmienna?.let { Shared.operacjaLIst.get(it).kategoria})
        intent.putExtra("data",zmienna?.let { Shared.operacjaLIst.get(it).data.toString()})
        intent.putExtra("kwota", zmienna?.let { Shared.operacjaLIst.get(it).kwota.toString()})
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun goGraph(view: View){
        val intent = Intent(this, WykresMainActivity::class.java)
        val wartosci = Shared.prepareDataWykres().toDoubleArray()
        intent.putExtra("lista",wartosci)
        startActivity(intent)
    }
}


