package Tests;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import org.junit.Test;

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
        assertEquals(true,flag);
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
    }

    @Test
    public void TSP() {
    }

    @Test
    public void copy() {
    }
}