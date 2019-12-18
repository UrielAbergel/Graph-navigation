package dataStructure;

import utils.Point3D;

import java.util.HashMap;

public class NodeData implements node_data {
    private int key , tag;
    private Point3D P3D;
    private double weight = Integer.MAX_VALUE;
    private String info;
    private int keyCounter = 1;
    HashMap<Integer,Double> HM ;
    public NodeData(Point3D p){
        this.P3D = p;
        this.key = keyCounter++;
    }
    public NodeData(double x, double y, double z){
        Point3D s = new Point3D(x,y,z);
        this.P3D = s;
        this.key = keyCounter++;
    }
    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public Point3D getLocation() {
        return this.P3D;
    }

    @Override
    public void setLocation(Point3D p) {
     Point3D s = new Point3D(p.x(),p.y(),p.z());
     this.P3D = s ;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
