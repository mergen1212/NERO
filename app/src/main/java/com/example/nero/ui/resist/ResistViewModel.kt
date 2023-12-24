package com.example.nero.ui.resist

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.nero.neuroimpl.BrainBitController
import com.neurosdk2.neuro.types.SensorState

class ResistViewModel : ViewModel() {

    var started = ObservableBoolean(false)

    val sampleO1 = ObservableField<String>()
    val sampleO2 = ObservableField<String>()
    val sampleT3 = ObservableField<String>()
    val sampleT4 = ObservableField<String>()

    val sampleImgO1 = ObservableField<Boolean>(false)
    val sampleImgO2 = ObservableField<Boolean>(false)
    val sampleImgT3 = ObservableField<Boolean>(false)
    val sampleImgT4 = ObservableField<Boolean>(false)

    val isNormal = ObservableField<Boolean>(false)

    var connected = ObservableBoolean(true)
    var hasDevice = ObservableBoolean(BrainBitController.hasDevice)


    fun onResistClicked(){
        if(started.get()){
            BrainBitController.stopResist()
        }
        else{
            BrainBitController.startResist {
                sampleO1.set(it.o1.toInt().toString())
                sampleO2.set(it.o2.toInt().toString())
                sampleT3.set(it.t3.toInt().toString())
                sampleT4.set(it.t4.toInt().toString())

                if (it.o1 < 2000000f){
                    sampleImgO1.set(true)
                }else{
                    sampleImgO1.set(false)
                }

                if (it.o2 < 2000000f){
                    sampleImgO2.set(true)
                }else{
                    sampleImgO2.set(false)
                }

                if (it.t3 < 2000000f){
                    sampleImgT3.set(true)
                }else{
                    sampleImgT3.set(false)
                }

                if (it.t4 < 2000000f){
                    sampleImgT4.set(true)
                }else{
                    sampleImgT4.set(false)
                }

                if (
                    sampleImgO1.get() == true &&
                    sampleImgO2.get() == true &&
                    sampleImgT3.get() == true &&
                    sampleImgT4.get() == true
                    ){
                    isNormal.set(true)
                }else{
                    isNormal.set(false)
                }
            }
        }
        started.set(!started.get())
    }

    fun reconnect() {
        if (BrainBitController.connectionState == SensorState.StateInRange) {
            BrainBitController.disconnectCurrent()
            connected.set(false)
        } else {
            BrainBitController.connectCurrent(onConnectionResult = {
                connected.set(it == SensorState.StateInRange)
            })
        }
    }

    fun close(){
        BrainBitController.stopResist()
    }
}