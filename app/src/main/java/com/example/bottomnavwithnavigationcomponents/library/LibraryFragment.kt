package com.example.bottomnavwithnavigationcomponents.library


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bottomnavwithnavigationcomponents.R
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // add click listener to book item
        awesome_book.setOnClickListener {
            val bundle = Bundle().apply {
                putString(BookFragment.KEY_TITLE, "Winds of Winter")
                putString(BookFragment.KEY_DATE, "2018")
            }

            findNavController().navigate(R.id.action_read, bundle)
        }
    }

}
