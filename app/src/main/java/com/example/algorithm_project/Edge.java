package com.example.algorithm_project;

import java.io.Serializable;

public class Edge implements Serializable ,Comparable<Edge>{

    Integer start, end, value;

    public Edge(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Edge o) {
        return this.value - o.value;
    }
}
