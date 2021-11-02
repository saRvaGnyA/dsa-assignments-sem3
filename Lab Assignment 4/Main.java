package com.company;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

    // function for testing
    static <E> void display(LinkedPositionalList<E> posList) {
        if (posList.isEmpty()) {
            System.out.println("Positional list is empty" + "\n");
        } else {
            System.out.println("Elements of the positional list are: ");
            // iterate through the positional list using element iterators and display the elements
            Iterator<E> it = posList.iterator();
            while (it.hasNext()) {
                System.out.print(it.next() + " --> ");
            }
            System.out.println("\nSize of the positional list is: " + posList.size());
            System.out.println("First node is :" + posList.first().getElement());
            System.out.println("Last node is :" + posList.last().getElement() + "\n");
        }
    }

    // Test application
    public static void main(String[] args) {
        LinkedPositionalList<String> pl = new LinkedPositionalList<>();
        // create an instance of a positional list of strings
        System.out.println("Creating the positional list");
        display(pl);

        // add first and last nodes
        System.out.println("!----------------------------->");
        System.out.println("Adding the first and last nodes");
        Position<String> n_first = pl.addFirst("Mumbai"); // first node
        Position<String> n_last = pl.addLast("Delhi"); // last node
        display(pl);

        // add some nodes
        System.out.println("!----------------------------->");
        System.out.println("Adding the second and second-last nodes");
        Position<String> n_sec = pl.addAfter(n_first, "Madras"); // second node
        Position<String> n_sec_last = pl.addBefore(n_last, "Kolkata"); // second last node
        display(pl);

        // modify a node
        System.out.println("!----------------------------->");
        System.out.println("Modifying the second node");
        String sec_old = pl.set(n_sec, "Chennai");
        System.out.println("Value at 2nd node before modification was: " + sec_old);
        display(pl);

        // removing the second last node
        System.out.println("!----------------------------->");
        System.out.println("Removing the second-last node");
        String removed_sec_last = pl.remove(n_sec_last);
        System.out.println("Element at the removed 2nd last node was: " + removed_sec_last);
        display(pl);

        // adding new head and tail nodes
        System.out.println("!----------------------------->");
        System.out.println("Adding new head and tail node");
        Position<String> new_first = pl.addFirst("Ahmedabad");
        Position<String> new_last = pl.addLast("Jaipur");
        display(pl);

        // adding some more nodes in b/w without storing them
        System.out.println("!----------------------------->");
        System.out.println("Adding some more nodes");
        pl.addAfter(new_first, "Lucknow");
        pl.addBefore(new_last, "Pune");
        display(pl);

        // storing the nodes previously not stored using accessor methods
        System.out.println("!----------------------------->");
        Position<String> stored_sec = pl.after(new_first);
        Position<String> stored_sec_last = pl.before(new_last);
        System.out.println("Nodes previously added were:");
        System.out.println(stored_sec.getElement() + " and " + stored_sec_last.getElement() + "\n");

        // using the positions iterable
        System.out.println("!----------------------------->");
        System.out.println("Printing the positional list using an iterable and for-each loop");
        Iterable<Position<String>> posIterable = pl.positions();
        // an iterable can support a for-each loop in Java
        for (Position<String> p : posIterable) {
            System.out.print(p.getElement() + " --> ");
        }
        System.out.println("\n");

        // searching for a node element using iterable
        System.out.println("!----------------------------->");
        System.out.println("Searching the positional list for a node");
        System.out.println("Enter the city you want to search: ");
        Scanner s = new Scanner(System.in);
        String to_search = s.nextLine();
        int pos = 0;
        for (Position<String> p : posIterable) {
            pos++;
            if (p.getElement().equals(to_search)) {
                System.out.println("City " + to_search + " found at position " + pos);
                return;
            }
        }
        System.out.println("City not found");
    }
}
