package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.LevelAccessor;
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

import net.mcmodded.rpgmobclasses.world.inventory.SetProfessionMenu;
import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

import io.netty.buffer.Unpooled;

public class OpenGuiSetProfessionProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player)
			_player.closeContainer();
		{
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(entity.getX(), entity.getY(), entity.getZ());
				NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("SetProfession");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new SetProfessionMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
				.setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEMAXHP.get()).getBaseValue() * (1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 20)));
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
				.setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEDMG.get()).getBaseValue() * (1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 20)));
		if (entity instanceof LivingEntity _entity)
			_entity.setHealth((float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getValue());
	}
}
