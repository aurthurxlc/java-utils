package cn.aurthur.lang.pool;

public interface StringPool {
    /**
     * 定义常用符号，方便统一维护
     */
    interface Symbol {

        /**
         * 空
         */
        String EMPTY = "";
        /**
         * 空格
         */
        String SPACE = " ";
        /**
         * 点号
         */
        String DOT = ".";
        /**
         * 点点
         */
        String DOTDOT = "..";
        /**
         * 逗号
         */
        String COMMA = ",";
        /**
         * 冒号
         */
        String COLON = ":";
        /**
         * 分号
         */
        String SEMICOLON = ";";
        /**
         * 换行符
         */
        String NEWLINE = "\n";
        /**
         * 斜杠
         **/
        String SLASH = "/";
        /**
         * 问号
         */
        String QUESTION = "?";
        /**
         * 单引号
         */
        String QUOTATION = "'";
        /**
         * 下划线
         */
        String UNDERLINE = "_";
        /**
         * 竖线
         */
        String VERTICAL = "|";
        /**
         * 美元符
         */
        String DOLLARS = "$";
        /**
         * 人民币
         */
        String RMB = "￥";
        /**
         * 百分号
         */
        String PERCENT = "%";
        /**
         * 零
         */
        String ZERO = "0";
        /**
         * 一
         */
        String ONE = "1";
        /**
         * 十
         */
        String TEN = "10";
        /**
         * null字符串
         */
        String NULL = "null";
        /**
         * 大括号
         */
        String BRACE = "{}";
        /**
         * 左大括号
         */
        String LEFT_BRACE = "{";
        /**
         * 右大括号
         */
        String RIGHT_BRACE = "}";
        /**
         * 中括号
         */
        String BRACKET = "[]";
        /**
         * 左中括号
         */
        String LEFT_BRACKET = "[";
        /**
         * 右中括号
         */
        String RIGHT_BRACKET = "]";
        /**
         * 小括号
         */
        String PARENTHESES = "()";
        /**
         * 左小括号
         */
        String LEFT_PARENTHESES = "(";
        /**
         * 右小括号
         */
        String RIGHT_PARENTHESES = ")";
        /**
         * 双引号
         */
        String DOUBLE_QUOTE = "\"";
        /**
         * 单引号
         */
        String SINGLE_QUOTE = "'";
        /**
         * 等号
         */
        String EQUALS = "=";
        /**
         * #号
         */
        String HASH = "#";

        // add
        String AMPERSAND = "&";
        String AT = "@";
        String ASTERISK = "*";
        String STAR = ASTERISK;
        String BACK_SLASH = "\\";
        String DASH = "-";
        String DOT_CLASS = ".class";
        String DOT_JAVA = ".java";
        String HAT = "^";
        String LEFT_CHEV = "<";
        String PLUS = "+";
        String EXCLAMATION_MARK = "!";
        String RETURN = "\r";
        String TAB = "\t";
        String RIGHT_CHEV = ">";
        String BACKTICK = "`";
        String CRLF = "\r\n";

        String HTML_NBSP = "&nbsp;";
        String HTML_AMP = "&amp";
        String HTML_QUOTE = "&quot;";
        String HTML_LT = "&lt;";
        String HTML_GT = "&gt;";
    }

    /**
     * 编码格式常量定义
     */
    interface Charset {
        String GBK = "GBK";
        String GB2312 = "GB2312";
        String UTF_8 = "UTF-8";
        String ISO_8859_1 = "ISO-8859-1";
        String US_ASCII = "US-ASCII";
        String UTF_16 = "UTF-16";
        String UTF_16BE = "UTF-16BE";
        String UTF_16LE = "UTF-16LE";

    }

    /**
     * 定义标准的格式
     */
    interface Format {
        String JSON = "json";
        String XML = "xml";
        String CSV = "csv";
    }

    /**
     * 单词
     */
    interface Word {
        String N = "n";
        String NO = "no";
        String NULL = "null";
        String OFF = "off";
        String ON = "on";
        String Y = "y";
        String YES = "yes";
        String ONE = "1";
        String ZERO = "0";
        String FALSE = "false";
        String TRUE = "true";
    }

    /**
     * 协议
     */
    interface Protocol {
        String HTTP = "http";
        String HTTPS = "https";
        String FTP = "ftp";
        String POP = "pop";
        String SMTP = "smtp";
        String IMAP = "imap";
        String P2P = "p2p";
        String LADP = "ladp";
        String TELNET = "telnet";
        String SSH = "ssh";
    }
}
