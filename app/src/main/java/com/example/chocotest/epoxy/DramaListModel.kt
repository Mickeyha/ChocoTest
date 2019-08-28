package com.example.chocotest.epoxy

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.chocotest.R
import com.example.chocotest.libs.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_drama_info)
abstract class DramaListModel : EpoxyModelWithHolder<DramaListModel.Holder>() {

    @EpoxyAttribute
    var title: String = ""
    @EpoxyAttribute
    var imageUrl:String = ""
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null
    @EpoxyAttribute
    var rate: String = ""

    override fun bind(holder: Holder) {
        val context = holder.cardView.context

        // click event
        holder.cardView.setOnClickListener(clickListener)

        // drama thumb
        val requestOptions = RequestOptions()
        requestOptions.placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.colorSearch)))
        requestOptions.centerCrop()
        Glide.with(context).load(imageUrl).apply(requestOptions).into(holder.dramaPic)

        // drama name
        holder.dramaName.text = title

        // drama rate
        holder.dramaRate.text = rate
    }

    class Holder : KotlinEpoxyHolder() {
        val cardView by bind<CardView> (R.id.view_card)
        val dramaName by bind<TextView> (R.id.text_drama_name)
        val dramaPic by bind<ImageView> (R.id.pic_drama)
        val dramaRate by bind<TextView> (R.id.text_rate)
    }
}