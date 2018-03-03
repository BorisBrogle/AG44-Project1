package fr.boris.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import fr.boris.model.Graph;
import fr.boris.model.Kosaraju;
import fr.boris.model.Node;


public class Main {
	public final static String FILE_TO_USE = "mat3.txt";
	public static void main(String[] args) 
	{
		Graph myGraph = new Graph();
		
	    try{
	        // Reads the graph
	    	System.out.println("----------[USING MATRIX "+FILE_TO_USE+"]----------\n");
	        Scanner reader = new Scanner(new FileInputStream("ressources/"+ FILE_TO_USE));
	        
	        int numberOfNodes = Integer.parseInt(reader.next());


	        for (int i = 0; i<numberOfNodes; i++)
	        {
	        	reader.nextLine();
		        Node newNode = new Node();
		        myGraph.addNode(i, newNode);
		        for (int j=0; j<numberOfNodes; j++)
		        {
		        	if (Integer.parseInt(reader.next()) != 0)
		        	{
		        		newNode.addSuccessor(j);
		        	}	
		        }
	        }
            reader.close();
            
            // Prints the graph 
    	    myGraph.listTheNodes();
    	    
    	    // Launch the algorithm
    	    Kosaraju kosa = new Kosaraju();
    	    kosa.launchAndPrintSCC(myGraph);
    	    int[][] N = kosa.createN(kosa.getSCComponents(myGraph.getNodeList()), myGraph.getNodeList());
    	    kosa.printN(N);
	    }
	    catch(FileNotFoundException e)
	    {
	    	System.out.println("Error: no file found");
	    }
	}
}
