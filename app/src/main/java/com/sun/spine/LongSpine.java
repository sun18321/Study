package com.sun.spine;

import android.content.Context;
import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
 * Created by sun on 2017/12/7.
 */

public class LongSpine extends ApplicationAdapter {

    private static LongSpine mMaSpine;

    OrthographicCamera camera;
    SpriteBatch batch;
    SkeletonRenderer renderer;
    TextureAtlas atlas;
    Skeleton skeleton;
    AnimationState state;
    SkeletonJson json;
    private SkeletonData mSkeletonData;
    private static Context mContext;

    public static LongSpine getInstance(Context context) {
        if (mMaSpine == null) {
            mContext = context;
            mMaSpine = new LongSpine();
        }
        return mMaSpine;
    }

    public LongSpine() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        renderer = new SkeletonRenderer();
        renderer.setPremultipliedAlpha(true); // PMA results in correct blending without outlines.

        atlas = new TextureAtlas(Gdx.files.internal("huojian/huojian.atlas"));
        json = new SkeletonJson(atlas); // This loads skeleton JSON data, which is stateless.
        json.setScale(1.5f); // Load the skeleton at 60% the size it was in Spine.
        mSkeletonData = json.readSkeletonData(Gdx.files.internal("huojian/huojian.json"));

        skeleton = new Skeleton(mSkeletonData); // Skeleton holds skeleton state (bone positions, slot attachments, etc).

        skeleton.setPosition(DisplayUtil.getDensity_Width(mContext)/2, DisplayUtil.getDensity_Height(mContext)/2);

        Log.d("resize", "den_width" + DisplayUtil.getDensity_Width(mContext) + "den_height" + DisplayUtil.getDensity_Height(mContext));

        AnimationStateData stateData = new AnimationStateData(mSkeletonData); // Defines mixing (crossfading) between animations.
//        stateData.setMix("long-m", "long-m", 0.2f);

        state = new AnimationState(stateData); // Holds the animation state for a skeleton (current animation, time, etc).

        state.setAnimation(0, "m", false);

    }

}
