package assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Town Graph class that uses towns as vertices and
 * roads as edges
 * @author Yonathan Kebede
 *
 */
public class TownGraph implements GraphInterface<Town, Road>{

	private Set<Town> townSet;
	private Set<Road> roadSet;
	
	private Set<Town> unvisitedTowns;
	
	/**
	 * No-arg constructor
	 */
	public TownGraph() {
		super();
		townSet = new HashSet<Town>();
		roadSet = new HashSet<Road>();
		
	}

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		// TODO Auto-generated method stub
		
		for(Road road: roadSet) {
				
			if(road.getTowns().contains(sourceVertex) && road.getTowns().contains(destinationVertex)) {
				return road;
			}
		}
		
		return null;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		// TODO Auto-generated method stub
		
		
		Road newRoad = new Road(sourceVertex,destinationVertex,weight,description);
		roadSet.add(newRoad);
		
		sourceVertex.addRoad(newRoad);
		destinationVertex.addRoad(newRoad);
			
		return newRoad;
	}

	@Override
	public boolean addVertex(Town v) {
		// TODO Auto-generated method stub
		return townSet.add(v);
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		// TODO Auto-generated method stub
		
		for( Road road: sourceVertex.getRoadList()) {
			if(road.getTowns().contains(destinationVertex)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean containsVertex(Town v) {

		for(Town town:townSet) {
			if (town.getName().equals(v.getName()))
				return true;
		}
		
		return false;
	}

	@Override
	public Set<Road> edgeSet() {
		// TODO Auto-generated method stub		
		return roadSet;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		// TODO Auto-generated method stub
		return new HashSet<Road>(vertex.getRoadList());
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		// TODO Auto-generated method stub

		Road roadToRemove = new Road(sourceVertex,destinationVertex,weight,description);
		
		for(Road road: roadSet) {
			if(road.compareTo(roadToRemove) == 0) {
				
				for(Town town: road.getTowns())
					town.getRoadList().remove(road);
				
				
				roadSet.remove(road);
				return road;
			}
		}
			
		
		return null;
	}

	@Override
	public boolean removeVertex(Town v) {
		// TODO Auto-generated method stub
		return townSet.remove(v);
	}

	@Override
	public Set<Town> vertexSet() {
		// TODO Auto-generated method stub
		return townSet;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		// TODO Auto-generated method stub
		resetVertices();
		dijkstraShortestPath(sourceVertex);
		
		ArrayList<String> path = new ArrayList<String>();
		Road road;
		StringBuilder stringPath;
		
		
		while(!destinationVertex.equals(sourceVertex)){
		
			road = getEdge(destinationVertex, destinationVertex.getBackVertex());
			
			stringPath = new StringBuilder(destinationVertex.getBackVertex().getName());
			stringPath.append(" via ");
			stringPath.append(road.getName());
			stringPath.append(" to ");
			stringPath.append(destinationVertex.getName());
			stringPath.append(" ");
			stringPath.append(road.getDistance());
			
			
			
			path.add(stringPath.toString());
			
			destinationVertex = destinationVertex.getBackVertex();
			
			
		} 
		Collections.reverse(path);
			
		return path;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		
		resetVertices();
		
		unvisitedTowns = new HashSet<Town>(townSet);
		
		Town startVertex = sourceVertex;
		Town selectedVertex = startVertex;
		Town nextVertex = null;
		Town anotherVertex = null;
		
		
		
		boolean done = false;
		
		while(!done && !unvisitedTowns.isEmpty()) {
			
			
			
			for(Road road: selectedVertex.getRoadList()) {
				
				
				anotherVertex = road.getOtherEnd(selectedVertex);
				
				if(anotherVertex.getCost() > road.getDistance() + selectedVertex.getCost() || anotherVertex.getCost() == 0) {
					
					anotherVertex.setCost(road.getDistance() + selectedVertex.getCost());
					anotherVertex.setBackVertex(selectedVertex);
				}

			}
			unvisitedTowns.remove(selectedVertex);
			
			if(!unvisitedTowns.isEmpty()) {
				
				int minWeight = Integer.MAX_VALUE;
				
				for(Town town: unvisitedTowns) {
					
					if(town.getCost() != 0 && minWeight > town.getCost()) {
						
						nextVertex = town;
						minWeight = town.getCost();
					}
					
				}
				
				selectedVertex = nextVertex;
			}
			
			
		}

	}
	
	/**
	 * Method that resets costs, predecessors and visited
	 * variables for each vertex in graph
	 */
	protected void resetVertices() {
		for(Town town: townSet) {
			town.setBackVertex(null);
			town.setCost(0);
			town.setVisited(false);
		}
	}
	
	

	

}
