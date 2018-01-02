package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.ApplicationListener;
import com.sun.mystudy.LibgdxActivity;
import com.sun.spine.Dragon;

/**
 * Created by sun on 2017/12/6.
 */

public class DriveUtils  {
    private static final String DRIVE_TAG = "drive_tag";

    protected AndroidGraphics graphics;
    protected AndroidInput input;
    protected AndroidAudio audio;
    protected AndroidFiles files;
    protected AndroidNet net;
    protected ApplicationListener listener;
    public Handler handler;
    private Context mContext;
    private static DriveUtils mDriveUtils = null;
    private LibgdxActivity mActivity;
    private  View mDragonView;
    private static Dragon mDragon;
    private RelativeLayout mRl_libgdx;
    private View mView;


    public static DriveUtils getInstance(Context context,Dragon dragon) {
        if (mDriveUtils == null) {
            mDriveUtils = new DriveUtils(context);
            mDragon = dragon;
        }
        Log.d(DRIVE_TAG, "drive");
        return mDriveUtils;
    }

    public static void cleanViews() {
        if (mDriveUtils != null) {
            mDriveUtils.mActivity = null;
            mDriveUtils.mRl_libgdx = null;
            mDriveUtils.mContext = null;
        }
        mDriveUtils = null;
    }

    public DriveUtils(Context context) {
        mContext = context;
        mActivity = (LibgdxActivity) context;

//        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
//        cfg.r = cfg.g = cfg.b = cfg.a = 8;
//        Dragon spine.dragon = new Dragon(context);
//        mDragonView = initializeForView(spine.dragon, cfg);
//
//        if (mDragonView instanceof SurfaceView) {
//            SurfaceView glView = (SurfaceView) mDragonView;
//            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
//            glView.setZOrderOnTop(true);
//        }
//        addLibGdx();

    }

