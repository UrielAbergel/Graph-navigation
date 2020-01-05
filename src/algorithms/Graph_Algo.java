package algorithms;

import java.io.*;
import java.util.*;

import dataStructure.*;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

import javax.swing.*;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms{
	private graph graph = new DGraph();

	public Graph_Algo(graph graph) {
		this.init(graph);
	}
	public Graph_Algo() {

	}
	//public Graph_Algo _G_ = new Graph_Algo();

	public graph getGraph(){
		return this.graph;
	}

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
			out.writeObject(this.getGraph());
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
		Iterator iter = this.getGraph().getV().iterator();
		while(iter.hasNext()){
			node_data thenewOne = (node_data)iter.next();
			isConnectedRec(thenewOne);
			Iterator <node_data> iter2 = this.getGraph().getV().iterator();
			while(iter2.hasNext()){
				node_data corrent = iter2.next();
				if(corrent.getTag()!=1) return false;
				corrent.setTag(0);
			}
		}
		ChangeTheTag();
		return true;
	}


	// need fix !
	private void isConnectedRec(node_data thenewOne) {
		//thenewOne = (NodeData)thenewOne;
		if(thenewOne.getTag()==1) return;
		thenewOne.setTag(1);
		if(this.getGraph().getE(thenewOne.getKey()) == null) return;
		for(edge_data edge : this.getGraph().getE(thenewOne.getKey())){
			int dest = edge.getDest();
			isConnectedRec(this.getGraph().getNode(dest));
		}

//		for(Integer key : ((NodeData) thenewOne).HM.keySet()) {
//			int dest = ((NodeData) thenewOne).HM.get(key).getDest();
//			isConnectedRec(this.graph.getNode(dest));
//		}
	}

	public ArrayList<Integer> MakeListInt(List<node_data> p ){
		ArrayList<Integer> ans = new ArrayList<>();
		Iterator<node_data> iter = p.iterator();
		while (iter.hasNext()){
			ans.add(iter.next().getKey());
		}
		return  ans;
	}

	public double shortestPathDist(int src, int dest) {
		if(src == dest) return 0;
		for (node_data nodes : this.getGraph().getV()){
			nodes.setWeight(Integer.MAX_VALUE);
			nodes.setTag(0);
		}
		try {
			node_data theCurrenSrc = this.getGraph().getNode(src);
			theCurrenSrc.setWeight(0);
			int min = theCurrenSrc.getKey();
			while (min != dest) {
				min = CheckWhatMin();
				this.getGraph().getNode(min).setTag(1);
				if (this.getGraph().getE(min) != null) {
					if (this.getGraph().getE(min).iterator() != null) {
						Iterator<edge_data> iterEdges = this.getGraph().getE(min).iterator();
						while (iterEdges.hasNext()) {
							edge_data theCurrentEdge = iterEdges.next();
							node_data CurrentSrc = this.getGraph().getNode(theCurrentEdge.getSrc());
							node_data CurrentDest = this.getGraph().getNode(theCurrentEdge.getDest());
							if (CurrentSrc.getWeight() + theCurrentEdge.getWeight() < CurrentDest.getWeight()) {
								CurrentDest.setWeight(CurrentSrc.getWeight() + theCurrentEdge.getWeight());
							}
						}
					}
				}
			}
			double ans = this.getGraph().getNode(dest).getWeight();
			return ans;


		}catch (Exception e){
			System.out.println("Cannot get to dest");
			return -1;
		}
	}

	private int CheckWhatMin() {
		Iterator<node_data> iter = this.getGraph().getV().iterator();
		double min = Integer.MAX_VALUE;
		int theGoodKey = 0;
		while (iter.hasNext()){
			node_data current = iter.next();
			if( current.getTag() == 0 && current.getWeight() <= min)
			{
				min = current.getWeight();
				theGoodKey = current.getKey();
			}
		}
		return theGoodKey;
	}

	private void resetWeightToDones() {
		Iterator<node_data> iter = this.getGraph().getV().iterator();
		while(iter.hasNext()){
			node_data n = iter.next();
			n.setWeight(Integer.MAX_VALUE);
		}
	}





	@Override
	public List<node_data> shortestPath(int src, int dest) {
		try {
			ArrayList<node_data> SPArrays = new ArrayList<>();
			double flag = shortestPathDist(src, dest);
			if(flag == Integer.MAX_VALUE || flag == -1) return null;
			graph CopyGraph = this.copy();
			CopyGraph = changeDir(CopyGraph);
		//	SPArrays = ReverseArrays(SPArrays);
			SPArrays = ReturnTheSPway(dest, src, CopyGraph);
			SPArrays = ReverseArrays(SPArrays);
			ChangeTheTag();
			ChangeTheTagToEdge();
			resetWeightToDones();
			return SPArrays;
		}
		catch (Exception e){
			return null;
		}
	}

	private ArrayList<node_data> ReverseArrays(ArrayList<node_data> spArrays) {
		ArrayList<node_data> theGoodCopy = new ArrayList<>();
		for (int i = spArrays.size()-1; i >= 0; i--) {
			theGoodCopy.add(spArrays.get(i));
		}
		return theGoodCopy;
	}

	private ArrayList<node_data> ReturnTheSPway(int src, int dest, graph copyGraph) {
		ArrayList<node_data> SaveTheWay = new ArrayList<>();
		node_data source = copyGraph.getNode(src);
		while(src!=dest){
			source = copyGraph.getNode(src);
			SaveTheWay.add(source);
			double weight = Double.MAX_VALUE;
			Iterator<edge_data> iteEd = copyGraph.getE(src).iterator();
			while (iteEd.hasNext()){
				edge_data tempEdge = iteEd.next();
				node_data tempNode = this.getGraph().getNode(tempEdge.getDest());
				if(source.getWeight()+tempEdge.getWeight()< weight && source.getWeight()-tempEdge.getWeight()==tempNode.getWeight())
				{
					weight = source.getWeight()+tempEdge.getWeight();
					src = tempEdge.getDest();
				}
			}

		}
		source = copyGraph.getNode(dest);
		SaveTheWay.add(source);
		return SaveTheWay;
	}

	private graph changeDir(graph copy) {
		Graph_Algo addCopy = new Graph_Algo();
		addCopy.graph = copy;
		graph CopyToCopy = addCopy.copy();
		Iterator<node_data> iter = copy.getV().iterator();
		while (iter.hasNext()){
			node_data tempOne = iter.next();
			if(copy.getE(tempOne.getKey())!=null) {
				Iterator<edge_data> iterEdge = copy.getE(tempOne.getKey()).iterator();
				while (iterEdge.hasNext()) {
					edge_data tempEdge = iterEdge.next();
					if (CopyToCopy.getEdge(tempEdge.getSrc(), tempEdge.getDest()).getTag() == 0) {
						if (CopyToCopy.getEdge(tempEdge.getDest(), tempEdge.getSrc()) == null) {
							CopyToCopy.connect(tempEdge.getDest(), tempEdge.getSrc(), tempEdge.getWeight());
							CopyToCopy.removeEdge(tempEdge.getSrc(), tempEdge.getDest());
						} else {
							edge_data tEdge = CopyToCopy.getEdge(tempEdge.getDest(), tempEdge.getSrc());
							CopyToCopy.connect(tempEdge.getDest(), tempEdge.getSrc(), tempEdge.getWeight());
							CopyToCopy.connect(tempEdge.getSrc(), tempEdge.getDest(), tEdge.getWeight());
							CopyToCopy.getEdge(tempEdge.getDest(), tempEdge.getSrc()).setTag(1);
						}
					}
				}
			}
		}
		return CopyToCopy;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		List<node_data> saveThisNodes = new LinkedList<node_data>();
		int theINow = targets.get(0) ;
		int indexToDelete = 0 ;
		int theOnetoThanos = 0 ;
		try {
			while (targets.size() != 1) {
				double min = Double.MAX_VALUE;
				for (int j = 0; j < targets.size(); j++) {
					double ans = shortestPathDist(theINow, targets.get(j));

					if ( targets.get(j) != theINow && min > ans) {

						min = shortestPathDist(theINow, targets.get(j));

						theOnetoThanos = targets.get(j);
						indexToDelete = targets.indexOf(theINow);
					}
				}
				targets.remove(indexToDelete);
				List<node_data> tempArrays = this.shortestPath(theINow, theOnetoThanos);
				AddToTheArrays(saveThisNodes, tempArrays);
				theINow = theOnetoThanos;

			}
			saveThisNodes.add(this.getGraph().getNode(theOnetoThanos));
			ChangeTheTag();
			return saveThisNodes;
		}
		catch (Exception e){
			System.out.println("Not Exist");
			return null;
		}
	}

	private void AddToTheArrays(List<node_data> saveThisNodes, List<node_data> tempArrays) {
		Iterator<node_data> iter = tempArrays.iterator();
		while (iter.hasNext())
		{
			saveThisNodes.add(iter.next());
		}
		saveThisNodes.remove(saveThisNodes.get(saveThisNodes.size()-1));
	}

	@Override
	public graph copy() {
		graph p = new DGraph();
		Iterator <node_data> iter = this.getGraph().getV().iterator();
		Iterator <node_data> iter2 = this.getGraph().getV().iterator();
		while(iter.hasNext()){
			NodeData theNewOne = (NodeData) iter.next();
			node_data theCopy = theNewOne.copy();
			p.addNode(theCopy);
		}
		while(iter2.hasNext()){
			node_data theNewOne = iter2.next();
			if(this.getGraph().getE(theNewOne.getKey())!=null) {
				Iterator<edge_data> iterE = this.getGraph().getE(theNewOne.getKey()).iterator();
				while (iterE.hasNext()) {
					EdgeData theNewEdge = (EdgeData) iterE.next();
					edge_data pp = theNewEdge.copy();
					p.connect(pp.getSrc(), pp.getDest(), pp.getWeight());
					p.getEdge(pp.getSrc(), pp.getDest()).setTag(pp.getTag());
				}
			}
		}

		return p;
	}

	public void ChangeTheTag(){
		Iterator<node_data> iter = this.getGraph().getV().iterator();
		while (iter.hasNext()){
			iter.next().setTag(0);
		}
	}
	public void ChangeTheTagToEdge(){
		Iterator<node_data> iter = this.getGraph().getV().iterator();
		while (iter.hasNext()){
			node_data n = iter.next();
			if(this.getGraph().getE(n.getKey())!=null) {
				Iterator<edge_data> iter2 = this.getGraph().getE(n.getKey()).iterator();
				while (iter2.hasNext()) {
					iter2.next().setTag(0);
				}
			}
		}
	}

	public static void main(String[] args) {
//		DGraph p = new DGraph();
//		NodeData test1 = new NodeData(1, 2, 3);
//		NodeData test2 = new NodeData(1, 2, 3);
//		NodeData test3 = new NodeData(1, 2, 3);
//		NodeData test4 = new NodeData(1, 2, 3);
//		NodeData test5 = new NodeData(1, 2, 3);
//		NodeData test6 = new NodeData(1, 2, 3);
//		p.addNode(test1);
//		p.addNode(test2);
//		p.addNode(test3);
//		p.addNode(test4);
//		p.addNode(test5);
//		p.addNode(test6);
//		p.connect(1, 2, 2);
//		p.connect(2, 3, 2);
//		p.connect(3, 4, 2);
//		p.connect(4, 5, 1);
//		p.connect(1, 4, 3);
//		p.connect(5, 1, 2);
//		//p.connect(3, 4, 10);
//		Graph_Algo e = new Graph_Algo();
//		e.init(p);
//		//e.save("tt");
//		Graph_Algo q = new Graph_Algo();
//		q.graph = e.copy();
		//q.init("tt");
//		System.out.println();
//		e.isConnected();
		//System.out.println(e.shortestPathDist(1,6));
		//	List<node_data> g = e.shortestPath(1,6);
		//	System.out.println(g.toString());
		//e.graph.getEdge(1,2).setInfo("111");
		//	edge_data ee = e.graph.getEdge(1,2);
		//String s = e.graph.getEdge(1,2).getInfo();
		//System.out.println(e.isConnected());
//		List<Integer> ppp = new ArrayList<>();
//		ppp.add(1);
//		ppp.add(4);
//		ppp.add(5);
//		//ppp.add(4);
//		//ppp.add(6);
//
//		List<node_data> rrrrr = e.TSP(ppp);
//		System.out.println("ll");
// need to done function that cancel the tag
//		Graph_Algo g = new Graph_Algo();
//		Point3D x = new Point3D(1,4,0);
//		Point3D y = new Point3D(2,5,0);
//		Point3D q = new Point3D(4,3,0);
//		node_data a = new NodeData(1,2,3);
//		node_data b =new NodeData(3,4,6);
//		node_data c = new NodeData(5,50,50);
//		DGraph d =new DGraph();
//		d.addNode(a);
//		d.addNode(b);
//		d.addNode(c);
//		d.connect(a.getKey(),b.getKey(),4);
//		d.connect(b.getKey(),c.getKey(),50);
//		d.connect(b.getKey(),a.getKey(),4);
//		d.connect(c.getKey(),b.getKey(),4);
//		g.init(d);
//		boolean f =g.isConnected();
//		System.out.println(f);
//		Point3D x = new Point3D(14,4,0);
//		Point3D x2 = new Point3D(-75,14,0);
//		Point3D x3 = new Point3D(80,5,0);
//		Point3D x4 = new Point3D(1,4,0);
//		Point3D x5 = new Point3D(-5,1,0);
//		Point3D x6 = new Point3D(8,3,0);
//		Point3D x7 = new Point3D(4,1,0);
//		Point3D x8 = new Point3D(75,14,0);
//		node_data a1 = new NodeData(x);
//		node_data a2 = new NodeData(x2);
//		node_data a3 = new NodeData(x3);
//		node_data a4 = new NodeData(x4);
//		node_data a5 = new NodeData(x5);
//		node_data a6 = new NodeData(x6);
//		node_data a7 = new NodeData(x7);
//		node_data a8 = new NodeData(x8);
//		DGraph d = new DGraph();
//		d.addNode(a1);
//		d.addNode(a2);
//		d.addNode(a3);
//		d.addNode(a4);
//		d.addNode(a5);
//		d.addNode(a6);
//		d.addNode(a7);
//		d.addNode(a8);
//		d.connect(a1.getKey(),a2.getKey(),1);
//		d.connect(a1.getKey(),a5.getKey(),2);
//		d.connect(a1.getKey(),a3.getKey(),6);
//		d.connect(a3.getKey(),a4.getKey(),7);
//		d.connect(a2.getKey(),a8.getKey(),8);
//		d.connect(a2.getKey(),a7.getKey(),7);
//		d.connect(a5.getKey(),a6.getKey(),2);
//		d.connect(a6.getKey(),a7.getKey(),3);
//		d.connect(a7.getKey(),a6.getKey(),3);
//		Graph_Algo p = new Graph_Algo();
//		p.init(d);
//		List<node_data> ans = new LinkedList<node_data>();
//		double ans1 = p.shortestPathDist(5,8);
//		System.out.println(ans1);
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
		double e = p.shortestPathDist(1,6);
		System.out.println(e);
	}
}