package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DamageCalculationProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getDirectEntity(), event.getSource().getEntity(), event.getAmount());
		}
	}

	public static void execute(Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
		execute(null, entity, immediatesourceentity, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
		if (entity == null || immediatesourceentity == null || sourceentity == null)
			return;
		if (sourceentity instanceof Player || sourceentity instanceof IronGolem || sourceentity instanceof Monster) {
			if (immediatesourceentity instanceof Arrow || immediatesourceentity instanceof SpectralArrow) {
				if (amount != 0) {
					((LivingHurtEvent) event).setAmount((Math.round(((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.PROJECTILEDAMAGE.get()).getValue())));
				}
			} else {
				if (!(entity instanceof LivingEntity && ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.SWORDSKILL.get()) != null
						&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof SwordItem)) {
					if (Math.random() < ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITCHANCE.get()).getValue() / 100) {
						((LivingHurtEvent) event).setAmount((Math.round((amount * (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getValue() / 100)
								* (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITDAMAGE.get()).getValue() / 100)))));
					} else {
						((LivingHurtEvent) event).setAmount((Math.round((amount * (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getValue() / 100)))));
					}
				} else {
					if (Math.random() < ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITCHANCE.get()).getValue() / 100) {
						((LivingHurtEvent) event).setAmount((Math.round((amount * (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getValue() / 100)
								* (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITDAMAGE.get()).getValue() / 100)
								* (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.SWORDSKILL.get()).getValue() / 100)))));
					} else {
						((LivingHurtEvent) event).setAmount((Math.round((amount * (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getValue() / 100)
								* (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.SWORDSKILL.get()).getValue() / 100)))));
					}
				}
			}
		}
	}
}
