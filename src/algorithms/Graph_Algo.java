package algorithms;

import java.io.*;
import java.util.*;

import dataStructure.*;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	private graph graph = new DGraph();

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
		ChangeTheTag();
		return true;
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
		if(src==dest) return 0;
		this.graph.getNode(src).setWeight(0);
		NodeData srcDataNode = (NodeData)this.graph.getNode(src);
		SPDrec(dest,srcDataNode);
		ChangeTheTag();
		//ChangeTheTagToEdge();
		//resetWeightToDones();
		return this.graph.getNode(dest).getWeight();

	}

	private void resetWeightToDones() {
		Iterator<node_data> iter = this.graph.getV().iterator();
		while(iter.hasNext()){
			iter.next().setWeight(0);
		}
	}

	public void SPDrec(int dest,NodeData current){
		if(current.getKey() == dest) return;
		if(current.getTag()==1) return;
		current.setTag(1);
		Iterator<EdgeData> ite = current.HM.values().iterator();
		while (ite.hasNext()){
			EdgeData toCheckedge = ite.next();
			int tempdest = toCheckedge.getDest();
			node_data p = this.graph.getNode(tempdest);
			if(current.getWeight()+toCheckedge.getWeight()<p.getWeight())
			{
				p.setWeight(toCheckedge.getWeight()+current.getWeight());
			}
			SPDrec(dest,(NodeData)p);
		}
	}



	@Override
	public List<node_data> shortestPath(int src, int dest) {
		ArrayList<node_data> SPArrays = new ArrayList<>();
		shortestPathDist(src,dest);
		graph CopyGraph = this.copy();
		CopyGraph = changeDir(CopyGraph);
		SPArrays = ReturnTheSPway(dest,src,CopyGraph);
		SPArrays =  ReverseArrays(SPArrays);
		ChangeTheTag();
		ChangeTheTagToEdge();
		return SPArrays;
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
			 	node_data tempNode = this.graph.getNode(tempEdge.getDest());
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

		while(!targets.isEmpty()) {
			double min = Double.MAX_VALUE;
			for (int j = 0 ; j < targets.size() ; j++ ) {
					if(min >shortestPathDist(theINow,targets.get(j)) && targets.get(j) !=theINow)
					{
						min = shortestPathDist(theINow,targets.get(j));
						theOnetoThanos = targets.get(j);
						indexToDelete = targets.indexOf(theINow) ;
					}
			}
			targets.remove(indexToDelete);
			List<node_data> tempArrays = this.shortestPath(theINow,theOnetoThanos);
			AddToTheArrays(saveThisNodes,tempArrays);
			theINow = theOnetoThanos ;

		}
		saveThisNodes.add(this.graph.getNode(theOnetoThanos));
		ChangeTheTag();
		return saveThisNodes;
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
		Iterator <node_data> iter = this.graph.getV().iterator();
		Iterator <node_data> iter2 = this.graph.getV().iterator();
		while(iter.hasNext()){
			NodeData theNewOne = (NodeData) iter.next();
			node_data theCopy = theNewOne.copy();
			p.addNode(theCopy);
		}
		while(iter2.hasNext()){
			node_data theNewOne = iter2.next();
			if(this.graph.getE(theNewOne.getKey())!=null) {
				Iterator<edge_data> iterE = this.graph.getE(theNewOne.getKey()).iterator();
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
		Iterator<node_data> iter = this.graph.getV().iterator();
		while (iter.hasNext()){
			iter.next().setTag(0);
		}
	}public void ChangeTheTagToEdge(){
		Iterator<node_data> iter = this.graph.getV().iterator();
		while (iter.hasNext()){
			node_data n = iter.next();
			if(this.graph.getE(n.getKey())!=null) {
				Iterator<edge_data> iter2 = this.graph.getE(n.getKey()).iterator();
				while (iter2.hasNext()) {
					iter2.next().setTag(0);
				}
			}
		}
	}

	public static void main(String[] args) {
		DGraph p = new DGraph();
		NodeData test1 = new NodeData(1, 2, 3);
		NodeData test2 = new NodeData(1, 2, 3);
		NodeData test3 = new NodeData(1, 2, 3);
		NodeData test4 = new NodeData(1, 2, 3);
		NodeData test5 = new NodeData(1, 2, 3);
		NodeData test6 = new NodeData(1, 2, 3);
		p.addNode(test1);
		p.addNode(test2);
		p.addNode(test3);
		p.addNode(test4);
		p.addNode(test5);
		p.addNode(test6);
		p.connect(1, 2, 2);
		p.connect(2, 3, 2);
		p.connect(3, 4, 2);
		p.connect(4, 5, 1);
		p.connect(1, 4, 3);
		p.connect(5, 1, 2);
		//p.connect(3, 4, 10);
		Graph_Algo e = new Graph_Algo();
		e.init(p);
		//e.save("tt");
		Graph_Algo q = new Graph_Algo();
		q.graph = e.copy();
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
		List<Integer> ppp = new ArrayList<>();
		ppp.add(1);
		ppp.add(4);
		ppp.add(5);
		//ppp.add(4);
		//ppp.add(6);

		List<node_data> rrrrr = e.TSP(ppp);
		System.out.println("ll");
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
	}
}
