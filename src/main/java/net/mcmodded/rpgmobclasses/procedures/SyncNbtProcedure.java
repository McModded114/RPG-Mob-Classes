package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcmodded.rpgmobclasses.network.RpgMobClassesModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class SyncNbtProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level, event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			{
				String _setval = entity.getPersistentData().getString("Class");
				entity.getCapability(RpgMobClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.classRpgSync = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				String _setval = entity.getPersistentData().getString("Profession");
				entity.getCapability(RpgMobClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ProfessionSync = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if (world.isClientSide()) {
			if (!((entity.getCapability(RpgMobClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RpgMobClassesModVariables.PlayerVariables())).classRpgSync).equals(entity.getPersistentData().getString("Class"))) {
				entity.getPersistentData().putString("Class", ((entity.getCapability(RpgMobClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RpgMobClassesModVariables.PlayerVariables())).classRpgSync));
			}
			if (!((entity.getCapability(RpgMobClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RpgMobClassesModVariables.PlayerVariables())).ProfessionSync).equals(entity.getPersistentData().getString("Profession"))) {
				entity.getPersistentData().putString("Profession", ((entity.getCapability(RpgMobClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RpgMobClassesModVariables.PlayerVariables())).ProfessionSync));
			}
		}
	}
}
