package com.example.phonesexpertsystem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.phonesexpertsystem.databinding.ActivityMainBinding

//Экран выбора хар-к пользователем
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // переменная отвечающая за верстку
    private lateinit var vm: AppModel // класс отвечающий за методы поиска телефонов
    val phone = PhoneCharacteristics(// инициализация класса характеристик
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        hasNFC = false,
        hasFingerprint = false,
        hasSD = false
    )


    override fun onCreate(savedInstanceState: Bundle?) {//создать экран
        super.onCreate(savedInstanceState)

        //инициализация классов
        vm = ViewModelProvider(this)[AppModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSpinners() //настроить выдвигающиеся окна
        setUpAdaptersListener() // настроить сохранение данных с выползающих окон
        setUpCheckers() //настроить окна с галочками

        binding.button.setOnClickListener {
            //Если нажата кнопка - отправить все полученные данные на другой экран
            intent = Intent(this, PhoneListActivity::class.java)
            intent.putExtra("phone", phone)
            startActivity(intent)
        }
    }

    private fun setUpSpinners() {//устанавливает какие данные будут в каждом из окон
        val adapterPrice = ArrayAdapter.createFromResource(
            this,
            R.array.Цены,
            android.R.layout.simple_spinner_item
        )
        adapterPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.moneyCheck.adapter = adapterPrice

        val adapterType = ArrayAdapter.createFromResource(
            this,
            R.array.Типы,
            android.R.layout.simple_spinner_item
        )
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.typeCheck.adapter = adapterType

        val adapterBrand = ArrayAdapter.createFromResource(
            this,
            R.array.Бренды,
            android.R.layout.simple_spinner_item
        )
        adapterBrand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.brandCheck.adapter = adapterBrand


        val adapterMemory = ArrayAdapter.createFromResource(
            this,
            R.array.Память,
            android.R.layout.simple_spinner_item
        )
        adapterMemory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.memoryCheck.adapter = adapterMemory

        val adapterColor = ArrayAdapter.createFromResource(
            this,
            R.array.Цвета,
            android.R.layout.simple_spinner_item
        )
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.colorCheck.adapter = adapterColor

        val adapterBattery = ArrayAdapter.createFromResource(
            this,
            R.array.Батареи,
            android.R.layout.simple_spinner_item
        )
        adapterBattery.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.batteryCheck.adapter = adapterBattery

        val adapterCam = ArrayAdapter.createFromResource(
            this,
            R.array.Камеры,
            android.R.layout.simple_spinner_item
        )
        adapterCam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.camCheck.adapter = adapterCam

    }

    private fun setUpAdaptersListener() {
        //если цена > 15к то телефон по умолчанию - смартфон
        binding.moneyCheck.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val resultArray = resources.getStringArray(R.array.Цены)
                val result = resultArray[p2]
                binding.typeCheck.isEnabled = result == "Меньше 15к" || result == "Не важно"
                if (result != "Меньше 15к") {
                    phone.type = "Смартфон"
                }
                //сохранение выбора
                phone.price = result
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.e("check", "nothing")
            }

        }
        binding.typeCheck.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val resultArray = resources.getStringArray(R.array.Типы)
                val result = resultArray[p2]
                phone.type = result
                //сохранение выбора
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.e("check", "nothing")
            }

        }
        binding.brandCheck.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val resultArray = resources.getStringArray(R.array.Бренды)
                val result = resultArray[p2]
                //сохранение выбора
                phone.name = result
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.e("check", "nothing")
            }

        }
        binding.memoryCheck.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val resultArray = resources.getStringArray(R.array.Память)
                val result = resultArray[p2]
                //сохранение выбора
                phone.ram = result
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.e("check", "nothing")
            }

        }
        binding.batteryCheck.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val resultArray = resources.getStringArray(R.array.Батареи)
                val result = resultArray[p2]
                //сохранение выбора
                phone.battery = result
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.e("check", "nothing")
            }

        }

        binding.camCheck.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val resultArray = resources.getStringArray(R.array.Камеры)
                val result = resultArray[p2]
                //сохранение выбора
                phone.camera = result
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.e("check", "nothing")
            }

        }
        binding.colorCheck.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val resultArray = resources.getStringArray(R.array.Цвета)
                val result = resultArray[p2]
                //сохранение выбора
                phone.color = result
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.e("check", "nothing")
            }

        }

    }

    private fun setUpCheckers() {
        //если нажат крестик - сохраняем
        binding.nfc.setOnCheckedChangeListener { _, b ->
            phone.hasNFC = b
        }
        binding.scanner.setOnCheckedChangeListener { _, b ->
            phone.hasFingerprint = b
        }
        binding.sd.setOnCheckedChangeListener { _, b ->
            phone.hasSD = b
        }
    }

}