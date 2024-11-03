package com.github.p1k0chu.mc_mod.bacap_overview.screen

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text

@Environment(EnvType.CLIENT)
class MainScreen(val parentScreen: Screen) : Screen(Text.of("BACAP overview")) {
    private var selectedPage: ScreenPage = ScreenPage.OVERVIEW
        set(value) {
            field = value

            this.rebuildGUI()
        }

    override fun init() {
        super.init()

        rebuildGUI()
    }

    override fun close() {
        super.close()

        MinecraftClient.getInstance().setScreen(parentScreen)
    }

    private fun rebuildGUI() {
        this.clearChildren()

        rebuildGUIPages()
        rebuildGUIElements()

        val exitButton = ButtonWidget
            .Builder(Text.of("Exit")) { this.close() }
            .dimensions(this.width - 60, this.height - 20, 50, 20)
            .build()
        this.addDrawableChild(exitButton)
    }

    private fun rebuildGUIPages() {
        ScreenPage.entries.forEachIndexed { index, screenPage ->
            val button = ButtonWidget
                .Builder(Text.of(screenPage.title)) {
                    selectedPage = screenPage
                }
                .dimensions(5 + index * 105, 10, 100, 20)
                .build()

            button.active = selectedPage != screenPage
            this.addDrawableChild(button)
        }
    }

    private fun rebuildGUIElements() {
        when (selectedPage) {
            ScreenPage.OVERVIEW -> drawOverviewElements()
            ScreenPage.ITEMS -> drawItemsElements()
            ScreenPage.STATS -> drawStatsElements()
        }
    }

    private fun drawOverviewElements() {

    }

    private fun drawItemsElements() {

    }

    private fun drawStatsElements() {

    }

    enum class ScreenPage(val title: String) {
        OVERVIEW("Overview"),
        ITEMS("Items"),
        STATS("Stats")
    }
}