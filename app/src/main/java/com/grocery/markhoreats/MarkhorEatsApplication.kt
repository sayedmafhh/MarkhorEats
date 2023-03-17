package com.grocery.markhoreats

import android.app.Application
import com.google.firebase.FirebaseApp

class MarkhorEatsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}