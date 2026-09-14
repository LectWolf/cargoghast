package cc.mcii.mcmod.cargoghast.mixin;

import cc.mcii.mcmod.cargoghast.GhastChestHolder;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityRemoveMixin {
	@Inject(method = "remove", at = @At("HEAD"))
	private void cargoghast$dropChest(Entity.RemovalReason reason, CallbackInfo ci) {
		if ((Object) this instanceof GhastChestHolder holder) {
			holder.cargoghast$dropOnRemove(reason);
		}
	}
}
