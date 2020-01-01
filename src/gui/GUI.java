package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.* ;
import dataStructure.NodeData;
import dataStructure.node_data;
import utils.Point3D;
import utils.*;



public class GUI {

    public Graph_Algo AlgoGraph = new Graph_Algo();
    public GUI(){
        StdDraw.thisGui = this;
        StdDraw.thisGui.AlgoGraph = this.AlgoGraph;
    }

    public Range returnTheX(){
        graph current = StdDraw.thisGui.AlgoGraph.getGraph();
        double MaxX = Integer.MIN_VALUE;
        double MinX = Integer.MAX_VALUE;
        Iterator<node_data> iter =current.getV().iterator();
        while (iter.hasNext()){
            node_data currentNode = iter.next();
            Point3D p = currentNode.getLocation();
            if(p.x() > MaxX) MaxX =  p.x();
            if(p.x() < MinX) MinX =  p.x();
        }
        Range ans = new Range(MinX , MaxX);
        return ans;
    }

    public Range returnTheY(){
        graph current = StdDraw.thisGui.AlgoGraph.getGraph();
        double MaxY = Integer.MIN_VALUE;
        double MinY = Integer.MAX_VALUE;
        Iterator<node_data> iter =current.getV().iterator();
        while (iter.hasNext()){
            node_data currentNode = iter.next();
            Point3D p = currentNode.getLocation();
            if(p.y() > MaxY) MaxY =  p.y();
            if(p.y() < MinY) MinY =  p.y();
        }
        Range ans = new Range(MinY , MaxY);
        return ans;
    }


    public void MainDraw(){
        Range x = returnTheX();
        Range y = returnTheY();
        StdDraw.setCanvasSize(700,700);
        StdDraw.setXscale(x.get_min()-50,x.get_max()+50);
        StdDraw.setYscale(y.get_min()-50,y.get_max()+50);
        int rightScaleX = (int) ((x.get_max()-x.get_min())/25);
        int rightScaleY = (int) ((y.get_max()-y.get_min())/25);
            Iterator<node_data> iterNodes = this.AlgoGraph.getGraph().getV().iterator();
            while (iterNodes.hasNext()) {
                node_data theCurrent = iterNodes.next();
                StdDraw.picture(theCurrent.getLocation().x(),theCurrent.getLocation().y(),"light.jfif",rightScaleX,rightScaleY);
                Point3D tempP = theCurrent.getLocation();
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(tempP.x(), tempP.y() + 30, "" + theCurrent.getKey());
                if (this.AlgoGraph.getGraph().getE(theCurrent.getKey()) != null) {
                    Iterator<edge_data> iterEdge = this.AlgoGraph.getGraph().getE(theCurrent.getKey()).iterator();
                    while (iterEdge.hasNext()) {
                        edge_data tempEdge = iterEdge.next();
                        node_data src = this.AlgoGraph.getGraph().getNode(tempEdge.getSrc());
                        Point3D srcP = src.getLocation();
                        node_data dest = this.AlgoGraph.getGraph().getNode(tempEdge.getDest());
                        Point3D destP = dest.getLocation();
                        StdDraw.setPenColor(Color.black);
                        StdDraw.setPenRadius(0.003);
                        StdDraw.line(srcP.x(), srcP.y(), destP.x(), destP.y());
                        StdDraw.setPenColor(Color.black);
                        StdDraw.setPenColor(Color.BLUE);
                        StdDraw.text((srcP.x()*0.2 +destP.x()*0.8), (srcP.y()*0.2+destP.y()*0.8)+10, "" + tempEdge.getWeight());
                        StdDraw.setPenColor(Color.magenta);
                        StdDraw.filledCircle((srcP.x()*0.1 +destP.x()*0.9), (srcP.y()*0.1 +destP.y()*0.9),5);
                    }
                }
            }

        StdDraw.createMenuBar();
        StdDraw.thisGui.AlgoGraph = this.AlgoGraph;
    }

    public ArrayList<Integer> MakeListInt(List<node_data> p ){
        ArrayList<Integer> ans = new ArrayList<>();
        Iterator<node_data> iter = p.iterator();
        while (iter.hasNext()){
            ans.add(iter.next().getKey());
        }
        return  ans;
    }

