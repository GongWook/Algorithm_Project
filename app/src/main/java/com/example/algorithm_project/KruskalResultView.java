package com.example.algorithm_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.*;

public class KruskalResultView extends SurfaceView implements SurfaceHolder.Callback {

    private KruskalThread thread;
    public Integer drawmode = 0;

    public List<Node> nodeArrayList = new ArrayList<>();
    public List<Edge> edgeArrayListList = new ArrayList<>();

    public List<Node> getNodeArrayList() {
        return nodeArrayList;
    }

    public void setNodeArrayList(List<Node> nodeArrayList) {
        this.nodeArrayList = nodeArrayList;
    }

    public List<Edge> getEdgeArrayListList() {
        return edgeArrayListList;
    }

    public void setEdgeArrayListList(List<Edge> edgeArrayListList) {
        this.edgeArrayListList = edgeArrayListList;
    }

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public KruskalResultView(Context context) {
        super(context);
        init();
    }

    public KruskalResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KruskalResultView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        thread.myThreadRun=false;
    }

    private void init() {
        getHolder().addCallback(this);
        thread = new KruskalThread(getHolder(), this);

        setFocusable(true); // make sure we get key events

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.RED);
        paint.setTextSize(45);
    }

    private void initpaint()
    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.RED);
        paint.setTextSize(45);
    }

    private void initBackGround()
    {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
    }


    private class KruskalThread extends Thread{
        private SurfaceHolder mThreadSurfaceHolder;
        private KruskalResultView mThreadKruskalView;
        private boolean myThreadRun = false;

        public KruskalThread(SurfaceHolder surfaceHolder, KruskalResultView surfaceDrawTouchView) {
            mThreadSurfaceHolder = surfaceHolder;
            mThreadKruskalView = surfaceDrawTouchView;
        }

        public void setRunning(boolean b) {
            myThreadRun = b;
        }

        @Override
        public void run() {
            // super.run();
            while (myThreadRun) {
                Canvas c = null;
                try {
                    c = mThreadSurfaceHolder.lockCanvas(null);
                    synchronized (mThreadSurfaceHolder) {

                        //그림 그리는 곳
                        if(drawmode ==1)
                        {
                            initBackGround();
                            c.drawPaint(paint);
                            initpaint();
                            int count = 0;
                            if(!nodeArrayList.isEmpty())
                            {
                                for(Node n : nodeArrayList)
                                {
                                    c.drawCircle(n.getX(),n.getY(),30,paint);
                                    Integer num = n.getNumber()+1;
                                    if (n.getNumber() < 10) {
                                        c.drawText(num.toString(), n.getX() - 12, n.getY() + 15, paint);
                                    } else {
                                        c.drawText(num.toString(), n.getX() - 21, n.getY() + 15, paint);
                                    }
                                }

                                for(Edge e : edgeArrayListList)
                                {
                                    if(count!=edgeArrayListList.size()/2)
                                    {
                                        c.drawLine(nodeArrayList.get(e.start-1).getX(),nodeArrayList.get(e.start-1).getY(),
                                                nodeArrayList.get(e.end-1).getX(),nodeArrayList.get(e.end-1).getY(),paint);
                                        c.drawText(e.value.toString(),
                                                (nodeArrayList.get(e.start-1).getX() + nodeArrayList.get(e.end-1).getX())/2 + 15,
                                                (nodeArrayList.get(e.start-1).getY() + nodeArrayList.get(e.end-1).getY())/2 - 30, paint);
                                    }
                                    count++;
                                }
                            }
                        }

                        if(drawmode ==2)
                        {
                            initBackGround();
                            c.drawPaint(paint);
                            initpaint();
                            if(!nodeArrayList.isEmpty())
                            {
                                for(Node n : nodeArrayList)
                                {
                                    c.drawCircle(n.getX(),n.getY(),30,paint);
                                    Integer num = n.getNumber()+1;
                                    if (n.getNumber() < 10) {
                                        c.drawText(num.toString(), n.getX() - 12, n.getY() + 15, paint);
                                    } else {
                                        c.drawText(num.toString(), n.getX() - 21, n.getY() + 15, paint);
                                    }
                                }

                                for(Edge e : edgeArrayListList)
                                {
                                    c.drawLine(nodeArrayList.get(e.start-1).getX(),nodeArrayList.get(e.start-1).getY(),
                                            nodeArrayList.get(e.end-1).getX(),nodeArrayList.get(e.end-1).getY(),paint);
                                    c.drawText(e.value.toString(),
                                            (nodeArrayList.get(e.start-1).getX() + nodeArrayList.get(e.end-1).getX())/2 + 15,
                                            (nodeArrayList.get(e.start-1).getY() + nodeArrayList.get(e.end-1).getY())/2 - 30, paint);
                                }
                            }
                        }

                    }
                } finally {
                    if (c != null) {
                        mThreadSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
}
