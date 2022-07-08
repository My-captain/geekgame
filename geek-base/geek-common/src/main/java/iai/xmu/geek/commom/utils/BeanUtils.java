package iai.xmu.geek.commom.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象转换类
 *
 * @Author: iai.xmu.edu.cn

 */
public class BeanUtils {

    public static <T> T toBean(Object o, Class<T> clazz) {
        if (clazz == null || o == null) {
            return null;
        }
        try {
            T newInstance = clazz.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(o, newInstance);
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> List<T> toList(List<?> list, Class<T> clazz) {
        if (list == null || clazz == null) {
            return null;
        }
        try {
            List<T> newList = new ArrayList<>();
            for (Object object : list) {
                T newInstance = clazz.newInstance();
                org.springframework.beans.BeanUtils.copyProperties(object, newInstance);
                newList.add(newInstance);
            }
            return newList;
        } catch (Exception e) {
            return null;
        }
    }

}
