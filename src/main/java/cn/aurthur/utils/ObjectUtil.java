package cn.aurthur.utils;

import com.bqteam.basetool.sdk.core.lang.pool.EmptyPool;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 对象相关工具类 <br>
 * defaultIfNull 默认值函数 <br>
 * equal 比较函数 <br>
 * hashCode Hash code 函数 <br>
 * contains 对象中是否包含元素 <br>
 * toString 对象以字符串方式输出 <br>
 * isSameType 比较对象的类型
 *
 * @author aurthur
 * @since 1.0.0
 */
public abstract class ObjectUtil {
    public ObjectUtil() {
        throw new AssertionError("工具类不允许实例化");
    }

    /**
     * 判断对象是否均为<code>null</code>
     *
     * @param objects 传入对象集
     * @return 均为<code>null</code>则返还<code>true</code>
     */
    public static boolean isAllNull(Object... objects) {
        if (objects == null) {
            return true;
        }

        for (Object object : objects) {
            if (object != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否有任一对象为<code>null</code>
     *
     * @param objects 传入对象集
     * @return 任一对象为<code>null</code>则返还<code>true</code>
     */
    public static boolean isAnyNull(Object... objects) {
        if (objects == null) {
            return true;
        }

        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }

    // ==========================================================================
    // 默认值函数。
    //
    // 当对象为null时，将对象转换成指定的默认对象。
    // ==========================================================================

    /**
     * 如果对象为<code>null</code>，则返回指定默认对象，否则返回对象本身。
     * <br>
     *
     * <pre>
     * ObjectUtil.defaultIfNull(null, null)      = null
     * ObjectUtil.defaultIfNull(null, "")        = ""
     * ObjectUtil.defaultIfNull(null, "zz")      = "zz"
     * ObjectUtil.defaultIfNull("abc", *)        = "abc"
     * ObjectUtil.defaultIfNull(Boolean.TRUE, *) = Boolean.TRUE
     * </pre>
     *
     * @param object       要测试的对象
     * @param defaultValue 默认值
     * @return 对象本身或默认对象
     */
    public static <T, S extends T> T defaultIfNull(T object, S defaultValue) {
        return object == null ? defaultValue : object;
    }

    // ==========================================================================
    // 比较函数。
    //
    // 以下方法用来比较两个对象的值或类型是否相同。
    // ==========================================================================

    /**
     * 比较两个对象是否完全相等。
     * <div>
     * 此方法可以正确地比较多维数组。
     * <pre>
     * ObjectUtil.equals(null, null)                  = true
     * ObjectUtil.equals(null, "")                    = false
     * ObjectUtil.equals("", null)                    = false
     * ObjectUtil.equals("", "")                      = true
     * ObjectUtil.equals(Boolean.TRUE, null)          = false
     * ObjectUtil.equals(Boolean.TRUE, "true")        = false
     * ObjectUtil.equals(Boolean.TRUE, Boolean.TRUE)  = true
     * ObjectUtil.equals(Boolean.TRUE, Boolean.FALSE) = false
     * </pre>
     * </div>
     *
     * @param object1 对象1
     * @param object2 对象2
     * @return 如果相等, 则返回<code>true</code>
     */
    public static boolean equal(Object object1, Object object2) {
        if (object1 == object2) {
            return true;
        }

        if (object1 == null || object2 == null) {
            return false;
        }

        if (!object1.getClass().equals(object2.getClass())) {
            return false;
        }

        if (object1 instanceof Object[]) {
            return Arrays.deepEquals((Object[]) object1, (Object[]) object2);
        }
        if (object1 instanceof int[]) {
            return Arrays.equals((int[]) object1, (int[]) object2);
        }
        if (object1 instanceof long[]) {
            return Arrays.equals((long[]) object1, (long[]) object2);
        }
        if (object1 instanceof short[]) {
            return Arrays.equals((short[]) object1, (short[]) object2);
        }
        if (object1 instanceof byte[]) {
            return Arrays.equals((byte[]) object1, (byte[]) object2);
        }
        if (object1 instanceof double[]) {
            return Arrays.equals((double[]) object1, (double[]) object2);
        }
        if (object1 instanceof float[]) {
            return Arrays.equals((float[]) object1, (float[]) object2);
        }
        if (object1 instanceof char[]) {
            return Arrays.equals((char[]) object1, (char[]) object2);
        }
        if (object1 instanceof boolean[]) {
            return Arrays.equals((boolean[]) object1, (boolean[]) object2);
        }
        return object1.equals(object2);
    }
    // ==========================================================================
    // Hash code函数。
    //
    // 以下方法用来取得对象的hash code。
    // ==========================================================================

    /**
     * 取得对象的hash值, 如果对象为<code>null</code>, 则返回<code>0</code>。
     * <div>
     * 此方法可以正确地处理多维数组。
     * </div>
     *
     * @param object 对象
     * @return hash值
     */
    public static int hashCode(Object object) {
        if (object == null) {
            return 0;
        } else if (object instanceof Object[]) {
            return Arrays.deepHashCode((Object[]) object);
        } else if (object instanceof int[]) {
            return Arrays.hashCode((int[]) object);
        } else if (object instanceof long[]) {
            return Arrays.hashCode((long[]) object);
        } else if (object instanceof short[]) {
            return Arrays.hashCode((short[]) object);
        } else if (object instanceof byte[]) {
            return Arrays.hashCode((byte[]) object);
        } else if (object instanceof double[]) {
            return Arrays.hashCode((double[]) object);
        } else if (object instanceof float[]) {
            return Arrays.hashCode((float[]) object);
        } else if (object instanceof char[]) {
            return Arrays.hashCode((char[]) object);
        } else if (object instanceof boolean[]) {
            return Arrays.hashCode((boolean[]) object);
        } else {
            return object.hashCode();
        }
    }

    // ==========================================================================
    // 对象中是否包含元素。
    // ==========================================================================

    /**
     * 对象中是否包含元素<br>
     * 支持的对象类型包括：
     * <ul>
     * <li>String</li>
     * <li>Collection</li>
     * <li>Map</li>
     * <li>Iterator</li>
     * <li>Enumeration</li>
     * <li>Array</li>
     * </ul>
     *
     * @param obj     对象
     * @param element 元素
     * @return 是否包含
     */
    public static boolean contains(Object obj, Object element) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            if (element == null) {
                return false;
            }
            return ((String) obj).contains(element.toString());
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).contains(element);
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).containsValue(element);
        }

