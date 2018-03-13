package com.example.administrator.myrecyclerviewadapter.util;

import java.lang.reflect.ParameterizedType;

/**
 * 作者：Administrator on 2017/7/13 10:30
 * <p>
 * 邮箱：liuzj@hi-board.com
 */


public class TUtil {

    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (ClassCastException e) {
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
