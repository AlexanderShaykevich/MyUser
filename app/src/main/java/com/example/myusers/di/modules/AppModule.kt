package com.example.myusers.di.modules

import com.example.myusers.viemodels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        UserViewModel(repository = get())
    }
}