package com.example.parkingmeter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:ParkingMeterViewModel
    private lateinit var buttonCalculateChange: Button
    private lateinit var amountReceived: EditText
    private lateinit var amountToDeduct: EditText
    private lateinit var changeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ParkingMeterViewModel::class.java)

        amountReceived = findViewById(R.id.amount_received)
        amountToDeduct = findViewById(R.id.amount_to_deduct)
        changeTextView = findViewById(R.id.change_textView)

        buttonCalculateChange = findViewById(R.id.button_calculate_change)
        buttonCalculateChange.setOnClickListener {
            viewModel.getChange(amountReceived.text.toString(),
                                    amountToDeduct.text.toString())
        }

        observeComponentData()
    }

    private fun observeComponentData() {
        viewModel.changeCalculated().observe(this) {
            changeTextView.text = it
        }
    }
}