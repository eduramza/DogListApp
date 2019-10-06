package com.eduramza.doglist.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eduramza.api.*
import com.eduramza.doglist.R
import com.eduramza.doglist.ui.home.dogs.DogImagesFragment
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configListeners()
    }

    fun configListeners(){
        card_labrador.setOnClickListener(this)
        card_hound.setOnClickListener(this)
        card_husky.setOnClickListener(this)
        card_pug.setOnClickListener(this)
    }

    override fun onClick(item: View) {
        when(item){
            card_labrador -> openListFragment(LABRADOR)
            card_hound -> openListFragment(HOUND)
            card_husky -> openListFragment(HUSKY)
            card_pug -> openListFragment(PUG)
            else -> return
        }
    }

    private fun openListFragment(breed: String){
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, DogImagesFragment.newInstance(breed), FRAGMENT_LIST)
            ?.commit()
    }

}
