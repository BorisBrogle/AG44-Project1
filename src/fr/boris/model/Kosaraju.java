package fr.boris.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Kosaraju 
{
	/* Function used to do DFS on a subgraph */
    public void DFS(HashMap<Integer, Node> nodeList, int index, boolean[] visited, List<Integer> component) 
    {
        visited[index] = true;
        for (int i = 0; i < nodeList.get(index).size(); i++)
        {
        	int successor = nodeList.get(index).get(i);
            if (!visited[successor])
            {
                DFS(nodeList, successor, visited, component);
            }
        }
        component.add(index);
    }
    
    /* Used to call the DFS function on all the different subgraphs */
    public List<Integer> fillOrder(HashMap<Integer, Node> nodeList, boolean[] visited) 
    {        
        List<Integer> order = new ArrayList<Integer>();
 
        for (int i = 0; i < nodeList.size(); i++)
            if (!visited[i])
                DFS(nodeList, i, visited, order);
        return order;
    }
    
    /* Creates a new graph that is the transpose of the first graph, and returns it */
    public HashMap<Integer,Node> getTranspose(HashMap<Integer, Node> nodeList)
    {
        HashMap<Integer,Node> newNodeList = new HashMap<Integer, Node>();
        
        /* Copy all the nodes in the new graph */
        for (int i = 0; i < nodeList.size(); i++)
        {
            Node newNode = new Node();
            newNodeList.put(i, newNode);
        }

        for (int j = 0; j < nodeList.size(); j++) 
        {
            for (int i = 0; i < nodeList.get(j).size(); i++)
            {
                newNodeList.get(nodeList.get(j).get(i)).add(j);
            }
        }
        return newNodeList;
    }
    
    /* This function returns the strongly connected components of the graph given in parameter */
    public List<List<Integer>> getSCComponents(HashMap<Integer,Node> nodeList)
    {
        boolean[] visited = new boolean[nodeList.size()];
        List<Integer> order = fillOrder(nodeList, visited);       
        /* Build the transpose of the graph */
        HashMap<Integer,Node> reverseGraph = getTranspose(nodeList);        
        /* Clear the visited array */
        visited = new boolean[nodeList.size()];
        /* Reverse the order list */
        Collections.reverse(order);
 
        /* Finally, build the list of strongly connected components */
        List<List<Integer>> SCComp = new ArrayList<>();
        for (int i = 0; i < order.size(); i++)
        {
            int node = order.get(i);
            if (!visited[node]) 
            {
                List<Integer> component = new ArrayList<>();
                DFS(reverseGraph, node, visited, component);
                SCComp.add(component);
            }
        }
        return SCComp;
    }
    
    /* Used to launch and print the algorithm */
    public void launchAndPrintSCC(Graph myGraph)
    {
    	List<List<Integer>> SCComp = getSCComponents(myGraph.getNodeList());
    	
    	System.out.println("\nList of the strongly connected components:");
	    for (int i=0; i<SCComp.size(); i++)
	    {
	    	System.out.print("Component #"+(i+1)+": [");
	    	for (int j=0; j<SCComp.get(i).size(); j++)
	    	{
	    		System.out.print(" "+(SCComp.get(i).get(j)+1));	
	    	}
	    	System.out.println(" ]");
	    }
    }
    
    /* Used to locate the component in which is situated the node send in parameter */
    private int locateComponent(List<List<Integer>> SCComp, int node)
    {
    	 for (int i=0; i<SCComp.size(); i++)
 	    {
 	    	for (int j=0; j<SCComp.get(i).size(); j++)
 	    	{
 	    		if(SCComp.get(i).get(j)==node)
 	    			return i;
 	    	}
 	    }
    	 return -1; //if an error occurs
    }
    
    /* Used to create and return the matrix N of the direct passages from one component to another */
    public int[][] createN(List<List<Integer>> SCComp, HashMap<Integer, Node> nodeList)
    {
    	int[][] N = new int[SCComp.size()][SCComp.size()];
    	
	    for (int i=0; i<SCComp.size(); i++) // i traverses the components
	    {
	    	for (int j=0; j<SCComp.get(i).size(); j++) // j traverses the nodes in this component
	    	{
	    		ArrayList<Integer> succList = nodeList.get(SCComp.get(i).get(j)).getSuccessors();
	    		for(int k=0; k<succList.size(); k++) // k traverses the successors of this node
	    		{
	    			if(locateComponent(SCComp, succList.get(k))!=i) // if the current component is not the same as the component of this successor, we have a bridge
	    				N[i][locateComponent(SCComp, succList.get(k))]++; // add the node in the matrix N
	    		}
	    	}
	    }
    	
    	return N;
    }
    
    /* Used to print the matrix N */
    public void printN(int[][] N)
    {
    	System.out.print("\nMatrix N :");
    	for(int i=0; i<N.length; i++)
    	{
    		System.out.print("\n");
    		for(int j=0; j<N.length; j++)
    		{
    			System.out.print(" "+N[i][j]);
    		}
    	}
    }
}
