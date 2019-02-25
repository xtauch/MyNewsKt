package tauch.xavier.mynewskt.Controllers.Activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import tauch.xavier.mynewskt.Adapters.PageAdapter
import tauch.xavier.mynewskt.R


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //FOR DESIGN
    private var toolbar: Toolbar? = null
    private var drawerLayout: DrawerLayout? = null
    private var navigationView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuring Toolbar
        this.configureToolbar()

        // Configure ViewPager and Tabs
        this.configureViewPagerAndTabs()

        // Configure DrawerLayout
        this.configureDrawerLayout()

        // Configure NavigationView
        this.configureNavigationView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu and add it to the Toolbar
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> startActivity(Intent(this, SearchActivity::class.java))
            R.id.action_settings -> startActivity(Intent(this, NotifActivity::class.java))
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        // 4 - Handle Navigation Item Click

        when (item.itemId) {
            R.id.activity_main_drawer_topstories -> activity_main_viewpager.currentItem = FRAGMENT_TOPSTORIES
            R.id.activity_main_drawer_mostpopular -> activity_main_viewpager.currentItem = FRAGMENT_MOSTPOPULAR
            R.id.activity_main_drawer_business -> activity_main_viewpager.currentItem = FRAGMENT_BUSINESS
            else -> {
            }
        }

        this.drawerLayout!!.closeDrawer(GravityCompat.START)

        return true
    }


    // ---------------------
    // CONFIG
    // ---------------------

    private fun configureToolbar() {
        // Get the toolbar view inside the activity layout
        this.toolbar = findViewById(R.id.toolbar)
        // Sets the Toolbar
        setSupportActionBar(toolbar)
    }

    private fun configureViewPagerAndTabs() {

        //Set Adapter PageAdapter and glue it together
        activity_main_viewpager.adapter = PageAdapter(supportFragmentManager, this)

        //  Glue TabLayout and ViewPager together
        activity_main_tabs.setupWithViewPager(activity_main_viewpager)
        // Design purpose. Tabs have the same width
        activity_main_tabs.setTabMode(TabLayout.MODE_FIXED)
    }

    // 2 - Configure Drawer Layout
    private fun configureDrawerLayout() {
        this.drawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout!!.addDrawerListener(toggle)
        toggle.syncState()
    }

    // 3 - Configure NavigationView
    private fun configureNavigationView() {
        this.navigationView = findViewById(R.id.nav_view)
        navigationView!!.setNavigationItemSelectedListener(this)
    }

    companion object {

        //FOR DATA
        // 2 - Identify each fragment with a number
        private val FRAGMENT_TOPSTORIES = 0
        private val FRAGMENT_MOSTPOPULAR = 1
        private val FRAGMENT_BUSINESS = 2
    }
}
