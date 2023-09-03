package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DefenseCalculationProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getEntity(), event.getAmount());
		}
	}

	public static void execute(Entity entity, Entity sourceentity, double amount) {
		execute(null, entity, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity, double amount) {
		if (entity == null || sourceentity == null)
			return;
		if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()) != null && (sourceentity instanceof Player || sourceentity instanceof IronGolem || sourceentity instanceof Monster)) {
			((LivingHurtEvent) event).setAmount((Math.round((amount / (1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).getValue() / 100)))));
		}
	}
}
