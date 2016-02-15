package com.danisagan.dwarfgame.entities;

import com.badlogic.gdx.Gdx;
import com.danisagan.dwarfgame.geometry.Vec2i;
import com.danisagan.dwarfgame.geometry.Vec3i;
import com.danisagan.dwarfgame.map.Cell;
import com.danisagan.dwarfgame.map.Job;
import com.danisagan.dwarfgame.map.Map;
import com.danisagan.dwarfgame.utils.Clock;

import java.util.Random;

/**
 * Created by daniel on 10/21/15.
 */
public class Entity {
    private Vec3i position;
    private Cell cell;
    private Job currentJob;
    private Clock clock = new Clock();

    public Entity(Vec3i position, Map map) {
        this.cell = map.getCell(position);
        this.cell.setEntity(this);
        this.position = position;
    }

    public void moveTo(Vec3i newPosition, Map map) {
        cell.setEntity(null);
        cell = map.getCell(newPosition);
        cell.setEntity(this);
        position = newPosition;
    }

    public void moveRelative(Vec2i movement, Map map) {
        Vec3i newPosition = Vec3i.add(getPosition(), new Vec3i(movement.x, movement.y, 0));
        moveTo(newPosition, map);
    }

    public Vec3i getPosition() {
        return position;
    }

    public Job getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }

    public void update(Map map) {
        long timeSinceLastAction = clock.getElapsedTimeMillis();
        if(getCurrentJob() == null) {
            if(timeSinceLastAction > 5000) {
                Random rn = new Random();
                Vec3i pos = getPosition();
                pos.x += rn.nextInt(3) - 1;
                pos.y += rn.nextInt(2) - 1;
                moveTo(pos, map);
                clock.reset();
                Gdx.app.log("Entity", "Idle dwarf: " + pos.toString());
            }
        } else {
            Vec2i movement = getMovementTowards(getCurrentJob().getTile(), map);
            long movementTime = (long)(movement.toVec2f().getLength() * 500.f);
            if(movementTime > 0 && clock.getElapsedTimeMillis() > movementTime) {
                moveRelative(movement, map);
                clock.reset();
                Gdx.app.log("Entity", "Moved entity to " + getPosition().toString());
            } else if(movementTime == 0 && clock.getElapsedTimeMillis() > 2000) {
                clock.reset();
                Gdx.app.log("Entity", "Entity already at position");
                map.applyAction(getCurrentJob().getTile());
                map.getJobManager().finishJob(this);
            }
        }
    }

    public Vec2i getMovementTowards(Vec2i targetPosition, Map map) {
        Vec2i movement = new Vec2i(0, 0);
        if(targetPosition.x > getPosition().x) {
            movement.x = 1;
        } else if(targetPosition.x < getPosition().x) {
            movement.x = -1;
        }
        if(targetPosition.y > getPosition().y) {
            movement.y = 1;
        } else if(targetPosition.y < getPosition().y) {
            movement.y = -1;
        }
        return movement;
    }
}
