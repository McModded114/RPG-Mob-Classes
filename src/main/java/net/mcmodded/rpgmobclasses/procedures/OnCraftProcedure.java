package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class OnCraftProcedure {
	@SubscribeEvent
	public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
		execute(event, event.getEntity().level, event.getEntity(), event.getCrafting());
	}

	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
		execute(null, world, entity, itemstack);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if (entity instanceof Player) {
				if ((entity.getPersistentData().getString("Profession")).equals("Blacksmith")) {
					if (itemstack.getItem() instanceof SwordItem) {
						itemstack.getOrCreateTag().putDouble("blacksmithbonus", 2);
					}
					if (itemstack.getItem() instanceof AxeItem) {
						itemstack.getOrCreateTag().putDouble("blacksmithbonus", 2);
					}
					if (itemstack.getItem() instanceof BowItem) {
						itemstack.getOrCreateTag().putDouble("blacksmithbonus", 2);
					}
					if (itemstack.getItem() instanceof CrossbowItem) {
						itemstack.getOrCreateTag().putDouble("blacksmithbonus", 2);
					}
				}
			}
		}
	}
}
