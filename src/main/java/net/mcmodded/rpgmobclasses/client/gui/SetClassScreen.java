package net.mcmodded.rpgmobclasses.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;

import net.mcmodded.rpgmobclasses.world.inventory.SetClassMenu;
import net.mcmodded.rpgmobclasses.network.SetClassButtonMessage;
import net.mcmodded.rpgmobclasses.RpgMobClassesMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class SetClassScreen extends AbstractContainerScreen<SetClassMenu> {
	private final static HashMap<String, Object> guistate = SetClassMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_knight;
	ImageButton imagebutton_mage;
	ImageButton imagebutton_scout;
	ImageButton imagebutton_berserker;

	public SetClassScreen(SetClassMenu container, Inventory inventory, Component text) {
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

	private static final ResourceLocation texture = new ResourceLocation("rpg_mob_classes:textures/screens/set_class.png");

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
		this.font.draw(poseStack, Component.translatable("gui.rpg_mob_classes.set_class.label_select_class"), 56, 7, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		imagebutton_knight = new ImageButton(this.leftPos + 15, this.topPos + 34, 16, 16, 0, 0, 16, new ResourceLocation("rpg_mob_classes:textures/screens/atlas/imagebutton_knight.png"), 16, 32, e -> {
			if (true) {
				RpgMobClassesMod.PACKET_HANDLER.sendToServer(new SetClassButtonMessage(0, x, y, z));
				SetClassButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:imagebutton_knight", imagebutton_knight);
		this.addRenderableWidget(imagebutton_knight);
		imagebutton_mage = new ImageButton(this.leftPos + 51, this.topPos + 34, 16, 16, 0, 0, 16, new ResourceLocation("rpg_mob_classes:textures/screens/atlas/imagebutton_mage.png"), 16, 32, e -> {
			if (true) {
				RpgMobClassesMod.PACKET_HANDLER.sendToServer(new SetClassButtonMessage(1, x, y, z));
				SetClassButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:imagebutton_mage", imagebutton_mage);
		this.addRenderableWidget(imagebutton_mage);
		imagebutton_scout = new ImageButton(this.leftPos + 105, this.topPos + 34, 16, 16, 0, 0, 16, new ResourceLocation("rpg_mob_classes:textures/screens/atlas/imagebutton_scout.png"), 16, 32, e -> {
			if (true) {
				RpgMobClassesMod.PACKET_HANDLER.sendToServer(new SetClassButtonMessage(2, x, y, z));
				SetClassButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		guistate.put("button:imagebutton_scout", imagebutton_scout);
		this.addRenderableWidget(imagebutton_scout);
		imagebutton_berserker = new ImageButton(this.leftPos + 141, this.topPos + 34, 16, 16, 0, 0, 16, new ResourceLocation("rpg_mob_classes:textures/screens/atlas/imagebutton_berserker.png"), 16, 32, e -> {
			if (true) {
				RpgMobClassesMod.PACKET_HANDLER.sendToServer(new SetClassButtonMessage(3, x, y, z));
				SetClassButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		guistate.put("button:imagebutton_berserker", imagebutton_berserker);
		this.addRenderableWidget(imagebutton_berserker);
	}
}
