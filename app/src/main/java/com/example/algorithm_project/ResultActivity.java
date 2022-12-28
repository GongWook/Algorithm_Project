package com.example.algorithm_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

public class ResultActivity extends AppCompatActivity {

    private Graph graph;
    private LinkedList<Edge>[] edgeGraph;

    private Button primResultBtn;
    private Button kruskalResultBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        graph = (Graph)intent.getSerializableExtra("Graph");

        primResultBtn = (Button)findViewById(R.id.primresult);
        kruskalResultBtn = (Button)findViewById(R.id.kruskalresult);

        primResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ResultActivity.this,PrimResultActivity.class);
                intent1.putExtra("Graph",graph);
                startActivity(intent1);
            }
        });

        kruskalResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ResultActivity.this,KruskalResultActivity.class);
                intent2.putExtra("Graph",graph);
                startActivity(intent2);
            }
        });

    }
}
