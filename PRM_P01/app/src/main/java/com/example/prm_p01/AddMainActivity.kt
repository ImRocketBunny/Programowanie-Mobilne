package com.example.prm_p01

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.prm_p01.databinding.ActivityAddMainBinding
import com.example.prm_p01.model.Operacja
import java.time.LocalDate
import java.util.*

class AddMainActivity : AppCompatActivity() {
    val binding by lazy { ActivityAddMainBinding.inflate(layoutInflater) }
    var edit = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(this, R.array.typy, android.R.layout.simple_spinner_item)
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter

                    if(intent.getStringExtra("typ")=="Wydatek"||intent.getStringExtra("typ")=="Przychod") {
                            val spinnerPosition = adapter.getPosition(intent.getStringExtra("typ"))
                            binding.spinner.setSelection(spinnerPosition)
                            binding.editTextTextPersonName.setText(intent.getStringExtra("miejsce"))
                            binding.editTextTextPersonName2.setText(intent.getStringExtra("kategoria"))
                            binding.editTextNumberDecimal.setText(intent.getStringExtra("kwota").toString())
                            binding.editTextDate.setText(intent.getStringExtra("data").toString())
                            edit = 1
                    }

                }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSave(view: View){
        val typ = binding.spinner.selectedItem.toString()
        val miejsce = binding.editTextTextPersonName.text.toString()
        val kategoria = binding.editTextTextPersonName2.text.toString()
        val kwota = binding.editTextNumberDecimal.text.toString()
        val data = binding.editTextDate.text.toString()

        if(typ.isEmpty()||miejsce.isEmpty()||kategoria.isEmpty()||kwota.isEmpty()||data.isEmpty()){
            Toast.makeText(this, "Nie uzupełniono wszystkich pól", Toast.LENGTH_LONG).show()
            return
        }
        val pattern = ("^(19|20)\\d\\d([-])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])\$").toRegex()
        if(!pattern.matches(data)){
            Toast.makeText(this, "Zły format daty", Toast.LENGTH_LONG).show()
            return
        }
        if(kwota.toDouble()<=0){
            Toast.makeText(this, "Wpisana kwota nie jest poprawna", Toast.LENGTH_LONG).show()
            return
        }
        val operacja = Operacja(typ, miejsce, kategoria, kwota.toDouble(), LocalDate.parse(data))
        if(edit==1){
            val liczba = intent.getStringExtra("index")
            println(liczba)
            if (liczba != null) {
                Shared.operacjaLIst.set(liczba.toInt(),operacja)
            }


            finish()
            edit=0
        }else{
            Shared.operacjaLIst.add(operacja)
            finish()
        }

    }

    fun onBack(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}