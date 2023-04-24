package com.tta.todoapplication.fragment.homefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tta.todoapplication.data.viewmodel.ToDoViewModel
import com.tta.todoapplication.databinding.FragmentHomeBinding
import com.tta.todoapplication.fragment.adapter.ItemTaskAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val adapter: ItemTaskAdapter by lazy { ItemTaskAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        mToDoViewModel.getAllData.observe(viewLifecycleOwner) { data ->
            Log.e("tta",data.toString())
            if (data.isEmpty()){
                binding.layoutNoTask.visibility = View.VISIBLE
                binding.layoutTask.visibility = View.GONE
            } else {
                adapter.setTaskList(data,requireActivity())
            }
        }
        setUpRecycle()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setUpRecycle() {
        binding.recTask.adapter = adapter
        binding.recTask.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}