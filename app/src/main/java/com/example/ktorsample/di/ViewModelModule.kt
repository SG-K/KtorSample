package com.example.ktorsample.di

import com.example.ktorsample.reposearch.viewmodels.GitHubSearchViewmodel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { GitHubSearchViewmodel(get()) }
}