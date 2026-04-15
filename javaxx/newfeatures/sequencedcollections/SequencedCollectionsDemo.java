package javaxx.newfeatures.sequencedcollections;

import java.util.*;

public class SequencedCollectionsDemo {
    public static void main(String[] args) {
        System.out.println("=== SequencedCollection Examples ===");

        // List implements SequencedCollection
        List<String> list = new ArrayList<>(List.of("A", "B", "C", "D"));
        System.out.println("Original list: " + list);
        System.out.println("First element: " + list.getFirst());
        System.out.println("Last element: " + list.getLast());

        // Add to beginning and end
        list.addFirst("Z");
        list.addLast("E");
        System.out.println("After adding first/last: " + list);

        // Remove from beginning and end
        list.removeFirst();
        list.removeLast();
        System.out.println("After removing first/last: " + list);

        // Reverse view
        List<String> reversed = list.reversed();
        System.out.println("Reversed view: " + reversed);

        System.out.println("\n=== SequencedSet Examples ===");

        // LinkedHashSet implements SequencedSet
        LinkedHashSet<String> set = new LinkedHashSet<>(List.of("A", "B", "C", "D"));
        System.out.println("Original set: " + set);
        System.out.println("First element: " + set.getFirst());
        System.out.println("Last element: " + set.getLast());

        // Add to beginning and end
        set.addFirst("Z");
        set.addLast("E");
        System.out.println("After adding first/last: " + set);

        // Remove from beginning and end
        set.removeFirst();
        set.removeLast();
        System.out.println("After removing first/last: " + set);

        System.out.println("\n=== SequencedMap Examples ===");

        // LinkedHashMap implements SequencedMap
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);

        System.out.println("Original map: " + map);
        System.out.println("First entry: " + map.firstEntry());
        System.out.println("Last entry: " + map.lastEntry());
        System.out.println("First key: " + map.firstEntry().getKey());
        System.out.println("Last key: " + map.lastEntry().getKey());

        // Add entries to beginning and end
        map.putFirst("Z", 0);
        map.putLast("E", 5);
        System.out.println("After adding first/last: " + map);

        // Get reversed view
        Map<String, Integer> reversedMap = map.reversed();
        System.out.println("Reversed map: " + reversedMap);

        // Poll first and last entries
        Map.Entry<String, Integer> firstEntry = map.pollFirstEntry();
        Map.Entry<String, Integer> lastEntry = map.pollLastEntry();
        System.out.println("Polled first entry: " + firstEntry);
        System.out.println("Polled last entry: " + lastEntry);
        System.out.println("Map after polling: " + map);

        System.out.println("\n=== Working with different collection types ===");

        // ArrayList
        ArrayList<String> arrayList = new ArrayList<>(List.of("X", "Y", "Z"));
        demonstrateSequencedCollection(arrayList, "ArrayList");

        // LinkedList
        LinkedList<String> linkedList = new LinkedList<>(List.of("X", "Y", "Z"));
        demonstrateSequencedCollection(linkedList, "LinkedList");

        // TreeSet (does NOT implement SequencedSet - it's sorted)
        TreeSet<String> treeSet = new TreeSet<>(List.of("X", "Y", "Z"));
        System.out.println("TreeSet (sorted, not sequenced): " + treeSet);
        // treeSet.getFirst(); // This would not compile
    }

    private static void demonstrateSequencedCollection(List<String> collection, String type) {
        System.out.println(type + ": " + collection);
        System.out.println("  First: " + collection.getFirst());
        System.out.println("  Last: " + collection.getLast());
        System.out.println("  Reversed: " + collection.reversed());
    }
}