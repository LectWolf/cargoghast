package cc.mcii.mcmod.cargoghast;

import cc.mcii.mcmod.cargoghast.net.SyncChestPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.entity.Entity;

public final class CargoGhastClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(SyncChestPayload.TYPE, (payload, context) ->
			context.client().execute(() -> {
				if (context.player() == null) {
					return;
				}
				Entity entity = context.player().level().getEntity(payload.entityId());
				if (entity instanceof GhastChestHolder holder) {
					holder.cargoghast$setHasChest(payload.hasChest());
				}
			}));
	}
}
