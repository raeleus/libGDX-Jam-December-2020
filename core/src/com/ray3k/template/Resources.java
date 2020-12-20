package com.ray3k.template;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.SkeletonData;

public class Resources {
    public static Skin skin_skin;

    public static SkeletonData spine_libgdx;

    public static AnimationStateData spine_libgdxAnimationData;

    public static SkeletonData spine_ray3k;

    public static AnimationStateData spine_ray3kAnimationData;

    public static TextureAtlas textures_textures;

    public static Sound sfx_click;

    public static Sound sfx_logoHit;

    public static Sound sfx_logoLibgd;

    public static Sound sfx_logoRay3kSong;

    public static Sound sfx_logoThwip;

    public static Sound sfx_logoVenom;

    public static Sound sfx_logoX;

    public static Music bgm_audioSample;

    public static Music bgm_menu;

    public static void loadResources(AssetManager assetManager) {
        skin_skin = assetManager.get("skin/skin.json");
        spine_libgdx = assetManager.get("spine/libgdx.json");
        spine_libgdxAnimationData = assetManager.get("spine/libgdx.json-animation");
        LibgdxAnimation.animation = spine_libgdx.findAnimation("animation");
        LibgdxAnimation.stand = spine_libgdx.findAnimation("stand");
        spine_ray3k = assetManager.get("spine/ray3k.json");
        spine_ray3kAnimationData = assetManager.get("spine/ray3k.json-animation");
        Ray3kAnimation.animation = spine_ray3k.findAnimation("animation");
        Ray3kAnimation.stand = spine_ray3k.findAnimation("stand");
        textures_textures = assetManager.get("textures/textures.atlas");
        sfx_click = assetManager.get("sfx/click.mp3");
        sfx_logoHit = assetManager.get("sfx/logo/hit.mp3");
        sfx_logoLibgd = assetManager.get("sfx/logo/libgd.mp3");
        sfx_logoRay3kSong = assetManager.get("sfx/logo/ray3k song.mp3");
        sfx_logoThwip = assetManager.get("sfx/logo/thwip.mp3");
        sfx_logoVenom = assetManager.get("sfx/logo/venom.mp3");
        sfx_logoX = assetManager.get("sfx/logo/x.mp3");
        bgm_audioSample = assetManager.get("bgm/audio-sample.mp3");
        bgm_menu = assetManager.get("bgm/menu.mp3");
    }

    public static class LibgdxAnimation {
        public static Animation animation;

        public static Animation stand;
    }

    public static class Ray3kAnimation {
        public static Animation animation;

        public static Animation stand;
    }
}
