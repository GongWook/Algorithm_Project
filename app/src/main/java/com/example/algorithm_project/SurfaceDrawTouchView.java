package com.example.algorithm_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.R)
public class SurfaceDrawTouchView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceThread thread;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float initX, initY, radius;
    private Integer count = 0;

    private List<Node> nodeArrayList = new ArrayList<>();
    public List<Edge> edgeArrayListList = new ArrayList<>();
    public LinkedList<Edge>[] graph;

    private boolean drawing=false;

    public SurfaceDrawTouchView(Context context) {
        super(context);
        init();
    }

    public SurfaceDrawTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfaceDrawTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
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

    public List<Node> getNodeArrayList() {
        return nodeArrayList;
    }

    private void init() {
        getHolder().addCallback(this);
        thread = new SurfaceThread(getHolder(), this);

        setFocusable(true); // make sure we get key events

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.RED);
        paint.setTextSize(45);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // return super.onTouchEvent(event);
        int action = event.getAction();
        if (action == MotionEvent.ACTION_MOVE) {

        } else if (action == MotionEvent.ACTION_DOWN) {
            initX = event.getX();
            initY = event.getY();
            radius = 30;

            Node node = new Node(initX,initY,count);
            nodeArrayList.add(node);

            count++;

        } else if (action == MotionEvent.ACTION_UP) {

        }

        return true;
    }

    private class SurfaceThread extends Thread{
        private SurfaceHolder mThreadSurfaceHolder;
        private SurfaceDrawTouchView mThreadSurfaceView;
        private boolean myThreadRun = false;

        public SurfaceThread(SurfaceHolder surfaceHolder, SurfaceDrawTouchView surfaceDrawTouchView) {
            mThreadSurfaceHolder = surfaceHolder;
            mThreadSurfaceView = surfaceDrawTouchView;
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

                            c.drawCircle(initX, initY, radius, paint);
                        if (count < 10) {
                            c.drawText(count.toString(), initX - 12, initY + 15, paint);
                        } else {
                            c.drawText(count.toString(), initX - 21, initY + 15, paint);
                        }

                        if(!edgeArrayListList.isEmpty())
                        {
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
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    if (c != null) {
                        mThreadSurfaceHolder.unlockCanvasAndPost(c);

                    }
                }
            }
        }
    }
}
