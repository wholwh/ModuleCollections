package com.byronginvest.modulecollections.data.util;

/**
 * Created by Gosha on 2015-12-23.
 */
public class VariableUtil {
    public static class StringUtil {
        /**
         * 返回boolean值判断字符串是否为空或Null
         * @param str String
         * @return boolean
         */
        public static boolean isEmtyOrNull(String str) {
            return str.length() <= 0 || null == str;
        }
    }
}
