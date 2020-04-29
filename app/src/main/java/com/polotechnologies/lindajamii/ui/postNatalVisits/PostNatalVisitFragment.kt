package com.polotechnologies.lindajamii.ui.postNatalVisits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.ActivityMainBinding
import com.polotechnologies.lindajamii.databinding.FragmentPostNatalVisitBinding


/**
 *
 */
class PostNatalVisitFragment : Fragment() {

    private lateinit var mainBinding: FragmentPostNatalVisitBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_natal_visit, container, false)
        return mainBinding.root
    }

}