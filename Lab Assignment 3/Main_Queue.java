package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // input the size of the queue from the user
        Scanner s = new Scanner(System.in);
        System.out.println("Enter size of the queue: ");
        int size_of_queue = s.nextInt();
        // create an instance of the implementation class with Integer type
        ArrayQueue<Integer> q = new ArrayQueue<Integer>(size_of_queue);
        boolean flag = true;
        // menu-driven operations
        while (flag) {
            // display options to the user and ask for input
            System.out.println("1.Size, 2.isEmpty(), 3.Enqueue, 4.First, 5.Dequeue, 6.Display, 0.Exit");
            int option = s.nextInt();
            // check the user input option and perform the selected operation
            switch (option) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    System.out.println("Size of the queue is: " + q.size());
                    break;
                case 2:
                    if (q.isEmpty())
                        System.out.println("Queue is empty");
                    else
                        System.out.println("Queue is not empty");
                    break;
                case 3: {
                    System.out.println("Enter element to enqueue");
                    int el = s.nextInt();
                    q.enqueue(el);
                    break;
                }
                case 4:
                    System.out.println("First element is " + q.first());
                    break;
                case 5:
                    System.out.println("Dequeued element is " + q.dequeue());
                    break;
                case 6:
                    q.display();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
