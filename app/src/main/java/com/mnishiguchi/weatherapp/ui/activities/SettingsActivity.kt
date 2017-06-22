package com.mnishiguchi.weatherapp.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.mnishiguchi.weatherapp.R
import com.mnishiguchi.weatherapp.ext.DelegateExt
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.toolbar.*


/**
 * Opens when the settings option is selected in the overflow menu in the toolbar.
 * Saves the preference when the user exits the activity.
 */
class SettingsActivity : AppCompatActivity() {

    companion object {
        val ZIP_CODE = "zipCode" // Key for SharedPreferences
        val DEFAULT_ZIP_CODE = 20001L // NOTE: Do not forget the "L" for Long.
    }

    // Data bound to SharedPreferences
    var zipCode: Long by DelegateExt.preference(this, ZIP_CODE, DEFAULT_ZIP_CODE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)

        // This adds a left-facing caret alongside the app icon and enables it as an action button.
        // https://developer.android.com/training/implementing-navigation/ancestral.html
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set current value on EditText.
        cityCode.setText(zipCode.toString())
    }

    // Called when the activity has detected the user's press of the back key.
    // https://developer.android.com/reference/android/app/Activity.html#onBackPressed()
    override fun onBackPressed() {
        // The default implementation simply finishes the current activity.
        super.onBackPressed()

        // Save data to SharedPreferences.
        zipCode = cityCode.text.toString().toLong()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // The Up button
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> false
        }
    }
}
