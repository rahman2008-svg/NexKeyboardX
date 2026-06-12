/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 */

package org.futo.inputmethod.latin.common;

/**
 * Unicode surrogate utilities.
 *
 * Emojis and many special symbols are represented in UTF-16 using surrogate pairs:
 * - High surrogate: 0xD800–0xDBFF
 * - Low surrogate:  0xDC00–0xDFFF
 *
 * Example:
 * U+1F625 😥 is encoded as two chars in Java UTF-16.
 */
public final class UnicodeSurrogate {

    // Correct ranges
    private static final char HIGH_SURROGATE_MIN = '\uD800';
    private static final char HIGH_SURROGATE_MAX = '\uDBFF';

    private static final char LOW_SURROGATE_MIN = '\uDC00';
    private static final char LOW_SURROGATE_MAX = '\uDFFF';

    private UnicodeSurrogate() {
        // utility class
    }

    /** Returns true if character is a high surrogate. */
    public static boolean isHighSurrogate(final char c) {
        return c >= HIGH_SURROGATE_MIN && c <= HIGH_SURROGATE_MAX;
    }

    /** Returns true if character is a low surrogate. */
    public static boolean isLowSurrogate(final char c) {
        return c >= LOW_SURROGATE_MIN && c <= LOW_SURROGATE_MAX;
    }

    /** Returns true if both chars form a valid surrogate pair. */
    public static boolean isSurrogatePair(final char high, final char low) {
        return isHighSurrogate(high) && isLowSurrogate(low);
    }

    /** Quick check using Java built-in API (safer). */
    public static boolean isSurrogate(final char c) {
        return Character.isSurrogate(c);
    }
}
