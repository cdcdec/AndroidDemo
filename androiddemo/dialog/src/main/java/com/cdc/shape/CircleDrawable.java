package com.cdc.shape;

import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;

public class CircleDrawable extends ShapeDrawable {

    public CircleDrawable(int backgroundColor, int radius) {
        this(backgroundColor, radius, radius, radius, radius);
    }

    public CircleDrawable(int backgroundColor, int radiusOut, int radiusInner, RectF rectF) {
        this(backgroundColor, radiusOut, radiusOut, radiusOut, radiusOut,radiusInner,radiusInner,radiusInner,radiusInner,rectF);
    }

    public CircleDrawable(int backgroundColor, int leftTopRadius, int rightTopRadius
            , int rightBottomRadius, int leftBottomRadius) {
        getPaint().setColor(backgroundColor);//内部填充颜色
        //圆角半径
        setShape(RoundRectShapeUtil.getRoundRectShape(leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius));
    }


    public CircleDrawable(int backgroundColor, int leftTopRadius, int rightTopRadius
            , int rightBottomRadius, int leftBottomRadius, int leftInnerTop, int rightInnerTop, int rightInnerBottom, int leftInnerBottom, RectF rectF) {
        getPaint().setColor(backgroundColor);//内部填充颜色
        //圆角半径
        setShape(RoundRectShapeUtil.getRoundRectShape(leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, leftInnerTop, rightInnerTop, rightInnerBottom, leftInnerBottom,rectF));
    }


}
