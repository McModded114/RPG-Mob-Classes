package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;

import net.mcmodded.rpgmobclasses.entity.MimicEntity;
import net.mcmodded.rpgmobclasses.RpgMobClassesMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MimicAttackProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level, event.getSource(), event.getEntity(), event.getSource().getEntity(), event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, DamageSource damagesource, Entity entity, Entity sourceentity, double amount) {
		execute(null, world, damagesource, entity, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, DamageSource damagesource, Entity entity, Entity sourceentity, double amount) {
		if (entity == null || sourceentity == null)
			return;
		double random = 0;
		if (sourceentity instanceof MimicEntity) {
			if (damagesource.is(DamageTypes.MOB_ATTACK)) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
				random = Math.random();
				if (random < 0.35) {
					if (sourceentity instanceof MimicEntity) {
						((MimicEntity) sourceentity).setAnimation("attack1");
					}
				} else if (random < 0.3) {
					if (sourceentity instanceof MimicEntity) {
						((MimicEntity) sourceentity).setAnimation("attack2");
					}
				} else {
					if (sourceentity instanceof MimicEntity) {
						((MimicEntity) sourceentity).setAnimation("attack3");
					}
				}
				RpgMobClassesMod.queueServerWork(10, () -> {
					NormalAttackProcedure.execute(world, entity, sourceentity, amount);
				});
			}
		}
	}
}
