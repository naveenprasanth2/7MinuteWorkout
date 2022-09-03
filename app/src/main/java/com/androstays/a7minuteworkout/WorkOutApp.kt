package com.androstays.a7minuteworkout

import android.app.Application

class WorkOutApp: Application() {
    val db by lazy{
        HistoryDataBase.getInstance(this)
    }
}