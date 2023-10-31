package cn.aurthur.utils;

import cn.aurthur.exception.CheckedFailedException;

/**
 * 断言<br>
 * 断言某些对象或值是否符合规定，否则抛出异常。经常用于做变量检查
 * <div>
 * notNull
 * isNull
 * isTrue
 * isFalse
 *
 * @author aurthur
 * @since 1.0.0
 */
public abstract class AssertUtil {
    public AssertUtil() {
        throw new AssertionError("工具类不允许实例化");
    }
    // ----------------------------------------------------------------------------------------------------
    // notNull

    /**
     * 确保对象不为空，否则抛出<code>CheckedFailedException</code>。
     */
    public static <T> T notNull(T object) {
        return notNull(object, null, null, (Object[]) null);
    }

    /**
     * 确保对象不为空，否则抛出<code>CheckedFailedException</code>。
     */
    public static <T> T notNull(T object, String message) {
        return notNull(object, null, message, (Object[]) null);
    }

    /**
     * 确保对象不为空，否则抛出<code>CheckedFailedException</code>。
     */
    public static <T> T notNull(T object, String message, Object... args) {
        return notNull(object, null, message, args);
    }

    /**
     * 确保对象不为空，否则抛出指定异常，默认为<code>CheckedFailedException</code>。
     */
    public static <T> T notNull(T object, ExceptionType exceptionType, String message, Object... args) {
        if (object == null) {
            if (exceptionType == null) {
                exceptionType = ExceptionType.CHECK_FAILED;
            }

            throw exceptionType.newInstance(__getMessage(message, args,
                    "[Assertion failed] - the argument is required; it must not be null"));
        }

        return object;
    }

    // ----------------------------------------------------------------------------------------------------
    // isNull

    /**
     * 确保对象为空，否则抛出<code>CheckedFailedException</code>。
     */
    public static <T> T isNull(T object) {
        return isNull(object, null, null, (Object[]) null);
    }

    /**
     * 确保对象为空，否则抛出<code>CheckedFailedException</code>。
     */
    public static <T> T isNull(T object, String message) {
        return isNull(object, null, message, (Object[]) null);
    }

    /**
     * 确保对象为空，否则抛出<code>CheckedFailedException</code>。
     */
    public static <T> T isNull(T object, String message, Object... args) {
        return isNull(object, null, message, args);
    }

    /**
     * 确保对象为空，否则抛出指定异常，默认为<code>CheckedFailedException</code>。
     */
    public static <T> T isNull(T object, ExceptionType exceptionType, String message, Object... args) {
        if (object != null) {
            if (exceptionType == null) {
                exceptionType = ExceptionType.CHECK_FAILED;
            }

            throw exceptionType.newInstance(__getMessage(message, args,
                    "[Assertion failed] - the object argument must be null"));
        }

        return null;
    }

    // ----------------------------------------------------------------------------------------------------
    // isTrue

    /**
     * 确保对象为空，否则抛出<code>CheckedFailedException</code>。
     */
    public static boolean isTrue(boolean expression) {
        return isTrue(expression, null, null, (Object[]) null);
    }

    /**
     * 确保对象为空，否则抛出<code>CheckedFailedException</code>。
     */
    public static boolean isTrue(boolean expression, String message) {
        return isTrue(expression, null, message, (Object[]) null);
    }

    /**
     * 确保对象为空，否则抛出<code>CheckedFailedException</code>。
     */
    public static boolean isTrue(boolean expression, String message, Object... args) {
        return isTrue(expression, null, message, args);
    }

    /**
     * 确保对象为空，否则抛出指定异常，默认为<code>CheckedFailedException</code>。
     */
    public static boolean isTrue(boolean expression, ExceptionType exceptionType, String message, Object... args) {
        if (!expression) {
            if (exceptionType == null) {
                exceptionType = ExceptionType.CHECK_FAILED;
            }

            throw exceptionType.newInstance(__getMessage(message, args,
                    "[Assertion failed] - the expression argument must be true"));
        }

        return true;
    }

    // ----------------------------------------------------------------------------------------------------
    // isFalse

    /**
     * 确保对象为空，否则抛出<code>CheckedFailedException</code>。
     */
    public static boolean isFalse(boolean expression) {
        return isFalse(expression, null, null, (Object[]) null);
    }

    /**
     * 确保对象为空，否则抛出<code>CheckedFailedException</code>。
     */
    public static boolean isFalse(boolean expression, String message) {
        return isFalse(expression, null, message, (Object[]) null);
    }

    /**
     * 确保对象为空，否则抛出<code>CheckedFailedException</code>。
     */
    public static boolean isFalse(boolean expression, String message, Object... args) {
        return isFalse(expression, null, message, args);
    }

    /**
     * 确保对象为空，否则抛出指定异常，默认为<code>CheckedFailedException</code>。
     */
    public static boolean isFalse(boolean expression, ExceptionType exceptionType, String message, Object... args) {
        if (expression) {
            if (exceptionType == null) {
                exceptionType = ExceptionType.CHECK_FAILED;
            }

            throw exceptionType.newInstance(__getMessage(message, args,
                    "[Assertion failed] - the expression argument must be false"));
        }

        return false;
    }

    /**
     * 取得带参数的消息。
     */
    private static String __getMessage(String message, Object[] args, String defaultMessage) {
        if (message == null) {
            message = defaultMessage;
        }

        if (args == null || args.length == 0) {
            return message;
        }

        return String.format(message, args);
    }

    /**
     * Assertion 错误类型。
     */
    public enum ExceptionType {
        CHECK_FAILED {
            @Override
            public RuntimeException newInstance(String message) {
                return new CheckedFailedException(message);
            }
        };

        public abstract RuntimeException newInstance(String message);
    }
}
