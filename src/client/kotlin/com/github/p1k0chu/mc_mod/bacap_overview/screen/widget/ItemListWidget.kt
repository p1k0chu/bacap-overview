package com.github.p1k0chu.mc_mod.bacap_overview.screen.widget

import com.github.p1k0chu.mc_mod.bacap_overview.screen.MainScreen
import net.minecraft.advancement.AdvancementProgress
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.widget.ElementListWidget

class ItemListWidget(
    client: MinecraftClient,
    x: Int, y: Int, width: Int, height: Int, itemHeight: Int,
    screen: MainScreen,
    advancementProgress: AdvancementProgress, type: ItemListEntry.Type
) : ElementListWidget<ItemListEntry>(client, width, height, y, itemHeight) {
    init {
        this.centerListVertically = false
        this.x = x

        advancementProgress.unobtainedCriteria.sorted().filterNotNull().forEach { it: String ->
            this.addEntry(ItemListEntry(it, false, type, screen))
        }
        advancementProgress.obtainedCriteria.sorted().filterNotNull().forEach { it: String ->
            this.addEntry(ItemListEntry(it, true, type, screen))
        }
    }

    override fun getRowWidth(): Int {
        return width
    }

    override fun getScrollbarX(): Int {
        return this.right - 6
    }
}