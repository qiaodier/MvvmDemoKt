package com.mvvm.kt.mvvm.view



import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 *@author qiaohao
 *@date 21-4-9 下午6:14
 *自定义分段进度条
 */
class MulticolourSeekBar : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, style: Int) : super(context, attrs, style)

    /*圆角的半径，依次为左上角xy半径，右上角，右下角，左下角*/
    private val mRids = floatArrayOf(10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f)

    /**
     * 画笔
     */
    private var mMulticlourPaint: Paint

    private var mMulticlourPaintBg: Paint

    //中间间隔的个数
    private var mMulticlourCount: Int = 4

    //中间间隔的宽度
    private var mMulticlourWidth = 5

    //整个view 的宽度
    private var mSeekBarWidth: Int = 0

    //整个view 的高度
    private var mSeekBarHeight: Int = 0

    //当前电量进度
    private var mProgress = 0

    //本次需要绘制几块
    private var mBlockCount = 0;

    private var mPath:Path

    private var mRectF:RectF

    init {

        //画笔
        mMulticlourPaint = Paint()
        mMulticlourPaint.color = Color.GREEN
        mMulticlourPaint.isAntiAlias = true
        mMulticlourPaintBg = Paint()
        mMulticlourPaintBg.color = Color.GRAY
        mMulticlourPaintBg.isAntiAlias = true
        mPath = Path()

        mRectF = RectF()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mSeekBarWidth = MeasureSpec.getSize(widthMeasureSpec)
        mSeekBarHeight = MeasureSpec.getSize(heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mRectF.set(0f,0f,width.toFloat(),height.toFloat())
    }


    override fun onDraw(canvas: Canvas?) {
        var saveCount = canvas?.saveLayer(mRectF,null,Canvas.ALL_SAVE_FLAG)
        mMulticlourPaint.color = Color.WHITE
        canvas?.drawRoundRect(mRectF,20f,20f,mMulticlourPaint)
        mMulticlourPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        mMulticlourPaintBg.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        //计算出每块宽度
        val singleWidth = (mSeekBarWidth - (mMulticlourCount * mMulticlourWidth)) / mMulticlourWidth
        if (mProgress > 100 || mProgress < 0) return
        //计算出本次需要绘制几块
        mBlockCount = this.mProgress / 20
        if (this.mProgress % 20 > 0) {
            mBlockCount += 1
        }
        //判断只绘制第一块的画，给画笔设置红色
        if (mBlockCount == 1) {
            mMulticlourPaint.color = Color.RED
        }else{
            mMulticlourPaint.color = Color.GREEN
        }

        for (i in 0 until 5) {
            if (i < mBlockCount) {
                mRectF = RectF(singleWidth.toFloat() * i + mMulticlourWidth * i, 0f, singleWidth.toFloat() * (i + 1) + mMulticlourWidth * i, mSeekBarHeight.toFloat())
                canvas?.drawRect(mRectF, mMulticlourPaint)
            } else {
                if(i==4){
                    mRectF = RectF(singleWidth.toFloat() * i + mMulticlourWidth * i, 0f, singleWidth.toFloat() * (i + 1) + mMulticlourWidth * (i + 1), mSeekBarHeight.toFloat())
                }else{
                    mRectF = RectF(singleWidth.toFloat() * i + mMulticlourWidth * i, 0f, singleWidth.toFloat() * (i + 1) + mMulticlourWidth * i, mSeekBarHeight.toFloat())
                }
                canvas?.drawRect(mRectF, mMulticlourPaintBg)
            }
        }
        super.onDraw(canvas)
        mMulticlourPaint.xfermode = null
        mMulticlourPaintBg.xfermode = null
        if (saveCount != null) {
            canvas?.restoreToCount(saveCount)
        }
    }

    /**
     * 设置进度
     */
    fun setProgress(progress: Int) {
        mProgress = progress
        requestLayout()
    }

}