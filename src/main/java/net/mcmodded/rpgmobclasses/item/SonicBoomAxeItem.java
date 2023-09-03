
package net.mcmodded.rpgmobclasses.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;

import net.mcmodded.rpgmobclasses.procedures.CustomItemLoseDurabilityProcedure;

public class SonicBoomAxeItem extends Item {
	public SonicBoomAxeItem() {
		super(new Item.Properties().durability(4063).rarity(Rarity.EPIC));
	}

	@Override
	public int getEnchantmentValue() {
		return 25;
	}

	@Override
	public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
		return 3F;
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
		CustomItemLoseDurabilityProcedure.execute(itemstack);
		return retval;
	}
}
