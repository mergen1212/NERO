package com.example.nero.ui.resist

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
import com.example.nero.databinding.FragmentResistBinding
import com.example.nero.neuroimpl.BrainBitController

class ResistFragment : Fragment() {

    companion object {
        fun newInstance() = ResistFragment()
    }

    private lateinit var binding: FragmentResistBinding
    private lateinit var viewModel: ResistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_resist, container, false)
        viewModel = ViewModelProvider(this)[ResistViewModel::class.java]
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener { viewModel.onResistClicked() }
        binding.buttonCurrentReconect.setOnClickListener {
            if(BrainBitController.hasDevice) viewModel.reconnect()
        }
        binding.buttonStartSession.setOnClickListener {
            if (viewModel.isNormal.get() == true){
                findNavController().navigate(R.id.action_resistFragment_to_emotionsFragment)
            }else{
                Toast.makeText(requireContext(), "Resistance error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewModel.close()
    }

}