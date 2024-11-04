package com.github.p1k0chu.mc_mod.bacap_overview.mixin.client;

import com.github.p1k0chu.mc_mod.bacap_overview.ClientAdvancementProgressGetter;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.client.network.ClientAdvancementManager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(ClientAdvancementManager.class)
public class ClientAdvancementManagerMixin implements ClientAdvancementProgressGetter {
    @Final
    @Shadow
    private Map<AdvancementEntry, AdvancementProgress> advancementProgresses;

    @Override
    public @Nullable AdvancementProgress bacap_overview$getAdvancementProgress(AdvancementEntry advancementEntry) {
        try {
            return advancementProgresses.get(advancementEntry);
        } catch (NullPointerException | ClassCastException e) {
            return null;
        }
    }
}
