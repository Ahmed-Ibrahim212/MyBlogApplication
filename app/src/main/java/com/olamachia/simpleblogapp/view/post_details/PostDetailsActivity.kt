package com.olamachia.simpleblogapp.view.post_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.model.domain.Comment
import com.olamachia.simpleblogapp.util.DataState
import com.olamachia.simpleblogapp.util.Utils
import com.olamachia.simpleblogapp.viewmodels.PostDetailViewModel

class PostDetailActivity : AppCompatActivity() {

    private var comments = listOf<Comment>()
    private val viewModel: PostDetailViewModel by viewModel()
    private lateinit var commentsAdapter: CommentsAdapter
    private lateinit var commentsProgressBar: ProgressBar
    private lateinit var tvPostTitle: TextView
    private lateinit var tvPostBody: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        val postId = intent.getIntExtra(POST_ID, 0)

        val title = intent.getStringExtra("Post Title")
        val body = intent.getStringExtra("Post Body")
        tvPostTitle.text = title
        tvPostBody.text = body

        val recyclerView = findViewById<RecyclerView>(R.id.commentsRecyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        viewModel.getPostComments(postId)
        viewModel.commentData.observe(this, Observer { dataState ->

            when(dataState.status) {
                DataState.Status.SUCCESS -> {
                    Utils.showProgressBar(commentsProgressBar, false)
                    dataState.data?.let { list ->
                        commentsAdapter = CommentsAdapter(this, list)
                        recyclerView.adapter = commentsAdapter
                    }
                }
                DataState.Status.ERROR -> {
                    Utils.showProgressBar(commentsProgressBar, false)
                    dataState.message?.let {
                        showError(it)
                    }
                }
                DataState.Status.LOADING -> {
                    Utils.showProgressBar(commentsProgressBar, true)
                }
            }
        })

        fabAddComment.setOnClickListener {
            AddCommentDialogFragment(postId).show(supportFragmentManager, "ADD COMMENT")
        }

    }
    private fun showError(msg: String) {
        Snackbar.make(parentView, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
        }.show()
    }

    companion object {
        const val POST_ID = "postId"
    }
}