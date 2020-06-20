package com.polotechnologies.lindajamii

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.polotechnologies.lindajamii.databinding.ActivityMainBinding
import com.polotechnologies.lindajamii.util.ExpectantVisitNotification


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LindaJamii)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()

        setNavControllerOptions()
        registerNotificationChannel()

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
        navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_main)!!.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    mBinding.toolbarMain.visibility = View.GONE
                    mBinding.drawerLayoutMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                R.id.patientsDetailsFragment -> {
                    mBinding.appBarMain.visibility = View.GONE
                    mBinding.toolbarMain.visibility = View.GONE
                }
                else -> {
                    mBinding.appBarMain.visibility = View.VISIBLE
                    mBinding.toolbarMain.visibility = View.VISIBLE
                    setUpToolbar(destination)
                    setDrawerLayout()

                }

            }

        }
    }

    private fun setUpToolbar(destination: NavDestination) {
        mBinding.toolbarMain.visibility = View.VISIBLE
        mBinding.toolbarMain.title = destination.label

        setSupportActionBar(mBinding.toolbarMain)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        NavigationUI.setupActionBarWithNavController(this, navController, mBinding.drawerLayoutMain)
    }

    private fun setDrawerLayout() {
        mBinding.drawerLayoutMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        NavigationUI.setupWithNavController(mBinding.navigationMain, navController)
        mBinding.navigationMain.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        if (navController.currentDestination!!.id == R.id.homeFragment) {
            when (item.itemId) {
                R.id.action_initial_visit -> {
                    navController.navigate(R.id.action_homeFragment_to_initialVisitFragment)
                    mBinding.drawerLayoutMain.closeDrawers()
                }
                R.id.action_subsequent_visit -> {
                    navController.navigate(R.id.action_homeFragment_to_subsequentVisitsFragment)
                    mBinding.drawerLayoutMain.closeDrawers()
                }

                R.id.action_delivery -> {
                    navController.navigate(R.id.action_homeFragment_to_deliveryFragment)
                    mBinding.drawerLayoutMain.closeDrawers()
                }

                R.id.action_post_natal_visit -> {
                    navController.navigate(R.id.action_homeFragment_to_postNatalVisitFragment)
                    mBinding.drawerLayoutMain.closeDrawers()
                }

                R.id.action_patients -> {
                    navController.navigate(R.id.action_homeFragment_to_patientsFragment)
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


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, mBinding.drawerLayoutMain)
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