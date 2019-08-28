package assignment6;

import java.util.HashSet;
import java.util.Set;

/**
 * Edge class that represents a road
 * @author Yonathan Kebede
 *
 */
public class Road implements Comparable<Road> {
	
	
	private int distance;
	private Set<Town> towns;
	private String name;
	
	/**
	 * Constructor to make an undirected edge(road) between to vertices(towns)
	 * @param town1 - town involved in edge
	 * @param town2 = another town involved in edge
	 * @param distance - weight of the edge
	 * @param name - label of the edge
	 */
	public Road(Town town1, Town town2, int distance, String name) {
		// TODO Auto-generated constructor stub
		super();
		this.distance = distance;
		this.towns = new HashSet<Town>();
		this.name = name;
		
		towns.add(town1);
		towns.add(town2);
		
	}
	
	/**
	 * Getter for name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for towns of an edge
	 * @return
	 */
	public HashSet<Town> getTowns() {
		return (HashSet<Town>) towns;
	}

	/**
	 * Getter for weight of edge
	 * @return weight
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Setter for weight
	 * @param distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}	
	
	@Override
	public int compareTo(Road road) {
		// TODO Auto-generated method stub
		if(road.name.equals(name)) {
			if(towns.equals(road.towns) && distance == road.distance)
				return 0;
			
			return 1;
		}
		
		return -1;
	}
	
	/**
	 * Equals method that says to road objects are the same 
	 * if they have the same name
	 * @param road
	 * @return returns true iff name is the same
	 */
	public boolean equals(Object obj) {
		return name.equals(((Road)obj).name);
	}
	
	
	/**
	 * Method that will get the other town
	 * in an edge connection given one of the
	 * two towns
	 * @param town - town that is the opposite side of the edge
	 * @return returns the town at the other side of the edge
	 */
	public Town getOtherEnd(Town town) {
		
		for(Town t: towns) {
			if(!t.equals(town))
				return t;
		}
		
		return null;
		
	}


}
