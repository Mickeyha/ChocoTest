package com.example.chocotest.adapter

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.chocotest.R
import com.example.chocotest.db.entity.ChocoDataEntity
import com.example.chocotest.view.DramaRateView
import kotlinx.android.synthetic.main.item_drama_info.view.*
import timber.log.Timber

class DramaListAdapter(private val dramaList: List<ChocoDataEntity>) : RecyclerView.Adapter<DramaListAdapter.Holder>() {

    var onClick = MutableLiveData<ChocoDataEntity>()

    override fun getItemCount(): Int = dramaList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.item_drama_info,
            parent,
            false)

        return Holder(view)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val context = holder.cardView.context

        val chocoDataEntity: ChocoDataEntity = dramaList[position]

        // click event
        holder.cardView.setOnClickListener {
            // fire recyclerView click event
            Timber.d("Mickey, OnCLick")
            onClick.value = chocoDataEntity
        }

        // drama thumb
        val requestOptions = RequestOptions()
        requestOptions.placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.colorSearch)))
        requestOptions.centerCrop()
        Glide.with(context).load(chocoDataEntity.thumb).apply(requestOptions).into(holder.dramaPic)

        // drama name
        holder.dramaName.text = chocoDataEntity.name

        // drama rate
        holder.dramaRate.setRate(chocoDataEntity.rating)
    }


    class Holder(val view: View): RecyclerView.ViewHolder(view) {
        val cardView : CardView =  view.view_card
        val dramaName : TextView =  view.text_drama_name
        val dramaPic : ImageView =  view.pic_drama
        val dramaRate : DramaRateView = view.text_rate
    }
}