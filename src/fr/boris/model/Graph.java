package fr.boris.model;

import java.util.HashMap;

public class Graph {
	private HashMap<Integer,Node> nodeList;
	
	public Graph() {
		this.nodeList = new HashMap<Integer, Node>();
	}
	
	public void addNode(int i, Node n) {
		this.nodeList.put(i, n);
	}
	
	public HashMap<Integer,Node> getNodeList() {
		return this.nodeList;
	}
	
	public void listTheNodes()
	{
		System.out.println("This graph contains "+nodeList.size()+" nodes: ");
		for (int i = 0; i<nodeList.size(); i++)
		{
			System.out.print("Node "+(i+1)+" - succs: { ");
			nodeList.get(i).printSucc();
			System.out.println("}");
					
		}
	}
}
