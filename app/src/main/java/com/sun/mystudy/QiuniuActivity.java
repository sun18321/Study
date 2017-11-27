package com.sun.mystudy;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;

public class QiuniuActivity extends AppCompatActivity {
    private String video_url = "http://dianbo.hainantaohua.com/youmei_short_video_144659191510975804631.mp4";
    private PLVideoTextureView mVideoView;
    private String TAG = "study_video";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_qiuniu);

        initPlayer();
    }

    private void initPlayer() {
        String videoPath = video_url;
        mVideoView = (PLVideoTextureView) findViewById(R.id.VideoView);
        View loadingView = findViewById(R.id.LoadingView);
        loadingView.setVisibility(View.VISIBLE);
        mVideoView.setBufferingIndicator(loadingView);
        AVOptions options = new AVOptions();
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 0);
        options.setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_AUTO);
//        if (!mIsLiveStreaming && cache) {
//            options.setString(AVOptions.KEY_CACHE_DIR, Config.DEFAULT_CACHE_DIR);
//        }
        mVideoView.setAVOptions(options);
        mVideoView.setDebugLoggingEnabled(true);
        mVideoView.setOnInfoListener(mOnInfoListener);
        mVideoView.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
        mVideoView.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
        mVideoView.setOnCompletionListener(mOnCompletionListener);
        mVideoView.setOnErrorListener(mOnErrorListener);
        mVideoView.setLooping(true);
        mVideoView.setVideoPath(videoPath);
        mVideoView.start();

    }

    private PLMediaPlayer.OnInfoListener mOnInfoListener = new PLMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(PLMediaPlayer plMediaPlayer, int what, int extra) {
            Log.i(TAG, "OnInfo, what = " + what + ", extra = " + extra);
            switch (what) {
                case PLMediaPlayer.MEDIA_INFO_BUFFERING_START:
                    break;
                case PLMediaPlayer.MEDIA_INFO_BUFFERING_END:
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    break;
                case PLMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                    Log.i(TAG, "First audio render time: " + extra + "ms");
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_FRAME_RENDERING:
                    Log.i(TAG, "video frame rendering, ts = " + extra);
                    break;
                case PLMediaPlayer.MEDIA_INFO_AUDIO_FRAME_RENDERING:
                    Log.i(TAG, "audio frame rendering, ts = " + extra);
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_GOP_TIME:
                    Log.i(TAG, "Gop Time: " + extra);
                    break;
                case PLMediaPlayer.MEDIA_INFO_SWITCHING_SW_DECODE:
                    Log.i(TAG, "Hardware decoding failure, switching software decoding!");
                    break;
                case PLMediaPlayer.MEDIA_INFO_METADATA:
                    Log.i(TAG, mVideoView.getMetadata().toString());
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_BITRATE:
                case PLMediaPlayer.MEDIA_INFO_VIDEO_FPS:
                    break;
                case PLMediaPlayer.MEDIA_INFO_CONNECTED:
                    Log.i(TAG, "Connected !");
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                    return mVideoView.setDisplayOrientation(360 - extra);
                default:
                    break;
            }
            return true;
        }
    };

    private PLMediaPlayer.OnErrorListener mOnErrorListener = new PLMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(PLMediaPlayer mp, int errorCode) {
            Log.e(TAG, "Error happened, errorCode = " + errorCode);
            switch (errorCode) {
                case PLMediaPlayer.ERROR_CODE_IO_ERROR:
                    /**
                     * SDK will do reconnecting automatically
                     */
                    return false;
                case PLMediaPlayer.ERROR_CODE_OPEN_FAILED:
                    break;
                case PLMediaPlayer.ERROR_CODE_SEEK_FAILED:
                    break;
                default:
                    break;
            }
            finish();
            return true;
        }
    };

    private PLMediaPlayer.OnCompletionListener mOnCompletionListener = new PLMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(PLMediaPlayer plMediaPlayer) {
            Log.i(TAG, "Play Completed !");
            finish();
        }
    };

    private PLMediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new PLMediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int precent) {
            Log.i(TAG, "onBufferingUpdate: " + precent);
        }
    };

    private PLMediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangedListener = new PLMediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(PLMediaPlayer plMediaPlayer, int width, int height) {
            Log.i(TAG, "onVideoSizeChanged: width = " + width + ", height = " + height);
        }
    };
}