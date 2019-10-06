package com.eduramza.doglist.ui.home.dogs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.eduramza.api.repository.feed.FeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DogImagesViewModel(private val repository: FeedRepository) : ViewModel() {

    private val progress = MutableLiveData<Boolean>()
    fun getProgress() = progress

    fun getListOfImages() = repository.getListOfDogs()

    fun downloadImages(breed: String) = GlobalScope.launch(Dispatchers.IO){
        progress.postValue(true)
        repository.getGetImagesFor(breed)
        progress.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        repository.clearList()
    }
}
