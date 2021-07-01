package com.example.prm_p01.adapter

import android.R
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.prm_p01.AddMainActivity
import com.example.prm_p01.MainActivity
import com.example.prm_p01.Shared
import com.example.prm_p01.databinding.ActivityMainBinding
import com.example.prm_p01.databinding.OperacjaListBinding
import com.example.prm_p01.model.Operacja
import java.security.AccessController.getContext


class OperacjaItem(val binding: OperacjaListBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(operacja: Operacja){
        binding.apply {
            typ.text = operacja.typ
            miejsce.text=operacja.miejsce
            kategoria.text=operacja.kategoria
            data.text=operacja.data.toString()
            kwota.text=operacja.kwota.toString()
        }
    }
}

class OperacjaAdapter() : RecyclerView.Adapter<OperacjaItem>() {
    var selectedItem: Int? = null
    var operacje: List<Operacja> = emptyList()
        set(value){
            field=value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperacjaItem {
        val binding = OperacjaListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )
        return OperacjaItem(binding).also { holder ->
            binding.root.setOnClickListener { changeSelection(holder.layoutPosition) }
            binding.root.setOnLongClickListener { changeSelection(holder.layoutPosition)
                AlertDialog.Builder(parent.context)
                        .setTitle("Usuniecie wpisu")
                        .setMessage("Czy chcesz usunac zaznaczony wpis?")
                        .setPositiveButton(R.string.yes) { dialog, which ->
                            selectedItem?.let { Shared.operacjaLIst.removeAt(it) }
                            val intent = Intent(parent.context, MainActivity::class.java)
                            startActivity(parent.context,intent,null)
                        }
                        .setNegativeButton(R.string.no, null)
                        .setIcon(R.drawable.ic_dialog_alert)
                        .show()
                return@setOnLongClickListener true
            }
        }

    }

    override fun getItemCount(): Int = operacje.size

    override fun onBindViewHolder(holder: OperacjaItem, position: Int) {
        holder.bind(operacje[position])
        holder.binding.frame.visibility = if (selectedItem == position) View.VISIBLE else View.INVISIBLE
    }

    fun changeSelection(position: Int){
        val oldOne = selectedItem
        selectedItem=position
        oldOne?.let {notifyItemChanged(it)  }

        notifyItemChanged(position)

    }
    fun selectedItem(): Int? {
        return selectedItem
    }





}