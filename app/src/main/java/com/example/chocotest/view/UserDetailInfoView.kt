package com.example.chocotest.view

import android.content.Context
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.chocotest.R
import kotlinx.android.synthetic.main.view_user_info.view.*

class UserDetailInfoView @JvmOverloads constructor (
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attributeSet, defStyleAttr) {

    init {
        LayoutInflater.from(this@UserDetailInfoView.context).inflate(R.layout.view_user_info, this, true)
    }

    fun setData(login: String?) {

        // user pic
        image_user_pic.colorFilter = LightingColorFilter(Color.WHITE, ContextCompat.getColor(this.context, R.color.colorUserDetailImageColor))

        // login
        login?.run {
            text_login.text = login
        }
    }
}