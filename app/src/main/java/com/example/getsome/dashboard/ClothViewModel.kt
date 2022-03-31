package com.example.getsome.dashboard


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getsome.model.ApiData
import com.example.getsome.repository.ClothRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClothViewModel(private val repository: ClothRepository) :ViewModel() {

    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getClothsList()
        }
    }
       val clothLiveData: LiveData<ApiData>
            get() = repository.listLiveData
}