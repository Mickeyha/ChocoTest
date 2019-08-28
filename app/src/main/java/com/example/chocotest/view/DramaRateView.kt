package com.example.chocotest.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.chocotest.R
import kotlinx.android.synthetic.main.view_drama_rate.view.*


class DramaRateView @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_drama_rate, this, true)
    }

    fun setRate(rate: Float) {
        text_star.text = String.format("%.2f", rate)
    }
}