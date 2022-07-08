package iai.xmu.geek.commom.utils;

import com.google.common.collect.Lists;
import iai.xmu.geek.commom.constant.Constant;

import java.util.*;
import java.util.stream.IntStream;

/**
 * StringUtils包装字符串处理类
 *
 * @Author: iai.xmu.edu.cn

 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 取定长字符串,不足的在前面补指定字符
     *
     * @param str     目标字符串
     * @param length  返回字符串长度
     * @param preChar 不足前补字符
     * @return
     */
    public static String fixStr(String str, int length, String preChar) {
        if (str == null) {
            str = "";
        }
        int addLen = length - str.length();
        if (addLen > 0) {
            for (int i = 0; i < addLen; i++) {
                str = preChar + str;
            }
        }
        return str;
    }


    /**
     * 判断集合非空
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断集合为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断对象为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (Constant.EMPTY.equals(object) || Constant.NULL.equals(object)) {
            return true;
        }
        return false;
    }

    /***
     *判断一个集合中的项是否有空
     * @param str
     * @return
     */
    public static boolean isEmpty(String[] str) {
        boolean temp = true;
        for (String s : str) {
            temp = isEmpty(s);
            if (temp) {
                break;
            } else {
                continue;
            }
        }
        return temp;
    }


    /**
     * 从Map中获取某项字符串属性，如果字符串为空直接设定的返回默认字符串
     *
     * @param map
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Map map, String key, String defaultValue) {
        if (map == null) {
            return defaultValue;
        }
        if (map.containsKey(key)) {
            Object obj = map.get(key);
            if (obj == null) {
                return defaultValue;
            } else {
                return obj.toString();
            }
        }
        return defaultValue;
    }

    /**
     * 将一个对象转换为字符串，如果字符串为空直接设定的返回默认字符串
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String getString(Object obj, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (isEmpty(obj.toString())) {
            return defaultValue;
        } else {
            return obj.toString();
        }
    }

    /**
     * 比较两个字符串是否相同(为空返回false)
     *
     * @param str  第一个字符串
     * @param str2 第二个字符串
     * @return 返回 true|false
     * @describe：
     */
    public static boolean equals(String str, String str2) {
        if (isEmpty(str2) || isEmpty(str)) {
            return false;
        }
        return str.equals(str2);
    }

    /**
     * 比较两个长整型数字是否相同(为空返回false)
     *
     * @param l1 第一个长整型数字
     * @param l2 第二个长整型数字
     * @return 返回 true|false
     * @describe：
     */
    public static boolean equals(Long l1, Long l2) {
        if (isEmpty(l1) || isEmpty(l2)) {
            return false;
        }
        return l1.equals(l2);
    }

    /**
     * 比较两个数组是否相同
     * 使用list自带的sort方法先进性排序，然后转成toString去判断两个集合是否相等
     *
     * @param list1
     * @param list2
     * @return
     */
    public static boolean equals(List list1, List list2) {
        if (isEmpty(list1) || isEmpty(list2)) {
            return false;
        }
        list1.sort(Comparator.comparing(String::hashCode));
        list2.sort(Comparator.comparing(String::hashCode));
        return list1.toString().equals(list2.toString());
    }

    /**
     * 截取字符串
     *
     * @param str   待截取的字符串
     * @param start 截取起始位置 （ 1 表示第一位 -1表示倒数第1位）
     * @param end   截取结束位置 （如上index）
     * @return
     */
    public static String sub(String str, int start, int end) {
        if (str == null || str.equals("")) {
            return "";
        }
        int len = str.length();
        start = start < 0 ? len + start : start - 1;
        end = end < 0 ? len + end + 1 : end;

        return str.substring(start, end);
    }

    /**
     * 随机产生一个num位数的数字密码
     *
     * @return
     */
    public static String generateRandomNum(int num) {
        List<String> list = Lists.newArrayList("0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9");
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, num).forEach(i -> sb.append(list.get(i)));
        return sb.toString();
    }

    /**
     * 手机号码前三后四脱敏
     *
     * @param mobile
     * @return
     */
    public static String mobileEncrypt(String mobile) {
        if (StringUtils.isEmpty(mobile) || (mobile.length() != 11)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 身份证前三后四脱敏
     *
     * @param id
     * @return
     */
    public static String idEncrypt(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
    }

    /**
     * 护照前2后3位脱敏，护照一般为8或9位
     *
     * @param id
     * @return
     */
    public static String idPassport(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.substring(0, 2) + new String(new char[id.length() - 5]).replace("\0", "*") + id.substring(id.length() - 3);
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。
     * 如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：hello_world->helloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains(Constant.UNDERLINE)) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1).toLowerCase();
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split(Constant.UNDERLINE);
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 去除前后空字符串
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return isEmpty(str) ? str : str.trim();
    }

}
