package com.cptp.console.keystone;

public class Vertex {
    public int x = 0;
    public int y = 0;

    public Vertex() {
    }

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vertex(String strVertex) {
        String v[] = strVertex.trim().split(",");
        if (v != null) {
            this.x = Integer.parseInt(v[0]);
            this.y = Integer.parseInt(v[1]);			
        }
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
