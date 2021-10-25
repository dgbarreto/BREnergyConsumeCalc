package com.avanade.brenergyconsumecalc

import android.icu.number.NumberFormatter
import android.icu.text.NumberFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.avanade.brenergyconsumecalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private val VALUE_YELLOW = 0.01874;
    private val VALUE_RED = 0.09492;
    private val COST_ENERGY =  0.534;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calc.setOnClickListener { calculate() }
    }

    fun calculate(){
        val potency = binding.potency.text.toString().toDouble()
        val hours = binding.hoursOfUse.text.toString().toDouble()
        val days = binding.daysOfUse.text.toString().toDouble()
        var valueFlag : Double

        if(binding.flagYellow.isChecked){
            valueFlag = VALUE_YELLOW
        }
        else if(binding.flagRed.isChecked){
            valueFlag = VALUE_RED
        }
        else{
            valueFlag = 0.0
        }

        val consumption = potency * hours * days / 1000
        val baseCalc = consumption * COST_ENERGY
        val finalCalc = baseCalc + (baseCalc * valueFlag)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.result.text = java.text.NumberFormat.getCurrencyInstance(resources.configuration.locales.get(0)).format(finalCalc)
        }
        else{
            binding.result.text = java.text.NumberFormat.getCurrencyInstance(resources.configuration.locale).format(finalCalc)
        }
    }
}