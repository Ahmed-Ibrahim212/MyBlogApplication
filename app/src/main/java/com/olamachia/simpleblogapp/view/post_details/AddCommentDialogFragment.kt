package com.olamachia.simpleblogapp.view.post_details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.model.domain.Comment
import com.olamachia.simpleblogapp.viewmodels.PostDetailViewModel
import kotlinx.android.synthetic.main.fragment_add_comment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCommentDialogFragment(private val postId: Int) : DialogFragment() {

    private val addCommentViewModel by viewModel<PostDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_comment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        buttonComment.setOnClickListener {
            val name = commenterName.text.toString()
            val email = emailAddress.text.toString()
            val body = newComment.text.toString()
            val id = 0

            if (name.isEmpty() || email.isEmpty() || body.isEmpty()){
                commenterName. error = "Name Can't Be Empty"
                emailAddress.error = "Email Can't Be Empty"
                newComment.error = "Comment Can't Be Empty"
            } else {
                val newComment = Comment(postId, id, name, email, body)
                addCommentViewModel.insertComment(newComment)
                addCommentViewModel.getPostComments(postId)
                Log.d("New Comments", "onActivityCreated: $newComment")
                dismiss()
            }

        }
    }

}