package com.github.p1k0chu.mc_mod.bacap_overview

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Main : ModInitializer {
    companion object {
        const val MOD_ID = "bac-overview"

        @JvmField
        val logger: Logger = LoggerFactory.getLogger(MOD_ID)
    }

    override fun onInitialize() {
    }
}