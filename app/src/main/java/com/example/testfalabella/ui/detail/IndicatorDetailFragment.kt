package com.example.testfalabella.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.domain.indicators.Indicators
import com.example.testfalabella.R
import com.example.testfalabella.ui.common.*
import kotlinx.android.synthetic.main.fragment_indicator_detail.*

class IndicatorDetailFragment : Fragment() {

    private lateinit var component: IndicatorDetailFragmentComponent
    private val viewModel: IndicatorDetailViewModel by lazy { getViewModel { component.indicatorDetailViewModel } }
    private val args: IndicatorDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_indicator_detail, container, false)

        component = context?.app?.component?.plus(IndicatorDetailFragmentModule())!!
        viewModel.model.observe(viewLifecycleOwner, Observer(::findIndicator))

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTitleDetail.text = args.code
        viewModel.findIndicatorByCode(args.code)

    }

    private fun findIndicator (event: Data<List<Indicators>>?) {

        event.with {
            when (dataState) {
                DataState.LOADING -> {}
                DataState.SUCCESS -> {}
                DataState.ERROR -> {}
            }

            data.notNull {

                tvCode.text = it.first().codigo
                tvName.text = it.first().nombre
                tvUnit.text = it.first().unidadMedida
                tvDate.text = it.first().fecha
                tvValue.text = it.first().valor

            }

        }
    }

}