package com.bangkit.suitmediatestapplication.data.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bangkit.suitmediatestapplication.R
import com.bangkit.suitmediatestapplication.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ItemAdapter : PagingDataAdapter<DataItem, ItemAdapter.ViewHolder>(DataItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: DataItem) {
                    binding.apply {
                        Glide.with(itemView)
                            .load(user.avatar)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .centerCrop()
                            .into(imgUser)
                        firstName.text = user.firstName
                        lastName.text = user.lastName
                        emailUser.text = user.email
                    }
                    itemView.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putString(
                            "Name_From_Third",
                            user.firstName + " " + user.lastName
                        )
                        val navController = itemView.findNavController()
                        navController.navigate(R.id.secondScreenFragment, bundle)
                    }
                }
            }

    companion object {
        private val DataItemDiffCallback = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
