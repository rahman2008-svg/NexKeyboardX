package com.nexvora.keyboard.latin.common;

import com.nexvora.keyboard.annotations.UsedForTesting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Utility methods for working with Java collections.
 *
 * Provides helper functions for safe array and collection operations
 * used in keyboard and dictionary processing.
 */
public final class CollectionUtils {

    private CollectionUtils() {
        // Utility class - no instantiation allowed
    }

    /**
     * Converts a sub-range of an array into an ArrayList.
     *
     * @param array input array
     * @param start start index (inclusive)
     * @param end end index (exclusive)
     * @return ArrayList containing selected elements
     * @throws IllegalArgumentException if indices are invalid
     */
    @Nonnull
    public static <E> ArrayList<E> arrayAsList(@Nonnull final E[] array,
                                              final int start,
                                              final int end) {

        if (start < 0 || start > end || end > array.length) {
            throw new IllegalArgumentException(
                    "Invalid range: start=" + start +
                    ", end=" + end +
                    ", length=" + array.length
            );
        }

        final ArrayList<E> list = new ArrayList<>(end - start);

        for (int i = start; i < end; i++) {
            list.add(array[i]);
        }

        return list;
    }

    /**
     * Checks whether a collection is null or empty.
     */
    @UsedForTesting
    public static boolean isNullOrEmpty(@Nullable final Collection<?> c) {
        return c == null || c.isEmpty();
    }

    /**
     * Checks whether a map is null or empty.
     */
    @UsedForTesting
    public static boolean isNullOrEmpty(@Nullable final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
