package com.eduramza.api.repository.feed

import androidx.lifecycle.MutableLiveData

interface FeedRepository {
    suspend fun getGetImagesFor(breed: String)
    fun getGenericError(): MutableLiveData<Boolean>
    fun getListOfDogs(): MutableLiveData<List<String>>
    fun clearList()
}