package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;

import net.mcmodded.rpgmobclasses.network.RpgMobClassesModVariables;
import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class InitalMobStatsProcedure {
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
		String name = "";
		String classRpg = "";
		String DisplayName = "";
		if (!world.isClientSide()) {
			if (entity instanceof Player) {
				((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DROPPEDXPTOLV.get()).setBaseValue(15);
				if (((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEDMG.get()).getBaseValue() == 0) {
					((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEDMG.get()).setBaseValue(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getBaseValue());
				}
				if (((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASESPEED.get()).getBaseValue() == 0) {
					((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASESPEED.get()).setBaseValue(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue());
				}
				if (((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEMAXHP.get()).getBaseValue() == 0) {
					((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEMAXHP.get()).setBaseValue(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue());
				}
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
						.setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEMAXHP.get()).getBaseValue() * (1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 20)));
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
						.setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEDMG.get()).getBaseValue() * (1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 20)));
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
						.setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASESPEED.get()).getBaseValue() * (1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.SPEED.get()).getBaseValue() / 100)));
			}
			if (entity instanceof Monster || entity instanceof IronGolem) {
				if ((entity.getPersistentData().getString("Name")).equals("")) {
					if (((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue() >= 100) {
						((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DROPPEDXPTOLV.get())
								.setBaseValue((5 * (((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue() / 20)));
					} else {
						((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DROPPEDXPTOLV.get()).setBaseValue(5);
					}
					if (Math.random() < 0.6) {
						((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).setBaseValue((1 + RpgMobClassesModVariables.MapVariables.get(world).difficulty_modifier));
					} else {
						if (Math.random() < 0.7) {
							((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).setBaseValue((3 + RpgMobClassesModVariables.MapVariables.get(world).difficulty_modifier));
						} else {
							((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).setBaseValue((5 + RpgMobClassesModVariables.MapVariables.get(world).difficulty_modifier));
						}
					}
					((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DROPPEDXP.get())
							.setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DROPPEDXPTOLV.get()).getBaseValue() * ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue()));
					((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.CRITCHANCE.get()).setBaseValue((5 + Math.round(1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 10)));
					((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.CRITDAMAGE.get()).setBaseValue((25 + Math.round(1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 10)));
					if (Math.random() < 0.1) {
						if (Math.random() < 0.8) {
							entity.getPersistentData().putString("Name", ("Enraged " + entity.getDisplayName().getString()));
							((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.FIREDAMAGE.get()).setBaseValue((2.5 * ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.FIREDAMAGE.get()).getBaseValue()));
							((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.ICEDAMAGE.get()).setBaseValue((2.5 * ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.ICEDAMAGE.get()).getBaseValue()));
							((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.MAGICDAMAGE.get()).setBaseValue((2.5 * ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.MAGICDAMAGE.get()).getBaseValue()));
							((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
									.setBaseValue((2.5 * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getBaseValue()));
							((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).setBaseValue((1.5 * ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getBaseValue()));
						} else {
							entity.getPersistentData().putString("Name", ("Elite " + entity.getDisplayName().getString()));
							((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
									.setBaseValue((5 * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue()));
							((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.FIREDAMAGE.get()).setBaseValue((2 * ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.FIREDAMAGE.get()).getBaseValue()));
							((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
									.setBaseValue((2 * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getBaseValue()));
							((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).setBaseValue((3 * ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getBaseValue()));
						}
						if (entity instanceof LivingEntity _entity)
							_entity.setHealth((float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getValue());
					} else {
						entity.getPersistentData().putString("Name", (entity.getDisplayName().getString()));
					}
				}
				if ((entity.getPersistentData().getString("Class")).equals("")) {
					if (Math.random() < 0.4) {
						if (Math.random() < 0.3) {
							if (Math.random() < 0.7) {
								entity.getPersistentData().putString("Class", "Knight");
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).setBaseValue(Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() * 2));
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).setBaseValue(Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() * 2));
								if (entity instanceof LivingEntity _entity) {
									ItemStack _setstack = new ItemStack(Items.SHIELD);
									_setstack.setCount(1);
									_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
									if (_entity instanceof Player _player)
										_player.getInventory().setChanged();
								}
							} else {
								entity.getPersistentData().putString("Class", "Berserker");
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).setBaseValue(Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() * 10));
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).setBaseValue(Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() * 2));
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.SPEED.get()).setBaseValue(20);
							}
						} else {
							entity.getPersistentData().putString("Class", "Mage");
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack = new ItemStack(Items.BOOK);
								_setstack.setCount(1);
								_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
							((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.MANA.get()).setBaseValue(Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() * 5));
							((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.MAXMANA.get()).setBaseValue(Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() * 5));
							((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.FIREDAMAGE.get()).setBaseValue(Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() * 2));
							if (((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() * 5 <= 50) {
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.SPEED.get()).setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() * 5));
							} else {
								((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.SPEED.get()).setBaseValue(50);
							}
						}
						entity.setCustomName(Component.literal(("[Lv:" + Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue()) + "] " + entity.getPersistentData().getString("Name") + " "
								+ entity.getPersistentData().getString("Class"))));
					}
					entity.setCustomName(Component.literal(("[Lv:" + Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue()) + "] " + entity.getPersistentData().getString("Name"))));
				}
				DisplayName = entity.getDisplayName().getString();
				name = entity.getPersistentData().getString("Name");
				classRpg = entity.getPersistentData().getString("Class");
				((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEDMG.get()).setBaseValue(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getBaseValue());
				((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASESPEED.get()).setBaseValue(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue());
				((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEMAXHP.get()).setBaseValue(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue());
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
						.setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEMAXHP.get()).getBaseValue() * (1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 20)));
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
						.setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASESPEED.get()).getBaseValue() * (1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.SPEED.get()).getBaseValue() / 100)));
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
						.setBaseValue((((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.BASEDMG.get()).getBaseValue() * (1 + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 20)));
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth((float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getValue());
			}
		} else {
			if (entity instanceof Monster || entity instanceof IronGolem) {
				entity.getPersistentData().putString("Name", name);
				entity.getPersistentData().putString("Class", classRpg);
				entity.setCustomName(Component.literal(DisplayName));
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth((float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getValue());
			}
		}
	}
}
