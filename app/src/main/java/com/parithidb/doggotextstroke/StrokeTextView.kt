package com.parithidb.doggotextstroke

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView

class StrokeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialTextView(context, attrs, defStyleAttr) {

    private var strokeWidthPx: Float = 6f
    private var strokeColor: Int = Color.BLACK

    init {
        attrs?.let {
            val ta = context.obtainStyledAttributes(it, R.styleable.StrokeTextView)
            strokeColor = ta.getColor(R.styleable.StrokeTextView_strokeColor, Color.BLACK)
            strokeWidthPx = ta.getDimension(R.styleable.StrokeTextView_strokeWidth, 6f)
            ta.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        val originalColor = currentTextColor
        val originalStyle = paint.style
        val originalStrokeWidth = paint.strokeWidth

        // Draw stroke
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidthPx
        setTextColor(strokeColor)
        super.onDraw(canvas)

        // Draw fill
        paint.style = Paint.Style.FILL
        paint.strokeWidth = originalStrokeWidth
        setTextColor(originalColor)
        super.onDraw(canvas)

        // Restore paint style (safety)
        paint.style = originalStyle
    }
}
