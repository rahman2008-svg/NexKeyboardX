package com.nexvora.keyboard.annotations;

/**
 * Indicates that the annotated class, method, or field is intended
 * to be accessible during testing and must not be removed or
 * obfuscated by ProGuard/R8.
 *
 * This ensures unit tests and instrumentation tests can reliably
 * access internal implementation details when required.
 */
public @interface UsedForTesting {
}
