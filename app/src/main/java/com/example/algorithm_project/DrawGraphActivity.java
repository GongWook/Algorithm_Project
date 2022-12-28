package com.example.algorithm_project;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class DrawGraphActivity extends AppCompatActivity {

    private TextView tx1;
    private TextView tx2;

    private SurfaceDrawTouchView surfaceDrawTouchView;
    private List<Node> nodeArrayList = new ArrayList<>();
    private List<Edge> edgeArrayListList = new ArrayList<>();
    //private LinkedList<Edge>[] edgeGraph = new LinkedList[11];
    private Graph graph;

    private Spinner spinner1;
    private Spinner spinner2;

    private EditText valueBox;
    private Button input_button;
    private Button result_button;
    Integer items[] = {1,2,3,4,5,6,7,8,9,10};

    int start, end, value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawgraph);

        /* edgeGraph 초기화
        for(int i=0; i<11; i++)
        {
            edgeGraph[i] = new LinkedList<>();
        }
       
         */

        //Surfcae view 가져오기
        surfaceDrawTouchView = findViewById(R.id.SurfaceDrawTouchView);

        //결과버튼
        result_button = (Button)findViewById(R.id.resultButton);

        //스피너 드록박스
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        //value box값 가져오기
        valueBox = (EditText)findViewById(R.id.ValueEdit);

        //입력 버튼 기능 (Edge 그리기)
        input_button = findViewById(R.id.input_button);
        input_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View v) {
                value = Integer.parseInt(valueBox.getText().toString());
                Edge edge = new Edge(start,end,value);
                Edge edge2 = new Edge(end,start,value);

                //edgeGraph edge 추가
                //edgeGraph[start].add(edge);
                //edgeGraph[end].add(edge2);

                edgeArrayListList.add(edge);
                //drawTouchView.setEdgeArrayListList(edgeArrayListList);
                surfaceDrawTouchView.edgeArrayListList.add(edge);

                Toast.makeText(DrawGraphActivity.this, edge.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //드롭박스 선택시 호출
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                start = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                end = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        result_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View v) {

                graph = new Graph(surfaceDrawTouchView.getNodeArrayList(),edgeArrayListList);
                Intent intent = new Intent(DrawGraphActivity.this,ResultActivity.class);
                intent.putExtra("Graph",graph);
                startActivity(intent);

            }
        });

    }
}
