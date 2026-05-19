/**
 * Stack.java
 * A generic stack implementation using a singly linked list.
 * Created for Project 14, used for undo/redo in Project 15.
 */
public class Stack<T> 
{
    // Internal node class for the stack
    private class StackNode 
	{
        T data;
        StackNode next;

        StackNode(T data) 
		{
            this.data = data;
            this.next = null;
        }
    }

    private StackNode top;
    private int size;

    public Stack() 
	{
        top = null;
        size = 0;
    }

    // Push an item onto the stack
    public void push(T item) 
	{
        StackNode newNode = new StackNode(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop an item from the stack
    public T pop() 
	{
        if (isEmpty()) 
		{
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    // Peek at the top item without removing it
    public T peek() 
	{
        if (isEmpty()) 
		{
            return null;
        }
        return top.data;
    }

    // Check if the stack is empty
    public boolean isEmpty() 
	{
        return top == null;
    }

    // Get the current size of the stack
    public int size() 
	{
        return size;
    }
}