package com.lanltn.android_core_helper.helper.cache;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

class CacheFileManager {

    private Context mContext;
    private String mFileName;
    private static final String empty = "";

    CacheFileManager(Context context, String fileName) {
        mContext = context;
        mFileName = fileName;
    }

    synchronized void saveCacheFile(String content) {
        String filename = mFileName;
        FileOutputStream outputStream;

        try {
            outputStream = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized void clearCache() {
        String filename = mFileName;
        FileOutputStream outputStream;

        try {
            outputStream = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(new byte[0]);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized String getCacheData() {
        try {
            FileInputStream fis = mContext.openFileInput(mFileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empty;
    }
}
