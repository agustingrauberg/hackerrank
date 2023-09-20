package com.agrauberg.exercises;

import com.agrauberg.exercises.LRUCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LRUCacheTest {

    static final int CAPACITY = 2;

    @Test
    void testEmptyCache() {
        LRUCache lru = new LRUCache(CAPACITY);

        Assertions.assertTrue(lru.getCache().isEmpty());
    }

    @Test
    void testAddition() {
        LRUCache lru = new LRUCache(CAPACITY);
        lru.add(1, 3.56);
        lru.add(2, 5.789);

        Assertions.assertEquals(2, lru.getCache().size());
        Assertions.assertTrue(lru.getCache().containsKey(1));
    }

    @Test
    void testUpdate() {
        LRUCache lru = new LRUCache(CAPACITY);
        lru.add(1, 1.23);
        lru.add(2, 2.34);
        lru.add(3, 3.45);

        Assertions.assertEquals(3, lru.getFirst().key);
        Assertions.assertEquals(2, lru.getLast().key);
        Assertions.assertFalse(lru.getCache().containsKey(1));
    }

    @Test
    void remove() {
        LRUCache lru = new LRUCache(CAPACITY);
        lru.add(1, 3.56);

        Assertions.assertEquals(1, lru.getCache().size());
        Assertions.assertTrue(lru.getCache().containsKey(1));

        lru.removeLRU();

        Assertions.assertTrue(lru.getCache().isEmpty());
        Assertions.assertFalse(lru.getCache().containsKey(1));
    }
}