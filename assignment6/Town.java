package assignment6;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Vertex class
 * @author Yonathan Kebede
 *
 */
public class Town implements Comparable<Town>{

	
	private String name;
	private ArrayList<Town> adjacentTowns;
	private ArrayList<Road> roadList;
	
	private Town backVertex;
	private int cost;
	private boolean visited;
	
	
	/**
	 * Constructor
	 * @param name - name of the vertex
	 * @param roadList - list of roads that come from the edge
	 */
	public Town(String name, ArrayList<Road> roadList) {
		super();
		this.name = name;
		this.roadList = roadList;
		
		cost = 0;
		visited = false;
		backVertex = null;
		
	}


	/**
	 * Constructor
	 * @param name - name of the vertex
	 */
	public Town(String name) {
		// TODO Auto-generated constructor stub
		super();
		this.name = name;
		roadList = new ArrayList<Road>();
		
		cost = 0;
		visited = false;
		backVertex = null;
		
	}
	
	/**
	 * Getter for neighboring towns list
	 * @return adjacentTowns
	 */
	public ArrayList<Town> getAdjacentTowns() {
		return adjacentTowns;
	}

	/**
	 * Setter for neighboring towns list
	 * @param arrayList
	 */
	public void setAdjacentTowns(ArrayList<Town> arrayList) {
		this.adjacentTowns = arrayList;
	}

	/**
	 * Getter for cost of travel from on vertex to this
	 * vertex
	 * @return cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Setter for cost
	 * @param cost
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * This method activates vertex as a visited
	 * vertex. Used for Dijikstra.
	 */
	public void visit() {
		visited = true;
	}
	
	/**
	 * This method deactivates vertex as a visited
	 * vertex. Used for Dijikstra.
	 */
	public void unvisit() {
		visited = false;
	}

	/**
	 * Checks if vertex is visited
	 * @return visited
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * Visited variable setter
	 * @param visited
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Roadlist setter
	 * @param roadList
	 */
	public void setRoadList(ArrayList<Road> roadList) {
		this.roadList = roadList;
	}

	/**
	 * Name getter
	 * @return name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Name setter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for predecessor vertex in Dijkstra
	 * @return backVertex - predecessor vertex
	 */
	public Town getBackVertex() {
		return backVertex;
	}
	
	/**
	 * Setter for predecessor vertex for Dijkstra algorithm
	 * @param town - town object that sets the predecessor vertex
	 */
	public void setBackVertex(Town town) {
		this.backVertex = town;
	}

	/**
	 * Getter for list of roads that are connected
	 * to this vertex
	 * @return
	 */
	public ArrayList<Road> getRoadList() {
		return roadList;
	}
	
	/**
	 * Method to add a road to the road list of the vertex
	 * @param road - new road that is to be added
	 */
	public void addRoad(Road road) {
		roadList.add(road);
	}

	@Override
	public int compareTo(Town o) {
		// TODO Auto-generated method stub
		
		if (name.equals(o.name))
			return 0;
		
		return 1;
	}

	@Override
	public String toString() {
		return "Town [name=" + name + "]";
	}


	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * Equals method that checks if names of objects match
	 */
	public boolean equals(Object obj) {	
		return ((Town)obj).name.equals(name);
	}
	
	/**
	 * Checks for Dijkstra algorithm if there is 
	 * predecessor
	 * @return true iff there is a predecessor
	 */
	public boolean hasPredecessor() {
		return backVertex != null;
	}
	
	/**
	 * Iterator for neighboring towns
	 * @return town iterator
	 */
	public Iterator<Town> getNeighborIterator(){
		return getAdjecentTowns().iterator();
	}

	/**
	 * Method for obtaining neighboring town list
	 * @return
	 */
	public ArrayList<Town> getAdjecentTowns() {
		// TODO Auto-generated method stub
		ArrayList<Town> adjacentTowns = new ArrayList<Town>();
		for(Road road: roadList) {
			for(Town town: road.getTowns()) {
				if(town != this) {
					adjacentTowns.add(town);
				}
			}
		}
		return adjacentTowns;
	}
	
	

}
