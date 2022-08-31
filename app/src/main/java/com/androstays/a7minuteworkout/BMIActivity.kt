package com.androstays.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.androstays.a7minuteworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.regex.Pattern

class BMIActivity : AppCompatActivity() {

    private var binding: ActivityBmiactivityBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarBMIActivity)
        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBMIActivity?.setNavigationOnClickListener { onBackPressed() }
        binding?.buttonCalculateUnits?.setOnClickListener {
            if (vaidateMetricUnits()) {
                val heightValue: Float =
                    binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
                val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmiValue: Float = weightValue / (heightValue * heightValue)
                val bmiString =
                    BigDecimal(bmiValue.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                binding?.tvYourBMI?.text = bmiString
                displayBMIResult(bmiValue)
            } else {
                binding?.llDisplayBmiResult?.visibility = View.INVISIBLE
                Toast.makeText(this, "enter valid data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayBMIResult(bmi: Float) {
        var bmiLabel: String = ""
        var bmiDescription: String = ""
        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely Underweight"
            bmiDescription = "OOPS!! You need to take care of yourself! Eat More"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Severely Underweight"
            bmiDescription = "OOPS!! You need to take care of yourself! Eat More"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Underweight"
            bmiDescription = "OOPS!! You need to take care of yourself! Eat More"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(24.9f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congrats!! You are in good shape"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Slightly Overweight"
            bmiDescription = "Oops!! You really need o take care of yourself! Workout more"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Obese Class | (Moderate Obese)"
            bmiDescription = "Oops!! You really need o take care of yourself! Workout more"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(39.9f) <= 0) {
            bmiLabel = "Obese Class || (Severely Obese)"
            bmiDescription = "You are in a dangerous condition! Act Now!!!"
        } else if (bmi.compareTo(40f) > 0) {
            bmiLabel = "Obese Class ||| (Very Severely Obese)"
            bmiDescription = "You are in a dangerous condition! Act Now!!!"
        }

        binding?.llDisplayBmiResult?.visibility = View.VISIBLE
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

    private fun vaidateMetricUnits(): Boolean {
        var isValid = true
        if (!Pattern.matches(
                "^[0-9]+|[0-9]+[.]?[0-9]+",
                binding?.etMetricUnitWeight?.text.toString()
            )
        ) {
            isValid = false
        }
        if (!Pattern.matches(
                "^[0-9]+|[0-9]+[.]?[0-9]+",
                binding?.etMetricUnitHeight?.text.toString()
            )
        ) {
            isValid = false
        }
        return isValid
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}