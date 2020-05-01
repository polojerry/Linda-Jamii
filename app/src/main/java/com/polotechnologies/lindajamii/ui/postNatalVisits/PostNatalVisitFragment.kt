package com.polotechnologies.lindajamii.ui.postNatalVisits

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentPostNatalVisitBinding
/**
 *
 */
internal class PostNatalVisitFragment : Fragment(R.layout.fragment_post_natal_visit) {

    private lateinit var mainBinding: FragmentPostNatalVisitBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainBinding = FragmentPostNatalVisitBinding.bind(view)
    }
}