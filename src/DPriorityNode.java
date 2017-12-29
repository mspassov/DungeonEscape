/**
 * 250901340
 * mspassov
 * @author Martin
 * @param <T>
 */

public class DPriorityNode<T> {
	private T element;
	private DPriorityNode<T> next;
	private DPriorityNode<T> prev;
	private double priority;
	
	/**
	 * Constructor for the class, takes in an element of type T and sets its priority
	 * @param data
	 * @param prio
	 * @author Martin
	 */
	
	
	public DPriorityNode(T data, double prio){
		next= null;
		prev=null;
		element= data;
		priority=prio;

	}
	
	/**
	 * Constructor creates empty node
	 * @author Martin
	 */
	
	public DPriorityNode(){
		next= null;
		prev=null;
		element= null;
		priority=0;
	}
	
	/**
	 * Method returns the priority for a given element 
	 * @return priority of type double
	 * @author Martin
	 */
	
	public double getPriority(){
		return priority;
	}
	
	/**
	 * Method retrieves an element from a specific note
	 * @return element
	 * @author Martin
	 */
	public T getElement(){
		return element;
	}
	
	/**
	 * Method returns the next node, relative to current one
	 * @return next
	 * @author Martin
	 */
	
	public DPriorityNode<T> getNext(){
		return next;
	}
	
	/**
	 * Method returns the previous node in the queue, relative to the current one
	 * @return prev which is the previous node
	 * @author Martin
	 */
	
	public DPriorityNode<T> getPrev(){
		return prev;
	}
	
	/**
	 * Method sets the element of the next node
	 * @param elem
	 * @author Martin
	 */
	
	public void setElement(T elem){
		element= elem;
	}
	
	/**
	 * Method sets the next node in the queue
	 * @param nextNode
	 * @author Martin
	 */
	
	public void setNext(DPriorityNode<T> nextNode){
		next=nextNode;
	}
	
	/**
	 * Method sets the previous node in the queue
	 * @param prevNode
	 * @author Martin
	 */
	
	public void setPrev(DPriorityNode<T> prevNode){
		prev= prevNode;
	}
	
	/**
	 * Method sets the priority of a node
	 * @param newPrio
	 * @author Martin
	 */
	
	public void setPriority(double newPrio){
		priority= newPrio;
	}
	

}