package com.polotechnologies.lindajamii

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.polotechnologies.lindajamii.databinding.ActivityMainBinding
import com.polotechnologies.lindajamii.util.ExpectantVisitNotification


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController: NavController
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LindaJamii)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mNavController = findNavController(R.id.nav_host_main)

        mAuth = FirebaseAuth.getInstance()

        setUpToolBarAndDrawer()
        setNavControllerOptions()


        registerNotificationChannel()

    }

    private fun setUpToolBarAndDrawer() {
        val appBarConfiguration = AppBarConfiguration(mNavController.graph, mBinding.drawerLayoutMain)
        mBinding.toolbarMain.setupWithNavController(mNavController, appBarConfiguration)

        mBinding.navigationViewMain.setupWithNavController(mNavController)
        mBinding.navigationViewMain.setNavigationItemSelectedListener(this)
        mBinding.drawerLayoutMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

    }

    private fun registerNotificationChannel() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                ExpectantVisitNotification.NOTIFICATION_CHANNEL,
                "Linda Jamii",
                NotificationManager.IMPORTANCE_HIGH
            )
            nm.createNotificationChannel(channel)

        }
    }

    private fun setNavControllerOptions() {
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    mBinding.toolbarMain.visibility = View.GONE
                }
                R.id.patientsDetailsFragment -> {
                    mBinding.toolbarMain.visibility = View.GONE
                }
                else -> {
                    mBinding.toolbarMain.visibility = View.VISIBLE
                    mBinding.drawerLayoutMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

                }

            }

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        if (mNavController.currentDestination!!.id == R.id.homeFragment) {
            when (item.itemId) {
                R.id.action_initial_visit -> {
                    mNavController.navigate(R.id.action_homeFragment_to_initialVisitFragment)
                    mBinding.drawerLayoutMain.closeDrawers()
                }
                R.id.action_subsequent_visit -> {
                    mNavController.navigate(R.id.action_homeFragment_to_subsequentVisitsFragment)
                    mBinding.drawerLayoutMain.closeDrawers()
                }

                R.id.action_delivery -> {
                    mNavController.navigate(R.id.action_homeFragment_to_deliveryFragment)
                    mBinding.drawerLayoutMain.closeDrawers()
                }

                R.id.action_post_natal_visit -> {
                    mNavController.navigate(R.id.action_homeFragment_to_postNatalVisitFragment)
                    mBinding.drawerLayoutMain.closeDrawers()
                }

                R.id.action_patients -> {
                    mNavController.navigate(R.id.action_homeFragment_to_patientsFragment)
                    mBinding.drawerLayoutMain.closeDrawers()
                }
                else -> {
                    return true
                }
            }
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onBackPressed() {
        if (mBinding.drawerLayoutMain.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayoutMain.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sign_out -> {
                signOutUser()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun signOutUser() {
        mAuth.signOut()
        Toast.makeText(
            baseContext.applicationContext,
            "Signed Out Successfully",
            Toast.LENGTH_SHORT
        ).show()
        findNavController(R.id.nav_host_main).navigate(R.id.homeFragment)
    }
}