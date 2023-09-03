/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcmodded.rpgmobclasses.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcmodded.rpgmobclasses.RpgMobClassesMod;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RpgMobClassesModAttributes {
	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, RpgMobClassesMod.MODID);
	public static final RegistryObject<Attribute> STRENGTH = ATTRIBUTES.register("strength", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".strength", 0, 0, 1000)).setSyncable(true));
	public static final RegistryObject<Attribute> DEFENSE = ATTRIBUTES.register("defense", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".defense", 0, 0, 1000)).setSyncable(true));
	public static final RegistryObject<Attribute> FIREDAMAGE = ATTRIBUTES.register("fire_damage", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".fire_damage", 0, 0, 1000)).setSyncable(true));
	public static final RegistryObject<Attribute> MANA = ATTRIBUTES.register("mana", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".mana", 0, 0, 5000)).setSyncable(true));
	public static final RegistryObject<Attribute> MAXMANA = ATTRIBUTES.register("max_mana", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".max_mana", 0, 0, 5000)).setSyncable(true));
	public static final RegistryObject<Attribute> LVL = ATTRIBUTES.register("lvl", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".lvl", 0, 0, 1000)).setSyncable(true));
	public static final RegistryObject<Attribute> XP = ATTRIBUTES.register("xp", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".xp", 0, 0, 10000)).setSyncable(true));
	public static final RegistryObject<Attribute> CRITCHANCE = ATTRIBUTES.register("crit_chance", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".crit_chance", 0, 0, 100)).setSyncable(true));
	public static final RegistryObject<Attribute> CRITDAMAGE = ATTRIBUTES.register("crit_damage", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".crit_damage", 0, 0, 1000)).setSyncable(true));
	public static final RegistryObject<Attribute> DROPPEDXP = ATTRIBUTES.register("dropped_xp", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".dropped_xp", 0, 0, 5000)).setSyncable(true));
	public static final RegistryObject<Attribute> PROJECTILEDAMAGE = ATTRIBUTES.register("projectile_damage", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".projectile_damage", 0, 0, 1000)).setSyncable(true));
	public static final RegistryObject<Attribute> SWORDSKILL = ATTRIBUTES.register("sword_skill", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".sword_skill", 0, 0, 100)).setSyncable(true));
	public static final RegistryObject<Attribute> BASESPEED = ATTRIBUTES.register("basespeed", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".basespeed", 0, 0, 1000)).setSyncable(true));
	public static final RegistryObject<Attribute> SPEED = ATTRIBUTES.register("speed", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".speed", 0, 0, 1000)).setSyncable(true));
	public static final RegistryObject<Attribute> SMITHINGSKILL = ATTRIBUTES.register("smithing_skill", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".smithing_skill", 0, 0, 1000)).setSyncable(true));
	public static final RegistryObject<Attribute> RAGE = ATTRIBUTES.register("rage", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".rage", 0, 0, 10)).setSyncable(true));
	public static final RegistryObject<Attribute> BASEMAXHP = ATTRIBUTES.register("basemaxhp", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".basemaxhp", 0, 0, 1024)).setSyncable(true));
	public static final RegistryObject<Attribute> BASEDMG = ATTRIBUTES.register("basedmg", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".basedmg", 0, 0, 1024)).setSyncable(true));
	public static final RegistryObject<Attribute> XPLOSS = ATTRIBUTES.register("xploss", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".xploss", 0, 0, 1000)).setSyncable(true));
	public static final RegistryObject<Attribute> DROPPEDXPTOLV = ATTRIBUTES.register("dropped_xp_to_lv", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".dropped_xp_to_lv", 0, 0, 5000)).setSyncable(true));
	public static final RegistryObject<Attribute> ICEDAMAGE = ATTRIBUTES.register("ice_damage", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".ice_damage", 0, 0, 1000)).setSyncable(true));
	public static final RegistryObject<Attribute> VOIDDAMAGE = ATTRIBUTES.register("void_damage", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".void_damage", 0, 0, 1000)).setSyncable(true));
	public static final RegistryObject<Attribute> MAGICDAMAGE = ATTRIBUTES.register("magic_damage", () -> (new RangedAttribute("attribute." + RpgMobClassesMod.MODID + ".magic_damage", 0, 0, 1000)).setSyncable(true));

	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ATTRIBUTES.register(FMLJavaModLoadingContext.get().getModEventBus());
		});
	}

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
		List<EntityType<? extends LivingEntity>> entityTypes = event.getTypes();
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, STRENGTH.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, DEFENSE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, FIREDAMAGE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, MANA.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, MAXMANA.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, LVL.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, XP.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, CRITCHANCE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, CRITDAMAGE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class) || baseClass.isAssignableFrom(Animal.class)) {
				event.add(e, DROPPEDXP.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, PROJECTILEDAMAGE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, SWORDSKILL.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, BASESPEED.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, SPEED.get());
			}
		});
		event.add(EntityType.PLAYER, SMITHINGSKILL.get());
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, RAGE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, BASEMAXHP.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, BASEDMG.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, XPLOSS.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, DROPPEDXPTOLV.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, ICEDAMAGE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, VOIDDAMAGE.get());
			}
		});
		entityTypes.forEach((e) -> {
			Class<? extends Entity> baseClass = e.getBaseClass();
			if (baseClass.isAssignableFrom(Monster.class)) {
				event.add(e, MAGICDAMAGE.get());
			}
		});
	}

	@Mod.EventBusSubscriber
	private class Utils {
		@SubscribeEvent
		public static void persistAttributes(PlayerEvent.Clone event) {
			Player oldP = event.getOriginal();
			Player newP = (Player) event.getEntity();
			newP.getAttribute(STRENGTH.get()).setBaseValue(oldP.getAttribute(STRENGTH.get()).getBaseValue());
			newP.getAttribute(DEFENSE.get()).setBaseValue(oldP.getAttribute(DEFENSE.get()).getBaseValue());
			newP.getAttribute(FIREDAMAGE.get()).setBaseValue(oldP.getAttribute(FIREDAMAGE.get()).getBaseValue());
			newP.getAttribute(MANA.get()).setBaseValue(oldP.getAttribute(MANA.get()).getBaseValue());
			newP.getAttribute(MAXMANA.get()).setBaseValue(oldP.getAttribute(MAXMANA.get()).getBaseValue());
			newP.getAttribute(LVL.get()).setBaseValue(oldP.getAttribute(LVL.get()).getBaseValue());
			newP.getAttribute(XP.get()).setBaseValue(oldP.getAttribute(XP.get()).getBaseValue());
			newP.getAttribute(CRITCHANCE.get()).setBaseValue(oldP.getAttribute(CRITCHANCE.get()).getBaseValue());
			newP.getAttribute(CRITDAMAGE.get()).setBaseValue(oldP.getAttribute(CRITDAMAGE.get()).getBaseValue());
			newP.getAttribute(DROPPEDXP.get()).setBaseValue(oldP.getAttribute(DROPPEDXP.get()).getBaseValue());
			newP.getAttribute(PROJECTILEDAMAGE.get()).setBaseValue(oldP.getAttribute(PROJECTILEDAMAGE.get()).getBaseValue());
			newP.getAttribute(SWORDSKILL.get()).setBaseValue(oldP.getAttribute(SWORDSKILL.get()).getBaseValue());
			newP.getAttribute(BASESPEED.get()).setBaseValue(oldP.getAttribute(BASESPEED.get()).getBaseValue());
			newP.getAttribute(SPEED.get()).setBaseValue(oldP.getAttribute(SPEED.get()).getBaseValue());
			newP.getAttribute(SMITHINGSKILL.get()).setBaseValue(oldP.getAttribute(SMITHINGSKILL.get()).getBaseValue());
			newP.getAttribute(RAGE.get()).setBaseValue(oldP.getAttribute(RAGE.get()).getBaseValue());
			newP.getAttribute(BASEMAXHP.get()).setBaseValue(oldP.getAttribute(BASEMAXHP.get()).getBaseValue());
			newP.getAttribute(BASEDMG.get()).setBaseValue(oldP.getAttribute(BASEDMG.get()).getBaseValue());
			newP.getAttribute(XPLOSS.get()).setBaseValue(oldP.getAttribute(XPLOSS.get()).getBaseValue());
			newP.getAttribute(DROPPEDXPTOLV.get()).setBaseValue(oldP.getAttribute(DROPPEDXPTOLV.get()).getBaseValue());
			newP.getAttribute(ICEDAMAGE.get()).setBaseValue(oldP.getAttribute(ICEDAMAGE.get()).getBaseValue());
			newP.getAttribute(VOIDDAMAGE.get()).setBaseValue(oldP.getAttribute(VOIDDAMAGE.get()).getBaseValue());
			newP.getAttribute(MAGICDAMAGE.get()).setBaseValue(oldP.getAttribute(MAGICDAMAGE.get()).getBaseValue());
		}
	}
}
