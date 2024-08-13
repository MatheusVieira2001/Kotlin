package com.vieira.relativelayoutt

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.vieira.relativelayoutt.databinding.ActivityMainBinding
import java.lang.Exception


class MainActivity : ComponentActivity(), View.OnClickListener{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCalc.setOnClickListener(this)
        binding.buttonClear.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_calc -> calculate()
            R.id.button_clear -> cleanInputs()
        }
    }

    fun calculate(){
        try {
            if (!isValid()){
                Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
            }else{
                val distance = binding.editDistance.text.toString().toFloat()
                val autonomy = binding.editAutonomy.text.toString().toFloat()
                val price = binding.editPrice.text.toString().toFloat()

                val calc = (distance * price) / autonomy
                val calcString = "R$ ${"%.2f".format(calc)}"
                binding.textTotalValue.text = calcString

                Toast.makeText(this, "Fim", Toast.LENGTH_SHORT).show()
            }
        }catch (E: Exception){
            Toast.makeText(this, E.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun cleanInputs(){
        binding.editDistance.text.clear()
        binding.editAutonomy.text.clear()
        binding.editPrice.text.clear()
        binding.textTotalValue.text = "R$ 0"
    }

    fun isValid(): Boolean{
        return (
                binding.editDistance.text.toString() != "" &&
                        binding.editAutonomy.text.toString() != "" &&
                        binding.editPrice.text.toString() != "" &&
                        binding.editAutonomy.text.toString().toFloat() != 0f
                )
    }


}