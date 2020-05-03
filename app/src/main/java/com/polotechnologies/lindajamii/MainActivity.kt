package com.polotechnologies.lindajamii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.polotechnologies.lindajamii.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LindaJamii)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setDisplayContent()
    }

    private fun setDisplayContent() {
        navController = supportFragmentManager.findFragmentById(R.id.nav_host_main)!!.findNavController()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment){
                mBinding.toolbarMain.visibility = View.GONE
                mBinding.drawerLayoutMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }else{
                navController.graph.startDestination = R.id.homeFragment
                setUpToolbar(destination)
                setDrawerLayout()
            }
        }

    }

    private fun setUpToolbar(destination: NavDestination) {
        mBinding.toolbarMain.visibility = View.VISIBLE
        mBinding.toolbarMain.title = destination.label

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
        item.isChecked = true
        mBinding.drawerLayoutMain.closeDrawers()

        TODO()
    }

}