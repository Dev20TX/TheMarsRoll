package com.dev20.themarsroll.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev20.themarsroll.R
import com.dev20.themarsroll.models.Photo
import kotlinx.android.synthetic.main.item_photo_preview.view.*

class MarsPhotoAdapter : RecyclerView.Adapter<MarsPhotoAdapter.MarsPhotoViewHolder>() {

     class MarsPhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.imgSrc == newItem.imgSrc
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photo_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Photo) -> Unit)? = null

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val photo = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(photo.imgSrc).into(ivMarsPhoto)
            earthDate.text = photo.earthDate
            setOnClickListener {
                onItemClickListener?.let { it(photo) }
                }
            }
        }
        fun setOnItemClickListener(listener: (Photo) -> Unit) {
            onItemClickListener = listener
        }
    }