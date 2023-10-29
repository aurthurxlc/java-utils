package cn.aurthur.utils;

import java.io.IOException;

/**
 * 字符串转义工具类
 */
public abstract class StringEscapeUtil {
    public StringEscapeUtil() {
        throw new AssertionError("工具类不允许实例化");
    }
    // ==========================================================================
    // Java和JavaScript。
    // ==========================================================================

    /**
     * 按Java的规则对字符串进行转义。
     * <div>
     * 将双引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
     * </div>
     * <div>
     * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
     * </div>
     * <div>
     * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成 <code>He didn't say, \"Stop!\"</code>
     * </div>
     *
     * @param str 要转义的字符串
     * @return 转义后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String escapeJava(String str) {
        return escapeJavaStyleString(str, false, false);
    }

    /**
     * 按Java的规则对字符串进行转义。
     * <div>
     * 将双引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
     * </div>
     * <div>
     * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
     * </div>
     * <div>
     * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成 <code>He didn't say, \"Stop!\"</code>
     * </div>
     *
     * @param str    要转义的字符串
     * @param strict 是否以严格的方式编码字符串
     * @return 转义后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String escapeJava(String str, boolean strict) {
        return escapeJavaStyleString(str, false, strict);
    }

    /**
     * 按Java的规则对字符串进行转义。
     * <div>
     * 将双引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
     * </div>
     * <div>
     * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
     * </div>
     * <div>
     * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成 <code>He didn't say, \"Stop!\"</code>
     * </div>
     *
     * @param str 要转义的字符串
     * @param out 输出流
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException              如果输出失败
     */
    public static void escapeJava(String str, Appendable out) throws IOException {
        escapeJavaStyleString(str, false, out, false);
    }

    /**
     * 按Java的规则对字符串进行转义。
     * <div>
     * 将双引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
     * </div>
     * <div>
     * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
     * </div>
     * <div>
     * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成 <code>He didn't say, \"Stop!\"</code>
     * </div>
     *
     * @param str    要转义的字符串
     * @param out    输出流
     * @param strict 是否以严格的方式编码字符串
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException              如果输出失败
     */
    public static void escapeJava(String str, Appendable out, boolean strict) throws IOException {
        escapeJavaStyleString(str, false, out, strict);
    }

    /**
     * 按JavaScript的规则对字符串进行转义。
     * <div>
     * 将双引号、单引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
     * </div>
     * <div>
     * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
     * </div>
     * <div>
     * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成 <code>He didn\'t say, \"Stop!\"</code>
     * </div>
     *
     * @param str 要转义的字符串
     * @return 转义后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String escapeJavaScript(String str) {
        return escapeJavaStyleString(str, true, false);
    }

    /**
     * 按JavaScript的规则对字符串进行转义。
     * <div>
     * 将双引号、单引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
     * </div>
     * <div>
     * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
     * </div>
     * <div>
     * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成 <code>He didn\'t say, \"Stop!\"</code>
     * </div>
     *
     * @param str    要转义的字符串
     * @param strict 是否以严格的方式编码字符串
     * @return 转义后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String escapeJavaScript(String str, boolean strict) {
        return escapeJavaStyleString(str, true, strict);
    }

    /**
     * 按JavaScript的规则对字符串进行转义。
     * <div>
     * 将双引号、单引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
     * </div>
     * <div>
     * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
     * </div>
     * <div>
     * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成 <code>He didn\'t say, \"Stop!\"</code>
     * </div>
     *
     * @param str 要转义的字符串
     * @param out 输出流
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException              如果输出失败
     */
    public static void escapeJavaScript(String str, Appendable out) throws IOException {
        escapeJavaStyleString(str, true, out, false);
    }

    /**
     * 按JavaScript的规则对字符串进行转义。
     * <div>
     * 将双引号、单引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
     * </div>
     * <div>
     * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
     * </div>
     * <div>
     * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成 <code>He didn\'t say, \"Stop!\"</code>
     * </div>
     *
     * @param str    要转义的字符串
     * @param out    输出流
     * @param strict 是否以严格的方式编码字符串
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException              如果输出失败
     */
    public static void escapeJavaScript(String str, Appendable out, boolean strict) throws IOException {
        escapeJavaStyleString(str, true, out, strict);
    }

