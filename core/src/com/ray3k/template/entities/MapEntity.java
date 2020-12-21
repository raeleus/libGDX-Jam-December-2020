package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.dongbat.jbump.Collisions;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;

public class MapEntity extends Entity {
    private AtlasSprite sprite;
    @Override
    public void create() {
        sprite = new AtlasSprite(Resources.textures_textures.findRegion("game/Template_Map_1"));
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
    
    }
    
    @Override
    public void draw(float delta) {
        sprite.draw(batch);
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
