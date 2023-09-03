package net.mcmodded.rpgmobclasses.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlot;

import net.mcmodded.rpgmobclasses.init.RpgMobClassesModItems;
import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;
import net.mcmodded.rpgmobclasses.RpgMobClassesMod;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class CalculatingAttributeModifiersProcedure {
	@SubscribeEvent
	public static void addAttributeModifier(ItemAttributeModifierEvent event) {
		execute(event, event.getItemStack());
	}

	public static void execute(ItemStack itemstack) {
		execute(null, itemstack);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack) {
		if (itemstack.getItem() == RpgMobClassesModItems.BASIC_WAND.get()) {
			if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.MAINHAND) {
				_event.addModifier(RpgMobClassesModAttributes.MAGICDAMAGE.get(),
						(new AttributeModifier(UUID.fromString("c19b057f-11a5-48b3-bdf7-aaab4bef38be"), RpgMobClassesMod.MODID + "." + "basicwanddmg", 3, AttributeModifier.Operation.ADDITION)));
			}
		}
		if (itemstack.getItem() == RpgMobClassesModItems.CHERRY_WAND.get()) {
			if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.MAINHAND) {
				_event.addModifier(RpgMobClassesModAttributes.MAGICDAMAGE.get(),
						(new AttributeModifier(UUID.fromString("55d743e9-b251-4940-878a-058dcca6f0b1"), RpgMobClassesMod.MODID + "." + "cherrywanddmg", 5, AttributeModifier.Operation.ADDITION)));
			}
		}
		if (itemstack.getItem() == RpgMobClassesModItems.SONIC_BOOM_AXE.get()) {
			if (itemstack.getOrCreateTag().getDouble("strength") == 0) {
				itemstack.getOrCreateTag().putDouble("strength", 90);
			}
			if (itemstack.getOrCreateTag().getDouble("dmg") == 0) {
				itemstack.getOrCreateTag().putDouble("dmg", 15);
			}
			if (itemstack.getOrCreateTag().getDouble("critdmg") == 0) {
				itemstack.getOrCreateTag().putDouble("critdmg", 120);
			}
			if (itemstack.getOrCreateTag().getDouble("critchance") == 0) {
				itemstack.getOrCreateTag().putDouble("critchance", 64);
			}
			if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.MAINHAND) {
				if (itemstack.getOrCreateTag().getDouble("strength") != 0) {
					_event.addModifier(RpgMobClassesModAttributes.STRENGTH.get(), (new AttributeModifier(UUID.fromString("b2c862bd-acd4-46af-af65-88614c82a015"), RpgMobClassesMod.MODID + "." + "classweaponstrength",
							(itemstack.getOrCreateTag().getDouble("strength")), AttributeModifier.Operation.ADDITION)));
				}
				if (itemstack.getOrCreateTag().getDouble("dmg") != 0) {
					_event.addModifier(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE,
							(new AttributeModifier(UUID.fromString("09f049cb-bb4f-4020-b20b-4d746a66c557"), RpgMobClassesMod.MODID + "." + "classweapondmg", (itemstack.getOrCreateTag().getDouble("dmg")), AttributeModifier.Operation.ADDITION)));
				}
				if (itemstack.getOrCreateTag().getDouble("critdmg") != 0) {
					_event.addModifier(RpgMobClassesModAttributes.CRITDAMAGE.get(), (new AttributeModifier(UUID.fromString("8a668ff4-cb5b-40a6-a97b-774a853a28bc"), RpgMobClassesMod.MODID + "." + "classweaponcritdmg",
							(itemstack.getOrCreateTag().getDouble("critdmg")), AttributeModifier.Operation.ADDITION)));
				}
				if (itemstack.getOrCreateTag().getDouble("critchance") != 0) {
					_event.addModifier(RpgMobClassesModAttributes.CRITCHANCE.get(), (new AttributeModifier(UUID.fromString("3ca6ef84-82ee-47aa-8eed-a7a6fd1f92a4"), RpgMobClassesMod.MODID + "." + "classweaponcritchance",
							(itemstack.getOrCreateTag().getDouble("critchance")), AttributeModifier.Operation.ADDITION)));
				}
			}
		}
		if (itemstack.getItem() == RpgMobClassesModItems.SONIC_BOOM_WAND.get()) {
			if (itemstack.getOrCreateTag().getDouble("dmg") == 0) {
				itemstack.getOrCreateTag().putDouble("dmg", 15);
			}
			if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.MAINHAND) {
				_event.addModifier(RpgMobClassesModAttributes.MAGICDAMAGE.get(),
						(new AttributeModifier(UUID.fromString("b76fd4f4-5058-4c2b-8b6e-36a30aef4a4b"), RpgMobClassesMod.MODID + "." + "classwanddmg", (itemstack.getOrCreateTag().getDouble("dmg")), AttributeModifier.Operation.ADDITION)));
			}
		}
		if (itemstack.getItem() instanceof BowItem) {
			if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.MAINHAND) {
				_event.addModifier(RpgMobClassesModAttributes.PROJECTILEDAMAGE.get(), (new AttributeModifier(UUID.fromString("e20b2b7b-c068-4927-90b2-e1a1137ac8c9"), RpgMobClassesMod.MODID + "." + "bowdmg", 7, AttributeModifier.Operation.ADDITION)));
				if (itemstack.getOrCreateTag().getDouble("blacksmithbonus") != 0) {
					_event.addModifier(RpgMobClassesModAttributes.PROJECTILEDAMAGE.get(), (new AttributeModifier(UUID.fromString("c2653b46-f9ae-4256-a0b4-fcba1dd11a07"), RpgMobClassesMod.MODID + "." + "blacksmithboost",
							(itemstack.getOrCreateTag().getDouble("blacksmithbonus")), AttributeModifier.Operation.ADDITION)));
				}
			}
		}
		if (itemstack.getItem() instanceof CrossbowItem) {
			if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.MAINHAND) {
				_event.addModifier(RpgMobClassesModAttributes.PROJECTILEDAMAGE.get(),
						(new AttributeModifier(UUID.fromString("c6d6b2cf-bcbc-48e4-a882-1dc617e18bf0"), RpgMobClassesMod.MODID + "." + "crossbowdmg", 9, AttributeModifier.Operation.ADDITION)));
				if (itemstack.getOrCreateTag().getDouble("blacksmithbonus") != 0) {
					_event.addModifier(RpgMobClassesModAttributes.PROJECTILEDAMAGE.get(), (new AttributeModifier(UUID.fromString("c2653b46-f9ae-4256-a0b4-fcba1dd11a07"), RpgMobClassesMod.MODID + "." + "blacksmithboost",
							(itemstack.getOrCreateTag().getDouble("blacksmithbonus")), AttributeModifier.Operation.ADDITION)));
				}
			}
		}
		if (itemstack.getItem() instanceof SwordItem) {
			if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.MAINHAND) {
				_event.addModifier(RpgMobClassesModAttributes.STRENGTH.get(), (new AttributeModifier(UUID.fromString("51f6e0ff-7fd7-4d89-9759-4724f2ea2f65"), RpgMobClassesMod.MODID + "." + "swordstrength",
						Math.round((1 + (itemstack.getItem() instanceof TieredItem _tierItem ? _tierItem.getTier().getLevel() : 0)) * 5), AttributeModifier.Operation.ADDITION)));
				if (itemstack.getOrCreateTag().getDouble("blacksmithbonus") != 0) {
					_event.addModifier(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE, (new AttributeModifier(UUID.fromString("c2653b46-f9ae-4256-a0b4-fcba1dd11a07"), RpgMobClassesMod.MODID + "." + "blacksmithboost",
							(itemstack.getOrCreateTag().getDouble("blacksmithbonus")), AttributeModifier.Operation.ADDITION)));
				}
				_event.addModifier(RpgMobClassesModAttributes.CRITDAMAGE.get(), (new AttributeModifier(UUID.fromString("447d4f3c-11c7-4822-80c4-ad0a420d74e2"), RpgMobClassesMod.MODID + "." + "swordcritdmg",
						Math.round((1 + (itemstack.getItem() instanceof TieredItem _tierItem ? _tierItem.getTier().getLevel() : 0)) * 15), AttributeModifier.Operation.ADDITION)));
				_event.addModifier(RpgMobClassesModAttributes.CRITCHANCE.get(), (new AttributeModifier(UUID.fromString("02023a40-e514-4009-8fac-5b7390da0dde"), RpgMobClassesMod.MODID + "." + "swordcritchance",
						Math.round((1 + (itemstack.getItem() instanceof TieredItem _tierItem ? _tierItem.getTier().getLevel() : 0)) * 8), AttributeModifier.Operation.ADDITION)));
			}
		}
		if (itemstack.getItem() instanceof AxeItem) {
			if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.MAINHAND) {
				if (itemstack.getOrCreateTag().getDouble("blacksmithbonus") != 0) {
					_event.addModifier(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE, (new AttributeModifier(UUID.fromString("c2653b46-f9ae-4256-a0b4-fcba1dd11a07"), RpgMobClassesMod.MODID + "." + "blacksmithboost",
							(itemstack.getOrCreateTag().getDouble("blacksmithbonus")), AttributeModifier.Operation.ADDITION)));
				}
				_event.addModifier(RpgMobClassesModAttributes.STRENGTH.get(), (new AttributeModifier(UUID.fromString("1d197dda-dac4-46d9-ab10-ddcb24d34c9f"), RpgMobClassesMod.MODID + "." + "axestrength",
						Math.round((1 + (itemstack.getItem() instanceof TieredItem _tierItem ? _tierItem.getTier().getLevel() : 0)) * 10), AttributeModifier.Operation.ADDITION)));
				_event.addModifier(RpgMobClassesModAttributes.CRITDAMAGE.get(), (new AttributeModifier(UUID.fromString("03c4b21d-bd79-4c40-814a-fa5dfec7ec91"), RpgMobClassesMod.MODID + "." + "axecritdmg",
						Math.round((1 + (itemstack.getItem() instanceof TieredItem _tierItem ? _tierItem.getTier().getLevel() : 0)) * 30), AttributeModifier.Operation.ADDITION)));
				_event.addModifier(RpgMobClassesModAttributes.CRITCHANCE.get(), (new AttributeModifier(UUID.fromString("1b07c1bb-835c-45ac-9e66-9693fdb68075"), RpgMobClassesMod.MODID + "." + "axecritchance",
						Math.round((1 + (itemstack.getItem() instanceof TieredItem _tierItem ? _tierItem.getTier().getLevel() : 0)) * 12), AttributeModifier.Operation.ADDITION)));
			}
		}
	}
}
