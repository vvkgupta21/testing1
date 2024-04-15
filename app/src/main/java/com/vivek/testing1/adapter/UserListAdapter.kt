package com.vivek.testing1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vivek.testing1.databinding.UserListItemsBinding
import com.vivek.testing1.models.UserDataResponseModelItem

class UserListAdapter(private var getList: ArrayList<UserDataResponseModelItem>) :
    RecyclerView.Adapter<UserListAdapter.Holder>() {

    class Holder(val binding: UserListItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(UserListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val model = getList[position]
        holder.binding.albumIdTxt.text = model.albumId.toString()
        holder.binding.userTitleTxt.text = model.title
        holder.binding.userImage.loadImage(model.url)
        holder.binding.thumbnailImg.loadImage(model.thumbnailUrl)

    }

    override fun getItemCount() = getList.size

    fun initList(initialList: ArrayList<UserDataResponseModelItem>) {
        getList = initialList
        notifyDataSetChanged()
    }

    private fun ImageView.loadImage(url: String) {
        Glide.with(context)
            .load(url)
            .into(this)
    }
}