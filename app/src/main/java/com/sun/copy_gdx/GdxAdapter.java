package com.sun.copy_gdx;

import android.content.Context;
import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.sun.util.DisplayUtil;


/**
 * Created by lizhangfeng on 16/6/20.
 * description: 骨骼动画ApplicationAdapter
 */
public class GdxAdapter extends ApplicationAdapter {


    OrthographicCamera camera;
    SpriteBatch batch;
    SkeletonRenderer renderer;

    TextureAtlas atlas;
    Skeleton skeleton;
    AnimationState state;

    SkeletonJson json;


    private Context context;
    private SkeletonData mSkeletonData;

    public GdxAdapter(Context context) {
        this.context = context;
    }

    public void create() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        renderer = new SkeletonRenderer();
        renderer.setPremultipliedAlpha(true); // PMA results in correct blending without outlines.

        atlas = new TextureAtlas(Gdx.files.internal("long/long.atlas"));
//        atlas = new TextureAtlas(Gdx.files.internal("vae/xusong.atlas"));
        json = new SkeletonJson(atlas); // This loads skeleton JSON data, which is stateless.
        json.setScale(1.5f); // Load the skeleton at 60% the size it was in Spine.
        mSkeletonData = json.readSkeletonData(Gdx.files.internal("long/long.json"));
//        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("vae/xusong.json"));

        skeleton = new Skeleton(mSkeletonData); // Skeleton holds skeleton state (bone positions, slot attachments, etc).

//        skeleton.setX(DisplayUtil.getDensity_Width(context));
//        skeleton.setY(DisplayUtil.getDensity_Height(context));

//        skeleton.setFlip(true, true);

        skeleton.setPosition(DisplayUtil.getDensity_Width(context)/2, DisplayUtil.getDensity_Height(context)/2);

        Log.d("resize", "den_width" + DisplayUtil.getDensity_Width(context) + "den_height" + DisplayUtil.getDensity_Height(context));

        AnimationStateData stateData = new AnimationStateData(mSkeletonData); // Defines mixing (crossfading) between animations.
//        stateData.setMix("run", "jump", 0.2f);
        stateData.setMix("long-m", "long-m", 0.2f);

        state = new AnimationState(stateData); // Holds the animation state for a skeleton (current animation, time, etc).
//        state.setTimeScale(0.5f); // Slow all animations down to 50% speed.

        // Queue animations on track 0.
        state.setAnimation(0, "long-m", true);

        state.addAnimation(0, "long-m", true, 0); // Run after the jump.
    }

    public void render() {
        state.update(Gdx.graphics.getDeltaTime()); // Update the animation time.

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glClearColor(0, 0, 0, 0);

        state.apply(skeleton); // Poses skeleton using current animations. This sets the bones' local SRT.
        skeleton.updateWorldTransform(); // Uses the bones' local SRT to compute their world SRT.

        // Configure the camera, SpriteBatch, and SkeletonRendererDebug.
        camera.update();
        batch.getProjectionMatrix().set(camera.combined);

        batch.begin();
        renderer.draw(batch, skeleton); // Draw the skeleton images.
        batch.end();

    }

    public void resize(int width, int height) {
        camera.setToOrtho(false); // Update camera with new size.
        Log.d("resize", "width" + width + "height" + height);

    }

    public void dispose() {
        atlas.dispose();
    }


}
