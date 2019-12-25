package dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


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
		n = (NodeData)n;
		((NodeData) n).setKey(keyCounter++);

	}

	@Override
	public void connect(int src, int dest, double w) {
		if(GraphMap.get(src) != null && GraphMap.get(dest) != null) {
			EdgeData edge = new EdgeData(src, dest, w);
			NodeData theNewSrc = (NodeData) this.getNode(src);
			if (theNewSrc.HM.containsKey(dest)) {
				theNewSrc.HM.replace(dest, edge);
			}
			else theNewSrc.HM.put(dest, edge);
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
		if(this.edgeHM.get(node_id) == null) return null;
		return this.edgeHM.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
		MC++;
		return GraphMap.remove(key);
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
}
