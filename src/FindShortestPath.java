/**
 * Martin Spassov
 * 250901340
 * mspassov
 */


import java.io.FileNotFoundException;
import java.io.IOException;

public class FindShortestPath {
	public static void main(String [] args){
		
		//First a dungeon is created, and the try catch blocks make sure that the text file is readable
		
		if(args.length < 1){	
			throw new IllegalArgumentException("Please provide a file as a command line argument");
		}
		
		Dungeon theDungeon=null;
		String dungeonFile=args[0];
		try{
			theDungeon= new Dungeon("dungeon5.txt");
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch(IOException e){
			System.out.println(e.getMessage());	
		}
		catch(InvalidDungeonCharacterException e){
			System.out.println("Input character is invalid");
		}
		
		
		DLinkedPriorityQueue<Hexagon> hexQueue= new DLinkedPriorityQueue<Hexagon>();
		Hexagon startChamber=theDungeon.getStart();
		Hexagon currentChamber=startChamber;
		int numPath=0;
		boolean exitFound=false;
		boolean check= false;
		int totalLength=0;
		hexQueue.add(currentChamber, 0);
		currentChamber.markEnqueued();
		
		//The outer file loop is placed in order to backtrack the program, in case the warrioer entered into a dragon chamber
		//or entered a chamber adjacent to a dragon
		//The inner loop decides where the warrior shoudl go
		
		while(check==false){
			while(!hexQueue.isEmpty() && !currentChamber.isExit()){
				
				//The start cheamber is removed and marked as dequeued
				currentChamber= hexQueue.removeMin();
				currentChamber.markDequeued();
				
				//If it is the exit, the inner and outer loop both break
				if(currentChamber.isExit()){
					totalLength=currentChamber.getDistanceToStart();
					check=true;
					exitFound=true;
					break;
				}
				
				//Next this for loop checks if the current chamber is a dragon, or if it is 
				//adjacent to a dragon
				//If so, it marks the current chamber as dequeued and restarts the inner loop
				
				for(int i=0; i<6; i++){
					if(currentChamber.getNeighbour(i) != null){
						if(currentChamber.getNeighbour(i).isDragon() || currentChamber.isDragon()){
							currentChamber.markDequeued();
							check= true;
							break;
						}
					}
				}
				
				if(check){
					check=false;
					break;
				}
				
				
				//The following loop determines which neighboring hexagon is closer to the exit.
				
				Hexagon neighbor=null;
				for(int i=0; i<=5; i++){
					if(currentChamber.getNeighbour(i)!=null && !currentChamber.getNeighbour(i).isWall() && !currentChamber.getNeighbour(i).isMarkedDequeued()){
						boolean modified=false;
						
						//the distance of each neighbor from the initial chamber is computer and added to a variable
						neighbor=currentChamber.getNeighbour(i);
						int D= 1+ currentChamber.getDistanceToStart();
						
						if(neighbor.getDistanceToStart()>D){
							neighbor.setDistanceToStart(D);
							modified= true;
						}
						neighbor.setPredecessor(currentChamber);
						
						//finally if the neighbor chamber has been enqueued and its distance has been modified, then its priority is 
						//updated.
						//Otherwise, the neighbor is added to the queue, and then marked as enqueued
						if(neighbor.isMarkedEnqueued() && modified==true){
							hexQueue.updatePriority(neighbor, neighbor.getDistanceToStart()+neighbor.getDistanceToExit(theDungeon));
						}
						else if(!neighbor.isMarkedEnqueued()){
							hexQueue.add(neighbor, neighbor.getDistanceToStart()+neighbor.getDistanceToExit(theDungeon));
							neighbor.markEnqueued();
						}
					}
				}
				
				//Sometimes there is no exit, so the program breaks when there is only one item left in the 
				//queue and everything else has been marked as dequeued 
				//The reason it has to be 1 item left is because this if statement is placed at the end of the loop
				//and the last element has not been removed yet
				if(hexQueue.size()==1){
					check=true;
					break;
				}
				
			}
		}
		 //the total length is computed once the exit chamber has been found. It will simply be the distance to the 
		//start of the initial chamber plus one because we also want to count the exit chamber itself.
		//It can be noticed that sometimes the length is shown as one less than the path in the graphic. This is because 
		//when there are two possible paths of equal distance, the program indicates both paths.
		//the length shows the length of only one of those paths. 
		totalLength=totalLength+1;
		if(exitFound){
			System.out.println("The exit was found and the shortest distance was: "+totalLength+" steps");
		}
		
		else{
			System.out.println("Exit was not found");
		}
		
		
		
		
	}

}