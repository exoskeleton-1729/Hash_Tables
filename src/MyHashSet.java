// Implements a singly-linked list.

public class MyHashSet {
	private ListNode[] buckets;
	private int objectCount;
	private double loadFactorLimit;

	// Constructor: creates an empty hash set with default parameters
	public MyHashSet()
	{
		this.buckets = new ListNode[10];
		this.objectCount = 0;
		this.loadFactorLimit = 0.75;
	}

	// Constructor: creates a hash set with the given initial bucket size and load factor limit
	public MyHashSet(int bucketCount, double loadFactorLimit)
	{
		this.buckets = new ListNode[bucketCount];
		this.objectCount = 0;
		this.loadFactorLimit = loadFactorLimit;
	}

	// Return a pointer to the bucket array
	public ListNode[] getBuckets() {
		return this.buckets;
	}

	// Returns true if this set is empty; otherwise returns false.
	public boolean isEmpty()
	{
		return (objectCount == 0);
	}

	// Returns the number of elements in this set.
	public int size()
	{
		return objectCount;
	}
	
	// Return the bucket index for the object
	public int whichBucket(Object obj) {
		return (0x7FFFFFFF & obj.hashCode()) % this.buckets.length;
	}

	// Returns the current load factor (objCount / buckets)
	public double currentLoadFactor() {
		return (double)objectCount/buckets.length;
	}


	// Return true if the object exists in the set, otherwise false.
	// Use the .equals method to check equality.
	public boolean contains(Object obj) {
		if(isEmpty())
			return false;
		else
		{
			int index = whichBucket(obj.hashCode());
        	ListNode node = buckets[index];
        	while (node != null) {
        		if (node.getValue().equals(obj))
        			return true;
            	node = node.getNext();
        	}
        }
        return false;
	}

	// Add an object to the set.
	// If the object already exists in the set you should *not* add another.
	// Return true if the object was added; false if the object already exists.
	// If an item should be added, add it to the beginning of the bucket.
	// After adding the element, check if the load factor is greater than the limit.
	// - If so, you must call rehash with double the current bucket size.
	public boolean add(Object obj) {
		int index = whichBucket(obj.hashCode());
        if(contains(obj))
        	return false;
        ListNode nodey = new ListNode(obj);
        nodey.setNext(buckets[index]);
        buckets[index] = nodey;
        objectCount++;
        if(currentLoadFactor() > loadFactorLimit)
        	rehash(buckets.length*2);
        return true;
	}

	// Remove the object.  Return true if successful, false if the object did not exist
	public boolean remove(Object obj) {
		int index = whichBucket(obj.hashCode());
        ListNode node = buckets[index];
        ListNode prevNode = null;
        while (node != null) {
            if (node.getValue().equals(obj))
            {
                if (prevNode == null)
                    buckets[index] = node.getNext(); 
                else
                    prevNode.setNext(node.getNext());
                objectCount--;
                return true;
            }
            prevNode = node;
            node = node.getNext();
        }
        return false;
	}

	// Rehash the set so that it contains the given number of buckets
	// Loop through all existing buckets, from 0 to length
	// rehash each object into the new bucket array in the order they appear on the original chain.
	public void rehash(int newBucketCount) {
		ListNode [] nodes = buckets; 
        buckets = new ListNode[newBucketCount];
        for (int i = 0; i < 2 * nodes.length; i++)
        	buckets[i] = null;
        
        objectCount = 0;
        for (int i = 0; i < nodes.length; i++) 
        {
        	ListNode node = nodes[i];
        	while(node != null)
        	{
        		add(node.getValue());
        		node = node.getNext();
        	}
        }

	}

	// The output should be in the following format:
	// [ #1, #2 | { b#: v1 v2 v3 } { b#: v1 v2 } ]
	// #1 is the objCount
	// #2 is the number of buckets
	// For each bucket that contains objects, create a substring that indicates the bucket index
	// And list all of the items in the bucket (in the order they appear)
	public String toString() 
	{
		ListNode node = null;
        StringBuilder sb = new StringBuilder();
        sb.append("[ " + objectCount + ", " + buckets.length + " | ");
        for (int i = 0; i < buckets.length; i++)
        {
            if (buckets[i] != null)
            {
                node = buckets[i];
                sb.append("{ b" + i + ": ");
                sb.append(node.getValue().toString());
                while (node.getNext() != null) {
                    node = node.getNext();
                    sb.append(" " + node.getValue().toString());
                }
                sb.append(" } ");
            }
        }
        sb.append("]");
        return sb.toString();
	}

}
