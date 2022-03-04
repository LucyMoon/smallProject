package com.example.firebasecloudmessaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TOPIC = "/topics/myTopic"

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        btnSend.setOnClickListener{
            val title = etTitle.text.toString()
            val message = etMessage.text.toString()
            if(title.isNotEmpty() && message.isNotEmpty()) {
                PushNotification(
                    NotificationData(title, message),
                    TOPIC
                ).also {
                    sendNotification(it)
                }
            }
        }
    }

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try{
            val response = Retrofitinstance.api.postNotification(notification)
            if(response.isSuccessful){
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            }
            else {
                Log.d(TAG, response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }
}