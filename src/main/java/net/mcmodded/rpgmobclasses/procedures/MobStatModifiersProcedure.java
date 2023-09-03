package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MobStatModifiersProcedure {
	@SubscribeEvent
	public static void onEntitySpawned(EntityJoinLevelEvent event) {
		execute(event, event.getLevel(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		String variant = "";
		String name = "";
		String classRpg = "";
		String DisplayName = "";
		if (entity instanceof Monster || entity instanceof IronGolem) {
			if (!world.isClientSide()) {
				if (Math.random() < 0.9) {
					if (Math.random() < 0.7) {
						if (Math.random() < 0.7) {
							if (Math.random() < 0.7) {
								if (Math.random() < 0.5) {
									entity.getPersistentData().putString("Variant", "All Seeing ");
									((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE)
											.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE).getBaseValue() * 4));
								} else {
									entity.getPersistentData().putString("Variant", "Swift ");
									((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
											.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue() * 1.5));
								}
							} else {
								if (Math.random() < 0.5) {
									entity.getPersistentData().putString("Variant", "Tough ");
									((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
											.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue() * 2));
									((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).getBaseValue() * 2));
								} else {
									entity.getPersistentData().putString("Variant", "Blind Swift ");
									((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
											.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue() * 1.5));
									((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE).setBaseValue(2);
								}
							}
						} else {
							if (Math.random() < 0.7) {
								if (Math.random() < 0.5) {
									entity.getPersistentData().putString("Variant", "Blind ");
									((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE).setBaseValue(2);
								} else {
									entity.getPersistentData().putString("Variant", "Slow ");
									((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
											.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue() / 1.5));
								}
							} else {
								if (Math.random() < 0.5) {
									entity.getPersistentData().putString("Variant", "Fragile ");
									((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
											.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue() / 2));
									((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).getBaseValue() / 2));
								} else {
									entity.getPersistentData().putString("Variant", "All Seeing Slow ");
									((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
											.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue() / 1.5));
									((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE)
											.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE).getBaseValue() * 4));
								}
							}
						}
					} else {
						if (Math.random() < 0.7) {
							if (Math.random() < 0.7) {
								entity.getPersistentData().putString("Variant", "Strong ");
								((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
										.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getBaseValue() * 1.5));
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).setBaseValue(Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getBaseValue() * 2));
							} else {
								entity.getPersistentData().putString("Variant", "Weak ");
								((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
										.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getBaseValue() / 1.5));
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).setBaseValue(Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getBaseValue() / 2));
							}
						} else {
							if (Math.random() < 0.5) {
								entity.getPersistentData().putString("Variant", "Strong Fragile ");
								((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
										.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue() / 2));
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).getBaseValue() / 2));
								((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
										.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getBaseValue() * 1.5));
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).setBaseValue(Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getBaseValue() * 2));
							} else {
								entity.getPersistentData().putString("Variant", "Tough Weak ");
								((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
										.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue() * 2));
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).getBaseValue() * 2));
								((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
										.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getBaseValue() / 1.5));
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).setBaseValue(Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getBaseValue() / 2));
							}
						}
					}
					if (!(entity.getPersistentData().getString("Class")).equals("")) {
						entity.setCustomName(Component.literal(("[Lv:" + Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue()) + "] " + entity.getPersistentData().getString("Variant")
								+ entity.getPersistentData().getString("Name") + " " + entity.getPersistentData().getString("Class"))));
					} else {
						entity.setCustomName(Component.literal(("[Lv:" + Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue()) + "] " + entity.getPersistentData().getString("Variant")
								+ entity.getPersistentData().getString("Name"))));
					}
				}
				((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEDMG.get()).setBaseValue(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getBaseValue());
				((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASESPEED.get()).setBaseValue(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue());
				((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEMAXHP.get()).setBaseValue(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue());
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
						.setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEMAXHP.get()).getBaseValue() * (1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 20)));
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
						.setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEDMG.get()).getBaseValue() * (1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 20)));
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth((float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getValue());
				variant = entity.getPersistentData().getString("Variant");
				name = entity.getPersistentData().getString("Name");
				classRpg = entity.getPersistentData().getString("Class");
				DisplayName = entity.getDisplayName().getString();
			} else {
				entity.getPersistentData().putString("Variant", variant);
				entity.getPersistentData().putString("Name", name);
				entity.getPersistentData().putString("Class", classRpg);
				entity.setCustomName(Component.literal(DisplayName));
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth((float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getValue());
			}
		}
	}
}
