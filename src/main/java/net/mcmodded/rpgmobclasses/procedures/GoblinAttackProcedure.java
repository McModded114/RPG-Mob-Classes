package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;

import net.mcmodded.rpgmobclasses.entity.GoblinEntity;
import net.mcmodded.rpgmobclasses.RpgMobClassesMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class GoblinAttackProcedure {
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
		if (entity instanceof GoblinEntity) {
			if (Math.random() < 0.7) {
				if (entity instanceof GoblinEntity) {
					((GoblinEntity) entity).setAnimation("hurt1");
				}
				if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 9, false, true));
				RpgMobClassesMod.queueServerWork(40, () -> {
					if (entity instanceof LivingEntity _entity)
						_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
				});
			} else {
				if (entity instanceof GoblinEntity) {
					((GoblinEntity) entity).setAnimation("hurt2");
				}
				if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 9, false, true));
				RpgMobClassesMod.queueServerWork(50, () -> {
					if (entity instanceof LivingEntity _entity)
						_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
				});
			}
		}
		if (sourceentity instanceof GoblinEntity) {
			if (damagesource.is(DamageTypes.MOB_ATTACK)) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
				if (!((((GoblinEntity) sourceentity).animationprocedure).equals("hurt") && (((GoblinEntity) sourceentity).animationprocedure).equals("hurt2"))) {
					if (Math.random() < 0.5) {
						if (sourceentity instanceof GoblinEntity) {
							((GoblinEntity) sourceentity).setAnimation("leftpunch");
						}
					} else {
						if (sourceentity instanceof GoblinEntity) {
							((GoblinEntity) sourceentity).setAnimation("rightpunch");
						}
					}
					RpgMobClassesMod.queueServerWork(16, () -> {
						NormalAttackProcedure.execute(world, entity, sourceentity, amount);
					});
				}
			}
		}
	}
}
