
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcmodded.rpgmobclasses.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.CreativeModeTabEvent;

import net.minecraft.world.item.CreativeModeTabs;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RpgMobClassesModTabs {
	@SubscribeEvent
	public static void buildTabContentsVanilla(CreativeModeTabEvent.BuildContents tabData) {

		if (tabData.getTab() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(RpgMobClassesModItems.GOBLIN_SPAWN_EGG.get());
			tabData.accept(RpgMobClassesModItems.MIMIC_SPAWN_EGG.get());
		}
	}
}
