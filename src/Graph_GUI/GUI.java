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

import algorithms.Graph_Algo;
import dataStructure.* ;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;



public class GUI {
    ArrayList<graph> theArrays = new ArrayList<>();
    GUI gui;

    public GUI(){
        StdDraw.setCanvasSize(700,700);
        StdDraw.setXscale(-600,600);
        StdDraw.setYscale(-600,600);
    }
    public void MainDraw(){
        StdDraw.setCanvasSize(700,700);
        StdDraw.setXscale(-600,600);
        StdDraw.setYscale(-600,600);
        for (int i = 0; i < this.theArrays.size(); i++) {
            Iterator<node_data> iterNodes = this.theArrays.get(i).getV().iterator();
            while (iterNodes.hasNext()) {
                node_data theCurrent = iterNodes.next();
                Point3D tempP = theCurrent.getLocation();
                StdDraw.setPenColor(Color.black);
                StdDraw.filledCircle(tempP.x(), tempP.y(), 9);
                StdDraw.text(tempP.x(), tempP.y() + 20, "" + theCurrent.getKey());
                if (this.theArrays.get(i).getE(theCurrent.getKey()) != null) {
                    Iterator<edge_data> iterEdge = this.theArrays.get(i).getE(theCurrent.getKey()).iterator();
                    while (iterEdge.hasNext()) {
                        edge_data tempEdge = iterEdge.next();
                        node_data src = this.theArrays.get(i).getNode(tempEdge.getSrc());
                        Point3D srcP = src.getLocation();
                        node_data dest = this.theArrays.get(i).getNode(tempEdge.getDest());
                        Point3D destP = dest.getLocation();
                        StdDraw.setPenColor(Color.red);
                        StdDraw.setPenRadius(0.007);
                        StdDraw.line(srcP.x(), srcP.y(), destP.x(), destP.y());
                        StdDraw.setPenColor(Color.black);
                        StdDraw.filledCircle(srcP.x(), srcP.y(), 9);
                        StdDraw.text(srcP.x(), srcP.y() + 20, "" + src.getKey());
                        StdDraw.filledCircle(destP.x(), destP.y(), 9);
                        StdDraw.text(destP.x(), destP.y() + 20, "" + dest.getKey());
                        StdDraw.setPenColor(Color.BLUE);
                        StdDraw.text((srcP.x()*0.2 +destP.x()*0.8), (srcP.y()*0.2+destP.y()*0.8)+10, "" + tempEdge.getWeight());
                        StdDraw.setPenColor(Color.GREEN);
                        StdDraw.filledCircle((srcP.x()*0.1 +destP.x()*0.9), (srcP.y()*0.1 +destP.y()*0.9),5);
                    }
                }
            }
        }
        StdDraw.createMenuBar();
    }



    public static void main(String[] args) {
        Graph_Algo g = new Graph_Algo();
        DGraph DG = new DGraph();
        NodeData n1 = new NodeData(-200,-200,3);
        NodeData n2 = new NodeData(150,-200,3);
        NodeData n3 = new NodeData(-300,40,3);
        NodeData n4 = new NodeData(450,-400,3);
        NodeData n5 = new NodeData(320,500,3);
        NodeData n6 = new NodeData(226,260,3);
        DG.addNode(n1);
        DG.addNode(n2);
        DG.addNode(n3);
        DG.addNode(n4);
        DG.addNode(n5);
        DG.addNode(n6);
        DG.connect(1,2,1);
        DG.connect(1,3,2);
        DG.connect(1,4,3);
        DG.connect(6,2,4);
        DG.connect(6,5,5);
        DG.connect(6,4,6);
        g.init(DG);

        GUI r = new GUI();

        r.theArrays.add(DG);
        r.MainDraw();
    }
}
