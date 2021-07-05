package com.commonsware.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class RosterListFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.todo_roster,container,false)
    }

    // now there are three ways to show this fragment:
//    • Use a <fragment> element in a layout resource. This is for fragments that we
//    will be showing all the time.
//    • Use a FragmentTransaction to tell a FragmentManager to display a fragment
//    in a specified container.
//    • Use the Navigation component to abstract away requests to navigate to a
//    particular screen from the implementation of that screen
}