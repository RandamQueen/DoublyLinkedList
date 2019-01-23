import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author Hannah Keating 
 *  @version 01/10/18 17:35:49
 *  
 *  Assumed for the worst case running time that size = N   
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
    		head = null;
    		tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: Theta(1) 
     *
     * Justification:
     *  An assumption of our system is that basic operations take Theta (1). 
     *  As this function is a simple if statement test, that is independent of the size 
     *  of the DLL, it will take a constant amount of time. 
     *  
     *  Assumption: 
     *  An assumption was made that the head and tail both pointed to null or a node, as
     *  according to the rules of linked lists. 
     */
    public boolean isEmpty()
    {
    		if( head == null ) 
    		{ 
    			return true;
    		}
    	return false;
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: Theta(N) 
     *
     * Justification:
     *  This function calls getSize and getNode. Both of these functions have a worst
     *  case running time of theta(N). As a result, the possible worst case running time is
     *  theta(3N). Due to the rules of asymptotic worst case, we record this as theta(N).  
     *  
     *  Assumption: 
     *  An assumption was made that the head and tail both pointed to null or a node, as
     *  according to the rules of linked lists. 
     */
    public void insertBefore( int pos, T data ) 
    {
    	DLLNode newNode = new DLLNode(data,null,null);
    		if( isEmpty() )
    		{ 
    			head = newNode; 
    			tail = newNode; 
        }
    		else if ( head == tail)
    		{ 
    			DLLNode existingNode = head; 
    			if( pos == 0)
    			{ 
    				existingNode.next = null;
    				existingNode.prev = newNode; 
    				newNode.next = existingNode;
    				head = newNode;
    				tail = existingNode;
    			}
    			else
    			{
    				existingNode.next = newNode; 
    				newNode.prev = existingNode;
    				tail = newNode;
    			}
    		}
    		else //if ( head != tail)
    		{
    			if( pos <= 0)
    			{ 
    				DLLNode oldHeadNode = head; 
    				oldHeadNode.prev = newNode; 
    				newNode.next = oldHeadNode;
    				head = newNode;
    			}
    			else if ( pos >= getSize()) 
    			{ 
    				DLLNode oldTailNode = tail; 
    				oldTailNode.next = newNode; 
    				newNode.prev = oldTailNode; 
    				tail = newNode; 
    			}
    			else 
    			{ 	
    				DLLNode currentNode = getNode(pos);
    				DLLNode prevNode = currentNode.prev;
    				prevNode.next = newNode; 
    				newNode.prev = prevNode; 
    				currentNode.prev = newNode; 
    				newNode.next = currentNode;
    			}
    		}
      return;
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  This function call getNode and returns the data from that Node. 
     *  Since getNode's worst case running time is Theta(N), is means that 
     *  this function is the same as the rest of the function has constant running time. 
     *
     */
    public T get(int pos) 
    {
      T returnData = null; 
      DLLNode requestedNode = getNode(pos);
      if(requestedNode != null )
      {
    	  	returnData = requestedNode.data;
      }
      return returnData;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic running time cost: Theta(N) 
     *
     * Justification:
     *  As we are getting the size and nodes from our DLL, we are looping through
     *  our DLL in this function, we are testing element equal to N. As our DLL gets larger,
     *  the testing times get bigger in conjunction with N. This means our order of growth is equal to N. 
     */
    public boolean deleteAt(int pos) 
    {
    	boolean wasNodeDeleted;
    	int size = getSize();  
    	if( size ==0)
    	{ 
    		wasNodeDeleted = false; 
    	}
    	else if( size == 1)
    	{ 
    		if ( pos == 0)
    		{ 
    			head = null; 
    			tail = null; 
    			wasNodeDeleted = true; 
    		}
    		else 
    		{ 
    			wasNodeDeleted = false;
    		}
    	}
    	else 
    	{ 
    		if ( pos == 0)
    		{ 
    			DLLNode currentHead = head; 
    			DLLNode newHead = currentHead.next;
    			newHead.prev = null; 
    			head = newHead;
    			wasNodeDeleted = true; 
    		}
    		else if( pos >= size || pos <0 )
    		{ 
    			wasNodeDeleted = false;
    		}
    		else if( pos == size -1 ) 
    		{ 
    			DLLNode newTail = getNode(pos -1);
    			newTail.next = null; 
    			tail = newTail; 
    			wasNodeDeleted = true;
    		}
    		else 
    		{ 
    			DLLNode prevNode = getNode( pos-1); 
    			DLLNode nextNode = getNode (pos +1); 
    			prevNode.next = nextNode; 
    			nextNode.prev = prevNode; 
    			wasNodeDeleted = true;
    		}
    	}
    	return wasNodeDeleted;
    }
    
    /**
	 * Reverses the list. If the list contains "A", "B", "C", "D" before the method
	 * is called Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: Theta(N) 
	 *
	 * Justification: 
	 * Within this function there is a while took that goes each element of the DLL. 
	 * This would take Theta(N) time, as the size of the list would affect the rate
	 *  at which it was done. The other operations in this loop only take constant 
	 *  time, so they wouldn't increase the running time of this program. 
	 */
    public void reverse() 
    {
    		int size = getSize();

    		if(size < 2 )
    		{
    			return;
    		}
    		DLLNode newTailNode = head;
    		DLLNode newHeadNode = tail;
    		if (size == 2) 
    		{	
    			newHeadNode.prev = null; 
    			newTailNode.next = null; 
    			newTailNode.prev = newHeadNode;
    			newHeadNode.next = newTailNode;	
    			head = newHeadNode; 
    			tail = newTailNode;
    			return; 
    		} 
    		else 
    		{ 
    			DLLNode currentListNode = head; 
    			DLLNode newListNode;
    			while(currentListNode != null )
    			{
    				newListNode = currentListNode.next; 
    				currentListNode.next = currentListNode.prev;
    				currentListNode.prev = newListNode; 
    				currentListNode = newListNode ; 	
    			}
    			newHeadNode.prev = null; 
    			newTailNode.next = null; 	
    			head = newHeadNode; 
    			tail = newTailNode;

    		}
    }

    /**
	 * Removes all duplicate elements from the list. The method should remove the
	 * _least_number_ of elements to make all elements unique. If the list contains
	 * "A", "B", "C", "B", "D", "A" before the method is called Then it should
	 * contain "A", "B", "C", "D" after it returns. The relative order of elements
	 * in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost: Theta(N^2) 
	 *
	 * Justification:  
	 * Our function call uses a nested while loops. As these loops are based on
	 * the size of the Linked list, the size of the array will affect how long it takes 
	 * the arrays to run. These loops check all the elements of the list against the other
	 * elements after it on the list, we say that it take N^2 time. The call the getSize() 
	 * doesn't affect the running time in this function as it is not the largest factor. 
	 */
	public void makeUnique() {
		DLLNode currentNode = head; 
		DLLNode prevNode = currentNode; 
		while(currentNode != null )
		{
			DLLNode testNode = currentNode.next; 	
			while (testNode != null)
			{
				if (currentNode.data == testNode.data) 
				{
					DLLNode shiftedNode = testNode.next; 
					if(prevNode != null )
					{
						prevNode .next = shiftedNode; 
					}
					if( shiftedNode != null)
					{
						shiftedNode.prev = prevNode; 
					}
					testNode.next = null; 
					testNode.prev= null;
					testNode = shiftedNode; 
				}
				else 
				{ 
					prevNode = testNode; 
					testNode = testNode.next ;
				}
			}
			currentNode = currentNode.next;
			prevNode = currentNode; 
		}
	}

     /** Gets the size of the list. 
      * @Param none 
      * @returns. Returns the size of the DLL 
      * 
      * Worst-case asymptotic running time cost: Theta(N) 
      *
      * Justification:
      *  In the worst case, we will will go into the last else statement,
      *  which contains a while loop. This while loop goes through all the nodes 
      *  of the DDL, so depending on the size of the list, the time will increase. 
      *    	
      */

     public int getSize()
     { 
    	 	int size =0; 
    	 	if( isEmpty())
    	 	{ 
    	 		 size =0;
    	 	}
    	 	else if ( head == tail)
    	 	{ 
    	 		size = 1;
    	 	}
    	 	else 
    	 	{ 
    	 		size = 1;
    	 		DLLNode currentNode = head;
    	 		while(currentNode.next != null )
    	 		{ 
    	 			size++; 
    	 			currentNode = currentNode.next;
    	 		}
    	 	}	
    	 	return size; 
     }
  
  /** Gets the Node located at the position passed. 
      * @Param int pos - the position of node to be returned   
      * @returns. DLLNode requestedNode - the node located at the pos in the list  
      * 
      * Worst-case asymptotic running time cost:  Theta(N) 
      *
      * Justification:
      *  In the worst case, we will will go into the last else statement,
      *  which contains a while loop. In the worst case, the position will be the 
      *  second last element, so that will mean the entire of the list will be looped through (N-2). 
      *  In accordances with the rules of meaning worst cases, we only record the highest order, which
      *  gives us N.  	
      *    	
      */
         
     DLLNode getNode(int pos)
     { 
    	 	DLLNode requestedNode = null; 
    	 	int sizeOfDLL = getSize(); 
    	 	if( sizeOfDLL == 0 )
    	 	{ 
    	 		return requestedNode; 
    	 	}
    	 	if( pos == 0)
    	 	{ 
    	 		requestedNode = head;
    	 	}
    	 	else if(pos >= sizeOfDLL || pos < 0)
    	 	{ 
    	 		return null; 
    	 	}
    	 	else 
    	 	{ 
    	 		int index =0; 
    	 		DLLNode currentNode = head; 
    	 		while(index != pos  )
    	 		{
    	 			currentNode = currentNode.next;
    	 			index++;
    	 		}
    	 		requestedNode = currentNode; 	
    	 	}	
    	 	return requestedNode; 
     }
     
     
    /*----------------------- STACK API 
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: Theta(N) 
     *
     * Justification:
     * This functions use 2 other functions in our program to run. Both these methods have
     * a running time of Theta(N), this would equal a running time of 2N. Due to the laws of calculating 
     * the worst case running time, we ignore the 3 and say the cost would be theta(N). 
     */
     
    public void push(T item) 
    {
    		int size = getSize(); 
    		insertBefore(size,item);
    }
    

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(N) 
     *
     * Justification:
	 *	This functions use 3 other functions in our program to run. Both these methods have
     * a running time of Theta(N), this would equal a running time of 3N. Due to the laws of calculating 
     * the worst case running time, we ignore the 3 and say the cost would be theta(N). 
     */
    public T pop() 
    {
    		T returnData = null;
    		int size = getSize(); 
    		if(size != 0 )
    		{
    			int lastNodeIndex = size -1; 
    			DLLNode returnNode = getNode(lastNodeIndex); 
    			returnData = returnNode.data; 
    			deleteAt(lastNodeIndex); 
    		}
    		return returnData; 
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */
 
    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: Theta(1) 
     *
     * Justification:
     *  This is a simple call to the insertBefore methods. In our insertBefore, if the position 
     *  of the new node is 0 aka it's a new head position, we take a short cut in our code. Meaning,
     *  that we do not call our more expensive methods to find the position of the for the new node, 
     *  we just put in into the DLL> This means our running time for enqueue is constant. 
     */
    
    public void enqueue(T item) 
    {
    		insertBefore(0,item);
    }

     /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an enqueue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(N) 
     *
     * Justification:
     *  This function works in a very similar way to the pop function. This function use 3 other functions
     *   in our program to run. These 3 methods have a running time of Theta(N), this would equal a running 
     *   time of 3N. Due to the laws of calculating the worst case running time, we ignore the 
     *   3 and say the cost would be theta(N). 
     */
    public T dequeue() 
    {
    		T returnData = null;
    		int size = getSize(); 
    		if(size != 0 )
    		{
    			int lastNodeIndex = size -1; 
    			DLLNode returnNode = getNode(lastNodeIndex); 
    			returnData = returnNode.data; 
    			deleteAt(lastNodeIndex); 
    		}
    		return returnData; 
    }	
    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}


