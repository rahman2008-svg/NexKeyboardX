package com.nexvora.keyboard.latin.common;

import com.nexvora.keyboard.annotations.UsedForTesting;

import java.util.Random;

import javax.annotation.Nonnull;

/**
 * Utility methods for generating and handling Unicode code points
 * used in testing keyboard and dictionary components.
 *
 * This class is strictly for testing purposes and should not be
 * included in production logic.
 */
@UsedForTesting
public class CodePointUtils {

    private CodePointUtils() {
        // Utility class - no instantiation allowed
    }

    public static final int[] LATIN_ALPHABETS_LOWER = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        0x00E0, 0x00E1, 0x00E2, 0x00E3, 0x00E4, 0x00E5,
        0x00E6, 0x00E7, 0x00E8, 0x00E9, 0x00EA, 0x00EB,
        0x00EC, 0x00ED, 0x00EE, 0x00EF, 0x00F0, 0x00F1,
        0x00F2, 0x00F3, 0x00F4, 0x00F5, 0x00F6, 0x00F7,
        0x00F9, 0x00FA, 0x00FB, 0x00FC, 0x00FD, 0x00FE,
        0x00FF
    };

    /**
     * Generates a random set of Unicode code points for testing.
     */
    @UsedForTesting
    @Nonnull
    public static int[] generateCodePointSet(final int codePointSetSize,
            @Nonnull final Random random) {

        final int[] codePointSet = new int[codePointSetSize];

        for (int i = codePointSet.length - 1; i >= 0; ) {
            final int r = Math.abs(random.nextInt());
            if (r < 0) continue;

            final int candidateCodePoint =
                    0x20 + r % (Character.MAX_CODE_POINT - 0x20);

            if (candidateCodePoint >= Character.MIN_SURROGATE
                    && candidateCodePoint <= Character.MAX_SURROGATE) {
                continue;
            }

            codePointSet[i] = candidateCodePoint;
            i--;
        }

        return codePointSet;
    }

    /**
     * Generates a pseudo-random word using a given code point set.
     */
    @UsedForTesting
    @Nonnull
    public static String generateWord(@Nonnull final Random random,
            @Nonnull final int[] codePointSet) {

        final StringBuilder builder = new StringBuilder();

        final int count =
                1 + (Math.abs(random.nextInt()) % 5)
                + (Math.abs(random.nextInt()) % 5)
                + (Math.abs(random.nextInt()) % 5)
                + (Math.abs(random.nextInt()) % 5)
                + (Math.abs(random.nextInt()) % 5)
                + (Math.abs(random.nextInt()) % 5)
                + (Math.abs(random.nextInt()) % 5)
                + (Math.abs(random.nextInt()) % 5);

        while (builder.length() < count) {
            builder.appendCodePoint(
                    codePointSet[Math.abs(random.nextInt()) % codePointSet.length]
            );
        }

        return builder.toString();
    }
}
