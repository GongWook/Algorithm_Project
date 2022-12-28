package com.example.algorithm_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.*;

public class DrawTouchView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float initX, initY, radius;
    private Integer count = 1;

    private List<Node> arr = new ArrayList<>();
    public List<Edge> edgeArrayListList = new ArrayList<>();

    public void setArr(List<Node> arr) {
        this.arr = arr;
    }

    public List<Edge> getEdgeArrayListList() {
        return edgeArrayListList;
    }

    public void setEdgeArrayListList(List<Edge> edgeArrayListList) {
        this.edgeArrayListList = edgeArrayListList;
    }

    public List<Node> getArr() {
        return arr;
    }

    public DrawTouchView(Context context) {
        super(context);
        init();
    }

    public DrawTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init()
    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLACK);
        paint.setTextSize(45);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if(action == MotionEvent.ACTION_MOVE)
        {

        }
        else if(action == MotionEvent.ACTION_DOWN)
        {
            initX = event.getX();
            initY = event.getY();
            radius = 30;

            Node node = new Node(initX,initY,count);
            arr.add(node);

            invalidate();
        }
        else if(action == MotionEvent.ACTION_UP)
        {
            count++;
            if(count>=10)
            {
                paint.setTextSize(40);
            }
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Node n : arr) {
            canvas.drawCircle(n.getX(), n.getY(), radius, paint);
            if (n.getNumber() < 10) {
                canvas.drawText(n.getNumber().toString(), n.getX() - 12, n.getY() + 15, paint);
            } else {
                canvas.drawText(n.getNumber().toString(), n.getX() - 21, n.getY() + 15, paint);
            }
        }
    }

}
