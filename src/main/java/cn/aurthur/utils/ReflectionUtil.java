package cn.aurthur.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * 有关 <code>Reflection</code> 处理的工具类。
 *
 * <div>
 * 这个类中的每个方法都可以“安全”地处理 <code>null</code> ，而不会抛出 <code>NullPointerException</code>。
 * </div>
 */
public abstract class ReflectionUtil {
    public ReflectionUtil() {
        throw new AssertionError("工具类不允许实例化");
    }

    /**
     * 根据对象的<code>Field</code>名返回<code>Field</code>
     *
     * @param object    要获取的对象
     * @param fieldName <code>Field</code>名
     * @return <code>Field</code>
     */
    public static Field getField(Object object, String fieldName) {
        if (ObjectUtil.isAnyNull(object, fieldName)) {
            return null;
        }

        return _getField(object.getClass(), fieldName);
    }

    /**
     * 根据对象的<code>Field</code>名返回<code>Field</code>
     *
     * @param clazz     要获取的类
     * @param fieldName <code>Field</code>名
     * @return <code>Field</code>
     */
    public static Field getField(Class<?> clazz, String fieldName) {
        if (ObjectUtil.isAnyNull(clazz, fieldName)) {
            return null;
        }

        return _getField(clazz, fieldName);
    }

    static Field _getField(Class<?> clazz, String fieldName) {
        for (Class<?> itr = clazz; hasSuperClass(itr); ) {
            Field[] fields = itr.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals(fieldName)) {
                    return field;
                }
            }

            itr = itr.getSuperclass();
        }

        return null;
    }

    /**
     * 读取<code>Field</code>的值
     *
     * @param field  目标<code>Field</code>
     * @param target 目标对象
     * @return <code>Field</code>值
     */
    public static <T> T readField(Field field, Object target) {
        if (field == null || target == null) {
            return null;
        }

        try {
            @SuppressWarnings("unchecked")
            T result = (T) _readField(field, target);

            return result;
        } catch (Exception ex) {
            throw ExceptionUtil.toRuntimeException(ex);
        }
    }

    /**
     * 要求参数参数不为<code>null</code>
     * <div>
     * 读取<code>Field</code>的值
     *
     * @param field  目标<code>Field</code>
     * @param target 目标对象
     * @return <code>Field</code>值
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    static Object _readField(Field field, Object target) throws IllegalArgumentException, IllegalAccessException {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        return field.get(target);
    }

    /**
     * 读取<code>Field</code>的值
     *
     * @param fieldName 目标<code>Field</code>名
     * @param target    目标对象
     * @return <code>Field</code>值
     */
    public static Object readField(String fieldName, Object target) {
        Field field = getField(target, fieldName);
        if (field == null) {
            return null;
        }

        try {
            return _readField(field, target);
        } catch (Exception ex) {
            throw ExceptionUtil.toRuntimeException(ex);
        }
    }

    /**
     * 写入<code>Field</code>的值
     *
     * @param field  目标<code>Field</code>
     * @param target 目标对象
     * @param value  写入的值
     */
    public static void writeField(Field field, Object target, Object value) {
        if (ObjectUtil.isAllNull(field, target, value)) {
            return;
        }

        try {
            _writeField(field, target, value);
        } catch (Exception ex) {
            throw ExceptionUtil.toRuntimeException(ex);
        }
    }

    /**
     * 要求参数参数不为<code>null</code>
     * <div>
     * 写入<code>Field</code>的值
     *
     * @param field  目标<code>Field</code>
     * @param target 目标对象
     * @param value  写入的值
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    static void _writeField(Field field, Object target, Object value) throws IllegalArgumentException,
            IllegalAccessException {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        field.set(target, value);
    }

    /**
     * 写入<code>Field</code>的值
     *
     * @param target    目标对象
     * @param fieldName 目标<code>Field</code>名
     * @param value     写入的值
     */
    public static void writeField(Object target, String fieldName, Object value) {
        Field field = getField(target, fieldName);
        if (field != null)
            try {
                _writeField(field, target, value);
            } catch (Exception ex) {
                throw ExceptionUtil.toRuntimeException(ex);
            }
    }


    /**
     * 获取类及父类的所有非静态<code>Field</code>，不包括<code>Object</code>的 <code>Field</code>
     * <br>
     * 如果<code>clazz</code>为<code>null</code>，返回<code>null</code>
     *
     * @param clazz 要获取的类
     * @return <code>Field</code>数组
     */
    public static Field[] getAllInstanceFields(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }

        return _getAllInstanceFields(clazz);
    }

    /**
     * 要求参数不为<code>null</code>
     * <div>
     * 获取类及父类的所有非静态<code>Field</code>，不包括<code>Object</code>的 <code>Field</code>
     *
     * @param clazz 要获取的类
     * @return <code>Field</code>数组
     */
    static Field[] _getAllInstanceFields(Class<?> clazz) {
        List<Field> fields = CollectionUtil.createArrayList();
        for (Class<?> itr = clazz; hasSuperClass(itr); ) {
            for (Field field : itr.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    fields.add(field);
                }
            }
            itr = itr.getSuperclass();
        }

        return fields.toArray(new Field[0]);
    }


    /**
     * 获取类及父类的所有非静态<code>Field</code>，不包括<code>Object</code>的 <code>Field</code>
     *
     * @param clazz      要获取的类
     * @param accessible 设置访问权限
     * @return <code>Field</code>数组
     */
    public static Field[] getAllInstanceFields(Class<?> clazz, boolean accessible) {
        Field[] fields = getAllInstanceFields(clazz);
        if (!EmptyUtil.isEmpty(fields)) {
            AccessibleObject.setAccessible(fields, accessible);
        }

        return fields;
    }

    /**
     * 获取对象的所有非静态<code>Field</code>，不包括<code>Object</code>的 <code>Field</code>
     * <br>
     * 如果<code>object</code>为<code>null</code>，返回<code>null</code>
     *
     * @param object 要获取的对象
     * @return <code>Field</code>数组
     */
    public static Field[] getAllInstanceFields(Object object) {
        if (object == null) {
            return null;
        }

        return _getAllInstanceFields(object.getClass());
    }

    /**
     * 判断是否有超类
     *
     * @param clazz 目标类
     * @return 如果有返回<code>true</code>，否则返回<code>false</code>
     */
    public static boolean hasSuperClass(Class<?> clazz) {
        return (clazz != null) && !clazz.equals(Object.class);
    }

    public static <A extends Annotation> boolean hasAnnotation(Field field, Class<A> annotationType) {
        if (ObjectUtil.isAnyNull(field, annotationType)) {
            return false;
        }

        return field.getAnnotation(annotationType) != null;
    }
}
