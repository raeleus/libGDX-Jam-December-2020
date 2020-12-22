package com.ray3k.template.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.ray3k.template.Core;
import com.ray3k.template.JamScreen;
import com.ray3k.template.entities.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.entities.PlayerEntity.*;

public class DialogPause extends Dialog {
    private JamScreen jamScreen;
    private boolean detectInput;
    
    public DialogPause(JamScreen jamScreen) {
        super("", skin);
        this.jamScreen = jamScreen;
        
        detectInput = true;
    
        Table root = getContentTable();
        
        root.pad(10);
        root.defaults().space(10);
        Label label = new Label("Paused", skin);
        root.add(label);
        
        root.row();
        label = new Label("Press Escape or any control\nto resume game", skin);
        label.setAlignment(Align.center);
        root.add(label);
        
        root.row();
        Table table = new Table();
        root.add(table);
    
        table.defaults().space(10);
        TextButton textButton = new TextButton("Resume", skin);
        table.add(textButton);
        textButton.addListener(sndChangeListener);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                fire(new ResumeEvent());
                hide();
                detectInput = false;
            }
        });
        
        textButton = new TextButton("Quit", skin);
        table.add(textButton);
        textButton.addListener(sndChangeListener);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                fire(new QuitEvent());
                hide();
                detectInput = false;
            }
        });
        
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Keys.ESCAPE) {
                    fire(new ResumeEvent());
                    hide();
                    detectInput = false;
                    event.cancel();
                    return true;
                } else {
                    return super.keyDown(event, keycode);
                }
            }
        });
        
        root.row();
        table = new Table();
        root.add(table);
        
        table.defaults().space(10);
        label = new Label("Player Move Speed", skin);
        table.add(label);
        
        var slider = new Slider(0, 2000, 1, false, skin);
        slider.setValue(MOVE_SPEED);
        table.add(slider).minWidth(500);
        
        var moveSpeedLabel = new Label(Float.toString(MOVE_SPEED), skin);
        table.add(moveSpeedLabel).minWidth(200);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                var slider = (Slider) actor;
                MOVE_SPEED = slider.getValue();
                moveSpeedLabel.setText(Float.toString(slider.getValue()));
            }
        });
    
        root.row();
        table = new Table();
        root.add(table);
    
        table.defaults().space(10);
        label = new Label("Player Sprint Speed", skin);
        table.add(label);
    
        slider = new Slider(0, 2000, 1, false, skin);
        slider.setValue(SPRINT_SPEED);
        table.add(slider).minWidth(500);
    
        var sprintSpeedLabel = new Label(Float.toString(SPRINT_SPEED), skin);
        table.add(sprintSpeedLabel).minWidth(200);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                var slider = (Slider) actor;
                SPRINT_SPEED = slider.getValue();
                sprintSpeedLabel.setText(Float.toString(slider.getValue()));
            }
        });
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
        
        if (detectInput) {
            if (jamScreen.isAnyBindingPressed()) {
                fire(new ResumeEvent());
                hide();
                detectInput = false;
            }
        }
    }
    
    public static class ResumeEvent extends Event {
    
    }
    
    public static class QuitEvent extends Event {
    
    }
    
    public static abstract class PauseListener implements EventListener {
        @Override
        public boolean handle(Event event) {
            if (event instanceof ResumeEvent) {
                resume();
                return true;
            } else if (event instanceof QuitEvent) {
                quit();
                return true;
            } else {
                return false;
            }
        }
        
        public abstract void resume();
        public abstract void quit();
    }
}
