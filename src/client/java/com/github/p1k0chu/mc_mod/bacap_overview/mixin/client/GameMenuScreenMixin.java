package com.github.p1k0chu.mc_mod.bacap_overview.mixin.client;

import com.github.p1k0chu.mc_mod.bacap_overview.screen.MainScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin {

    @Inject(at = @At("HEAD"), method = "initWidgets")
    private void initWidgets(CallbackInfo ci) {
        GameMenuScreen screen = (GameMenuScreen) (Object) this;
        screen.addDrawableChild(ButtonWidget.builder(
                        Text.of("BACAP Overview"),
                        (btn) -> MinecraftClient.getInstance().setScreen(new MainScreen(screen))
                )
                .position(5, 5)
                .size(120, 20)
                .build());
    }
}
