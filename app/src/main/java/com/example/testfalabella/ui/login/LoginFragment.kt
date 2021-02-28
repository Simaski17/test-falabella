package com.example.testfalabella.ui.login

import android.content.Context
import android.content.SharedPreferences
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
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.etPassword
import kotlinx.android.synthetic.main.fragment_login.etUsername
import kotlinx.android.synthetic.main.fragment_login.tilPassword
import kotlinx.android.synthetic.main.fragment_login.tilUsername
import kotlinx.android.synthetic.main.fragment_register.*

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var component: LoginFragmentComponent
    private val viewModel: LoginViewModel by lazy { getViewModel { component.loginViewModel } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        component = context?.app?.component?.plus(LoginFragmentModule())!!
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btSignIn.setOnClickListener(this)
        btRegister.setOnClickListener(this)

        val sharedPreferences: SharedPreferences =
            activity?.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)!!

        val sharedIdValue = sharedPreferences.getBoolean("isOnlinne",false)

        Log.e("IIS ONLINE", "VALUE $sharedIdValue")

        if (sharedIdValue) {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            findNavController().navigate(action)
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btSignIn -> {
                if (etUsername.text.isEmpty()) {
                    Log.e("TAG", "Empty Username")
                    tilUsername.error = "Campo Obligatorio"
                } else if (etPassword.text.isEmpty()) {
                    Log.e("TAG", "Empty Password")
                    tilPassword.error = "Campo Obligatorio"
                } else {
                    viewModel.signIn(
                        username = ChCrypto.aesEncrypt(etUsername.text.toString(), ChCrypto.secretKey),
                        password = ChCrypto.aesEncrypt(etPassword.text.toString(), ChCrypto.secretKey)
                    )
                }
            }
            R.id.btRegister -> {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun updateUi(event: Data<List<Users>>?) {

        event.with {
            when (dataState) {
                DataState.LOADING -> {
                    pbUserLogin.visibility = View.VISIBLE
                }
                DataState.SUCCESS -> {
                    pbUserLogin.visibility = View.GONE
                    tilUsername.error = null
                    tilPassword.error = null
                    etUsername.text = null
                    etPassword.text = null
                }
                DataState.ERROR -> {
                    pbUserLogin.visibility = View.GONE
                    view?.let { Snackbar.make(it, R.string.snackbar_login, Snackbar.LENGTH_LONG).show() }
                }
            }

            data.notNull {

                val sharedPreferences: SharedPreferences =
                    activity?.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)!!
                val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                editor.putBoolean("isOnlinne", true)
                editor.putString("username", it.first().username)
                editor.apply()
                editor.commit()

                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                findNavController().navigate(action)

            }
        }
    }

}