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

import dataStructure.* ;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;



public class GUI {
    ArrayList<graph> theArrays = new ArrayList<>();

    public void MainDraw(){
        StdDraw.setCanvasSize(800,800);
        StdDraw.setXscale(-600,600);
        StdDraw.setYscale(-600,600);
        for (int i = 0; i < this.theArrays.size(); i++) {
            Iterator<node_data> iterNodes = this.theArrays.get(i).getV().iterator();
            while (iterNodes.hasNext()){
                node_data theCurrent = iterNodes.next();
                Point3D tempP = theCurrent.getLocation();
                StdDraw.setPenColor(Color.black);
                StdDraw.filledCircle(tempP.x(),tempP.y(),9);
                Iterator<edge_data> iterEdge = this.theArrays.get(i).getE(theCurrent.getKey()).iterator();
                while (iterEdge.hasNext()){
                    edge_data tempEdge = iterEdge.next();
                    node_data src = this.theArrays.get(i).getNode(tempEdge.getSrc());
                    Point3D srcP = src.getLocation();
                    node_data dest = this.theArrays.get(i).getNode(tempEdge.getDest());
                    Point3D destP  = dest.getLocation();
                    StdDraw.setPenColor(Color.red);
                    StdDraw.setPenRadius(0.007);
                    StdDraw.line(srcP.x(),srcP.y(),destP.x(),destP.y());
                    StdDraw.setPenColor(Color.black);
                    StdDraw.filledCircle(srcP.x(), srcP.y(),9);
                    StdDraw.filledCircle(destP.x(), destP.y(),9);
                }
            }
        }
    }



    public static void main(String[] args) {
        DGraph q = new DGraph();
        NodeData p1 = new NodeData(100,200,4);
        NodeData p2 = new NodeData(300,400,5);
        NodeData p3 = new NodeData(150,300,7);
        NodeData p4 = new NodeData(200,450,4);
        q.addNode(p1);
        q.addNode(p2);
        q.addNode(p3);
        q.addNode(p4);
        q.connect(1,2,60);
        q.connect(1,3,50);
        q.connect(1,4,40);
        q.connect(4,3,30);

        GUI r = new GUI();
        r.theArrays.add(q);
        r.MainDraw();




    }
}
