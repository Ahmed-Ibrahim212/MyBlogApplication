package com.olamachia.simpleblogapp.view.post_details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.model.domain.Comment
import java.util.*

class CommentsAdapter(private val context: Context, private val list: List<Comment>) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {
   inner class CommentViewHolder (view: View, private val context: Context) : RecyclerView.ViewHolder(view) {

        fun bind(comment: Comment) {

            itemView.findViewById<TextView>(R.id.userName).text = comment.name.capitalize(Locale.ROOT)
            itemView.findViewById<TextView>(R.id.userEmail).text = comment.email
            itemView.findViewById<TextView>(R.id.commentBody).text = comment.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_comments, parent, false)
        return CommentViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}