package mx.ggl.atizappanmobile.model

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import mx.ggl.atizappanmobile.R
import mx.ggl.atizappanmobile.ui.notifications.NotificationsFragment

class ServicioMensajesFB : FirebaseMessagingService() {
    val db = Firebase.firestore
    private val channelName = "alertasPC"
    private val channelId = "mx.itesm.ycr.firebase"

    override fun onNewToken(token: String) {
        val values = hashMapOf("token" to token)
        db.collection("Tokens")
            .add(values)
            .addOnSuccessListener {
                FirebaseMessaging.getInstance().subscribeToTopic("alertas")
                Toast.makeText(this, "Sucess", Toast.LENGTH_LONG)
                    .show()
            }
            .addOnFailureListener {
                Toast.makeText(this,
                    it.toString(), Toast.LENGTH_LONG).show()
            }
    }



    override fun onMessageReceived(message: RemoteMessage) {
        if (message.notification != null) {
            generarNotificacion(message)
        }
    }

    private fun generarNotificacion(message: RemoteMessage) {
        val intent =Intent(this,NotificationsFragment::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent =PendingIntent.getActivity(this,
            0,intent,PendingIntent.FLAG_MUTABLE)

        var builder =NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.alert_high)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
        builder = builder.setContent(crearVistaRemota(message))

        val admNotificaciones = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canalNotificaciones =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            admNotificaciones.createNotificationChannel(canalNotificaciones)
        }
        //Genera la notif LOCALMENTE
        admNotificaciones.notify(0, builder.build())
    }
    @SuppressLint("RemoteViewLayout")
    private fun crearVistaRemota(message: RemoteMessage): RemoteViews {
        val titulo = message.notification?.title!!
        val mensaje = message.notification?.body!!
        val vistaRemota = RemoteViews("mx.ggl.atizappanmobile", R.layout.notificacion)
        vistaRemota.setTextViewText(R.id.title_notif, titulo)
        vistaRemota.setTextViewText(R.id.text_notif, mensaje)
        vistaRemota.setImageViewResource(R.id.imgIcono, R.drawable.alert_high)
        return vistaRemota
    }
}