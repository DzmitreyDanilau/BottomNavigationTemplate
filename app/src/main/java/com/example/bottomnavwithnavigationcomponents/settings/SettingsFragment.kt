package com.example.bottomnavwithnavigationcomponents.settings


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bottomnavwithnavigationcomponents.R
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_privacy_settings.setOnClickListener {
            findNavController()
                .navigate(R.id.action_privacy_settings)
        }

        btn_profile_settings.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profile_settings)
        }
    }
}
