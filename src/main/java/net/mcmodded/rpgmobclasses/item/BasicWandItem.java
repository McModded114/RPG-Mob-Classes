
package net.mcmodded.rpgmobclasses.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;

import net.mcmodded.rpgmobclasses.procedures.BasicBeamProcedure;

public class BasicWandItem extends Item {
	public BasicWandItem() {
		super(new Item.Properties().durability(59).rarity(Rarity.COMMON));
	}

	@Override
	public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
		boolean retval = super.onEntitySwing(itemstack, entity);
		BasicBeamProcedure.execute(entity.level, entity.getX(), entity.getY(), entity.getZ(), entity, itemstack);
		return retval;
	}
}
