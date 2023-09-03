package net.mcmodded.rpgmobclasses.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

import net.mcmodded.rpgmobclasses.world.inventory.ClassInfoMenu;
import net.mcmodded.rpgmobclasses.procedures.GetStrengthProcedure;
import net.mcmodded.rpgmobclasses.procedures.GetSpeedProcedure;
import net.mcmodded.rpgmobclasses.procedures.GetProfessionProcedure;
import net.mcmodded.rpgmobclasses.procedures.GetLvAndXpProcedure;
import net.mcmodded.rpgmobclasses.procedures.GetDroppedXpProcedure;
import net.mcmodded.rpgmobclasses.procedures.GetDefenseProcedure;
import net.mcmodded.rpgmobclasses.procedures.GetClassProcedure;
import net.mcmodded.rpgmobclasses.procedures.DisplayPlayerProcedure;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class ClassInfoScreen extends AbstractContainerScreen<ClassInfoMenu> {
	private final static HashMap<String, Object> guistate = ClassInfoMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public ClassInfoScreen(ClassInfoMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("rpg_mob_classes:textures/screens/class_info.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		if (DisplayPlayerProcedure.execute(entity) instanceof LivingEntity livingEntity) {
			InventoryScreen.renderEntityInInventoryFollowsAngle(ms, this.leftPos + 17, this.topPos + 55, 20, 0f, 0, livingEntity);
		}
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
		this.font.draw(poseStack,

				GetStrengthProcedure.execute(entity), 6, 61, -12829636);
		this.font.draw(poseStack,

				GetSpeedProcedure.execute(entity), 6, 79, -12829636);
		this.font.draw(poseStack,

				GetDefenseProcedure.execute(entity), 6, 97, -12829636);
		this.font.draw(poseStack,

				GetDroppedXpProcedure.execute(entity), 33, 43, -12829636);
		this.font.draw(poseStack,

				GetLvAndXpProcedure.execute(entity), 33, 25, -12829636);
		this.font.draw(poseStack,

				GetClassProcedure.execute(entity), 33, 7, -12829636);
		this.font.draw(poseStack,

				GetProfessionProcedure.execute(entity), 6, 115, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
	}
}
