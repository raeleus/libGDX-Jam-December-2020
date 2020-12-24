package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.screens.GameScreen.*;

public class SlideableEntity extends Entity implements Obstacle {
    public float width;
    public float height;
    public static Color color = Color.valueOf("d9a1ed");
    
    public SlideableEntity(float width, float height) {
        this.width = width;
        this.height = height;
        setCollisionBox(0, 0, width, height, nullFilter);
    }
    
    @Override
    public void create() {
        depth = PROP_DEPTH;
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
    
    }
    
    @Override
    public void draw(float delta) {
        if (debugging) {
            shapeDrawer.setColor(color);
            shapeDrawer.filledRectangle(x, y, width, height);
        }
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
