package com.example.nero.ui.emotions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.nero.databinding.FragmentEmotionsBinding


class EmotionsFragment : Fragment() {
    private lateinit var binding: FragmentEmotionsBinding
    private lateinit var viewModel: EmotionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmotionsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPieChart()

        viewModel = ViewModelProvider(this)[EmotionsViewModel::class.java]

        viewModel.onSignalClicked()

        binding.viewModel = viewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewModel.close()
    }

    private fun setupPieChart() {
        val pieChart = AnyChart.pie()
        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Relaxation", 50))
        data.add(ValueDataEntry("Attention", 50))
        pieChart.data(data)

        pieChart.title("Relaxation and Attention")
        pieChart.legend().title().enabled(false)

        binding.chart1.setChart(pieChart)
    }
}