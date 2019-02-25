package tauch.xavier.mynewskt.Controllers.Activities

import android.os.Bundle

import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_result.*

import tauch.xavier.mynewskt.Controllers.Fragments.ResultFragment
import tauch.xavier.mynewskt.R

class ResultActivity : AppCompatActivity() {



    // FOR DATA
    private var query: String? = null
    private var from_date: String? = null
    private var to_date: String? = null
    private var checkboxes: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        this.configureToolbar()

        // Get data from SearchActivity
        query = intent.getStringExtra("QUERY")
        from_date = intent.getStringExtra("FROM_DATE")
        to_date = intent.getStringExtra("TO_DATE")
        checkboxes = intent.getStringExtra("CHECKBOXES")

        sendData()
    }

    // --------------
    // CONFIGURATION
    // --------------

    private fun configureToolbar() {
        // Add back button
        setSupportActionBar(activity_result_toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun sendData() {
        // Pack data in a bundle
        val bundle = Bundle()
        bundle.putString("query", query)
        bundle.putString("beginDate", from_date)
        bundle.putString("endDate", to_date)
        bundle.putString("checkboxes", checkboxes)

        // Pass over the bundle to our fragment
        val resultFragment = ResultFragment()
        resultFragment.arguments = bundle

        // Then show our fragment
        supportFragmentManager.beginTransaction().replace(R.id.container, resultFragment).commit()
    }
}