package org.futo.inputmethod.latin.common;

import org.futo.inputmethod.annotations.UsedForTesting;

import java.util.Arrays;

import javax.annotation.Nonnull;

/**
 * A dynamically resizable int array.
 *
 * NOTE: This class is NOT thread-safe.
 */
public final class ResizableIntArray {

    @Nonnull
    private int[] mArray;

    private int mLength;

    public ResizableIntArray(int capacity) {
        if (capacity < 0) capacity = 0;
        mArray = new int[capacity];
        mLength = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= mLength) {
            throw new ArrayIndexOutOfBoundsException(
                    "length=" + mLength + ", index=" + index
            );
        }
        return mArray[index];
    }

    public void addAt(int index, int value) {
        if (index < 0) return;

        if (index < mLength) {
            mArray[index] = value;
            return;
        }

        ensureCapacity(index + 1);
        mLength = index;
        add(value);
    }

    public void add(int value) {
        ensureCapacity(mLength + 1);
        mArray[mLength++] = value;
    }

    private int calculateCapacity(int minimumCapacity) {
        int current = mArray.length;

        if (current >= minimumCapacity) return 0;

        int doubled = current == 0 ? 1 : current * 2;

        return Math.max(minimumCapacity, doubled);
    }

    private void ensureCapacity(int minimumCapacity) {
        int newCapacity = calculateCapacity(minimumCapacity);

        if (newCapacity > 0) {
            mArray = Arrays.copyOf(mArray, newCapacity);
        }
    }

    public int getLength() {
        return mLength;
    }

    public void setLength(int newLength) {
        if (newLength < 0) newLength = 0;

        ensureCapacity(newLength);

        if (newLength > mLength) {
            Arrays.fill(mArray, mLength, newLength, 0);
        }

        mLength = newLength;
    }

    public void reset(int capacity) {
        if (capacity < 0) capacity = 0;
        mArray = new int[capacity];
        mLength = 0;
    }

    @Nonnull
    public int[] getPrimitiveArray() {
        return mArray;
    }

    public void set(@Nonnull ResizableIntArray src) {
        mArray = src.mArray;
        mLength = src.mLength;
    }

    public void copy(@Nonnull ResizableIntArray src) {
        if (mArray.length < src.mLength) {
            mArray = new int[src.mLength];
        }

        System.arraycopy(src.mArray, 0, mArray, 0, src.mLength);
        mLength = src.mLength;
    }

    public void append(@Nonnull ResizableIntArray src, int startPos, int length) {
        if (length <= 0) return;
        if (startPos < 0 || startPos + length > src.mLength) return;

        int newLength = mLength + length;
        ensureCapacity(newLength);

        System.arraycopy(src.mArray, startPos, mArray, mLength, length);
        mLength = newLength;
    }

    public void fill(int value, int startPos, int length) {
        if (startPos < 0 || length <= 0) return;

        int end = startPos + length;
        ensureCapacity(end);

        Arrays.fill(mArray, startPos, end, value);

        if (mLength < end) {
            mLength = end;
        }
    }

    @UsedForTesting
    public void shift(int elementCount) {
        if (elementCount <= 0) return;
        if (elementCount >= mLength) {
            mLength = 0;
            return;
        }

        System.arraycopy(mArray, elementCount, mArray, 0, mLength - elementCount);
        mLength -= elementCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('[');
        for (int i = 0; i < mLength; i++) {
            if (i > 0) sb.append(',');
            sb.append(mArray[i]);
        }
        sb.append(']');

        return sb.toString();
    }
}
