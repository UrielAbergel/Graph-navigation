package dataStructure;

import javax.xml.soap.Node;

public class EdgeData implements edge_data {
    NodeData src , dest;
    private double weight ;
    int Tag ;


    public EdgeData(){
        src = dest = null;
        weight = 0 ;
        Tag = 0 ;
    }

    public EdgeData(NodeData src , NodeData dest , int wei , int tag){
        this.src = src ;
        this.dest = dest ;
        this.weight = wei;
        this.Tag = tag;
    }

    public EdgeData(NodeData src , NodeData dest ){
        this.src = src ;
        this.dest = dest ;
        this.weight = 0;
        this.Tag = 0;
    }



    @Override
    public int getSrc()
    {
        return this.src.getKey();
    }

    @Override
    public int getDest() {
        return this.dest.getKey();
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
}
