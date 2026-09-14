package cc.mcii.mcmod.cargoghast.mixin;

import cc.mcii.mcmod.cargoghast.CargoGhast;
import cc.mcii.mcmod.cargoghast.GhastChestHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
//? if >=1.21.11 {
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
//?} else {
/*import net.minecraft.world.entity.animal.HappyGhast;
*///?}
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HappyGhast.class)
public abstract class HappyGhastMixin implements GhastChestHolder {
	@Unique
	private boolean cargoghast$hasChest;

	@Unique
	private final SimpleContainer cargoghast$chest = new SimpleContainer(27) {
		@Override
		public boolean stillValid(Player player) {
			return cargoghast$stillValid(player);
		}
	};

	@Override
	public boolean cargoghast$hasChest() {
		return this.cargoghast$hasChest;
	}

	@Override
	public void cargoghast$setHasChest(boolean value) {
		this.cargoghast$hasChest = value;
		CargoGhast.syncChest((Entity) (Object) this, value);
	}

	@Override
	public SimpleContainer cargoghast$chest() {
		return this.cargoghast$chest;
	}

	@Override
	public boolean cargoghast$stillValid(Player player) {
		HappyGhast ghast = (HappyGhast) (Object) this;
		return this.cargoghast$hasChest && ghast.isAlive() && player.distanceTo(ghast) < 8.0F;
	}

	@Override
	public ItemStack cargoghast$chestItem() {
		return new ItemStack(Items.CHEST);
	}

	@Inject(method = "getRiddenInput", at = @At("RETURN"), cancellable = true)
	private void cargoghast$faster(Player player, Vec3 input, CallbackInfoReturnable<Vec3> cir) {
		Vec3 value = cir.getReturnValue().scale(2.8D);
		if (player.isShiftKeyDown()) {
			value = value.add(0.0D, -0.45D, 0.0D);
		}
		cir.setReturnValue(value);
	}

	@ModifyConstant(method = "tickRidden", constant = @Constant(floatValue = 0.08F))
	private float cargoghast$turn(float original) {
		return 0.32F;
	}

	@Inject(method = "isOnStillTimeout", at = @At("HEAD"), cancellable = true)
	private void cargoghast$noTimeout(CallbackInfoReturnable<Boolean> cir) {
		HappyGhast ghast = (HappyGhast) (Object) this;
		if (ghast.getFirstPassenger() instanceof Player) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "checkRestriction", at = @At("HEAD"), cancellable = true)
	private void cargoghast$noLeashHome(CallbackInfo ci) {
		HappyGhast ghast = (HappyGhast) (Object) this;
		if (ghast.isVehicle()) {
			ci.cancel();
		}
	}

	@Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
	private void cargoghast$chest(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
		HappyGhast ghast = (HappyGhast) (Object) this;
		if (ghast.isBaby() || !(ghast.level() instanceof ServerLevel)) {
			return;
		}
		ItemStack held = player.getItemInHand(hand);
		if (!this.cargoghast$hasChest && held.is(Items.CHEST) && player.isShiftKeyDown()) {
			if (!player.getAbilities().instabuild) {
				held.shrink(1);
			}
			this.cargoghast$setHasChest(true);
			cir.setReturnValue(InteractionResult.SUCCESS);
			return;
		}
		if (this.cargoghast$hasChest && player.isShiftKeyDown() && held.isEmpty()) {
			this.cargoghast$open(player);
			cir.setReturnValue(InteractionResult.SUCCESS);
		}
	}

	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
	private void cargoghast$save(ValueOutput output, CallbackInfo ci) {
		output.putBoolean("CargoGhastChest", this.cargoghast$hasChest);
		if (this.cargoghast$hasChest) {
			ValueOutput.TypedOutputList<ItemStack> list = output.list("CargoGhastItems", ItemStack.OPTIONAL_CODEC);
			for (int i = 0; i < this.cargoghast$chest.getContainerSize(); i++) {
				list.add(this.cargoghast$chest.getItem(i));
			}
		}
	}

	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	private void cargoghast$load(ValueInput input, CallbackInfo ci) {
		this.cargoghast$hasChest = input.getBooleanOr("CargoGhastChest", false);
		this.cargoghast$chest.clearContent();
		if (!this.cargoghast$hasChest) {
			return;
		}
		int i = 0;
		for (ItemStack stack : input.listOrEmpty("CargoGhastItems", ItemStack.OPTIONAL_CODEC)) {
			if (i >= this.cargoghast$chest.getContainerSize()) {
				break;
			}
			this.cargoghast$chest.setItem(i++, stack);
		}
	}

	@Override
	public void cargoghast$dropOnRemove(Entity.RemovalReason reason) {
		HappyGhast ghast = (HappyGhast) (Object) this;
		if (!(ghast.level() instanceof ServerLevel serverLevel) || !this.cargoghast$hasChest) {
			return;
		}
		if (reason != Entity.RemovalReason.KILLED && reason != Entity.RemovalReason.DISCARDED) {
			return;
		}
		ghast.spawnAtLocation(serverLevel, Items.CHEST);
		Containers.dropContents(serverLevel, ghast.blockPosition(), this.cargoghast$chest);
		this.cargoghast$setHasChest(false);
	}

	@Override
	public void cargoghast$open(Player player) {
		if (!this.cargoghast$hasChest) {
			return;
		}
		player.openMenu(new SimpleMenuProvider(
			(id, inv, p) -> ChestMenu.threeRows(id, inv, this.cargoghast$chest),
			Component.translatable("container.cargoghast.chest")
		));
	}
}
