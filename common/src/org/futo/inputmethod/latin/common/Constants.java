package com.nexvora.keyboard.latin.common;

import com.nexvora.keyboard.annotations.UsedForTesting;

import javax.annotation.Nonnull;

/**
 * Central repository of constants used across the NexVora Keyboard engine.
 *
 * Includes key codes, IME options, subtype flags, and internal configuration
 * values used by input method, suggestion engine, and gesture typing system.
 */
public final class Constants {

    public static final class Color {
        public static final int ALPHA_OPAQUE = 255;
    }

    public static final class ImeOption {

        @Deprecated
        public static final String NO_MICROPHONE_COMPAT = "nm";

        public static final String NO_MICROPHONE = "noMicrophoneKey";
        public static final String NO_SETTINGS_KEY = "noSettingsKey";
        public static final String FORCE_ASCII = "forceAscii";
        public static final String NO_FLOATING_GESTURE_PREVIEW = "noGestureFloatingPreview";

        private ImeOption() {}
    }

    public static final class Subtype {

        public static final String KEYBOARD_MODE = "keyboard";

        public static final class ExtraValue {

            public static final String ASCII_CAPABLE = "AsciiCapable";
            public static final String ENABLED_WHEN_DEFAULT_IS_NOT_ASCII_CAPABLE =
                    "EnabledWhenDefaultIsNotAsciiCapable";
            public static final String EMOJI_CAPABLE = "EmojiCapable";
            public static final String REQ_NETWORK_CONNECTIVITY = "requireNetworkConnectivity";
            public static final String UNTRANSLATABLE_STRING_IN_SUBTYPE_NAME =
                    "UntranslatableReplacementStringInSubtypeName";
            public static final String KEYBOARD_LAYOUT_SET = "KeyboardLayoutSet";
            public static final String IS_ADDITIONAL_SUBTYPE = "isAdditionalSubtype";
            public static final String COMBINING_RULES = "CombiningRules";

            private ExtraValue() {}
        }

        private Subtype() {}
    }

    public static final class TextUtils {
        public static final int CAP_MODE_OFF = 0;

        private TextUtils() {}
    }

    public static final int NOT_A_CODE = -1;
    public static final int NOT_A_CURSOR_POSITION = -1;
    public static final int NOT_A_COORDINATE = -1;
    public static final int SUGGESTION_STRIP_COORDINATE = -2;
    public static final int EXTERNAL_KEYBOARD_COORDINATE = -4;

    public static final int EDITOR_CONTENTS_CACHE_SIZE = 1024;
    public static final int MAX_CHARACTERS_FOR_RECAPITALIZATION = 1024 * 100;

    public static final int LONG_PRESS_MILLISECONDS = 200;
    public static final int GET_SUGGESTED_WORDS_TIMEOUT = 200;
    public static final int DELETE_ACCELERATE_AT = 20;

    public static final String WORD_SEPARATOR = " ";

    public static final int CUSTOM_CODE_SHOW_INPUT_METHOD_PICKER = 1;

    public static final int CODE_ENTER = '\n';
    public static final int CODE_TAB = '\t';
    public static final int CODE_SPACE = ' ';
    public static final int CODE_PERIOD = '.';
    public static final int CODE_COMMA = ',';
    public static final int CODE_DASH = '-';
    public static final int CODE_SINGLE_QUOTE = '\'';
    public static final int CODE_DOUBLE_QUOTE = '"';
    public static final int CODE_SLASH = '/';
    public static final int CODE_BACKSLASH = '\\';
    public static final int CODE_VERTICAL_BAR = '|';
    public static final int CODE_COMMERCIAL_AT = '@';
    public static final int CODE_PLUS = '+';
    public static final int CODE_PERCENT = '%';
    public static final int CODE_CLOSING_PARENTHESIS = ')';
    public static final int CODE_CLOSING_SQUARE_BRACKET = ']';
    public static final int CODE_CLOSING_CURLY_BRACKET = '}';
    public static final int CODE_CLOSING_ANGLE_BRACKET = '>';
    public static final int CODE_INVERTED_QUESTION_MARK = 0xBF;
    public static final int CODE_INVERTED_EXCLAMATION_MARK = 0xA1;
    public static final int CODE_GRAVE_ACCENT = '`';
    public static final int CODE_CIRCUMFLEX_ACCENT = '^';
    public static final int CODE_TILDE = '~';

