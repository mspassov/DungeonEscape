import testPackage.InvalidElementException;

/**
 * 250901340
 * mspassov
 * @author Martin
 * @param <T>
 */


public class DLinkedPriorityQueue<T> implements PriorityQueueADT<T> {
	private DPriorityNode<T> front;
	private DPriorityNode<T> rear;
	private int count;

	/**
	 *The first constructor does not take any parameters
	 *@author Martin
	 */
	public DLinkedPriorityQueue() {
		count = 0;
		front = null;
		rear = null;
	}
	
	/**
	 * Method takes in an element of type T and adds it to the priority queue
	 * @param an element element of type T
	 * @param element priority of type double 
	 * @author Martin 
	 */
	
	public void add(T element, double priority) {
		
		//First method creates a temporary node, and then it checks if the queue is empty
		//If it is, node becomes the front
		//If the queue is not empty, then teh new node is linked to the back
		
		DPriorityNode<T> node = new DPriorityNode<T>(element, priority);
		if (isEmpty()) {
			front = node;
			rear = node;
		} else {
			rear.setNext(node);
			node.setPrev(rear);
			rear = node;
		}
		count++;
	}
	
	/**
	 * Priority of a given element is updated 
	 * @param an element is passed whose priority has to be updated
	 * @param newPriority 
	 * @author Martin
	 * 
	 */
		
	public void updatePriority(T element, double newPriority) throws InvalidElementException{
		int found=0;
		//temporary node is created and assigned to equal front
		
		DPriorityNode<T> temp= new DPriorityNode<T>();
		temp=front;		
		
		//the loop through the queue and determines if a node in the queue matches the node parameter
		//if it does the it updates the priority of the given node
		for(int i=0; i<count; i++){
			if(temp==null){
				break;
			}
			if(temp.getElement().equals(element)){
				temp.setPriority(newPriority);
				found=1;
			}
			
			temp=temp.getNext();
		}
		
		if(found==0){
			throw new InvalidElementException("Element not in Queue");
		}
	}
	
	
	/**
	 * 
	 * Method removes the element with the smallest priority from the queue
	 * @return targetElem returns the element of highest priority from the queue
	 * @author Martin
	 */
	
	public T removeMin() throws EmptyPriorityQueueException{
		T targetElem=null;
		if(count==0){
			throw new EmptyPriorityQueueException("Priority queue is empty");
		}
		
		//two nodes are created. temp is used to traverse the queue, and smallTemp is used
		// to store the node with the smallest priority
		else{
			DPriorityNode<T> temp= new DPriorityNode<T>();
			DPriorityNode<T> smallTemp= new DPriorityNode<T>();
			temp=front;
			double smallestPrio=temp.getPriority();
			temp=temp.getNext();

			//loop runs through the queue to determine the node with the smallest priority 
			for(int i=0; i<count; i++){
				if(temp==null){
					break;
				}
				if(temp.getPriority()<smallestPrio){
					smallTemp=temp;
					smallestPrio=temp.getPriority();
				}
				temp=temp.getNext();
				
			}
			//First case is if that node is in the front. If it is, it must be removed
			
			if(front.getPriority()==smallestPrio){
				T smallElem= front.getElement();
				front=front.getNext();
				count--;
				return smallElem;
			}
			
			//Second case is if the target node is in rear. In that case, it again must be removed
			
			if(rear.getPriority()==smallestPrio){
				T smallElem= rear.getElement();
				rear=rear.getPrev();
				rear.setNext(null);
				count--;
				return smallElem;
			}
			
			//Third case, is if teh target node is in the middle. If that's true, then teh traverser
			//goes through the loop and removes it
			
			DPriorityNode<T> traverser= new DPriorityNode<T>();
			traverser=front;
			
			for(int i=0; i<count; i++){
				if(traverser==null){
					break;
				}
				
				if(traverser.getPriority()==smallestPrio){
					targetElem=traverser.getElement();
					traverser.getPrev().setNext(traverser.getNext());
					traverser.getNext().setPrev(traverser.getPrev());
					count--;
					break;
				}
				
				traverser=traverser.getNext();
			}
		}
		
		//finally the node with the smallest priority is returned
		
		return targetElem;	
			
			
		}
		
	/**
	 * Checks to see if the queue is empty
	 * @author Martin
	 */
	public boolean isEmpty(){
		if(count==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Method determines the size of the queue
	 * @author Martin
	 * @return count of type int
	 * @author Martin
	 */
	
	public int size(){
		return count;
	}
	
	/**
	 * Method returns string representation of the queue
	 * @author Martin
	 * @return s of type String 
	 */
	
	public String toString(){
		DPriorityNode<T> temp= new DPriorityNode<T>();
		temp=front;
		String s="";
		for (int i=0; i<count; i++){
			if(temp==null){
				break;
			}
			
			s=s+temp.getElement();
			temp=temp.getNext();
		}
		
		return s;
	}
	
}
	
	
	
	
	
	
	