package tauch.xavier.mynewskt.Utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v4.app.NotificationCompat
import tauch.xavier.mynewskt.Controllers.Activities.NotifActivity
import tauch.xavier.mynewskt.Controllers.Activities.ResultActivity
import tauch.xavier.mynewskt.R

import java.text.SimpleDateFormat
import java.util.Date

import android.content.Context.MODE_PRIVATE

class NotificationReceiver : BroadcastReceiver() {

    // For data
    private val query: String? = null
    private val from_date: String? = null
    private val to_date: String? = null
    private val checkboxes: String? = null

    // ----------
    // DATA
    // ----------

    // getCurrentDay inside a String
    private val currentDay: String
        get() {
            val curDate = Date()
            val format = SimpleDateFormat("yyyyMMdd")
            return format.format(curDate)
        }

    override fun onReceive(context: Context, intent: Intent) {
        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //Create the channel object with unique ID MYNEWS_CHANNEL_ID
            val channel =
                NotificationChannel(MYNEWS_CHANNEL_ID, MYNEWS_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Daily notifications"
            mNotificationManager.createNotificationChannel(channel)
        }

        // Get data from SearchActivity

        val pendingIntent =
            PendingIntent.getActivity(context, 0, configureIntent(context), PendingIntent.FLAG_UPDATE_CURRENT)

        val mBuilder = NotificationCompat.Builder(context, MYNEWS_CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher) // notification icon
            .setContentTitle("My News Notification") // title for notification
            .setContentText("It's time for you daily news checking !")// message for notification
            .setAutoCancel(true) // clear notification after click

        mNotificationManager.notify(NotifActivity.UNIQUE_ID, mBuilder.build())
    }

    // ----------
    // INTENT
    // ----------

    private fun configureIntent(context: Context): Intent {
        val preferences = context.getSharedPreferences(USER_PREF, MODE_PRIVATE)

        val intent = Intent(context, ResultActivity::class.java)

        intent.putExtra(query, preferences.getString(SEARCH_PREF, null))
        intent.putExtra(checkboxes, preferences.getString(CHECKED_PREF, null))
        intent.putExtra(from_date, FROM_DATE_VALUE)
        intent.putExtra(to_date, currentDay)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP


        return intent
    }

    companion object {

        private val MYNEWS_CHANNEL_ID = "com.example.youpiman.mynews"
        private val MYNEWS_CHANNEL_NAME = "MYNEWS Channel"
        private val FROM_DATE_VALUE = "20000101"

        val USER_PREF = "USER_PREF"
        val SEARCH_PREF = "SEARCH_PREF"
        val CHECKED_PREF = "CHECKED_PREF"
    }
}