package com.jamiedev.mod.common;

import com.jamiedev.mod.common.entities.*;
import com.jamiedev.mod.common.init.*;
import com.jamiedev.mod.common.items.JamiesModItemGroup;
import com.jamiedev.mod.mixin.SpawnRestrictMixin;
import com.jamiedev.mod.common.network.SyncPlayerHookS2C;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.jamiedev.mod.common.init.JamiesModEntityTypes.*;

public class JamiesMod implements ModInitializer {
	public static String MOD_ID = "bygone";

	public static AnimalArmorItem.Type BIG_BEAK_ARMOR;

	@Override
	public void onInitialize() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		SpawnRestriction.register(SCUTTLE, SpawnLocationTypes.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ScuttleEntity::canSpawn);
		SpawnRestriction.register(GLARE, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, GlareEntity::canSpawn);
		SpawnRestriction.register(BIG_BEAK, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, BigBeakEntity::canSpawn);
		SpawnRestriction.register(TRILOBITE, SpawnLocationTypes.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, TrilobiteEntity::canSpawn);

		SpawnRestrictMixin.callRegister(COELACANTH, SpawnLocationTypes.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CoelacanthEntity::canSpawn);
		JamiesModBlocks.init();
		JamiesModBlockEntities.init();
		JamiesModItems.init();
		JamiesModEntityTypes.init();
		JamiesModEntityTypes.postInit();
		JamiesModBiomes.init();
		JamiesModItemGroup.registerItemgroups();
		JamiesModFeatures.init();
		JamiesModStructures.init();
		JamiesModParticleTypes.init();
		JamiesModPortals.init();
		JamiesModSoundEvents.init();
		JamiesModMisc.init();
		JamiesModCriteria.init();

//		JamiesModPaintings.bootstrap((Registerable<PaintingVariant>) RegistryKeys.PAINTING_VARIANT);

		JamiesMod.LOGGER.info("Registering Entities for " + JamiesMod.MOD_ID);

		PayloadTypeRegistry.playS2C().register(SyncPlayerHookS2C.PACkET_ID, SyncPlayerHookS2C.CODEC);
	}

	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static Identifier getModId(String id){
		return Identifier.of(MOD_ID, id);
	}

}

/**
 *
 *
 *
 * JAMIES MOD v7i643
 *
 * dimension
 *
 *
 */