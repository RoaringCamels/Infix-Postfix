import java.util.Arrays;
import java.util.EmptyStackException;

public class ResizableArrayStack<T> implements StackInterface<T> {
   private T[] stack; // Array of stack entries
   private int topIndex; // index of top entry
   private boolean integrityOK = false;
   private static final int DEFAULT_CAPACITY = 50;
   private static final int MAX_CAPACITY = 10000;

   public ResizableArrayStack() {
      this(DEFAULT_CAPACITY);
   } // end default constructor

   public ResizableArrayStack(int initialCapacity) {
      integrityOK = false;
      checkCapacity(initialCapacity);

      // The cast is safe because the new array contains null entries.
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[]) new Object[initialCapacity];
      stack = tempStack;
      topIndex = -1;
      integrityOK = true;
   } 
   /** 
    * @param newEntry
    */
   // end constructor

   @Override
   public void push(T newEntry) {
      checkIntegrity();
      ensureCapacity();
      stack[topIndex + 1] = newEntry;
      topIndex++;
   }// end push
   
   /** 
    * @return T
    */
   @Override
   public T pop() {
      checkIntegrity();
      if (isEmpty()) {
         throw new EmptyStackException();
      } else {
         T top = stack[topIndex];
         stack[topIndex] = null;
         topIndex--;
         return top;
      }
   }
   /** 
    * @return T
    */
   // end pop

   @Override
   public T peek() {
      checkIntegrity();
      if (isEmpty()) {
         throw new EmptyStackException();
      } else {
         return stack[topIndex];
      }
   }
   /** 
    * @return boolean
    */
   // end peek

   @Override
   public boolean isEmpty() {
      return topIndex < 0;
   }// end isEmpty

   @Override
   public void clear() {
      checkIntegrity();
      while (topIndex > -1) {
         stack[topIndex] = null;
         topIndex--;
      }
   }// end clear

   // doubles the size of the stack if it is full
   private void ensureCapacity() {
      if (topIndex >= stack.length - 1) // if array is full, double its size
      {
         int newLength = 2 * stack.length;
         checkCapacity(newLength);
         stack = Arrays.copyOf(stack, newLength);
      } // end ensureCapacity
   }

   // throws an exception if not initialized
   private void checkIntegrity() {
      if (!integrityOK)
         throw new SecurityException("ArrayBag object is corrupt.");
   }
   /** 
    * @param capacity
    */
   // end checkIntegrity

   // Throws an exception if the client requests a capacity that is too large.
   private void checkCapacity(int capacity) {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException(
               "Attempt to create a bag whose capacity exceeds " + "allowed maximum of " + MAX_CAPACITY);
   }
   /** 
    * @param postfix
    * @return int
    */
   // end checkCapacity

   public int evaluatePostFix(String postfix) {
      ResizableArrayStack<Integer> valueStack = new ResizableArrayStack<>();// makes a new object named valueStack
      for (int c : postfix.toCharArray()) {// as long as there is a char in the parameter
         if (Character.isDigit(c)) {// if the char is a digit it will
            valueStack.push(c);// push it onto the stack
            System.out.println(valueStack.peek()); // can remove after debugging
         } else if (c == '+' || c == '-' || c == '*' || c == '/'// if it is not a digit
               || c == '^') {
            int operandTwo = valueStack.pop();// pop the top of the stack
            int operandOne = valueStack.pop();// pop the top of the stack again
            int result = asciiToNum(c, operandTwo, operandOne);// convert the text to numbers and perform the operation
            System.out.println("\nResult: " + result);// can remove after debugging
            valueStack.push((result + '0'));// push the result
         }
      }
      System.out.println("\nEvaluation of Postfix: ");
      return valueStack.peek() - 48;
   }

   
   /** 
    * @param c
    * @param opTwo
    * @param opOne
    * @return int
    */
   public static int asciiToNum(int c, int opTwo, int opOne) {
      int result = 0;
      opOne -= 48;
      opTwo -= 48;
      switch (c) {
         case '+':
            result = opOne + opTwo;
            break;
         case '-':
            result = opOne - opTwo;
            break;
         case '*':
            result = opTwo * opOne;
            break;
         case '/':
            result = opOne / opTwo;
            break;
         case '^':
            result = opOne * opOne;
            break;
      }
      System.out.println("\nResult of the operation: " + result);
      return result;
   }// end asciiToNum
} // end ResizableArrayStack
