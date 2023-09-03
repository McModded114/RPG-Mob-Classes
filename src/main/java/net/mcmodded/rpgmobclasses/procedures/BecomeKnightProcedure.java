package net.mcmodded.rpgmobclasses.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

public class BecomeKnightProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putString("Class", "Knight");
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).setBaseValue(1);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.XP.get()).setBaseValue(0);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).setBaseValue(2);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).setBaseValue(2);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.MAXMANA.get()).setBaseValue(0);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.MANA.get()).setBaseValue(0);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.FIREDAMAGE.get()).setBaseValue(0);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.ICEDAMAGE.get()).setBaseValue(0);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.MAGICDAMAGE.get()).setBaseValue(0);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.PROJECTILEDAMAGE.get()).setBaseValue(0);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.VOIDDAMAGE.get()).setBaseValue(0);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.CRITCHANCE.get()).setBaseValue(5);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.CRITDAMAGE.get()).setBaseValue(25);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DROPPEDXP.get()).setBaseValue(5);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.SWORDSKILL.get()).setBaseValue(5);
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.SPEED.get()).setBaseValue(0);
		OpenGuiSetProfessionProcedure.execute(world, entity);
	}
}
