package com.nexvora.keyboard.annotations;

/**
 * Indicates that the annotated class, method, or field must not be removed
 * or obfuscated by ProGuard/R8 during build optimization.
 *
 * This is required for components that are accessed via:
 * - Reflection
 * - Native (JNI) code
 * - Android system callbacks (e.g., InputMethodService)
 * - XML or manifest declarations
 *
 * Removing these elements may break core keyboard functionality.
 */
public @interface ExternallyReferenced {
}
