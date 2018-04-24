package com.wanandroid.utils.auxiliary;

import android.content.Context;
import android.content.SharedPreferences;

/**@author  作者:haoyue 
 * @date 创建时间：Mar 23, 2016 9:33:59 PM 
 * @detail(用一句话阐述本class文件的作用:本地存储帮助类) 
 */
public class SharedPreferencesUtils {
	//SharedPreferences的名字
	private static final String PREFERENCE_NAME = "WAN_ANDROID";
	public static void set(Context context, String filename, String name, String data) {
		try {
			SharedPreferences userInfo = context.getSharedPreferences(filename, 0);
			userInfo.edit().putString(name, data).commit();
		} catch (Exception e) {
		}
	}
	public static boolean setString(Context context, String key, String value) {
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, value);
		return editor.commit();
	}
	public static String getString(Context context, String key) {
		return getString(context, key, null);
	}
	public static String getString(Context context, String key,
								   String defaultValue) {
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		return settings.getString(key, defaultValue);
	}
	public static boolean setInt(Context context, String key, int value) {
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt(key, value);
		return editor.commit();
	}
	public static int getInt(Context context, String key) {
		return getInt(context, key, -1);
	}
	public static int getInt(Context context, String key, int defaultValue) {
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		return settings.getInt(key, defaultValue);
	}
	public static boolean setLong(Context context, String key, long value) {
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putLong(key, value);
		return editor.commit();
	}
	public static long getLong(Context context, String key) {
		return getLong(context, key, -1);
	}
	public static long getLong(Context context, String key, long defaultValue) {
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		return settings.getLong(key, defaultValue);
	}

	public static boolean setFloat(Context context, String key, float value) {
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putFloat(key, value);
		return editor.commit();
	}
	public static float setFloat(Context context, String key) {
		return getFloat(context, key, -1);
	}
	public static float getFloat(Context context, String key, float defaultValue) {
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		return settings.getFloat(key, defaultValue);
	}
	public static boolean setBoolean(Context context, String key, boolean value) {
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(key, value);
		return editor.commit();
	}
	public static boolean getBoolean(Context context, String key) {
		return getBoolean(context, key, false);
	}
	public static boolean getBoolean(Context context, String key, boolean defaultValue) {
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		return settings.getBoolean(key, defaultValue);
	}
}
