package tauch.xavier.mynewskt.Controllers.Activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.form_search.*
import kotlinx.android.synthetic.main.toolbar.*

import tauch.xavier.mynewskt.R

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date

class SearchActivity : AppCompatActivity() {


    lateinit var mToolbar: Toolbar

    // Query Search
    lateinit var searchQuery: EditText

    // Period of time
    lateinit var searchFromDate: EditText
    lateinit var searchToDate: EditText

    // Checkboxes

    lateinit var mCheckboxesTextview: TextView
    lateinit var artsCheckBox: CheckBox
    lateinit var businessCheckBox: CheckBox
    lateinit var entrepreneursCheckBox: CheckBox
    lateinit var politicsCheckBox: CheckBox
    lateinit var sportsCheckBox: CheckBox
    lateinit var travelCheckBox: CheckBox

    // Search button
    lateinit var searchButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mToolbar = toolbar
        searchQuery = form_search_query

        searchFromDate = form_search_fromdate
        searchToDate = form_search_todate

        mCheckboxesTextview = form_search_warning_checkboxes

        artsCheckBox = form_search_left_checkBox_arts
        businessCheckBox = form_search_left_checkBox_business
        entrepreneursCheckBox = form_search_left_checkBox_entrepreneurs
        politicsCheckBox = form_search_right_checkBox_politics
        sportsCheckBox = form_search_right_checkBox_sports
        travelCheckBox = form_search_right_checkBox_travel

        searchButton = form_search_button

        // Add back button
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Configure the two DatePickerDialogs
        this.configureEditText(searchFromDate!!)
        this.configureEditText(searchToDate!!)

        // Configure the launch button
        launchResearch()
    }

    // -------------
    // UTILS
    // -------------

    // getCurrentDay into a String
    private val currentDay: String
        get() {
            val curDate = Date()

            val format = SimpleDateFormat("yyyyMMdd")

            return format.format(curDate)
        }



    // ---------------
    // ACTION
    // ---------------

    private fun launchResearch() {
        searchButton!!.setOnClickListener {
            if (TextUtils.isEmpty(searchQuery!!.text)) {
                searchQuery!!.error = "This field is required !"
            } else if (!checkingCheckboxes()) {
                mCheckboxesTextview!!.visibility = View.VISIBLE
            } else {
                val intent = Intent(this@SearchActivity, ResultActivity::class.java)

                // get the query term(s)
                intent.putExtra("QUERY", searchQuery!!.text.toString())

                // get the begin date
                var fromDate = searchFromDate!!.text.toString()
                Log.e("fromDate", searchFromDate!!.text.toString())
                if (fromDate.length <= 1) {
                    fromDate = "20000101"
                } else {
                    fromDate = fromDate.replace(" / ", "")
                }
                intent.putExtra("FROM_DATE", fromDate)

                // get the end date
                var toDate = searchToDate!!.text.toString()
                Log.e("toDate", searchToDate!!.text.toString())
                if (toDate.length <= 1) {
                    toDate = currentDay
                } else {
                    toDate = toDate.replace(" / ", "")
                }
                intent.putExtra("TO_DATE", toDate)

                // get checkboxes
                intent.putExtra("CHECKBOXES", checkBoxString())
                startActivity(intent)
            }
        }
    }

    // ---------------
    // CONFIGURATION
    // ---------------

    private fun configureEditText(editText: EditText) {
        editText.inputType = InputType.TYPE_NULL
        editText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val picker: DatePickerDialog

            picker = DatePickerDialog(
                this@SearchActivity,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val sMonth: String
                    if (month < 10) {
                        sMonth = "0" + (month + 1)
                    } else {
                        sMonth = Integer.toString(month + 1)
                    }
                    val sDayOfMonth: String
                    if (dayOfMonth < 10) {
                        sDayOfMonth = "0$dayOfMonth"
                    } else {
                        sDayOfMonth = Integer.toString(dayOfMonth)
                    }
                    editText.setText("$year / $sMonth / $sDayOfMonth")
                },
                year,
                month,
                day
            )
            picker.show()
        }
    }


    // Create a piece or URI with checked checkboxes
    private fun checkBoxString(): String {
        var checkString: String? = null

        val checkBoxes = ArrayList<CheckBox>()
        checkBoxes.add(artsCheckBox)
        checkBoxes.add(businessCheckBox)
        checkBoxes.add(entrepreneursCheckBox)
        checkBoxes.add(politicsCheckBox)
        checkBoxes.add(sportsCheckBox)
        checkBoxes.add(travelCheckBox)

        for (checkBox in checkBoxes) {
            if (checkBox.isChecked && checkString != null) {
                checkString = checkString + "\" \"" + checkBox.text.toString()
            } else if (checkBox.isChecked && checkString == null) {
                checkString = "news_desk(\"" + checkBox.text.toString()
            }
        }

        return checkString!! + "\")"
    }

    // Check if one checkbox is checked
    private fun checkingCheckboxes(): Boolean {
        var checkCheckboxes: Boolean? = false

        val checkBoxes = ArrayList<CheckBox>()
        checkBoxes.add(artsCheckBox)
        checkBoxes.add(businessCheckBox)
        checkBoxes.add(entrepreneursCheckBox)
        checkBoxes.add(politicsCheckBox)
        checkBoxes.add(sportsCheckBox)
        checkBoxes.add(travelCheckBox)

        for (checkBox in checkBoxes) {
            if (checkBox.isChecked) checkCheckboxes = true
        }

        return checkCheckboxes!!
    }
}