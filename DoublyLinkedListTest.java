import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  Hannah Keating 
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the isEmpty works
     */
    @Test
    public void testIsEmpty()   
    { 
    	  boolean expectedResult = true; 
    	  DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	  assertEquals("Tests isEmpty() to see if it returns true for an empty List",expectedResult, testDLL.isEmpty());
    	  testDLL.insertBefore(0,4);
    	  expectedResult = false; 
    	  assertEquals("Tests isEmpty() to see if it returns false for a list with elements",expectedResult, testDLL.isEmpty());
    }
    
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        assertEquals( "Checking insertBefore to a list containing 1 elements at position 0", "1", testDLL.toString() );
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    /**
     * Check if the get() works
     */
    @Test
    public void testGet()
    {
    		Integer expectedValue = null;
    		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    		assertEquals("Return null if there is no node to return info from",expectedValue,testDLL.get(0));
    		testDLL.insertBefore(0, 1);
    		assertEquals("Return null if node outside the list size",expectedValue,testDLL.get(7));
    		expectedValue = 1; 
    		assertEquals("Return 1 if there the correct node has been accessed"
    				+ " for a list with 1 element ",expectedValue,testDLL.get(0));
    		testDLL.insertBefore(1, 5);
    		expectedValue = 5; 
    		assertEquals("Return 5 if there the correct node has been accessed"
    				+ " for a list with 2 or more elements",expectedValue,testDLL.get(1));
    }
   
    /**
     * Check if the deleteAt() works
     */
    @Test
    public void testDeleteAt()
    {
    		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    		assertFalse("Returns false if trying to delete a node if the List is empty", testDLL.deleteAt(0));
    		testDLL.insertBefore(0, 5);
    		assertFalse("Returns False if delete at a poisition greather than the size of the List"
    				+ " when the lis has one elementt ",testDLL.deleteAt(3));
    		assertTrue(" Return true if successfully delete a head node in a one element list",
    				testDLL.deleteAt(0));
    		testDLL.insertBefore(0, 1); 
    		testDLL.insertBefore(1, 2);
    		testDLL.insertBefore(2, 3);
    		testDLL.insertBefore(3, 4);
    		testDLL.insertBefore(4, 5);
    		
    		assertFalse("Returns False if delete at a poisition greather than the size of the List"
    				+ " when the lis has more than one elementt ",testDLL.deleteAt(5));
    		assertTrue(" Return true if successfully delete a tail node in a multi element list",
    				testDLL.deleteAt(4));
       	assertTrue(" Return true if successfully delete a middle node in a multi element list",
    				testDLL.deleteAt(2));
    		assertTrue(" Return true if successfully delete a head node in a multi element list",
    				testDLL.deleteAt(0));
    		assertFalse("Returns False if delete at a poisition less than 0 ",testDLL.deleteAt(-5));
    }
    
    /**
     * Check if the reverse() works
     */
    @Test
    public void testReverse()
    {
    		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    		testDLL.reverse();
    		assertEquals( "Checking reverse() with a list with no elements", "", testDLL.toString());
    		testDLL.insertBefore(0, 1);
    		testDLL.reverse();
    		assertEquals( "Checking reverse() with a list with one elements", "1", testDLL.toString());
    		testDLL.insertBefore(1, 2);
    		testDLL.reverse();
    		assertEquals( "Checking reverse() with a list with two elements", "2,1", testDLL.toString());
    		testDLL.reverse(); 
    		testDLL.insertBefore(2, 3);
    		testDLL.reverse(); 
    		assertEquals( "Checking to see if List in ordered correctly with a list with  more than two elements",
    				"3,2,1", testDLL.toString());
    		testDLL.reverse();
   		testDLL.insertBefore(3, 4);
   		testDLL.insertBefore(4, 5);
    		assertEquals( "Checking to see if List in ordered correctly with a list with  more than two elements",
    				"1,2,3,4,5", testDLL.toString());
    		testDLL.reverse(); 
    		assertEquals( "Checking reverse() with a list with  more than two elements",
    				"5,4,3,2,1", testDLL.toString());
    }
    
    /**
     * Check if the geSizet() works
     */
    @Test
    public void testGetSize()
    {
    	  int expectedResult = 0;
    	  DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	  assertEquals("Tests getSize returns 0 for an empty list",expectedResult, testDLL.getSize());
    	  expectedResult =1; 
    	  testDLL.insertBefore(0, 5);
    	  assertEquals("Tests getSize returns 1 for a list with 1 element",expectedResult, testDLL.getSize());
    	  expectedResult =2; 
    	  testDLL.insertBefore(0, 5);
    	  assertEquals("Tests getSize returns the correct size for a list with more than 1 node",
    			  expectedResult, testDLL.getSize());
    }
    
    /**
     * Check if the makeUnique() works
     */
    @Test
    public void testMakeUnique()
    { 
  	  DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
  	testDLL.insertBefore(0, 1);
	testDLL.makeUnique(); 
	assertEquals( "Checking reverse() with a list with two elements", "1", testDLL.toString());
  	testDLL.insertBefore(1, 2);
	testDLL.makeUnique(); 
	assertEquals( "Checking reverse() with a list with two unqiue elements", "1,2", testDLL.toString());
	testDLL.insertBefore(2, 2);
	testDLL.insertBefore(3, 3);
	testDLL.insertBefore(3, 1);
	testDLL.insertBefore(5, 4);
	testDLL.insertBefore(6, 2);
	assertEquals( "Checking list before printing to ensure correct order",
			"1,2,2,1,3,4,2", testDLL.toString());
	testDLL.makeUnique(); 
	assertEquals( "Checking list before printing to ensure correct order",
			"1,2,3,4", testDLL.toString());
    }
    
    /**
     * Check if the get() works
     */
    @Test
    public <DLLNode> void testGetNode()
    {
    	  DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	  assertNull(" Tests if null is return a node is requested from an empty list", testDLL.getNode(0));
    	  testDLL.insertBefore(0, 5);
    	  assertNull(" Tests if null is return if requested a position greater "
    	  		+ "than size of List", testDLL.getNode(5));
    	  assertNull(" Tests if null is return if requested a  negative position ", testDLL.getNode(-1));
    }

    /**
     * Check if the the stack API worked correctly. 
     */
    @Test
    public <DLLNode> void testStack()
    { 
    		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>(); 
    		testDLL.push(1);
    		testDLL.push(2);
    		testDLL.push(3);
    		assertEquals( "Checking list to see if 3 elements where correctly pushed to list",
    				"1,2,3", testDLL.toString());
    		Integer expectedResult = 3; 
    		assertEquals( "Checking if pop operation was successful",expectedResult, testDLL.pop());
    		assertEquals( "Checking list to see if element was deleted correctly",
    				"1,2", testDLL.toString());
    		testDLL.pop();
    		testDLL.pop();
    		assertEquals( "Checking list is empty after successful pops ",
    				"", testDLL.toString());
    		expectedResult = null; 
    		assertEquals( "Check if null is returned for a pop call when the list is empty ",
    				expectedResult, testDLL.pop());	
    }
    
    /**
     * Check if the the queue API worked correctly. 
     */
    @Test
    public <DLLNode> void testQueue()
    { 
    		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>(); 
    		testDLL.enqueue(1);
    		testDLL.enqueue(2);
    		testDLL.enqueue(3);
    		assertEquals( "Checking list to see if 3 elements where correctly queued to list",
    				"3,2,1", testDLL.toString());
    		Integer expectedResult = 1; 
    		assertEquals( "Checking if dequeue operation was successful",expectedResult, testDLL.dequeue());
    		assertEquals( "Checking list to see if element was deleted correctly",
    				"3,2", testDLL.toString());
    		testDLL.dequeue();
    		testDLL.dequeue();
    		assertEquals( "Checking list is empty after successful pops ",
    				"", testDLL.toString());
    		expectedResult = null; 
    		assertEquals( "Check if null is returned for a pop call when the list is empty ",
    				expectedResult, testDLL.dequeue());	
    }
}
