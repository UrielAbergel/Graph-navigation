package Tests;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.graph;
import dataStructure.node_data;
import org.junit.Test;
import utils.Point3D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class Graph_AlgoTest {

    @Test
    public void init() {
        Graph_Algo g = new Graph_Algo();
        DGraph DG = new DGraph();
        NodeData n1 = new NodeData(1,2,3);
        NodeData n2 = new NodeData(1,2,3);
        NodeData n3 = new NodeData(1,2,3);
        NodeData n4 = new NodeData(1,2,3);
        NodeData n5 = new NodeData(1,2,3);
        NodeData n6 = new NodeData(1,2,3);
        DG.addNode(n1);
        DG.addNode(n2);
        DG.addNode(n3);
        DG.addNode(n4);
        DG.addNode(n5);
        DG.addNode(n6);
        DG.connect(1,2,5);
        DG.connect(1,3,5);
        DG.connect(1,4,5);
        DG.connect(6,2,5);
        DG.connect(6,5,5);
        DG.connect(6,4,5);
        g.init(DG);
        assertEquals(DG,g.getGraph());
    }

    @Test
    public void testInit() {
        Graph_Algo g = new Graph_Algo();
        DGraph DG = new DGraph();
        NodeData n1 = new NodeData(1,2,3);
        NodeData n2 = new NodeData(1,2,3);
        NodeData n3 = new NodeData(1,2,3);
        NodeData n4 = new NodeData(1,2,3);
        NodeData n5 = new NodeData(1,2,3);
        NodeData n6 = new NodeData(1,2,3);
        DG.addNode(n1);
        DG.addNode(n2);
        DG.addNode(n3);
        DG.addNode(n4);
        DG.addNode(n5);
        DG.addNode(n6);
        DG.connect(1,2,5);
        DG.connect(1,3,5);
        DG.connect(1,4,5);
        DG.connect(6,2,5);
        DG.connect(6,5,5);
        DG.connect(6,4,5);
        g.init(DG);
        g.save("TestSave");
        Graph_Algo g2 = new Graph_Algo();
        g2.init("TestSave");
        Boolean flag = g.getGraph().getEdge(1,2).getWeight() == g2.getGraph().getEdge(1,2).getWeight();
        Boolean flag2 = g.isConnected() == g2.isConnected();
        assertEquals(true,flag);
        assertEquals(true,flag2);

    }

    @Test
    public void save() {
        Graph_Algo g = new Graph_Algo();
        DGraph DG = new DGraph();
        NodeData n1 = new NodeData(100,200,3);
        NodeData n2 = new NodeData(150,200,3);
        NodeData n3 = new NodeData(300,450,3);
        NodeData n4 = new NodeData(450,500,3);
        NodeData n5 = new NodeData(320,600,3);
        NodeData n6 = new NodeData(226,260,3);
        DG.addNode(n1);
        DG.addNode(n2);
        DG.addNode(n3);
        DG.addNode(n4);
        DG.addNode(n5);
        DG.addNode(n6);
        DG.connect(1,2,5);
        DG.connect(1,3,5);
        DG.connect(1,4,5);
        DG.connect(6,2,5);
        DG.connect(6,5,5);
        DG.connect(6,4,5);
        g.init(DG);
        g.save("TestSave");
        Graph_Algo g2 = new Graph_Algo();
        g2.init("TestSave");
        Boolean flag = g.getGraph().getEdge(1,2).getWeight() == g2.getGraph().getEdge(1,2).getWeight();
        Boolean flag2 = g.isConnected() == g2.isConnected();
        assertEquals(true,flag);
        assertEquals(true,flag2);
    }

    @Test
    public void isConnected() {
        Graph_Algo g = new Graph_Algo();
        DGraph DG = new DGraph();
        NodeData n1 = new NodeData(100,200,3);
        NodeData n2 = new NodeData(150,200,3);
        NodeData n3 = new NodeData(300,450,3);
        NodeData n4 = new NodeData(450,500,3);
        NodeData n5 = new NodeData(320,600,3);
        NodeData n6 = new NodeData(226,260,3);
        DG.addNode(n1);
        DG.addNode(n2);
        DG.addNode(n3);
        DG.addNode(n4);
        DG.addNode(n5);
        DG.addNode(n6);
        DG.connect(1,2,5);
        DG.connect(2,3,5);
        DG.connect(3,4,5);
        DG.connect(4,5,5);
        DG.connect(5,6,5);
        DG.connect(6,1,5);
        g.init(DG);
        assertEquals(true,g.isConnected());
        Graph_Algo g2 = new Graph_Algo();
        DGraph DG2 = new DGraph();
        NodeData n21 = new NodeData(100,200,3);
        NodeData n22 = new NodeData(150,200,3);
        NodeData n23 = new NodeData(300,450,3);
        NodeData n24 = new NodeData(450,500,3);
        NodeData n25 = new NodeData(320,600,3);
        NodeData n26 = new NodeData(226,260,3);
        DG.addNode(n21);
        DG.addNode(n22);
        DG.addNode(n23);
        DG.addNode(n24);
        DG.addNode(n25);
        DG.addNode(n26);
        DG.connect(1,2,5);
        DG.connect(2,3,5);
        DG.connect(3,4,5);
        DG.connect(4,5,5);
        DG.connect(5,6,5);
        DG.connect(6,2,5);
        g.init(DG);
        assertEquals(false,g.isConnected());
        Graph_Algo g3 = new Graph_Algo();
        DGraph DG3 = new DGraph();
        NodeData n31 = new NodeData(100,200,3);
        NodeData n32 = new NodeData(150,200,3);
        DG3.addNode(n31);
        DG3.addNode(n32);
        DG3.connect(1,2,4);
        DG3.connect(2,1,4);
        g3.init(DG3);
        assertEquals(true,g3.isConnected());

    }
//------------------- NEED TO LOOKOUT AT CONNECT!! DO NOT CHANGE ID THERE IS CONNECTION!
    @Test
    public void shortestPathDist() {
        Graph_Algo g = new Graph_Algo();
        DGraph DG = new DGraph();
        NodeData n1 = new NodeData(100,200,3);
        NodeData n2 = new NodeData(150,200,3);
        NodeData n3 = new NodeData(300,450,3);
        NodeData n4 = new NodeData(450,500,3);
        NodeData n5 = new NodeData(320,600,3);
        NodeData n6 = new NodeData(226,260,3);
        DG.addNode(n1);
        DG.addNode(n2);
        DG.addNode(n3);
        DG.addNode(n4);
        DG.addNode(n5);
        DG.addNode(n6);
        DG.connect(1,2,5);
        DG.connect(2,3,5);
        DG.connect(3,4,5);
        DG.connect(4,5,5);
        DG.connect(5,6,5);
        DG.connect(6,1,5);
        g.init(DG);
        boolean flag = 25 == g.shortestPathDist(1,6);
        assertEquals(true,flag);
        DG.connect(1,2,1);
        DG.connect(2,3,2);
        DG.connect(3,4,3);
        DG.connect(4,5,4);
        DG.connect(5,6,5);
        DG.connect(1,6,10);
        g.init(DG);
        flag = 10 == g.shortestPathDist(1,6);
        DG.reset();
        assertEquals(true,flag);
        NodeData b1 = new NodeData(100,200,3);
        NodeData b2 = new NodeData(150,200,3);
        NodeData b3 = new NodeData(300,450,3);
        NodeData b4 = new NodeData(450,500,3);
        NodeData b5 = new NodeData(320,600,3);
        NodeData b6 = new NodeData(226,260,3);
        DG.addNode(b1);
        DG.addNode(b2);
        DG.addNode(b3);
        DG.addNode(b4);
        DG.addNode(b5);
        DG.addNode(b6);
        DG.connect(1,2,1);
        DG.connect(2,3,2);
        DG.connect(3,4,3);
        DG.connect(4,5,4);
        DG.connect(5,6,5);
        DG.connect(1,6,16);
        g.init(DG);
        flag = 15 == g.shortestPathDist(1,6);
        assertEquals(true,flag);
    }

    @Test
    public void shortestPath() {
        Point3D x = new Point3D(14,4,0);
        Point3D x2 = new Point3D(-75,14,0);
        Point3D x3 = new Point3D(80,5,0);
        Point3D x4 = new Point3D(1,4,0);
        Point3D x5 = new Point3D(-5,1,0);
        Point3D x6 = new Point3D(8,3,0);
        Point3D x7 = new Point3D(4,1,0);
        Point3D x8 = new Point3D(75,14,0);
        node_data a1 = new NodeData(x);
        node_data a2 = new NodeData(x2);
        node_data a3 = new NodeData(x3);
        node_data a4 = new NodeData(x4);
        node_data a5 = new NodeData(x5);
        node_data a6 = new NodeData(x6);
        node_data a7 = new NodeData(x7);
        node_data a8 = new NodeData(x8);
        DGraph d = new DGraph();
        d.addNode(a1);
        d.addNode(a2);
        d.addNode(a3);
        d.addNode(a4);
        d.addNode(a5);
        d.addNode(a6);
        d.addNode(a7);
        d.addNode(a8);
        d.connect(a1.getKey(),a2.getKey(),5);
        d.connect(a1.getKey(),a5.getKey(),2);
        d.connect(a1.getKey(),a3.getKey(),6);
        d.connect(a3.getKey(),a4.getKey(),7);
        d.connect(a2.getKey(),a8.getKey(),8);
        d.connect(a2.getKey(),a7.getKey(),3);
        d.connect(a5.getKey(),a6.getKey(),2);
        d.connect(a6.getKey(),a7.getKey(),3);
        d.connect(a7.getKey(),a6.getKey(),3);
        Graph_Algo p = new Graph_Algo();
        p.init(d);
        List<node_data> ans = new LinkedList<node_data>();
        ans = p.shortestPath(1,7);
        List<node_data> test = new LinkedList<node_data>();
        test.add(a1);
        test.add(a5);
        test.add(a6);
        test.add(a7);
        assertEquals(test.get(0).getKey(),ans.get(0).getKey());
        assertEquals(test.get(1).getKey(),ans.get(1).getKey());
        assertEquals(test.get(2).getKey(),ans.get(2).getKey());
        assertEquals(test.get(3).getKey(),ans.get(3).getKey());
    }

    @Test
    public void TSP() {

    }

    @Test
    public void copy() {Point3D x = new Point3D(14,4,0);
        Point3D x2 = new Point3D(-75,14,0);
        Point3D x3 = new Point3D(80,5,0);
        Point3D x4 = new Point3D(1,4,0);
        Point3D x5 = new Point3D(-5,1,0);
        Point3D x6 = new Point3D(8,3,0);
        Point3D x7 = new Point3D(4,1,0);
        Point3D x8 = new Point3D(75,14,0);
        node_data a1 = new NodeData(x);
        node_data a2 = new NodeData(x2);
        node_data a3 = new NodeData(x3);
        node_data a4 = new NodeData(x4);
        node_data a5 = new NodeData(x5);
        node_data a6 = new NodeData(x6);
        node_data a7 = new NodeData(x7);
        node_data a8 = new NodeData(x8);
        DGraph d = new DGraph();
        d.addNode(a1);
        d.addNode(a2);
        d.addNode(a3);
        d.addNode(a4);
        d.addNode(a5);
        d.addNode(a6);
        d.addNode(a7);
        d.addNode(a8);
        d.connect(a1.getKey(),a2.getKey(),5);
        d.connect(a1.getKey(),a5.getKey(),2);
        d.connect(a1.getKey(),a3.getKey(),6);
        d.connect(a3.getKey(),a4.getKey(),7);
        d.connect(a2.getKey(),a8.getKey(),8);
        d.connect(a2.getKey(),a7.getKey(),3);
        d.connect(a5.getKey(),a6.getKey(),2);
        d.connect(a6.getKey(),a7.getKey(),3);
        d.connect(a7.getKey(),a6.getKey(),3);
        Graph_Algo p = new Graph_Algo();
        p.init(d);
        graph g = new DGraph();
        g = p.copy();
        assertNotEquals(g,p.getGraph());
    }
}