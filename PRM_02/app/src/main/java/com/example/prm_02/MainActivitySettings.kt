package com.example.prm_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.prm_02.databinding.ActivityMainAddBinding
import com.example.prm_02.databinding.ActivityMainSettingsBinding
import java.io.File
import java.io.FileWriter

class MainActivitySettings : AppCompatActivity() {
    val binding by lazy { ActivityMainSettingsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.r.setText(intent.getStringExtra("r"))
        binding.g.setText(intent.getStringExtra("g"))
        binding.b.setText(intent.getStringExtra("b"))
        binding.size.setText(intent.getStringExtra("size"))
        binding.odleglosc.setText(intent.getStringExtra("radius"))
    }

    fun onSaveSettings(view: View){

        val r = binding.r.text.toString()
        val g = binding.g.text.toString()
        val b = binding.b.text.toString()
        val size = binding.size.text.toString()
        val radius = binding.odleglosc.text.toString()
        if(r.isEmpty()||g.isEmpty()||b.isEmpty()||size.isEmpty()||radius.isEmpty()){
            Toast.makeText(this, "Nie uzupełniono wszystkich pól", Toast.LENGTH_LONG).show()
            return
        }
        if(r.toInt()<0||r.toInt()>255){
            Toast.makeText(this, "liczba r poza skala (0-255)", Toast.LENGTH_LONG).show()
            return
        }
        if(g.toInt()<0||g.toInt()>255){
            Toast.makeText(this, "liczba g poza skala (0-255)", Toast.LENGTH_LONG).show()
            return
        }
        if(b.toInt()<0||b.toInt()>255){
            Toast.makeText(this, "liczba b poza skala (0-255)", Toast.LENGTH_LONG).show()
            return
        }
        if(size.toInt()<0||radius.toInt()<0){
            Toast.makeText(this, "radius i/lub mniejsze niz 0", Toast.LENGTH_LONG).show()
            return
        }

        val defSettings = File("/data/data/com.example.prm_02", "settings.txt")
        val writer = FileWriter(defSettings)
        writer.append(r+"\n"+g+"\n"+b+"\n"+size+"\n"+radius)
        writer.flush()
        writer.close()
        finish()

    }
    fun onBackSettings(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}


