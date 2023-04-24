package com.tta.todoapplication.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tta.todoapplication.data.models.ToDoData
import com.tta.todoapplication.databinding.ItemTaskBinding

class ItemTaskAdapter : RecyclerView.Adapter<ItemTaskAdapter.ItemTaskAdapterViewHolder>() {
    private var imageList : List<ToDoData> = listOf()
    private lateinit var context: Context

    fun setTaskList(imageList : List<ToDoData>,context: Context){
        this.imageList = imageList
        this.context = context
        notifyDataSetChanged()
    }

    private var onClickUpdate: ((i: Int) -> Unit)? = null
    fun setClickGoto(position: ((i: Int) -> Unit)) {
        onClickUpdate = position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTaskAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return ItemTaskAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemTaskAdapterViewHolder, position: Int) {
        var image = imageList[position]
        Glide.with(context)
            .load(image.numberIcon)
            .into(holder.binding.imageView4)

        holder.binding.tvNameCategory.text = image.nameCategory
        holder.binding.tvTaskTitle.text = image.title
        holder.binding.tvTaskTime.text = image.time

        holder.binding.cardTask.setOnClickListener {
            onClickUpdate?.let {
                it(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return imageList.size
    }
    class ItemTaskAdapterViewHolder(
        val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }
}