package com.jamiedev.mod.common.init;

import com.jamiedev.mod.common.JamiesMod;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class JamiesModDamageTypes {
    public static final RegistryKey<DamageType> SPIKE = register("spike");

    private static RegistryKey<DamageType> register(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, JamiesMod.getModId(name));
    }
}