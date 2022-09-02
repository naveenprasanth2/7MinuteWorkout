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
    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"
    }

    private var binding: ActivityBmiactivityBinding? = null
    private var currentVisibleView: String = METRIC_UNITS_VIEW
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

            if (currentVisibleView == METRIC_UNITS_VIEW) {
                if (validateMetricUnits()) {
                    calculateUnitsForMetricSystem()
                } else {
                    binding?.llDisplayBmiResult?.visibility = View.INVISIBLE
                    Toast.makeText(this, "enter valid data", Toast.LENGTH_SHORT).show()
                }
            } else if (currentVisibleView == US_UNITS_VIEW) {
                if (validateUsMetricUnits()) {
                    calculateUnitsForUsSystem()
                } else {
                    binding?.llDisplayBmiResult?.visibility = View.INVISIBLE
                    Toast.makeText(this, "enter valid data", Toast.LENGTH_SHORT).show()
                }
            }
        }

        makeVisibleMetricsUnitView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId: Int ->
            if (checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricsUnitView()
            } else {
                makeVisibleUSUnitView()
            }
        }
    }

    private fun calculateUnitsForMetricSystem() {
        try {
            val heightValue: Float =
                binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
            val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
            val bmiValue: Float = weightValue / (heightValue * heightValue)
            val bmiString =
                BigDecimal(bmiValue.toDouble()).setScale(2, RoundingMode.HALF_EVEN)
                    .toString()
            binding?.tvYourBMI?.text = bmiString
            displayBMIResult(bmiValue)
        } catch (e: Exception) {
            Toast.makeText(this, "enter valid data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateUnitsForUsSystem() {
        try {
            val heightValueFeet: Float =
                binding?.etUsMetricUnitHeightFeet?.text.toString().toFloat()
            val heightValueInch: Float =
                binding?.etUsMetricUnitHeightInch?.text.toString().toFloat()
            val weightValue: Float = binding?.etUsMetricUnitWeight?.text.toString().toFloat()

            val heightValue = heightValueInch + (heightValueFeet * 12)
            val bmiValue: Float = 703 * (weightValue / (heightValue * heightValue))
            val bmiString =
                BigDecimal(bmiValue.toDouble()).setScale(2, RoundingMode.HALF_EVEN)
                    .toString()
            binding?.tvYourBMI?.text = bmiString
            displayBMIResult(bmiValue)
        } catch (e: Exception) {
            Toast.makeText(this, "enter valid data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayBMIResult(bmi: Float) {
        var bmiLabel = ""
        var bmiDescription = ""
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
            bmiLabel = "Obese Class I (Moderate Obese)"
            bmiDescription = "Oops!! You really need o take care of yourself! Workout more"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(39.9f) <= 0) {
            bmiLabel = "Obese Class II (Severely Obese)"
            bmiDescription = "You are in a dangerous condition! Act Now!!!"
        } else if (bmi.compareTo(40f) > 0) {
            bmiLabel = "Obese Class III (Very Severely Obese)"
            bmiDescription = "You are in a dangerous condition! Act Now!!!"
        }

        binding?.llDisplayBmiResult?.visibility = View.VISIBLE
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

    private fun makeVisibleMetricsUnitView() {
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
        binding?.tilUsMetricUnitWeight?.visibility = View.GONE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.GONE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.GONE

        binding?.etMetricUnitWeight?.text!!.clear()
        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.llDisplayBmiResult?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUSUnitView() {
        currentVisibleView = US_UNITS_VIEW

        binding?.tilMetricUsUnitHeightFeet?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.VISIBLE
        binding?.tilUsMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitWeight?.visibility = View.GONE
        binding?.tilMetricUnitHeight?.visibility = View.GONE

        binding?.etUsMetricUnitWeight?.text!!.clear()
        binding?.etUsMetricUnitHeightFeet?.text!!.clear()
        binding?.etUsMetricUnitHeightInch?.text!!.clear()
        binding?.llDisplayBmiResult?.visibility = View.INVISIBLE
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true
        if (!Pattern.matches(
                getString(R.string.validate_number_regex),
                binding?.etMetricUnitWeight?.text.toString()
            )
        ) {
            isValid = false
        }
        if (!Pattern.matches(
                getString(R.string.validate_number_regex),
                binding?.etMetricUnitHeight?.text.toString()
            )
        ) {
            isValid = false
        }
        return isValid
    }

    private fun validateUsMetricUnits(): Boolean {
        var isValid = true
        if (!Pattern.matches(
                getString(R.string.validate_number_regex),
                binding?.etUsMetricUnitWeight?.text.toString()
            )
        ) {
            isValid = false
        }
        if (!Pattern.matches(
                getString(R.string.validate_number_regex),
                binding?.etUsMetricUnitHeightFeet?.text.toString()
            )
        ) {
            isValid = false
        }

        if (!Pattern.matches(
                getString(R.string.validate_number_regex),
                binding?.etUsMetricUnitHeightInch?.text.toString()
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