import testPackage.InvalidElementException;

public class TestPriorityQueue {

	public static void main(String[] args) {
		DLinkedPriorityQueue<String> prioQueue = new DLinkedPriorityQueue<String>();
		boolean testPassed;
		String s;
		
		// Test 1: size, isEmpty, removeMin
		// --------------------------------
		testPassed = true;
		try {
			if (!prioQueue.isEmpty()) testPassed = false;
			if (prioQueue.size() != 0) testPassed = false;
			s = prioQueue.removeMin();
			System.out.println("Test 1 failed");
		}
		catch (EmptyPriorityQueueException e) {
			if (testPassed) System.out.println("Test 1 passed");
			else System.out.println("Test 1 failed");
		}
		catch (Exception e) {
			System.out.println("Test 1 failed");
		}
		
		// Test 2: add, removeMin, updateMin.
		// -----------------------------------
		testPassed = true;
		prioQueue.add("data1",1.0);
		System.out.println("added");
		try {
			prioQueue.updatePriority("data1",2.0);
			s = prioQueue.removeMin();
			if (!s.equals("data1")) testPassed = false;
		}
		catch (Exception e) {
			testPassed = false;
		}
		
		try {
			prioQueue.updatePriority("data0",1.0);
			testPassed = false;
		}
		catch (InvalidElementException e) {
		}
		catch (Exception e) {
			testPassed = false;
		}
		
		if (testPassed) System.out.println("Test 2 passed");
		else System.out.println("Test 2 failed");
		
		// Test 3: add removeMin, size
		// ---------------------------
		prioQueue = new DLinkedPriorityQueue<String>();
		testPassed = true;
		try {
			for (int i = 0; i < 1000; ++i) 
				prioQueue.add("data"+i,(double)((i+1) % 10)+(double)i/1000.0);
			
			for (int i = 0; i < 20; ++i) {
				s = prioQueue.removeMin();
				if (!s.equals("data"+(9+i*10))) testPassed = false;
			}
			
			if (prioQueue.size() != 980) testPassed = false;
		}
		catch (Exception e) {
			testPassed = false;
		}
		
		if (testPassed) System.out.println("Test 3 passed");
		else System.out.println("Test 3 failed");

		// Test 4: add, updateMin
		// ----------------------
		prioQueue = new DLinkedPriorityQueue<String>();
		testPassed = true;
		try {
			for (int i = 1000; i > 0; --i) 
				prioQueue.add("data"+i,(double)((i+1) % 10)+(double) i/1000.0);
			
			for (int i = 0; i < 20; ++i) {
				prioQueue.updatePriority("data"+(9+i*10),1.0+i); 
			}
			
			s = prioQueue.removeMin();
			if (!s.equals("data209")) testPassed = false;
		}
		catch (Exception e) {
			testPassed = false;
		}
		
		try {
			prioQueue.updatePriority("data0",0.0);
			testPassed = false;
		}
		catch (InvalidElementException e) {
		}
		
		if (testPassed) System.out.println("Test 4 passed");
		else System.out.println("Test 4 failed");

		// Test 5: toString
		// ----------------
		testPassed = true;
		try {
			s = prioQueue.toString();
			for (int i = 1000; i > 0; --i)
				if (i != 209 && !s.contains("data"+i)) testPassed = false;
		}
		catch (Exception e) {
			testPassed = false;
		}
		
		if (testPassed) System.out.println("Test 5 passed");
		else System.out.println("Test 5 failed");
	}

}
