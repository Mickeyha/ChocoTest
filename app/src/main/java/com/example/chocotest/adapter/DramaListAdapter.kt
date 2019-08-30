package com.example.chocotest.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.chocotest.R
import com.example.chocotest.db.entity.ChocoDataEntity
import com.example.chocotest.util.TimeUtil
import com.example.chocotest.util.TimeUtil.Companion.SIMPLE_DATE_FORMAT_YY_MM_DD
import com.example.chocotest.view.DramaRateView
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_drama_info.view.*
import timber.log.Timber


class DramaListAdapter(private val dramaList: List<ChocoDataEntity>) : RecyclerView.Adapter<DramaListAdapter.Holder>() {

    lateinit var onClick : PublishSubject<ChocoDataEntity>

    override fun getItemCount(): Int = dramaList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.item_drama_info,
            parent,
            false)

        return Holder(view)
    }


    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val context = holder.cardView.context

        val chocoDataEntity: ChocoDataEntity = dramaList[position]

        // click event
        holder.cardView.setOnClickListener {
            // fire recyclerView click event
            Timber.d("Mickey, OnCLick")
            onClick.onNext(chocoDataEntity)
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

        // drama created at
        holder.dramaCreatedAt.text =
            TimeUtil.INSTANCE
                .transferToNewFormat(chocoDataEntity.createdAt, SIMPLE_DATE_FORMAT_YY_MM_DD)
    }


    class Holder(val view: View): RecyclerView.ViewHolder(view) {
        val cardView : CardView =  view.view_card
        val dramaName : TextView =  view.text_drama_name
        val dramaPic : ImageView =  view.pic_drama
        val dramaRate : DramaRateView = view.text_rate
        val dramaCreatedAt : TextView = view.text_created_at
    }
}