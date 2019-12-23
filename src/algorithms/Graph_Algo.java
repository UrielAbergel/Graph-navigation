package algorithms;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import dataStructure.*;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	private graph graph = new DGraph();

	@Override
	public void init(graph g) {
		this.graph =  g;
	}

	@Override
	public void init(String file_name) {
		try
		{
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);

			graph = (DGraph)in.readObject();

			in.close();
			file.close();

			System.out.println("Object has been deserialized");
		}
		catch(IOException ex)
		{
			System.out.println("IOException is caught");
		}

		catch(ClassNotFoundException ex)
		{
			System.out.println("ClassNotFoundException is caught");
		}

	}

	@Override
	public void save(String file_name) {
		try{

			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(this.graph);
			out.close();
			file.close();

			System.out.println("Object has been serialized");
		}
		catch(IOException ex)
		{
			System.out.println("IOException is caught");
		}

	}

	@Override
	public boolean isConnected() {
		Iterator iter = graph.getV().iterator();
		while(iter.hasNext()){
			node_data thenewOne = (node_data)iter.next();
			isConnectedRec(thenewOne);
			Iterator <node_data> iter2 = graph.getV().iterator();
			while(iter2.hasNext()){
				node_data corrent = iter2.next();
				if(corrent.getTag()!=1) return false;
				corrent.setTag(0);
			}
		}
		return true;
	}

	private void changeDirectory(Collection<edge_data> e, graph tempGraph) {
		Iterator <edge_data> iter4 = e.iterator();
		while(iter4.hasNext()){
			edge_data ED = iter4.next();
			tempGraph.connect(ED.getDest(),ED.getSrc(),ED.getWeight());
			tempGraph.removeEdge(ED.getSrc(),ED.getDest());
		}
	}

	private void isConnectedRec(node_data thenewOne) {
		thenewOne = (NodeData)thenewOne;
		if(thenewOne.getTag()==1) return;
		thenewOne.setTag(1);
		for(Integer key : ((NodeData) thenewOne).HM.keySet()) {
			int dest = ((NodeData) thenewOne).HM.get(key).getDest();
			isConnectedRec(this.graph.getNode(dest));
		}
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}
//------------------------ NEED TO FIX!! NOT DOING TO DEEP COPY TO NodeData
	@Override
	public graph copy() {
		graph p = new DGraph();
		Iterator <node_data> iter = this.graph.getV().iterator();
		Iterator <node_data> iter2 = this.graph.getV().iterator();
		while(iter.hasNext()){
			NodeData theNewOne = (NodeData) iter.next();
			node_data theCopy = theNewOne.copy();
			p.addNode(theCopy);
		}
		while(iter2.hasNext()){
			node_data theNewOne = iter2.next();
			Iterator <edge_data> iterE = this.graph.getE(theNewOne.getKey()).iterator();
			while(iterE.hasNext()){
				edge_data theNewEdge = iterE.next();
				p.connect(theNewEdge.getSrc(),theNewEdge.getDest(),theNewEdge.getWeight());
				p.getEdge(theNewEdge.getSrc(),theNewEdge.getDest()).setTag(theNewEdge.getTag());
			}
		}

		return p;
	}

	public static void main(String[] args) {
		DGraph p = new DGraph();
		NodeData test1 = new NodeData(1, 2, 3);
		NodeData test2 = new NodeData(1, 2, 3);
		NodeData test3 = new NodeData(1, 2, 3);
		p.addNode(test1);
		p.addNode(test2);
		p.addNode(test3);
		p.connect(1, 2, 10);
		p.connect(2, 3, 10);
		p.connect(3, 1, 10);
		Graph_Algo e = new Graph_Algo();
		e.init(p);
		e.save("tt");
		Graph_Algo q = new Graph_Algo();
		q.graph = e.copy();
		//q.init("tt");
		System.out.println();
		System.out.println(e.isConnected());
	}
}
