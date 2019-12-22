package dataStructure;

import utils.Point3D;

import java.io.Serializable;
import java.util.HashMap;

public class NodeData implements node_data, Serializable {
    private int key , tag;
    public Point3D P3D;
    private double weight = Integer.MAX_VALUE;
    private static int keyCounter = 1;
    public HashMap<Integer,EdgeData> HM = new HashMap<Integer, EdgeData>();
    private String info;

    public edge_data getDestEdge(int dest)
    {
      return HM.get(dest);
    }
    public edge_data removeEdge(int dest){
        return this.HM.remove(dest);
    }
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
     //if(DGraph.GraphMap.containsKey(this.getKey())) DGraph.MC++;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
       // if(DGraph.GraphMap.containsKey(this.getKey())) DGraph.MC++;
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    public static void main(String[] args) {
        NodeData nd = new NodeData(5,5,5);
        NodeData nd1 = new NodeData(4,4,4);
        NodeData nd2 = new NodeData(3,3,3);
        NodeData nd3 = new NodeData(2,2,2);
//        EdgeData r = new EdgeData(nd ,nd1 ,5 , 0);
//        EdgeData r1 = new EdgeData(nd ,nd2 ,7 , 0);
//        EdgeData r2 = new EdgeData(nd ,nd3 ,6 , 0);

//        nd.HM.put(nd1.getKey(), r);
//        nd.HM.put(3, r2);
//        nd.HM.put(5, r1);
//        nd.HM.forEach((k,v) -> System.out.println("the Key :" + k + "The Value: " + v));
//        for(Integer key : nd.HM.keySet())
//        {
//            System.out.println(key);
//            System.out.println(nd.HM.get(key));
//        }

    }
}
