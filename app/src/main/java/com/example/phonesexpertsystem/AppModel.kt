package com.example.phonesexpertsystem

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppModel : ViewModel() {
    private var phone = MutableLiveData<PhoneCharacteristics>()


    fun sendPhone(new: PhoneCharacteristics) {
        phone.value = new
    }

    fun choosePhones(): MutableList<Phone> {
        Log.e("!!!", "!!!!!")
        Log.e("ph", "${phone.value}")
        val newBase = mutableListOf<Phone>()
        for (i in base) {
            with(phone.value) {
                if (
                    this?.name?.let { checkBrand(it, i) } == true
                    && checkPrice(this.price, i)
                    && checkType(this.type, i)
                    && checkRam(this.ram, i)
                    && checkBattery(this.battery, i)
                    && checkCamera(this.camera, i)
                    && checkColor(this.color, i)
                    && this.hasNFC == i.hasNFC
                    && this.hasFingerprint == i.hasFingerprint
                    && this.hasSD == i.hasSD
                ) {
                    newBase.add(i)
                }
            }
        }
        return newBase
    }

    private fun checkColor(color: String?, i: Phone): Boolean {
        if (color == "Не важно") {
            return true
        }
        if (color == i.color) {
            return true
        } else
            if (color == "Другие") {
                if (i.color != "Чёрный" && i.color != "Белый") {
                    return true
                }
            }
        return false
    }

    private fun checkCamera(camera: String?, i: Phone): Boolean {
        if (camera == "Не важно") {
            return true
        }
        when (camera) {
            "4–10 Мпикс" -> return i.camera!! in 4..10
            "12–20 Мпикс" -> return i.camera!! in 12..20
            "более 21 Мпикс" -> return i.camera!! >= 21
        }
        return false
    }

    private fun checkRam(ram: String?, i: Phone): Boolean {
        if (ram == "Не важно") {
            return true
        }
        when (ram) {
            "до 16 ГБ" -> return i.ram!! < 16
            "16–32 ГБ" -> return i.ram!! in 16..32
            "32–64 ГБ" -> return i.ram!! in 32..64
            "64–128 ГБ" -> return i.ram!! in 64..128
            "> 128 ГБ" -> return i.ram!! > 128
        }
        return false
    }

    private fun checkBattery(battery: String?, i: Phone): Boolean {
        if (battery == "Не важно") {
            return true
        }
        when (battery) {
            "до 3000 мАч" -> return i.battery!! < 3000
            "3000–3500 мАч" -> return i.battery!! in 3000..3500
            "3500–4000 мАч" -> return i.battery!! in 3500..4000
            "более 4000 мАч" -> return i.battery!! > 4000
        }
        return false
    }

    private fun checkType(type: String?, i: Phone): Boolean {
        return type == i.type
    }

    private fun checkPrice(price: String?, i: Phone): Boolean {
        Log.e("price", price.toString())
        if (price == "Не важно") {
            return true
        }
        when (price) {
            "Меньше 15к" -> return i.price!! < 15000
            "15к-30к" -> return i.price!! in 15000..30000
            "30к-50к" -> return i.price!! in 30000..50000
            "Больше 50к" -> return i.price!! > 50000
        }
        return false
    }

    private fun checkBrand(name: String, i: Phone): Boolean {
        if (name == "Не важно") {
            return true
        }
        if (name == "Другие") {
            return i.name?.contains("Samsung") == false && i.name?.contains("iPhone") == false && i.name?.contains(
                "Huawei"
            ) == false && i.name?.contains("Xiaomi") == false
        }
        return i.name?.contains(name) == true
    }
}