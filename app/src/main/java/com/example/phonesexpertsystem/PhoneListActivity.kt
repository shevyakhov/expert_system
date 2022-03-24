package com.example.phonesexpertsystem

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phonesexpertsystem.databinding.ActivityPhoneListBinding

//Экран вывода подходящих телефонов
class PhoneListActivity : AppCompatActivity() {
    private lateinit var vm: AppModel
    private lateinit var binding: ActivityPhoneListBinding
    private val phoneAdapter = PhoneAdapter() // листающийся список

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val phone = intent.getParcelableExtra<PhoneCharacteristics>("phone")// достать данные из 1го экрана

        binding = ActivityPhoneListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this)[AppModel::class.java]

        if (phone != null) {
            //использовать хар-ки для поиска списка подходящих телефонов
            vm.sendPhone(phone)
        }

        bindingInit()//метод верстки


    }

    private fun bindingInit() {
        binding.apply {
            //проинициализировать слайдер
            phoneList.layoutManager =
                LinearLayoutManager(this@PhoneListActivity, RecyclerView.VERTICAL, false)
            phoneList.adapter = phoneAdapter
        }
        val newBase = vm.choosePhones() //найти список подходящих телефонов
        // вывести пользователю сообщение о кол-ве подходящих моделей
        Toast.makeText(this, "Вам подходит ${newBase.size} вариантов", Toast.LENGTH_SHORT).show()

        for (i in newBase){ // добавить все найденные модели в слайдер
            phoneAdapter.addPhone(i)
        }

    }

}