package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.ray3k.template.*;

import javax.script.Bindings;

import static com.ray3k.template.Core.Binding.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerEntity extends Entity {
    public static float SPRINT_SPEED = 500;
    public static float MOVE_SPEED = 250;
    
    @Override
    public void create() {
        setSkeletonData(spine_player, spine_playerAnimationData);
        animationState.setAnimation(0, Resources.PlayerAnimation.stand, true);
        skeletonBounds.update(skeleton, true);
        setCollisionBox(skeletonBounds, CollisionFilter.defaultFilter);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
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
        
        setMotion(speed, direction);
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
    
    }
}