    public static final String REGEXP_PERIOD = "\\.";
    public static final String STRING_SPACE = " ";

    public static final int CODE_SHIFT = -1;
    public static final int CODE_CAPSLOCK = -2;
    public static final int CODE_SWITCH_ALPHA_SYMBOL = -3;
    public static final int CODE_OUTPUT_TEXT = -4;
    public static final int CODE_DELETE = -5;
    public static final int CODE_SETTINGS = -6;
    public static final int CODE_SHORTCUT = -7;
    public static final int CODE_ACTION_NEXT = -8;
    public static final int CODE_ACTION_PREVIOUS = -9;
    public static final int CODE_LANGUAGE_SWITCH = -10;
    public static final int CODE_EMOJI = -11;
    public static final int CODE_SHIFT_ENTER = -12;
    public static final int CODE_SYMBOL_SHIFT = -13;
    public static final int CODE_ALPHA_FROM_EMOJI = -14;
    public static final int CODE_TO_NUMBER_LAYOUT = -15;
    public static final int CODE_TO_ALT_0_LAYOUT = -16;
    public static final int CODE_TO_ALT_1_LAYOUT = -17;
    public static final int CODE_TO_ALT_2_LAYOUT = -18;
    public static final int CODE_TO_ALPHA_0_LAYOUT = -19;
    public static final int CODE_TO_ALPHA_1_LAYOUT = -20;
    public static final int CODE_TO_ALPHA_2_LAYOUT = -21;
    public static final int CODE_TO_ALPHA_3_LAYOUT = -22;
    public static final int CODE_OUTPUT_TEXT_WITH_SPACES = -23;
    public static final int CODE_UNSPECIFIED = -24;

    public static final int CODE_ACTION_0 = -1050;
    public static final int CODE_ACTION_MAX = CODE_ACTION_0 + 100;

    public static final int CODE_ALT_ACTION_0 = -2050;
    public static final int CODE_ALT_ACTION_MAX = CODE_ALT_ACTION_0 + 100;

    public static boolean isValidCoordinate(final int coordinate) {
        return coordinate >= 0;
    }

    public static final int SCREEN_METRICS_SMALL_PHONE = 0;
    public static final int SCREEN_METRICS_LARGE_PHONE = 1;
    public static final int SCREEN_METRICS_LARGE_TABLET = 2;
    public static final int SCREEN_METRICS_SMALL_TABLET = 3;

    @UsedForTesting
    public static boolean isPhone(final int screenMetrics) {
        return screenMetrics == SCREEN_METRICS_SMALL_PHONE
                || screenMetrics == SCREEN_METRICS_LARGE_PHONE;
    }

    @UsedForTesting
    public static boolean isTablet(final int screenMetrics) {
        return screenMetrics == SCREEN_METRICS_SMALL_TABLET
                || screenMetrics == SCREEN_METRICS_LARGE_TABLET;
    }

    public static final int DEFAULT_GESTURE_POINTS_CAPACITY = 128;

    public static final int MAX_IME_DECODER_RESULTS = 20;
    public static final int DECODER_SCORE_SCALAR = 1000000;
    public static final int DECODER_MAX_SCORE = 1000000000;

    public static final int EVENT_BACKSPACE = 1;
    public static final int EVENT_REJECTION = 2;
    public static final int EVENT_REVERT = 3;

    public static final int VOICE_INPUT_CONTEXT_SIZE = 3;

    private Constants() {}
}
