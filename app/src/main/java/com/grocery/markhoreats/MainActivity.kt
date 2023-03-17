package com.grocery.markhoreats

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.grocery.markhoreats.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("HomeFragment", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token: String? = task.result

                // Log and toast
                val msg = getString(R.string.msg_token_fmt, token)
                Log.d("HomeFragment", msg)
                //Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            })

        /*FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("MainActivity", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d("MainActivity", msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(
                NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW)
            )
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}