package com.example.bottomnavwithnavigationcomponents.shared.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.example.bottomnavwithnavigationcomponents.shared.utils.rootDestinations

class BaseFragment : Fragment() {

    private val defaultInt = -1
    private var layoutRes: Int = -1
    private var toolbarId: Int = -1
    private var navHostId: Int = -1
    private val appBarConfig = AppBarConfiguration(rootDestinations)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            layoutRes = it.getInt(KEY_LAYOUT)
            toolbarId = it.getInt(KEY_TOOLBAR)
            navHostId = it.getInt(KEY_NAV_HOST)
        } ?: return
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return if (layoutRes == defaultInt) null
        else inflater.inflate(layoutRes, container, false)
    }

    override fun onStart() {
        super.onStart()
        // return early if no arguments were parsed
        if (toolbarId == defaultInt || navHostId == defaultInt) return
        // find navController using navHostFragment
        val toolbar = requireActivity().findViewById<Toolbar>(toolbarId)
        val navController = requireActivity().findNavController(navHostId)
        // setup navigation with root destinations and toolbar
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfig)
    }


    fun onBackPressed(): Boolean {
        return requireActivity()
            .findNavController(navHostId)
            .navigateUp(appBarConfig)
    }

    fun popToRoot() {
        val navController =
            requireActivity().findNavController(navHostId)
        // navigate to the start destination
        navController.popBackStack(
            navController.graph.startDestination, false
        )
    }


    companion object {
        private const val KEY_LAYOUT = "layout_key"
        private const val KEY_NAV_HOST = "nav_host_key"
        private const val KEY_TOOLBAR = "toolbar_key"


        fun newInstance(layoutRes: Int, toolbarId: Int, navHostId: Int) = BaseFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_LAYOUT, layoutRes)
                putInt(KEY_NAV_HOST, navHostId)
                putInt(KEY_TOOLBAR, toolbarId)
            }
        }
    }
}