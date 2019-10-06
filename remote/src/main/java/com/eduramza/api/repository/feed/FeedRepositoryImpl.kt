package com.eduramza.api.repository.feed

import androidx.lifecycle.MutableLiveData
import com.eduramza.api.source.IdWallApi
import com.eduramza.local.model.UserPreferences
import java.lang.Exception

class FeedRepositoryImpl(private val idWallApi: IdWallApi,
                         private val prefs: UserPreferences) : FeedRepository{

    private val errorGeneric = MutableLiveData<Boolean>()
    override fun getGenericError() = errorGeneric

    private val listOfDogs = MutableLiveData<List<String>>()
    override fun getListOfDogs() = listOfDogs

    override suspend fun getGetImagesFor(breed: String) {
        clearError()
        //Verificar se no banco local elas já foram baixadas
        try {
            prefs.token?.let{
                val result = idWallApi.getBreed(it, breed)
                //should save in local database
                listOfDogs.postValue(result.list)
            }

        } catch (e: Exception){
            errorGeneric.postValue(true)
        }
    }

    private fun clearError(){
        errorGeneric.postValue(false)
    }

    override fun clearList() {
        listOfDogs.postValue(emptyList())
    }

}