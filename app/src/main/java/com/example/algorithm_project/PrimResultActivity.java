package com.example.algorithm_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class PrimResultActivity extends AppCompatActivity {

    private Graph graph;
    private Button HalfBtn;
    private Button FinalBtn;
    private KruskalResultView primResultView;
    ArrayList<Edge> mst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prim_result);

        HalfBtn = (Button)findViewById(R.id.Half_Btn);
        FinalBtn = (Button)findViewById(R.id.Final_Btn);
        primResultView = (KruskalResultView)findViewById(R.id.primView);

        Intent intent = getIntent();
        graph = (Graph)intent.getSerializableExtra("Graph");

        new Thread(new Runnable() { @Override public void run() {

            //prim 알고리즘 실행
            mst = Prim(graph);

            primResultView.setEdgeArrayListList(mst);
            primResultView.setNodeArrayList(graph.getNodeArrayList());

        } }).start();


        HalfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primResultView.drawmode=1;
            }
        });

        FinalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primResultView.drawmode=2;
            }
        });
    }

    public ArrayList<Edge> Prim(Graph graph)
    {
        int V,E;
        LinkedList<Edge>[] edgeGraph;
        ArrayList<Edge> mst;
        boolean[] visit;

        V = graph.getNodeArrayList().size();
        E = graph.getEdgeArrayList().size();
        edgeGraph = new LinkedList[V+1];
        visit = new boolean[V+1];

        for(int i=0; i<=V; i++)
        {
            edgeGraph[i] = new LinkedList<>();
        }

        mst = new ArrayList<>();

        for(int i =0; i<E; i++)
        {
            int v1 = graph.edgeArrayList.get(i).start;
            int v2 = graph.edgeArrayList.get(i).end;
            int value = graph.edgeArrayList.get(i).value;

            edgeGraph[v1].add(new Edge(v1,v2,value));
            edgeGraph[v2].add(new Edge(v2,v1,value));
        }

        mst = makeMst(visit,edgeGraph);
        return mst;
    }

    public static ArrayList<Edge> makeMst(boolean[] visit,LinkedList<Edge>[] edgeGraph)
    {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Edge> mst = new ArrayList<>();

        queue.add(1);

        while(!queue.isEmpty())
        {
            int nowV = queue.poll();
            visit[nowV] = true;

            for(Edge edge : edgeGraph[nowV])
            {
                if(!visit[edge.end])
                {
                    pq.add(edge);
                }
            }

            while(!pq.isEmpty())
            {
                Edge edge = pq.poll();
                if(!visit[edge.end])
                {
                    queue.add(edge.end);
                    visit[edge.end] = true;
                    mst.add(edge);
                    break;
                }
            }
        }

        return mst;
    }

}
