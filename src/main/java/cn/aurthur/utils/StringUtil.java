package cn.aurthur.utils;

import cn.aurthur.lang.pool.EmptyPool;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串相关工具类
 * <br>
 * equals 比较两个字符串 <br>
 * length 检查字符串长度 <br>
 * isAlpha isNumeric isWhitespace判定字符串的类型是否为：字母、数字、空白 <br>
 * getBytes 字符串转 byte <br>
 * createString byte 转字符串 <br>
 * count 统计字符出现的次数 <br>
 * removeAll 移除字符串中所有给定字符串 <br>
 * builder 创建StringBuilder对象 <br>
 * isBlank isAllBlank isAnyBlank 判断字符串是否是 空 <br>
 * trim trimStart trimEnd 除去字符串头尾部的字符串 <br>
 * split 字符串分割 <br>
 * defaultIfNull 默认值函数 <br>
 *
 * @author aurthur
 * @since 1.0.0
 */
public abstract class StringUtil {
    public StringUtil() {
        throw new AssertionError("工具类不允许实例化");
    }

    public static final String EMPTY = "";

    /*
     * ========================================================================== ==
     */
    /* 比较函数。 */
    /*                                                                              */
    /* 以下方法用来比较两个字符串是否相同。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 比较两个字符串（大小写敏感）。
     *
     * <pre>
     * StringUtil.equals(null, null)   = true
     * StringUtil.equals(null, &quot;abc&quot;)  = false
     * StringUtil.equals(&quot;abc&quot;, null)  = false
     * StringUtil.equals(&quot;abc&quot;, &quot;abc&quot;) = true
     * StringUtil.equals(&quot;abc&quot;, &quot;ABC&quot;) = false
     * </pre>
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     */
    public static boolean equals(CharSequence str1, CharSequence str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.toString().equals(str2.toString());
    }

