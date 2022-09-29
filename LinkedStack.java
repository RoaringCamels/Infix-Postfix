import java.util.*;


/**
 * A class of stacks whose entries are stored in a chain of nodes.
 */
public class LinkedStack<T> implements StackInterface<T> {
   private Node topNode; // references the first node in the chain.

   public LinkedStack() {
      topNode = null;
   } // end default constructor

   
   /** 
    * @param newEntry
    */
   public void push(T newEntry) {
      Node newNode = new Node(newEntry, topNode);
      topNode = newNode;
   }

   
   /** 
    * @return T
    */
   public T pop() {
      T top = peek();
      topNode = topNode.getNextNode();
      return top;
   } // end pop

   
   /** 
    * @return T
    */
   public T peek() {
      if (isEmpty())
         throw new EmptyStackException();
      else
         return topNode.getData();
   } // end pop

   
   /** 
    * @return boolean
    */
   public boolean isEmpty() {
      return topNode == null;
   } // end isEmpty

   public void clear() {
      topNode = null;
   } // end clear

   
   /** 
    * @param nextChar
    * @return int
    */
   private int pemdas(char nextChar) {
      if (nextChar == '+')
         return 2;
      else if (nextChar == '-')
         return 2;
      else if (nextChar == '/')
         return 1;
      else if (nextChar == '*')
         return 1;
      else if (nextChar == '(')
         return 3;
      else if (nextChar == ')')
         return 3;
      else
         return 0;
   }

   
   /** 
    * @param infix
    * @return String
    */
   public String convertToPostFix(String infix) {
      StackInterface<Character> operatorStack = new LinkedStack<>();
      String postfix = "";
      int index = 0;
      while (index < infix.length()) {
         char nextCharacter = infix.charAt(index);
         if (nextCharacter == 'a' || nextCharacter == 'b' || nextCharacter == 'c' || nextCharacter == 'd'
               || nextCharacter == 'e') {
            postfix += nextCharacter;
         }
         else if (nextCharacter == '+' || nextCharacter == '-' || nextCharacter == '*' || nextCharacter == '/') {
            while(!operatorStack.isEmpty() && pemdas(nextCharacter) >= pemdas(operatorStack.peek())) 
         } 
         else if (nextCharacter == '+' || nextCharacter == '-' || nextCharacter == '*' || nextCharacter == '/') {
            while (!operatorStack.isEmpty() && pemdas(nextCharacter) >= pemdas(operatorStack.peek())) {
               postfix += operatorStack.peek();
               operatorStack.pop();
            }
            operatorStack.push(nextCharacter);
         } 
         else if (nextCharacter == '(') {
            operatorStack.push(nextCharacter);
         } 
         else if (nextCharacter == ')') {
            char topOperator = operatorStack.pop();
            while (topOperator != '(') {
               postfix += topOperator;
               topOperator = operatorStack.pop();
            }
         }
         System.out.println(postfix);
         index++;
      }
      while (!operatorStack.isEmpty()) {
         char topOperator = operatorStack.pop();
         postfix += topOperator;
      }
      return postfix;
   }

   private class Node {
      private T data; // entry in stack/
      private Node next; // link to next node

      // Constructors and methods getData, setData, getNextNode, and setNextNode
      private Node(T dataPortion) {
         this(dataPortion, null);
      } // end constructor

      private Node(T dataPortion, Node nextNode) {
         data = dataPortion;
         next = nextNode;
      } // end constructor

      private T getData() {
         return data;
      } // end getData

      private void setData(T newData) {
         data = newData;
      } // end setData

      private Node getNextNode() {
         return next;
      } // end getNextNode

      private void setNextNode(Node nextNode) {
         next = nextNode;
      } // end setNextNode
   } // end Node
} // end LinkedStack
