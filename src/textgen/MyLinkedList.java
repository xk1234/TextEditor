package textgen;

import java.util.AbstractList;


public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
        head = new LLNode<E>(null);
        tail = new LLNode<E>(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		// TODO: Implement this method
		// add elem to end of list
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        LLNode<E> newNode = new LLNode<E>(element);
        LLNode<E> slast = tail.prev;
        slast.next = newNode;
        tail.prev = newNode;
        newNode.next = tail;
        newNode.prev = slast;
        size += 1;
        return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		// if index out of bounds, throw exception
		LLNode<E> currNode = head;
        int idx = -1;
        if (size == 0 || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        while (idx < index) {
            currNode = currNode.next;
            idx += 1;
        }
        return currNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		LLNode<E> currNode = head;
        int idx = -1;
		if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        while (idx < (index - 1)) {
            currNode = currNode.next;
            idx += 1;
        }
        LLNode<E> newNode = new LLNode<E>(element);
        LLNode<E> nxt = currNode.next;
        currNode.next = newNode;
        nxt.prev = newNode;
        newNode.next = nxt;
        newNode.prev = currNode;
        size += 1;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		// remove elem from idx
		LLNode<E> currNode = head;
        int idx = -1;
        if (size == 0 || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        while (idx < index) {
            currNode = currNode.next;
            idx += 1;
        }
        // now currNode is node to be removed
        LLNode<E> prevNode = currNode.prev;
        LLNode<E> nextNode = currNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        currNode.next = null;
        currNode.prev = null;
        size -= 1;
        return currNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		// set elem at index
		LLNode<E> currNode = head;
        int idx = -1;
        if (size == 0 || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (element == null) {
        	throw new NullPointerException("Element cannot be null");
        }
        while (idx < index) {
            currNode = currNode.next;
            idx += 1;
        }
        LLNode<E> newNode = new LLNode<E>(element);
        LLNode<E> temp = currNode;
        currNode = newNode;
        return temp.data;
	}
	
	public String toString() {
		LLNode<E> currNode = head;
		String res = "";
		int idx =-1;
		while (idx < size) {
            res += currNode.data + "(";
            res += Integer.toString(idx);
            if (currNode.next != null) {
            	res += ")>";
            }
            idx += 1;
            currNode = currNode.next;
        }
		return res;
		
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
