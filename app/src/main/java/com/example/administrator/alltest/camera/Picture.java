package com.example.administrator.alltest.camera;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class Picture {
    private String name;
    private int resource;
    public Picture(String name,int resource){
        this.name=name;
        this.resource=resource;
    }

    public String getName(){
        return name;
    }
    public int getResource(){
        return resource;
    }
}
