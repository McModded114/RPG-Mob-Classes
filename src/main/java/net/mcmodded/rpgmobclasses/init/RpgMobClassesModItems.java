
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcmodded.rpgmobclasses.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.item.Item;

import net.mcmodded.rpgmobclasses.item.SonicBoomWandItem;
import net.mcmodded.rpgmobclasses.item.SonicBoomAxeItem;
import net.mcmodded.rpgmobclasses.item.SkillsInfoItemItem;
import net.mcmodded.rpgmobclasses.item.ClassInfoItemItem;
import net.mcmodded.rpgmobclasses.item.CherryWandItem;
import net.mcmodded.rpgmobclasses.item.BasicWandItem;
import net.mcmodded.rpgmobclasses.RpgMobClassesMod;

public class RpgMobClassesModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, RpgMobClassesMod.MODID);
	public static final RegistryObject<Item> GOBLIN_SPAWN_EGG = REGISTRY.register("goblin_spawn_egg", () -> new ForgeSpawnEggItem(RpgMobClassesModEntities.GOBLIN, -13272786, -10534865, new Item.Properties()));
	public static final RegistryObject<Item> MIMIC_SPAWN_EGG = REGISTRY.register("mimic_spawn_egg", () -> new ForgeSpawnEggItem(RpgMobClassesModEntities.MIMIC, -5278397, -9879510, new Item.Properties()));
	public static final RegistryObject<Item> CLASS_INFO_ITEM = REGISTRY.register("class_info_item", () -> new ClassInfoItemItem());
	public static final RegistryObject<Item> SKILLS_INFO_ITEM = REGISTRY.register("skills_info_item", () -> new SkillsInfoItemItem());
	public static final RegistryObject<Item> SONIC_BOOM_WAND = REGISTRY.register("sonic_boom_wand", () -> new SonicBoomWandItem());
	public static final RegistryObject<Item> SONIC_BOOM_AXE = REGISTRY.register("sonic_boom_axe", () -> new SonicBoomAxeItem());
	public static final RegistryObject<Item> BASIC_WAND = REGISTRY.register("basic_wand", () -> new BasicWandItem());
	public static final RegistryObject<Item> CHERRY_WAND = REGISTRY.register("cherry_wand", () -> new CherryWandItem());
}
