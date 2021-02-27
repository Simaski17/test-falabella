package com.example.testfalabella.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testfalabella.R
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

                }
            }
        }
    }

}