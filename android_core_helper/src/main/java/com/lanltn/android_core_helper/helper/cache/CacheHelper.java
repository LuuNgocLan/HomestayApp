package com.lanltn.android_core_helper.helper.cache;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("ALL")
public class CacheHelper
        implements CacheListener {

    private static final String defaultCache = "cache_default";
    private CacheMemory mCacheMemory;
    private static HashMap<String, CacheHelper> mMultiTon = new HashMap<>();
    private CacheListener mCacheListener;
    private ArrayList<Runnable> mWaitingCache = new ArrayList<>();

    public static void clearAll() {
        mMultiTon.clear();
        mMultiTon = new HashMap<>();
    }

    public static CacheHelper getInstance(Context context) {
        return getInstance(context, defaultCache, null);
    }

    public static CacheHelper getInstance(Context context, CacheListener cacheListener) {
        return getInstance(context, defaultCache, cacheListener);
    }

    public static CacheHelper getInstance(Context context, String cacheName, CacheListener cacheListener) {
        if (mMultiTon.get(cacheName) == null) {
            mMultiTon.put(cacheName, new CacheHelper(context, cacheName, cacheListener));
        }
        return mMultiTon.get(cacheName);
    }

    private CacheHelper(Context context, String cacheName, CacheListener cacheListener) {
        mCacheListener = cacheListener;
        mCacheMemory = new CacheMemory(context, cacheName, this);
    }

    public void saveObject(Object object) {
        saveObject(object, null);
    }

    public void saveObject(Object object, String key) {
        mCacheMemory.addObject(object, key);
    }

    public void saveObjects(List object) {
        mCacheMemory.addObjects(object);
    }

    public void saveObjects(List object, String key) {
        mCacheMemory.addObjects(object, key);
    }

    public <T> T getObject(Class<T> classOfT, String key) {
        return mCacheMemory.getObject(classOfT, key);
    }

    public <T> void getObject(final Class<T> classOfT, final String key, final CacheValueListener cacheValueListener) {
        if (mCacheMemory.isLoadCacheDone()) {
            cacheValueListener.cacheReturn(mCacheMemory.getObject(classOfT, key));
        } else {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    cacheValueListener.cacheReturn(mCacheMemory.getObject(classOfT, key));
                }
            };
            mWaitingCache.add(runnable);
        }
    }

    public <T> T getObject(Class<T> classOfT, int key) {
        return getObject(classOfT, String.valueOf(key));
    }

    public <T> void getObject(Class<T> classOfT, int key, CacheValueListener cacheValueListener) {
        getObject(classOfT, String.valueOf(key), cacheValueListener);
    }

    public <T> List<T> getObjects(Class<T> classOfT, String field, String value) {
        return mCacheMemory.getObjects(classOfT, field, value);
    }

    public <T> List<T> getObjects(Class<T> classOfT) {
        return mCacheMemory.getObjects(classOfT);
    }

    public <T> List<T> getObjects(Class<T> classOfT, String key) {
        return mCacheMemory.getObjects(classOfT, key);
    }

    public <T> void getObjects(final Class<T> classOfT, final String field, final String value,
                               final CacheValuesListener cacheValuesListener) {
        if (mCacheMemory.isLoadCacheDone()) {
            cacheValuesListener.cacheReturn(mCacheMemory.getObjects(classOfT, field, value));
        } else {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    cacheValuesListener.cacheReturn(mCacheMemory.getObjects(classOfT, field, value));
                }
            };
            mWaitingCache.add(runnable);
        }
    }

    public <T> List<T> getObjects(Class<T> classOfT, String field, int value) {
        return this.getObjects(classOfT, field, String.valueOf(value));
    }


    public void clearCache() {
        mCacheMemory.clearCache();
    }

    public <T> void getObjects(Class<T> classOfT, String field, int value, CacheValuesListener cacheValuesListener) {
        this.getObjects(classOfT, field, String.valueOf(value), cacheValuesListener);
    }

    public boolean isSyncWithCache() {
        return mCacheMemory.isLoadCacheDone();
    }

    @Override
    public void onLoadCacheDone() {
        if (mCacheListener != null) {
            mCacheListener.onLoadCacheDone();
        }

        for (Runnable runnable : mWaitingCache) {
            runnable.run();
        }
        mWaitingCache.clear();
    }

    public static interface CacheValueListener {

        void cacheReturn(Object object);
    }

    public static interface CacheValuesListener {

        void cacheReturn(List data);
    }
}
