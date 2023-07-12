package com.bangkit.suitmediatestapplication.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.bangkit.suitmediatestapplication.R

class EditText : AppCompatEditText {

    constructor(context: Context) : super(context){

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){

    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context,attrs,defStyleAttr){

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        context.apply {
            background = ContextCompat.getDrawable(this, R.drawable.custom_form)
            setTextColor(ContextCompat.getColor(this, R.color.black))
            setHintTextColor(ContextCompat.getColor(this, androidx.appcompat.R.color.material_blue_grey_800))
        }
        isSingleLine = true
        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
    }

}