package com.example.nero.ui.info

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nero.neuroimpl.BrainBitController

class InfoViewModel : ViewModel() {
    var infoText = ObservableField(BrainBitController.fullInfo())
}