package com.example.firebasecloudmessaging

data class PushNotification(
    val data: NotificationData,
    val to: String
)
