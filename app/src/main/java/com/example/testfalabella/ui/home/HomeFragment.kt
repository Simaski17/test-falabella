package com.example.testfalabella.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.indicators.Indicators
import com.example.testfalabella.R
import com.example.testfalabella.ui.common.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var component: HomeFragmentComponent
    private val viewModel: HomeViewModel by lazy { getViewModel { component.homeViewModel } }
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        component = context?.app?.component?.plus(HomeFragmentModule())!!
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        viewModel.getListIndicators()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences =
            activity?.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)!!

        val sharedUsername = sharedPreferences.getString("username", "default")

        tvHomeTitle.text = "Hola $sharedUsername"

        rvIndicatorsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            homeAdapter = HomeAdapter (){
                val action = HomeFragmentDirections.actionHomeFragmentToIndicatorDetailFragment(code = it.codigo)
                findNavController().navigate(action)
            }
            rvIndicatorsList.adapter = homeAdapter

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.SignOff -> {
                val sharedPreferences: SharedPreferences =
                    activity?.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)!!
                val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                editor.putBoolean("isOnlinne", false)
                editor.apply()
                editor.commit()
                findNavController().popBackStack(R.id.homeFragment, true);
                findNavController().navigate(R.id.loginFragment);
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateUi(event: Data<List<Indicators>>?) {

        event.with {
            when (dataState) {
                DataState.LOADING -> {
                    tvNotIndicators.visibility = View.GONE
                    pbIndicatorsList.visibility = View.VISIBLE
                }
                DataState.SUCCESS -> {
                    tvNotIndicators.visibility = View.GONE
                    pbIndicatorsList.visibility = View.GONE
                }
                DataState.ERROR -> {
                    tvNotIndicators.visibility = View.VISIBLE
                    pbIndicatorsList.visibility = View.GONE
                }
            }

            data.notNull {

                rvIndicatorsList.visibility = View.VISIBLE
                Log.e("LIST INDICATORS", it.toString())
                var lista = ArrayList(it)
                homeAdapter.updateListIndicators(lista)

            }

        }
    }

}