    public void addLibGdx(int type) {

        mDragon.setAnimType(type);
        if (mRl_libgdx == null) {
            Log.d("spine/dragon", "null");
            mRl_libgdx = mActivity.mRl_libgdx;
            mView = mActivity.mView;
            mRl_libgdx.addView(mView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        } else {
            Log.d("spine/dragon", "no-null");
            mRl_libgdx.removeAllViews();
            mView = mActivity.mView;
            mRl_libgdx.addView(mView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        }

    }

//    public View initializeForView(ApplicationListener listener, AndroidApplicationConfiguration config) {
//        init(listener, config, true);
//        return graphics.getView();
//    }
//
//    private void init(ApplicationListener listener, AndroidApplicationConfiguration config, boolean isForView) {
//        if (this.getVersion() < 8) {
//            throw new GdxRuntimeException("LibGDX requires Android API Level 8 or later.");
//        } else {
//            this.graphics = new AndroidGraphics(this, config, (ResolutionStrategy) (config.resolutionStrategy == null ? new FillResolutionStrategy() : config.resolutionStrategy));
//            this.input = AndroidInputFactory.newAndroidInput(this, mContext, this.graphics.view, config);
//            this.audio = new AndroidAudio(mContext, config);
//            mContext.getFilesDir();
//            this.files = new AndroidFiles(mContext.getAssets(), mContext.getFilesDir().getAbsolutePath());
//            this.net = new AndroidNet(this);
//            this.listener = listener;
//            this.handler = new Handler();
//            this.useImmersiveMode = config.useImmersiveMode;
//            this.hideStatusBar = config.hideStatusBar;
//            this.addLifecycleListener(new LifecycleListener() {
//                public void resume() {
//
//                }
//
//                public void pause() {
//                    audio.pause();
//                }
//
//                public void dispose() {
//                    audio.dispose();
//                }
//            });
//            Gdx.app = this;
//            Gdx.input = this.getInput();
//            Gdx.audio = this.getAudio();
//            Gdx.files = this.getFiles();
//            Gdx.graphics = this.getGraphics();
//            Gdx.net = this.getNet();
//            if (!isForView) {
//                try {
//                    mActivity.requestWindowFeature(1);
//                } catch (Exception var8) {
//                    this.log("AndroidApplication", "Content already displayed, cannot request FEATURE_NO_TITLE", var8);
//                }
//                mActivity.getWindow().setFlags(1024, 1024);
//                mActivity.getWindow().clearFlags(2048);
//                mActivity.setContentView(this.graphics.getView(), createLayoutParams());
//            }
//            this.createWakeLock(config.useWakelock);
//            this.hideStatusBar(this.hideStatusBar);
//            this.useImmersiveMode(this.useImmersiveMode);
//            if (this.useImmersiveMode && this.getVersion() >= 19) {
//                try {
//                    Class e = Class.forName("com.badlogic.gdx.backends.android.AndroidVisibilityListener");
//                    Object o = e.newInstance();
//                    Method method = e.getDeclaredMethod("createListener", new Class[]{AndroidApplicationBase.class});
//                    method.invoke(o, new Object[]{this});
//                } catch (Exception var7) {
//                    this.log("AndroidApplication", "Failed to create AndroidVisibilityListener", var7);
//                }
//            }
//        }
//    }
//
//    protected FrameLayout.LayoutParams createLayoutParams() {
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
//        layoutParams.gravity = 17;
//        return layoutParams;
//    }
//
//    protected void createWakeLock(boolean use) {
//        if (use) {
//            mActivity.getWindow().addFlags(128);
//        }
//    }
//
//    protected void hideStatusBar(boolean hide) {
//        if (hide && this.getVersion() >= 11) {
//            View rootView = mActivity.getWindow().getDecorView();
//            try {
//                Method e = View.class.getMethod("setSystemUiVisibility", new Class[]{Integer.TYPE});
//                if (this.getVersion() <= 13) {
//                    e.invoke(rootView, new Object[]{Integer.valueOf(0)});
//                }
//                e.invoke(rootView, new Object[]{Integer.valueOf(1)});
//            } catch (Exception var4) {
//                this.log("AndroidApplication", "Can\'t hide status bar", var4);
//            }
//        }
//    }
//
//
//    @Override
//    public void create() {
//        super.create();
//
//        Log.d(DRIVE_TAG, "create");
//    }
//
//    @Override
//    public void render() {
//        super.render();
//
//        Log.d(DRIVE_TAG, "render");
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        super.resize(width, height);
//
//        Log.d(DRIVE_TAG, "resize");
//    }
//
//    @Override
//    public void dispose() {
//        super.dispose();
//
//        Log.d(DRIVE_TAG, "dispose");
//    }
//
//
//    @Override
//    public Context getContext() {
//        return mContext;
//    }
//
//    @Override
//    public Array<Runnable> getRunnables() {
//        return runnables;
//    }
//
//    @Override
//    public Array<Runnable> getExecutedRunnables() {
//        return executedRunnables;
//    }
//
//    @Override
//    public void runOnUiThread(Runnable runnable) {
//
//    }
//
//    @Override
//    public void startActivity(Intent intent) {
//
//    }
//
//    @Override
//    public ApplicationListener getApplicationListener() {
//        return listener;
//    }
//
//    @Override
//    public Graphics getGraphics() {
//        return graphics;
//    }
//
//    @Override
//    public Audio getAudio() {
//        return audio;
//    }
//
//    @Override
//    public AndroidInput getInput() {
//        return input;
//    }
//
//    @Override
//    public Files getFiles() {
//        return files;
//    }
//
//    @Override
//    public Net getNet() {
//        return net;
//    }
//
//    @Override
//    public void log(String s, String s1) {
//
//    }
//
//    @Override
//    public void log(String s, String s1, Throwable throwable) {
//
//    }
//
//    @Override
//    public void error(String s, String s1) {
//
//    }
//
//    @Override
//    public void error(String s, String s1, Throwable throwable) {
//
//    }
//
//    @Override
//    public void debug(String s, String s1) {
//
//    }
//
//    @Override
//    public void debug(String s, String s1, Throwable throwable) {
//
//    }
//
//    @Override
//    public void setLogLevel(int i) {
//
//    }
//
//    @Override
//    public int getLogLevel() {
//        return 0;
//    }
//
//    @Override
//    public ApplicationType getType() {
//        return ApplicationType.Android;
//    }
//
//    @Override
//    public int getVersion() {
//        return Build.VERSION.SDK_INT;
//    }
//
//    @Override
//    public long getJavaHeap() {
//        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//    }
//
//    @Override
//    public long getNativeHeap() {
//        return Debug.getNativeHeapAllocatedSize();
//    }
//
//    @Override
//    public Preferences getPreferences(String s) {
//        return new AndroidPreferences(mContext.getSharedPreferences(s, 0));
//    }
//
//    @Override
//    public Clipboard getClipboard() {
//        if (this.clipboard == null) {
//            this.clipboard = new AndroidClipboard(mContext);
//        }
//        return this.clipboard;
//    }
//
//    @Override
//    public void postRunnable(Runnable runnable) {
//        Array var2 = this.runnables;
//        synchronized (this.runnables) {
//            this.runnables.add(runnable);
//            Gdx.graphics.requestRendering();
//        }
//    }
//
//    @Override
//    public void exit() {
//        this.handler.post(new Runnable() {
//            public void run() {
//                mActivity.finish();
//            }
//        });
//    }
//
//    @Override
//    public void addLifecycleListener(LifecycleListener lifecycleListener) {
//        Array var2 = this.lifecycleListeners;
//        synchronized (this.lifecycleListeners) {
//            this.lifecycleListeners.add(lifecycleListener);
//        }
//    }
//
//    @Override
//    public void removeLifecycleListener(LifecycleListener lifecycleListener) {
//        Array var2 = this.lifecycleListeners;
//        synchronized (this.lifecycleListeners) {
//            this.lifecycleListeners.removeValue(lifecycleListener, true);
//        }
//
//    }
//
//    @Override
//    public Array<LifecycleListener> getLifecycleListeners() {
//        return lifecycleListeners;
//    }
//
//    @Override
//    public Window getApplicationWindow() {
//        return mActivity.getWindow();
//    }
//
//    @Override
//    public WindowManager getWindowManager() {
//        return null;
//    }
//
//    @Override
//    public void useImmersiveMode(boolean b) {
//        if (b && this.getVersion() >= 19) {
//            View view = mActivity.getWindow().getDecorView();
//            try {
//                Method e = View.class.getMethod("setSystemUiVisibility", new Class[]{Integer.TYPE});
//                short code = 5894;
//                e.invoke(view, new Object[]{Integer.valueOf(code)});
//            } catch (Exception var5) {
//                this.log("AndroidApplication", "Can\'t set immersive mode", var5);
//            }
//        }
//    }
//
//    @Override
//    public Handler getHandler() {
//        return handler;
//    }
}
