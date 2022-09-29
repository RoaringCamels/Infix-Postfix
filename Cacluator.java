import java.util.Scanner;

public class Cacluator {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println(
            "This program will output a Solutions to a postfix or an infix equation.");
        while(true){
            System.out.println(
                    "Would you like to convert a infix to an postfix(1), Evaluate a postfix(2), or Quit (0).");
                    int menu = s.nextInt();
                    if (menu == 1) {
                        Scanner scan = new Scanner(System.in);
                        System.out.println("You have chosen to convert an infix to postfix.");
                        System.out.println("Please enter infix");
                        String infix = scan.nextLine();
                        LinkedStack<String> infixExpression = new LinkedStack<>();
                        String result = infixExpression.convertToPostFix(infix);
                        System.out.println(result);
                        scan.close();
                        s.close();
                        break;

                    } else if (menu == 2) {
                        Scanner scan = new Scanner(System.in);
                        System.out.println("You have chosen to convert an postfix to infix.");
                        System.out.println("Please enter the number of characters in expression");
                        String postfix = scan.nextLine();
                        ResizableArrayStack<Integer> valueStack1 = new ResizableArrayStack<>();
                        int result = valueStack1.evaluatePostFix(postfix);
                        System.out.println(result);
                        scan.close();
                        s.close();
                        break;

                    } else if (menu == 0) {
                        break;
                    }
                    s.close();

        }

    }

}
