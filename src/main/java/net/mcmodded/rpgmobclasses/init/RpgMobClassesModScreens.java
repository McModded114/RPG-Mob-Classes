
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcmodded.rpgmobclasses.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcmodded.rpgmobclasses.client.gui.SetProfessionScreen;
import net.mcmodded.rpgmobclasses.client.gui.SetClassScreen;
import net.mcmodded.rpgmobclasses.client.gui.ClassInfoScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RpgMobClassesModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(RpgMobClassesModMenus.SET_CLASS.get(), SetClassScreen::new);
			MenuScreens.register(RpgMobClassesModMenus.CLASS_INFO.get(), ClassInfoScreen::new);
			MenuScreens.register(RpgMobClassesModMenus.SET_PROFESSION.get(), SetProfessionScreen::new);
		});
	}
}
