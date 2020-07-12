package com.example.ktorsample.reposearch.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ktorsample.reposearch.models.GitHubSearchResponse
import com.example.ktorsample.network.GitHubRepo
import com.example.ktorsample.network.Result
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.*

class GitHubSearchViewmodel(private val gitHubRepo: GitHubRepo) : ViewModel() {

    val scope : CoroutineScope = CoroutineScope(Dispatchers.IO )

    private val githubRepoLiveData = MutableLiveData<Result<GitHubSearchResponse>>()

    fun observeData() : MutableLiveData<Result<GitHubSearchResponse>>{
        return githubRepoLiveData
    }

//    fun getReposFromGitHub(data : String){
//        gitHubRepo.getRepositories(liveData = githubRepoLiveData, query = data)
//    }


    @KtorExperimentalAPI
    fun getReposFromGitHub(data : String){
        scope.launch {
            gitHubRepo.getRepositoriesKtor(liveData = githubRepoLiveData,
                query = data)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }

}