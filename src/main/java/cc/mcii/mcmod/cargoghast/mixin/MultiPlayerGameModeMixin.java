package cc.mcii.mcmod.cargoghast.mixin;

import cc.mcii.mcmod.cargoghast.GhastChestHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiPlayerGameMode.class)
public abstract class MultiPlayerGameModeMixin {
	@Shadow
	@Final
	private Minecraft minecraft;

	@Inject(method = "isServerControlledInventory", at = @At("HEAD"), cancellable = true)
	private void cargoghast$ghastChest(CallbackInfoReturnable<Boolean> cir) {
		if (this.minecraft.player == null) {
			return;
		}
		if (this.minecraft.player.getVehicle() instanceof GhastChestHolder holder && holder.cargoghast$hasChest()) {
			cir.setReturnValue(true);
		}
	}
}
