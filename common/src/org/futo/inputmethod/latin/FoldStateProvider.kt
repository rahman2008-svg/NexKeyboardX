package com.nexvora.keyboard.latin

import androidx.window.layout.FoldingFeature

/**
 * Represents folding-related device configuration for foldable devices.
 * This helps the keyboard adapt its layout dynamically based on fold state.
 */
data class FoldingOptions(
    val feature: FoldingFeature?
)

/**
 * Provides current fold state information of the device.
 * Implementations should supply real-time folding updates
 * for adaptive keyboard layout rendering.
 */
interface FoldStateProvider {
    val foldState: FoldingOptions
}
