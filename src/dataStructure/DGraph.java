package dataStructure;

import java.io.Serializable;
import java.util.*;


public class DGraph implements graph, Serializable {

	public HashMap<Integer,node_data> GraphMap = new HashMap<>();
	public HashMap<Integer,HashMap<Integer,edge_data>> edgeHM = new HashMap<Integer, HashMap<Integer, edge_data>>();
	public int EdgeSize = 0;
	public int keyCounter = 1 ;
	public int MC;

	/**
	 * reset use only for test!!!
	 */
	public void reset(){
		this.EdgeSize = 0;
		this.GraphMap.clear();
		this.edgeHM.clear();
		this.MC = 0;
		this.keyCounter = 1;
	}

	@Override
	public node_data getNode(int key) {

		return GraphMap.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {

		if(this.edgeHM.get(src)==null){
			return null;
		}
		if(this.edgeHM.get(src).get(dest)==null){
			return null;
		}
		return this.edgeHM.get(src).get(dest);
	}

	private int getWieght(int src, int dest) {
		HashMap<Integer,edge_data> s = this.edgeHM.get(src);
		return (int)s.get(dest).getWeight();
	}

	@Override
	public void addNode(node_data n) {
		MC++;
		this.GraphMap.put(keyCounter,n);
		((NodeData) n).setKey(keyCounter++);

	}

	@Override
	public void connect(int src, int dest, double w) {
		if(GraphMap.get(src) != null && GraphMap.get(dest) != null) {
			EdgeData edge = new EdgeData(src, dest, w);
			NodeData theNewSrc = (NodeData) this.getNode(src);
			HashMap<Integer,edge_data> s = this.edgeHM.get(src);
			if(s==null){
				s = new HashMap<Integer,edge_data>();
				this.edgeHM.put(src,s);
			}
			if(this.edgeHM.get(src).containsKey(dest)){
				this.edgeHM.get(src).remove(dest);
				this.edgeHM.get(src).put(dest,edge);
			}
			else this.edgeHM.get(src).put(dest,edge);
			MC++;
			EdgeSize++;
		}
		else{
			System.out.println("src or dest is null");
		}
	}

	@Override
	public Collection<node_data> getV() {
		return GraphMap.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		if(!this.edgeHM.containsKey(node_id)) return null;
		if(this.edgeHM.get(node_id) == null) return null;
		return this.edgeHM.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
		if(GraphMap.containsKey(key)) {
			MC++;
			if (edgeHM.containsKey(key)) {
				Iterator<edge_data> iter = edgeHM.get(key).values().iterator();
				while (iter.hasNext()) {
					edge_data edge = iter.next();
					this.removeEdge(edge.getSrc(), edge.getDest());
					iter = edgeHM.get(key).values().iterator();
				}
				edgeHM.remove(key);
			}
			Iterator<node_data> iter2 = GraphMap.values().iterator();
			while (iter2.hasNext()) {
				node_data n = iter2.next();
				if (edgeHM.containsKey(n.getKey())) {
					if (edgeHM.get(n.getKey()).containsKey(key)) {
						edgeHM.get(n.getKey()).remove(key);
					}
				}
			}
			return GraphMap.remove(key);
		}
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(this.edgeHM.get(src).get(dest)!=null) {
			MC++;
			EdgeSize--;
			return this.edgeHM.get(src).remove(dest);
		}
		else return null;
	}

	@Override
	public int nodeSize() {
		return GraphMap.size();
	}

	@Override
	public int edgeSize() {
		return EdgeSize;
	}

	@Override
	public int getMC() {
		return this.MC;
	}
	public static String StringToHash(int src,int dest){
		return ""+src+","+dest;
	}
	public String toString(){
		return GraphMap.toString();
	}

	public static void main(String[] args) {
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
		DG.removeNode(5);
	}
}
