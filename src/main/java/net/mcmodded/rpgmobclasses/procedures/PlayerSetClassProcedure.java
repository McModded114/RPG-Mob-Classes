package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcmodded.rpgmobclasses.world.inventory.SetClassMenu;
import net.mcmodded.rpgmobclasses.init.RpgMobClassesModItems;
import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

import javax.annotation.Nullable;

import io.netty.buffer.Unpooled;

@Mod.EventBusSubscriber
public class PlayerSetClassProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity().level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean foundItem = false;
		if (!(entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(RpgMobClassesModItems.CLASS_INFO_ITEM.get())) : false)) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(RpgMobClassesModItems.CLASS_INFO_ITEM.get());
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.SWORDSKILL.get()).getBaseValue() != 0) {
			entity.getPersistentData().putString("Class", "Knight");
		}
		if (((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.SPEED.get()).getBaseValue() == 75) {
			entity.getPersistentData().putString("Class", "Scout");
		}
		if (((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.RAGE.get()).getBaseValue() != 0) {
			entity.getPersistentData().putString("Class", "Slayer");
		}
		if (((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.MAXMANA.get()).getBaseValue() != 0) {
			entity.getPersistentData().putString("Class", "Mage");
		}
		if (((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.SMITHINGSKILL.get()).getBaseValue() != 0) {
			entity.getPersistentData().putString("Profession", "Blacksmith");
		}
		if ((entity.getPersistentData().getString("Class")).equals("")) {
			{
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("SetClass");
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new SetClassMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			}
		}
	}
}
