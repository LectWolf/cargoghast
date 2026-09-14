package cc.mcii.mcmod.cargoghast;

import cc.mcii.mcmod.cargoghast.net.SyncChestPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.EntityTrackingEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? if >=1.21.11 {
import net.minecraft.resources.Identifier;
//?} else {
/*import net.minecraft.resources.ResourceLocation;
*///?}

public final class CargoGhast implements ModInitializer {
	public static final String MOD_ID = "cargoghast";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		//? if >=26.1 {
		PayloadTypeRegistry.clientboundPlay().register(SyncChestPayload.TYPE, SyncChestPayload.CODEC);
		//?} else {
		/*PayloadTypeRegistry.playS2C().register(SyncChestPayload.TYPE, SyncChestPayload.CODEC);
		*///?}

		EntityTrackingEvents.START_TRACKING.register((entity, player) -> {
			if (entity instanceof GhastChestHolder holder) {
				ServerPlayNetworking.send(player, new SyncChestPayload(entity.getId(), holder.cargoghast$hasChest()));
			}
		});

		LOGGER.info("Cargo Ghast loaded");
	}

	public static void syncChest(Entity entity, boolean hasChest) {
		if (entity.level().isClientSide()) {
			return;
		}
		SyncChestPayload payload = new SyncChestPayload(entity.getId(), hasChest);
		for (var player : PlayerLookup.tracking(entity)) {
			ServerPlayNetworking.send(player, payload);
		}
	}

	//? if >=1.21.11 {
	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
	//?} else {
/*	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
	*///?}
}
