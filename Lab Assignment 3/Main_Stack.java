package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // ask the user for the size of the stack
        Scanner s = new Scanner(System.in);
        System.out.println("Enter size of the stack: ");
        int size_of_stack = s.nextInt();
        // create an instance of the implementation class with integers as the type
        ArrayStack<Integer> st = new ArrayStack<Integer>(size_of_stack);
        boolean flag = true;
        // menu-driven options
        while (flag) {
            // display options
            System.out.println("Enter any of the following options: ");
            System.out.println("1.Push, 2. Pop, 3. Size, 4. isEmpty(), 5. Top, 6. Display, 0. Exit");
            int option = s.nextInt();
            // check the user input option, and perform the selected operation on the stack
            switch (option) {
                case 0:
                    flag = false;
                    break;
                case 1: {
                    System.out.println("Enter value to push: ");
                    int val = s.nextInt();
                    st.push(val);
                    break;
                }
                case 2: {
                    int popped_element = st.pop();
                    System.out.println("Popped element was: " + popped_element);
                    break;
                }
                case 3: {
                    System.out.println("Size of the stack is: " + st.size());
                    break;
                }
                case 4: {
                    if (st.isEmpty())
                        System.out.println("Stack is empty");
                    else
                        System.out.println("Stack is not empty");
                    break;
                }
                case 5: {
                    System.out.println("Top element of the stack is: " + st.top());
                    break;
                }
                case 6: {
                    st.display();
                    break;
                }
                default:
                    System.out.println("Enter valid option");
            }
        }
    }
}
