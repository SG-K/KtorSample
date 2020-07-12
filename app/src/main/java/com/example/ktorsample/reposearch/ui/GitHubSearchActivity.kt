package com.example.ktorsample.reposearch.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.ktorsample.R
import com.example.ktorsample.network.Result
import com.example.ktorsample.reposearch.adapter.ReposAdapter
import com.example.ktorsample.reposearch.viewmodels.GitHubSearchViewmodel
import com.example.ktorsample.utils.extensions.isValid
import com.example.ktorsample.utils.extensions.remove
import com.example.ktorsample.utils.extensions.show
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class GitHubSearchActivity : AppCompatActivity() {

    private val viewmodelGitHuSearch : GitHubSearchViewmodel by viewModel()
    lateinit var adapter : ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodelConnections()

        adapter = ReposAdapter()
        edt_search?.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                edt_search?.text?.toString()?.let {
                    viewmodelGitHuSearch.getReposFromGitHub("user:$it")
                    return@OnEditorActionListener true
                }
            }
            return@OnEditorActionListener false
        })
        imagesRecyclerView?.adapter = adapter

    }

    private fun viewmodelConnections() {
        viewmodelGitHuSearch.observeData().observe(this, Observer {
            when(it){
                is Result.Success -> {
                    adapter.setData(it.data.items)
                    imagesRecyclerView?.show()
                    tv_error?.remove()
                }
                is Result.Error.RecoverableError -> {
                    imagesRecyclerView?.remove()
                    tv_error?.show()
                    tv_error?.text = it.exception.message
                }
                is Result.Error.NonRecoverableError -> {
                    imagesRecyclerView?.remove()
                    tv_error?.show()
                    tv_error?.text = it.exception.message
                }
                is Result.Loading -> {
                    imagesRecyclerView?.remove()
                    tv_error?.remove()
                    if ( it.status){
                        Toast.makeText(this,"Loading...",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

}
