package com.example.getsome.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.getsome.repository.ClothRepository

class ClothViewModelFactory(private val repository: ClothRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClothViewModel(repository) as T
    }
}