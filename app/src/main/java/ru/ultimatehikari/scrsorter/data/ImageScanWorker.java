package ru.ultimatehikari.scrsorter.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ru.ultimatehikari.scrsorter.App;
import ru.ultimatehikari.scrsorter.R;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;

public class ImageScanWorker extends Worker {
    private static final String DEFAULT_PATH = "";
    private static final int DEFAULT_VERSION = 0;
    private final SharedPreferences sharedPref =
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

    public ImageScanWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    private Cursor openCursor(){
        //        String[] projection = new String[] {
//                MediaStore.MediaColumns.DATA
//        };
        Log.i("IMG", "starting querying..");

        //TODO path used vaguely
        String selection = MediaStore.Images.Media.DATA + " like ? ";
        String[] selectionArgs = new String[] {
                "%Screenshots%"
        };
        String sortOrder = null;

        return getApplicationContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                selection,
                selectionArgs,
                null
        );
    }

    private List<PictureEntity> scanWithCursor(Cursor cursor){


        Log.i("IMG", String.valueOf(cursor.getCount()));

        var data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        var date = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_ADDED);
        var name = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

        List<PictureEntity> pictures = new LinkedList<>();

        while (cursor.moveToNext()) {
            Log.i("IMG", cursor.getString(name));
            var pic = new PictureEntity();
            pic.setName(cursor.getString(name));
            pic.setUrl(cursor.getString(data));
            pictures.add(pic);
        }

        return pictures;
    }

    private boolean ifImageAmountChanged(Cursor cursor){
        var key = getApplicationContext().getString(R.string.mediastore_version_key);
        var settingsVersion = sharedPref
                .getInt(key, DEFAULT_VERSION);
        var storeVersion = cursor.getCount();
        Log.i("IMG", "versions: " + settingsVersion + storeVersion);
        if(settingsVersion != storeVersion){
            sharedPref.edit().putInt(key, storeVersion).apply();
            return true;
        }
        return false;
    }

    @Override
    @NonNull
    public Result doWork() {

        var path = sharedPref.getString(
                getApplicationContext().getString(R.string.scan_path),
                DEFAULT_PATH);

        Log.i("IMG", "Rescanning path: " + path);

        MediaScannerConnection.scanFile(getApplicationContext(),
                new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("IMG", "Scan completed" + path);

                        var cursor = openCursor();

                        if(ifImageAmountChanged(cursor)){
                            var list = scanWithCursor(cursor);
                            AppDatabase.getInstance(getApplicationContext())
                                    .pictureDao().insertAllNew(list);
                        }

                        cursor.close();
                    }
                });

        return Result.success();
    }


}