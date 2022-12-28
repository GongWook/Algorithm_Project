package com.example.algorithm_project;

import java.io.Serializable;
import java.util.*;

public class Graph implements Serializable {

    List<Node> nodeArrayList = new ArrayList<>();
    List<Edge> edgeArrayList = new ArrayList<>();
    //LinkedList<Edge>[] edgeGraph = new LinkedList[11];

    /*
    public Graph(List<Node> nodeArrayList, List<Edge> edgeArrayList, LinkedList<Edge>[] edgeGraph) {
        this.nodeArrayList = nodeArrayList;
        this.edgeArrayList = edgeArrayList;
        this.edgeGraph = edgeGraph;
    }


    public LinkedList<Edge>[] getEdgeGraph() {
        return edgeGraph;
    }

    public void setEdgeGraph(LinkedList<Edge>[] edgeGraph) {
        this.edgeGraph = edgeGraph;
    }

     */

    public Graph(List<Node> nodeArrayList, List<Edge> edgeArrayList) {
        this.nodeArrayList = nodeArrayList;
        this.edgeArrayList = edgeArrayList;
    }

    public List<Node> getNodeArrayList() {
        return nodeArrayList;
    }

    public void setNodeArrayList(List<Node> nodeArrayList) {
        this.nodeArrayList = nodeArrayList;
    }

    public List<Edge> getEdgeArrayList() {
        return edgeArrayList;
    }

    public void setEdgeArrayList(List<Edge> edgeArrayList) {
        this.edgeArrayList = edgeArrayList;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodeArrayList=" + nodeArrayList +
                ", edgeArrayList=" + edgeArrayList +
                '}';
    }
}
