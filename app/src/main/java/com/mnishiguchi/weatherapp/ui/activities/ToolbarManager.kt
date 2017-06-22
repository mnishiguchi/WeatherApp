package com.mnishiguchi.weatherapp.ui.activities

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.mnishiguchi.weatherapp.R
import com.mnishiguchi.weatherapp.ext.ctx
import com.mnishiguchi.weatherapp.ext.slideEnter
import com.mnishiguchi.weatherapp.ext.slideExit
import com.mnishiguchi.weatherapp.ui.App
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Specify whether it shows the up navigation action or not
 * Animate the toolbar when scrolling
 * Assign the same menu to all activities and an event for the actions
 */
interface ToolbarManager {

    // Abstract property declaration for a toolbar instance
    val toolbar: Toolbar

    // Accessor implementation
    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> toolbar.ctx.startActivity<SettingsActivity>()
                else                 -> App.instance.toast("Unknown option")
            }
            true
        }
    }

    /**
     * Enables the navigation up button in the toolbar.
     */
    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable(): DrawerArrowDrawable {
        // https://developer.android.com/reference/android/support/v7/graphics/drawable/DrawerArrowDrawable.html
        return DrawerArrowDrawable(toolbar.ctx).apply {
            progress = 1f
        }
    }

    /**
     * The toolbar gets hidden when the view is scrolling down; appears when scrolling up.
     */
    fun attachToolbarToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0)
                    toolbar.slideExit()
                else
                    toolbar.slideEnter()
            }
        })
    }
}
