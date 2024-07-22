package com.example.emailapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.emailapp.databinding.EmailRowLayoutBinding


class EmailAdapter(
    private val context: Context,
    private val emailList: MutableList<Email>,
    val clickInterface:ClickDetectorInterface
): RecyclerView.Adapter<EmailAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: EmailRowLayoutBinding) : RecyclerView.ViewHolder (binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EmailRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return emailList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val email: Email = this.emailList[position]

        holder.binding.tvFrom.text = email.from
        holder.binding.tvTimeReceived.text = email.timeReceived
        holder.binding.tvSubjectLine.text = email.subjectLine
        holder.binding.tvEmailText.text = email.emailText

        var importanceImage = R.drawable.ic_star_unfilled
        if (email.isImportant) {
            importanceImage = R.drawable.ic_star_filled
        }
        Glide.with(context)
            .load(importanceImage)
            .placeholder(R.drawable.ic_question_mark)
            .error(R.drawable.ic_question_mark)
            .into(holder.binding.ivImportant)

        holder.binding.parentLayout.setOnClickListener {
            clickInterface.onRowClicked(position)
        }

        holder.binding.ivImportant.setOnClickListener {
            clickInterface.onImportanceClicked(position)
        }
    }
}
