package com.example.searchviewgit.ui.main.adaper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchviewgit.R
import com.example.searchviewgit.model.User
import com.example.searchviewgit.model.UserResponse
import kotlinx.android.synthetic.main.item_search.view.*

class MainAdapter
    : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var _data = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return MainViewHolder(mView)
    }

    override fun getItemCount(): Int = _data.size

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(user: User){
            with(itemView){
                name.text = user.login
                score.text = user.score.toString()
                Glide.with(context)
                    .load(user.avatar_url)
                    .into(imageView)
            }
        }

    }
}