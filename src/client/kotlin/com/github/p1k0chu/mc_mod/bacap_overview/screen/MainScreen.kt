package com.github.p1k0chu.mc_mod.bacap_overview.screen

import com.github.p1k0chu.mc_mod.bacap_overview.ClientAdvancementProgressGetter
import com.github.p1k0chu.mc_mod.bacap_overview.Language
import com.github.p1k0chu.mc_mod.bacap_overview.screen.widget.ItemListEntry
import com.github.p1k0chu.mc_mod.bacap_overview.screen.widget.ItemListWidget
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.TextWidget
import net.minecraft.text.Text
import net.minecraft.util.Identifier

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
        val client = MinecraftClient.getInstance()
        val advHandler = client?.player?.networkHandler?.advancementHandler
        val progressGetter = advHandler as? ClientAdvancementProgressGetter

        val itemAdv = progressGetter?.`bacap_overview$getAdvancementProgress`(advHandler.get(ITEM_ADV))
        val blockAdv = progressGetter?.`bacap_overview$getAdvancementProgress`(advHandler.get(BLOCK_ADV))

        if (itemAdv == null || blockAdv == null) {
            val label = TextWidget(Language.NOTHING_HERE_YET.text, this.textRenderer)
            label.setPosition(width / 2 - label.width / 2, (height - 20) / 2 - label.height / 2 + 30)

            this.addDrawableChild(label)

            return
        }

        val itemList = ItemListWidget(client, 0, 32, width / 2, height - 32, 16, this, itemAdv, ItemListEntry.Type.ITEM)
        this.addDrawableChild(itemList)

        val blockList =
            ItemListWidget(client, width / 2, 32, width / 2, height - 32, 16, this, blockAdv, ItemListEntry.Type.BLOCK)
        this.addDrawableChild(blockList)
    }

    private fun drawStatsElements() {

    }

    enum class ScreenPage(val title: String) {
        OVERVIEW("Overview"),
        ITEMS("Items"),
        STATS("Stats")
    }

    companion object {
        val ITEM_ADV = Identifier.of("blazeandcave:challenges/stack_all_the_items")
        val BLOCK_ADV = Identifier.of("blazeandcave:challenges/stack_all_the_blocks")
    }
}