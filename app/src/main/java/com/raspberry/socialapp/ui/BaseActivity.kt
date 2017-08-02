package com.raspberry.socialapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.raspberry.socialapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

open class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    fun onCreateDrawable(){
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        search.setOnClickListener { view ->
            Snackbar.make( view,
                    "Replace with your own action",
                     Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    internal fun goLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun goProfilePage() {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.log_out) {
            FirebaseAuth.getInstance().signOut()
            LoginManager.getInstance().logOut()
            goLoginScreen()
        } else if (id == R.id.editProfile) {
            goProfilePage()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
