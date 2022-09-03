package com.androstays.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androstays.a7minuteworkout.databinding.ActivityFinishBinding
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarFinish)
        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarFinish?.setNavigationOnClickListener {
            finish()
        }

        binding?.finishButton?.setOnClickListener {
            finish()
        }

        val dao =  (application as WorkOutApp).db.historyDao()
        addDateToDataBase(dao)
    }

    private fun addDateToDataBase(historyDao: HistoryDao) {
        val cal = Calendar.getInstance()
        val dateTime = cal.time

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val resultDate = sdf.format(dateTime)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(resultDate))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}