package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.ray3k.template.*;

import static com.ray3k.template.Core.Binding.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.Resources.PlayerAnimation.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerEntity extends Entity {
    public static float SPRINT_SPEED = 300;
    public static float MOVE_SPEED = 200;
    public static float SLIDE_SPEED = 600;
    public static float SLIDE_FRICTION = 1400;
    public static float JUMP_SPEED = 550;
    public static float JUMP_FRICTION = 1100;
    private boolean sliding;
    private boolean jumping;
    private float friction;
    private boolean pitFalling;
    private float pitRespawnX;
    private float pitRespawnY;
    
    @Override
    public void create() {
        setSkeletonData(spine_player, spine_playerAnimationData);
        animationState.setAnimation(0, Resources.PlayerAnimation.stand, true);
        skeletonBounds.update(skeleton, true);
        setCollisionBox(skeletonBounds, (item, other) -> {
            if (other.userData instanceof WallEntity) {
                return Response.slide;
            } else if (other.userData instanceof  PitEntity) {
                return Response.cross;
            }
            return null;
        });
        animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void complete(TrackEntry entry) {
                if (entry.getAnimation() == pitDeath) {
                    setPosition(pitRespawnX, pitRespawnY);
                    animationState.setAnimation(0, stand, true);
                    pitFalling = false;
                }
            }
        });
    }
    
    @Override
    public void actBefore(float delta) {
        if (!pitFalling && !sliding && !jumping) {
            pitRespawnX = x;
            pitRespawnY = y;
        }
    }
    
    @Override
    public void act(float delta) {
        if (!pitFalling && !sliding && !jumping) {
            float direction;
            float speed = gameScreen.isBindingPressed(SPRINT) ? SPRINT_SPEED : MOVE_SPEED;
            if (gameScreen.areAllBindingsPressed(UP, RIGHT)) {
                animationState.setAnimation(0, PlayerAnimation.northEast, true);
                direction = 45;
            } else if (gameScreen.areAllBindingsPressed(DOWN, RIGHT)) {
                animationState.setAnimation(0, PlayerAnimation.southEast, true);
                direction = 325;
            } else if (gameScreen.isBindingPressed(RIGHT)) {
                animationState.setAnimation(0, PlayerAnimation.east, true);
                direction = 0;
            } else if (gameScreen.areAllBindingsPressed(UP, LEFT)) {
                animationState.setAnimation(0, PlayerAnimation.northWest, true);
                direction = 135;
            } else if (gameScreen.areAllBindingsPressed(DOWN, LEFT)) {
                animationState.setAnimation(0, PlayerAnimation.southWest, true);
                direction = 215;
            } else if (gameScreen.isBindingPressed(LEFT)) {
                animationState.setAnimation(0, PlayerAnimation.west, true);
                direction = 180;
            } else if (gameScreen.isBindingPressed(UP)) {
                animationState.setAnimation(0, PlayerAnimation.north, true);
                direction = 90;
            } else if (gameScreen.isBindingPressed(DOWN)) {
                animationState.setAnimation(0, PlayerAnimation.south, true);
                direction = 270;
            } else {
                animationState.setAnimation(0, PlayerAnimation.stand, true);
                direction = 0;
                speed = 0;
            }
            
            if (gameScreen.isBindingJustPressed(SLIDE) && gameScreen.isAnyBindingPressed(UP, DOWN, LEFT, RIGHT)) {
                speed = SLIDE_SPEED;
                friction = SLIDE_FRICTION;
                sliding = true;
                animationState.setAnimation(0, slide, true);
            }
    
            if (gameScreen.isBindingJustPressed(JUMP) && gameScreen.isAnyBindingPressed(UP, DOWN, LEFT, RIGHT)) {
                speed = JUMP_SPEED;
                friction = JUMP_FRICTION;
                jumping = true;
                animationState.setAnimation(0, jump, false);
            }
    
            setMotion(speed, direction);
        }
        
        if (sliding) {
            setSpeed(Utils.approach(getSpeed(), 0, friction * delta));
            if (MathUtils.isZero(getSpeed())) {
                sliding = false;
                animationState.setAnimation(0, stand, true);
            }
        }
    
        if (jumping) {
            setSpeed(Utils.approach(getSpeed(), 0, friction * delta));
            animationState.getCurrent(0).setTrackTime((JUMP_SPEED - getSpeed()) / JUMP_SPEED);
            if (MathUtils.isZero(getSpeed())) {
                jumping = false;
                animationState.addAnimation(0, stand, true, 0);
            }
        }
    
        gameScreen.camera.position.set(x, y, 0);
    }
    
    @Override
    public void draw(float delta) {
        shapeDrawer.setColor(Color.BLUE);
        shapeDrawer.setDefaultLineWidth(1);
        shapeDrawer.rectangle(x + bboxX, y + bboxY, bboxWidth, bboxHeight);
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
        for (int i = 0; i < collisions.size(); i++) {
            var collision = collisions.get(i);
            if (!jumping && !pitFalling && collision.other.userData instanceof PitEntity) {
                if (getCollisionBoxCenterX() > collision.otherRect.x && getCollisionBoxCenterX() < collision.otherRect.x + collision.otherRect.w && getCollisionBoxCenterY() > collision.otherRect.y && getCollisionBoxCenterY() < collision.otherRect.y + collision.otherRect.h) {
                    sliding = false;
                    jumping = false;
                    pitFalling = true;
                    animationState.setAnimation(0, pitDeath, false);
                    setSpeed(0);
                }
            }
        }
    }
}
