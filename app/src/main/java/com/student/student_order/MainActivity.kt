package com.student.student_order

import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, LocationListener {
    override fun onLocationChanged(p0: Location?) {
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }

    lateinit var mImageView: ImageView
    lateinit var mNameTextView: TextView
    lateinit var mEmailTextView: TextView
    var lat :Double = 0.0
    var lon :Double = 0.0


    private var locationManager: LocationManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        initLayout()
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        } catch (ex: SecurityException) {

        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
                startActivity(Intent(this,MemberActivity::class.java))
            }
            R.id.nav_gallery -> {


            }
            R.id.nav_slideshow -> {
                val strInput = "037603156"
                val myIntentDial = Intent(Intent.ACTION_CALL, Uri.parse("tel:$strInput"))
                startActivity(myIntentDial)
            }
            R.id.nav_manage -> {

            goTOGooglemap(lat,lon,24.683258, 120.967297)

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {
                Toast.makeText(this,"會員登出",Toast.LENGTH_SHORT).show()
                LoginManager.getInstance().logOut()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()


            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    fun initLayout(){
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val headerView = navigationView.getHeaderView(0)
        mImageView = headerView.findViewById(R.id.imageView)
        mNameTextView = headerView.findViewById(R.id.nametext)
        mEmailTextView = headerView.findViewById(R.id.textView)
        FacebookManager.checkFbState(this,mImageView,mNameTextView,mEmailTextView)

    }


    fun goTOGooglemap(startLatitude:Double,startLongitude:Double,endLatitude:Double,endLongitude:Double){
        val startLatitude = startLatitude
        val startLongitude = startLongitude
        val endLatitude = endLatitude
        val endLongitude = endLongitude

        val saddr = "saddr=$startLatitude,$startLongitude"
        val daddr = "daddr=$endLatitude,$endLongitude"
        val uriString = "http://maps.google.com/maps?$saddr&$daddr"

        val uri = Uri.parse(uriString)

        val intent = Intent(android.content.Intent.ACTION_VIEW, uri)

        // If you want to get rid of the dialog,
        // Before the startActivity() add this
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")

        startActivity(intent)
    }
    private val locationListener: LocationListener = object : LocationListener {
        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }

        override fun onProviderEnabled(p0: String?) {
        }

        override fun onProviderDisabled(p0: String?) {
        }

        override fun onLocationChanged(location: Location) {
            lat = location.latitude
            lon = location.longitude

        }


    }

}