    /**
     * 按Java或JavaScript的规则对字符串进行转义。
     *
     * @param str        要转义的字符串
     * @param javascript 是否对单引号和slash进行转义
     * @param strict     是否以严格的方式编码字符串
     * @return 转义后的字符串
     */
    private static String escapeJavaStyleString(String str, boolean javascript, boolean strict) {
        if (str == null) {
            return null;
        }

        try {
            StringBuilder out = new StringBuilder(str.length() * 2);

            if (escapeJavaStyleString(str, javascript, out, strict)) {
                return out.toString();
            }

            return str;
        } catch (IOException e) {
            return str; // StringBuilder不可能发生这个异常
        }
    }

    /**
     * 按Java或JavaScript的规则对字符串进行转义。
     *
     * @param str        要转义的字符串
     * @param javascript 是否对单引号和slash进行转义
     * @param out        输出流
     * @param strict     是否以严格的方式编码字符串
     * @return 如果字符串没有变化，则返回<code>false</code>
     */
    private static boolean escapeJavaStyleString(String str, boolean javascript, Appendable out, boolean strict)
            throws IOException {
        boolean needToChange = false;

        if (out == null) {
            throw new IllegalArgumentException("The Appendable must not be null");
        }

        if (str == null) {
            return needToChange;
        }

        int length = str.length();

        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);