        if (obj instanceof Iterator) {
            Iterator<?> iter = (Iterator<?>) obj;
            while (iter.hasNext()) {
                Object o = iter.next();
                if (equal(o, element)) {
                    return true;
                }
            }
            return false;
        }
        if (obj instanceof Enumeration) {
            Enumeration<?> enumeration = (Enumeration<?>) obj;
            while (enumeration.hasMoreElements()) {
                Object o = enumeration.nextElement();
                if (equal(o, element)) {
                    return true;
                }
            }
            return false;
        }
        if (obj.getClass().isArray()) {
            int len = Array.getLength(obj);
            for (int i = 0; i < len; i++) {
                Object o = Array.get(obj, i);
                if (equal(o, element)) {
                    return true;
                }
            }
        }
        return false;
    }

    // ==========================================================================
    // toString方法。
    // ==========================================================================

    /**
     * 取得对象的<code>toString()</code>的值，如果对象为<code>null</code>，则返回空字符串 <code>""</code>。
     * <br>
     *
     * <pre>
     * ObjectUtil.toString(null)         = ""
     * ObjectUtil.toString("")           = ""
     * ObjectUtil.toString("bat")        = "bat"
     * ObjectUtil.toString(Boolean.TRUE) = "true"
     * ObjectUtil.toString([1, 2, 3])    = "[1, 2, 3]"
     * </pre>
     *
     * @param object 对象
     * @return 对象的<code>toString()</code>的返回值，或空字符串<code>""</code>
     */
    public static String toString(Object object) {
        return toString(object, EmptyPool.EMPTY_STRING);
    }

    /**
     * 取得对象的<code>toString()</code>的值，如果对象为<code>null</code>，则返回指定字符串。
     * <br>
     *
     * <pre>
     * ObjectUtil.toString(null, null)           = null
     * ObjectUtil.toString(null, "null")         = "null"
     * ObjectUtil.toString("", "null")           = ""
     * ObjectUtil.toString("bat", "null")        = "bat"
     * ObjectUtil.toString(Boolean.TRUE, "null") = "true"
     * ObjectUtil.toString([1, 2, 3], "null")    = "[1, 2, 3]"
     * </pre>
     *
     * @param object  对象
     * @param nullStr 如果对象为<code>null</code>，则返回该字符串
     * @return 对象的<code>toString()</code>的返回值，或指定字符串
     */
    public static String toString(Object object, String nullStr) {
        if (object == null) {
            return nullStr;
        } else if (object instanceof Object[]) {
            return Arrays.deepToString((Object[]) object);
        } else if (object instanceof int[]) {
            return Arrays.toString((int[]) object);
        } else if (object instanceof long[]) {
            return Arrays.toString((long[]) object);
        } else if (object instanceof short[]) {
            return Arrays.toString((short[]) object);
        } else if (object instanceof byte[]) {
            return Arrays.toString((byte[]) object);
        } else if (object instanceof double[]) {
            return Arrays.toString((double[]) object);
        } else if (object instanceof float[]) {
            return Arrays.toString((float[]) object);
        } else if (object instanceof char[]) {
            return Arrays.toString((char[]) object);
        } else if (object instanceof boolean[]) {
            return Arrays.toString((boolean[]) object);
        } else {
            return object.toString();
        }
    }

    /*
     * ========================================================================== ==
     */
    /* 比较对象的类型。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 检查两个对象是否属于相同类型。<code>null</code>将被看作任意类型。
     *
     * @param object1 对象1
     * @param object2 对象2
     * @return 如果两个对象有相同的类型，则返回<code>true</code>
     */
    public static boolean isSameType(Object object1, Object object2) {
        if ((object1 == null) || (object2 == null)) {
            return true;
        }

        return object1.getClass().equals(object2.getClass());
    }

}
