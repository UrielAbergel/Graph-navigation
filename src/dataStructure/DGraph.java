package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class DGraph implements graph{
	public HashMap<Integer,node_data> GraphMap = new HashMap<>();
	public HashMap<String,edge_data> edgeHM = new HashMap<String,edge_data>();
	//public HashMap<Integer,HashMap<Integer,node_data>> vectorsHM = new HashMap<Integer, HashMap<Integer,node_data>>();
	public static int MC;
	@Override
	public node_data getNode(int key) {
		return GraphMap.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		return edgeHM.get(""+src+","+dest);
	}

	private int getWieght(int src, int dest) {
		return (int)edgeHM.get(""+src+","+dest).getWeight();
	}

	@Override
	public void addNode(node_data n) {
		MC++;
		this.GraphMap.put(n.getKey(),n);
	}

	@Override
	public void connect(int src, int dest, double w) {
		if(GraphMap.get(src) != null && GraphMap.get(dest) != null) {
			EdgeData edge = new EdgeData(src, dest, w);
			NodeData theNewSrc = (NodeData) this.getNode(src);
			if (theNewSrc.HM.containsKey(dest)) {
				theNewSrc.HM.replace(dest, edge);
			} else theNewSrc.HM.put(dest, edge);
			this.edgeHM.put("" + src + "," + dest, edge);
			MC++;
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
		return edgeHM.values();
	}

	@Override
	public node_data removeNode(int key) {
		MC++;
		return GraphMap.remove(key);
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		NodeData sorce = (NodeData)GraphMap.get(src);
		edgeHM.remove(StringToHash(src,dest));
		MC++;
		return sorce.removeEdge(dest);
	}

	@Override
	public int nodeSize() {
		return GraphMap.size();
	}

	@Override
	public int edgeSize() {
		return edgeHM.size();
	}

	@Override
	public int getMC() {
		return this.MC;
	}
	public static String StringToHash(int src,int dest){
		return ""+src+","+dest;
	}
}
