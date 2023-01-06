package com.marcospb.justocallenge.ui.user_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcospb.justocallenge.R
import com.marcospb.justocallenge.databinding.UserListItemBinding
import com.marcospb.justocallenge.domain.models.UserDomain

class UserListAdapter(private val onItemClick: (user: UserDomain) -> Unit) :
    ListAdapter<UserDomain, UserListAdapter.ViewHolder>(USER_DIFFCALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserListItemBinding.bind(itemView)

        init {
            binding.root.setOnClickListener {
                onItemClick(getItem(adapterPosition))
            }
        }

        fun bind(user: UserDomain) {
            Glide.with(binding.root.context)
                .load(user.photo)
                .into(binding.shapeableImageView)

            binding.userName.text = user.username
            binding.contry.text = ""
            binding.email.text = user.email
        }

    }


    object USER_DIFFCALLBACK : DiffUtil.ItemCallback<UserDomain>() {
        override fun areItemsTheSame(oldItem: UserDomain, newItem: UserDomain): Boolean {
            return oldItem.username == newItem.username
        }

        override fun areContentsTheSame(oldItem: UserDomain, newItem: UserDomain): Boolean {
            return oldItem == newItem
        }


    }


}