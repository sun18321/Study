package com.sun.mystudy;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity {
    private final int TAKE_PHOTO = 1001;
    private final int CHOOSE_PHOTO = 1004;
    private final int PHOTO_SD_PERMISSION = 1003;
    private final int CAMERA_PERMISIION = 1002;
    private Uri imageUri;

    @BindView(R.id.picture)
    Button mPicture;
    @BindView(R.id.camera)
    Button mCamera;
    @BindView(R.id.image)
    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.camera, R.id.picture})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera:
//                checkPermission(CAMERA_PERMISIION);
                takePhoto();
                break;
            case R.id.picture:
//                checkPermission(PHOTO_SD_PERMISSION);
                choosePhoto();
                break;
        }
    }


    private void checkPermission(int code) {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permission_sd = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_GRANTED && code == CAMERA_PERMISIION) {
            takePhoto();
        } else {
            if (permission_sd == PackageManager.PERMISSION_GRANTED && code == PHOTO_SD_PERMISSION) {
                choosePhoto();
            } else {
                String[] requestPermission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(this, requestPermission, CAMERA_PERMISIION);
            }
        }
    }

    private void choosePhoto() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);

    }

    private void takePhoto() {
        File outImage = new File(getExternalCacheDir(), "image" + System.currentTimeMillis() + ".jpg");
        try {
            outImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(CameraActivity.this, "com.sun.mystudy.provider", outImage);
        } else {
            imageUri = Uri.fromFile(outImage);
        }

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("photo", "request:" + requestCode + "--" + "result:" + resultCode);

        if (requestCode == TAKE_PHOTO && resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                mImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (requestCode == CHOOSE_PHOTO && resultCode == RESULT_OK) {
            if (Build.VERSION.SDK_INT >= 19) {
                handleImageAfterKitKat(data);
            } else {
                handleImageBeforeKitKat(data);
            }
        }
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageAfterKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();

        Log.d("photo", "" + uri);

        if (DocumentsContract.isDocumentUri(this,uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                imagePath = uri.getPath();
            }
            displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath!=null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            mImage.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takePhoto();
        } else if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            choosePhoto();
        } else {
            if (requestCode == CAMERA_PERMISIION) {
                checkPermission(CAMERA_PERMISIION);
            } else if (requestCode == PHOTO_SD_PERMISSION) {
                checkPermission(PHOTO_SD_PERMISSION);
            }
        }
    }
}
