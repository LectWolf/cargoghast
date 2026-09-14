package cc.mcii.mcmod.cargoghast;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface GhastChestHolder {
	boolean cargoghast$hasChest();

	void cargoghast$setHasChest(boolean value);

	SimpleContainer cargoghast$chest();

	boolean cargoghast$stillValid(Player player);

	ItemStack cargoghast$chestItem();

	void cargoghast$dropOnRemove(Entity.RemovalReason reason);

	void cargoghast$open(Player player);
}
