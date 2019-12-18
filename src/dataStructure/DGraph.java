package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class DGraph implements graph{
	private HashMap<Integer,node_data> GraphMap = new HashMap<>();
	public static List<edge_data> edgeList = new ArrayList<>();

	@Override
	public node_data getNode(int key) {
		return GraphMap.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		node_data _src = GraphMap.get(src);
		NodeData src_node = (NodeData) _src;
		return  src_node.getDestEdge(dest);
	}

	private int getWieght(int src, int dest) {
		node_data _src = GraphMap.get(src);
		NodeData src_node = (NodeData) _src;
		return (int)src_node.getDestEdge(dest).getWeight();
	}

	@Override
	public void addNode(node_data n) {
		this.GraphMap.put(n.getKey(),n);
	}

	@Override
	public void connect(int src, int dest, double w) {
		EdgeData edge = new EdgeData(src,dest,2,0);
	}

	@Override
	public Collection<node_data> getV() {
		return GraphMap.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return edgeList;
	}

	@Override
	public node_data removeNode(int key) {
		return GraphMap.remove(key);
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		NodeData sorce = (NodeData)GraphMap.get(src);
		return sorce.removeEdge(dest);
	}

	@Override
	public int nodeSize() {
		return GraphMap.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
