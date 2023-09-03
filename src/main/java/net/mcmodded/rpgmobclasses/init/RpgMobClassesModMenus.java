
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcmodded.rpgmobclasses.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcmodded.rpgmobclasses.world.inventory.SetProfessionMenu;
import net.mcmodded.rpgmobclasses.world.inventory.SetClassMenu;
import net.mcmodded.rpgmobclasses.world.inventory.ClassInfoMenu;
import net.mcmodded.rpgmobclasses.RpgMobClassesMod;

public class RpgMobClassesModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, RpgMobClassesMod.MODID);
	public static final RegistryObject<MenuType<SetClassMenu>> SET_CLASS = REGISTRY.register("set_class", () -> IForgeMenuType.create(SetClassMenu::new));
	public static final RegistryObject<MenuType<ClassInfoMenu>> CLASS_INFO = REGISTRY.register("class_info", () -> IForgeMenuType.create(ClassInfoMenu::new));
	public static final RegistryObject<MenuType<SetProfessionMenu>> SET_PROFESSION = REGISTRY.register("set_profession", () -> IForgeMenuType.create(SetProfessionMenu::new));
}
