package com.jamiedev.bygone.common.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import org.jetbrains.annotations.Nullable;

public class LubberNavigation  extends GroundPathNavigation {
    @Nullable
    private BlockPos targetPos;

    public LubberNavigation(Mob mobEntity, Level world) {
        super(mobEntity, world);
    }

    @Override
    public Path createPath(BlockPos target, int distance) {
        this.targetPos = target;
        return super.createPath(target, distance);
    }

    @Override
    public Path createPath(Entity entity, int distance) {
        this.targetPos = entity.blockPosition();
        return super.createPath(entity, distance);
    }

    @Override
    public boolean moveTo(Entity entity, double speed) {
        Path path = this.createPath(entity, 0);
        if (path != null) {
            return this.moveTo(path, speed);
        } else {
            this.targetPos = entity.blockPosition();
            this.speedModifier = speed;
            return true;
        }
    }

    @Override
    public void tick() {
        if (!this.isDone()) {
            super.tick();
        } else {
            if (this.targetPos != null) {
                if (!this.targetPos.closerToCenterThan(this.mob.position(), this.mob.getBbWidth()) && (!(this.mob.getY() > (double)this.targetPos.getY()) || !BlockPos.containing(this.targetPos.getX(), this.mob.getY(), this.targetPos.getZ()).closerToCenterThan(this.mob.position(), this.mob.getBbWidth()))) {
                    this.mob.getMoveControl().setWantedPosition(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), this.speedModifier);
                } else {
                    this.targetPos = null;
                }
            }

        }
    }
}
