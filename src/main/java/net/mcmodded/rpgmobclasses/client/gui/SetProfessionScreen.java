package net.mcmodded.rpgmobclasses.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;

import net.mcmodded.rpgmobclasses.world.inventory.SetProfessionMenu;
import net.mcmodded.rpgmobclasses.network.SetProfessionButtonMessage;
import net.mcmodded.rpgmobclasses.RpgMobClassesMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class SetProfessionScreen extends AbstractContainerScreen<SetProfessionMenu> {
	private final static HashMap<String, Object> guistate = SetProfessionMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_blacksmith;

	public SetProfessionScreen(SetProfessionMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}

	private static final ResourceLocation texture = new ResourceLocation("rpg_mob_classes:textures/screens/set_profession.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("gui.rpg_mob_classes.set_profession.label_select_profession"), 42, 7, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		imagebutton_blacksmith = new ImageButton(this.leftPos + 15, this.topPos + 34, 16, 16, 0, 0, 16, new ResourceLocation("rpg_mob_classes:textures/screens/atlas/imagebutton_blacksmith.png"), 16, 32, e -> {
			if (true) {
				RpgMobClassesMod.PACKET_HANDLER.sendToServer(new SetProfessionButtonMessage(0, x, y, z));
				SetProfessionButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:imagebutton_blacksmith", imagebutton_blacksmith);
		this.addRenderableWidget(imagebutton_blacksmith);
	}
}
