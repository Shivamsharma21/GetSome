package com.example.getsome.userprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.getsome.model.UserModel

class UserProfileViewModelFactory(var userModel: UserModel) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
           return UserProfileViewModel(userModel) as T
    }
}