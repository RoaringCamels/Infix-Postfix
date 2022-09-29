import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        ResizableArrayStack<Integer> valueStack6 = new ResizableArrayStack<>();
        int result1 = valueStack6.evaluatePostFix("53-2*6+");
        System.out.println(result1);
    }

    @Test
    public void convertToPostFix() {
        LinkedStack<String> infixExpression = new LinkedStack<>();
        String result = infixExpression.convertToPostFix("a+b*c");
        assertEquals("abc*+", result);
    }

    @Test
    public void convertToPostFix2() {
        LinkedStack<String> infixExpression = new LinkedStack<>();
        String result = infixExpression.convertToPostFix("a*b/(c-a)+d*e");
        assertEquals("ab*ca-/de*+", result);
    }

    @Test
    public void evaluatePostFix() {
        ResizableArrayStack<Integer> valueStack1 = new ResizableArrayStack<>();
        int result = valueStack1.evaluatePostFix("64+5*2/");
        assertEquals(25, result);
    }

    @Test
    public void evaluatePostFix2() {
        ResizableArrayStack<Integer> valueStack2 = new ResizableArrayStack<>();
        int result = valueStack2.evaluatePostFix("53-2*6+");
        assertEquals(10, result);
    }
}