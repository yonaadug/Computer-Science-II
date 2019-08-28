package assignment6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Manager class for the town graph class
 * @author Yonathan Kebede
 *
 */
public class TownGraphManager implements TownGraphManagerInterface{
	
	
	private TownGraph townGraph;

	/**
	 * No-arg constructor
	 */
	public TownGraphManager() {
		townGraph = new TownGraph();
	}
	 
	
	@Override
	public boolean addRoad(String town1, String town2, int distance, String roadName) {
		// TODO Auto-generated method stub
		
		Town firstTown = findVertex(town1);
		Town secondTown = findVertex(town2);
		
		
		return townGraph.addEdge(firstTown, secondTown, distance, roadName) != null;
	}

	@Override
	public String getRoad(String town1, String town2) {
		// TODO Auto-generated method stub
		
		Town sourceVertex = findVertex(town1);
		Town destinationVertex = findVertex(town2);
		
		return townGraph.getEdge(sourceVertex,destinationVertex).getName();
	}

	@Override
	public boolean addTown(String v) {
		// TODO Auto-generated method stub
		
		Town town = findVertex(v);
		
		if(town != null) {
			return false;
		}
		
		return townGraph.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		// TODO Auto-generated method stub
		return findVertex(name);
	}

	@Override
	public boolean containsTown(String v) {
		// TODO Auto-generated method stub
		return findVertex(v) != null;
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		// TODO Auto-generated method stub
		
		boolean found = false;
		Iterator<Town> iter;
		
		for(Town town : townGraph.vertexSet()) {
			
			if(town1.equals(town.getName())) {
				iter = town.getNeighborIterator();
				while(!found && iter.hasNext()) {

					if(iter.next().getName().equals(town2)) {
						found = true;
					}

				}
			}
			
		}
	
		return found;
	}

	@Override
	public ArrayList<String> allRoads() {
		// TODO Auto-generated method stub
		
		ArrayList<String> roadStrings = new ArrayList<String>();
		
		for(Road road: townGraph.edgeSet()) 
			roadStrings.add(road.getName());
		
		Collections.sort(roadStrings);
		
		return roadStrings;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String roadStr) {
		// TODO Auto-generated method stub
		
		Town firstTown = null;
		Town secondTown = null;
		
		for(Town town: townGraph.vertexSet()) {
			
			if (town.getName().equals(town1))
				firstTown = town;
			else if(town.getName().equals(town2))
				secondTown = town;
			
		}
		
		for(Road road: townGraph.edgeSet()) {
			if(road.getName().equals(roadStr) && road.getTowns().contains(firstTown) && road.getTowns().contains(secondTown)) {
				townGraph.removeEdge(firstTown, secondTown, road.getDistance(), road.getName());
				return true;
			}
		}
		
		
		return false;
	}

	@Override
	public boolean deleteTown(String v) {
		// TODO Auto-generated method stub
		return townGraph.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		// TODO Auto-generated method stub
		
		ArrayList<String> townList = new ArrayList<String>();
		
		for (Town town: townGraph.vertexSet()) {
			townList.add(town.getName());
		}
		
		Collections.sort(townList);
		
		return townList;
	}

	@Override
	public ArrayList<String> getPathSets(String name, String name2) {
		// TODO Auto-generated method stub
		townGraph.resetVertices();
		
		Town firstTown = findVertex(name);
		Town secondTown = findVertex(name2);
		
		ArrayList<String> shortPath = townGraph.shortestPath(firstTown,secondTown);
		ArrayList<String> shortPathMiles = new ArrayList<String>();
		
		StringBuilder pathStr;
		
		for(String str: shortPath) {
			shortPathMiles.add(str+" miles");
		}
		
		//Collections.reverse(shortPathMiles);
		
		pathStr = new StringBuilder("Total miles: ");
		pathStr.append(secondTown.getCost());
		pathStr.append(" miles");
		
		shortPathMiles.add(pathStr.toString());
		
		
		
		return shortPathMiles;
		
		

	}

	/**
	 * Method makes a graph from text in a file by making edges and vertices
	 * @param selectedFile - file that has collection of edges and vertices
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void populateTownGraph(File selectedFile) throws FileNotFoundException,IOException {
		// TODO Auto-generated method stub
		
		Scanner fileScan = new Scanner(selectedFile);
		String line;
		String[] strSplit;
		String[] roadStr;
		
		townGraph = new TownGraph();

		
		
		while(fileScan.hasNext()) {
			line = fileScan.nextLine();
			strSplit = line.split(";");
			
			roadStr = strSplit[0].split(",");
			//System.out.println(strSplit[0]);
			
		
			String name = strSplit[1];
			String name2 = strSplit[2];
						
			Town firstTown = findVertex(name);
			Town secondTown = findVertex(name2);
						
			if(firstTown == null) {
				firstTown = new Town(name);
				townGraph.addVertex(firstTown);
			}
			
			if(secondTown == null) {
				secondTown = new Town(name2);
				townGraph.addVertex(secondTown);
			}
			
			townGraph.addEdge(firstTown, secondTown, Integer.parseInt(roadStr[1]), roadStr[0]);				
		}
		
		
		fileScan.close();
		
	}
	
	/**
	 * Method that helps find vertex with a certain name
	 * @param name
	 * @return
	 */
	private Town findVertex(String name) {
		
		Town firstTown = null;
		
		
		for(Town town: townGraph.vertexSet()) {
			
			if (town.getName().equals(name))
				firstTown = town;
		}
		
		return firstTown;
		
	}


	@Override
	public ArrayList<String> getPath(String name, String name2) {
		return getPathSets(name, name2);
	}


}
