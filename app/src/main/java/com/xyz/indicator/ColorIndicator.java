package com.xyz.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.RequiresApi;

public class ColorIndicator extends View {

    private Paint paint = new Paint();
    private float indicatorSize = 24;
    private float indicatorPadding = 60;
    private float indicatorRectWidth = 40;

    private int normalColor = 0x4cd8d8d8;
    private int selectedColor = 0xFFd8d8d8;
    private int indicatorNum;
    private int selectedIndex;
    private boolean showLongRect = false;

    public ColorIndicator(Context context) {
        this(context, null);
    }

    public ColorIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ColorIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
    }

    public void setIndicatorNum(int indicatorNum) {
        this.indicatorNum = indicatorNum;
        requestLayout();
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        invalidate();

    }

    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        invalidate();
    }

    public void setIndicatorPadding(float indicatorPadding) {
        this.indicatorPadding = indicatorPadding;
        requestLayout();
    }

    public void setIndicatorSize(float indicatorSize) {
        this.indicatorSize = indicatorSize;
        requestLayout();
    }

    /**
     * 设置选中时是长条形的圆中间的矩形宽度
     */
    public void setIndicatorRectWidth(float indicatorRectWidth) {
        this.indicatorRectWidth = indicatorRectWidth;
        requestLayout();
    }

    /**
     * 设置选中的形状（圆点 of 长矩形）
     */
    public void setIndicatorLongRect(boolean showLongRect) {
        this.showLongRect = showLongRect;
        invalidate();
    }

    /**
     * 设置选中的角标
     */
    public void selected(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = (int) (indicatorNum * indicatorSize + (indicatorNum - 1) * indicatorPadding + indicatorRectWidth);
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec((int) indicatorSize, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showLongRect) {
            //选中item显示长条形圆
            showLongRectView(canvas);
        } else {//全是圆形
            showCircleView(canvas);
        }
    }

    private void showLongRectView(Canvas canvas) {
        for (int i = 0; i < indicatorNum; i++) {
            if (selectedIndex == i) {
                paint.setColor(selectedColor);
                float left = i * (indicatorSize + indicatorPadding);
                float top = 0;
                float right = i * (indicatorSize + indicatorPadding) + indicatorSize;
                float bottom = canvas.getHeight();
                RectF rectF = new RectF(left, top, right, bottom);
                canvas.drawArc(rectF, -270, 180, true, paint);
                RectF rectF1 = new RectF(left + indicatorSize / 2, top, left + indicatorRectWidth, bottom);
                canvas.drawRect(rectF1, paint);
                RectF rectF2 = new RectF(left + indicatorRectWidth - indicatorSize / 2, top, left + indicatorRectWidth + indicatorSize / 2, bottom);
                canvas.drawArc(rectF2, 270, 180, true, paint);
            } else {
                paint.setColor(normalColor);
                if (i < selectedIndex) {
                    canvas.drawCircle(i * (indicatorSize + indicatorPadding) + indicatorSize / 2, canvas.getHeight() / 2, indicatorSize / 2, paint);
                } else {
                    canvas.drawCircle(i * (indicatorSize + indicatorPadding) + indicatorSize / 2 + indicatorRectWidth, canvas.getHeight() / 2, indicatorSize / 2, paint);
                }
            }
        }
    }

    private void showCircleView(Canvas canvas) {
        for (int i = 0; i < indicatorNum; i++) {
            if (selectedIndex == i) {
                paint.setColor(selectedColor);
            } else {
                paint.setColor(normalColor);
            }
            canvas.drawCircle(i * (indicatorSize + indicatorPadding) + indicatorSize / 2, canvas.getHeight() / 2, indicatorSize / 2, paint);
        }
    }

}
