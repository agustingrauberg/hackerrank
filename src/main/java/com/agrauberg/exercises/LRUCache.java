package com.agrauberg.exercises;

import java.util.*;
import java.util.stream.Collectors;

public class LRUCache {

    private final int capacity;
    private Node first = null;
    private Node last = null;
    private final Map<Integer, Node> cache;

    public static final class Node {
        int key;
        double value;
        Node prev;
        Node next;

        public Node(int key, double value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return "Key: %d, Value: %f".formatted(key, value);
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }

    public void add(int key, double value) {
        Node node = cache.get(key);
        if (node != null) {
            // Relink my prev and next nodes
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                // I'm already the first --- de nothing
                return;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            return;
        }

        // I'm new to the cache --- check capacity and place me first
        if (cache.size() >= capacity) {
            removeLRU();
        }
        // new element --- place me first
        node = new Node(key, value);
        placeFirst(node);
        cache.put(key, node);
    }

    // point my previous to the next, and next to the previous
    // null my previous, as I'm the first node
    public void placeFirst(Node node) {
        if (first != null) {
            first.prev = node;
        }

        node.next = first;
        node.prev = null;
        first = node;

        if (last == null) {
            last = node;
        }
    }

    // if I'm the last, then my previous is now the last node
    // if I'm the first then my next is now the first node
    public void removeLRU() {
        if (last == null || cache.isEmpty()) {
            return;
        }

        cache.remove(last.key);

        if (last.prev != null) {
            last.prev.next = null;
            last = last.prev;
        }
    }

    public Map<Integer, Node> getCache() {
        return this.cache;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    @Override
    public String toString() {
        return "- Capacity: %s".formatted(this.capacity) + System.lineSeparator() +
                "- Last used: %s".formatted(this.first) + System.lineSeparator() +
                "- Least used: %s".formatted(this.last) + System.lineSeparator() +
                cacheToString() +
                getChainedCache();
    }

    private String cacheToString() {
        return "- Cache: %n%s".formatted(this.cache.values().stream().map(n -> "\tN: %d ---> %f".formatted(n.key, n.value)).collect(Collectors.joining(System.lineSeparator())));
    }

    public String getChainedCache() {
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(first)) {
            sb.append("%s FULL CHAIN: ".formatted(System.lineSeparator()));
            Node currentNode = first;
            while (currentNode != null) {
                sb.append(" %s ".formatted(currentNode.key));
                currentNode = currentNode.next;
            }
        }
        return sb.toString();
    }
}