package com.example.testfalabella.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.domain.users.Users
import com.example.testfalabella.R
import com.example.testfalabella.ui.common.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var component: RegisterFragmentComponent
    private val viewModel: RegisterViewModel by lazy { getViewModel { component.registerViewModel } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        component = context?.app?.component?.plus(RegisterFragmentModule())!!

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
        viewModel.modelFindUser.observe(viewLifecycleOwner, Observer(::findUser))

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btSave -> {
                if (etName.text.isEmpty()) {
                    Log.e("TAG", "Empty Username")
                    tilName.error = "Campo Obligatorio"
                } else if (etFirstName.text.isEmpty()) {
                    Log.e("TAG", "Empty Username")
                    tilFirstName.error = "Campo Obligatorio"
                } else if (etUsername.text.isEmpty()) {
                    Log.e("TAG", "Empty Username")
                    tilUsername.error = "Campo Obligatorio"
                } else if (etPassword.text.isEmpty()){
                    Log.e("TAG", "Empty Password")
                    tilPassword.error = "Campo Obligatorio"
                } else {
                    viewModel.findUserByUsername(username = etUsername.text.toString())
                }
            }
        }
    }

    /* save user */
    private fun updateUi(event: Data<Boolean>?) {

        event.with {
            when (dataState) {
                DataState.LOADING -> {
                    pbUserRegister.visibility = View.VISIBLE
                }
                DataState.SUCCESS -> {
                    pbUserRegister.visibility = View.GONE
                    tilUsername.error = null
                    tilPassword.error = null
                    tilName.error = null
                    tilFirstName.error = null

                    etUsername.text = null
                    etPassword.text = null
                    etName.text = null
                    etFirstName.text = null

                    findNavController().popBackStack(R.id.registerFragment, true);
                    findNavController().navigate(R.id.loginFragment);

                }
                DataState.ERROR -> {
                    pbUserRegister.visibility = View.GONE
                }
            }

        }
    }

    /* search if username exists */
    private fun findUser(event: Data<List<Users>>?) {

        event.with {
            when (dataState) {
                DataState.LOADING -> {
                    pbUserRegister.visibility = View.VISIBLE
                }
                DataState.SUCCESS -> {
                    viewModel.saveUser(users = Users(name = etName.text.toString(), firstname = etFirstName.text.toString(), username =
                    etUsername.text.toString(), password = etPassword.text.toString())
                    )
                }
                DataState.ERROR -> {
                    pbUserRegister.visibility = View.GONE
                    view?.let { Snackbar.make(it, R.string.snackbar_username, Snackbar.LENGTH_LONG).show() }
                }
            }

        }
    }


}