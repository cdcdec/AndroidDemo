package com.cdc.shape;

import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;

public class RoundRectShapeUtil {
    public static RoundRectShape getRoundRectShape(int leftTop, int rightTop, int rightBottom, int leftBottom){
        float outerRadii[] = new float[8];
        if (leftTop > 0) {
            outerRadii[0] = leftTop;
            outerRadii[1] = leftTop;
        }
        if (rightTop > 0) {
            outerRadii[2] = rightTop;
            outerRadii[3] = rightTop;
        }
        if (rightBottom > 0) {
            outerRadii[4] = rightBottom;
            outerRadii[5] = rightBottom;
        }
        if (leftBottom > 0) {
            outerRadii[6] = leftBottom;
            outerRadii[7] = leftBottom;
        }
        return new RoundRectShape(outerRadii, null, null);
    }


    public static RoundRectShape getRoundRectShape(int leftTop, int rightTop, int rightBottom, int leftBottom,int leftInnerTop, int rightInnerTop, int rightInnerBottom, int leftInnerBottom,RectF inset){
        float outerRadii[] = new float[8];
        float innerRadii[] = new float[8];

        // 内部矩形与外部矩形的距离  inset

        if (leftTop > 0) {
            outerRadii[0] = leftTop;
            outerRadii[1] = leftTop;
        }
        if (rightTop > 0) {
            outerRadii[2] = rightTop;
            outerRadii[3] = rightTop;
        }
        if (rightBottom > 0) {
            outerRadii[4] = rightBottom;
            outerRadii[5] = rightBottom;
        }
        if (leftBottom > 0) {
            outerRadii[6] = leftBottom;
            outerRadii[7] = leftBottom;
        }


        if (leftInnerTop > 0) {
            innerRadii[0] = leftInnerTop;
            innerRadii[1] = leftInnerTop;
        }
        if (rightInnerTop > 0) {
            innerRadii[2] = rightInnerTop;
            innerRadii[3] = rightInnerTop;
        }
        if (rightInnerBottom > 0) {
            innerRadii[4] = rightInnerBottom;
            innerRadii[5] = rightInnerBottom;
        }
        if (leftInnerBottom > 0) {
            innerRadii[6] = leftInnerBottom;
            innerRadii[7] = leftInnerBottom;
        }


        return new RoundRectShape(outerRadii, inset, innerRadii);
    }




}
