/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9003_mouselabelingonimage;

import java.awt.Point;

/**
 *
 * @author miguel
 */
public class Rectangle {
    private int x;
    private int y;
    private int w;
    private int h;
    private String label;
    
    Rectangle(int x, int y, int w, int h, String label)
    {
        this.x     = x;
        this.y     = y;
        this.w     = w;
        this.h     = h;
        this.label = label;
    }
    
    public boolean isPointInRectangle(Point p)
    {
        boolean bnd = false;
        
        int x = (int)p.getX();
        int y = (int)p.getY();
        
        if(x>this.x & x<this.w & y>this.y & y<this.h)          
        {
            bnd = true;
            System.out.println("point = "+x+","+y);
            System.out.println("rect  = "+this.x+","+this.y+","+this.w+","+this.h);
        }
        
        return bnd;
    }

    @Override
    public String toString()
    {
        return String.valueOf(x)+","+String.valueOf(y)+","+String.valueOf(w)+","+String.valueOf(h);
    }
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the w
     */
    public int getW() {
        return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     * @return the h
     */
    public int getH() {
        return h;
    }

    /**
     * @param h the h to set
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
}