    public void update(List<node_data> p){
        StdDraw.clear();
        Range x = returnTheX();
        Range y = returnTheY();
        StdDraw.setCanvasSize(700,700);
        StdDraw.setXscale(x.get_min()-50,x.get_max()+50);
        StdDraw.setYscale(y.get_min()-50,y.get_max()+50);
        int rightScaleX = (int) ((x.get_max()-x.get_min())/25);
        int rightScaleY = (int) ((y.get_max()-y.get_min())/25);
        Iterator<node_data> iterNodes = StdDraw.thisGui.AlgoGraph.getGraph().getV().iterator();
        while (iterNodes.hasNext()) {
            node_data theCurrent = iterNodes.next();
            StdDraw.picture(theCurrent.getLocation().x(),theCurrent.getLocation().y(),"light.jfif",rightScaleX,rightScaleY);
            Point3D tempP = theCurrent.getLocation();
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(tempP.x(), tempP.y() + 30, "" + theCurrent.getKey());
            if (StdDraw.thisGui.AlgoGraph.getGraph().getE(theCurrent.getKey()) != null) {
                Iterator<edge_data> iterEdge = StdDraw.thisGui.AlgoGraph.getGraph().getE(theCurrent.getKey()).iterator();
                while (iterEdge.hasNext()) {
                    edge_data tempEdge = iterEdge.next();
                    node_data src = StdDraw.thisGui.AlgoGraph.getGraph().getNode(tempEdge.getSrc());
                    Point3D srcP = src.getLocation();
                    node_data dest = StdDraw.thisGui.AlgoGraph.getGraph().getNode(tempEdge.getDest());
                    Point3D destP = dest.getLocation();
                    StdDraw.setPenColor(Color.black);
                    StdDraw.setPenRadius(0.003);
                    StdDraw.line(srcP.x(), srcP.y(), destP.x(), destP.y());
                    StdDraw.setPenColor(Color.black);
                    StdDraw.setPenColor(Color.BLUE);
                    StdDraw.text((srcP.x()*0.2 +destP.x()*0.8), (srcP.y()*0.2+destP.y()*0.8)+10, "" + tempEdge.getWeight());
                    StdDraw.setPenColor(Color.magenta);
                    StdDraw.filledCircle((srcP.x()*0.1 +destP.x()*0.9), (srcP.y()*0.1 +destP.y()*0.9),5);
                }
            }
        }
        ArrayList<Integer> save = this.MakeListInt(p);
        for (int i = 0; i < save.size()-1; i++) {
            int src = save.get(i);
            int dest = save.get(i+1);
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.line(this.AlgoGraph.getGraph().getNode(src).getLocation().x(),this.AlgoGraph.getGraph().getNode(src).getLocation().y(),this.AlgoGraph.getGraph().getNode(dest).getLocation().x(),this.AlgoGraph.getGraph().getNode(dest).getLocation().y());
            StdDraw.picture((this.AlgoGraph.getGraph().getNode(src).getLocation().x()+this.AlgoGraph.getGraph().getNode(dest).getLocation().x())/2 ,(this.AlgoGraph.getGraph().getNode(src).getLocation().y()+this.AlgoGraph.getGraph().getNode(dest).getLocation().y())/2 , "PostmanPat.jpg" , rightScaleX+10,rightScaleY+10);
        }
        StdDraw.createMenuBar();

    }


    public void update(){
        StdDraw.clear();
        Range x = returnTheX();
        Range y = returnTheY();
        StdDraw.setCanvasSize(700,700);
        StdDraw.setXscale(x.get_min()-50,x.get_max()+50);
        StdDraw.setYscale(y.get_min()-50,y.get_max()+50);
        int rightScaleX = (int) ((x.get_max()-x.get_min())/25);
        int rightScaleY = (int) ((y.get_max()-y.get_min())/25);
        Iterator<node_data> iterNodes = StdDraw.thisGui.AlgoGraph.getGraph().getV().iterator();
        while (iterNodes.hasNext()) {
            node_data theCurrent = iterNodes.next();
            StdDraw.picture(theCurrent.getLocation().x(),theCurrent.getLocation().y(),"light.jfif",rightScaleX,rightScaleY);
            Point3D tempP = theCurrent.getLocation();
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(tempP.x(), tempP.y() + 30, "" + theCurrent.getKey());
            if (StdDraw.thisGui.AlgoGraph.getGraph().getE(theCurrent.getKey()) != null) {
                Iterator<edge_data> iterEdge = StdDraw.thisGui.AlgoGraph.getGraph().getE(theCurrent.getKey()).iterator();
                while (iterEdge.hasNext()) {
                    edge_data tempEdge = iterEdge.next();
                    node_data src = StdDraw.thisGui.AlgoGraph.getGraph().getNode(tempEdge.getSrc());
                    Point3D srcP = src.getLocation();
                    node_data dest = StdDraw.thisGui.AlgoGraph.getGraph().getNode(tempEdge.getDest());
                    Point3D destP = dest.getLocation();
                    StdDraw.setPenColor(Color.black);
                    StdDraw.setPenRadius(0.003);
                    StdDraw.line(srcP.x(), srcP.y(), destP.x(), destP.y());
                    StdDraw.setPenColor(Color.black);
                    StdDraw.setPenColor(Color.BLUE);
                    StdDraw.text((srcP.x()*0.2 +destP.x()*0.8), (srcP.y()*0.2+destP.y()*0.8)+10, "" + tempEdge.getWeight());
                    StdDraw.setPenColor(Color.magenta);
                    StdDraw.filledCircle((srcP.x()*0.1 +destP.x()*0.9), (srcP.y()*0.1 +destP.y()*0.9),5);
                }
            }
        }


        StdDraw.createMenuBar();

    }


