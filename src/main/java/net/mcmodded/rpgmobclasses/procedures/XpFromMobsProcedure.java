package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcmodded.rpgmobclasses.network.RpgMobClassesModVariables;
import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

import javax.annotation.Nullable;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class XpFromMobsProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level, event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if ((entity instanceof Monster || entity instanceof Animal || entity instanceof IronGolem || entity instanceof Player) && (sourceentity instanceof Monster || sourceentity instanceof Player)) {
			((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.XP.get())
					.setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.XP.get()).getBaseValue() + ((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.DROPPEDXP.get()).getValue()));
			if (((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.XP.get()).getBaseValue() >= ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() * 10
					* (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.XPLOSS.get()).getBaseValue())) {
				((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.LVL.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() + 1));
				((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.XP.get()).setBaseValue(0);
				((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.DROPPEDXP.get())
						.setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() * ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.DROPPEDXPTOLV.get()).getBaseValue()));
				if (sourceentity instanceof Monster) {
					if (!(sourceentity.getPersistentData().getString("Variant")).equals("")) {
						if (!(sourceentity.getPersistentData().getString("Class")).equals("")) {
							sourceentity.setCustomName(Component.literal(("[Lv:" + Math.round(((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue()) + "] "
									+ sourceentity.getPersistentData().getString("Variant") + sourceentity.getPersistentData().getString("Name") + " " + sourceentity.getPersistentData().getString("Class"))));
						} else {
							sourceentity.setCustomName(Component.literal(("[Lv:" + Math.round(((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue()) + "] "
									+ sourceentity.getPersistentData().getString("Variant") + sourceentity.getPersistentData().getString("Name"))));
						}
					} else {
						if (!(sourceentity.getPersistentData().getString("Class")).equals("")) {
							sourceentity.setCustomName(Component.literal(("[Lv:" + Math.round(((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue()) + "] " + sourceentity.getPersistentData().getString("Name")
									+ " " + sourceentity.getPersistentData().getString("Class"))));
						} else {
							sourceentity
									.setCustomName(Component.literal(("[Lv:" + Math.round(((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue()) + "] " + sourceentity.getPersistentData().getString("Name"))));
						}
					}
				}
				for (Entity entityiterator : new ArrayList<>(world.players())) {
					if (entityiterator instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(Component.literal((sourceentity.getDisplayName().getString() + " Has leveled up to lv: " + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue())), false);
				}
				if (Math.random() < 0.2) {
					RpgMobClassesModVariables.MapVariables.get(world).difficulty_modifier = RpgMobClassesModVariables.MapVariables.get(world).difficulty_modifier + 1;
					RpgMobClassesModVariables.MapVariables.get(world).syncData(world);
					for (Entity entityiterator : new ArrayList<>(world.players())) {
						if (entityiterator instanceof Player _player && !_player.level.isClientSide())
							_player.displayClientMessage(Component.literal(("Difficulty Modifier increased to " + RpgMobClassesModVariables.MapVariables.get(world).difficulty_modifier + "!")), false);
					}
				}
				if ((sourceentity.getPersistentData().getString("Class")).equals("Knight")) {
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.DEFENSE.get()).getBaseValue() + 1));
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.SWORDSKILL.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.SWORDSKILL.get()).getBaseValue() + 1));
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getBaseValue() + 1));
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITCHANCE.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITCHANCE.get()).getBaseValue() + 5));
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITDAMAGE.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITDAMAGE.get()).getBaseValue() + 5));
				}
				if ((sourceentity.getPersistentData().getString("Class")).equals("Slayer")) {
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getBaseValue() + 2));
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITCHANCE.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITCHANCE.get()).getBaseValue() + 5));
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITDAMAGE.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITDAMAGE.get()).getBaseValue() + 5));
				}
				if ((sourceentity.getPersistentData().getString("Class")).equals("Mage")) {
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.FIREDAMAGE.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.FIREDAMAGE.get()).getBaseValue() + 1));
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.ICEDAMAGE.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.ICEDAMAGE.get()).getBaseValue() + 1));
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.MAGICDAMAGE.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.MAGICDAMAGE.get()).getBaseValue() + 1));
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.MANA.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.MANA.get()).getBaseValue() + 5));
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.MAXMANA.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.MAXMANA.get()).getBaseValue() + 5));
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITCHANCE.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITCHANCE.get()).getBaseValue() + 5));
					if (((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.SPEED.get()).getBaseValue() <= 45) {
						((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.SPEED.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.SPEED.get()).getBaseValue() + 5));
					} else {
						((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.SPEED.get()).setBaseValue(50);
					}
					((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITDAMAGE.get()).setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITDAMAGE.get()).getBaseValue() + 5));
				}
				((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(
						(((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.BASEMAXHP.get()).getBaseValue() * (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 20)));
				((LivingEntity) sourceentity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
						.setBaseValue((((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.BASEDMG.get()).getBaseValue() * (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 20)));
				if (sourceentity instanceof LivingEntity _entity)
					_entity.setHealth((float) ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1)
							+ (((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.BASEMAXHP.get()).getBaseValue() * (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue() / 20)) / 2));
			}
		}
	}
}
