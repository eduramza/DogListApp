package com.eduramza.doglist.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.eduramza.doglist.R
import com.eduramza.doglist.ui.home.HomeActivity
import com.eduramza.local.model.UserPreferences
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_login.setOnClickListener {
            loginViewModel.doLogin(edit_email.text.toString())
        }

        setupObserver()
    }

    private fun setupObserver(){
        loginViewModel.getSuccess().observe(this, Observer {
            openHome()
        })

        loginViewModel.getError().observe(this, Observer {
            input_email.error = it
        })
    }

    private fun openHome(){
        val intent = Intent(activity, HomeActivity::class.java)
        context?.startActivity(intent)
        activity?.finish()
    }

}
