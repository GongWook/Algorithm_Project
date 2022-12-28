package com.example.algorithm_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KruskalResultActivity extends AppCompatActivity {

    private Graph graph;
    private Button HalfBtn;
    private Button FinalBtn;
    private KruskalResultView kruskalResultView;
    private List<Edge> edgeArrayList= new ArrayList<>();

    ArrayList<Edge> mst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kruskal_result);

        Intent intent = getIntent();
        graph = (Graph)intent.getSerializableExtra("Graph");
        edgeArrayList = graph.getEdgeArrayList();

        HalfBtn = (Button)findViewById(R.id.HalfBtn);
        FinalBtn = (Button)findViewById(R.id.FinalBtn);
        kruskalResultView = findViewById(R.id.kruskalView);

        new Thread(new Runnable() { @Override public void run() {
            mst=kruskal(graph);

            kruskalResultView.setEdgeArrayListList(mst);
            kruskalResultView.setNodeArrayList(graph.getNodeArrayList());
        } }).start();


        HalfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kruskalResultView.drawmode=1;
            }
        });

        FinalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kruskalResultView.drawmode=2;
            }
        });


    }

    public ArrayList<Edge> kruskal (Graph graph)
    {
        int V, E;
        ArrayList<Edge> mst;
        int[] arr ;
        PriorityQueue<Edge> pq;

        V = graph.nodeArrayList.size();
        E = graph.edgeArrayList.size();

        //mst 최종 간선 수, arr union-find위한 배열
        mst = new ArrayList<>();
        arr = new int[V+1];
        pq = new PriorityQueue<>();

        for(int i =0; i<=V; i++)
        {
            arr[i]=i;
        }

        for(Edge e : graph.getEdgeArrayList())
        {
            //Log.d("Edge test", e.toString());
            pq.add(e);

        }

        //Log.d("pq test", pq.toString());
       // Log.d("pq test", pq.poll().toString());

        while(mst.size() < (V-1))
        {

            Edge edge = pq.poll();
            if(find(edge.getStart(),arr)!=find(edge.getEnd(), arr))
            {
                mst.add(edge);
                union(edge.getStart(), edge.getEnd(), arr);
            }
        }

        return mst;
    }

    public static int find(int n, int[] arr)
    {
        if(n==arr[n])
        {
            return n;
        }else
        {
            int p = find(arr[n], arr);
            arr[n]=p;
            return p;
        }
    }

    public static void union(int n1, int n2, int[] arr)
    {
        int p1 = find(n1, arr);
        int p2 = find(n2, arr);

        if(p1 != p2)
        {
            arr[p1] = p2;
        }
    }

}
