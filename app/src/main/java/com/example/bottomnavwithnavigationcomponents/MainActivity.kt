package com.example.bottomnavwithnavigationcomponents

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.bottomnavwithnavigationcomponents.shared.views.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private val backStack = Stack<Int>()
    private val indexToPage = mapOf(0 to R.id.home, 1 to R.id.library, 2 to R.id.settings)

    // list of base destination containers
    private val fragments = listOf(
        BaseFragment.newInstance(R.layout.content_home_base, R.id.toolbar_home, R.id.nav_host_home),
        BaseFragment.newInstance(
            R.layout.content_library_base,
            R.id.toolbar_library,
            R.id.nav_host_library
        ),
        BaseFragment.newInstance(
            R.layout.content_settings_base,
            R.id.toolbar_settings,
            R.id.nav_host_settings
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup main view pager
        main_pager.addOnPageChangeListener(this)
        main_pager.adapter = ViewPagerAdapter()
        bottom_nav.setOnNavigationItemSelectedListener(this)
        if (backStack.isEmpty()) backStack.push(0)
    }

    override fun onBackPressed() {
        if (backStack.size > 1) {
            // remove current position from stack
            backStack.pop()
            // set the next item in stack as current
            main_pager.currentItem = backStack.peek()
        } else super.onBackPressed()
    }

    override fun onPageScrollStateChanged(state: Int) {}
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(page: Int) {
        val itemId = indexToPage[page] ?: R.id.home
        if (bottom_nav.selectedItemId != itemId)
            bottom_nav.selectedItemId = itemId
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val position = indexToPage.values.indexOf(menuItem.itemId)
        if (main_pager.currentItem != position) setItem(position)
        return true
    }

    private fun setItem(position: Int) {
        main_pager.currentItem = position
        backStack.push(position)
    }


    inner class ViewPagerAdapter : FragmentPagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

    }
}
