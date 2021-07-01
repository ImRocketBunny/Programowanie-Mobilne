package com.example.prm_p01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prm_p01.views.Wykres

class WykresMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(intent.getDoubleArrayExtra("lista")?.let { Wykres(this, it) })
    }
}