    public void init(Graph_Algo g){
        this.AlgoGraph = g;
    }
    public static void main(String[] args) {
//        Graph_Algo g = new Graph_Algo();
//        DGraph DG = new DGraph();
//        NodeData n1 = new NodeData(-200,-200,3);
//        NodeData n2 = new NodeData(-400   ,300,3);
//        NodeData n3 = new NodeData(300,150,3);
//        NodeData n4 = new NodeData(380,-300,3);
//        NodeData n5 = new NodeData(150,-400,3);
//        NodeData n6 = new NodeData(0,-450,3);
//        DG.addNode(n1);
//        DG.addNode(n2);
//        DG.addNode(n3);
//        DG.addNode(n4);
//        DG.addNode(n5);
//        DG.addNode(n6);
//        DG.connect(1,2,14);
//        DG.connect(2,3,2);
//        DG.connect(3,4,3);
//        DG.connect(4,5,1);
//        DG.connect(5,6,2);
//        DG.connect(6,1,3);
//        DG.connect(1,3,1);
//        DG.connect(3,2,2);
//        DG.connect(5,2,3);
//        DG.connect(1,3,1);
//        DG.connect(3,2,2);
//        DG.connect(4,2,10);
//        //DG.connect(2,4,10);
//
//       g.init(DG);
//        List<Integer> t = new LinkedList<Integer>();
//        t.add(4);
//        t.add(3);
//        t.add(5);
//        GUI r = new GUI();
//        r.init(g);
//        r.MainDraw();
//       List<node_data> p = g.TSP(t);
//        System.out.println("R");
        //        NodeData n1 = new NodeData(-200,-200,3);
//        NodeData n2 = new NodeData(-400   ,300,3);
//        NodeData n3 = new NodeData(300,150,3);
//        NodeData n4 = new NodeData(380,-300,3);
//        NodeData n5 = new NodeData(150,-400,3);
//        NodeData n6 = new NodeData(0,-450,3);
        Point3D x = new Point3D(-400,300,0);
        Point3D x2 = new Point3D(300,150,0);
        Point3D x3 = new Point3D(380,-300,0);
        Point3D x4 = new Point3D(150,-400,0);
        Point3D x5 = new Point3D(0,-450,0);
        Point3D x6 = new Point3D(200,-300,0);
        Point3D x7 = new Point3D(-400,-150,0);
        Point3D x8 = new Point3D(-400,120,0);
        NodeData a1 = new NodeData(x);
        NodeData a2 = new NodeData(x2);
        NodeData a3 = new NodeData(x3);
        NodeData a4 = new NodeData(x4);
        NodeData a5 = new NodeData(x5);
        NodeData a6 = new NodeData(x6);
        NodeData a7 = new NodeData(x7);
        NodeData a8 = new NodeData(x8);
        DGraph d = new DGraph();
        d.addNode(a1);
        d.addNode(a2);
        d.addNode(a3);
        d.addNode(a4);
        d.addNode(a5);
        d.addNode(a6);
        d.addNode(a7);
        d.addNode(a8);
        d.connect(1,2,5);
        d.connect(1,5,2);
        d.connect(1,3,6);
        d.connect(1,6,5);
        d.connect(3,4,7);
        d.connect(2,8,8);
        d.connect(2,7,3);
        d.connect(5,1,5);
        d.connect(5,6,2);
        d.connect(6,1,3);
        d.connect(6,5,3);
        d.connect(6,7,3);
        d.connect(7,6,3);
        Graph_Algo p = new Graph_Algo();
        p.init(d);
        List<Integer> r = new LinkedList<>();
        r.add(1);
        r.add(6);
        r.add(5);
        List<node_data> ans = p.TSP(r);
        GUI q = new GUI();
        q.init(p);
        q.MainDraw();
        double bbbb = p.shortestPathDist(3,4);
    //    List<node_data> theList = p.TSP(r);
        double eeeee = p.shortestPathDist(1,6);
   //     List<node_data> qqqq =  p.TSP(r);
        System.out.println(p.isConnected());
        System.out.println("r");

    }
}
