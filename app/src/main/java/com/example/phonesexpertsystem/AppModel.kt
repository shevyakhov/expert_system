package com.example.phonesexpertsystem

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//класс отвечающий за связь элементов UI и методов
class AppModel : ViewModel() {

    //небходимые характеристики телефона с доступом из любой точки приложения
    private var phone = MutableLiveData<PhoneCharacteristics>()

    //переприсвоить хар-ки
    fun sendPhone(new: PhoneCharacteristics) {
        phone.value = new
    }

    // метод вычисляющий список подходящих моделей
    fun choosePhones(): MutableList<Phone> {

        val newBase = mutableListOf<Phone>()//формирование массива с телефонами
        for (i in base) {//проход по всей базе данных
            with(phone.value) {
                if (//вызывает методы проверки хар-к
                    this?.name?.let { checkBrand(it, i) } == true  //проверяет подходит ли бренд
                    && checkPrice(this.price, i)               //проверяет подходит ли цена
                    && checkType(this.type, i)                 //проверяет подходит ли бренд
                    && checkRam(this.ram, i)                   //проверяет подходит ли память
                    && checkBattery(this.battery, i)           //проверяет подходит ли батарея
                    && checkCamera(this.camera, i)             //проверяет подходит ли камера
                    && checkColor(this.color, i)               //проверяет подходит ли цвет
                    && this.hasNFC == i.hasNFC                 //проверяет есть ли NFC
                    && this.hasFingerprint == i.hasFingerprint //проверяет есть ли сканнер пальца
                    && this.hasSD == i.hasSD                   //проверяет подходит ли SD разъем
                ) {
                    newBase.add(i) // если телефон подходит - добавляем в список
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