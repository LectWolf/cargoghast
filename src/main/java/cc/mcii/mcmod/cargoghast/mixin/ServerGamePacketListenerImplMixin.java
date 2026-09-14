package cc.mcii.mcmod.cargoghast.mixin;

import cc.mcii.mcmod.cargoghast.GhastChestHolder;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerGamePacketListenerImplMixin {
	@Shadow
	public ServerPlayer player;

	@Inject(method = "handlePlayerCommand", at = @At("TAIL"))
	private void cargoghast$openGhastChest(ServerboundPlayerCommandPacket packet, CallbackInfo ci) {
		if (packet.getAction() != ServerboundPlayerCommandPacket.Action.OPEN_INVENTORY) {
			return;
		}
		if (this.player.getVehicle() instanceof GhastChestHolder holder) {
			holder.cargoghast$open(this.player);
		}
	}
}
