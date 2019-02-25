package tauch.xavier.mynewskt.Controllers.Activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_notif.*
import kotlinx.android.synthetic.main.form_search.*
import tauch.xavier.mynewskt.R
import tauch.xavier.mynewskt.Utils.NotificationReceiver

import java.util.*

class NotifActivity : AppCompatActivity() {


    // For Preferences
    private var mPreferences: SharedPreferences? = null
    private var set: MutableSet<String>? = HashSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notif)

        // Set the UI
        form_search_notif.visibility = View.VISIBLE
        form_search_button.visibility = View.GONE
        form_search_dates.visibility = View.GONE

        // Add back button
        setSupportActionBar(activity_notif_toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // init SharedPreferences
        mPreferences = getSharedPreferences(USER_PREFERENCES, MODE_PRIVATE)

        // loading last preferences if they exists
        loadingPreferences()

        // Configure the notification switch
        this.configureNotifications()
    }

    // ---------------
    // CONFIGURATION
    // ---------------

    private fun configureNotifications() {
        form_search_switch_notif.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (TextUtils.isEmpty(form_search_query.text)) {
                    form_search_query.error = "This field is required !"
                } else if (!checkingCheckboxes()) {
                    form_search_warning_checkboxes.visibility = View.VISIBLE
                } else {
                    savingPreferences()
                    // prepare notification
                    val intent = Intent(applicationContext, NotificationReceiver::class.java)
                    val pendingIntent = PendingIntent.getBroadcast(
                        applicationContext,
                        UNIQUE_ID,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )

                    // set notification at 12am
                    val calendar = Calendar.getInstance()
                    calendar.set(Calendar.HOUR_OF_DAY, 12)
                    calendar.set(Calendar.MINUTE, 0)
                    calendar.set(Calendar.SECOND, 0)

                    //set up alarm
                    val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        AlarmManager.INTERVAL_DAY,
                        pendingIntent
                    )
                }
            }
        }
    }

    // ---------------
    // DATA
    // ---------------

    private fun savingPreferences() {
        mPreferences!!.edit().clear().apply()
        mPreferences!!.edit().putString(SEARCH_PREF, form_search_query.text.toString()).apply()
        mPreferences!!.edit().putString(CHECKED_PREF, checkBoxString()).apply()
        mPreferences!!.edit().putStringSet(CHECKBOX_PREF, set).apply()
        mPreferences!!.edit().putBoolean(SWITCH_PREF, form_search_switch_notif.isChecked).apply()
        Log.i("switch is checked: ", "" + form_search_switch_notif.isChecked)
    }

    private fun loadingPreferences() {
        // terms of last search
        form_search_query.setText(mPreferences!!.getString(SEARCH_PREF, null))

        // last checked checkboxes
        set = mPreferences!!.getStringSet(CHECKBOX_PREF, HashSet())
        if (set != null && !set!!.isEmpty()) {
            for (checkbox in set!!) {
                when (checkbox) {
                    "Arts" -> form_search_left_checkBox_arts.isChecked = true
                    "Business" -> form_search_left_checkBox_business.isChecked = true
                    "Entrepreneurs" -> form_search_left_checkBox_entrepreneurs.isChecked = true
                    "Politics" -> form_search_right_checkBox_politics.isChecked = true
                    "Sports" -> form_search_right_checkBox_sports.isChecked = true
                    "Travel" -> form_search_right_checkBox_travel.isChecked = true
                    else -> Log.e("Checkbox Switch Error: ", checkbox)
                }
            }
        }

        // Retrieve last switch position
        val switchBoolean = mPreferences!!.getBoolean(SWITCH_PREF, false)
        Log.i("switchBoolean", ": $switchBoolean")
        // Check in saved preferences if notifications were previously enabled and restore saved state
        form_search_switch_notif.isChecked = switchBoolean
    }

    // Create a piece or URI with checked checkboxes
    private fun checkBoxString(): String {
        var checkString: String? = null

        val checkBoxes = ArrayList<CheckBox>()
        checkBoxes.add(form_search_left_checkBox_arts)
        checkBoxes.add(form_search_left_checkBox_business)
        checkBoxes.add(form_search_left_checkBox_entrepreneurs)
        checkBoxes.add(form_search_right_checkBox_politics)
        checkBoxes.add(form_search_right_checkBox_sports)
        checkBoxes.add(form_search_right_checkBox_travel)

        for (checkBox in checkBoxes) {
            if (checkBox.isChecked && checkString != null) {
                checkString = checkString + "\" \"" + checkBox.text.toString()
            } else if (checkBox.isChecked && checkString == null) {
                checkString = "news_desk(\"" + checkBox.text.toString()
            }
        }

        return checkString!! + "\")"
    }

    // Check if one checkbox is checked so we know if a warning message should be displayed
    private fun checkingCheckboxes(): Boolean {
        var checkCheckboxes: Boolean? = false

        val checkBoxes = ArrayList<CheckBox>()
        checkBoxes.add(form_search_left_checkBox_arts)
        checkBoxes.add(form_search_left_checkBox_business)
        checkBoxes.add(form_search_left_checkBox_entrepreneurs)
        checkBoxes.add(form_search_right_checkBox_politics)
        checkBoxes.add(form_search_right_checkBox_sports)
        checkBoxes.add(form_search_right_checkBox_travel)

        set = HashSet()

        for (checkBox in checkBoxes) {
            if (checkBox.isChecked) {
                checkCheckboxes = true
                set!!.add(checkBox.text.toString())
            }
        }
        return checkCheckboxes!!
    }

    companion object {

        // For Notification
        val UNIQUE_ID = 100
        val USER_PREFERENCES = "USER_PREF"

        // For Data
        val SEARCH_PREF = "SEARCH_PREF"
        val CHECKBOX_PREF = "CHECKBOX_PREF"
        val CHECKED_PREF = "CHECKED_PREF"
        val SWITCH_PREF = "SWITCH_PREF"
    }
}