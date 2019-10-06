package com.eduramza.doglist.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.eduramza.api.FRAGMENT_HOME
import com.eduramza.api.FRAGMENT_LIST
import com.eduramza.doglist.R
import com.eduramza.doglist.ui.login.LoginActivity
import com.eduramza.local.model.UserPreferences
import org.koin.android.ext.android.inject


class HomeActivity : AppCompatActivity() {

    val prefs: UserPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            attachHomeFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> navigateBetweenFragments()
            R.id.menu_logout -> logout()
            else -> navigateBetweenFragments()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        navigateBetweenFragments()
//        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun attachHomeFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment.newInstance(), FRAGMENT_HOME)
            .commit()
    }

    private fun logout(){
        prefs.clearSharedPreference()
        Log.d("UserSession", "token deslogado -> ${prefs.token}")
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateBetweenFragments(){
        if (supportFragmentManager.findFragmentByTag(FRAGMENT_LIST) != null){
            attachHomeFragment()
        } else {
            finish()
        }
    }
}
