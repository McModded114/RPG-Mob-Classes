
package net.mcmodded.rpgmobclasses.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcmodded.rpgmobclasses.world.inventory.SetClassMenu;
import net.mcmodded.rpgmobclasses.procedures.BecomeScoutProcedure;
import net.mcmodded.rpgmobclasses.procedures.BecomeMageProcedure;
import net.mcmodded.rpgmobclasses.procedures.BecomeKnightProcedure;
import net.mcmodded.rpgmobclasses.procedures.BecomeBerserkerProcedure;
import net.mcmodded.rpgmobclasses.RpgMobClassesMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetClassButtonMessage {
	private final int buttonID, x, y, z;

	public SetClassButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public SetClassButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(SetClassButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(SetClassButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level;
		HashMap guistate = SetClassMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			BecomeKnightProcedure.execute(world, entity);
		}
		if (buttonID == 1) {

			BecomeMageProcedure.execute(world, entity);
		}
		if (buttonID == 2) {

			BecomeScoutProcedure.execute(world, entity);
		}
		if (buttonID == 3) {

			BecomeBerserkerProcedure.execute(world, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RpgMobClassesMod.addNetworkMessage(SetClassButtonMessage.class, SetClassButtonMessage::buffer, SetClassButtonMessage::new, SetClassButtonMessage::handler);
	}
}
