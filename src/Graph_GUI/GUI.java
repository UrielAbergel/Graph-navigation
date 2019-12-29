package Graph_GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.* ;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;



public class GUI {

    public Graph_Algo AlgoGraph = new Graph_Algo();

    public void MainDraw(){
        StdDraw.thisGui.AlgoGraph = this.AlgoGraph;
        StdDraw.setCanvasSize(700,700);
        StdDraw.setXscale(-600,600);
        StdDraw.setYscale(-600,600);

            Iterator<node_data> iterNodes = this.AlgoGraph.getGraph().getV().iterator();
            while (iterNodes.hasNext()) {
                node_data theCurrent = iterNodes.next();
                StdDraw.picture(theCurrent.getLocation().x(),theCurrent.getLocation().y(),"light.jfif",40,40);
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

    public void update(List<node_data> theList){
        StdDraw.clear();
        StdDraw.thisGui.AlgoGraph = this.AlgoGraph;
        StdDraw.setCanvasSize(700,700);
        StdDraw.setXscale(-600,600);
        StdDraw.setYscale(-600,600);

        Iterator<node_data> iterNodes = this.AlgoGraph.getGraph().getV().iterator();
        while (iterNodes.hasNext()) {
            node_data theCurrent = iterNodes.next();
            StdDraw.picture(theCurrent.getLocation().x(),theCurrent.getLocation().y(),"light.jfif",45,45);
            Point3D tempP = theCurrent.getLocation();
            StdDraw.setPenColor(Color.black);
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
                    StdDraw.setPenColor(Color.BLUE);
                    StdDraw.text((srcP.x()*0.2 +destP.x()*0.8), (srcP.y()*0.2+destP.y()*0.8)+10, "" + tempEdge.getWeight());
                    StdDraw.setPenColor(Color.magenta);
                    StdDraw.filledCircle((srcP.x()*0.1 +destP.x()*0.9), (srcP.y()*0.1 +destP.y()*0.9),5);
                }
            }

        ArrayList<Integer> save = this.MakeListInt(theList);
            for (int i = 0; i < save.size()-1; i++) {
                int src = save.get(i);
                int dest = save.get(i+1);
                StdDraw.setPenColor(Color.GREEN);
                StdDraw.line(this.AlgoGraph.getGraph().getNode(src).getLocation().x(),this.AlgoGraph.getGraph().getNode(src).getLocation().y(),this.AlgoGraph.getGraph().getNode(dest).getLocation().x(),this.AlgoGraph.getGraph().getNode(dest).getLocation().y());
            }

        }
        StdDraw.createMenuBar();
        StdDraw.thisGui.AlgoGraph = this.AlgoGraph;

    }


    public void init(Graph_Algo g){
        this.AlgoGraph = g;
    }
    public static void main(String[] args) {
        Graph_Algo g = new Graph_Algo();
        DGraph DG = new DGraph();
        NodeData n1 = new NodeData(-200,-200,3);
        NodeData n2 = new NodeData(-400   ,300,3);
        NodeData n3 = new NodeData(300,150,3);
        NodeData n4 = new NodeData(380,-300,3);
        NodeData n5 = new NodeData(150,-400,3);
        NodeData n6 = new NodeData(0,-450,3);
        DG.addNode(n1);
        DG.addNode(n2);
        DG.addNode(n3);
        DG.addNode(n4);
        DG.addNode(n5);
        DG.addNode(n6);
        DG.connect(1,2,14);
        DG.connect(2,3,2);
        DG.connect(3,4,3);
        DG.connect(4,5,1);
        DG.connect(5,6,2);
        DG.connect(6,1,3);
        DG.connect(1,3,1);
        DG.connect(3,2,2);
        DG.connect(5,2,3);
        DG.connect(1,3,1);
        DG.connect(3,2,2);
        DG.connect(4,2,10);
        //DG.connect(2,4,10);

        g.init(DG);
        List<Integer> t = new LinkedList<Integer>();
        t.add(4);
        t.add(3);
        t.add(5);
        GUI r = new GUI();
        r.init(g);
        r.MainDraw();
        List<node_data> list = g.TSP(t);

    }
}
