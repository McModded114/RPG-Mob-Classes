package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BerserkerRageProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level, event.getEntity(), event.getSource().getEntity(), event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, double amount) {
		execute(null, world, entity, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity, double amount) {
		if (entity == null || sourceentity == null)
			return;
		if (!world.isClientSide()) {
			if (sourceentity instanceof Monster || sourceentity instanceof Player) {
				if ((sourceentity.getPersistentData().getString("Class")).equals("Slayer")) {
					if (((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.RAGE.get()).getBaseValue() > 1) {
						((LivingHurtEvent) event).setAmount((Math.round((amount * (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.RAGE.get()).getBaseValue() / 10)))));
						((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.RAGE.get()).setBaseValue(1);
					}
				}
				if ((entity.getPersistentData().getString("Class")).equals("Slayer")) {
					if (Math.random() < 0.7) {
						((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.RAGE.get()).setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.RAGE.get()).getBaseValue() + 1));
					}
				}
			}
		}
	}
}
