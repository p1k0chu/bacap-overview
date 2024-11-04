package com.github.p1k0chu.mc_mod.bacap_overview;

import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementProgress;

public interface ClientAdvancementProgressGetter {
    default AdvancementProgress bacap_overview$getAdvancementProgress(AdvancementEntry advancementEntry) {
        return null;
    }
}
