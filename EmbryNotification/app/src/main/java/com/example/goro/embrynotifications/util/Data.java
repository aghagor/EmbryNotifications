package com.example.goro.embrynotifications.util;

public class Data {
    private int id;
    private String col1;
    private String col2;

    public Data(int id, String col1, String col2) {
        this.id = id;
        this.col1 = col1;
        this.col2 = col2;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCol1() {
        return col1;
    }
    public void setCol1(String col1) {
        this.col1 = col1;
    }
    public String getCol2() {
        return col2;
    }
    public void setCol2(String col2) {
        this.col2 = col2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(", ").append(col1).append(", ").append(col2);
        return sb.toString();
    }
}