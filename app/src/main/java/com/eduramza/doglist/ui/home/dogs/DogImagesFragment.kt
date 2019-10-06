package com.eduramza.doglist.ui.home.dogs

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.eduramza.api.PATH
import com.eduramza.doglist.R
import com.eduramza.doglist.ui.loading.LoadingFragment
import kotlinx.android.synthetic.main.dog_images_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class DogImagesFragment : Fragment(), DogsAdapterListener, LoadingFragment.LoadingListener {

    companion object {
        private lateinit var breed: String
        fun newInstance(breed: String): DogImagesFragment{
            this.breed = breed
            return DogImagesFragment()
        }
    }

    private val dogsViewModel by viewModel<DogImagesViewModel>()
    private var listOfDogs = ArrayList<String>()
    private val loadingFragment: LoadingFragment by lazy {
        LoadingFragment.newInstance(this@DogImagesFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dog_images_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dogsViewModel.downloadImages(breed)
        setupObserve()
    }

    private fun setupObserve(){
        dogsViewModel.getProgress().observe(this, Observer {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })
        dogsViewModel.getListOfImages().observe(this, Observer {result ->
            listOfDogs.addAll(result)
            grid_dog_gallery.adapter =
                context?.let {
                        it -> DogsAdapter(listOfDogs, it, this)
                }
        })
    }

    private fun showLoading(){
        loadingFragment.show(activity!!.supportFragmentManager, "Loading")
    }

    private fun hideLoading(){
        loadingFragment.dismiss()
    }

    override fun doPreview(url: String) {
        val intent = Intent(activity, PreviewActivity::class.java)
        intent.putExtra(PATH, url)
        context?.startActivity(intent)
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
