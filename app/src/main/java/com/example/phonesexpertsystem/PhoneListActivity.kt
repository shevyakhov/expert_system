package com.example.phonesexpertsystem

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phonesexpertsystem.databinding.ActivityPhoneListBinding

class PhoneListActivity : AppCompatActivity() {
    private lateinit var vm: AppModel
    private val phoneAdapter = PhoneAdapter()
    private lateinit var binding: ActivityPhoneListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val phone = intent.getParcelableExtra<PhoneCharacteristics>("phone")
        binding = ActivityPhoneListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this)[AppModel::class.java]
        if (phone != null) {
            vm.sendPhone(phone)
        }
        bindingInit()


    }

    private fun bindingInit() {
        binding.apply {
            phoneList.layoutManager =
                LinearLayoutManager(this@PhoneListActivity, RecyclerView.VERTICAL, false)
            phoneList.adapter = phoneAdapter
        }
        val newBase = vm.choosePhones()
        Toast.makeText(this, "Вам подходит ${newBase.size} вариантов", Toast.LENGTH_SHORT).show()
        for (i in newBase){
            phoneAdapter.addPhone(i)
        }

    }

}