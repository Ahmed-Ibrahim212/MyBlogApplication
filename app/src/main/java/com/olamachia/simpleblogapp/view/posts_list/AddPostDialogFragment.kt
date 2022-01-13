package com.olamachia.simpleblogapp.view.posts_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.model.domain.Post
import com.olamachia.simpleblogapp.viewmodels.BlogPostsViewModel
import kotlinx.android.synthetic.main.fragment_add_posts_dialog.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPostDialogFragment : DialogFragment() {

    private val addPostViewModel by viewModel<BlogPostsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_posts_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonPost.setOnClickListener {
            val postTitle = newPostTitle.text.toString()
            val postBody = newPostBody.text.toString()
            val userId = 10

            if (postTitle.isEmpty() || postBody.isEmpty()) {
                newPostTitle.error = "This field can't be empty"
                newPostBody.error = "This field can't be empty"
            }
            else {
                val newPost = Post(userId, id, postTitle, postBody)
                addPostViewModel.insertPost(newPost)
                addPostViewModel.fetchPosts()
            }

            requireActivity().recreate()
        }
    }
}