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
        double FixXscale = (x.get_max()-x.get_min())*0.2;
        double FixYscale = (y.get_max()-y.get_min())*0.2;
        StdDraw.setXscale(x.get_min()-FixXscale,x.get_max()*1.1);
        StdDraw.setYscale(y.get_min()-FixYscale,y.get_max()*1.15);
        double TheYUp = (y.get_max()-y.get_min())*0.03;
        double rightScaleX = ((x.get_max()-x.get_min())*0.04);
        double rightScaleY =  ((y.get_max()-y.get_min())*0.04);
            Iterator<node_data> iterNodes = this.AlgoGraph.getGraph().getV().iterator();
            while (iterNodes.hasNext()) {
                node_data theCurrent = iterNodes.next();
                StdDraw.picture(theCurrent.getLocation().x(),theCurrent.getLocation().y(),"light.jfif",rightScaleX,rightScaleY);
                Point3D tempP = theCurrent.getLocation();
                StdDraw.setPenColor(Color.black);
                StdDraw.text(tempP.x(), tempP.y()+TheYUp, "" + theCurrent.getKey());
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
                        StdDraw.text((srcP.x()*0.2 +destP.x()*0.8), (srcP.y()*0.2+destP.y()*0.8)+TheYUp, "" + tempEdge.getWeight());
                        StdDraw.setPenColor(Color.RED);
                        StdDraw.filledCircle((srcP.x()*0.1 +destP.x()*0.9), (srcP.y()*0.1 +destP.y()*0.9),rightScaleX*0.25);
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
        double FixXscale = (x.get_max()-x.get_min())*0.2;
        double FixYscale = (y.get_max()-y.get_min())*0.2;
        StdDraw.setXscale(x.get_min()-FixXscale,x.get_max()*1.1);
        StdDraw.setYscale(y.get_min()-FixYscale,y.get_max()*1.15);
        double TheYUp = (y.get_max()-y.get_min())*0.03;
        double rightScaleX = ((x.get_max()-x.get_min())*0.04);
        double rightScaleY =  ((y.get_max()-y.get_min())*0.04);
        Iterator<node_data> iterNodes = this.AlgoGraph.getGraph().getV().iterator();
        while (iterNodes.hasNext()) {
            node_data theCurrent = iterNodes.next();
            StdDraw.picture(theCurrent.getLocation().x(),theCurrent.getLocation().y(),"light.jfif",rightScaleX,rightScaleY);
            Point3D tempP = theCurrent.getLocation();
            StdDraw.setPenColor(Color.black);
            StdDraw.text(tempP.x(), tempP.y()+TheYUp, "" + theCurrent.getKey());
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
                    StdDraw.text((srcP.x()*0.2 +destP.x()*0.8), (srcP.y()*0.2+destP.y()*0.8)+TheYUp, "" + tempEdge.getWeight());
                        StdDraw.setPenColor(Color.magenta);
                        StdDraw.filledCircle((srcP.x()*0.1 +destP.x()*0.9), (srcP.y()*0.1 +destP.y()*0.9),rightScaleX*0.25);
                }
            }
        }
        ArrayList<Integer> save = this.MakeListInt(p);
        for (int i = 0; i < save.size()-1; i++) {
            int src = save.get(i);
            int dest = save.get(i+1);
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.line(this.AlgoGraph.getGraph().getNode(src).getLocation().x(),this.AlgoGraph.getGraph().getNode(src).getLocation().y(),this.AlgoGraph.getGraph().getNode(dest).getLocation().x(),this.AlgoGraph.getGraph().getNode(dest).getLocation().y());
            StdDraw.picture((this.AlgoGraph.getGraph().getNode(src).getLocation().x()+this.AlgoGraph.getGraph().getNode(dest).getLocation().x())/2 ,(this.AlgoGraph.getGraph().getNode(src).getLocation().y()+this.AlgoGraph.getGraph().getNode(dest).getLocation().y())/2 , "PostmanPat.jpg" , rightScaleX,rightScaleY);
        }
        StdDraw.createMenuBar();

    }


    public void update(){
        StdDraw.clear();
        Range x = returnTheX();
        Range y = returnTheY();
        StdDraw.setCanvasSize(700,700);
        double FixXscale = (x.get_max()-x.get_min())*0.2;
        double FixYscale = (y.get_max()-y.get_min())*0.2;
        StdDraw.setXscale(x.get_min()-FixXscale,x.get_max()*1.1);
        StdDraw.setYscale(y.get_min()-FixYscale,y.get_max()*1.15);
        double TheYUp = (y.get_max()-y.get_min())*0.03;
        double rightScaleX = ((x.get_max()-x.get_min())*0.04);
        double rightScaleY =  ((y.get_max()-y.get_min())*0.04);
        Iterator<node_data> iterNodes = this.AlgoGraph.getGraph().getV().iterator();
        while (iterNodes.hasNext()) {
            node_data theCurrent = iterNodes.next();
            StdDraw.picture(theCurrent.getLocation().x(),theCurrent.getLocation().y(),"light.jfif",rightScaleX,rightScaleY);
            Point3D tempP = theCurrent.getLocation();
            StdDraw.setPenColor(Color.black);
            StdDraw.text(tempP.x(), tempP.y()+TheYUp, "" + theCurrent.getKey());
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
                    StdDraw.text((srcP.x()*0.2 +destP.x()*0.8), (srcP.y()*0.2+destP.y()*0.8)+TheYUp, "" + tempEdge.getWeight());
                        StdDraw.setPenColor(Color.magenta);
                        StdDraw.filledCircle((srcP.x()*0.1 +destP.x()*0.9), (srcP.y()*0.1 +destP.y()*0.9),rightScaleX*0.25);
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
//        Point3D x = new Point3D(-400,300,0);
//        Point3D x2 = new Point3D(300,150,0);
//        Point3D x3 = new Point3D(380,-300,0);
//        Point3D x4 = new Point3D(150,-400,0);
//        Point3D x5 = new Point3D(0,-450,0);
//        Point3D x6 = new Point3D(200,-300,0);
//        Point3D x7 = new Point3D(-400,-150,0);
//        Point3D x8 = new Point3D(-400,120,0);
//        NodeData a1 = new NodeData(x);
//        NodeData a2 = new NodeData(x2);
//        NodeData a3 = new NodeData(x3);
//        NodeData a4 = new NodeData(x4);
//        NodeData a5 = new NodeData(x5);
//        NodeData a6 = new NodeData(x6);
//        NodeData a7 = new NodeData(x7);
//        NodeData a8 = new NodeData(x8);
//        DGraph d = new DGraph();
//        d.addNode(a1);
//        d.addNode(a2);
//        d.addNode(a3);
//        d.addNode(a4);
//        d.addNode(a5);
//        d.addNode(a6);
//        d.addNode(a7);
//        d.addNode(a8);
//        d.connect(1,2,5);
//        d.connect(1,5,2);
//        d.connect(1,3,6);
//        d.connect(1,6,5);
//        d.connect(3,4,7);
//        d.connect(2,8,8);
//        d.connect(2,7,3);
//        d.connect(5,1,5);
//        d.connect(5,6,2);
//        d.connect(6,1,3);
//        d.connect(6,5,3);
//        d.connect(6,7,3);
//        d.connect(7,6,3);
//        Graph_Algo p = new Graph_Algo();
//        p.init(d);
//        List<Integer> r = new LinkedList<>();
//        r.add(1);
//        r.add(6);
//        r.add(5);
//        List<node_data> ans = p.TSP(r);
//        GUI q = new GUI();
//        q.init(p);
//        q.MainDraw();
//        double bbbb = p.shortestPathDist(3,4);
//    //    List<node_data> theList = p.TSP(r);
//        double eeeee = p.shortestPathDist(1,6);
//   //     List<node_data> qqqq =  p.TSP(r);
//        System.out.println(p.isConnected());
//        System.out.println("r");


        Graph_Algo G = new Graph_Algo();
        Point3D p00 = new Point3D(1, 6, 0);
        Point3D p11 = new Point3D(0, 2, 3);
        Point3D p22 = new Point3D(1, 4, 0);
        Point3D p33 = new Point3D(5, 2, 0);
        Point3D p44 = new Point3D(6,5, 0);
        Point3D p55 = new Point3D(4,6, 0);
        Point3D p66 = new Point3D(3,5, 0);
        Point3D p77 = new Point3D(4,10,0);
        Point3D p88 = new Point3D(4.10,0);
        Point3D p99 = new Point3D(1,30);
        Point3D p10 = new Point3D(10,40);
        node_data node1 = new NodeData(p00);
        node_data node2 = new NodeData(p11);
        node_data node3 = new NodeData(p22);
        node_data node4 = new NodeData(p33);
        node_data node5 = new NodeData(p44);
        node_data node6 = new NodeData(p55);
        node_data node7 = new NodeData(p66);
        node_data node8 = new NodeData(p77);
        node_data node9 =new NodeData(p88);
        node_data node10 = new NodeData(p99);
        node_data node11 = new NodeData(p10);

        DGraph Dg = new DGraph();
        Dg.addNode(node1);
        Dg.addNode(node2);
        Dg.addNode(node3);
        Dg.addNode(node4);
        Dg.addNode(node5);
        Dg.addNode(node6);
        Dg.addNode(node7);
        Dg.addNode(node8);
        Dg.addNode(node9);
        Dg.addNode(node10);
        Dg.addNode(node11);


        Dg.connect(node1.getKey(), node2.getKey(), 5);
        Dg.connect(node1.getKey(), node3.getKey(), 3);
        Dg.connect(node1.getKey(), node4.getKey(), 2);
        Dg.connect(node2.getKey(), node5.getKey(), 2);
        Dg.connect(node3.getKey(), node6.getKey(), 4);
        Dg.connect(node3.getKey(),node1.getKey(),2);
        Dg.connect(node4.getKey(), node6.getKey(), 4);
        Dg.connect(node4.getKey(), node7.getKey(), 2);
        Dg.connect(node5.getKey(), node8.getKey(), 6);
        Dg.connect(node5.getKey(), node7.getKey(), 1);
        Dg.connect(node5.getKey(),node2.getKey(),4);
        Dg.connect(node6.getKey(),node11.getKey(),3);
        Dg.connect(node7.getKey(),node8.getKey(),4);
        Dg.connect(node7.getKey(),node6.getKey(),1);
        Dg.connect(node7.getKey(),node11.getKey(),9);
        Dg.connect(node8.getKey(),node7.getKey(),1);
        Dg.connect(node8.getKey(),node9.getKey(),9);
        Dg.connect(node9.getKey(),node8.getKey(),3);
        Dg.connect(node9.getKey(),node10.getKey(),5);
        Dg.connect(node10.getKey(),node9.getKey(),2);
        Dg.connect(node10.getKey(),node11.getKey(),1);
        Dg.connect(node11.getKey(),node10.getKey(),2);

        G.init(Dg);
        System.out.println("Distance betwenn 1-6 is :" + G.shortestPathDist(node1.getKey(),node6.getKey()));
        System.out.println("Distance between 6-7 is : " + G.shortestPathDist(node6.getKey(),node7.getKey()));
        System.out.println("Distance between 4-1 is : " + G.shortestPathDist(node4.getKey(),node1.getKey()));
        System.out.println("Distance between 7-9 is : " + G.shortestPathDist(node7.getKey(),node9.getKey()));
        System.out.println("Distance between 3-2 is : " + G.shortestPathDist(node3.getKey(),node2.getKey()));

        System.out.println("The graph is Connected :" + G.isConnected());
        System.out.println("The shortest path between 5-10 is :" + G.shortestPath(node5.getKey(),node10.getKey()));
        GUI r = new GUI();
        r.init(G);
        r.MainDraw();
        G.shortestPath(node10.getKey(),node1.getKey());
        System.out.println("The shortest path between 10-1 is :" + G.shortestPath(node10.getKey(),node1.getKey()));
        System.out.println("The shortest path between 7-2 is :" + G.shortestPath(node7.getKey(),node2.getKey()));
        System.out.println("The shortest path between 1-9 is :" + G.shortestPath(node1.getKey(),node9.getKey()));

        List<Integer> ans = new LinkedList<>();
        ans.add(1);
        ans.add(7);
        ans.add(3);
        ans.add(10);
        List<Integer> ans2 = new LinkedList<>();
        ans2.add(1);
        ans2.add(10);
        ans2.add(4);
        ans2.add(5);
        System.out.println("TSP[1,7,3,10] is: " +  G.TSP(ans));
        System.out.println("TSP[10,1,4,5] is : " + G.TSP(ans2));
//        GUI r = new GUI();
//        r.init(G);
//        r.MainDraw();
        System.out.println("r");
    }
}
