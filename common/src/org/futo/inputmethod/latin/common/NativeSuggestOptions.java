package org.futo.inputmethod.latin.common;

/**
 * Container for native suggestion engine options.
 * These values are passed to native (C/C++) layer.
 *
 * NOTE: Keep in sync with suggest_options.h
 */
public class NativeSuggestOptions {

    // ⚠ Must match native header order exactly
    private static final int IS_GESTURE = 0;
    private static final int USE_FULL_EDIT_DISTANCE = 1;
    private static final int BLOCK_OFFENSIVE_WORDS = 2;
    private static final int SPACE_AWARE_GESTURE_ENABLED = 3;
    private static final int WEIGHT_FOR_LOCALE_IN_THOUSANDS = 4;

    private static final int OPTIONS_SIZE = 5;

    private final int[] mOptions;

    public NativeSuggestOptions() {
        mOptions = new int[OPTIONS_SIZE];
    }

    /**
     * Enable/disable gesture input mode.
     */
    public void setIsGesture(boolean value) {
        mOptions[IS_GESTURE] = value ? 1 : 0;
    }

    /**
     * Enable full edit distance calculation.
     */
    public void setUseFullEditDistance(boolean value) {
        mOptions[USE_FULL_EDIT_DISTANCE] = value ? 1 : 0;
    }

    /**
     * Block offensive word suggestions.
     */
    public void setBlockOffensiveWords(boolean value) {
        mOptions[BLOCK_OFFENSIVE_WORDS] = value ? 1 : 0;
    }

    /**
     * Enable/disable space-aware gesture recognition.
     */
    public void setSpaceAwareGestureEnabled(boolean value) {
        mOptions[SPACE_AWARE_GESTURE_ENABLED] = value ? 1 : 0;
    }

    /**
     * Set locale weight multiplier (fixed-point in thousands).
     * Example: 1.5 → 1500
     */
    public void setWeightForLocale(float value) {
        if (Float.isNaN(value) || Float.isInfinite(value)) {
            value = 1.0f;
        }
        mOptions[WEIGHT_FOR_LOCALE_IN_THOUSANDS] = (int) (value * 1000f);
    }

    /**
     * Returns raw options array for native layer.
     * WARNING: Do not modify returned array.
     */
    public int[] getOptions() {
        return mOptions;
    }
}
