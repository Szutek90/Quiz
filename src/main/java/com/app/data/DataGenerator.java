package com.app.data;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The DataGenerator interface provides methods for generating random data.
 */

public interface DataGenerator {

    /**
     * Generates a HashSet of random integers.
     *
     * @param size     The upper bound (exclusive) for random integer generation.
     * @param quantity The number of unique random integers to generate.
     * @return A HashSet containing the specified number of unique random integers.
     */
    static Set<Integer> generateUniqueRandomIntegers(int size, int quantity) {
        var randomValues = new HashSet<Integer>();
        // Generate unique random integers until the desired quantity is reached
        do {
            randomValues.add(ThreadLocalRandom.current().nextInt(1, size + 1));
        } while (randomValues.size() != quantity);

        return randomValues;
    }

    static int generateRandomInteger(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
