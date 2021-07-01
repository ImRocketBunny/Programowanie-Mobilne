package com.example.prm_02

import android.R
import android.app.AlertDialog
import android.content.Intent
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.HandlerCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.prm_02.databinding.OperacjaListBinding
import com.example.prm_02.model.Fotka
import kotlin.concurrent.thread


class OperacjaItem(val binding: OperacjaListBinding) :RecyclerView.ViewHolder(binding.root){
    fun bind(fotka: Fotka){
        binding.apply {
            fotkaView.setImageDrawable(fotka.zdjecie)
        }
    }

}

class ListAdapter(): RecyclerView.Adapter<OperacjaItem>(){
    private val handler = HandlerCompat.createAsync(Looper.getMainLooper())
    var selectedItem: Int? = null
    var fotki: List<Fotka> = emptyList()
        set(value){
            field=value
            handler.post{
                notifyDataSetChanged()
            }

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
                var opis = ""
                selectedItem?.let { it1 -> opis+=Shared.fotkaList.get(it1).opis }
                AlertDialog.Builder(parent.context)
                    .setTitle("Opis")
                    .setMessage(opis)
                    .setPositiveButton(R.string.yes) { dialog, which ->

                    }
                    .setNegativeButton(R.string.no, null)
                    .setIcon(R.drawable.ic_dialog_alert)
                    .show()
                return@setOnLongClickListener true
            }
        }
    }

    override fun onBindViewHolder(holder: OperacjaItem, position: Int) {
        holder.bind(fotki[position])
        holder.binding.frame.visibility = if (selectedItem == position) View.VISIBLE else View.INVISIBLE
    }

    override fun getItemCount(): Int {
        return fotki.size
    }
    fun changeSelection(position: Int){
        val oldOne = selectedItem
        selectedItem=position
        oldOne?.let {notifyItemChanged(it)  }

        notifyItemChanged(position)

    }

}



