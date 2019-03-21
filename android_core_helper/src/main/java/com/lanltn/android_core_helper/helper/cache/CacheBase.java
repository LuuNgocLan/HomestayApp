package com.lanltn.android_core_helper.helper.cache;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

class CacheBase {

    @SerializedName("cache")
    volatile Map<String, CacheItems> mCache = new HashMap<>();

    static class CacheItems {

        @SerializedName("cache_items")
        Map<String, Object> mCacheItems = new HashMap<>();
    }
}
