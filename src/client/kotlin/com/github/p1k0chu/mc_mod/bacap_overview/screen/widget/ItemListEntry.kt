package com.github.p1k0chu.mc_mod.bacap_overview.screen.widget

import com.github.p1k0chu.mc_mod.bacap_overview.BACAPOverview
import com.github.p1k0chu.mc_mod.bacap_overview.screen.MainScreen
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.Element
import net.minecraft.client.gui.Selectable
import net.minecraft.client.gui.widget.ElementListWidget
import net.minecraft.client.gui.widget.TextWidget
import net.minecraft.client.render.RenderLayer
import net.minecraft.registry.Registries
import net.minecraft.text.Text
import net.minecraft.util.Colors
import net.minecraft.util.Identifier

class ItemListEntry(
    val itemId: String, val done: Boolean, type: Type,
    screen: MainScreen
) : ElementListWidget.Entry<ItemListEntry>() {
    val nameLabel = TextWidget(
        when (type) {
            Type.ITEM -> Text.translatable("item.minecraft.${itemId}")
            Type.BLOCK -> Text.translatable("block.minecraft.${itemId}")
        },
        screen.textRenderer
    )
//    val idLabel = TextWidget(
//        Text.literal("[$itemId]").styled { style -> style.withItalic(true).withColor(Colors.GRAY) },
//        screen.textRenderer
//    )

    override fun selectableChildren(): List<Selectable?>? {
        return listOf(
            nameLabel,
//            idLabel
        )
    }

    override fun render(
        context: DrawContext?,
        index: Int,
        y: Int,
        x: Int,
        entryWidth: Int,
        entryHeight: Int,
        mouseX: Int,
        mouseY: Int,
        hovered: Boolean,
        tickDelta: Float
    ) {
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)
        RenderSystem.enableBlend()
        RenderSystem.enableDepthTest()

        context?.drawTexture(
            { id -> RenderLayer.getGuiTextured(id) },
            if (done) DONE_TEXTURE else NOT_DONE_TEXTURE,
            x, y,
            0f, 0f,
            16, 16,
            16, 16,
            Colors.WHITE
        )

        context?.drawItem(
            Registries.ITEM.get(Identifier.ofVanilla(itemId)).defaultStack,
            x + 18, y
        )

        this.nameLabel.x = x + 36
        this.nameLabel.y = y
        this.nameLabel.height = entryHeight
        this.nameLabel.render(context, mouseX, mouseY, tickDelta)

//        this.idLabel.x = x + 38 + this.nameLabel.width
//        this.idLabel.y = y
//        this.idLabel.height = entryHeight
//        this.idLabel.render(context, mouseX, mouseY, tickDelta)
    }

    override fun children(): List<Element?>? {
        return listOf(
            nameLabel,
//            idLabel
        )
    }

    enum class Type {
        ITEM, BLOCK;
    }

    companion object {
        val DONE_TEXTURE = Identifier.of(BACAPOverview.MOD_ID, "textures/gui/icons/done.png")
        val NOT_DONE_TEXTURE = Identifier.of(BACAPOverview.MOD_ID, "textures/gui/icons/not_done.png")
    }
}