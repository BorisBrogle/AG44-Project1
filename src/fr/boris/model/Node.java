package fr.boris.model;

import java.util.ArrayList;

public class Node {
	private ArrayList<Integer> successors;
	
	public Node() {
		this.successors = new ArrayList<Integer>();
	}
	
	public void addSuccessor(Integer n) {
		this.successors.add(n);
	}
	
	public void printSucc() {
		for (int i = 0; i<successors.size(); i++)
		{
			System.out.print(successors.get(i)+1+" ");
		}
	}
	
	public int size() {
		return this.successors.size();
	}
	
	public ArrayList<Integer> getSuccessors()
	{
		return this.successors;
	}
	
	public int get(int i)
	{
		return this.successors.get(i);
	}
	
	public void add(int i)
	{
		this.successors.add(i);
	}
	
	
	
}
