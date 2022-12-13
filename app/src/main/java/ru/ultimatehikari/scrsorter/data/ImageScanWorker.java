package ru.ultimatehikari.scrsorter.data;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ImageScanWorker extends Worker {
    public ImageScanWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    @NonNull
    public Result doWork() {
//        String[] projection = new String[] {
//                MediaStore.MediaColumns.DATA
//        };
        Log.i("IMG", "starting querying..");

        String selection = MediaStore.Images.Media.DATA + " like ? ";
        String[] selectionArgs = new String[] {
                "%Screenshots%"
        };
        String sortOrder = null;

        Cursor cursor = getApplicationContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                selection,
                selectionArgs,
                null
        );

        Log.i("IMG", String.valueOf(cursor.getCount()));

        var data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        var date = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_ADDED);
        var name = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

        while (cursor.moveToNext()) {
            Log.i("IMG", cursor.getString(name));
        }

        cursor.close();
        return Result.success();
    }
}