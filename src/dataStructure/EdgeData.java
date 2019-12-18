package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class EdgeData implements edge_data {
    int src , dest;
    private double weight;
    int Tag ;
    public EdgeData(int src , int dest , double weight , int tag){
        this.src = src ;
        this.dest = dest ;
        this.weight = weight;
        this.Tag = tag;
        DGraph.edgeHM.put(StringToHash(src,dest), this);
    }
    public EdgeData(int src , int dest , double weight){
        this.src = src ;
        this.dest = dest ;
        this.weight = weight;
        this.Tag = 0;

        DGraph.edgeHM.put(StringToHash(src,dest), this);;
    }
    @Override
    public int getSrc()
    {
        return src;
    }

    @Override
    public int getDest() {
        return dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag()
    {
        return this.Tag;
    }

    @Override
    public void setTag(int t)
    {
    this.Tag = t ;
    }
    public static String StringToHash(int src,int dest){
        return ""+src+","+dest;
    }
}
