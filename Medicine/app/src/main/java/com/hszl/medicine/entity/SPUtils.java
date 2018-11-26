package com.hszl.medicine.entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.hszl.medicine.app.App;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class SPUtils {
    public static final String FILE_NAME = "share_data";
    public SPUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    public static boolean saveUser(User u) {
        return saveObject("user.bin", u);
    }
    public static boolean saveObject(String path, Object obj) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean e;
        try {fos =
                App.instance.getApplicationContext().openFileOutput
                        (path, 0);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            boolean ex = true;
            return ex;
        } catch (FileNotFoundException var22) {
            var22.printStackTrace();
            e = false;
            return e;
        } catch (IOException var23) {
            var23.printStackTrace();
            Log.i("TAG", "duxie" + var23.toString());
            e = false;
        } finally {
            if(null != fos) {
                try {
                    fos.close();
                } catch (IOException var21) {
                    var21.printStackTrace();
                }
            }
            if(null != oos) {
                try {
                    oos.close();
                } catch (Exception var20) {
                    var20.printStackTrace();
                }
            }
        }
        return e;
    }
    public static Object getObject(String path) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;try {
            fis =
                    App.instance.getApplicationContext().openFileInput(path);
            ois = new ObjectInputStream(fis);
            Object e = ois.readObject();
            return e;
        } catch (FileNotFoundException var23) {
            var23.printStackTrace();
        } catch (IOException var24) {
            var24.printStackTrace();
        } catch (ClassNotFoundException var25) {
            var25.printStackTrace();
        } finally {
            if(null != fis) {
                try {
                    fis.close();
                } catch (Exception var22) {
                    var22.printStackTrace();
                }
            }
            if(null != ois) {
                try {
                    ois.close();
                } catch (Exception var21) {
                    var21.printStackTrace();
                }
            }
        }
        return null;
    }
    public static User getUser() {
        return (User)getObject("user.bin");
    }
    public static void put(Context context, String key, Object
            object) {SharedPreferences sp =
            context.getSharedPreferences("share_data", 0);
        SharedPreferences.Editor editor = sp.edit();
        if(object instanceof String) {
            editor.putString(key, (String)object);
        } else if(object instanceof Integer) {
            editor.putInt(key, ((Integer)object).intValue());
        } else if(object instanceof Boolean) {
            editor.putBoolean(key,
                    ((Boolean)object).booleanValue());
        } else if(object instanceof Float) {
            editor.putFloat(key, ((Float)object).floatValue());
        } else if(object instanceof Long) {
            editor.putLong(key, ((Long)object).longValue());
        } else {
            editor.putString(key, object.toString());
        }
        SPUtils.SharedPreferencesCompat.apply(editor);
    }
    public static Object get(Context context, String key, Object
            defaultObject) {
        SharedPreferences sp =
                context.getSharedPreferences("share_data", 0);
        return defaultObject instanceof String?sp.getString(key,
                (String)defaultObject):(defaultObject instanceof Integer?
                Integer.valueOf(sp.getInt(key,
                        ((Integer)defaultObject).intValue())):(defaultObject instanceof
                Boolean?Boolean.valueOf(sp.getBoolean(key,
                ((Boolean)defaultObject).booleanValue())):(defaultObject
                instanceof Float?Float.valueOf(sp.getFloat(key,
                ((Float)defaultObject).floatValue())):(defaultObject instanceof
                Long?Long.valueOf(sp.getLong(key,
                ((Long)defaultObject).longValue())):null))));
    }
    public static void remove(Context context, String key) {
        SharedPreferences sp =
                context.getSharedPreferences("share_data", 0);
        SharedPreferences.Editor editor = sp.edit();editor.remove(key);
        SPUtils.SharedPreferencesCompat.apply(editor);
    }
    public static void clear(Context context) {
        SharedPreferences sp =
                context.getSharedPreferences("share_data", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SPUtils.SharedPreferencesCompat.apply(editor);
    }
    public static boolean contains(Context context, String key)
    {
        SharedPreferences sp =
                context.getSharedPreferences("share_data", 0);
        return sp.contains(key);
    }
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp =
                context.getSharedPreferences("share_data", 0);
        return sp.getAll();
    }
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod =
                findApplyMethod();
        private SharedPreferencesCompat() {
        }
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply", new Class[0]);
            } catch (NoSuchMethodException var1) {
                return null;
            }
        }public static void apply(SharedPreferences.Editor editor) {
            try {
                if(sApplyMethod != null) {
                    sApplyMethod.invoke(editor, new Object[0]);
                    return;
                }
            } catch (IllegalArgumentException var2) {
                ;
            } catch (IllegalAccessException var3) {
                ;
            } catch (InvocationTargetException var4) {
                ;
            }
            editor.commit();
        }
    }
}
