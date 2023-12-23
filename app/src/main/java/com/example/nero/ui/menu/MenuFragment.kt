package com.example.nero.ui.menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.nero.R
import com.example.nero.databinding.FragmentMenuBinding
import com.example.nero.neuroimpl.BrainBitController
import com.neurosdk2.neuro.types.SensorState

class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private lateinit var binding: FragmentMenuBinding
    private lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)
        viewModel = ViewModelProvider(this)[MenuViewModel::class.java]
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_searchFragment)
        }

        binding.buttonResist.setOnClickListener {
            if(BrainBitController.connectionState == SensorState.StateInRange){
                findNavController().navigate(R.id.action_menuFragment_to_resistFragment)
            }
            else {
                Toast.makeText(requireActivity(), "Connect to device first!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonInfo.setOnClickListener {
            if(BrainBitController.connectionState == SensorState.StateInRange){
                findNavController().navigate(R.id.action_menuFragment_to_infoFragment)
            }
            else {
                Toast.makeText(requireActivity(), "Connect to device first!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonCurrentReconect.setOnClickListener {
            if(BrainBitController.hasDevice) viewModel.reconnect()
        }

        binding.buttonEmotions.setOnClickListener {
            if(BrainBitController.connectionState == SensorState.StateInRange){
                findNavController().navigate(R.id.action_menuFragment_to_emotionsFragment)
            }
            else {
                Toast.makeText(requireActivity(), "Connect to device first!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onResume() {
        super.onResume()

        viewModel.updateDeviceInfo()
    }

}