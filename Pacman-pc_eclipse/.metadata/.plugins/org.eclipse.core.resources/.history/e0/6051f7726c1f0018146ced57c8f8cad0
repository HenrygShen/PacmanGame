package com.henryandlincoln.swimmyfishy;


public class Rectangle {

    public int x;
    public int y;
    public float width;
    public float height;

    private boolean firstIntersect;

    public Rectangle(){
        firstIntersect = true;
    }

    public void setWidth(float width){
        this.width = width;
    }

    public void setHeight(float height){
        this.height = height;
    }

    public boolean intersects(Rectangle other){

        return (this.x < (other.x + other.width) && (this.x + this.width) > other.x && this.y < (other.y  + other.height) && (this.y +  this.height) > other.y );
    }

    public boolean firstIntersect(){

        return this.firstIntersect;
    }

    public void setFirstIntersect(boolean firstIntersect){

        this.firstIntersect = firstIntersect;
    }

}
