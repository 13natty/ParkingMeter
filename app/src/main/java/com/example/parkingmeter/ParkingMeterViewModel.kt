package com.example.parkingmeter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ParkingMeterViewModel: ViewModel() {

    private val change = MutableLiveData<String>()

    fun changeCalculated(): LiveData<String> = change

    fun getChange(amountReceived: String, amountToDeduct: String) {
        if(amountReceived.isEmpty() || amountToDeduct.isEmpty()) {
            change.postValue("Please provide the amounts")
            return
        }
        val received = amountReceived.toDouble()
        val toDeduct = amountToDeduct.toDouble()
        if(toDeduct > received){
            change.postValue("amount to be deducted cannot be grater than amount received")
            return
        }
        val changeVal = received - toDeduct
        if(changeVal.equals(0.0)) {
            change.postValue("R0.00")
            return
        }

        getDenominations(changeVal)
    }

    private fun getDenominations(changeVal: Double) {

        var money = changeVal
        var finalString ="The total change is R$money\n"
        while (money > 0){
            when {
                money >= 200 -> {
                    //these are the R200 notes
                    val numNotes = (money / 200).toInt()
                    finalString+="$numNotes x R200 \n"
                    money -= numNotes * 200
                }
                money >= 100 -> {
                    //these are the R100 notes
                    val numNotes = (money / 100).toInt()
                    finalString+="$numNotes x R100 \n"
                    money -= numNotes * 100
                }
                money >= 50 -> {
                    //these are the R50 notes
                    val numNotes = (money / 50).toInt()
                    finalString+="$numNotes x R50 \n"
                    money -= numNotes * 50
                }
                money >= 20 -> {
                    //these are the R20 notes
                    val numNotes = (money / 20).toInt()
                    finalString+="$numNotes x R20 \n"
                    money -= numNotes * 20
                }
                money >= 10 -> {
                    //these are the R10 notes
                    val numNotes = (money / 10).toInt()
                    finalString+="$numNotes x R10 \n"
                    money -= numNotes * 10
                }
                money >= 5 -> {
                    //these are the R5 coins
                    val numCoins = (money / 5).toInt()
                    finalString+="$numCoins x R5 \n"
                    money -= numCoins * 5

                }
                money >= 2 -> {
                    //these are the R2 coins
                    val numCoins = (money / 2).toInt()
                    finalString+="$numCoins x R2 \n"
                    money -= numCoins * 2

                }
                money >= 1 -> {
                    //these are the R1 notes
                    val numCoins = (money / 1).toInt()
                    finalString+="$numCoins x R1 \n"
                    money -= numCoins * 1

                }
            }
        }
        change.postValue(finalString)
    }
}