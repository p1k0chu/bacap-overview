package com.github.p1k0chu.mc_mod.bacap_overview

import net.minecraft.text.MutableText
import net.minecraft.text.Text

enum class Language(val key: String) {
    NOTHING_HERE_YET("bacap-overview.hud.nothing_here_yet");

    val text: MutableText = Text.translatable(key)
    fun getText(vararg args: Any): MutableText = Text.translatable(key, *args)
}