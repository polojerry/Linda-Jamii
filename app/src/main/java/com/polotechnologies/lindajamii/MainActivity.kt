package com.polotechnologies.lindajamii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.polotechnologies.lindajamii.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    Toolbar.OnMenuItemClickListener {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LindaJamii)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setNavControllerOptions()

        setDrawerLayout()
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
                else -> {
                    setUpToolbar(destination)
                    setDrawerLayout()

                }
            }

        }
    }

    private fun setUpToolbar(destination: NavDestination) {
        mBinding.toolbarMain.visibility = View.VISIBLE
        mBinding.toolbarMain.title = destination.label

        navController.graph.startDestination = R.id.homeFragment

        setSupportActionBar(mBinding.toolbarMain)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        NavigationUI.setupActionBarWithNavController(this, navController, mBinding.drawerLayoutMain)
    }

    private fun setDrawerLayout() {
        mBinding.drawerLayoutMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        NavigationUI.setupWithNavController(mBinding.navigationMain, navController)
        mBinding.navigationMain.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_initial_visit->{
                navController.navigate(R.id.action_homeFragment_to_initialVisitFragment)
                mBinding.drawerLayoutMain.closeDrawers()
            }
            R.id.action_subsequent_visit->{
                navController.navigate(R.id.action_homeFragment_to_subsequentVisitsFragment)
                mBinding.drawerLayoutMain.closeDrawers()
            }

            R.id.action_delivery->{
                navController.navigate(R.id.action_homeFragment_to_deliveryFragment)
                mBinding.drawerLayoutMain.closeDrawers()
            }

            R.id.action_post_natal_visit->{
                navController.navigate(R.id.action_homeFragment_to_postNatalVisitFragment)
                mBinding.drawerLayoutMain.closeDrawers()
            }

            R.id.action_patients->{
                navController.navigate(R.id.action_homeFragment_to_patientsFragment)
                mBinding.drawerLayoutMain.closeDrawers()
            }
            else->{
                return true
            }
        }
        return true
    }

    private fun checkCloseDrawer(){
        /*item.isisChecked = true*/
        mBinding.drawerLayoutMain.closeDrawers()
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

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_sign_out) {
            Toast.makeText(applicationContext, "Signed Out Successfully", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}