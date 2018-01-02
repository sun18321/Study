package com.sun.spine;

import android.content.Context;
import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.DriveUtils;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.SkeletonRendererDebug;
import com.sun.util.DisplayUtil;

/**
 * Created by sun on 2017/12/6.
 */

public class Dragon extends ApplicationAdapter {
    private int anim_type;
    private Context mContext;
    private int i = 1;

    public Dragon(Context context) {
        mContext = context;

        Log.d("spine/dragon", "construct");
    }


    OrthographicCamera camera;
    SpriteBatch batch;
    SkeletonRenderer renderer;
    SkeletonRendererDebug debugRenderer;
    TextureAtlas atlas;
    Skeleton skeleton;
    AnimationState state;
    SkeletonJson json;

    public void setAnimType(int type) {
//        i++;
//        anim_type = type;
//        camera = new OrthographicCamera();
//        batch = new SpriteBatch();
//        renderer = new SkeletonRenderer();
//        atlas = new TextureAtlas(Gdx.files.internal("spine/dragon/long.atlas"));
//        json = new SkeletonJson(atlas); // This loads skeleton JSON data, which is stateless.
//        json.setScale(1.5f); // Load the skeleton at 60% the size it was in Spine.
//        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("spine/dragon/long.json"));
//        skeleton = new Skeleton(skeletonData); // Skeleton holds skeleton state (bone positions, slot attachments, etc).
//        skeleton.setPosition(DisplayUtil.getDensity_Width(mContext)/2, DisplayUtil.getDensity_Height(mContext)/2);
//        AnimationStateData stateData = new AnimationStateData(skeletonData); // Defines mixing (crossfading) between animations.
////        stateData.setMix("long-m", "long-m", 0.2f);
//        state = new AnimationState(stateData); // Holds the animation state for a skeleton (current animation, time, etc).
//        renderer.setPremultipliedAlpha(true); // PMA results in correct blending without outlines.
//
//
//        state.setTimeScale(1.0f); // Slow all animations down to 50% speed.
//        // Queue animations on track 0.
//        state.setAnimation(0, "MIDLE", false);
//
//        state.addListener(new AnimationState.AnimationStateListener() {
//            @Override
//            public void event(int i, Event event) {
//
//            }
//
//            @Override
//            public void complete(int i, int i1) {
//                Log.d("spine/dragon", "complete");
//                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//                Gdx.gl.glClearColor(0, 0, 0, 0);
//
//            }
//
//            @Override
//            public void start(int i) {
//
//            }
//
//            @Override
//            public void end(int i) {
////                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//                Log.d("spine/dragon", "end");
//
//            }
//        });

//                MaSpine instance = MaSpine.getInstance(mContext);
//                camera = instance.camera;
//                batch = instance.batch;
//                renderer = instance.renderer;
//                renderer.setPremultipliedAlpha(true); // PMA results in correct blending without outlines.
//                atlas = instance.atlas;
//                json = instance.json;
//                skeleton = instance.skeleton;
//                state = instance.state;
    }

    public void create() {
        Log.d("spine/dragon", "create");

//        switch (anim_type) {
//            case 0:
//                MaSpine instance = MaSpine.getInstance(mContext);
//                camera = instance.camera;
//                batch = instance.batch;
//                renderer = instance.renderer;
//                renderer.setPremultipliedAlpha(true); // PMA results in correct blending without outlines.
//                atlas = instance.atlas;
//                json = instance.json;
//                skeleton = instance.skeleton;
//                state = instance.state;
//                break;
//            case 1:
//                LongSpine longSpine = LongSpine.getInstance(mContext);
//                camera = longSpine.camera;
//                batch = longSpine.batch;
//                renderer = longSpine.renderer;
//                renderer.setPremultipliedAlpha(true); // PMA results in correct blending without outlines.
//                atlas = longSpine.atlas;
//                json = longSpine.json;
//                skeleton = longSpine.skeleton;
//                state = longSpine.state;
//                break;
//        }


        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        renderer = new SkeletonRenderer();
        atlas = new TextureAtlas(Gdx.files.internal("spine/dragon/long.atlas"));
        json = new SkeletonJson(atlas); // This loads skeleton JSON data, which is stateless.
        json.setScale(1.5f); // Load the skeleton at 60% the size it was in Spine.
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("spine/dragon/long.json"));
        skeleton = new Skeleton(skeletonData); // Skeleton holds skeleton state (bone positions, slot attachments, etc).
        skeleton.setPosition(DisplayUtil.getDensity_Width(mContext)/2, DisplayUtil.getDensity_Height(mContext)/2);
        AnimationStateData stateData = new AnimationStateData(skeletonData); // Defines mixing (crossfading) between animations.
//        stateData.setMix("long-m", "long-m", 0.2f);
        state = new AnimationState(stateData); // Holds the animation state for a skeleton (current animation, time, etc).

        state.addListener(new AnimationState.AnimationStateListener() {
            @Override
            public void event(int i, Event event) {

            }

            @Override
            public void complete(int i, int i1) {
                Log.d("dragon", "spine_over");


            }

            @Override
            public void start(int i) {

            }

            @Override
            public void end(int i) {

            }
        });

        renderer.setPremultipliedAlpha(true); // PMA results in correct blending without outlines.


        state.setTimeScale(1.0f); // Slow all animations down to 50% speed.
        // Queue animations on track 0.
        state.setAnimation(0, "MIDLE", false);

        state.addListener(new AnimationState.AnimationStateListener() {
            @Override
            public void event(int i, Event event) {

            }

            @Override
            public void complete(int i, int i1) {
                Log.d("spine/dragon", "complete");
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                Gdx.gl.glClearColor(0, 0, 0, 0);

            }

            @Override
            public void start(int i) {

            }

            @Override
            public void end(int i) {
//                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                Log.d("spine/dragon", "end");

            }
        });
    }

    public void render() {
        Log.d("spine/dragon", "render" + i);

        if (state != null) {


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
    }

    public void resize(int width, int height) {
        camera.setToOrtho(false); // Update camera with new size.
    }

    public void dispose() {
        atlas.dispose();
    }

    public void setAnimate(String animate) {
        state.addAnimation(0, animate, true, 0);
    }

    public void zoomBig() {
        camera.zoom = 0.5f;
    }

    public void zoomSmall() {
        camera.zoom = 1f;
    }
}
