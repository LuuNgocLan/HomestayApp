package com.lanltn.android_core_helper.utils;

import android.util.Log;

public class LogUtils {

    private static final boolean DEBUG = true;
    private static final String DEBUG_TAG = "PROJECT";

    public static void d(String tag, String msg) {
        if (DEBUG) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

            Log.d(DEBUG_TAG + " " + className + "." + methodName + "():" + lineNumber + " " + tag + " ", msg + "");
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

            Log.d(DEBUG_TAG + " " + className + "." + methodName + "():" + lineNumber + " ", msg + "");
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

            Log.d(DEBUG_TAG + " " + className + "." + methodName + "():" + lineNumber + " " + tag + " ", msg + "");
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

            Log.e(DEBUG_TAG + " " + className + "." + methodName + "():" + lineNumber + " " + tag + " ", msg + "");
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

            Log.i(DEBUG_TAG + " " + className + "." + methodName + "():" + lineNumber + " ", msg + "");
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

            Log.e(DEBUG_TAG + " " + className + "." + methodName + "():" + lineNumber + " ", msg + "");
        }
    }
}
