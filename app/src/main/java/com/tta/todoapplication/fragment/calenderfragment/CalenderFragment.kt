package com.tta.todoapplication.fragment.calenderfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tta.todoapplication.databinding.FragmentCalenderBinding
import com.tta.todoapplication.databinding.FragmentFocusBinding
import com.tta.todoapplication.databinding.FragmentHomeBinding

class CalenderFragment : Fragment() {
    private var _binding: FragmentCalenderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // test crash app
        binding.calender.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }
    }
}