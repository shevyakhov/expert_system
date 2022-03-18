package com.example.phonesexpertsystem

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phonesexpertsystem.databinding.PhoneItemBinding


class PhoneAdapter : RecyclerView.Adapter<PhoneAdapter.Holder>() {
    private val phoneList = ArrayList<Phone>()

    class Holder(v: View) : RecyclerView.ViewHolder(v) {
        private val binding = PhoneItemBinding.bind(v)
        val context = v.context
        fun bind(p: Phone) = with(binding) {
            name.text = p.name
            price.text = p.price.toString() + " ₽"
            camera.text = p.camera.toString() +" Мпикс"
            battery.text = p.battery.toString()+ " мАч"
            ram.text = p.ram.toString()+ " ГБ"
            color.text = p.color
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.phone_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(phoneList[position])
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addPhone(f: Phone) {
        phoneList.add(f)
        notifyDataSetChanged()
    }
}