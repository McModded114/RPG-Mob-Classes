
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcmodded.rpgmobclasses.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcmodded.rpgmobclasses.client.renderer.MimicRenderer;
import net.mcmodded.rpgmobclasses.client.renderer.GoblinRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RpgMobClassesModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(RpgMobClassesModEntities.GOBLIN.get(), GoblinRenderer::new);
		event.registerEntityRenderer(RpgMobClassesModEntities.MIMIC.get(), MimicRenderer::new);
	}
}