    /**
     * 比较两个字符串（大小写不敏感）。
     *
     * <pre>
     * StringUtil.equalsIgnoreCase(null, null)   = true
     * StringUtil.equalsIgnoreCase(null, &quot;abc&quot;)  = false
     * StringUtil.equalsIgnoreCase(&quot;abc&quot;, null)  = false
     * StringUtil.equalsIgnoreCase(&quot;abc&quot;, &quot;abc&quot;) = true
     * StringUtil.equalsIgnoreCase(&quot;abc&quot;, &quot;ABC&quot;) = true
     * </pre>
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     */
    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.toString().equalsIgnoreCase(str2.toString());
    }

    /*
     * ========================================================================== ==
     */
    /* 字符串类型判定函数。 */
    /*                                                                              */
    /* 判定字符串的类型是否为：字母、数字、空白等 */
    /*
     * ========================================================================== ==
     */

    /**
     * 该方法主要使用正则表达式来判断字符串中是否包含字母
     */
    public static boolean containAlpha(String cardNum) {
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(cardNum);
        return m.matches();
    }

    /**
     * 判断字符串是否只包含unicode字母。
     *
     * <div>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
     * <code>true</code>。
     * </div>
     *
     * <pre>
     * StringUtil.isAlpha(null)   = false
     * StringUtil.isAlpha(&quot;&quot;)     = true
     * StringUtil.isAlpha(&quot;  &quot;)   = false
     * StringUtil.isAlpha(&quot;abc&quot;)  = true
     * StringUtil.isAlpha(&quot;ab2c&quot;) = false
     * StringUtil.isAlpha(&quot;ab-c&quot;) = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果字符串非<code>null</code>并且全由unicode字母组成，则返回<code>true</code>
     */
    public static boolean isAlpha(String str) {
        if (str == null) {
            return false;
        }

        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断字符串是否只包含unicode字母和空格<code>' '</code>。
     *
     * <div>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
     * <code>true</code>。
     * </div>
     *
     * <pre>
     * StringUtil.isAlphaSpace(null)   = false
     * StringUtil.isAlphaSpace(&quot;&quot;)     = true
     * StringUtil.isAlphaSpace(&quot;  &quot;)   = true
     * StringUtil.isAlphaSpace(&quot;abc&quot;)  = true
     * StringUtil.isAlphaSpace(&quot;ab c&quot;) = true
     * StringUtil.isAlphaSpace(&quot;ab2c&quot;) = false
     * StringUtil.isAlphaSpace(&quot;ab-c&quot;) = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果字符串非<code>null</code>并且全由unicode字母和空格组成，则返回<code>true</code>
     */
    public static boolean isAlphaSpace(String str) {
        if (str == null) {
            return false;
        }

        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (!Character.isLetter(str.charAt(i)) && (str.charAt(i) != ' ')) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断字符串是否只包含unicode字母和数字。
     *
     * <div>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
     * <code>true</code>。
     * </div>
     *
     * <pre>
     * StringUtil.isAlphanumeric(null)   = false
     * StringUtil.isAlphanumeric(&quot;&quot;)     = true
     * StringUtil.isAlphanumeric(&quot;  &quot;)   = false
     * StringUtil.isAlphanumeric(&quot;abc&quot;)  = true
     * StringUtil.isAlphanumeric(&quot;ab c&quot;) = false
     * StringUtil.isAlphanumeric(&quot;ab2c&quot;) = true
     * StringUtil.isAlphanumeric(&quot;ab-c&quot;) = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果字符串非<code>null</code>并且全由unicode字母数字组成，则返回<code>true</code>
     */
    public static boolean isAlphanumeric(String str) {
        if (str == null) {
            return false;
        }

        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断字符串是否只包含unicode字母数字和空格<code>' '</code>。
     *
     * <div>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
     * <code>true</code>。
     * </div>
     *
     * <pre>
     * StringUtil.isAlphanumericSpace(null)   = false
     * StringUtil.isAlphanumericSpace(&quot;&quot;)     = true
     * StringUtil.isAlphanumericSpace(&quot;  &quot;)   = true
     * StringUtil.isAlphanumericSpace(&quot;abc&quot;)  = true
     * StringUtil.isAlphanumericSpace(&quot;ab c&quot;) = true
     * StringUtil.isAlphanumericSpace(&quot;ab2c&quot;) = true
     * StringUtil.isAlphanumericSpace(&quot;ab-c&quot;) = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果字符串非<code>null</code>并且全由unicode字母数字和空格组成，则返回<code>true</code>
     */
    public static boolean isAlphanumericSpace(String str) {
        if (str == null) {
            return false;
        }

        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (!Character.isLetterOrDigit(str.charAt(i)) && (str.charAt(i) != ' ')) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断字符串是否只包含unicode数字。
     *
     * <div>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
     * <code>true</code>。
     * </div>
     *
     * <pre>
     * StringUtil.isNumeric(null)   = false
     * StringUtil.isNumeric(&quot;&quot;)     = true
     * StringUtil.isNumeric(&quot;  &quot;)   = false
     * StringUtil.isNumeric(&quot;123&quot;)  = true
     * StringUtil.isNumeric(&quot;12 3&quot;) = false
     * StringUtil.isNumeric(&quot;ab2c&quot;) = false
     * StringUtil.isNumeric(&quot;12-3&quot;) = false
     * StringUtil.isNumeric(&quot;12.3&quot;) = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果字符串非<code>null</code>并且全由unicode数字组成，则返回<code>true</code>
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }

        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断字符串是否只包含unicode数字和空格<code>' '</code>。
     *
     * <div>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
     * <code>true</code>。
     * </div>
     *
     * <pre>
     * StringUtil.isNumericSpace(null)   = false
     * StringUtil.isNumericSpace(&quot;&quot;)     = true
     * StringUtil.isNumericSpace(&quot;  &quot;)   = true
     * StringUtil.isNumericSpace(&quot;123&quot;)  = true
     * StringUtil.isNumericSpace(&quot;12 3&quot;) = true
     * StringUtil.isNumericSpace(&quot;ab2c&quot;) = false
     * StringUtil.isNumericSpace(&quot;12-3&quot;) = false
     * StringUtil.isNumericSpace(&quot;12.3&quot;) = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果字符串非<code>null</code>并且全由unicode数字和空格组成，则返回<code>true</code>
     */
    public static boolean isNumericSpace(String str) {
        if (str == null) {
            return false;
        }

        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(str.charAt(i)) && (str.charAt(i) != ' ')) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断字符串是否只包含unicode空白。
     *
     * <div>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
     * <code>true</code>。
     * </div>
     *
     * <pre>
     * StringUtil.isWhitespace(null)   = false
     * StringUtil.isWhitespace(&quot;&quot;)     = true
     * StringUtil.isWhitespace(&quot;  &quot;)   = true
     * StringUtil.isWhitespace(&quot;abc&quot;)  = false
     * StringUtil.isWhitespace(&quot;ab2c&quot;) = false
     * StringUtil.isWhitespace(&quot;ab-c&quot;) = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果字符串非<code>null</code>并且全由unicode空白组成，则返回<code>true</code>
     */
    public static boolean isWhitespace(String str) {
        if (str == null) {
            return false;
        }

        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    // ------------------------------------------------------------------------
    // length

    /**
     * 检查字符串长度，null 返回 0
     */
    public static int length(final CharSequence s) {
        return s == null ? 0 : s.length();
    }

    public static int trimLength(final CharSequence s) {
        return (s == null) ? 0 : s.toString().trim().length();
    }

    // ------------------------------------------------------------------------
    // lastIndexOf
    public static int lastIndexOf(CharSequence path, char ch) {
        if (path == null) {
            return -1;
        }
        return path.toString().lastIndexOf(ch);
    }

    /**
     * 按照指定编码将字符串转 byte
     */
    public static byte[] getBytes(CharSequence s, String encoding) {
        try {
            return s == null ? null : s.toString().getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(String.format("Encoding (%s) is not supported by your JVM", encoding),
                    e);
        }
    }

    /**
     * 按照指定编码将字符串转 byte
     */
    public static byte[] getBytes(CharSequence s, Charset charset) {
        if (charset == null) {
            // 如果 charset 为 null, 使用默认编码
            return s.toString().getBytes();
        }
        return s == null ? null : s.toString().getBytes(charset);
    }

    /**
     * 将 byte 以指定编码编码转换为字符串
     */
    public static String createString(byte[] bytes, String encoding) {
        try {
            return bytes == null ? null : new String(bytes, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(String.format("Encoding (%s) is not supported by your JVM", encoding),
                    e);
        }
    }

    /**
     * 将 byte 以指定编码编码转换为字符串
     */
    public static String createString(byte[] bytes, Charset charset) {
        if (charset == null) {
            // 如果 charset 为 null, 使用默认编码
            return new String(bytes);
        }
        return bytes == null ? null : new String(bytes, charset);
    }

    /**
     * 将字符串以 UTF-8 编码转换为 byte
     */
    public static byte[] getBytesWithUTF8(CharSequence s) {
        return getBytes(s.toString(), CharsetUtil.UTF_8);
    }

    /**
     * 将 byte 以 UTF-8 编码转换为字符串
     */
    public static String createStringWithUTF8(byte[] bytes) {
        return createString(bytes, CharsetUtil.UTF_8);
    }

    /**
     * 统计 c 在 s 中出现了几次
     */
    public static int count(String s, char c) {
        if (EmptyUtil.isEmpty(s)) {
            return 0;
        }

        int count = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }

        return count;
    }

    /**
     * 移除字符串中所有给定字符串<br>
     * 例：removeAll("aa-bb-cc-dd", "-") =》 aabbccdd
     *
     * @param str         字符串
     * @param strToRemove 被移除的字符串
     * @return 移除后的字符串
     */
    public static String removeAll(CharSequence str, CharSequence strToRemove) {
        if (EmptyUtil.isEmpty(str) || EmptyUtil.isEmpty(strToRemove)) {
            return str.toString();
        }
        return str.toString().replace(strToRemove, EMPTY);
    }

    /**
     * 去除字符串中指定的多个字符，如有多个则全部去除
     *
     * @param str   字符串
     * @param chars 字符列表
     * @return 去除后的字符
     */
    public static String removeAll(CharSequence str, char... chars) {
        if (EmptyUtil.isEmptyStr(str.toString()) || EmptyUtil.isEmpty(chars)) {
            return str.toString();
        }
        final int len = str.length();
        final StringBuilder builder = builder(len);
        char c;
        for (int i = 0; i < len; i++) {
            c = str.charAt(i);
            if (!ArrayUtil.contains(chars, c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * 创建StringBuilder对象
     *
     * @return StringBuilder对象
     */
    public static StringBuilder builder() {
        return new StringBuilder();
    }

    /**
     * 创建StringBuilder对象
     *
     * @param capacity 初始大小
     * @return StringBuilder对象
     */
    public static StringBuilder builder(int capacity) {
        return new StringBuilder(capacity);
    }

    /**
     * 创建StringBuilder对象
     *
     * @param strs 初始字符串列表
     * @return StringBuilder对象
     */
    public static StringBuilder builder(CharSequence... strs) {
        final StringBuilder sb = new StringBuilder();
        for (CharSequence str : strs) {
            sb.append(str);
        }
        return sb;
    }

    /**
     * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     *
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank(&quot;&quot;)        = true
     * StringUtil.isBlank(&quot; &quot;)       = true
     * StringUtil.isBlank(&quot;bob&quot;)     = false
     * StringUtil.isBlank(&quot;  bob  &quot;) = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAllBlank(String... strings) {
        if (strings == null) {
            return true;
        }
        for (String string : strings) {
            if (!isBlank(string)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnyBlank(String... strings) {
        if (strings == null) {
            return true;
        }
        for (String string : strings) {
            if (isBlank(string)) {
                return true;
            }
        }
        return false;
    }

    /*
     * ========================================================================== ==
     */
    /* 去空白（或指定字符）的函数。 */
    /*                                                                              */
    /* 以下方法用来除去一个字串中的空白或指定字符。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 除去字符串头尾部的空白，如果字符串是<code>null</code>，依然返回<code>null</code>。
     *
     * <div>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     *
     * <pre>
     * StringUtil.trim(null)          = null
     * StringUtil.trim(&quot;&quot;)            = &quot;&quot;
     * StringUtil.trim(&quot;     &quot;)       = &quot;&quot;
     * StringUtil.trim(&quot;abc&quot;)         = &quot;abc&quot;
     * StringUtil.trim(&quot;    abc    &quot;) = &quot;abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String trim(String str) {
        return __trim(str, null, 0);
    }

    /**
     * Trims array of strings. <code>null</code> array elements are ignored.
     */
    public static void trimAll(String[] strings) {
        if (strings == null) {
            return;
        }
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            if (string != null) {
                strings[i] = trim(string);
            }
        }
    }

    /**
     * 除去字符串头尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     *
     * <pre>
     * StringUtil.trim(null, *)          = null
     * StringUtil.trim(&quot;&quot;, *)            = &quot;&quot;
     * StringUtil.trim(&quot;abc&quot;, null)      = &quot;abc&quot;
     * StringUtil.trim(&quot;  abc&quot;, null)    = &quot;abc&quot;
     * StringUtil.trim(&quot;abc  &quot;, null)    = &quot;abc&quot;
     * StringUtil.trim(&quot; abc &quot;, null)    = &quot;abc&quot;
     * StringUtil.trim(&quot;  abcyx&quot;, &quot;xyz&quot;) = &quot;  abc&quot;
     * </pre>
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String trim(String str, String stripChars) {
        return __trim(str, stripChars, 0);
    }

    /**
     * 除去字符串头部的空白，如果字符串是<code>null</code>，则返回<code>null</code>。
     *
     * <div>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     *
     * <pre>
     * StringUtil.trimStart(null)         = null
     * StringUtil.trimStart(&quot;&quot;)           = &quot;&quot;
     * StringUtil.trimStart(&quot;abc&quot;)        = &quot;abc&quot;
     * StringUtil.trimStart(&quot;  abc&quot;)      = &quot;abc&quot;
     * StringUtil.trimStart(&quot;abc  &quot;)      = &quot;abc  &quot;
     * StringUtil.trimStart(&quot; abc &quot;)      = &quot;abc &quot;
     * </pre>
     *
     * </div>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
     *         <code>null</code>
     */
    public static String trimStart(String str) {
        return __trim(str, null, -1);
    }

    /**
     * 除去字符串头部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     *
     * <pre>
     * StringUtil.trimStart(null, *)          = null
     * StringUtil.trimStart(&quot;&quot;, *)            = &quot;&quot;
     * StringUtil.trimStart(&quot;abc&quot;, &quot;&quot;)        = &quot;abc&quot;
     * StringUtil.trimStart(&quot;abc&quot;, null)      = &quot;abc&quot;
     * StringUtil.trimStart(&quot;  abc&quot;, null)    = &quot;abc&quot;
     * StringUtil.trimStart(&quot;abc  &quot;, null)    = &quot;abc  &quot;
     * StringUtil.trimStart(&quot; abc &quot;, null)    = &quot;abc &quot;
     * StringUtil.trimStart(&quot;yxabc  &quot;, &quot;xyz&quot;) = &quot;abc  &quot;
     * </pre>
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String trimStart(String str, String stripChars) {
        return __trim(str, stripChars, -1);
    }

    /**
     * 除去字符串尾部的空白，如果字符串是<code>null</code>，则返回<code>null</code>。
     *
     * <div>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     *
     * <pre>
     * StringUtil.trimEnd(null)       = null
     * StringUtil.trimEnd(&quot;&quot;)         = &quot;&quot;
     * StringUtil.trimEnd(&quot;abc&quot;)      = &quot;abc&quot;
     * StringUtil.trimEnd(&quot;  abc&quot;)    = &quot;  abc&quot;
     * StringUtil.trimEnd(&quot;abc  &quot;)    = &quot;abc&quot;
     * StringUtil.trimEnd(&quot; abc &quot;)    = &quot; abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
     *         <code>null</code>
     */
    public static String trimEnd(String str) {
        return __trim(str, null, 1);
    }

    /**
     * 除去字符串尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     *
     * <pre>
     * StringUtil.trimEnd(null, *)          = null
     * StringUtil.trimEnd(&quot;&quot;, *)            = &quot;&quot;
     * StringUtil.trimEnd(&quot;abc&quot;, &quot;&quot;)        = &quot;abc&quot;
     * StringUtil.trimEnd(&quot;abc&quot;, null)      = &quot;abc&quot;
     * StringUtil.trimEnd(&quot;  abc&quot;, null)    = &quot;  abc&quot;
     * StringUtil.trimEnd(&quot;abc  &quot;, null)    = &quot;abc&quot;
     * StringUtil.trimEnd(&quot; abc &quot;, null)    = &quot; abc&quot;
     * StringUtil.trimEnd(&quot;  abcyx&quot;, &quot;xyz&quot;) = &quot;  abc&quot;
     * </pre>
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String trimEnd(String str, String stripChars) {
        return __trim(str, stripChars, 1);
    }

    /**
     * 除去字符串头尾部的空白，如果结果字符串是空字符串<code>""</code>，则返回<code>null</code>。
     *
     * <div>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     *
     * <pre>
     * StringUtil.trimToNull(null)          = null
     * StringUtil.trimToNull(&quot;&quot;)            = null
     * StringUtil.trimToNull(&quot;     &quot;)       = null
     * StringUtil.trimToNull(&quot;abc&quot;)         = &quot;abc&quot;
     * StringUtil.trimToNull(&quot;    abc    &quot;) = &quot;abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
     *         <code>null</code>
     */
    public static String trimToNull(String str) {
        return trimToNull(str, null);
    }

    /**
     * 除去字符串头尾部的空白，如果结果字符串是空字符串<code>""</code>，则返回<code>null</code>。
     *
     * <div>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     *
     * <pre>
     * StringUtil.trim(null, *)          = null
     * StringUtil.trim(&quot;&quot;, *)            = null
     * StringUtil.trim(&quot;abc&quot;, null)      = &quot;abc&quot;
     * StringUtil.trim(&quot;  abc&quot;, null)    = &quot;abc&quot;
     * StringUtil.trim(&quot;abc  &quot;, null)    = &quot;abc&quot;
     * StringUtil.trim(&quot; abc &quot;, null)    = &quot;abc&quot;
     * StringUtil.trim(&quot;  abcyx&quot;, &quot;xyz&quot;) = &quot;  abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
     *         <code>null</code>
     */
    public static String trimToNull(String str, String stripChars) {
        String result = trim(str, stripChars);

        if ((result == null) || (result.length() == 0)) {
            return null;
        }

        return result;
    }

    /**
     * 除去字符串头尾部的空白，如果字符串是<code>null</code>，则返回空字符串<code>""</code>。
     *
     * <div>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     *
     * <pre>
     * StringUtil.trimToEmpty(null)          = &quot;&quot;
     * StringUtil.trimToEmpty(&quot;&quot;)            = &quot;&quot;
     * StringUtil.trimToEmpty(&quot;     &quot;)       = &quot;&quot;
     * StringUtil.trimToEmpty(&quot;abc&quot;)         = &quot;abc&quot;
     * StringUtil.trimToEmpty(&quot;    abc    &quot;) = &quot;abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
     *         <code>null</code>
     */
    public static String trimToEmpty(String str) {
        return trimToEmpty(str, null);
    }

    /**
     * 除去字符串头尾部的空白，如果字符串是<code>null</code>，则返回空字符串<code>""</code>。
     *
     * <div>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     *
     * <pre>
     * StringUtil.trim(null, *)          = &quot;&quot;
     * StringUtil.trim(&quot;&quot;, *)            = &quot;&quot;
     * StringUtil.trim(&quot;abc&quot;, null)      = &quot;abc&quot;
     * StringUtil.trim(&quot;  abc&quot;, null)    = &quot;abc&quot;
     * StringUtil.trim(&quot;abc  &quot;, null)    = &quot;abc&quot;
     * StringUtil.trim(&quot; abc &quot;, null)    = &quot;abc&quot;
     * StringUtil.trim(&quot;  abcyx&quot;, &quot;xyz&quot;) = &quot;  abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
     *         <code>null</code>
     */
    public static String trimToEmpty(String str, String stripChars) {
        String result = trim(str, stripChars);

        if (result == null) {
            return EmptyPool.EMPTY_STRING;
        }

        return result;
    }

    /**
     * 除去字符串头尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     *
     * <pre>
     * StringUtil.trim(null, *)          = null
     * StringUtil.trim(&quot;&quot;, *)            = &quot;&quot;
     * StringUtil.trim(&quot;abc&quot;, null)      = &quot;abc&quot;
     * StringUtil.trim(&quot;  abc&quot;, null)    = &quot;abc&quot;
     * StringUtil.trim(&quot;abc  &quot;, null)    = &quot;abc&quot;
     * StringUtil.trim(&quot; abc &quot;, null)    = &quot;abc&quot;
     * StringUtil.trim(&quot;  abcyx&quot;, &quot;xyz&quot;) = &quot;  abc&quot;
     * </pre>
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     * @param mode       <code>-1</code>表示trimStart，<code>0</code>表示trim全部，
     *                   <code>1</code>表示trimEnd
     * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    private static String __trim(String str, String stripChars, int mode) {
        if (str == null) {
            return null;
        }

        int length = str.length();
        int start = 0;
        int end = length;

        // 扫描字符串头部
        if (mode <= 0) {
            if (stripChars == null) {
                while ((start < end) && (Character.isWhitespace(str.charAt(start)))) {
                    start++;
                }
            } else if (stripChars.length() == 0) {
                return str;
            } else {
                while ((start < end) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                    start++;
                }
            }
        }

        // 扫描字符串尾部
        if (mode >= 0) {
            if (stripChars == null) {
                while ((start < end) && (Character.isWhitespace(str.charAt(end - 1)))) {
                    end--;
                }
            } else if (stripChars.length() == 0) {
                return str;
            } else {
                while ((start < end) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                    end--;
                }
            }
        }

        if ((start > 0) || (end < length)) {
            return str.substring(start, end);
        }

        return str;
    }

    /*
     * ========================================================================== ==
     */
    /* 字符串分割函数。 */
    /*                                                                              */
    /* 将字符串按指定分隔符分割。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 将字符串按空白字符分割。
     *
     * <div>
     * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.split(null)       = null
     * StringUtil.split(&quot;&quot;)         = []
     * StringUtil.split(&quot;abc def&quot;)  = [&quot;abc&quot;, &quot;def&quot;]
     * StringUtil.split(&quot;abc  def&quot;) = [&quot;abc&quot;, &quot;def&quot;]
     * StringUtil.split(&quot; abc &quot;)    = [&quot;abc&quot;]
     * </pre>
     *
     * </div>
     *
     * @param str 要分割的字符串
     * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String[] split(String str) {
        return split(str, null, -1);
    }

    /**
     * 将字符串按指定字符分割。
     *
     * <div>
     * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.split(null, *)         = null
     * StringUtil.split(&quot;&quot;, *)           = []
     * StringUtil.split(&quot;a.b.c&quot;, '.')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtil.split(&quot;a..b.c&quot;, '.')   = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * StringUtil.split(&quot;a:b:c&quot;, '.')    = [&quot;a:b:c&quot;]
     * StringUtil.split(&quot;a b c&quot;, ' ')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * </pre>
     *
     * </div>
     *
     * @param str           要分割的字符串
     * @param separatorChar 分隔符
     * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String[] split(String str, char separatorChar) {
        if (str == null) {
            return null;
        }

        int length = str.length();

        if (length == 0) {
            return EmptyPool.EMPTY_STRING_ARRAY;
        }

        List<String> list = CollectionUtil.createArrayList();
        int i = 0;
        int start = 0;
        boolean match = false;

        while (i < length) {
            if (str.charAt(i) == separatorChar) {
                if (match) {
                    list.add(str.substring(start, i));
                    match = false;
                }

                start = ++i;
                continue;
            }

            match = true;
            i++;
        }

        if (match) {
            list.add(str.substring(start, i));
        }

        return list.toArray(new String[0]);
    }

    /**
     * 将字符串按指定字符分割。
     *
     * <div>
     * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.split(null, *)                = null
     * StringUtil.split(&quot;&quot;, *)                  = []
     * StringUtil.split(&quot;abc def&quot;, null)        = [&quot;abc&quot;, &quot;def&quot;]
     * StringUtil.split(&quot;abc def&quot;, &quot; &quot;)         = [&quot;abc&quot;, &quot;def&quot;]
     * StringUtil.split(&quot;abc  def&quot;, &quot; &quot;)        = [&quot;abc&quot;, &quot;def&quot;]
     * StringUtil.split(&quot; ab:  cd::ef  &quot;, &quot;:&quot;)  = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtil.split(&quot;abc.def&quot;, &quot;&quot;)          = [&quot;abc.def&quot;]
     * </pre>
     *
     * </div>
     *
     * @param str            要分割的字符串
     * @param separatorChars 分隔符
     * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String[] split(String str, String separatorChars) {
        return split(str, separatorChars, -1);
    }

    /**
     * 将字符串按指定字符分割。
     *
     * <div>
     * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.split(null, *, *)                 = null
     * StringUtil.split(&quot;&quot;, *, *)                   = []
     * StringUtil.split(&quot;ab cd ef&quot;, null, 0)        = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtil.split(&quot;  ab   cd ef  &quot;, null, 0)  = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtil.split(&quot;ab:cd::ef&quot;, &quot;:&quot;, 0)        = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
     * StringUtil.split(&quot;ab:cd:ef&quot;, &quot;:&quot;, 2)         = [&quot;ab&quot;, &quot;cdef&quot;]
     * StringUtil.split(&quot;abc.def&quot;, &quot;&quot;, 2)           = [&quot;abc.def&quot;]
     * </pre>
     *
     * </div>
     *
     * @param str            要分割的字符串
     * @param separatorChars 分隔符
     * @param max            返回的数组的最大个数，如果小于等于0，则表示无限制
     * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String[] split(String str, String separatorChars, int max) {
        if (str == null) {
            return null;
        }

        int length = str.length();

        if (length == 0) {
            return EmptyPool.EMPTY_STRING_ARRAY;
        }

        List<String> list = CollectionUtil.createArrayList();
        int sizePlus1 = 1;
        int i = 0;
        int start = 0;
        boolean match = false;

        if (separatorChars == null) {
            // null表示使用空白作为分隔符
            while (i < length) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match) {
                        if (sizePlus1++ == max) {
                            i = length;
                        }

                        list.add(str.substring(start, i));
                        match = false;
                    }

                    start = ++i;
                    continue;
                }

                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // 优化分隔符长度为1的情形
            char sep = separatorChars.charAt(0);

            while (i < length) {
                if (str.charAt(i) == sep) {
                    if (match) {
                        if (sizePlus1++ == max) {
                            i = length;
                        }

                        list.add(str.substring(start, i));
                        match = false;
                    }

                    start = ++i;
                    continue;
                }

                match = true;
                i++;
            }
        } else {
            // 一般情形
            while (i < length) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match) {
                        if (sizePlus1++ == max) {
                            i = length;
                        }

                        list.add(str.substring(start, i));
                        match = false;
                    }

                    start = ++i;
                    continue;
                }

                match = true;
                i++;
            }
        }

        if (match) {
            list.add(str.substring(start, i));
        }

        return list.toArray(new String[0]);
    }

    /*
     * ========================================================================== ==
     */
    /* 默认值函数。 */
    /*                                                                              */
    /* 当字符串为null、empty或blank时，将字符串转换成指定的默认字符串。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 如果字符串是<code>null</code>，则返回空字符串<code>""</code>，否则返回字符串本身。
     *
     * <pre>
     * StringUtil.defaultIfNull(null)  = &quot;&quot;
     * StringUtil.defaultIfNull(&quot;&quot;)    = &quot;&quot;
     * StringUtil.defaultIfNull(&quot;  &quot;)  = &quot;  &quot;
     * StringUtil.defaultIfNull(&quot;bat&quot;) = &quot;bat&quot;
     * </pre>
     *
     * @param str 要转换的字符串
     * @return 字符串本身或空字符串<code>""</code>
     */
    public static String defaultIfNull(String str) {
        return (str == null) ? EmptyPool.EMPTY_STRING : str;
    }

    /**
     * 如果字符串是<code>null</code>，则返回指定默认字符串，否则返回字符串本身。
     *
     * <pre>
     * StringUtil.defaultIfNull(null, &quot;default&quot;)  = &quot;default&quot;
     * StringUtil.defaultIfNull(&quot;&quot;, &quot;default&quot;)    = &quot;&quot;
     * StringUtil.defaultIfNull(&quot;  &quot;, &quot;default&quot;)  = &quot;  &quot;
     * StringUtil.defaultIfNull(&quot;bat&quot;, &quot;default&quot;) = &quot;bat&quot;
     * </pre>
     *
     * @param str        要转换的字符串
     * @param defaultStr 默认字符串
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfNull(String str, String defaultStr) {
        return (str == null) ? defaultStr : str;
    }

    /*
     * ========================================================================== ==
     */
    /* 大小写转换。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 将字符串转换成大写。
     *
     * <div>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.toUpperCasing(null)  = null
     * StringUtil.toUpperCasing(&quot;&quot;)    = &quot;&quot;
     * StringUtil.toUpperCasing(&quot;aBc&quot;) = &quot;ABC&quot;
     * </pre>
     *
     * </div>
     *
     * @param str 要转换的字符串
     * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String toUpperCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toUpperCase();
    }

    /**
     * 将字符串转换成小写。
     *
     * <div>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.toLowerCasing(null)  = null
     * StringUtil.toLowerCasing(&quot;&quot;)    = &quot;&quot;
     * StringUtil.toLowerCasing(&quot;aBc&quot;) = &quot;abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str 要转换的字符串
     * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String toLowerCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toLowerCase();
    }

    /**
     * 将字符串的首字符转成大写（<code>Character.toTitleCase</code>），其它字符不变。
     *
     * <div>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.capitalize(null)  = null
     * StringUtil.capitalize(&quot;&quot;)    = &quot;&quot;
     * StringUtil.capitalize(&quot;cat&quot;) = &quot;Cat&quot;
     * StringUtil.capitalize(&quot;cAt&quot;) = &quot;CAt&quot;
     * </pre>
     *
     * </div>
     *
     * @param str 要转换的字符串
     * @return 首字符为大写的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String capitalize(String str) {
        int strLen;

        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }

        return Character.toTitleCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 将字符串的首字符转成小写，其它字符不变。
     *
     * <div>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.uncapitalize(null)  = null
     * StringUtil.uncapitalize(&quot;&quot;)    = &quot;&quot;
     * StringUtil.uncapitalize(&quot;Cat&quot;) = &quot;cat&quot;
     * StringUtil.uncapitalize(&quot;CAT&quot;) = &quot;cAT&quot;
     * </pre>
     *
     * </div>
     *
     * @param str 要转换的字符串
     * @return 首字符为小写的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String uncapitalize(String str) {
        int strLen;

        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }

        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 反转字符串的大小写。
     *
     * <div>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.swapCasing(null)                 = null
     * StringUtil.swapCasing(&quot;&quot;)                   = &quot;&quot;
     * StringUtil.swapCasing(&quot;The dog has a BONE&quot;) = &quot;tHE DOG HAS A bone&quot;
     * </pre>
     *
     * </div>
     *
     * @param str 要转换的字符串
     * @return 大小写被反转的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String swapCase(String str) {
        int strLen;

        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }

        StringBuilder builder = new StringBuilder(strLen);

        char ch = 0;

        for (int i = 0; i < strLen; i++) {
            ch = str.charAt(i);

            if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }

            builder.append(ch);
        }

        return builder.toString();
    }

    /*
     * ========================================================================== ==
     */
    /* 字符串查找函数 —— 字符或字符串。 */
    /*                                                                              */
    /* 在字符串中查找指定字符或字符串。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 在字符串中查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
     *
     * <pre>
     * StringUtil.indexOf(null, *)         = -1
     * StringUtil.indexOf(&quot;&quot;, *)           = -1
     * StringUtil.indexOf(&quot;aabaabaa&quot;, 'a') = 0
     * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b') = 2
     * </pre>
     *
     * @param str        要扫描的字符串
     * @param searchChar 要查找的字符
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOf(String str, char searchChar) {
        if ((str == null) || (str.length() == 0)) {
            return -1;
        }

        return str.indexOf(searchChar);
    }

    /**
     * 在字符串中查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
     *
     * <pre>
     * StringUtil.indexOf(null, *, *)          = -1
     * StringUtil.indexOf(&quot;&quot;, *, *)            = -1
     * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', 0)  = 2
     * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', 3)  = 5
     * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', 9)  = -1
     * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', -1) = 2
     * </pre>
     *
     * @param str        要扫描的字符串
     * @param searchChar 要查找的字符
     * @param startPos   开始搜索的索引值，如果小于0，则看作0
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOf(String str, char searchChar, int startPos) {
        if ((str == null) || (str.length() == 0)) {
            return -1;
        }

        return str.indexOf(searchChar, startPos);
    }

    /**
     * 在字符串中查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
     *
     * <pre>
     * StringUtil.indexOf(null, *)          = -1
     * StringUtil.indexOf(*, null)          = -1
     * StringUtil.indexOf(&quot;&quot;, &quot;&quot;)           = 0
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;)  = 0
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;)  = 2
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;) = 1
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;&quot;)   = 0
     * </pre>
     *
     * @param str       要扫描的字符串
     * @param searchStr 要查找的字符串
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOf(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }

        return str.indexOf(searchStr);
    }

    /**
     * 在字符串中查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
     *
     * <pre>
     * StringUtil.indexOf(null, *, *)          = -1
     * StringUtil.indexOf(*, null, *)          = -1
     * StringUtil.indexOf(&quot;&quot;, &quot;&quot;, 0)           = 0
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 0)  = 0
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 0)  = 2
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;, 0) = 1
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 3)  = 5
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 9)  = -1
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, -1) = 2
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;&quot;, 2)   = 2
     * StringUtil.indexOf(&quot;abc&quot;, &quot;&quot;, 9)        = 3
     * </pre>
     *
     * @param str       要扫描的字符串
     * @param searchStr 要查找的字符串
     * @param startPos  开始搜索的索引值，如果小于0，则看作0
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOf(String str, String searchStr, int startPos) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }

        // JDK1.3及以下版本的bug：不能正确处理下面的情况
        if ((searchStr.length() == 0) && (startPos >= str.length())) {
            return str.length();
        }

        return str.indexOf(searchStr, startPos);
    }

    /**
     * 在字符串中查找指定字符串，并返回第num个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回 <code>-1</code>。
     *
     * @param num       出现的次数
     * @param str       要扫描的字符串
     * @param searchStr 要查找的字符串
     * @return 第num个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOf(int num, String str, String searchStr) {
        int index = 0;
        for (int i = 0; i < num; i++) {
            index = indexOf(str, searchStr, index + 1);
        }
        return index;
    }

    /**
     * 在字符串中查找指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回 <code>-1</code>。
     * 如果字符集合为<code>null</code>或空，也返回
     * <code>-1</code>。
     *
     * <pre>
     * StringUtil.indexOfAny(null, *)                = -1
     * StringUtil.indexOfAny(&quot;&quot;, *)                  = -1
     * StringUtil.indexOfAny(*, null)                = -1
     * StringUtil.indexOfAny(*, [])                  = -1
     * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;,['z','a']) = 0
     * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;,['b','y']) = 3
     * StringUtil.indexOfAny(&quot;aba&quot;, ['z'])           = -1
     * </pre>
     *
     * @param str         要扫描的字符串
     * @param searchChars 要搜索的字符集合
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOfAny(String str, char[] searchChars) {
        return indexOfAny(str, searchChars, 0);
    }

    public static int indexOfAny(String str, char[] searchChars, int startIndex) {
        if ((str == null) || (str.length() == 0) || (searchChars == null) || (searchChars.length == 0)) {
            return -1;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < str.length(); i++) {
            char ch = str.charAt(i);

            for (char searchChar : searchChars) {
                if (searchChar == ch) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 在字符串中查找指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回 <code>-1</code>。
     * 如果字符集合为<code>null</code>或空，也返回
     * <code>-1</code>。
     *
     * <pre>
     * StringUtil.indexOfAny(null, *)            = -1
     * StringUtil.indexOfAny(&quot;&quot;, *)              = -1
     * StringUtil.indexOfAny(*, null)            = -1
     * StringUtil.indexOfAny(*, &quot;&quot;)              = -1
     * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, &quot;za&quot;) = 0
     * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, &quot;by&quot;) = 3
     * StringUtil.indexOfAny(&quot;aba&quot;,&quot;z&quot;)          = -1
     * </pre>
     *
     * @param str         要扫描的字符串
     * @param searchChars 要搜索的字符集合
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOfAny(String str, String searchChars) {
        return indexOfAny(str, searchChars, 0);
    }

    public static int indexOfAny(String str, String searchChars, int startIndex) {
        if ((str == null) || (str.length() == 0) || (searchChars == null) || (searchChars.length() == 0)) {
            return -1;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        for (int i = startIndex; i < str.length(); i++) {
            char ch = str.charAt(i);

            for (int j = 0; j < searchChars.length(); j++) {
                if (searchChars.charAt(j) == ch) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 在字符串中查找指定字符串集合中的字符串，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
     * <code>-1</code>。 如果字符串集合为<code>null</code>或空，也返回
     * <code>-1</code>。 如果字符串集合包括<code>""</code>，并且字符串不为<code>null</code>，则返回
     * <code>str.length()</code>
     *
     * <pre>
     * StringUtil.indexOfAny(null, *)                     = -1
     * StringUtil.indexOfAny(*, null)                     = -1
     * StringUtil.indexOfAny(*, [])                       = -1
     * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;ab&quot;,&quot;cd&quot;])   = 2
     * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;cd&quot;,&quot;ab&quot;])   = 2
     * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;op&quot;])   = -1
     * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;zab&quot;,&quot;aby&quot;]) = 1
     * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;&quot;])          = 0
     * StringUtil.indexOfAny(&quot;&quot;, [&quot;&quot;])                    = 0
     * StringUtil.indexOfAny(&quot;&quot;, [&quot;a&quot;])                   = -1
     * </pre>
     *
     * @param str        要扫描的字符串
     * @param searchStrs 要搜索的字符串集合
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOfAny(String str, String[] searchStrs) {
        return indexOfAny(str, searchStrs, 0);
    }

    public static int indexOfAny(String str, String[] searchStrs, int startIndex) {
        if ((str == null) || (searchStrs == null)) {
            return -1;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        int sz = searchStrs.length;

        // String's can't have a MAX_VALUEth index.
        int ret = Integer.MAX_VALUE;

        int tmp = 0;

        for (String search : searchStrs) {
            if (search == null) {
                continue;
            }

            tmp = str.indexOf(search);

            if (tmp == -1) {
                continue;
            }

            if (tmp < ret) {
                ret = tmp;
            }
        }

        return (ret == Integer.MAX_VALUE) ? (-1) : ret;
    }

    /**
     * 在字符串中查找不在指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
     * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回
     * <code>-1</code>。
     *
     * <pre>
     * StringUtil.indexOfAnyBut(null, *)             = -1
     * StringUtil.indexOfAnyBut(&quot;&quot;, *)               = -1
     * StringUtil.indexOfAnyBut(*, null)             = -1
     * StringUtil.indexOfAnyBut(*, [])               = -1
     * StringUtil.indexOfAnyBut(&quot;zzabyycdxx&quot;,'za')   = 3
     * StringUtil.indexOfAnyBut(&quot;zzabyycdxx&quot;, 'by')  = 0
     * StringUtil.indexOfAnyBut(&quot;aba&quot;, 'ab')         = -1
     * </pre>
     *
     * @param str         要扫描的字符串
     * @param searchChars 要搜索的字符集合
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOfAnyBut(String str, char[] searchChars) {
        if ((str == null) || (str.length() == 0) || (searchChars == null) || (searchChars.length == 0)) {
            return -1;
        }

        outer: for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            for (char searchChar : searchChars) {
                if (searchChar == ch) {
                    continue outer;
                }
            }

            return i;
        }

        return -1;
    }

    /**
     * 在字符串中查找不在指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
     * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回
     * <code>-1</code>。
     *
     * <pre>
     * StringUtil.indexOfAnyBut(null, *)            = -1
     * StringUtil.indexOfAnyBut(&quot;&quot;, *)              = -1
     * StringUtil.indexOfAnyBut(*, null)            = -1
     * StringUtil.indexOfAnyBut(*, &quot;&quot;)              = -1
     * StringUtil.indexOfAnyBut(&quot;zzabyycdxx&quot;, &quot;za&quot;) = 3
     * StringUtil.indexOfAnyBut(&quot;zzabyycdxx&quot;, &quot;by&quot;) = 0
     * StringUtil.indexOfAnyBut(&quot;aba&quot;,&quot;ab&quot;)         = -1
     * </pre>
     *
     * @param str         要扫描的字符串
     * @param searchChars 要搜索的字符集合
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOfAnyBut(String str, String searchChars) {
        if ((str == null) || (str.length() == 0) || (searchChars == null) || (searchChars.length() == 0)) {
            return -1;
        }

        for (int i = 0; i < str.length(); i++) {
            if (searchChars.indexOf(str.charAt(i)) < 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 从字符串尾部开始查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回 <code>-1</code>。
     *
     * <pre>
     * StringUtil.lastIndexOf(null, *)         = -1
     * StringUtil.lastIndexOf(&quot;&quot;, *)           = -1
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'a') = 7
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b') = 5
     * </pre>
     *
     * @param str        要扫描的字符串
     * @param searchChar 要查找的字符
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int lastIndexOf(String str, char searchChar) {
        if ((str == null) || (str.length() == 0)) {
            return -1;
        }

        return str.lastIndexOf(searchChar);
    }

    /**
     * 从字符串尾部开始查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回 <code>-1</code>。
     *
     * <pre>
     * StringUtil.lastIndexOf(null, *, *)          = -1
     * StringUtil.lastIndexOf(&quot;&quot;, *,  *)           = -1
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', 8)  = 5
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', 4)  = 2
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', 0)  = -1
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', 9)  = 5
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', -1) = -1
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'a', 0)  = 0
     * </pre>
     *
     * @param str        要扫描的字符串
     * @param searchChar 要查找的字符
     * @param startPos   从指定索引开始向前搜索
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int lastIndexOf(String str, char searchChar, int startPos) {
        if ((str == null) || (str.length() == 0)) {
            return -1;
        }

        return str.lastIndexOf(searchChar, startPos);
    }

    /**
     * 从字符串尾部开始查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回 <code>-1</code>。
     *
     * <pre>
     * StringUtil.lastIndexOf(null, *)         = -1
     * StringUtil.lastIndexOf(&quot;&quot;, *)           = -1
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'a') = 7
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b') = 5
     * </pre>
     *
     * @param str       要扫描的字符串
     * @param searchStr 要查找的字符串
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int lastIndexOf(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }

        return str.lastIndexOf(searchStr);
    }

    /**
     * 从字符串尾部开始查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回 <code>-1</code>。
     *
     * <pre>
     * StringUtil.lastIndexOf(null, *, *)          = -1
     * StringUtil.lastIndexOf(*, null, *)          = -1
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 8)  = 7
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 8)  = 5
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;ab&quot;, 8) = 4
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 9)  = 5
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, -1) = -1
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 0)  = 0
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 0)  = -1
     * </pre>
     *
     * @param str       要扫描的字符串
     * @param searchStr 要查找的字符串
     * @param startPos  从指定索引开始向前搜索
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int lastIndexOf(String str, String searchStr, int startPos) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }

        return str.lastIndexOf(searchStr, startPos);
    }

    /**
     * 从字符串尾部开始查找指定字符串集合中的字符串，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
     * <code>-1</code>。 如果字符串集合为<code>null</code>
     * 或空，也返回<code>-1</code>。 如果字符串集合包括<code>""</code>，并且字符串不为<code>null</code>，则返回
     * <code>str.length()</code>
     *
     * <pre>
     * StringUtil.lastIndexOfAny(null, *)                   = -1
     * StringUtil.lastIndexOfAny(*, null)                   = -1
     * StringUtil.lastIndexOfAny(*, [])                     = -1
     * StringUtil.lastIndexOfAny(*, [null])                 = -1
     * StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;ab&quot;,&quot;cd&quot;]) = 6
     * StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;cd&quot;,&quot;ab&quot;]) = 6
     * StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;op&quot;]) = -1
     * StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;op&quot;]) = -1
     * StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;&quot;])   = 10
     * </pre>
     *
     * @param str        要扫描的字符串
     * @param searchStrs 要搜索的字符串集合
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int lastIndexOfAny(String str, String[] searchStrs) {
        if ((str == null) || (searchStrs == null)) {
            return -1;
        }

        int searchStrsLength = searchStrs.length;
        int index = -1;
        int tmp = 0;

        for (String search : searchStrs) {
            if (search == null) {
                continue;
            }

            tmp = str.lastIndexOf(search);

            if (tmp > index) {
                index = tmp;
            }
        }

        return index;
    }

    /**
     * 检查字符串中是否包含指定的字符。如果字符串为<code>null</code>，将返回<code>false</code>。
     *
     * <pre>
     * StringUtil.contains(null, *)    = false
     * StringUtil.contains(&quot;&quot;, *)      = false
     * StringUtil.contains(&quot;abc&quot;, 'a') = true
     * StringUtil.contains(&quot;abc&quot;, 'z') = false
     * </pre>
     *
     * @param str        要扫描的字符串
     * @param searchChar 要查找的字符
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean contains(String str, char searchChar) {
        if ((str == null) || (str.length() == 0)) {
            return false;
        }

        return str.indexOf(searchChar) >= 0;
    }

    /**
     * 检查字符串中是否包含指定的字符串。如果字符串为<code>null</code>，将返回<code>false</code>。
     *
     * <pre>
     * StringUtil.contains(null, *)     = false
     * StringUtil.contains(*, null)     = false
     * StringUtil.contains(&quot;&quot;, &quot;&quot;)      = true
     * StringUtil.contains(&quot;abc&quot;, &quot;&quot;)   = true
     * StringUtil.contains(&quot;abc&quot;, &quot;a&quot;)  = true
     * StringUtil.contains(&quot;abc&quot;, &quot;z&quot;)  = false
     * </pre>
     *
     * @param str       要扫描的字符串
     * @param searchStr 要查找的字符串
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean contains(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return false;
        }

        return str.contains(searchStr);
    }

    /**
     * 检查字符串是是否只包含指定字符集合中的字符。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>
     * 则返回<code>false</code>。 但是空字符串永远返回
     * <code>true</code>.
     * </div>
     *
     * <pre>
     * StringUtil.containsOnly(null, *)       = false
     * StringUtil.containsOnly(*, null)       = false
     * StringUtil.containsOnly(&quot;&quot;, *)         = true
     * StringUtil.containsOnly(&quot;ab&quot;, '')      = false
     * StringUtil.containsOnly(&quot;abab&quot;, 'abc') = true
     * StringUtil.containsOnly(&quot;ab1&quot;, 'abc')  = false
     * StringUtil.containsOnly(&quot;abz&quot;, 'abc')  = false
     * </pre>
     *
     * @param str   要扫描的字符串
     * @param valid 要查找的字符串
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean containsOnly(String str, char[] valid) {
        if ((valid == null) || (str == null)) {
            return false;
        }

        if (str.length() == 0) {
            return true;
        }

        if (valid.length == 0) {
            return false;
        }

        return indexOfAnyBut(str, valid) == -1;
    }

    /**
     * 检查字符串是是否只包含指定字符集合中的字符。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>
     * 则返回<code>false</code>。 但是空字符串永远返回
     * <code>true</code>.
     * </div>
     *
     * <pre>
     * StringUtil.containsOnly(null, *)       = false
     * StringUtil.containsOnly(*, null)       = false
     * StringUtil.containsOnly(&quot;&quot;, *)         = true
     * StringUtil.containsOnly(&quot;ab&quot;, &quot;&quot;)      = false
     * StringUtil.containsOnly(&quot;abab&quot;, &quot;abc&quot;) = true
     * StringUtil.containsOnly(&quot;ab1&quot;, &quot;abc&quot;)  = false
     * StringUtil.containsOnly(&quot;abz&quot;, &quot;abc&quot;)  = false
     * </pre>
     *
     * @param str   要扫描的字符串
     * @param valid 要查找的字符串
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean containsOnly(String str, String valid) {
        if ((str == null) || (valid == null)) {
            return false;
        }

        return containsOnly(str, valid.toCharArray());
    }

    /**
     * 检查字符串是是否不包含指定字符集合中的字符。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>
     * 则返回<code>true</code>。 但是空字符串永远返回
     * <code>true</code>.
     * </div>
     *
     * <pre>
     * StringUtil.containsNone(null, *)       = true
     * StringUtil.containsNone(*, null)       = true
     * StringUtil.containsNone(&quot;&quot;, *)         = true
     * StringUtil.containsNone(&quot;ab&quot;, '')      = true
     * StringUtil.containsNone(&quot;abab&quot;, 'xyz') = true
     * StringUtil.containsNone(&quot;ab1&quot;, 'xyz')  = true
     * StringUtil.containsNone(&quot;abz&quot;, 'xyz')  = false
     * </pre>
     *
     * @param str     要扫描的字符串
     * @param invalid 要查找的字符串
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean containsNone(String str, char[] invalid) {
        if ((str == null) || (invalid == null)) {
            return true;
        }

        int strSize = str.length();

        for (int i = 0; i < strSize; i++) {
            char ch = str.charAt(i);

            for (char c : invalid) {
                if (c == ch) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 检查字符串是是否不包含指定字符集合中的字符。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>
     * 则返回<code>true</code>。 但是空字符串永远返回
     * <code>true</code>.
     * </div>
     *
     * <pre>
     * StringUtil.containsNone(null, *)       = true
     * StringUtil.containsNone(*, null)       = true
     * StringUtil.containsNone(&quot;&quot;, *)         = true
     * StringUtil.containsNone(&quot;ab&quot;, &quot;&quot;)      = true
     * StringUtil.containsNone(&quot;abab&quot;, &quot;xyz&quot;) = true
     * StringUtil.containsNone(&quot;ab1&quot;, &quot;xyz&quot;)  = true
     * StringUtil.containsNone(&quot;abz&quot;, &quot;xyz&quot;)  = false
     * </pre>
     *
     * @param str          要扫描的字符串
     * @param invalidChars 要查找的字符串
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean containsNone(String str, String invalidChars) {
        if ((str == null) || (invalidChars == null)) {
            return true;
        }

        return containsNone(str, invalidChars.toCharArray());
    }

    /**
     * 取得指定子串在字符串中出现的次数。
     *
     * <div>
     * 如果字符串为<code>null</code>或空，则返回<code>0</code>。
     *
     * <pre>
     * StringUtil.countMatches(null, *)       = 0
     * StringUtil.countMatches(&quot;&quot;, *)         = 0
     * StringUtil.countMatches(&quot;abba&quot;, null)  = 0
     * StringUtil.countMatches(&quot;abba&quot;, &quot;&quot;)    = 0
     * StringUtil.countMatches(&quot;abba&quot;, &quot;a&quot;)   = 2
     * StringUtil.countMatches(&quot;abba&quot;, &quot;ab&quot;)  = 1
     * StringUtil.countMatches(&quot;abba&quot;, &quot;xxx&quot;) = 0
     * </pre>
     *
     * </div>
     *
     * @param str    要扫描的字符串
     * @param subStr 子字符串
     * @return 子串在字符串中出现的次数，如果字符串为<code>null</code>或空，则返回<code>0</code>
     */
    public static int countMatches(String str, String subStr) {
        if ((str == null) || (str.length() == 0) || (subStr == null) || (subStr.length() == 0)) {
            return 0;
        }

        int count = 0;
        int index = 0;

        while ((index = str.indexOf(subStr, index)) != -1) {
            count++;
            index += subStr.length();
        }

        return count;
    }

    /*
     * ========================================================================== ==
     */
    /* 取子串函数。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 取指定字符串的子串。
     *
     * <div>
     * 负的索引代表从尾部开始计算。如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.substring(null, *)   = null
     * StringUtil.substring(&quot;&quot;, *)     = &quot;&quot;
     * StringUtil.substring(&quot;abc&quot;, 0)  = &quot;abc&quot;
     * StringUtil.substring(&quot;abc&quot;, 2)  = &quot;c&quot;
     * StringUtil.substring(&quot;abc&quot;, 4)  = &quot;&quot;
     * StringUtil.substring(&quot;abc&quot;, -2) = &quot;bc&quot;
     * StringUtil.substring(&quot;abc&quot;, -4) = &quot;abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str   字符串
     * @param start 起始索引，如果为负数，表示从尾部查找
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substring(String str, int start) {
        if (str == null) {
            return null;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }

        if (start > str.length()) {
            return EmptyPool.EMPTY_STRING;
        }

        return str.substring(start);
    }

    /**
     * 取指定字符串的子串。
     *
     * <div>
     * 负的索引代表从尾部开始计算。如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.substring(null, *, *)    = null
     * StringUtil.substring(&quot;&quot;, * ,  *)    = &quot;&quot;;
     * StringUtil.substring(&quot;abc&quot;, 0, 2)   = &quot;ab&quot;
     * StringUtil.substring(&quot;abc&quot;, 2, 0)   = &quot;&quot;
     * StringUtil.substring(&quot;abc&quot;, 2, 4)   = &quot;c&quot;
     * StringUtil.substring(&quot;abc&quot;, 4, 6)   = &quot;&quot;
     * StringUtil.substring(&quot;abc&quot;, 2, 2)   = &quot;&quot;
     * StringUtil.substring(&quot;abc&quot;, -2, -1) = &quot;b&quot;
     * StringUtil.substring(&quot;abc&quot;, -4, 2)  = &quot;ab&quot;
     * </pre>
     *
     * </div>
     *
     * @param str   字符串
     * @param start 起始索引，如果为负数，表示从尾部计算
     * @param end   结束索引（不含），如果为负数，表示从尾部计算
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        if (end < 0) {
            end = str.length() + end;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return EmptyPool.EMPTY_STRING;
        }

        if (start < 0) {
            start = 0;
        }

        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 取得长度为指定字符数的最左边的子串。
     *
     * <pre>
     * StringUtil.left(null, *)    = null
     * StringUtil.left(*, -ve)     = &quot;&quot;
     * StringUtil.left(&quot;&quot;, *)      = &quot;&quot;
     * StringUtil.left(&quot;abc&quot;, 0)   = &quot;&quot;
     * StringUtil.left(&quot;abc&quot;, 2)   = &quot;ab&quot;
     * StringUtil.left(&quot;abc&quot;, 4)   = &quot;abc&quot;
     * </pre>
     *
     * @param str 字符串
     * @param len 最左子串的长度
     * @return 子串，如果原始字串为<code>null</code>，则返回<code>null</code>
     */
    public static String left(String str, int len) {
        if (str == null) {
            return null;
        }

        if (len < 0) {
            return EmptyPool.EMPTY_STRING;
        }

        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(0, len);
        }
    }

    /**
     * 取得长度为指定字符数的最右边的子串。
     *
     * <pre>
     * StringUtil.right(null, *)    = null
     * StringUtil.right(*, -ve)     = &quot;&quot;
     * StringUtil.right(&quot;&quot;, *)      = &quot;&quot;
     * StringUtil.right(&quot;abc&quot;, 0)   = &quot;&quot;
     * StringUtil.right(&quot;abc&quot;, 2)   = &quot;bc&quot;
     * StringUtil.right(&quot;abc&quot;, 4)   = &quot;abc&quot;
     * </pre>
     *
     * @param str 字符串
     * @param len 最右子串的长度
     * @return 子串，如果原始字串为<code>null</code>，则返回<code>null</code>
     */
    public static String right(String str, int len) {
        if (str == null) {
            return null;
        }

        if (len < 0) {
            return EmptyPool.EMPTY_STRING;
        }

        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(str.length() - len);
        }
    }

    /**
     * 取得从指定索引开始计算的、长度为指定字符数的子串。
     *
     * <pre>
     * StringUtil.mid(null, *, *)    = null
     * StringUtil.mid(*, *, -ve)     = &quot;&quot;
     * StringUtil.mid(&quot;&quot;, 0, *)      = &quot;&quot;
     * StringUtil.mid(&quot;abc&quot;, 0, 2)   = &quot;ab&quot;
     * StringUtil.mid(&quot;abc&quot;, 0, 4)   = &quot;abc&quot;
     * StringUtil.mid(&quot;abc&quot;, 2, 4)   = &quot;c&quot;
     * StringUtil.mid(&quot;abc&quot;, 4, 2)   = &quot;&quot;
     * StringUtil.mid(&quot;abc&quot;, -2, 2)  = &quot;ab&quot;
     * </pre>
     *
     * @param str 字符串
     * @param pos 起始索引，如果为负数，则看作<code>0</code>
     * @param len 子串的长度，如果为负数，则看作长度为<code>0</code>
     * @return 子串，如果原始字串为<code>null</code>，则返回<code>null</code>
     */
    public static String mid(String str, int pos, int len) {
        if (str == null) {
            return null;
        }

        if ((len < 0) || (pos > str.length())) {
            return EmptyPool.EMPTY_STRING;
        }

        if (pos < 0) {
            pos = 0;
        }

        if (str.length() <= (pos + len)) {
            return str.substring(pos);
        } else {
            return str.substring(pos, pos + len);
        }
    }

    /*
     * ========================================================================== ==
     */
    /* 搜索并取子串函数。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 取得第一个出现的分隔子串之前的子串。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
     * 或未找到该子串，则返回原字符串。
     *
     * <pre>
     * StringUtil.substringBefore(null, *)      = null
     * StringUtil.substringBefore(&quot;&quot;, *)        = &quot;&quot;
     * StringUtil.substringBefore(&quot;abc&quot;, &quot;a&quot;)   = &quot;&quot;
     * StringUtil.substringBefore(&quot;abcba&quot;, &quot;b&quot;) = &quot;a&quot;
     * StringUtil.substringBefore(&quot;abc&quot;, &quot;c&quot;)   = &quot;ab&quot;
     * StringUtil.substringBefore(&quot;abc&quot;, &quot;d&quot;)   = &quot;abc&quot;
     * StringUtil.substringBefore(&quot;abc&quot;, &quot;&quot;)    = &quot;&quot;
     * StringUtil.substringBefore(&quot;abc&quot;, null)  = &quot;abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str       字符串
     * @param separator 要搜索的分隔子串
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substringBefore(String str, String separator) {
        if ((str == null) || (separator == null) || (str.length() == 0)) {
            return str;
        }

        if (separator.length() == 0) {
            return EmptyPool.EMPTY_STRING;
        }

        int pos = str.indexOf(separator);

        if (pos == -1) {
            return str;
        }

        return str.substring(0, pos);
    }

    /**
     * 取得第一个出现的分隔子串之后的子串。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
     * 或未找到该子串，则返回原字符串。
     *
     * <pre>
     * StringUtil.substringAfter(null, *)      = null
     * StringUtil.substringAfter(&quot;&quot;, *)        = &quot;&quot;
     * StringUtil.substringAfter(*, null)      = &quot;&quot;
     * StringUtil.substringAfter(&quot;abc&quot;, &quot;a&quot;)   = &quot;bc&quot;
     * StringUtil.substringAfter(&quot;abcba&quot;, &quot;b&quot;) = &quot;cba&quot;
     * StringUtil.substringAfter(&quot;abc&quot;, &quot;c&quot;)   = &quot;&quot;
     * StringUtil.substringAfter(&quot;abc&quot;, &quot;d&quot;)   = &quot;&quot;
     * StringUtil.substringAfter(&quot;abc&quot;, &quot;&quot;)    = &quot;abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str       字符串
     * @param separator 要搜索的分隔子串
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substringAfter(String str, String separator) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }

        if (separator == null) {
            return EmptyPool.EMPTY_STRING;
        }

        int pos = str.indexOf(separator);

        if (pos == -1) {
            return EmptyPool.EMPTY_STRING;
        }

        return str.substring(pos + separator.length());
    }

    /**
     * 取得最后一个的分隔子串之前的子串。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
     * 或未找到该子串，则返回原字符串。
     *
     * <pre>
     * StringUtil.substringBeforeLast(null, *)      = null
     * StringUtil.substringBeforeLast(&quot;&quot;, *)        = &quot;&quot;
     * StringUtil.substringBeforeLast(&quot;abcba&quot;, &quot;b&quot;) = &quot;abc&quot;
     * StringUtil.substringBeforeLast(&quot;abc&quot;, &quot;c&quot;)   = &quot;ab&quot;
     * StringUtil.substringBeforeLast(&quot;a&quot;, &quot;a&quot;)     = &quot;&quot;
     * StringUtil.substringBeforeLast(&quot;a&quot;, &quot;z&quot;)     = &quot;a&quot;
     * StringUtil.substringBeforeLast(&quot;a&quot;, null)    = &quot;a&quot;
     * StringUtil.substringBeforeLast(&quot;a&quot;, &quot;&quot;)      = &quot;a&quot;
     * </pre>
     *
     * </div>
     *
     * @param str       字符串
     * @param separator 要搜索的分隔子串
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substringBeforeLast(String str, String separator) {
        if ((str == null) || (separator == null) || (str.length() == 0) || (separator.length() == 0)) {
            return str;
        }

        int pos = str.lastIndexOf(separator);

        if (pos == -1) {
            return str;
        }

        return str.substring(0, pos);
    }

    /**
     * 取得最后一个的分隔子串之后的子串。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
     * 或未找到该子串，则返回原字符串。
     *
     * <pre>
     * StringUtil.substringAfterLast(null, *)      = null
     * StringUtil.substringAfterLast(&quot;&quot;, *)        = &quot;&quot;
     * StringUtil.substringAfterLast(*, &quot;&quot;)        = &quot;&quot;
     * StringUtil.substringAfterLast(*, null)      = &quot;&quot;
     * StringUtil.substringAfterLast(&quot;abc&quot;, &quot;a&quot;)   = &quot;bc&quot;
     * StringUtil.substringAfterLast(&quot;abcba&quot;, &quot;b&quot;) = &quot;a&quot;
     * StringUtil.substringAfterLast(&quot;abc&quot;, &quot;c&quot;)   = &quot;&quot;
     * StringUtil.substringAfterLast(&quot;a&quot;, &quot;a&quot;)     = &quot;&quot;
     * StringUtil.substringAfterLast(&quot;a&quot;, &quot;z&quot;)     = &quot;&quot;
     * </pre>
     *
     * </div>
     *
     * @param str       字符串
     * @param separator 要搜索的分隔子串
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substringAfterLast(String str, String separator) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }

        if ((separator == null) || (separator.length() == 0)) {
            return EmptyPool.EMPTY_STRING;
        }

        int pos = str.lastIndexOf(separator);

        if ((pos == -1) || (pos == (str.length() - separator.length()))) {
            return EmptyPool.EMPTY_STRING;
        }

        return str.substring(pos + separator.length());
    }

    /**
     * 取得指定分隔符的前两次出现之间的子串。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
     * ，则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.substringBetween(null, *)            = null
     * StringUtil.substringBetween(&quot;&quot;, &quot;&quot;)             = &quot;&quot;
     * StringUtil.substringBetween(&quot;&quot;, &quot;tag&quot;)          = null
     * StringUtil.substringBetween(&quot;tagabctag&quot;, null)  = null
     * StringUtil.substringBetween(&quot;tagabctag&quot;, &quot;&quot;)    = &quot;&quot;
     * StringUtil.substringBetween(&quot;tagabctag&quot;, &quot;tag&quot;) = &quot;abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str 字符串
     * @param tag 要搜索的分隔子串
     * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
     */
    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag, 0);
    }

    /**
     * 取得两个分隔符之间的子串。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
     * ，则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.substringBetween(null, *, *)          = null
     * StringUtil.substringBetween(&quot;&quot;, &quot;&quot;, &quot;&quot;)          = &quot;&quot;
     * StringUtil.substringBetween(&quot;&quot;, &quot;&quot;, &quot;tag&quot;)       = null
     * StringUtil.substringBetween(&quot;&quot;, &quot;tag&quot;, &quot;tag&quot;)    = null
     * StringUtil.substringBetween(&quot;yabcz&quot;, null, null) = null
     * StringUtil.substringBetween(&quot;yabcz&quot;, &quot;&quot;, &quot;&quot;)     = &quot;&quot;
     * StringUtil.substringBetween(&quot;yabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
     * StringUtil.substringBetween(&quot;yabczyabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str   字符串
     * @param open  要搜索的分隔子串1
     * @param close 要搜索的分隔子串2
     * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
     */
    public static String substringBetween(String str, String open, String close) {
        return substringBetween(str, open, close, 0);
    }

    /**
     * 取得两个分隔符之间的子串。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
     * ，则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.substringBetween(null, *, *)          = null
     * StringUtil.substringBetween(&quot;&quot;, &quot;&quot;, &quot;&quot;)          = &quot;&quot;
     * StringUtil.substringBetween(&quot;&quot;, &quot;&quot;, &quot;tag&quot;)       = null
     * StringUtil.substringBetween(&quot;&quot;, &quot;tag&quot;, &quot;tag&quot;)    = null
     * StringUtil.substringBetween(&quot;yabcz&quot;, null, null) = null
     * StringUtil.substringBetween(&quot;yabcz&quot;, &quot;&quot;, &quot;&quot;)     = &quot;&quot;
     * StringUtil.substringBetween(&quot;yabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
     * StringUtil.substringBetween(&quot;yabczyabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
     * </pre>
     *
     * </div>
     *
     * @param str       字符串
     * @param open      要搜索的分隔子串1
     * @param close     要搜索的分隔子串2
     * @param fromIndex 从指定index处搜索
     * @return 子串。如果原始串为<code>null</code>则返回<code>null</code>，如果未找到分隔子串则返回<code>str</code>自身
     */
    public static String substringBetween(String str, String open, String close, int fromIndex) {
        if ((str == null) || (open == null) || (close == null)) {
            return null;
        }

        int start = str.indexOf(open, fromIndex);

        if (start != -1) {
            int end = str.indexOf(close, start + open.length());

            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }

        return str;
    }
    /*
     * ========================================================================== ==
     */
    /* 删除字符。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 删除所有在<code>Character.isWhitespace(char)</code>中所定义的空白。
     *
     * <pre>
     * StringUtil.deleteWhitespace(null)         = null
     * StringUtil.deleteWhitespace(&quot;&quot;)           = &quot;&quot;
     * StringUtil.deleteWhitespace(&quot;abc&quot;)        = &quot;abc&quot;
     * StringUtil.deleteWhitespace(&quot;   ab  c  &quot;) = &quot;abc&quot;
     * </pre>
     *
     * @param str 要处理的字符串
     * @return 去空白后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String deleteWhitespace(String str) {
        if (str == null) {
            return null;
        }

        int sz = str.length();
        StringBuilder builder = new StringBuilder(sz);

        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                builder.append(str.charAt(i));
            }
        }

        return builder.toString();
    }

    /*
     * ========================================================================== ==
     */
    /* 替换子串。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 替换指定的子串，只替换第一个出现的子串。
     *
     * <div>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
     * ，则返回原字符串。
     *
     * <pre>
     * StringUtil.replaceOnce(null, *, *)        = null
     * StringUtil.replaceOnce(&quot;&quot;, *, *)          = &quot;&quot;
     * StringUtil.replaceOnce(&quot;aba&quot;, null, null) = &quot;aba&quot;
     * StringUtil.replaceOnce(&quot;aba&quot;, null, null) = &quot;aba&quot;
     * StringUtil.replaceOnce(&quot;aba&quot;, &quot;a&quot;, null)  = &quot;aba&quot;
     * StringUtil.replaceOnce(&quot;aba&quot;, &quot;a&quot;, &quot;&quot;)    = &quot;ba&quot;
     * StringUtil.replaceOnce(&quot;aba&quot;, &quot;a&quot;, &quot;z&quot;)   = &quot;zba&quot;
     * </pre>
     *
     * </div>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceOnce(String text, String repl, String with) {
        return replace(text, repl, with, 1);
    }

    /**
     * Replaces the very first occurrence of a character in a string.
     *
     * @param str         string
     * @param searchChar  char to replace
     * @param replaceChar char to replace with
     */
    public static String replaceOnce(String str, char searchChar, char replaceChar) {
        if (str == null) {
            return null;
        }
        int index = str.indexOf(searchChar);
        if (index == -1) {
            return str;
        }
        char[] ch = str.toCharArray();
        ch[index] = replaceChar;
        return new String(ch);
    }

    /**
     * 替换指定的子串，替换所有出现的子串。
     *
     * <div>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
     * ，则返回原字符串。
     *
     * <pre>
     * StringUtil.replace(null, *, *)        = null
     * StringUtil.replace(&quot;&quot;, *, *)          = &quot;&quot;
     * StringUtil.replace(&quot;aba&quot;, null, null) = &quot;aba&quot;
     * StringUtil.replace(&quot;aba&quot;, null, null) = &quot;aba&quot;
     * StringUtil.replace(&quot;aba&quot;, &quot;a&quot;, null)  = &quot;aba&quot;
     * StringUtil.replace(&quot;aba&quot;, &quot;a&quot;, &quot;&quot;)    = &quot;b&quot;
     * StringUtil.replace(&quot;aba&quot;, &quot;a&quot;, &quot;z&quot;)   = &quot;zbz&quot;
     * </pre>
     *
     * </div>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    /**
     * 替换指定的子串，替换指定的次数。
     *
     * <div>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
     * ，则返回原字符串。
     *
     * <pre>
     * StringUtil.replace(null, *, *, *)         = null
     * StringUtil.replace(&quot;&quot;, *, *, *)           = &quot;&quot;
     * StringUtil.replace(&quot;abaa&quot;, null, null, 1) = &quot;abaa&quot;
     * StringUtil.replace(&quot;abaa&quot;, null, null, 1) = &quot;abaa&quot;
     * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, null, 1)  = &quot;abaa&quot;
     * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;&quot;, 1)    = &quot;baa&quot;
     * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 0)   = &quot;abaa&quot;
     * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 1)   = &quot;zbaa&quot;
     * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 2)   = &quot;zbza&quot;
     * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, -1)  = &quot;zbzz&quot;
     * </pre>
     *
     * </div>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     * @param max  maximum number of values to replace, or <code>-1</code> if no
     *             maximum
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with, int max) {
        if ((text == null) || (repl == null) || (with == null) || (repl.length() == 0) || (max == 0)) {
            return text;
        }

        StringBuilder buf = new StringBuilder(text.length());
        int start = 0;
        int end = 0;

        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            if (--max == 0) {
                break;
            }
        }

        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * 替换指定的子串，只替换第一个出现的子串。
     *
     * @param startPos 开始搜索的索引值，如果小于0，则看作0
     * @param text     要扫描的字符串
     * @param repl     要搜索的子串
     * @param with     替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceOnce(int startPos, String text, String repl, String with) {
        String target = StringUtil.substring(text, startPos);
        String before = StringUtil.substring(text, 0, startPos);
        String after = StringUtil.replaceOnce(target, repl, with);
        return (before + after);
    }

    /**
     * 将字符串中所有指定的字符，替换成另一个。
     *
     * <div>
     * 如果字符串为<code>null</code>则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.replaceChars(null, *, *)        = null
     * StringUtil.replaceChars(&quot;&quot;, *, *)          = &quot;&quot;
     * StringUtil.replaceChars(&quot;abcba&quot;, 'b', 'y') = &quot;aycya&quot;
     * StringUtil.replaceChars(&quot;abcba&quot;, 'z', 'y') = &quot;abcba&quot;
     * </pre>
     *
     * </div>
     *
     * @param str         要扫描的字符串
     * @param searchChar  要搜索的字符
     * @param replaceChar 替换字符
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceChars(String str, char searchChar, char replaceChar) {
        if (str == null) {
            return null;
        }

        return str.replace(searchChar, replaceChar);
    }

    /**
     * Replaces all occurrences of a characters in a string.
     *
     * @param s    input string
     * @param sub  characters to replace
     * @param with characters to replace with
     */
    public static String replaceChars(String s, char[] sub, char[] with) {
        if (s == null || sub == null || with == null) {
            return s;
        }

        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            for (int j = 0; j < sub.length; j++) {
                if (c == sub[j]) {
                    str[i] = with[j];
                    break;
                }
            }
        }
        return new String(str);
    }

    /**
     * 将字符串中所有指定的字符，替换成另一个。
     *
     * <div>
     * 如果字符串为<code>null</code>则返回<code>null</code>。如果搜索字符串为<code>null</code>
     * 或空，则返回原字符串。
     * </div>
     *
     * <div>
     * 例如：
     * <code>replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly</code>
     * 。
     * </div>
     *
     * <div>
     * 通常搜索字符串和替换字符串是等长的，如果搜索字符串比替换字符串长，则多余的字符将被删除。 如果搜索字符串比替换字符串短，则缺少的字符将被忽略。
     *
     * <pre>
     * StringUtil.replaceChars(null, *, *)           = null
     * StringUtil.replaceChars(&quot;&quot;, *, *)             = &quot;&quot;
     * StringUtil.replaceChars(&quot;abc&quot;, null, *)       = &quot;abc&quot;
     * StringUtil.replaceChars(&quot;abc&quot;, &quot;&quot;, *)         = &quot;abc&quot;
     * StringUtil.replaceChars(&quot;abc&quot;, &quot;b&quot;, null)     = &quot;ac&quot;
     * StringUtil.replaceChars(&quot;abc&quot;, &quot;b&quot;, &quot;&quot;)       = &quot;ac&quot;
     * StringUtil.replaceChars(&quot;abcba&quot;, &quot;bc&quot;, &quot;yz&quot;)  = &quot;ayzya&quot;
     * StringUtil.replaceChars(&quot;abcba&quot;, &quot;bc&quot;, &quot;y&quot;)   = &quot;ayya&quot;
     * StringUtil.replaceChars(&quot;abcba&quot;, &quot;bc&quot;, &quot;yzx&quot;) = &quot;ayzya&quot;
     * </pre>
     *
     * </div>
     *
     * @param str          要扫描的字符串
     * @param searchChars  要搜索的字符串
     * @param replaceChars 替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceChars(String str, String searchChars, String replaceChars) {
        if ((str == null) || (str.length() == 0) || (searchChars == null) || (searchChars.length() == 0)) {
            return str;
        }

        char[] chars = str.toCharArray();
        int len = chars.length;
        boolean modified = false;

        for (int i = 0, isize = searchChars.length(); i < isize; i++) {
            char searchChar = searchChars.charAt(i);

            if ((replaceChars == null) || (i >= replaceChars.length())) {
                // 删除
                int pos = 0;

                for (int j = 0; j < len; j++) {
                    if (chars[j] != searchChar) {
                        chars[pos++] = chars[j];
                    } else {
                        modified = true;
                    }
                }

                len = pos;
            } else {
                // 替换
                for (int j = 0; j < len; j++) {
                    if (chars[j] == searchChar) {
                        chars[j] = replaceChars.charAt(i);
                        modified = true;
                    }
                }
            }
        }

        if (!modified) {
            return str;
        }

        return new String(chars, 0, len);
    }

    /**
     * Replaces the very last occurrence of a substring with supplied string.
     *
     * @param text source string
     * @param sub  substring to replace
     * @param with substring to replace with
     */
    public static String replaceLast(String text, String sub, String with) {
        if ((text == null) || (sub == null) || (with == null)) {
            return null;
        }
        int i = text.lastIndexOf(sub);
        if (i == -1) {
            return text;
        }
        return text.substring(0, i) + with + text.substring(i + sub.length());
    }

    /**
     * Replaces the very last occurrence of a character in a string.
     *
     * @param text string
     * @param sub  char to replace
     * @param with char to replace with
     */
    public static String replaceLast(String text, char sub, char with) {
        if (text == null) {
            return null;
        }
        int index = text.lastIndexOf(sub);
        if (index == -1) {
            return text;
        }
        char[] str = text.toCharArray();
        str[index] = with;
        return new String(str);
    }

    /**
     * 将指定的子串用另一指定子串覆盖。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 负的索引值将被看作<code>0</code>
     * ，越界的索引值将被设置成字符串的长度相同的值。
     *
     * <pre>
     * StringUtil.overlay(null, *, *, *)            = null
     * StringUtil.overlay(&quot;&quot;, &quot;abc&quot;, 0, 0)          = &quot;abc&quot;
     * StringUtil.overlay(&quot;abcdef&quot;, null, 2, 4)     = &quot;abef&quot;
     * StringUtil.overlay(&quot;abcdef&quot;, &quot;&quot;, 2, 4)       = &quot;abef&quot;
     * StringUtil.overlay(&quot;abcdef&quot;, &quot;&quot;, 4, 2)       = &quot;abef&quot;
     * StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 2, 4)   = &quot;abzzzzef&quot;
     * StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 4, 2)   = &quot;abzzzzef&quot;
     * StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, -1, 4)  = &quot;zzzzef&quot;
     * StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 2, 8)   = &quot;abzzzz&quot;
     * StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, -2, -3) = &quot;zzzzabcdef&quot;
     * StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 8, 10)  = &quot;abcdefzzzz&quot;
     * </pre>
     *
     * </div>
     *
     * @param str     要扫描的字符串
     * @param overlay 用来覆盖的字符串
     * @param start   起始索引
     * @param end     结束索引
     * @return 被覆盖后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String overlay(String str, String overlay, int start, int end) {
        if (str == null) {
            return null;
        }

        if (overlay == null) {
            overlay = EmptyPool.EMPTY_STRING;
        }

        int len = str.length();

        if (start < 0) {
            start = 0;
        }

        if (start > len) {
            start = len;
        }

        if (end < 0) {
            end = 0;
        }

        if (end > len) {
            end = len;
        }

        if (start > end) {
            int temp = start;

            start = end;
            end = temp;
        }

        return str.substring(0, start) + overlay + str.substring(end);
    }

    /*
     * ========================================================================== ==
     */
    /* 反转字符串。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 反转字符串中的字符顺序。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。
     * </div>
     *
     * <pre>
     * StringUtil.reverse(null)  = null
     * StringUtil.reverse(&quot;&quot;)    = &quot;&quot;
     * StringUtil.reverse(&quot;bat&quot;) = &quot;tab&quot;
     * </pre>
     *
     * @param str 要反转的字符串
     * @return 反转后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String reverse(String str) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }

        return new StringBuilder(str).reverse().toString();
    }

    /**
     * 反转指定分隔符分隔的各子串的顺序。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。
     * </div>
     *
     * <pre>
     * StringUtil.reverseDelimited(null, *)      = null
     * StringUtil.reverseDelimited(&quot;&quot;, *)        = &quot;&quot;
     * StringUtil.reverseDelimited(&quot;a.b.c&quot;, 'x') = &quot;a.b.c&quot;
     * StringUtil.reverseDelimited(&quot;a.b.c&quot;, '.') = &quot;c.b.a&quot;
     * </pre>
     *
     * @param str           要反转的字符串
     * @param separatorChar 分隔符
     * @return 反转后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String reverseDelimited(String str, char separatorChar) {
        if (str == null) {
            return null;
        }

        String[] strs = split(str, separatorChar);

        ArrayUtil.reverse(strs);

        return join(strs, separatorChar);
    }

    /**
     * 反转指定分隔符分隔的各子串的顺序。
     *
     * <div>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。
     * </div>
     *
     * <pre>
     * StringUtil.reverseDelimited(null, *, *)          = null
     * StringUtil.reverseDelimited(&quot;&quot;, *, *)            = &quot;&quot;
     * StringUtil.reverseDelimited(&quot;a.b.c&quot;, null, null) = &quot;a.b.c&quot;
     * StringUtil.reverseDelimited(&quot;a.b.c&quot;, &quot;&quot;, null)   = &quot;a.b.c&quot;
     * StringUtil.reverseDelimited(&quot;a.b.c&quot;, &quot;.&quot;, &quot;,&quot;)   = &quot;c,b,a&quot;
     * StringUtil.reverseDelimited(&quot;a.b.c&quot;, &quot;.&quot;, null)  = &quot;c b a&quot;
     * </pre>
     *
     * @param str            要反转的字符串
     * @param separatorChars 分隔符，如果为<code>null</code>，则默认使用空白字符
     * @param separator      用来连接子串的分隔符，如果为<code>null</code>，默认使用空格
     * @return 反转后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String reverseDelimited(String str, String separatorChars, String separator) {
        if (str == null) {
            return null;
        }

        String[] strs = split(str, separatorChars);

        ArrayUtil.reverse(strs);

        if (separator == null) {
            return join(strs, ' ');
        }

        return join(strs, separator);
    }

    /*
     * ========================================================================== ==
     */
    /* 字符串连接函数。 */
    /*                                                                              */
    /* 将多个对象按指定分隔符连接成字符串。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 将数组中的元素连接成一个字符串。
     *
     * <pre>
     * StringUtil.join(null)            = null
     * StringUtil.join([])              = &quot;&quot;
     * StringUtil.join([null])          = &quot;&quot;
     * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]) = &quot;abc&quot;
     * StringUtil.join([null, &quot;&quot;, &quot;a&quot;]) = &quot;a&quot;
     * </pre>
     *
     * @param array 要连接的数组
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static <T> String join(T... array) {
        return join(array, null);
    }

    /**
     * 将数组中的元素连接成一个字符串。
     *
     * <pre>
     * StringUtil.join(null, *)               = null
     * StringUtil.join([], *)                 = &quot;&quot;
     * StringUtil.join([null], *)             = &quot;&quot;
     * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], ';')  = &quot;a;b;c&quot;
     * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null) = &quot;abc&quot;
     * StringUtil.join([null, &quot;&quot;, &quot;a&quot;], ';')  = &quot;;;a&quot;
     * </pre>
     *
     * @param array     要连接的数组
     * @param separator 分隔符
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static <T> String join(T[] array, char separator) {
        if (array == null) {
            return null;
        }

        int arraySize = array.length;
        int bufSize = (arraySize == 0) ? 0
                : ((((array[0] == null) ? 16 : array[0].toString().length()) + 1) * arraySize);
        StringBuilder buf = new StringBuilder(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(separator);
            }

            if (array[i] != null) {
                buf.append(array[i]);
            }
        }

        return buf.toString();
    }

    /**
     * 将数组中的元素连接成一个字符串。
     *
     * <pre>
     * StringUtil.join(null, *)                = null
     * StringUtil.join([], *)                  = &quot;&quot;
     * StringUtil.join([null], *)              = &quot;&quot;
     * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;--&quot;)  = &quot;a--b--c&quot;
     * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null)  = &quot;abc&quot;
     * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;&quot;)    = &quot;abc&quot;
     * StringUtil.join([null, &quot;&quot;, &quot;a&quot;], ',')   = &quot;,,a&quot;
     * </pre>
     *
     * @param array     要连接的数组
     * @param separator 分隔符
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static <T> String join(T[] array, String separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = EmptyPool.EMPTY_STRING;
        }

        int arraySize = array.length;

        // ArraySize == 0: Len = 0
        // ArraySize > 0: Len = NofStrings *(len(firstString) + len(separator))
        // (估计大约所有的字符串都一样长)
        int bufSize = arraySize == 0 ? 0
                : arraySize * ((array[0] == null ? 16 : array[0].toString().length()) + separator
                        .length());

        StringBuilder buf = new StringBuilder(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(separator);
            }

            if (array[i] != null) {
                buf.append(array[i]);
            }
        }

        return buf.toString();
    }

    /**
     * 将<code>Iterator</code>中的元素连接成一个字符串。
     *
     * <pre>
     * StringUtil.join(null, *)                = null
     * StringUtil.join([], *)                  = &quot;&quot;
     * StringUtil.join([null], *)              = &quot;&quot;
     * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;--&quot;)  = &quot;a--b--c&quot;
     * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null)  = &quot;abc&quot;
     * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;&quot;)    = &quot;abc&quot;
     * StringUtil.join([null, &quot;&quot;, &quot;a&quot;], ',')   = &quot;,,a&quot;
     * </pre>
     *
     * @param iterator  要连接的<code>Iterator</code>
     * @param separator 分隔符
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(Iterator<?> iterator, char separator) {
        if (iterator == null) {
            return null;
        }

        StringBuilder buf = new StringBuilder(256); // Java默认值是16, 可能偏小

        while (iterator.hasNext()) {
            Object obj = iterator.next();

            if (obj != null) {
                buf.append(obj);
            }

            if (iterator.hasNext()) {
                buf.append(separator);
            }
        }

        return buf.toString();
    }

    /**
     * 将<code>Iterator</code>中的元素连接成一个字符串。
     *
     * <pre>
     * StringUtil.join(null, *)                = null
     * StringUtil.join([], *)                  = &quot;&quot;
     * StringUtil.join([null], *)              = &quot;&quot;
     * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;--&quot;)  = &quot;a--b--c&quot;
     * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null)  = &quot;abc&quot;
     * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;&quot;)    = &quot;abc&quot;
     * StringUtil.join([null, &quot;&quot;, &quot;a&quot;], ',')   = &quot;,,a&quot;
     * </pre>
     *
     * @param iterator  要连接的<code>Iterator</code>
     * @param separator 分隔符
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        }

        StringBuilder buf = new StringBuilder(256); // Java默认值是16, 可能偏小

        while (iterator.hasNext()) {
            Object obj = iterator.next();

            if (obj != null) {
                buf.append(obj);
            }

            if ((separator != null) && iterator.hasNext()) {
                buf.append(separator);
            }
        }

        return buf.toString();
    }

    /*
     * ========================================================================== ==
     */
    /* 取得字符串的缩略。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 将字符串转换成指定长度的缩略，例如： 将"Now is the time for all good men"转换成"Now is the time
     * for..."。
     *
     * <ul>
     * <li>
     * 如果<code>str</code>比<code>maxWidth</code>短，直接返回；</li>
     * <li>
     * 否则将它转换成缩略：<code>substring(str, 0, max-3) + "..."</code>；</li>
     * <li>
     * 如果<code>maxWidth</code>小于<code>4</code>抛出
     * <code>IllegalArgumentException</code>；</li>
     * <li>
     * 返回的字符串不可能长于指定的<code>maxWidth</code>。</li>
     * </ul>
     *
     * <pre>
     * StringUtil.abbreviate(null, *)      = null
     * StringUtil.abbreviate(&quot;&quot;, 4)        = &quot;&quot;
     * StringUtil.abbreviate(&quot;abcdefg&quot;, 6) = &quot;abc...&quot;
     * StringUtil.abbreviate(&quot;abcdefg&quot;, 7) = &quot;abcdefg&quot;
     * StringUtil.abbreviate(&quot;abcdefg&quot;, 8) = &quot;abcdefg&quot;
     * StringUtil.abbreviate(&quot;abcdefg&quot;, 4) = &quot;a...&quot;
     * StringUtil.abbreviate(&quot;abcdefg&quot;, 3) = IllegalArgumentException
     * </pre>
     *
     * @param str      要检查的字符串
     * @param maxWidth 最大长度，不小于<code>4</code>，如果小于<code>4</code>，则看作<code>4</code>
     * @return 字符串缩略，如果原始字符串为<code>null</code>则返回<code>null</code>
     */
    public static String abbreviate(String str, int maxWidth) {
        return abbreviate(str, 0, maxWidth);
    }

    /**
     * 将字符串转换成指定长度的缩略，例如： 将"Now is the time for all good men"转换成"...is the time
     * for..."。
     *
     * <div>
     * 和<code>abbreviate(String, int)</code>类似，但是增加了一个“左边界”偏移量。
     * 注意，“左边界”处的字符未必出现在结果字符串的最左边，但一定出现在结果字符串中。
     * </div>
     *
     * <div>
     * 返回的字符串不可能长于指定的<code>maxWidth</code>。
     *
     * <pre>
     * StringUtil.abbreviate(null, *, *)                = null
     * StringUtil.abbreviate(&quot;&quot;, 0, 4)                  = &quot;&quot;
     * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, -1, 10) = &quot;abcdefg...&quot;
     * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 0, 10)  = &quot;abcdefg...&quot;
     * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 1, 10)  = &quot;abcdefg...&quot;
     * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 4, 10)  = &quot;abcdefg...&quot;
     * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 5, 10)  = &quot;...fghi...&quot;
     * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 6, 10)  = &quot;...ghij...&quot;
     * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 8, 10)  = &quot;...ijklmno&quot;
     * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 10, 10) = &quot;...ijklmno&quot;
     * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 12, 10) = &quot;...ijklmno&quot;
     * StringUtil.abbreviate(&quot;abcdefghij&quot;, 0, 3)        = IllegalArgumentException
     * StringUtil.abbreviate(&quot;abcdefghij&quot;, 5, 6)        = IllegalArgumentException
     * </pre>
     *
     * </div>
     *
     * @param str      要检查的字符串
     * @param offset   左边界偏移量
     * @param maxWidth 最大长度，不小于<code>4</code>，如果小于<code>4</code>，则看作<code>4</code>
     * @return 字符串缩略，如果原始字符串为<code>null</code>则返回<code>null</code>
     */
    public static String abbreviate(String str, int offset, int maxWidth) {
        if (str == null) {
            return null;
        }

        // 调整最大宽度
        if (maxWidth < 4) {
            maxWidth = 4;
        }

        if (str.length() <= maxWidth) {
            return str;
        }

        if (offset > str.length()) {
            offset = str.length();
        }

        if ((str.length() - offset) < (maxWidth - 3)) {
            offset = str.length() - (maxWidth - 3);
        }

        if (offset <= 4) {
            return str.substring(0, maxWidth - 3) + "...";
        }

        // 调整最大宽度
        if (maxWidth < 7) {
            maxWidth = 7;
        }

        if ((offset + (maxWidth - 3)) < str.length()) {
            return "..." + abbreviate(str.substring(offset), maxWidth - 3);
        }

        return "..." + str.substring(str.length() - (maxWidth - 3));
    }

    /*
     * ========================================================================== ==
     */
    /* 比较两个字符串的异同。 */
    /*                                                                              */
    /* 查找字符串之间的差异，比较字符串的相似度。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 比较两个字符串，取得第二个字符串中，和第一个字符串不同的部分。
     *
     * <pre>
     * StringUtil.difference(&quot;i am a machine&quot;, &quot;i am a robot&quot;)  = &quot;robot&quot;
     * StringUtil.difference(null, null)                        = null
     * StringUtil.difference(&quot;&quot;, &quot;&quot;)                            = &quot;&quot;
     * StringUtil.difference(&quot;&quot;, null)                          = &quot;&quot;
     * StringUtil.difference(&quot;&quot;, &quot;abc&quot;)                         = &quot;abc&quot;
     * StringUtil.difference(&quot;abc&quot;, &quot;&quot;)                         = &quot;&quot;
     * StringUtil.difference(&quot;abc&quot;, &quot;abc&quot;)                      = &quot;&quot;
     * StringUtil.difference(&quot;ab&quot;, &quot;abxyz&quot;)                     = &quot;xyz&quot;
     * StringUtil.difference(&quot;abcde&quot;, &quot;abxyz&quot;)                  = &quot;xyz&quot;
     * StringUtil.difference(&quot;abcde&quot;, &quot;xyz&quot;)                    = &quot;xyz&quot;
     * </pre>
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 第二个字符串中，和第一个字符串不同的部分。如果两个字符串相同，则返回空字符串<code>""</code>
     */
    public static String difference(String str1, String str2) {
        if (str1 == null) {
            return str2;
        }

        if (str2 == null) {
            return str1;
        }

        int index = indexOfDifference(str1, str2);

        if (index == -1) {
            return EmptyPool.EMPTY_STRING;
        }

        return str2.substring(index);
    }

    /**
     * 比较两个字符串，取得两字符串开始不同的索引值。
     *
     * <pre>
     * StringUtil.indexOfDifference(&quot;i am a machine&quot;, &quot;i am a robot&quot;)   = 7
     * StringUtil.indexOfDifference(null, null)                         = -1
     * StringUtil.indexOfDifference(&quot;&quot;, null)                           = -1
     * StringUtil.indexOfDifference(&quot;&quot;, &quot;&quot;)                             = -1
     * StringUtil.indexOfDifference(&quot;&quot;, &quot;abc&quot;)                          = 0
     * StringUtil.indexOfDifference(&quot;abc&quot;, &quot;&quot;)                          = 0
     * StringUtil.indexOfDifference(&quot;abc&quot;, &quot;abc&quot;)                       = -1
     * StringUtil.indexOfDifference(&quot;ab&quot;, &quot;abxyz&quot;)                      = 2
     * StringUtil.indexOfDifference(&quot;abcde&quot;, &quot;abxyz&quot;)                   = 2
     * StringUtil.indexOfDifference(&quot;abcde&quot;, &quot;xyz&quot;)                     = 0
     * </pre>
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 两字符串开始产生差异的索引值，如果两字符串相同，则返回<code>-1</code>
     */
    public static int indexOfDifference(String str1, String str2) {
        if ((str1 == null) || (str2 == null) || (str1.equals(str2))) {
            return -1;
        }

        int i;

        for (i = 0; (i < str1.length()) && (i < str2.length()); ++i) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
        }
        return i;
    }

    /**
     * 取得两个字符串的相似度，<code>0</code>代表字符串相等，数字越大表示字符串越不像。
     *
     * <div>
     * 这个算法取自<a href="http://www.merriampark.com/ld.htm">http://www.merriampark.com
     * /ld.htm</a>。
     * 它计算的是从字符串1转变到字符串2所需要的删除、插入和替换的步骤数。
     * </div>
     *
     * <pre>
     * StringUtil.getLevenshteinDistance(null, *)             = IllegalArgumentException
     * StringUtil.getLevenshteinDistance(*, null)             = IllegalArgumentException
     * StringUtil.getLevenshteinDistance(&quot;&quot;,&quot;&quot;)               = 0
     * StringUtil.getLevenshteinDistance(&quot;&quot;,&quot;a&quot;)              = 1
     * StringUtil.getLevenshteinDistance(&quot;aaapppp&quot;, &quot;&quot;)       = 7
     * StringUtil.getLevenshteinDistance(&quot;frog&quot;, &quot;fog&quot;)       = 1
     * StringUtil.getLevenshteinDistance(&quot;fly&quot;, &quot;ant&quot;)        = 3
     * StringUtil.getLevenshteinDistance(&quot;elephant&quot;, &quot;hippo&quot;) = 7
     * StringUtil.getLevenshteinDistance(&quot;hippo&quot;, &quot;elephant&quot;) = 7
     * StringUtil.getLevenshteinDistance(&quot;hippo&quot;, &quot;zzzzzzzz&quot;) = 8
     * StringUtil.getLevenshteinDistance(&quot;hello&quot;, &quot;hallo&quot;)    = 1
     * </pre>
     *
     * @param s 第一个字符串，如果是<code>null</code>，则看作空字符串
     * @param t 第二个字符串，如果是<code>null</code>，则看作空字符串
     * @return 相似度值
     */
    public static int getLevenshteinDistance(String s, String t) {
        s = defaultIfNull(s);
        t = defaultIfNull(t);

        int[][] d; // matrix
        int n; // length of s
        int m; // length of t
        int i; // iterates through s
        int j; // iterates through t
        char s_i; // ith character of s
        char t_j; // jth character of t
        int cost; // cost

        // Step 1
        n = s.length();
        m = t.length();

        if (n == 0) {
            return m;
        }

        if (m == 0) {
            return n;
        }

        d = new int[n + 1][m + 1];

        // Step 2
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        // Step 3
        for (i = 1; i <= n; i++) {
            s_i = s.charAt(i - 1);

            // Step 4
            for (j = 1; j <= m; j++) {
                t_j = t.charAt(j - 1);

                // Step 5
                if (s_i == t_j) {
                    cost = 0;
                } else {
                    cost = 1;
                }

                // Step 6
                d[i][j] = __minNumber(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);
            }
        }

        // Step 7
        return d[n][m];
    }

    /**
     * 取得最小数。
     *
     * @param a 整数1
     * @param b 整数2
     * @param c 整数3
     * @return 三个数中的最小值
     */
    private static int __minNumber(int a, int b, int c) {
        if (b < a) {
            a = b;
        }

        if (c < a) {
            a = c;
        }

        return a;
    }

    /**
     * 将字符串转移为ASCII码
     *
     * <pre>
     * StringUtil.getASCII(null) = null
     * StringUtil.getASCII(&quot;中华人民共和国&quot;) = &quot;d6d0bbaac8cbc3f1b9b2bacdb9fa&quot;
     * StringUtil.getASCII(&quot;abc&quot;) = &quot;616263&quot;
     * </pre>
     *
     * @param str 字符串
     * @return ASCII码，如果原字符串为<code>null</code>，则返回<code>null</code>。
     */
    public static String getASCII(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        byte[] byteArray = str.getBytes();
        for (byte b : byteArray) {
            builder.append(Integer.toHexString(b & 0xff));
        }
        return builder.toString();
    }

    // ==========================================================================
    // 格式替换。
    // ==========================================================================

    /**
     * 将字符串转换成camel case。
     * <div>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     * <br>
     *
     * <pre>
     * StringUtil.toCamelCase(null)  = null
     * StringUtil.toCamelCase("")    = ""
     * StringUtil.toCamelCase("aBc") = "aBc"
     * StringUtil.toCamelCase("aBc def") = "aBcDef"
     * StringUtil.toCamelCase("aBc def_ghi") = "aBcDefGhi"
     * StringUtil.toCamelCase("aBc def_ghi 123") = "aBcDefGhi123"
     * </pre>
     * 
     * <br>
     * </div>
     * <div>
     * 此方法会保留除了下划线和空白以外的所有分隔符。
     * </div>
     *
     * @param str 要转换的字符串
     * @return camel case字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String toCamelCase(String str) {
        return new WordTokenizer() {
            @Override
            protected void startSentence(StringBuilder buffer, char ch) {
                buffer.append(Character.toLowerCase(ch));
            }

            @Override
            protected void startWord(StringBuilder buffer, char ch) {
                if (!isDelimiter(buffer.charAt(buffer.length() - 1))) {
                    buffer.append(Character.toUpperCase(ch));
                } else {
                    buffer.append(Character.toLowerCase(ch));
                }
            }

            @Override
            protected void inWord(StringBuilder buffer, char ch) {
                buffer.append(Character.toLowerCase(ch));
            }

            @Override
            protected void startDigitSentence(StringBuilder buffer, char ch) {
                buffer.append(ch);
            }

            @Override
            protected void startDigitWord(StringBuilder buffer, char ch) {
                buffer.append(ch);
            }

            @Override
            protected void inDigitWord(StringBuilder buffer, char ch) {
                buffer.append(ch);
            }

            @Override
            protected void inDelimiter(StringBuilder buffer, char ch) {
                if (ch != UNDERSCORE) {
                    buffer.append(ch);
                }
            }
        }.parse(str);
    }

    /**
     * 将字符串转换成pascal case。
     * <div>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     * 
     * <pre>
     * StringUtil.toPascalCase(null)  = null
     * StringUtil.toPascalCase("")    = ""
     * StringUtil.toPascalCase("aBc") = "ABc"
     * StringUtil.toPascalCase("aBc def") = "ABcDef"
     * StringUtil.toPascalCase("aBc def_ghi") = "ABcDefGhi"
     * StringUtil.toPascalCase("aBc def_ghi 123") = "aBcDefGhi123"
     * </pre>
     * 
     * <br>
     * 此方法会保留除了下划线和空白以外的所有分隔符。
     * </div>
     *
     * @param str 要转换的字符串
     * @return pascal case字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String toPascalCase(String str) {
        return new WordTokenizer() {
            @Override
            protected void startSentence(StringBuilder buffer, char ch) {
                buffer.append(Character.toUpperCase(ch));
            }

            @Override
            protected void startWord(StringBuilder buffer, char ch) {
                buffer.append(Character.toUpperCase(ch));
            }

            @Override
            protected void inWord(StringBuilder buffer, char ch) {
                buffer.append(Character.toLowerCase(ch));
            }

            @Override
            protected void startDigitSentence(StringBuilder buffer, char ch) {
                buffer.append(ch);
            }

            @Override
            protected void startDigitWord(StringBuilder buffer, char ch) {
                buffer.append(ch);
            }

            @Override
            protected void inDigitWord(StringBuilder buffer, char ch) {
                buffer.append(ch);
            }

            @Override
            protected void inDelimiter(StringBuilder buffer, char ch) {
                if (ch != UNDERSCORE) {
                    buffer.append(ch);
                }
            }
        }.parse(str);
    }

    /**
     * 将字符串转换成下划线分隔的大写字符串。
     * <div>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     * <br>
     *
     * <pre>
     * StringUtil.toUpperCaseWithUnderscores(null)  = null
     * StringUtil.toUpperCaseWithUnderscores("")    = ""
     * StringUtil.toUpperCaseWithUnderscores("aBc") = "A_BC"
     * StringUtil.toUpperCaseWithUnderscores("aBc def") = "A_BC_DEF"
     * StringUtil.toUpperCaseWithUnderscores("aBc def_ghi") = "A_BC_DEF_GHI"
     * StringUtil.toUpperCaseWithUnderscores("aBc def_ghi 123") = "A_BC_DEF_GHI_123"
     * StringUtil.toUpperCaseWithUnderscores("__a__Bc__") = "__A__BC__"
     * </pre>
     * 
     * <br>
     * </div>
     * <div>
     * 此方法会保留除了空白以外的所有分隔符。
     * </div>
     *
     * @param str 要转换的字符串
     * @return 下划线分隔的大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String toUpperCaseWithUnderscores(String str) {
        return new WordTokenizer() {
            @Override
            protected void startSentence(StringBuilder buffer, char ch) {
                buffer.append(Character.toUpperCase(ch));
            }

            @Override
            protected void startWord(StringBuilder buffer, char ch) {
                if (!isDelimiter(buffer.charAt(buffer.length() - 1))) {
                    buffer.append(UNDERSCORE);
                }

                buffer.append(Character.toUpperCase(ch));
            }

            @Override
            protected void inWord(StringBuilder buffer, char ch) {
                buffer.append(Character.toUpperCase(ch));
            }

            @Override
            protected void startDigitSentence(StringBuilder buffer, char ch) {
                buffer.append(ch);
            }

            @Override
            protected void startDigitWord(StringBuilder buffer, char ch) {
                if (!isDelimiter(buffer.charAt(buffer.length() - 1))) {
                    buffer.append(UNDERSCORE);
                }

                buffer.append(ch);
            }

            @Override
            protected void inDigitWord(StringBuilder buffer, char ch) {
                buffer.append(ch);
            }

            @Override
            protected void inDelimiter(StringBuilder buffer, char ch) {
                buffer.append(ch);
            }
        }.parse(str);
    }

    /**
     * 将字符串转换成下划线分隔的小写字符串。
     * <div>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     * <br>
     *
     * <pre>
     * StringUtil.toLowerCaseWithUnderscores(null)  = null
     * StringUtil.toLowerCaseWithUnderscores("")    = ""
     * StringUtil.toLowerCaseWithUnderscores("aBc") = "a_bc"
     * StringUtil.toLowerCaseWithUnderscores("aBc def") = "a_bc_def"
     * StringUtil.toLowerCaseWithUnderscores("aBc def_ghi") = "a_bc_def_ghi"
     * StringUtil.toLowerCaseWithUnderscores("aBc def_ghi 123") = "a_bc_def_ghi_123"
     * StringUtil.toLowerCaseWithUnderscores("__a__Bc__") = "__a__bc__"
     * </pre>
     * 
     * <br>
     * </div>
     * <div>
     * 此方法会保留除了空白以外的所有分隔符。
     * </div>
     *
     * @param str 要转换的字符串
     * @return 下划线分隔的小写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String toLowerCaseWithUnderscores(String str) {
        return new WordTokenizer() {
            @Override
            protected void startSentence(StringBuilder buffer, char ch) {
                buffer.append(Character.toLowerCase(ch));
            }

            @Override
            protected void startWord(StringBuilder buffer, char ch) {
                if (!isDelimiter(buffer.charAt(buffer.length() - 1))) {
                    buffer.append(UNDERSCORE);
                }

                buffer.append(Character.toLowerCase(ch));
            }

            @Override
            protected void inWord(StringBuilder buffer, char ch) {
                buffer.append(Character.toLowerCase(ch));
            }

            @Override
            protected void startDigitSentence(StringBuilder buffer, char ch) {
                buffer.append(ch);
            }

            @Override
            protected void startDigitWord(StringBuilder buffer, char ch) {
                if (!isDelimiter(buffer.charAt(buffer.length() - 1))) {
                    buffer.append(UNDERSCORE);
                }

                buffer.append(ch);
            }

            @Override
            protected void inDigitWord(StringBuilder buffer, char ch) {
                buffer.append(ch);
            }

            @Override
            protected void inDelimiter(StringBuilder buffer, char ch) {
                buffer.append(ch);
            }
        }.parse(str);
    }

    /**
     * 解析出下列语法所构成的<code>SENTENCE</code>。
     * <br>
     *
     * <pre>
     *  SENTENCE = WORD (DELIMITER* WORD)*
     *
     *  WORD = UPPER_CASE_WORD | LOWER_CASE_WORD | TITLE_CASE_WORD | DIGIT_WORD
     *
     *  UPPER_CASE_WORD = UPPER_CASE_LETTER+
     *  LOWER_CASE_WORD = LOWER_CASE_LETTER+
     *  TITLE_CASE_WORD = UPPER_CASE_LETTER LOWER_CASE_LETTER+
     *  DIGIT_WORD      = DIGIT+
     *
     *  UPPER_CASE_LETTER = Character.isUpperCase()
     *  LOWER_CASE_LETTER = Character.isLowerCase()
     *  DIGIT             = Character.isDigit()
     *  NON_LETTER_DIGIT  = !Character.isUpperCase() && !Character.isLowerCase() && !Character.isDigit()
     *
     *  DELIMITER = WHITESPACE | NON_LETTER_DIGIT
     * </pre>
     */
    private abstract static class WordTokenizer {
        protected static final char UNDERSCORE = '_';

        /**
         * Parse sentence。
         */
        public String parse(String str) {
            if (EmptyUtil.isEmpty(str)) {
                return str;
            }

            int length = str.length();
            StringBuilder buffer = new StringBuilder(length);

            for (int index = 0; index < length; index++) {
                char ch = str.charAt(index);

                // 忽略空白。
                if (Character.isWhitespace(ch)) {
                    continue;
                }

                // 大写字母开始：UpperCaseWord或是TitleCaseWord。
                if (Character.isUpperCase(ch)) {
                    int wordIndex = index + 1;

                    while (wordIndex < length) {
                        char wordChar = str.charAt(wordIndex);

                        if (Character.isUpperCase(wordChar)) {
                            wordIndex++;
                        } else if (Character.isLowerCase(wordChar)) {
                            wordIndex--;
                            break;
                        } else {
                            break;
                        }
                    }

                    // 1. wordIndex == length，说明最后一个字母为大写，以upperCaseWord处理之。
                    // 2. wordIndex == index，说明index处为一个titleCaseWord。
                    // 3. wordIndex > index，说明index到wordIndex -
                    // 1处全部是大写，以upperCaseWord处理。
                    if (wordIndex == length || wordIndex > index) {
                        index = parseUpperCaseWord(buffer, str, index, wordIndex);
                    } else {
                        index = parseTitleCaseWord(buffer, str, index);
                    }

                    continue;
                }

                // 小写字母开始：LowerCaseWord。
                if (Character.isLowerCase(ch)) {
                    index = parseLowerCaseWord(buffer, str, index);
                    continue;
                }

                // 数字开始：DigitWord。
                if (Character.isDigit(ch)) {
                    index = parseDigitWord(buffer, str, index);
                    continue;
                }

                // 非字母数字开始：Delimiter。
                inDelimiter(buffer, ch);
            }

            return buffer.toString();
        }

        private int parseUpperCaseWord(StringBuilder buffer, String str, int index, int length) {
            char ch = str.charAt(index++);

            // 首字母，必然存在且为大写。
            if (buffer.length() == 0) {
                startSentence(buffer, ch);
            } else {
                startWord(buffer, ch);
            }

            // 后续字母，必为小写。
            for (; index < length; index++) {
                ch = str.charAt(index);
                inWord(buffer, ch);
            }

            return index - 1;
        }

        private int parseLowerCaseWord(StringBuilder buffer, String str, int index) {
            char ch = str.charAt(index++);

            // 首字母，必然存在且为小写。
            if (buffer.length() == 0) {
                startSentence(buffer, ch);
            } else {
                startWord(buffer, ch);
            }

            // 后续字母，必为小写。
            int length = str.length();

            for (; index < length; index++) {
                ch = str.charAt(index);

                if (Character.isLowerCase(ch)) {
                    inWord(buffer, ch);
                } else {
                    break;
                }
            }

            return index - 1;
        }

        private int parseTitleCaseWord(StringBuilder buffer, String str, int index) {
            char ch = str.charAt(index++);

            // 首字母，必然存在且为大写。
            if (buffer.length() == 0) {
                startSentence(buffer, ch);
            } else {
                startWord(buffer, ch);
            }

            // 后续字母，必为小写。
            int length = str.length();

            for (; index < length; index++) {
                ch = str.charAt(index);

                if (Character.isLowerCase(ch)) {
                    inWord(buffer, ch);
                } else {
                    break;
                }
            }

            return index - 1;
        }

        private int parseDigitWord(StringBuilder buffer, String str, int index) {
            char ch = str.charAt(index++);

            // 首字符，必然存在且为数字。
            if (buffer.length() == 0) {
                startDigitSentence(buffer, ch);
            } else {
                startDigitWord(buffer, ch);
            }

            // 后续字符，必为数字。
            int length = str.length();

            for (; index < length; index++) {
                ch = str.charAt(index);

                if (Character.isDigit(ch)) {
                    inDigitWord(buffer, ch);
                } else {
                    break;
                }
            }

            return index - 1;
        }

        protected boolean isDelimiter(char ch) {
            return !Character.isUpperCase(ch) && !Character.isLowerCase(ch) && !Character.isDigit(ch);
        }

        protected abstract void startSentence(StringBuilder buffer, char ch);

        protected abstract void startWord(StringBuilder buffer, char ch);

        protected abstract void inWord(StringBuilder buffer, char ch);

        protected abstract void startDigitSentence(StringBuilder buffer, char ch);

        protected abstract void startDigitWord(StringBuilder buffer, char ch);

        protected abstract void inDigitWord(StringBuilder buffer, char ch);

        protected abstract void inDelimiter(StringBuilder buffer, char ch);
    }

}
