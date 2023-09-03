
package net.mcmodded.rpgmobclasses.entity;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;

import net.mcmodded.rpgmobclasses.procedures.HpBelowQuarterProcedure;
import net.mcmodded.rpgmobclasses.procedures.HpAboveQuarterProcedure;
import net.mcmodded.rpgmobclasses.init.RpgMobClassesModEntities;

public class GoblinEntity extends Monster implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.STRING);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";

	public GoblinEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(RpgMobClassesModEntities.GOBLIN.get(), world);
	}

	public GoblinEntity(EntityType<GoblinEntity> type, Level world) {
		super(type, world);
		xpReward = 4;
		setNoAi(false);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "goblin");
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) * 1.5;
			}
		});
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this) {
			@Override
			public boolean canUse() {
				double x = GoblinEntity.this.getX();
				double y = GoblinEntity.this.getY();
				double z = GoblinEntity.this.getZ();
				Entity entity = GoblinEntity.this;
				Level world = GoblinEntity.this.level;
				return super.canUse() && HpAboveQuarterProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GoblinEntity.this.getX();
				double y = GoblinEntity.this.getY();
				double z = GoblinEntity.this.getZ();
				Entity entity = GoblinEntity.this;
				Level world = GoblinEntity.this.level;
				return super.canContinueToUse() && HpAboveQuarterProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new FloatGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, false, false) {
			@Override
			public boolean canUse() {
				double x = GoblinEntity.this.getX();
				double y = GoblinEntity.this.getY();
				double z = GoblinEntity.this.getZ();
				Entity entity = GoblinEntity.this;
				Level world = GoblinEntity.this.level;
				return super.canUse() && HpAboveQuarterProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GoblinEntity.this.getX();
				double y = GoblinEntity.this.getY();
				double z = GoblinEntity.this.getZ();
				Entity entity = GoblinEntity.this;
				Level world = GoblinEntity.this.level;
				return super.canContinueToUse() && HpAboveQuarterProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(7, new PanicGoal(this, 1.2) {
			@Override
			public boolean canUse() {
				double x = GoblinEntity.this.getX();
				double y = GoblinEntity.this.getY();
				double z = GoblinEntity.this.getZ();
				Entity entity = GoblinEntity.this;
				Level world = GoblinEntity.this.level;
				return super.canUse() && HpBelowQuarterProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GoblinEntity.this.getX();
				double y = GoblinEntity.this.getY();
				double z = GoblinEntity.this.getZ();
				Entity entity = GoblinEntity.this;
				Level world = GoblinEntity.this.level;
				return super.canContinueToUse() && HpBelowQuarterProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(8, new AvoidEntityGoal<>(this, Player.class, (float) 10, 1, 1.2) {
			@Override
			public boolean canUse() {
				double x = GoblinEntity.this.getX();
				double y = GoblinEntity.this.getY();
				double z = GoblinEntity.this.getZ();
				Entity entity = GoblinEntity.this;
				Level world = GoblinEntity.this.level;
				return super.canUse() && HpBelowQuarterProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GoblinEntity.this.getX();
				double y = GoblinEntity.this.getY();
				double z = GoblinEntity.this.getZ();
				Entity entity = GoblinEntity.this;
				Level world = GoblinEntity.this.level;
				return super.canContinueToUse() && HpBelowQuarterProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, AbstractGolem.class, (float) 10, 1, 1.2) {
			@Override
			public boolean canUse() {
				double x = GoblinEntity.this.getX();
				double y = GoblinEntity.this.getY();
				double z = GoblinEntity.this.getZ();
				Entity entity = GoblinEntity.this;
				Level world = GoblinEntity.this.level;
				return super.canUse() && HpBelowQuarterProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GoblinEntity.this.getX();
				double y = GoblinEntity.this.getY();
				double z = GoblinEntity.this.getZ();
				Entity entity = GoblinEntity.this;
				Level world = GoblinEntity.this.level;
				return super.canContinueToUse() && HpBelowQuarterProcedure.execute(entity);
			}
		});
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		this.refreshDimensions();
	}

	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		return super.getDimensions(p_33597_).scale((float) 1);
	}

	public static void init() {
		SpawnPlacements.register(RpgMobClassesModEntities.GOBLIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getBlockState(pos.below()).getMaterial() == Material.GRASS && world.getRawBrightness(pos, 0) > 8));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.35);
		builder = builder.add(Attributes.MAX_HEALTH, 20);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 32);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (this.animationprocedure.equals("empty")) {
			if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))

			) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
		}
		return PlayState.STOP;
	}

	private PlayState procedurePredicate(AnimationState event) {
		Entity entity = this;
		Level world = entity.level;
		boolean loop = false;
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		if (!loop && this.lastloop) {
			this.lastloop = false;
			event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
			event.getController().forceAnimationReset();
			return PlayState.STOP;
		}
		if (!this.animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
			if (!loop) {
				event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
				if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
					this.animationprocedure = "empty";
					event.getController().forceAnimationReset();
				}
			} else {
				event.getController().setAnimation(RawAnimation.begin().thenLoop(this.animationprocedure));
				this.lastloop = true;
			}
		}
		return PlayState.CONTINUE;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(GoblinEntity.RemovalReason.KILLED);
			this.dropExperience();
		}
	}

	public String getSyncedAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public void setAnimation(String animation) {
		this.entityData.set(ANIMATION, animation);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
		data.add(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
