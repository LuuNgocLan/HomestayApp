package com.lanltn.android_core_helper.helper.cache;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
class CacheMemory {

    private CacheFileManager mCacheFileManager;
    private CacheBase mCacheBase;
    private CacheListener mCacheListener;
    private boolean mLoadCacheDone = false;
    private boolean mWaitToSaveCache = false;
    private final String ListDataKey = "LIST_DATA";

    CacheMemory(Context context, String cacheName, CacheListener cacheListener) {
        this.mCacheListener = cacheListener;
        init(context, cacheName);
    }

    void addObject(Object object, String key) {
        synchronized (this) {
            String finalKey = key != null ? key : getKey(object);
            if (finalKey == null)
                return;

            if (getCache(className(object)) == null) {
                mCacheBase.mCache.put(className(object), new CacheBase.CacheItems());
            }
            getCache(className(object)).mCacheItems.put(finalKey, object);
            notifyDataChanged();
        }
    }

    void addObjects(List object, @NonNull String key) {
        synchronized (this) {
            if (getCache(ListDataKey) == null) {
                mCacheBase.mCache.put(ListDataKey, new CacheBase.CacheItems());
            }
            getCache(ListDataKey).mCacheItems.put(key, object);
            notifyDataChanged();
        }
    }

    void addObjects(List objects) {
        synchronized (this) {
            for (Object object : objects) {
                String key = getKey(object);
                if (key == null)
                    return;

                if (getCache(className(object)) == null) {
                    mCacheBase.mCache.put(className(object), new CacheBase.CacheItems());
                }
                getCache(className(object)).mCacheItems.put(key, object);
            }
            notifyDataChanged();
        }
    }

    private String getKey(Object object) {
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    return String.valueOf(field.get(object));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeObject(Class objectClass, String key) {
        synchronized (this) {
            if (getCache(className(objectClass)) != null) {
                getCache(className(objectClass)).mCacheItems.remove(key);
            }
            notifyDataChanged();
        }
    }

    <T> T getObject(Class<T> classOfT, String key) {
        CacheBase.CacheItems cacheItems = getCache(className(classOfT));
        if (cacheItems != null) {
            Object object = cacheItems.mCacheItems.get(key);
            return parseObject(classOfT, object);
        }
        return null;
    }

    <T> List<T> getObjects(Class<T> classOfT, String fieldName, String value) {
        CacheBase.CacheItems cacheItems = getCache(className(classOfT));
        if (cacheItems != null) {
            List<T> datas = new ArrayList<>();
            for (Object object : cacheItems.mCacheItems.values()) {
                try {
                    T tObject = parseObject(classOfT, object);
                    Field field = tObject.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    if (field.get(tObject).toString().equals(value)) {
                        datas.add(tObject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return datas;
        }
        return new ArrayList<>();
    }

    <T> List<T> getObjects(Class<T> classOfT) {
        CacheBase.CacheItems cacheItems = getCache(className(classOfT));
        if (cacheItems != null) {
            List<T> datas = new ArrayList<>();
            for (Object object : cacheItems.mCacheItems.values()) {
                try {
                    T tObject = parseObject(classOfT, object);
                    datas.add(tObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return datas;
        }
        return new ArrayList<>();
    }

    <T> List<T> getObjects(Class<T> classOfT, String key) {
        CacheBase.CacheItems cacheItems = getCache(ListDataKey);
        Object object = null;
        if (cacheItems != null) {
            object = cacheItems.mCacheItems.get(key);
        }
        if (object instanceof List) {
            List data = (List) object;
            List<T> dataResponse = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                dataResponse.add(parseObject(classOfT, data.get(i)));
            }
            return dataResponse;
        }
        return null;
    }

    private <T> T parseObject(Class<T> classOfT, Object object) {
        if (object != null) {
            if (classOfT.isInstance(object)) {
                return (T) object;
            } else {
                Gson gson = new Gson();
                String json = gson.toJson(object);
                return gson.fromJson(json, classOfT);
            }
        }
        return null;
    }

    private void notifyDataChanged() {
        if (isLoadCacheDone()) {
            saveCache();
        } else {
            mWaitToSaveCache = true;
        }
    }

    private void saveCache() {
        final String data = new Gson().toJson(mCacheBase);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mCacheFileManager.saveCacheFile(data);
            }
        }).start();
    }

    public void clearCache() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mCacheBase.mCache.clear();
                mCacheFileManager.clearCache();
            }
        }).start();
    }

    private String className(Object object) {
        return object.getClass().getSimpleName();
    }

    private String className(Class object) {
        return object.getSimpleName();
    }

    private CacheBase.CacheItems getCache(String className) {
        return mCacheBase.mCache.get(className);
    }

    private void init(Context context, String cacheName) {
        mCacheFileManager = new CacheFileManager(context, cacheName);
        mCacheBase = new CacheBase();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String data = mCacheFileManager.getCacheData();
                final CacheBase mDbCache = new Gson().fromJson(data, CacheBase.class);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (this) {
                            if (mDbCache != null)
                                mCacheBase.mCache.putAll(mDbCache.mCache);
                            mLoadCacheDone = true;
                            if (mCacheListener != null) {
                                mCacheListener.onLoadCacheDone();
                            }

                            if (mWaitToSaveCache) {
                                notifyDataChanged();
                                mWaitToSaveCache = false;
                            }
                        }
                    }
                });
            }
        }).start();
    }

    public boolean isLoadCacheDone() {
        return mLoadCacheDone;
    }
}
