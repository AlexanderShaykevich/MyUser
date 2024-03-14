package com.example.myusers.di.modules

import com.example.myusers.data.RepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single {
        RepositoryImpl(api = get(), context = get())
    }

}
