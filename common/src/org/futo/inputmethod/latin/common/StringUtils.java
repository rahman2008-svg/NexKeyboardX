package org.futo.inputmethod.latin.common;

import org.futo.inputmethod.annotations.UsedForTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class StringUtils {

    public static final int CAPITALIZE_NONE = 0;
    public static final int CAPITALIZE_FIRST = 1;
    public static final int CAPITALIZE_ALL = 2;

    @Nonnull
    private static final String EMPTY_STRING = "";

    private static final char CHAR_LINE_FEED = 0x000A;
    private static final char CHAR_VERTICAL_TAB = 0x000B;
    private static final char CHAR_FORM_FEED = 0x000C;
    private static final char CHAR_CARRIAGE_RETURN = 0x000D;
    private static final char CHAR_NEXT_LINE = 0x0085;
    private static final char CHAR_LINE_SEPARATOR = 0x2028;
    private static final char CHAR_PARAGRAPH_SEPARATOR = 0x2029;

    private static final HashMap<Integer, Integer> uppercaseCodeReplacements = new HashMap<>();

    static {
        uppercaseCodeReplacements.put(0x00DF, 0x1E9E); // ß -> ẞ
    }

    private StringUtils() {}

    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static String join(@Nonnull CharSequence delimiter, @Nonnull Iterable<?> tokens) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (Object t : tokens) {
            if (!first) sb.append(delimiter);
            sb.append(t);
            first = false;
        }
        return sb.toString();
    }

    public static boolean equals(@Nullable CharSequence a, @Nullable CharSequence b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        if (a.length() != b.length()) return false;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }

    public static int codePointCount(@Nullable CharSequence text) {
        if (isEmpty(text)) return 0;
        return Character.codePointCount(text, 0, text.length());
    }

    @Nonnull
    public static String newSingleCodePointString(int codePoint) {
        if (Character.charCount(codePoint) == 1) {
            return String.valueOf((char) codePoint);
        }
        return new String(Character.toChars(codePoint));
    }

    public static boolean containsInArray(@Nonnull String text, @Nonnull String[] array) {
        for (String e : array) {
            if (text.equals(e)) return true;
        }
        return false;
    }

    private static final String COMMA = ",";

    public static boolean containsInCommaSplittableText(@Nonnull String text,
                                                        @Nullable String extraValues) {
        if (isEmpty(extraValues)) return false;
        return containsInArray(text, extraValues.split(COMMA));
    }

    @Nonnull
    public static String removeFromCommaSplittableTextIfExists(@Nonnull String text,
                                                               @Nullable String extraValues) {
        if (isEmpty(extraValues)) return EMPTY_STRING;

        String[] parts = extraValues.split(COMMA);
        if (!containsInArray(text, parts)) return extraValues;

        ArrayList<String> out = new ArrayList<>();
        for (String p : parts) {
            if (!text.equals(p)) out.add(p);
        }
        return join(COMMA, out);
    }

    public static void removeDupes(@Nonnull ArrayList<String> suggestions) {
        for (int i = 1; i < suggestions.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (equals(suggestions.get(i), suggestions.get(j))) {
                    suggestions.remove(i);
                    i--;
                    break;
                }
            }
        }
    }

    @Nonnull
    public static String capitalizeFirstCodePoint(@Nonnull String s, @Nonnull Locale locale) {
        if (s.length() <= 1) return s.toUpperCase(locale);

        int cut = s.offsetByCodePoints(0, 1);
        return s.substring(0, cut).toUpperCase(locale) + s.substring(cut);
    }

    @Nonnull
    public static String capitalizeFirstAndDowncaseRest(@Nonnull String s, @Nonnull Locale locale) {
        if (s.length() <= 1) return s.toUpperCase(locale);

        int cut = s.offsetByCodePoints(0, 1);
        return s.substring(0, cut).toUpperCase(locale)
                + s.substring(cut).toLowerCase(locale);
    }

    public static int[] toCodePointArray(@Nonnull CharSequence cs) {
        return toCodePointArray(cs, 0, cs.length());
    }

    @Nonnull
    public static int[] toCodePointArray(@Nonnull CharSequence cs, int start, int end) {
        if (cs.length() == 0) return new int[0];

        int[] out = new int[Character.codePointCount(cs, start, end)];
        copyCodePointsAndReturnCodePointCount(out, cs, start, end, false);
        return out;
    }

    public static int copyCodePointsAndReturnCodePointCount(
            @Nonnull int[] dest,
            @Nonnull CharSequence cs,
            int start,
            int end,
            boolean downCase
    ) {
        int idx = 0;

        for (int i = start; i < end;
             i = Character.offsetByCodePoints(cs, i, 1)) {

            int cp = Character.codePointAt(cs, i);
            dest[idx++] = downCase ? Character.toLowerCase(cp) : cp;
        }
        return idx;
    }

    @Nonnull
    public static int[] toSortedCodePointArray(@Nonnull String s) {
        int[] cps = toCodePointArray(s);
        Arrays.sort(cps);
        return cps;
    }

    public static String getStringFromNullTerminatedCodePointArray(@Nonnull int[] cps) {
        int len = cps.length;
        for (int i = 0; i < cps.length; i++) {
            if (cps[i] == 0) {
                len = i;
                break;
            }
        }
        return new String(cps, 0, len);
    }

    public static boolean isIdenticalAfterUpcase(@Nonnull String text) {
        for (int i = 0; i < text.length();) {
            int cp = text.codePointAt(i);
            if (Character.isLetter(cp) && !Character.isUpperCase(cp)) return false;
            i += Character.charCount(cp);
        }
        return true;
    }

    public static boolean isIdenticalAfterDowncase(@Nonnull String text) {
        for (int i = 0; i < text.length();) {
            int cp = text.codePointAt(i);
            if (Character.isLetter(cp) && !Character.isLowerCase(cp)) return false;
            i += Character.charCount(cp);
        }
        return true;
    }

    public static boolean hasLineBreakCharacter(@Nullable String text) {
        if (isEmpty(text)) return false;

        for (int i = text.length() - 1; i >= 0; i--) {
            char c = text.charAt(i);
            if (c == CHAR_LINE_FEED ||
                c == CHAR_VERTICAL_TAB ||
                c == CHAR_FORM_FEED ||
                c == CHAR_CARRIAGE_RETURN ||
                c == CHAR_NEXT_LINE ||
                c == CHAR_LINE_SEPARATOR ||
                c == CHAR_PARAGRAPH_SEPARATOR) {
                return true;
            }
        }
        return false;
    }
}
