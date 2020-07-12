package com.example.ktorsample.network

import androidx.lifecycle.MutableLiveData
import com.example.ktorsample.reposearch.models.GitHubSearchResponse
import com.example.ktorsample.utils.extensions.isValid
import com.example.ktorsample.utils.extensions.print
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.http.HttpMethod
import io.ktor.util.KtorExperimentalAPI

class GitHubRepo( private val client : HttpClient) {

    @KtorExperimentalAPI
    suspend fun getRepositoriesKtor(liveData : MutableLiveData<Result<GitHubSearchResponse>>, query : String){
        "Ktor_request entered 0".print()
        liveData.postValue(Result.Loading(true))
        "Ktor_request entered".print()

        try{
            val requestUrl = "https://api.github.com/search/repositories?q=$query"
            "Ktor_request URL: $requestUrl".print()

            val response =
                client.request<GitHubSearchResponse>(requestUrl) {
                    method = HttpMethod.Get
                  headers {
                      append("My-Custom-Header", "HeaderValue")
                  }
                }

            "Ktor_request Response: $response".print()
            liveData.postValue(Result.Success(response))
        }catch (e : java.lang.Exception){
            "Ktor_request Error: ${e.message?:"Un-traceable"}".print()
            "Ktor_request Error: $e".print()
            if (e.message.isValid()) {
                liveData.postValue(Result.Error.RecoverableError(Exception(e.message)))
            }else{
                liveData.postValue(Result.Error.NonRecoverableError(Exception("Un-traceable")))
            }
        }

    }



}
