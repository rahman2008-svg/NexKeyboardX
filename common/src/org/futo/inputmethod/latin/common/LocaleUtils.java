package com.nexvora.keyboard.latin.common;

import com.nexvora.keyboard.Subtypes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Utility class for locale parsing, matching and language direction detection.
 *
 * Used for:
 * - Keyboard language switching
 * - Subtype selection
 * - RTL/LTR detection
 */
public final class LocaleUtils {

    private LocaleUtils() {
        // utility class
    }

    // ---------------- MATCH LEVEL CONSTANTS ----------------

    public static final int LOCALE_NO_MATCH = 0;
    public static final int LOCALE_LANGUAGE_MATCH_COUNTRY_DIFFER = 3;
    public static final int LOCALE_LANGUAGE_AND_COUNTRY_MATCH_VARIANT_DIFFER = 6;
    public static final int LOCALE_ANY_MATCH = 10;
    public static final int LOCALE_LANGUAGE_MATCH = 15;
    public static final int LOCALE_LANGUAGE_AND_COUNTRY_MATCH = 20;
    public static final int LOCALE_FULL_MATCH = 30;

    private static final int LOCALE_MATCH = LOCALE_ANY_MATCH;
    private static final int MATCH_LEVEL_MAX = 30;

    // ---------------- MATCH LOGIC ----------------

    public static int getMatchLevel(@Nullable final String referenceLocale,
                                    @Nullable final String testedLocale) {

        if (StringUtils.isEmpty(referenceLocale)) {
            return StringUtils.isEmpty(testedLocale)
                    ? LOCALE_FULL_MATCH
                    : LOCALE_ANY_MATCH;
        }

        if (testedLocale == null) return LOCALE_NO_MATCH;

        final String[] ref = referenceLocale.split("_", 3);
        final String[] test = testedLocale.split("_", 3);

        if (!ref[0].equals(test[0])) return LOCALE_NO_MATCH;

        switch (ref.length) {

            case 1:
                return (test.length == 1)
                        ? LOCALE_FULL_MATCH
                        : LOCALE_LANGUAGE_MATCH;

            case 2:
                if (test.length == 1) return LOCALE_LANGUAGE_MATCH_COUNTRY_DIFFER;
                if (!ref[1].equals(test[1]))
                    return LOCALE_LANGUAGE_MATCH_COUNTRY_DIFFER;

                return (test.length == 3)
                        ? LOCALE_LANGUAGE_AND_COUNTRY_MATCH
                        : LOCALE_FULL_MATCH;

            case 3:
                if (test.length == 1)
                    return LOCALE_LANGUAGE_MATCH_COUNTRY_DIFFER;

                if (!ref[1].equals(test[1]))
                    return LOCALE_LANGUAGE_MATCH_COUNTRY_DIFFER;

                if (test.length == 2)
                    return LOCALE_LANGUAGE_AND_COUNTRY_MATCH_VARIANT_DIFFER;

                if (!ref[2].equals(test[2]))
                    return LOCALE_LANGUAGE_AND_COUNTRY_MATCH_VARIANT_DIFFER;

                return LOCALE_FULL_MATCH;
        }

        return LOCALE_NO_MATCH;
    }

    public static String getMatchLevelSortedString(final int matchLevel) {
        return String.format(Locale.ROOT, "%02d", MATCH_LEVEL_MAX - matchLevel);
    }

    public static boolean isMatch(final int level) {
        return LOCALE_MATCH <= level;
    }

    // ---------------- LOCALE CACHE ----------------

    private static final HashMap<String, Locale> sLocaleCache = new HashMap<>();

    @Nonnull
    public static Locale constructLocaleFromString(@Nonnull final String localeString) {

        if (localeString.contains("__#")) {
            return Subtypes.INSTANCE.getLocale(localeString);
        }

        synchronized (sLocaleCache) {
            if (sLocaleCache.containsKey(localeString)) {
                return sLocaleCache.get(localeString);
            }

            final String[] e = localeString.split("_", 3);
            final Locale locale;

            if (e.length == 1) {
                locale = new Locale(e[0]);
            } else if (e.length == 2) {
                locale = new Locale(e[0], e[1]);
            } else {
                locale = new Locale(e[0], e[1], e[2]);
            }

            sLocaleCache.put(localeString, locale);
            return locale;
        }
    }

    // ---------------- RTL SUPPORT ----------------

    private static final HashSet<String> RTL_LANGS = new HashSet<>();

    static {
        RTL_LANGS.add("ar");
        RTL_LANGS.add("fa");
        RTL_LANGS.add("iw");
        RTL_LANGS.add("ku");
        RTL_LANGS.add("ps");
        RTL_LANGS.add("sd");
        RTL_LANGS.add("ug");
        RTL_LANGS.add("ur");
        RTL_LANGS.add("yi");
    }

    public static boolean isRtlLanguage(@Nonnull final Locale locale) {
        return RTL_LANGS.contains(locale.getLanguage());
    }
}
