package com.example.notificationexam

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.graphics.drawable.IconCompat

class MainActivity : AppCompatActivity() {
    var NOTIFICATION_ID_BASIC = 1000 //basic
    val NOTIFICATION_ID_BIGTV = 1001 //BIGTEXT
    val NOTIFICATION_ID_BIGPIC = 1002 //BIGPICTURE
    val NOTIFICATION_ID_IN = 1003 //INBOX
    val NOTIFICATION_ID_MSG = 1004 //MESSAGING
    val NOTIFICATION_ID_IR = 1005 //INLINE REPLY
    val NOTIFICATION_ID_HEAD = 1006 //HEAD UP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val basicbtn = findViewById<Button>(R.id.basic_btn)
        val bigtvbtn = findViewById<Button>(R.id.bigtv_btn)
        val bigpicbtn = findViewById<Button>(R.id.bigpic_btn)
        val inboxbtn = findViewById<Button>(R.id.inbox_btn)
        val msgbtn = findViewById<Button>(R.id.msg_btn)
        val inlinebtn = findViewById<Button>(R.id.inline_btn)
        val headupbtn = findViewById<Button>(R.id.headup_btn)

        basicbtn.setOnClickListener{
            basicnoti()
        }
        bigtvbtn.setOnClickListener{
            bigtvnoti()
        }
        bigpicbtn.setOnClickListener{
            bigpicnoti()
        }
        inboxbtn.setOnClickListener{
            inboxnoti()
        }
        msgbtn.setOnClickListener{
            messagingnoti()
        }
        inlinebtn.setOnClickListener{
            inlinereplynoti()
        }
        headupbtn.setOnClickListener{
            headupnoti()
        }


    }

    private fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) { //노티피케이션 채널 만들기
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun basicnoti(){ //가장 기본적인 노티피케이션
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT,
            false, getString(R.string.app_name), "App notification channel") //노티피케이션 채널 만들기

        val channelId = "$packageName-${getString(R.string.app_name)}" // 채널을 만들때 사용한 채널 ID. 노티 등록할 때 필요
        val title = "Android Developer" //보여줄 제목
        val content = "Notifications in Android P" //보여줄 내용

        val intent = Intent(baseContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)    // PendingIntent. 노티를 터치했을 때 액티비티를 실행하기 위해 필요

        val builder = NotificationCompat.Builder(this, channelId)  // 노티피케이션을 만드는 빌더. 여기에 채널 ID를 인자로 넣어야 함
        builder.setSmallIcon(R.drawable.ic_launcher_background)    // 노티에 보이는 아이콘
        builder.setContentTitle(title)    // 노티에 보이는 제목
        builder.setContentText(content)    // 노티에 보이는 내용
        builder.priority = NotificationCompat.PRIORITY_DEFAULT    // 노티의 중요도를 나타낸다 중요도에 따라 노티가 보이지 않을 수도 있음
        builder.setAutoCancel(true)   // AutoCancel이 true이면 사용자가 노티를 터치했을 때 사라지게 합니다. false면 눌러도 사라지지 않음
        builder.setContentIntent(pendingIntent)   // 위에서 만든 PendingIntent를 노티에 등록한다

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_ID_BASIC, builder.build())    // NotificationManager.notify()으로 노티를 등록한다.

    }

    fun bigtvnoti(){ //BigText 스타일 적용한 노티피케이션
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel")

        val channelId = "$packageName-${getString(R.string.app_name)}"
        val title = "Android Developer"
        val content = "Notifications in Android P"

        val intent = Intent(baseContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val bigText = "Android 9 introduces several enhancements to notifications," +
                " all of which are available to developers targeting API level 28 and above."
        val style = NotificationCompat.BigTextStyle()   // BigTextStyle 객체 생성
        style.bigText(bigText)    // 스타일에 bigText를 설정

        val builder = NotificationCompat.Builder(this, channelId)
        builder.setSmallIcon(R.drawable.ic_launcher_background)
        builder.setContentTitle(title)
        builder.setContentText(content)
        builder.setStyle(style)   // 빌더에 BigTextStyle을 설정
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_ID_BIGTV, builder.build())
    }

    fun bigpicnoti(){ //BigPicture 스타일 적용한 노티피케이션
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel")

        val channelId = "$packageName-${getString(R.string.app_name)}"
        val title = "Castle"
        val content = "Welcome to my catle"

        val intent = Intent(baseContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val style = NotificationCompat.BigPictureStyle()    // BigPictureStyle 객체 생성
        style.bigPicture(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_foreground))   // 스타일에 Big Picture를 등록. 인자는 비트맵 이미지를 받는데 BitmapFactory로 리소스를 비트맵으로 변환

        val builder = NotificationCompat.Builder(this, channelId)
        builder.setSmallIcon(R.drawable.ic_launcher_background)
        builder.setContentTitle(title)
        builder.setContentText(content)
        builder.setStyle(style)   // 빌더에 스타일 설정
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_ID_BIGPIC, builder.build())
    }

    fun inboxnoti(){ //Inbox 스타일 적용한 노티피케이션 (많은 양의 아이템들을 표현하는데 좋은 노티, 이메일 등에서 쓰임)
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel")

        val channelId = "$packageName-${getString(R.string.app_name)}"
        val title = "3 Mails"   // 노티의 제목
        val content = "+5 Mails"   // 노티가 접혔을 때 보이는 텍스트. 펼쳤을 때는 보이지 않음

        val intent = Intent(baseContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val style = NotificationCompat.InboxStyle()   // InboxStyle 객체를 생성
        style.addLine("Mail1 ...")    // addLine()으로 리스트의 아이템들을 등록하며, 노티가 펼쳐졌을 때 보이는 텍스트
        style.addLine("Mail2 ...")
        style.addLine("Mail3 ...")

        val builder = NotificationCompat.Builder(this, channelId)
        builder.setSmallIcon(R.drawable.ic_launcher_background)
        builder.setContentTitle(title)
        builder.setContentText(content)
        builder.setStyle(style)   // 빌더에 스타일을 설정
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_ID_IN, builder.build())
    }

    fun messagingnoti(){ //Messaging 스타일 적용한 노티 (사용자 간의 대화 내용 표현)
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel")

        val channelId = "$packageName-${getString(R.string.app_name)}"
        val title = "Messaging style title"
        val content = "Messaging style content"

        val intent = Intent(baseContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val userIcon1 = IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground)  // Person 객체 생성에 쓰이는 Icon 객체 생성
        val userIcon2 = IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground)
        val userIcon3 = IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground)
        val userName1 = "Chacha"    // Person 객체의 이름
        val userName2 = "Android"
        val userName3 = "JS"
        val timestamp = System.currentTimeMillis()   // Message 등록할 때 쓰이는 시간. 샘플이르모 현재 시간을 넣음
        val user1 = Person.Builder().setIcon(userIcon1).setName(userName1).build()  // Person.Builder를 이용하여 Person 객체 생성. 여기에 아이콘과 이름이 들어감
        val user2 = Person.Builder().setIcon(userIcon2).setName(userName2).build()
        val user3 = Person.Builder().setIcon(userIcon3).setName(userName3).build()
        val style = NotificationCompat.MessagingStyle(user3)  // MessagingStyle 객체를 생성하며, 인자로 Person 객체를 전달. Person은 메시지를 받는 사람
        style.addMessage("You can get great deals there", timestamp, user1)  // User1이 보낸 메시지
        style.addMessage("I know what to get", timestamp, user2)  // User2이 보낸 메시지

        val builder = NotificationCompat.Builder(this, channelId)  // 빌더를 생성
        builder.setSmallIcon(R.drawable.ic_launcher_background)
        builder.setContentTitle(title)
        builder.setContentText(content)
        builder.setStyle(style)  // 빌더에 스타일 설정
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_ID_MSG, builder.build())
    }

    fun inlinereplynoti(){ //Inline Reply 스타일 적용한 노티 (노티에서 바로 응답을 할 수 있는 기능), MessagingStyle의 코드에서 추가
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel")

        val channelId = "$packageName-${getString(R.string.app_name)}"
        val title = "3 Messages"
        val content = "+5 Messages"

        val intent = Intent(baseContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val replyLabel = "Enter your reply here"
        val remoteInput = RemoteInput.Builder("key_reply")
            .setLabel(replyLabel)
            .build()
        val replyAction = NotificationCompat.Action.Builder(
            android.R.drawable.sym_action_chat, "REPLY", pendingIntent)   // Reply Action을 만듬
            .addRemoteInput(remoteInput)  // 잘 모르겠음
            .setAllowGeneratedReplies(true)  // 앱 알림에 추천 당잡이나 작업을 표시
            .build()

        val userIcon1 = IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground)
        val userIcon2 = IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground)
        val userIcon3 = IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground)
        val userName1 = "Chacha"
        val userName2 = "Android"
        val userName3 = "JS"
        val timestamp = System.currentTimeMillis()
        val user1 = Person.Builder().setIcon(userIcon1).setName(userName1).build()
        val user2 = Person.Builder().setIcon(userIcon2).setName(userName2).build()
        val user3 = Person.Builder().setIcon(userIcon3).setName(userName3).build()
        val style = NotificationCompat.MessagingStyle(user3)
        style.addMessage("You can get great deals there", timestamp, user1)
        style.addMessage("I know what to get", timestamp, user2)

        val builder = NotificationCompat.Builder(this, channelId)
        builder.setSmallIcon(R.drawable.ic_launcher_background)
        builder.setContentTitle(title)
        builder.setContentText(content)
        builder.setStyle(style)
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)
        builder.addAction(replyAction)    // 빌더에 Reply Action 추가
        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel,
            "DISMISS", pendingIntent); // Dismiss Action을 추가. 버튼의 아이콘과 버튼이 눌렸을 때 실행될 PendingIntent를 인자로 넣음

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_ID_IR, builder.build())
    }

    fun headupnoti(){ //Head up noti 따로 스테이터스바를 내리지 않아도 상단에 뜸(기본 노티에 적용) AndroidManifest.xml에 퍼미션 추가해야 하는 것 있음
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_HIGH, false,
            getString(R.string.app_name), "App notification channel")   // 채널을 만들 때 중요도를 IMPORTANCE_HIGH로 설정해야함

        val channelId = "$packageName-${getString(R.string.app_name)}"
        val title = "Android Developer"
        val content = "Notifications in Android P"

        val intent = Intent(baseContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val fullScreenPendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)    // PendingIntent를 만듬. 빌더에 fullScreenPendingIntent를 등록하기 때문에 변수이름을 fullScreenPendingIntent라고 설정

        val builder = NotificationCompat.Builder(this, channelId)
        builder.setSmallIcon(R.drawable.ic_launcher_background)
        builder.setContentTitle(title)
        builder.setContentText(content)
        builder.priority = NotificationCompat.PRIORITY_HIGH   // 빌더의 priority를 PRIORITY_HIGH로 설정
        builder.setAutoCancel(true)
        builder.setFullScreenIntent(fullScreenPendingIntent, true)   // fullScreenPendingIntent를 등록

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_ID_HEAD, builder.build())
    }
}