            if (ch < 32) {
                switch (ch) {
                    case '\b':
                        out.append('\\');
                        out.append('b');
                        break;

                    case '\n':
                        out.append('\\');
                        out.append('n');
                        break;

                    case '\t':
                        out.append('\\');
                        out.append('t');
                        break;

                    case '\f':
                        out.append('\\');
                        out.append('f');
                        break;

                    case '\r':
                        out.append('\\');
                        out.append('r');
                        break;

                    default:

                        if (ch > 0xf) {
                            out.append("\\u00").append(Integer.toHexString(ch).toUpperCase());
                        } else {
                            out.append("\\u000").append(Integer.toHexString(ch).toUpperCase());
                        }

                        break;
                }

                // 设置改变标志
                needToChange = true;
            } else if (strict && ch > 0xff) {
                if (ch > 0xfff) {
                    out.append("\\u").append(Integer.toHexString(ch).toUpperCase());
                } else {
                    out.append("\\u0").append(Integer.toHexString(ch).toUpperCase());
                }

                // 设置改变标志
                needToChange = true;
            } else {
                switch (ch) {
                    case '\'':
                    case '/': // 注意：对于javascript，对/进行escape是重要的安全措施。

                        if (javascript) {
                            out.append('\\');

                            // 设置改变标志
                            needToChange = true;
                        }

                        out.append(ch);

                        break;

                    case '"':
                        out.append('\\');
                        out.append('"');

                        // 设置改变标志
                        needToChange = true;
                        break;

                    case '\\':
                        out.append('\\');
                        out.append('\\');

                        // 设置改变标志
                        needToChange = true;
                        break;

                    default:
                        out.append(ch);
                        break;
                }
            }
        }

        return needToChange;
    }

    /**
     * 按Java的规则对字符串进行反向转义。
     * <div>
     * <code>'\\'</code>开头的形式转换成相应的字符，例如<code>\t</code>将被转换成tab制表符
     * </div>
     * <div>
     * 如果转义符不能被识别，它将被保留不变。
     * </div>
     *
     * @param str 不包含转义字符的字符串
     * @return 恢复成未转义的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String unescapeJava(String str) {
        return unescapeJavaStyleString(str);
    }

    /**
     * 按Java的规则对字符串进行反向转义。
     * <div>
     * <code>'\\'</code>开头的形式转换成相应的字符，例如<code>\t</code>将被转换成tab制表符
     * </div>
     * <div>
     * 如果转义符不能被识别，它将被保留不变。
     * </div>
     *
     * @param str 包含转义字符的字符串
     * @param out 输出流
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException              如果输出失败
     */
    public static void unescapeJava(String str, Appendable out) throws IOException {
        unescapeJavaStyleString(str, out);
    }

    /**
     * 按JavaScript的规则对字符串进行反向转义。
     * <div>
     * <code>'\\'</code>开头的形式转换成相应的字符，例如<code>\t</code>将被转换成tab制表符
     * </div>
     * <div>
     * 如果转义符不能被识别，它将被保留不变。
     * </div>
     *
     * @param str 包含转义字符的字符串
     * @return 恢复成未转义的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String unescapeJavaScript(String str) {
        return unescapeJavaStyleString(str);
    }

    /**
     * 按Java的规则对字符串进行反向转义。
     * <div>
     * <code>'\\'</code>开头的形式转换成相应的字符，例如<code>\t</code>将被转换成tab制表符
     * </div>
     * <div>
     * 如果转义符不能被识别，它将被保留不变。
     * </div>
     *
     * @param str 包含转义字符的字符串
     * @param out 输出流
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException              如果输出失败
     */
    public static void unescapeJavaScript(String str, Appendable out) throws IOException {
        unescapeJavaStyleString(str, out);
    }

    /**
     * 按Java的规则对字符串进行反向转义。
     * <div>
     * <code>'\\'</code>开头的形式转换成相应的字符，例如<code>\t</code>将被转换成tab制表符
     * </div>
     * <div>
     * 如果转义符不能被识别，它将被保留不变。
     * </div>
     *
     * @param str 包含转义字符的字符串
     * @return 不包含转义字符的字符串
     */
    private static String unescapeJavaStyleString(String str) {
        if (str == null) {
            return null;
        }

        try {
            StringBuilder out = new StringBuilder(str.length());

            if (unescapeJavaStyleString(str, out)) {
                return out.toString();
            }

            return str;
        } catch (IOException e) {
            return str; // StringBuilder不可能发生这个异常
        }
    }

    /**
     * 按Java的规则对字符串进行反向转义。
     * <div>
     * <code>'\\'</code>开头的形式转换成相应的字符，例如<code>\t</code>将被转换成tab制表符
     * </div>
     * <div>
     * 如果转义符不能被识别，它将被保留不变。
     * </div>
     *
     * @param str 包含转义字符的字符串
     * @param out 输出流
     * @return 如果字符串没有变化，则返回<code>false</code>
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException              如果输出失败
     */
    private static boolean unescapeJavaStyleString(String str, Appendable out) throws IOException {
        boolean needToChange = false;

        if (out == null) {
            throw new IllegalArgumentException("The Appendable must not be null");
        }

        if (str == null) {
            return needToChange;
        }

        int length = str.length();
        StringBuilder unicode = new StringBuilder(4);
        boolean hadSlash = false;
        boolean inUnicode = false;

        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);

            if (inUnicode) {
                unicode.append(ch);

                if (unicode.length() == 4) {
                    String unicodeStr = unicode.toString();

                    try {
                        int value = Integer.parseInt(unicodeStr, 16);

                        out.append((char) value);
                        unicode.setLength(0);
                        inUnicode = false;
                        hadSlash = false;

                        // 设置改变标志
                        needToChange = true;
                    } catch (NumberFormatException e) {
                        out.append("\\u").append(unicodeStr);
                    }
                }

                continue;
            }

            if (hadSlash) {
                hadSlash = false;

                switch (ch) {
                    case '\\':
                        out.append('\\');

                        // 设置改变标志
                        needToChange = true;
                        break;

                    case '\'':
                        out.append('\'');

                        // 设置改变标志
                        needToChange = true;
                        break;

                    case '\"':
                        out.append('"');

                        // 设置改变标志
                        needToChange = true;
                        break;

                    case 'r':
                        out.append('\r');

                        // 设置改变标志
                        needToChange = true;
                        break;

                    case 'f':
                        out.append('\f');

                        // 设置改变标志
                        needToChange = true;
                        break;

                    case 't':
                        out.append('\t');

                        // 设置改变标志
                        needToChange = true;
                        break;

                    case 'n':
                        out.append('\n');

                        // 设置改变标志
                        needToChange = true;
                        break;

                    case 'b':
                        out.append('\b');

                        // 设置改变标志
                        needToChange = true;
                        break;

                    case 'u': {
                        inUnicode = true;
                        break;
                    }

                    default:
                        out.append(ch);
                        break;
                }

                continue;
            } else if (ch == '\\') {
                hadSlash = true;
                continue;
            }

            out.append(ch);
        }

        if (hadSlash) {
            out.append('\\');
        }

        return needToChange;
    }

}
