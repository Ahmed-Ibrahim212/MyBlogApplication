package com.olamachia.simpleblogapp.view.posts_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.model.domain.Post
import com.olamachia.simpleblogapp.util.DataState
import com.olamachia.simpleblogapp.viewmodels.BlogPostsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlogPostsActivity : AppCompatActivity() {

    private val posts = ArrayList<Post>()
    private val blogPostViewModel: BlogPostsViewModel by viewModel()
    private lateinit var postsAdapter: BlogPostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_posts)

        subscribeObservers()

        val recyclerView = postsRecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        postsAdapter = BlogPostsAdapter(this, posts)
        recyclerView.adapter = postsAdapter

        // Add New Post
        fabAddPost.setOnClickListener {
            AddPostDialogFragment().show(supportFragmentManager, "ADD POST")
        }

        // SearchPosts
        postSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { blogPostViewModel.searchPosts(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { blogPostViewModel.searchPosts(it) }
                return false
            }
        })
    }

    private fun subscribeObservers() {
        blogPostViewModel.dataState.observe(this, { result ->
            when(result.status) {
                DataState.Status.SUCCESS -> {
                    Utils.showProgressBar(progressBar, false)
                    result.data?.let { list ->
                        postsAdapter.updateData(list)
                    }
                }
                DataState.Status.ERROR -> {
                    Utils.showProgressBar(progressBar, false)
                    result.message?.let {
                        showError(it)
                    }
                }
                DataState.Status.LOADING -> {
                    Utils.showProgressBar(progressBar, true)
                }
            }
        })
    }

    private fun showError(msg: String?) {
        if (msg != null) {
            text.text = msg
        }
        text.text = getString(R.string.error_text)
    }

}