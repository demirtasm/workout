package com.example.a7minuteworkout.app

import android.app.Application
import com.example.a7minuteworkout.model.HistoryDatabase

class WorkoutApp: Application() {

    val db by lazy{
        HistoryDatabase.getInstance(this)
    }
}