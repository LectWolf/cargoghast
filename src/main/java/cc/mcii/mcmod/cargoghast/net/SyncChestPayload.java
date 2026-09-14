package cc.mcii.mcmod.cargoghast.net;

import cc.mcii.mcmod.cargoghast.CargoGhast;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record SyncChestPayload(int entityId, boolean hasChest) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<SyncChestPayload> TYPE =
		new CustomPacketPayload.Type<>(CargoGhast.id("sync_chest"));
	public static final StreamCodec<RegistryFriendlyByteBuf, SyncChestPayload> CODEC =
		StreamCodec.composite(
			ByteBufCodecs.VAR_INT, SyncChestPayload::entityId,
			ByteBufCodecs.BOOL, SyncChestPayload::hasChest,
			SyncChestPayload::new
		);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
