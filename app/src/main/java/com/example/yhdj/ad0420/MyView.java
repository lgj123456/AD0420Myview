package com.example.yhdj.ad0420;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhdj on 2017/4/20.
 */

public class MyView extends View {
    private Paint mPaint;

    private Path pathRed = new Path();

    private float dx, dy, mx, my, ux, uy;
    private boolean isRed = true;
    private boolean isGreen = false;
    private boolean isBlack = false;
    private boolean isBlue = false;
    private Path pathBlack = new Path();
    private Path pathBlue = new Path();
    private Path pathGreen = new Path();
    List<Path> mPaths = new ArrayList<>();
    private Bitmap mBitmap;
    private Canvas mCanvas;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        mBitmap = Bitmap.createBitmap(720, 800, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawBitmap(mBitmap, 0, 0, mPaint);


        if (isRed) {
            canvas.drawPath(pathRed, mPaint);
        } else if (isBlack) {
            canvas.drawPath(pathBlack, mPaint);
        } else if (isBlue) {
            canvas.drawPath(pathBlue, mPaint);
        } else {
            canvas.drawPath(pathGreen, mPaint);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dx = event.getX();
                dy = event.getY();
                if (isRed) {
                    pathRed.moveTo(dx, dy);
                } else if (isBlack) {
                    pathBlack.moveTo(dx, dy);
                } else if (isBlue) {
                    pathBlue.moveTo(dx, dy);
                } else {
                    pathGreen.moveTo(dx, dy);
                }

                break;
            case MotionEvent.ACTION_MOVE:
                mx = event.getX();
                my = event.getY();
                if (isRed) {
                    pathRed.lineTo(mx, my);

                } else if (isBlack) {
                    pathBlack.lineTo(mx, my);

                } else if (isBlue) {
                    pathBlue.lineTo(mx, my);

                } else {
                    pathGreen.lineTo(mx, my);

                }

                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                ux = event.getX();
                uy = event.getY();
                if (isRed) {
                    pathRed.lineTo(ux, uy);
                    mCanvas.drawPath(pathRed, mPaint);
                } else if (isBlack) {
                    pathBlack.lineTo(ux, uy);
                    mCanvas.drawPath(pathBlack, mPaint);
                } else if (isBlue) {
                    pathBlue.lineTo(ux, uy);
                    mCanvas.drawPath(pathBlue, mPaint);
                } else {
                    pathGreen.lineTo(ux, uy);
                    mCanvas.drawPath(pathGreen, mPaint);
                }

                invalidate();
                break;
        }
        return true;
    }

    public void changeColor(int color) {
        switch (color) {
            case Constant.RED:
                changeRED();
                break;
            case Constant.BLUE:
                changeBlue();
                break;
            case Constant.BLACK:
                changeBlack();
                break;
            case Constant.GREEN:
                changeGreen();
                break;
        }
    }

    private void changeGreen() {

        mPaint.setColor(Color.GREEN);
        isBlack = false;
        isBlue = false;
        isRed = false;
        isGreen = true;

    }

    private void changeBlack() {

        mPaint.setColor(Color.BLACK);
        isBlack = true;
        isBlue = false;
        isRed = false;
        isGreen = false;
    }

    private void changeBlue() {

        mPaint.setColor(Color.BLUE);
        isBlack = false;
        isBlue = true;
        isRed = false;
        isGreen = false;
    }

    private void changeRED() {

        mPaint.setColor(Color.RED);
        isBlack = false;
        isBlue = false;
        isRed = true;
        isGreen = false;


    }

    public void eraser() {
        mPaint.setAlpha(0);
        mPaint.setColor(Color.WHITE);
        // mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));//橡皮擦效果
        mPaint.setColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(10);
    }

//    public void pencil(){
//        mPaint.setColor(Color.GRAY);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(10);
//
//    }
}



