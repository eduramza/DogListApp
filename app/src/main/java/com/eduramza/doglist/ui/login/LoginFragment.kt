package com.eduramza.doglist.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.eduramza.doglist.R
import com.eduramza.doglist.ui.home.HomeActivity
import com.eduramza.doglist.ui.loading.LoadingFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(), LoadingFragment.LoadingListener {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val loginViewModel: LoginViewModel by viewModel()
    private val loadingFragment: LoadingFragment by lazy {
        LoadingFragment.newInstance(this@LoginFragment)
    }

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
        tv_about_dev.setOnClickListener {
            Snackbar.make(it, "Que tal me convidar para um café?", Snackbar.LENGTH_LONG).show()
        }

        setupObserver()
    }

    private fun setupObserver(){
        loginViewModel.getSuccess().observe(this, Observer { success ->
            success?.let {
                openHome()
            }
        })

        loginViewModel.getError().observe(this, Observer {
            input_email.error = it
        })

        loginViewModel.getProgress().observe(this, Observer {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    private fun showLoading(){
        loadingFragment.show(activity!!.supportFragmentManager, "Loading")
    }

    private fun hideLoading(){
        loadingFragment.dismiss()
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun openHome(){
        val intent = Intent(activity, HomeActivity::class.java)
        context?.startActivity(intent)
        activity?.finish()
    }

}
