package net.mcmodded.rpgmobclasses.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;
import net.mcmodded.rpgmobclasses.entity.MimicEntity;

public class NormalAttackProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, double amount) {
		if (entity == null || sourceentity == null)
			return;
		DamageSource dmgCause = null;
		if (!(sourceentity instanceof MimicEntity)) {
			dmgCause = (new Object() {
				public DamageSource get(LevelAccessor _world, final String _msgID, Entity _directSource) {
					return new DamageSource(((Level) _world).registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.CACTUS), _directSource) {
						@Override
						public Component getLocalizedDeathMessage(LivingEntity _livingEntity) {
							Component _attackerName = null;
							Component _entityName = _livingEntity.getDisplayName();
							Component _itemName = null;
							Entity _attacker = this.getEntity();
							ItemStack _itemStack = ItemStack.EMPTY;
							if (_attacker != null) {
								_attackerName = _attacker.getDisplayName();
							}
							if (_attacker instanceof LivingEntity _livingAttacker) {
								_itemStack = _livingAttacker.getMainHandItem();
							}
							if (!_itemStack.isEmpty() && _itemStack.hasCustomHoverName()) {
								_itemName = _itemStack.getDisplayName();
							}
							if (_attacker != null && _itemName != null) {
								return Component.translatable("death.attack." + _msgID + ".player.item", _entityName, _attackerName, _itemName);
							} else if (_attacker != null) {
								return Component.translatable("death.attack." + _msgID + ".player", _entityName, _attackerName);
							} else {
								return Component.translatable("death.attack." + _msgID, _entityName);
							}
						}
					};
				}
			}).get(world, "rpgMob", sourceentity);
		} else {
			dmgCause = (new Object() {
				public DamageSource get(LevelAccessor _world, final String _msgID, Entity _directSource) {
					return new DamageSource(((Level) _world).registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.CACTUS), _directSource) {
						@Override
						public Component getLocalizedDeathMessage(LivingEntity _livingEntity) {
							Component _attackerName = null;
							Component _entityName = _livingEntity.getDisplayName();
							Component _itemName = null;
							Entity _attacker = this.getEntity();
							ItemStack _itemStack = ItemStack.EMPTY;
							if (_attacker != null) {
								_attackerName = _attacker.getDisplayName();
							}
							if (_attacker instanceof LivingEntity _livingAttacker) {
								_itemStack = _livingAttacker.getMainHandItem();
							}
							if (!_itemStack.isEmpty() && _itemStack.hasCustomHoverName()) {
								_itemName = _itemStack.getDisplayName();
							}
							if (_attacker != null && _itemName != null) {
								return Component.translatable("death.attack." + _msgID + ".player.item", _entityName, _attackerName, _itemName);
							} else if (_attacker != null) {
								return Component.translatable("death.attack." + _msgID + ".player", _entityName, _attackerName);
							} else {
								return Component.translatable("death.attack." + _msgID, _entityName);
							}
						}
					};
				}
			}).get(world, "eaten", sourceentity);
		}
		if (!(entity instanceof LivingEntity && ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.SWORDSKILL.get()) != null
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof SwordItem)) {
			if (Math.random() < ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITCHANCE.get()).getValue() / 100) {
				entity.hurt(dmgCause, (float) (amount * (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getValue() / 100)
						* (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITDAMAGE.get()).getValue() / 100)));
			} else {
				entity.hurt(dmgCause, (float) (amount * (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getValue() / 100)));
			}
		} else {
			if (Math.random() < ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITCHANCE.get()).getValue() / 100) {
				entity.hurt(dmgCause,
						(float) (amount * (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getValue() / 100)
								* (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.CRITDAMAGE.get()).getValue() / 100)
								* (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.SWORDSKILL.get()).getValue() / 100)));
			} else {
				entity.hurt(dmgCause, (float) (amount * (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getValue() / 100)
						* (1 + ((LivingEntity) sourceentity).getAttribute(RpgMobClassesModAttributes.SWORDSKILL.get()).getValue() / 100)));
			}
		}
	}
}
