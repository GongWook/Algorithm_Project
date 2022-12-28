package com.example.algorithm_project;

import java.io.Serializable;

public class Node implements Serializable {

    private float X;
    private float Y;
    private Integer Number;

    public Node(float x, float y, Integer number) {
        this.X = x;
        this.Y = y;
        this.Number = number;
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    @Override
    public String toString() {
        return "Node{" +
                "X=" + X +
                ", Y=" + Y +
                ", Number=" + Number +
                '}';
    }
}
