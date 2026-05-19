/**
 * Project14_StackImplementation.java
 * Tests the Stack class with two stacks working together:
 * undoStack and redoStack — exactly as Project 15 will use them.
 * 
 * Demonstrates:
 * - Push and pop operations
 * - Peek without removing
 * - Stack overflow/underflow handling
 * - The undo/redo pattern using dual stacks
 * - Stack of Strings (action descriptions) and Stack of Integers
 * 
 * Compile: javac Stack.java Project14_StackImplementation.java
 * Run:     java Project14_StackImplementation
 */
public class Project14_StackImplementation 
{
    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("  PROJECT 14: STACK IMPLEMENTATION       ");
        System.out.println("=========================================\n");

        // ===== TEST 1: Basic Stack Operations =====
        System.out.println("--- Test 1: Basic Push and Pop (Strings) ---");
        Stack<String> actionStack = new Stack<>();

        System.out.println("Pushing actions: 'Add Alice', 'Add Bob', 'Add Charlie'");
        actionStack.push("Add Alice");
        actionStack.push("Add Bob");
        actionStack.push("Add Charlie");

        System.out.println("Stack size: " + actionStack.size());
        System.out.println("Top of stack (peek): " + actionStack.peek());

        System.out.println("\nPopping all actions:");
        while (!actionStack.isEmpty()) 
		{
            System.out.println("  Popped: " + actionStack.pop());
        }
        System.out.println("Stack size after popping all: " + actionStack.size());

        // ===== TEST 2: Pop from Empty Stack =====
        System.out.println("\n--- Test 2: Pop from Empty Stack ---");
        System.out.println("Attempting to pop from empty stack:");
        String result = actionStack.pop();
        System.out.println("Result: " + result);  // Should be null

        // ===== TEST 3: Stack with Integers =====
        System.out.println("\n--- Test 3: Stack with Integers ---");
        Stack<Integer> numberStack = new Stack<>();

        numberStack.push(100);
        numberStack.push(200);
        numberStack.push(300);

        System.out.println("Integer stack size: " + numberStack.size());
        System.out.println("Top: " + numberStack.peek());
        System.out.println("Popped: " + numberStack.pop());
        System.out.println("Popped: " + numberStack.pop());
        System.out.println("Remaining size: " + numberStack.size());
        System.out.println("Popped: " + numberStack.pop());
        System.out.println("Empty? " + numberStack.isEmpty());

        // ===== TEST 4: Undo/Redo Pattern (Critical for Project 15!) =====
        System.out.println("\n=========================================");
        System.out.println("--- Test 4: Undo/Redo Pattern ---");
        System.out.println("=========================================");

        Stack<String> undoStack = new Stack<>();
        Stack<String> redoStack = new Stack<>();

        // Simulate user actions
        System.out.println("\n[User adds students]");
        performAction(undoStack, redoStack, "Added student: Peter Parker");
        performAction(undoStack, redoStack, "Added student: Tony Stark");
        performAction(undoStack, redoStack, "Added student: Steve Rogers");

        System.out.println("\n[Current undo stack (top = most recent)]:");
        printStack(undoStack);

        // Undo one action
        System.out.println("\n[User presses UNDO]");
        undo(undoStack, redoStack);

        System.out.println("\n[After 1 undo:]");
        System.out.print("Undo stack: ");
        printStack(undoStack);
        System.out.print("Redo stack: ");
        printStack(redoStack);

        // Undo another action
        System.out.println("\n[User presses UNDO again]");
        undo(undoStack, redoStack);

        System.out.println("\n[After 2 undos:]");
        System.out.print("Undo stack: ");
        printStack(undoStack);
        System.out.print("Redo stack: ");
        printStack(redoStack);

        // Redo one action
        System.out.println("\n[User presses REDO]");
        redo(undoStack, redoStack);

        System.out.println("\n[After 1 redo:]");
        System.out.print("Undo stack: ");
        printStack(undoStack);
        System.out.print("Redo stack: ");
        printStack(redoStack);

        // Add new action (should clear redo stack)
        System.out.println("\n[User adds new student after undo/redo]");
        performAction(undoStack, redoStack, "Added student: Natasha Romanoff");

        System.out.println("\n[Final state — redo stack cleared by new action:]");
        System.out.print("Undo stack: ");
        printStack(undoStack);
        System.out.print("Redo stack: ");
        printStack(redoStack);

        // ===== TEST 5: Stack of Student IDs (for undo delete) =====
        System.out.println("\n--- Test 5: Stack for Deleted Student IDs ---");
        Stack<Integer> deletedIdStack = new Stack<>();

        deletedIdStack.push(401);
        deletedIdStack.push(402);
        deletedIdStack.push(403);

        System.out.println("Deleted IDs stack size: " + deletedIdStack.size());
        System.out.println("Last deleted ID (peek): " + deletedIdStack.peek());
        System.out.println("Restoring student with ID: " + deletedIdStack.pop());
        System.out.println("Restoring student with ID: " + deletedIdStack.pop());
        System.out.println("Remaining deleted IDs: " + deletedIdStack.size());

        System.out.println("\n=========================================");
        System.out.println("  STACK IMPLEMENTATION — COMPLETE        ");
        System.out.println("=========================================");
    }

    /**
     * Simulates performing an action: pushes to undo stack, clears redo stack.
     * New actions invalidate the redo history.
     */
    public static void performAction(Stack<String> undoStack, Stack<String> redoStack, String action) 
	{
        undoStack.push(action);
        // Clear redo stack when a new action is performed
        while (!redoStack.isEmpty()) 
		{
            redoStack.pop();
        }
        System.out.println("  ACTION: " + action);
    }

    /**
     * Undo: pop from undo stack, push to redo stack.
     */
    public static void undo(Stack<String> undoStack, Stack<String> redoStack) 
	{
        if (undoStack.isEmpty()) 
		{
            System.out.println("  Nothing to undo.");
            return;
        }
        String action = undoStack.pop();
        redoStack.push(action);
        System.out.println("  UNDO: " + action);
    }

    /**
     * Redo: pop from redo stack, push to undo stack.
     */
    public static void redo(Stack<String> undoStack, Stack<String> redoStack) 
	{
        if (redoStack.isEmpty()) 
		{
            System.out.println("  Nothing to redo.");
            return;
        }
        String action = redoStack.pop();
        undoStack.push(action);
        System.out.println("  REDO: " + action);
    }

    /**
     * Helper to print stack contents (top to bottom).
     */
    public static void printStack(Stack<String> stack) 
	{
        if (stack.isEmpty()) 
		{
            System.out.println("[empty]");
            return;
        }
        // We need to create a temp stack to print without destroying original
        Stack<String> temp = new Stack<>();
        System.out.print("[");
        boolean first = true;
        while (!stack.isEmpty()) 
		{
            String item = stack.pop();
            if (!first) System.out.print(", ");
            System.out.print(item);
            first = false;
            temp.push(item);
        }
        System.out.println("]");
        // Restore original stack
        while (!temp.isEmpty()) 
		{
            stack.push(temp.pop());
        }
    }
}