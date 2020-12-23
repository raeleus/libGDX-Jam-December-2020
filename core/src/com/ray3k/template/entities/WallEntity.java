package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.screens.GameScreen.*;

public class WallEntity extends Entity {
    public float width;
    public float height;
    private static final Color color = Color.valueOf("756e86");
    
    public WallEntity(float width, float height) {
        this.width = width;
        this.height = height;
        setCollisionBox(0, 0, width, height, new CollisionFilter() {
            @Override
            public Response filter(Item item, Item other) {
                return null;
            }
        });
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
        shapeDrawer.setColor(color);
        shapeDrawer.filledRectangle(x, y, width, height);
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
