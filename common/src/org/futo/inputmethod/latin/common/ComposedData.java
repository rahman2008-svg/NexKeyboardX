package com.nexvora.keyboard.latin.common;

import javax.annotation.Nonnull;

/**
 * Immutable snapshot of word composition state during typing.
 *
 * This class holds input pointer data, typing mode, and the
 * currently composed word used by the keyboard engine.
 */
public class ComposedData {

    @Nonnull
    public final InputPointers mInputPointers;

    public final boolean mIsBatchMode;

    @Nonnull
    public final String mTypedWord;

    public ComposedData(@Nonnull final InputPointers inputPointers,
                        final boolean isBatchMode,
                        @Nonnull final String typedWord) {

        this.mInputPointers = inputPointers;
        this.mIsBatchMode = isBatchMode;
        this.mTypedWord = typedWord;
    }

    /**
     * Copies Unicode code points of the typed word into destination array,
     * excluding trailing single quotes.
     *
     * @param destination output buffer
     * @return number of copied code points, or -1 if buffer is too small
     */
    public int copyCodePointsExceptTrailingSingleQuotesAndReturnCodePointCount(
            @Nonnull final int[] destination) {

        final int lastIndex = mTypedWord.length()
                - StringUtils.getTrailingSingleQuotesCount(mTypedWord);

        if (lastIndex <= 0) {
            return 0;
        }

        final int codePointSize =
                Character.codePointCount(mTypedWord, 0, lastIndex);

        if (codePointSize > destination.length) {
            return -1;
        }

        return StringUtils.copyCodePointsAndReturnCodePointCount(
                destination,
                mTypedWord,
                0,
                lastIndex,
                true
        );
    }
}
