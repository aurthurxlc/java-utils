package cn.aurthur.utils;

import java.lang.reflect.Array;

/**
 * 数组工具类
 * <br>
 * 基本类型（Primitive）：int boolean float char byte double short long <br>
 * Byte --- byte <br>
 * Short --- short <br>
 * Integer --- int <br>
 * Long --- long <br>
 * Float --- float <br>
 * Double --- double <br>
 * Character --- char <br>
 * Boolean --- boolean <br>
 * length 取数组长度 <br>
 * isSameLength 比较数组的长度 <br>
 * newArray 新建一个空数组 <br>
 * getComponentType 获取数组对象的元素类型 <br>
 * getArrayType 根据数组元素类型，获取数组的类型 <br>
 * append 将新元素添加到已有数组中 <br>
 * insert 将新元素插入到到已有数组中的某个位置 <br>
 * reverse 数组元素反转 <br>
 * indexOf lastIndexOf contains 在数组中查找一个元素或一个元素序列 <br>
 * clone 克隆函数，浅拷贝 <br>
 * toString 将数组转换成易于阅读的字符串表示 <br>
 * isCompatible 判断两个数组相容性 <br>
 * primitiveToString 基本类型转String <br>
 *
 * @author Aurthur
 * @since 1.0.0
 */
public abstract class ArrayUtil {
    private ArrayUtil() {
        throw new AssertionError("工具类不允许实例化");
    }

    /**
     * 数组中元素未找到的下标，值为-1
     */
    public static final int INDEX_NOT_FOUND = -1;


    // ==========================================================================
    // 取得数组长度。
    // ==========================================================================

    /**
     * 取数组长度，如果 array 不合法返回 0
     */
    public static int length(Object array) {
        return length(array, 0, 0);
    }

    /**
     * 取得数组长度
     *
     * @param array
     * @param defaultIfNull     对象为 null 时返回
     * @param defaultIfNotArray 对象不是Array时返回
     * @return
     */
    public static int length(Object array, int defaultIfNull, int defaultIfNotArray) {
        if (array == null) {
            return defaultIfNull; // null
        }
        if (array instanceof Object[]) {
            return ((Object[]) array).length;
        }
        if (array instanceof long[]) {
            return ((long[]) array).length;
        }
        if (array instanceof int[]) {
            return ((int[]) array).length;
        }
        if (array instanceof short[]) {
            return ((short[]) array).length;
        }
        if (array instanceof byte[]) {
            return ((byte[]) array).length;
        }
        if (array instanceof double[]) {
            return ((double[]) array).length;
        }
        if (array instanceof float[]) {
            return ((float[]) array).length;
        }
        if (array instanceof boolean[]) {
            return ((boolean[]) array).length;
        }
        if (array instanceof char[]) {
            return ((char[]) array).length;
        }
        return defaultIfNotArray; // not an array
    }


    // ==========================================================================
    // 比较数组的长度。
    // ==========================================================================

    /**
     * 判断两个数组是否具有相同的长度。如果数组为<code>null</code>则被看作长度为<code>0</code>。
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 如果两个数组长度相同，则返回<code>true</code>
     */
    public static boolean isSameLength(Object[] array1, Object[] array2) {
        int length1 = array1 == null ? 0 : array1.length;
        int length2 = array2 == null ? 0 : array2.length;

        return length1 == length2;
    }

    /**
     * 判断两个数组是否具有相同的长度。如果数组为<code>null</code>则被看作长度为<code>0</code>。
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 如果两个数组长度相同，则返回<code>true</code>
     */
    public static boolean isSameLength(long[] array1, long[] array2) {
        int length1 = array1 == null ? 0 : array1.length;
        int length2 = array2 == null ? 0 : array2.length;

        return length1 == length2;
    }

    /**
     * 判断两个数组是否具有相同的长度。如果数组为<code>null</code>则被看作长度为<code>0</code>。
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 如果两个数组长度相同，则返回<code>true</code>
     */
    public static boolean isSameLength(int[] array1, int[] array2) {
        int length1 = array1 == null ? 0 : array1.length;
        int length2 = array2 == null ? 0 : array2.length;

        return length1 == length2;
    }

    /**
     * 判断两个数组是否具有相同的长度。如果数组为<code>null</code>则被看作长度为<code>0</code>。
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 如果两个数组长度相同，则返回<code>true</code>
     */
    public static boolean isSameLength(short[] array1, short[] array2) {
        int length1 = array1 == null ? 0 : array1.length;
        int length2 = array2 == null ? 0 : array2.length;

        return length1 == length2;
    }

    /**
     * 判断两个数组是否具有相同的长度。如果数组为<code>null</code>则被看作长度为<code>0</code>。
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 如果两个数组长度相同，则返回<code>true</code>
     */
    public static boolean isSameLength(byte[] array1, byte[] array2) {
        int length1 = array1 == null ? 0 : array1.length;
        int length2 = array2 == null ? 0 : array2.length;

        return length1 == length2;
    }

    /**
     * 判断两个数组是否具有相同的长度。如果数组为<code>null</code>则被看作长度为<code>0</code>。
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 如果两个数组长度相同，则返回<code>true</code>
     */
    public static boolean isSameLength(double[] array1, double[] array2) {
        int length1 = array1 == null ? 0 : array1.length;
        int length2 = array2 == null ? 0 : array2.length;

        return length1 == length2;
    }

    /**
     * 判断两个数组是否具有相同的长度。如果数组为<code>null</code>则被看作长度为<code>0</code>。
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 如果两个数组长度相同，则返回<code>true</code>
     */
    public static boolean isSameLength(float[] array1, float[] array2) {
        int length1 = array1 == null ? 0 : array1.length;
        int length2 = array2 == null ? 0 : array2.length;

        return length1 == length2;
    }

    /**
     * 判断两个数组是否具有相同的长度。如果数组为<code>null</code>则被看作长度为<code>0</code>。
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 如果两个数组长度相同，则返回<code>true</code>
     */
    public static boolean isSameLength(boolean[] array1, boolean[] array2) {
        int length1 = array1 == null ? 0 : array1.length;
        int length2 = array2 == null ? 0 : array2.length;

        return length1 == length2;
    }

    /**
     * 判断两个数组是否具有相同的长度。如果数组为<code>null</code>则被看作长度为<code>0</code>。
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 如果两个数组长度相同，则返回<code>true</code>
     */
    public static boolean isSameLength(char[] array1, char[] array2) {
        int length1 = array1 == null ? 0 : array1.length;
        int length2 = array2 == null ? 0 : array2.length;

        return length1 == length2;
    }

    /**
     * 新建一个空数组
     *
     * @param <T>           数组元素类型
     * @param componentType 元素类型
     * @param newSize       大小
     * @return 空数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Class<?> componentType, int newSize) {
        return (T[]) Array.newInstance(componentType, newSize);
    }

    /**
     * 新建一个空数组
     *
     * @param newSize 大小
     * @return 空数组
     */
    public static Object[] newArray(int newSize) {
        return new Object[newSize];
    }

    /**
     * 获取数组对象的元素类型
     *
     * @param array 数组对象
     * @return 元素类型
     */
    public static Class<?> getComponentType(Object array) {
        return null == array ? null : array.getClass().getComponentType();
    }

    /**
     * 获取数组对象的元素类型
     *
     * @param arrayClass 数组类
     * @return 元素类型
     */
    public static Class<?> getComponentType(Class<?> arrayClass) {
        return null == arrayClass ? null : arrayClass.getComponentType();
    }

    /**
     * 根据数组元素类型，获取数组的类型<br>
     * 方法是通过创建一个空数组从而获取其类型
     *
     * @param componentType 数组元素类型
     * @return 数组类型
     */
    public static Class<?> getArrayType(Class<?> componentType) {
        return Array.newInstance(componentType, 0).getClass();
    }

    /**
     * 将新元素添加到已有数组中<br>
     * 添加新元素会生成一个新的数组，不影响原数组
     *
     * @param <T>         数组元素类型
     * @param buffer      已有数组
     * @param newElements 新元素
     * @return 新数组
     */
    @SafeVarargs
    public static <T> T[] append(T[] buffer, T... newElements) {
        if (EmptyUtil.isEmpty(buffer)) {
            return newElements;
        }
        return insert(buffer, buffer.length, newElements);
    }

    /**
     * 将新元素添加到已有数组中<br>
     * 添加新元素会生成一个新的数组，不影响原数组
     *
     * @param <T>         数组元素类型
     * @param array       已有数组
     * @param newElements 新元素
     * @return 新数组
     */
    @SafeVarargs
    public static <T> Object append(Object array, T... newElements) {
        if (EmptyUtil.isEmpty(array)) {
            return newElements;
        }
        return insert(array, length(array), newElements);
    }

    /**
     * 将新元素插入到到已有数组中的某个位置<br>
     * 添加新元素会生成一个新的数组，不影响原数组<br>
     * 如果插入位置为为负数，从原数组从后向前计数，若大于原数组长度，则空白处用null填充
     *
     * @param <T>         数组元素类型
     * @param buffer      已有数组
     * @param index       插入位置，此位置为对应此位置元素之前的空档
     * @param newElements 新元素
     * @return 新数组
     * @since 4.0.8
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] insert(T[] buffer, int index, T... newElements) {
        return (T[]) insert((Object) buffer, index, newElements);
    }

    /**
     * 将新元素插入到到已有数组中的某个位置<br>
     * 添加新元素会生成一个新的数组，不影响原数组<br>
     * 如果插入位置为为负数，从原数组从后向前计数，若大于原数组长度，则空白处用null填充
     *
     * @param <T>         数组元素类型
     * @param array       已有数组
     * @param index       插入位置，此位置为对应此位置元素之前的空档
     * @param newElements 新元素
     * @return 新数组
     */
    @SuppressWarnings({"unchecked", "SuspiciousSystemArraycopy"})
    public static <T> Object insert(Object array, int index, T... newElements) {
        if (EmptyUtil.isEmpty(newElements)) {
            return array;
        }
        if (EmptyUtil.isEmpty(array)) {
            return newElements;
        }

        final int len = length(array);
        if (index < 0) {
            index = (index % len) + len;
        }

        final T[] result = newArray(array.getClass().getComponentType(), Math.max(len, index) + newElements.length);
        System.arraycopy(array, 0, result, 0, Math.min(len, index));
        System.arraycopy(newElements, 0, result, index, newElements.length);
        if (index < len) {
            System.arraycopy(array, index, result, index + newElements.length, len - index);
        }
        return result;
    }


    // ==========================================================================
    // 反转数组的元素顺序。
    // ==========================================================================

    /**
     * 反转数组的元素顺序。如果数组为<code>null</code>，则什么也不做。
     *
     * @param array 要反转的数组
     */
    public static <T> void reverse(T[] array) {
        if (array == null) {
            return;
        }

        T tmp;

        for (int i = 0, j = array.length - 1; j > i; i++, j--) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }
    }

    /**
     * 反转数组的元素顺序。如果数组为<code>null</code>，则什么也不做。
     *
     * @param array 要反转的数组
     */
    public static void reverse(long[] array) {
        if (array == null) {
            return;
        }

        long tmp;

        for (int i = 0, j = array.length - 1; j > i; i++, j--) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }
    }

    /**
     * 反转数组的元素顺序。如果数组为<code>null</code>，则什么也不做。
     *
     * @param array 要反转的数组
     */
    public static void reverse(int[] array) {
        if (array == null) {
            return;
        }

        int tmp;

        for (int i = 0, j = array.length - 1; j > i; i++, j--) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }
    }

    /**
     * 反转数组的元素顺序。如果数组为<code>null</code>，则什么也不做。
     *
     * @param array 要反转的数组
     */
    public static void reverse(short[] array) {
        if (array == null) {
            return;
        }

        short tmp;

        for (int i = 0, j = array.length - 1; j > i; i++, j--) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }
    }

    /**
     * 反转数组的元素顺序。如果数组为<code>null</code>，则什么也不做。
     *
     * @param array 要反转的数组
     */
    public static void reverse(byte[] array) {
        if (array == null) {
            return;
        }

        byte tmp;

        for (int i = 0, j = array.length - 1; j > i; i++, j--) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }
    }

    /**
     * 反转数组的元素顺序。如果数组为<code>null</code>，则什么也不做。
     *
     * @param array 要反转的数组
     */
    public static void reverse(double[] array) {
        if (array == null) {
            return;
        }

        double tmp;

        for (int i = 0, j = array.length - 1; j > i; i++, j--) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }
    }

    /**
     * 反转数组的元素顺序。如果数组为<code>null</code>，则什么也不做。
     *
     * @param array 要反转的数组
     */
    public static void reverse(float[] array) {
        if (array == null) {
            return;
        }

        float tmp;

        for (int i = 0, j = array.length - 1; j > i; i++, j--) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }
    }

    /**
     * 反转数组的元素顺序。如果数组为<code>null</code>，则什么也不做。
     *
     * @param array 要反转的数组
     */
    public static void reverse(boolean[] array) {
        if (array == null) {
            return;
        }

        boolean tmp;

        for (int i = 0, j = array.length - 1; j > i; i++, j--) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }
    }

    /**
     * 反转数组的元素顺序。如果数组为<code>null</code>，则什么也不做。
     *
     * @param array 要反转的数组
     */
    public static void reverse(char[] array) {
        if (array == null) {
            return;
        }

        char tmp;

        for (int i = 0, j = array.length - 1; j > i; i++, j--) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }
    }

    // ==========================================================================
    // 在数组中查找一个元素或一个元素序列。
    //
    // 类型：Object[]
    // ==========================================================================

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array        要扫描的数组
     * @param objectToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind, 0);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(Object[] array, Object[] arrayToFind) {
        return indexOf(array, arrayToFind, 0);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array        要扫描的数组
     * @param objectToFind 要查找的元素
     * @param startIndex   起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (objectToFind == null) {
            for (int i = startIndex; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = startIndex; i < array.length; i++) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(Object[] array, Object[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        if (startIndex >= sourceLength) {
            return targetLength == 0 ? sourceLength : INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        Object first = arrayToFind[0];
        int i = startIndex;
        int max = sourceLength - targetLength;

        startSearchForFirst:
        while (true) {
            // 查找第一个元素
            while (i <= max && !ObjectUtil.equal(array[i], first)) {
                i++;
            }

            if (i > max) {
                return INDEX_NOT_FOUND;
            }

            // 已经找到第一个元素，接着找
            int j = i + 1;
            int end = j + targetLength - 1;
            int k = 1;

            while (j < end) {
                if (!ObjectUtil.equal(array[j++], arrayToFind[k++])) {
                    i++;

                    // 重新查找第一个元素
                    continue startSearchForFirst;
                }
            }

            // 找到了
            return i;
        }
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array        要扫描的数组
     * @param objectToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(Object[] array, Object objectToFind) {
        return lastIndexOf(array, objectToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(Object[] array, Object[] arrayToFind) {
        return lastIndexOf(array, arrayToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array        要扫描的数组
     * @param objectToFind 要查找的元素
     * @param startIndex   起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(Object[] array, Object objectToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }

        if (objectToFind == null) {
            for (int i = startIndex; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = startIndex; i >= 0; i--) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(Object[] array, Object[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        int rightIndex = sourceLength - targetLength;

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex > rightIndex) {
            startIndex = rightIndex;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        int lastIndex = targetLength - 1;
        Object last = arrayToFind[lastIndex];
        int min = targetLength - 1;
        int i = min + startIndex;

        startSearchForLast:
        while (true) {
            while (i >= min && !ObjectUtil.equal(array[i], last)) {
                i--;
            }

            if (i < min) {
                return INDEX_NOT_FOUND;
            }

            int j = i - 1;
            int start = j - (targetLength - 1);
            int k = lastIndex - 1;

            while (j > start) {
                if (!ObjectUtil.equal(array[j--], arrayToFind[k--])) {
                    i--;
                    continue startSearchForLast;
                }
            }

            return start + 1;
        }
    }

    /**
     * 判断指定对象是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array        要扫描的数组
     * @param objectToFind 要查找的元素
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定元素序列是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(Object[] array, Object[] arrayToFind) {
        return lastIndexOf(array, arrayToFind) != INDEX_NOT_FOUND;
    }


    // ==========================================================================
    // 在数组中查找一个元素或一个元素序列。
    //
    // 类型：long[]
    // ==========================================================================

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param longToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(long[] array, long longToFind) {
        return indexOf(array, longToFind, 0);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(long[] array, long[] arrayToFind) {
        return indexOf(array, arrayToFind, 0);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param longToFind 要查找的元素
     * @param startIndex 起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(long[] array, long longToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        for (int i = startIndex; i < array.length; i++) {
            if (longToFind == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(long[] array, long[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        if (startIndex >= sourceLength) {
            return targetLength == 0 ? sourceLength : INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        long first = arrayToFind[0];
        int i = startIndex;
        int max = sourceLength - targetLength;

        startSearchForFirst:
        while (true) {
            // 查找第一个元素
            while (i <= max && array[i] != first) {
                i++;
            }

            if (i > max) {
                return INDEX_NOT_FOUND;
            }

            // 已经找到第一个元素，接着找
            int j = i + 1;
            int end = j + targetLength - 1;
            int k = 1;

            while (j < end) {
                if (array[j++] != arrayToFind[k++]) {
                    i++;

                    // 重新查找第一个元素
                    continue startSearchForFirst;
                }
            }

            // 找到了
            return i;
        }
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param longToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(long[] array, long longToFind) {
        return lastIndexOf(array, longToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(long[] array, long[] arrayToFind) {
        return lastIndexOf(array, arrayToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array      要扫描的数组
     * @param longToFind 要查找的元素
     * @param startIndex 起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(long[] array, long longToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }

        for (int i = startIndex; i >= 0; i--) {
            if (longToFind == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(long[] array, long[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        int rightIndex = sourceLength - targetLength;

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex > rightIndex) {
            startIndex = rightIndex;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        int lastIndex = targetLength - 1;
        long last = arrayToFind[lastIndex];
        int min = targetLength - 1;
        int i = min + startIndex;

        startSearchForLast:
        while (true) {
            while (i >= min && array[i] != last) {
                i--;
            }

            if (i < min) {
                return INDEX_NOT_FOUND;
            }

            int j = i - 1;
            int start = j - (targetLength - 1);
            int k = lastIndex - 1;

            while (j > start) {
                if (array[j--] != arrayToFind[k--]) {
                    i--;
                    continue startSearchForLast;
                }
            }

            return start + 1;
        }
    }

    /**
     * 判断指定对象是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param longToFind 要查找的元素
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(long[] array, long longToFind) {
        return indexOf(array, longToFind) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定元素序列是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(long[] array, long[] arrayToFind) {
        return indexOf(array, arrayToFind) != INDEX_NOT_FOUND;
    }

    // ==========================================================================
    // 在数组中查找一个元素或一个元素序列。
    //
    // 类型：int[]
    // ==========================================================================

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array     要扫描的数组
     * @param intToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(int[] array, int intToFind) {
        return indexOf(array, intToFind, 0);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(int[] array, int[] arrayToFind) {
        return indexOf(array, arrayToFind, 0);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param intToFind  要查找的元素
     * @param startIndex 起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(int[] array, int intToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        for (int i = startIndex; i < array.length; i++) {
            if (intToFind == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(int[] array, int[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        if (startIndex >= sourceLength) {
            return targetLength == 0 ? sourceLength : INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        int first = arrayToFind[0];
        int i = startIndex;
        int max = sourceLength - targetLength;

        startSearchForFirst:
        while (true) {
            // 查找第一个元素
            while (i <= max && array[i] != first) {
                i++;
            }

            if (i > max) {
                return INDEX_NOT_FOUND;
            }

            // 已经找到第一个元素，接着找
            int j = i + 1;
            int end = j + targetLength - 1;
            int k = 1;

            while (j < end) {
                if (array[j++] != arrayToFind[k++]) {
                    i++;

                    // 重新查找第一个元素
                    continue startSearchForFirst;
                }
            }

            // 找到了
            return i;
        }
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array     要扫描的数组
     * @param intToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(int[] array, int intToFind) {
        return lastIndexOf(array, intToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(int[] array, int[] arrayToFind) {
        return lastIndexOf(array, arrayToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array      要扫描的数组
     * @param intToFind  要查找的元素
     * @param startIndex 起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(int[] array, int intToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }

        for (int i = startIndex; i >= 0; i--) {
            if (intToFind == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(int[] array, int[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        int rightIndex = sourceLength - targetLength;

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex > rightIndex) {
            startIndex = rightIndex;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        int lastIndex = targetLength - 1;
        int last = arrayToFind[lastIndex];
        int min = targetLength - 1;
        int i = min + startIndex;

        startSearchForLast:
        while (true) {
            while (i >= min && array[i] != last) {
                i--;
            }

            if (i < min) {
                return INDEX_NOT_FOUND;
            }

            int j = i - 1;
            int start = j - (targetLength - 1);
            int k = lastIndex - 1;

            while (j > start) {
                if (array[j--] != arrayToFind[k--]) {
                    i--;
                    continue startSearchForLast;
                }
            }

            return start + 1;
        }
    }

    /**
     * 判断指定对象是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array     要扫描的数组
     * @param intToFind 要查找的元素
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(int[] array, int intToFind) {
        return indexOf(array, intToFind) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定元素序列是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(int[] array, int[] arrayToFind) {
        return indexOf(array, arrayToFind) != INDEX_NOT_FOUND;
    }

    // ==========================================================================
    // 在数组中查找一个元素或一个元素序列。
    //
    // 类型：short[]
    // ==========================================================================

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param shortToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(short[] array, short shortToFind) {
        return indexOf(array, shortToFind, 0);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(short[] array, short[] arrayToFind) {
        return indexOf(array, arrayToFind, 0);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param shortToFind 要查找的元素
     * @param startIndex  起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(short[] array, short shortToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        for (int i = startIndex; i < array.length; i++) {
            if (shortToFind == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(short[] array, short[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        if (startIndex >= sourceLength) {
            return targetLength == 0 ? sourceLength : INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        short first = arrayToFind[0];
        int i = startIndex;
        int max = sourceLength - targetLength;

        startSearchForFirst:
        while (true) {
            // 查找第一个元素
            while (i <= max && array[i] != first) {
                i++;
            }

            if (i > max) {
                return INDEX_NOT_FOUND;
            }

            // 已经找到第一个元素，接着找
            int j = i + 1;
            int end = j + targetLength - 1;
            int k = 1;

            while (j < end) {
                if (array[j++] != arrayToFind[k++]) {
                    i++;

                    // 重新查找第一个元素
                    continue startSearchForFirst;
                }
            }

            // 找到了
            return i;
        }
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param shortToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(short[] array, short shortToFind) {
        return lastIndexOf(array, shortToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(short[] array, short[] arrayToFind) {
        return lastIndexOf(array, arrayToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param shortToFind 要查找的元素
     * @param startIndex  起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(short[] array, short shortToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }

        for (int i = startIndex; i >= 0; i--) {
            if (shortToFind == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(short[] array, short[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        int rightIndex = sourceLength - targetLength;

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex > rightIndex) {
            startIndex = rightIndex;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        int lastIndex = targetLength - 1;
        short last = arrayToFind[lastIndex];
        int min = targetLength - 1;
        int i = min + startIndex;

        startSearchForLast:
        while (true) {
            while (i >= min && array[i] != last) {
                i--;
            }

            if (i < min) {
                return INDEX_NOT_FOUND;
            }

            int j = i - 1;
            int start = j - (targetLength - 1);
            int k = lastIndex - 1;

            while (j > start) {
                if (array[j--] != arrayToFind[k--]) {
                    i--;
                    continue startSearchForLast;
                }
            }

            return start + 1;
        }
    }

    /**
     * 判断指定对象是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param shortToFind 要查找的元素
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(short[] array, short shortToFind) {
        return indexOf(array, shortToFind) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定元素序列是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(short[] array, short[] arrayToFind) {
        return indexOf(array, arrayToFind) != INDEX_NOT_FOUND;
    }

    // ==========================================================================
    // 在数组中查找一个元素或一个元素序列。
    //
    // 类型：byte[]
    // ==========================================================================

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param byteToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(byte[] array, byte byteToFind) {
        return indexOf(array, byteToFind, 0);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(byte[] array, byte[] arrayToFind) {
        return indexOf(array, arrayToFind, 0);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param byteToFind 要查找的元素
     * @param startIndex 起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(byte[] array, byte byteToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        for (int i = startIndex; i < array.length; i++) {
            if (byteToFind == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(byte[] array, byte[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        if (startIndex >= sourceLength) {
            return targetLength == 0 ? sourceLength : INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        byte first = arrayToFind[0];
        int i = startIndex;
        int max = sourceLength - targetLength;

        startSearchForFirst:
        while (true) {
            // 查找第一个元素
            while (i <= max && array[i] != first) {
                i++;
            }

            if (i > max) {
                return INDEX_NOT_FOUND;
            }

            // 已经找到第一个元素，接着找
            int j = i + 1;
            int end = j + targetLength - 1;
            int k = 1;

            while (j < end) {
                if (array[j++] != arrayToFind[k++]) {
                    i++;

                    // 重新查找第一个元素
                    continue startSearchForFirst;
                }
            }

            // 找到了
            return i;
        }
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param byteToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(byte[] array, byte byteToFind) {
        return lastIndexOf(array, byteToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(byte[] array, byte[] arrayToFind) {
        return lastIndexOf(array, arrayToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array      要扫描的数组
     * @param byteToFind 要查找的元素
     * @param startIndex 起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(byte[] array, byte byteToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }

        for (int i = startIndex; i >= 0; i--) {
            if (byteToFind == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(byte[] array, byte[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        int rightIndex = sourceLength - targetLength;

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex > rightIndex) {
            startIndex = rightIndex;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        int lastIndex = targetLength - 1;
        byte last = arrayToFind[lastIndex];
        int min = targetLength - 1;
        int i = min + startIndex;

        startSearchForLast:
        while (true) {
            while (i >= min && array[i] != last) {
                i--;
            }

            if (i < min) {
                return INDEX_NOT_FOUND;
            }

            int j = i - 1;
            int start = j - (targetLength - 1);
            int k = lastIndex - 1;

            while (j > start) {
                if (array[j--] != arrayToFind[k--]) {
                    i--;
                    continue startSearchForLast;
                }
            }

            return start + 1;
        }
    }

    /**
     * 判断指定对象是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param byteToFind 要查找的元素
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(byte[] array, byte byteToFind) {
        return indexOf(array, byteToFind) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定元素序列是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(byte[] array, byte[] arrayToFind) {
        return indexOf(array, arrayToFind) != INDEX_NOT_FOUND;
    }

    // ==========================================================================
    // 在数组中查找一个元素或一个元素序列。
    //
    // 类型：double[]
    // ==========================================================================

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array        要扫描的数组
     * @param doubleToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(double[] array, double doubleToFind) {
        return indexOf(array, doubleToFind, 0, 0);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array        要扫描的数组
     * @param doubleToFind 要查找的元素
     * @param tolerance    误差
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(double[] array, double doubleToFind, double tolerance) {
        return indexOf(array, doubleToFind, 0, tolerance);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(double[] array, double[] arrayToFind) {
        return indexOf(array, arrayToFind, 0, 0);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param tolerance   误差
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(double[] array, double[] arrayToFind, double tolerance) {
        return indexOf(array, arrayToFind, 0, tolerance);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array        要扫描的数组
     * @param doubleToFind 要查找的元素
     * @param startIndex   起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(double[] array, double doubleToFind, int startIndex) {
        return indexOf(array, doubleToFind, startIndex, 0);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array        要扫描的数组
     * @param doubleToFind 要查找的元素
     * @param startIndex   起始索引
     * @param tolerance    误差
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(double[] array, double doubleToFind, int startIndex, double tolerance) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        double min = doubleToFind - tolerance;
        double max = doubleToFind + tolerance;

        for (int i = startIndex; i < array.length; i++) {
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(double[] array, double[] arrayToFind, int startIndex) {
        return indexOf(array, arrayToFind, startIndex, 0);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @param tolerance   误差
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(double[] array, double[] arrayToFind, int startIndex, double tolerance) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        if (startIndex >= sourceLength) {
            return targetLength == 0 ? sourceLength : INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        double firstMin = arrayToFind[0] - tolerance;
        double firstMax = arrayToFind[0] + tolerance;
        int i = startIndex;
        int max = sourceLength - targetLength;

        startSearchForFirst:
        while (true) {
            // 查找第一个元素
            while (i <= max && (array[i] < firstMin || array[i] > firstMax)) {
                i++;
            }

            if (i > max) {
                return INDEX_NOT_FOUND;
            }

            // 已经找到第一个元素，接着找
            int j = i + 1;
            int end = j + targetLength - 1;
            int k = 1;

            while (j < end) {
                if (Math.abs(array[j++] - arrayToFind[k++]) > tolerance) {
                    i++;

                    // 重新查找第一个元素
                    continue startSearchForFirst;
                }
            }

            // 找到了
            return i;
        }
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array        要扫描的数组
     * @param doubleToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(double[] array, double doubleToFind) {
        return lastIndexOf(array, doubleToFind, Integer.MAX_VALUE, 0);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array        要扫描的数组
     * @param doubleToFind 要查找的元素
     * @param tolerance    误差
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(double[] array, double doubleToFind, double tolerance) {
        return lastIndexOf(array, doubleToFind, Integer.MAX_VALUE, tolerance);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(double[] array, double[] arrayToFind) {
        return lastIndexOf(array, arrayToFind, Integer.MAX_VALUE, 0);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param tolerance   误差
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(double[] array, double[] arrayToFind, double tolerance) {
        return lastIndexOf(array, arrayToFind, Integer.MAX_VALUE, tolerance);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array        要扫描的数组
     * @param doubleToFind 要查找的元素
     * @param startIndex   起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(double[] array, double doubleToFind, int startIndex) {
        return lastIndexOf(array, doubleToFind, startIndex, 0);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array        要扫描的数组
     * @param doubleToFind 要查找的元素
     * @param startIndex   起始索引
     * @param tolerance    误差
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(double[] array, double doubleToFind, int startIndex, double tolerance) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }

        double min = doubleToFind - tolerance;
        double max = doubleToFind + tolerance;

        for (int i = startIndex; i >= 0; i--) {
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(double[] array, double[] arrayToFind, int startIndex) {
        return lastIndexOf(array, arrayToFind, startIndex, 0);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @param tolerance   误差
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(double[] array, double[] arrayToFind, int startIndex, double tolerance) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        int rightIndex = sourceLength - targetLength;

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex > rightIndex) {
            startIndex = rightIndex;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        int lastIndex = targetLength - 1;
        double lastMin = arrayToFind[lastIndex] - tolerance;
        double lastMax = arrayToFind[lastIndex] + tolerance;
        int min = targetLength - 1;
        int i = min + startIndex;

        startSearchForLast:
        while (true) {
            while (i >= min && (array[i] < lastMin || array[i] > lastMax)) {
                i--;
            }

            if (i < min) {
                return INDEX_NOT_FOUND;
            }

            int j = i - 1;
            int start = j - (targetLength - 1);
            int k = lastIndex - 1;

            while (j > start) {
                if (Math.abs(array[j--] - arrayToFind[k--]) > tolerance) {
                    i--;
                    continue startSearchForLast;
                }
            }

            return start + 1;
        }
    }

    /**
     * 判断指定对象是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array        要扫描的数组
     * @param doubleToFind 要查找的元素
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(double[] array, double doubleToFind) {
        return indexOf(array, doubleToFind) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定对象是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array        要扫描的数组
     * @param doubleToFind 要查找的元素
     * @param tolerance    误差
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(double[] array, double doubleToFind, double tolerance) {
        return indexOf(array, doubleToFind, tolerance) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定元素序列是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(double[] array, double[] arrayToFind) {
        return indexOf(array, arrayToFind) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定元素序列是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param tolerance   误差
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(double[] array, double[] arrayToFind, double tolerance) {
        return indexOf(array, arrayToFind, tolerance) != INDEX_NOT_FOUND;
    }

    // ==========================================================================
    // 在数组中查找一个元素或一个元素序列。
    //
    // 类型：float[]
    // ==========================================================================

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param floatToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(float[] array, float floatToFind) {
        return indexOf(array, floatToFind, 0, 0);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param floatToFind 要查找的元素
     * @param tolerance   误差
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(float[] array, float floatToFind, float tolerance) {
        return indexOf(array, floatToFind, 0, tolerance);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(float[] array, float[] arrayToFind) {
        return indexOf(array, arrayToFind, 0, 0);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param tolerance   误差
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(float[] array, float[] arrayToFind, float tolerance) {
        return indexOf(array, arrayToFind, 0, tolerance);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param floatToFind 要查找的元素
     * @param startIndex  起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(float[] array, float floatToFind, int startIndex) {
        return indexOf(array, floatToFind, startIndex, 0);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param floatToFind 要查找的元素
     * @param startIndex  起始索引
     * @param tolerance   误差
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(float[] array, float floatToFind, int startIndex, float tolerance) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        float min = floatToFind - tolerance;
        float max = floatToFind + tolerance;

        for (int i = startIndex; i < array.length; i++) {
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(float[] array, float[] arrayToFind, int startIndex) {
        return indexOf(array, arrayToFind, startIndex, 0);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @param tolerance   误差
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(float[] array, float[] arrayToFind, int startIndex, float tolerance) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        if (startIndex >= sourceLength) {
            return targetLength == 0 ? sourceLength : INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        float firstMin = arrayToFind[0] - tolerance;
        float firstMax = arrayToFind[0] + tolerance;
        int i = startIndex;
        int max = sourceLength - targetLength;

        startSearchForFirst:
        while (true) {
            // 查找第一个元素
            while (i <= max && (array[i] < firstMin || array[i] > firstMax)) {
                i++;
            }

            if (i > max) {
                return INDEX_NOT_FOUND;
            }

            // 已经找到第一个元素，接着找
            int j = i + 1;
            int end = j + targetLength - 1;
            int k = 1;

            while (j < end) {
                if (Math.abs(array[j++] - arrayToFind[k++]) > tolerance) {
                    i++;

                    // 重新查找第一个元素
                    continue startSearchForFirst;
                }
            }

            // 找到了
            return i;
        }
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param floatToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(float[] array, float floatToFind) {
        return lastIndexOf(array, floatToFind, Integer.MAX_VALUE, 0);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param floatToFind 要查找的元素
     * @param tolerance   误差
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(float[] array, float floatToFind, float tolerance) {
        return lastIndexOf(array, floatToFind, Integer.MAX_VALUE, tolerance);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(float[] array, float[] arrayToFind) {
        return lastIndexOf(array, arrayToFind, Integer.MAX_VALUE, 0);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param tolerance   误差
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(float[] array, float[] arrayToFind, float tolerance) {
        return lastIndexOf(array, arrayToFind, Integer.MAX_VALUE, tolerance);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param floatToFind 要查找的元素
     * @param startIndex  起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(float[] array, float floatToFind, int startIndex) {
        return lastIndexOf(array, floatToFind, startIndex, 0);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param floatToFind 要查找的元素
     * @param startIndex  起始索引
     * @param tolerance   误差
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(float[] array, float floatToFind, int startIndex, float tolerance) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }

        float min = floatToFind - tolerance;
        float max = floatToFind + tolerance;

        for (int i = startIndex; i >= 0; i--) {
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(float[] array, float[] arrayToFind, int startIndex) {
        return lastIndexOf(array, arrayToFind, startIndex, 0);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @param tolerance   误差
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(float[] array, float[] arrayToFind, int startIndex, float tolerance) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        int rightIndex = sourceLength - targetLength;

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex > rightIndex) {
            startIndex = rightIndex;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        int lastIndex = targetLength - 1;
        float lastMin = arrayToFind[lastIndex] - tolerance;
        float lastMax = arrayToFind[lastIndex] + tolerance;
        int min = targetLength - 1;
        int i = min + startIndex;

        startSearchForLast:
        while (true) {
            while (i >= min && (array[i] < lastMin || array[i] > lastMax)) {
                i--;
            }

            if (i < min) {
                return INDEX_NOT_FOUND;
            }

            int j = i - 1;
            int start = j - (targetLength - 1);
            int k = lastIndex - 1;

            while (j > start) {
                if (Math.abs(array[j--] - arrayToFind[k--]) > tolerance) {
                    i--;
                    continue startSearchForLast;
                }
            }

            return start + 1;
        }
    }

    /**
     * 判断指定对象是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param floatToFind 要查找的元素
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(float[] array, float floatToFind) {
        return indexOf(array, floatToFind) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定对象是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param floatToFind 要查找的元素
     * @param tolerance   误差
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(float[] array, float floatToFind, float tolerance) {
        return indexOf(array, floatToFind, tolerance) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定元素序列是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(float[] array, float[] arrayToFind) {
        return indexOf(array, arrayToFind) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定元素序列是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param tolerance   误差
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(float[] array, float[] arrayToFind, float tolerance) {
        return indexOf(array, arrayToFind, tolerance) != INDEX_NOT_FOUND;
    }

    // ==========================================================================
    // 在数组中查找一个元素或一个元素序列。
    //
    // 类型：boolean[]
    // ==========================================================================

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array         要扫描的数组
     * @param booleanToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(boolean[] array, boolean booleanToFind) {
        return indexOf(array, booleanToFind, 0);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(boolean[] array, boolean[] arrayToFind) {
        return indexOf(array, arrayToFind, 0);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array         要扫描的数组
     * @param booleanToFind 要查找的元素
     * @param startIndex    起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(boolean[] array, boolean booleanToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        for (int i = startIndex; i < array.length; i++) {
            if (booleanToFind == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(boolean[] array, boolean[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        if (startIndex >= sourceLength) {
            return targetLength == 0 ? sourceLength : INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        boolean first = arrayToFind[0];
        int i = startIndex;
        int max = sourceLength - targetLength;

        startSearchForFirst:
        while (true) {
            // 查找第一个元素
            while (i <= max && array[i] != first) {
                i++;
            }

            if (i > max) {
                return INDEX_NOT_FOUND;
            }

            // 已经找到第一个元素，接着找
            int j = i + 1;
            int end = j + targetLength - 1;
            int k = 1;

            while (j < end) {
                if (array[j++] != arrayToFind[k++]) {
                    i++;

                    // 重新查找第一个元素
                    continue startSearchForFirst;
                }
            }

            // 找到了
            return i;
        }
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array         要扫描的数组
     * @param booleanToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(boolean[] array, boolean booleanToFind) {
        return lastIndexOf(array, booleanToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(boolean[] array, boolean[] arrayToFind) {
        return lastIndexOf(array, arrayToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array         要扫描的数组
     * @param booleanToFind 要查找的元素
     * @param startIndex    起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(boolean[] array, boolean booleanToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }

        for (int i = startIndex; i >= 0; i--) {
            if (booleanToFind == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(boolean[] array, boolean[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        int rightIndex = sourceLength - targetLength;

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex > rightIndex) {
            startIndex = rightIndex;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        int lastIndex = targetLength - 1;
        boolean last = arrayToFind[lastIndex];
        int min = targetLength - 1;
        int i = min + startIndex;

        startSearchForLast:
        while (true) {
            while (i >= min && array[i] != last) {
                i--;
            }

            if (i < min) {
                return INDEX_NOT_FOUND;
            }

            int j = i - 1;
            int start = j - (targetLength - 1);
            int k = lastIndex - 1;

            while (j > start) {
                if (array[j--] != arrayToFind[k--]) {
                    i--;
                    continue startSearchForLast;
                }
            }

            return start + 1;
        }
    }

    /**
     * 判断指定对象是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array         要扫描的数组
     * @param booleanToFind 要查找的元素
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(boolean[] array, boolean booleanToFind) {
        return indexOf(array, booleanToFind) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定元素序列是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(boolean[] array, boolean[] arrayToFind) {
        return indexOf(array, arrayToFind) != INDEX_NOT_FOUND;
    }

    // ==========================================================================
    // 在数组中查找一个元素或一个元素序列。
    //
    // 类型：char[]
    // ==========================================================================

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param charToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(char[] array, char charToFind) {
        return indexOf(array, charToFind, 0);
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(char[] array, char[] arrayToFind) {
        return indexOf(array, arrayToFind, 0);
    }

    /**
     * 在数组中查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param charToFind 要查找的元素
     * @param startIndex 起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(char[] array, char charToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        for (int i = startIndex; i < array.length; i++) {
            if (charToFind == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则看作<code>0</code>，超出数组长度的起始索引则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int indexOf(char[] array, char[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        if (startIndex >= sourceLength) {
            return targetLength == 0 ? sourceLength : INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        char first = arrayToFind[0];
        int i = startIndex;
        int max = sourceLength - targetLength;

        startSearchForFirst:
        while (true) {
            // 查找第一个元素
            while (i <= max && array[i] != first) {
                i++;
            }

            if (i > max) {
                return INDEX_NOT_FOUND;
            }

            // 已经找到第一个元素，接着找
            int j = i + 1;
            int end = j + targetLength - 1;
            int k = 1;

            while (j < end) {
                if (array[j++] != arrayToFind[k++]) {
                    i++;

                    // 重新查找第一个元素
                    continue startSearchForFirst;
                }
            }

            // 找到了
            return i;
        }
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param charToFind 要查找的元素
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(char[] array, char charToFind) {
        return lastIndexOf(array, charToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(char[] array, char[] arrayToFind) {
        return lastIndexOf(array, arrayToFind, Integer.MAX_VALUE);
    }

    /**
     * 在数组中从末尾开始查找一个元素。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array      要扫描的数组
     * @param charToFind 要查找的元素
     * @param startIndex 起始索引
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(char[] array, char charToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }

        for (int i = startIndex; i >= 0; i--) {
            if (charToFind == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 在数组中从末尾开始查找一个元素序列。
     * <div>
     * 如果未找到或数组为<code>null</code>则返回<code>-1</code>。
     * </div>
     * <div>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @param startIndex  起始索引
     * @return 该元素序列在数组中的序号，如果数组为<code>null</code>或未找到，则返回<code>-1</code>。
     */
    public static int lastIndexOf(char[] array, char[] arrayToFind, int startIndex) {
        if (array == null || arrayToFind == null) {
            return INDEX_NOT_FOUND;
        }

        int sourceLength = array.length;
        int targetLength = arrayToFind.length;

        int rightIndex = sourceLength - targetLength;

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex > rightIndex) {
            startIndex = rightIndex;
        }

        if (targetLength == 0) {
            return startIndex;
        }

        int lastIndex = targetLength - 1;
        char last = arrayToFind[lastIndex];
        int min = targetLength - 1;
        int i = min + startIndex;

        startSearchForLast:
        while (true) {
            while (i >= min && array[i] != last) {
                i--;
            }

            if (i < min) {
                return INDEX_NOT_FOUND;
            }

            int j = i - 1;
            int start = j - (targetLength - 1);
            int k = lastIndex - 1;

            while (j > start) {
                if (array[j--] != arrayToFind[k--]) {
                    i--;
                    continue startSearchForLast;
                }
            }

            return start + 1;
        }
    }

    /**
     * 判断指定对象是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array      要扫描的数组
     * @param charToFind 要查找的元素
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(char[] array, char charToFind) {
        return indexOf(array, charToFind) != INDEX_NOT_FOUND;
    }

    /**
     * 判断指定元素序列是否存在于指定数组中。
     * <div>
     * 如果数组为<code>null</code>则返回<code>false</code>。
     * </div>
     *
     * @param array       要扫描的数组
     * @param arrayToFind 要查找的元素序列
     * @return 如果找到则返回<code>true</code>
     */
    public static boolean contains(char[] array, char[] arrayToFind) {
        return indexOf(array, arrayToFind) != INDEX_NOT_FOUND;
    }

    /*
     * ========================================================================== ==
     */
    /* Clone函数。 */
    /*                                                                              */
    /* 以下方法调用Object.clone方法，进行“浅复制”（shallow copy）。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 复制一个数组。如果数组为<code>null</code>，则返回<code>null</code>。
     *
     * <div>
     * 此方法只进行“浅复制”，也就是说，数组中的对象本身不会被复制。 另外，此方法也不处理多维数组。
     * </div>
     *
     * @param array 要复制的数组
     * @return 数组的复本，如果原始数组为<code>null</code>，则返回<code>null</code>
     */
    public static <T> T[] clone(T[] array) {
        if (array == null) {
            return null;
        }

        return array.clone();
    }

    /**
     * 复制一个数组。如果数组为<code>null</code>，则返回<code>null</code>。
     *
     * <div>
     * 此方法也不处理多维数组。
     * </div>
     *
     * @param array 要复制的数组
     * @return 数组的复本，如果原始数组为<code>null</code>，则返回<code>null</code>
     */
    public static long[] clone(long[] array) {
        if (array == null) {
            return null;
        }

        return array.clone();
    }

    /**
     * 复制一个数组。如果数组为<code>null</code>，则返回<code>null</code>。
     *
     * <div>
     * 此方法也不处理多维数组。
     * </div>
     *
     * @param array 要复制的数组
     * @return 数组的复本，如果原始数组为<code>null</code>，则返回<code>null</code>
     */
    public static int[] clone(int[] array) {
        if (array == null) {
            return null;
        }

        return array.clone();
    }

    /**
     * 复制一个数组。如果数组为<code>null</code>，则返回<code>null</code>。
     *
     * <div>
     * 此方法也不处理多维数组。
     * </div>
     *
     * @param array 要复制的数组
     * @return 数组的复本，如果原始数组为<code>null</code>，则返回<code>null</code>
     */
    public static short[] clone(short[] array) {
        if (array == null) {
            return null;
        }

        return array.clone();
    }

    /**
     * 复制一个数组。如果数组为<code>null</code>，则返回<code>null</code>。
     *
     * <div>
     * 此方法也不处理多维数组。
     * </div>
     *
     * @param array 要复制的数组
     * @return 数组的复本，如果原始数组为<code>null</code>，则返回<code>null</code>
     */
    public static byte[] clone(byte[] array) {
        if (array == null) {
            return null;
        }

        return array.clone();
    }

    /**
     * 复制一个数组。如果数组为<code>null</code>，则返回<code>null</code>。
     *
     * <div>
     * 此方法也不处理多维数组。
     * </div>
     *
     * @param array 要复制的数组
     * @return 数组的复本，如果原始数组为<code>null</code>，则返回<code>null</code>
     */
    public static double[] clone(double[] array) {
        if (array == null) {
            return null;
        }

        return array.clone();
    }

    /**
     * 复制一个数组。如果数组为<code>null</code>，则返回<code>null</code>。
     *
     * <div>
     * 此方法也不处理多维数组。
     * </div>
     *
     * @param array 要复制的数组
     * @return 数组的复本，如果原始数组为<code>null</code>，则返回<code>null</code>
     */
    public static float[] clone(float[] array) {
        if (array == null) {
            return null;
        }

        return array.clone();
    }

    /**
     * 复制一个数组。如果数组为<code>null</code>，则返回<code>null</code>。
     *
     * <div>
     * 此方法也不处理多维数组。
     * </div>
     *
     * @param array 要复制的数组
     * @return 数组的复本，如果原始数组为<code>null</code>，则返回<code>null</code>
     */
    public static boolean[] clone(boolean[] array) {
        if (array == null) {
            return null;
        }

        return array.clone();
    }

    /**
     * 复制一个数组。如果数组为<code>null</code>，则返回<code>null</code>。
     *
     * <div>
     * 此方法也不处理多维数组。
     * </div>
     *
     * @param array 要复制的数组
     * @return 数组的复本，如果原始数组为<code>null</code>，则返回<code>null</code>
     */
    public static char[] clone(char[] array) {
        if (array == null) {
            return null;
        }

        return array.clone();
    }

    /**
     * 将数组转换成易于阅读的字符串表示。
     *
     * <div>
     * 如果数组是<code>null</code>则返回<code>[]</code>，支持多维数组。 如果数组元素为<code>null</code> ，则显示<code>&lt;null&gt;</code>。
     *
     * <pre>
     * ArrayUtil.toString(null)                              = &quot;[]&quot;
     * ArrayUtil.toString(new int[] {1, 2, 3})               = &quot;[1, 2, 3]&quot;
     * ArrayUtil.toString(new boolean[] {true, false, true}) = &quot;[true, false, true]&quot;
     * ArrayUtil.toString(new Object[] {
     *                       {1, 2, 3},  // 嵌套数组
     *                       hello,      // 嵌套非数组
     *                       null,       // 嵌套null
     *                       {},         // 嵌套空数组
     *                       {2, 3, 4}   // 嵌套数组
     *                    })                                 = &quot;[[1, 2, 3], hello, &lt;null&gt;, [], [2, 3, 4]]&quot;
     * </pre>
     *
     * </div>
     *
     * @param array 要转换的数组
     * @return 字符串表示，<code>"[]"</code>表示空数组或<code>null</code>
     */
    public static String toString(Object array) {
        if (EmptyUtil.isEmpty(array)) {
            return null;
        }
        Class<?> type = array.getClass();
        if (type.isArray()) {
            if (type == char[].class) {
                return __toString((char[]) array);
            }

            if (type == int[].class) {
                return __toString((int[]) array);
            }
            if (type == long[].class) {
                return __toString((long[]) array);
            }
            if (type == byte[].class) {
                return __toString((byte[]) array);
            }
            if (type == float[].class) {
                return __toString((float[]) array);
            }
            if (type == double[].class) {
                return __toString((double[]) array);
            }
            if (type == short[].class) {
                return __toString((short[]) array);
            }
            if (type == boolean[].class) {
                return __toString((boolean[]) array);
            }
            return __toString((Object[]) array);
        }

        return array.toString();
    }

    private static String __toString(int[] array) {
        int iMax = array.length - 1;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == iMax) {
                return builder.toString();
            }
            builder.append(",");
        }
    }

    private static String __toString(long[] array) {
        int iMax = array.length - 1;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == iMax) {
                return builder.toString();
            }
            builder.append(",");
        }
    }

    private static String __toString(short[] array) {
        int iMax = array.length - 1;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == iMax) {
                return builder.toString();
            }
            builder.append(",");
        }
    }

    private static String __toString(char[] array) {
        int iMax = array.length - 1;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == iMax) {
                return builder.toString();
            }
            builder.append(",");
        }
    }

    private static String __toString(byte[] array) {
        int iMax = array.length - 1;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == iMax) {
                return builder.toString();
            }
            builder.append(",");
        }
    }

    private static String __toString(boolean[] array) {
        int iMax = array.length - 1;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == iMax) {
                return builder.toString();
            }
            builder.append(",");
        }
    }

    private static String __toString(float[] array) {
        int iMax = array.length - 1;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == iMax) {
                return builder.toString();
            }
            builder.append(",");
        }
    }

    private static String __toString(double[] array) {
        int iMax = array.length - 1;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == iMax) {
                return builder.toString();
            }
            builder.append(",");
        }
    }

    private static String __toString(Object[] array) {
        int iMax = array.length - 1;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == iMax) {
                return builder.toString();
            }
            builder.append(",");
        }
    }

    /**
     * 将数组转换成易于阅读的字符串表示。
     *
     * <div>
     * 如果数组是<code>null</code>则返回指定字符串，支持多维数组。 如果数组元素为<code>null</code>，则显示 <code>&lt;null&gt;</code>。
     *
     * <pre>
     * ArrayUtil.toString(null, &quot;null&quot;)                              = &quot;null&quot;
     * ArrayUtil.toString(new int[] {1, 2, 3}, &quot;null&quot;)               = &quot;[1, 2, 3]&quot;
     * ArrayUtil.toString(new boolean[] {true, false, true}, &quot;null&quot;) = &quot;[true, false, true]&quot;
     * ArrayUtil.toString(new Object[] {
     *                       {1, 2, 3},  // 嵌套数组
     *                       hello,      // 嵌套非数组
     *                       null,       // 嵌套null
     *                       {},         // 嵌套空数组
     *                       {2, 3, 4}   // 嵌套数组
     *                    }, &quot;null&quot;)                                 = &quot;[[1, 2, 3], hello, &lt;null&gt;, [], [2, 3, 4]]&quot;
     * </pre>
     *
     * </div>
     *
     * @param array        要转换的数组
     * @param nullArrayStr 如果数组是<code>null</code>，则返回此字符串
     * @return 字符串表示，或返回指定字符串表示<code>null</code>
     */
    public static String toString(Object array, String nullArrayStr) {
        return toString(array, nullArrayStr, "<null>");
    }

    /**
     * 将数组转换成易于阅读的字符串表示。
     *
     * <div>
     * 如果数组是<code>null</code>则返回指定字符串，支持多维数组。 如果数组元素为<code>null</code>，则显示指定字符串。
     *
     * <pre>
     * ArrayUtil.toString(null, &quot;null&quot;, &quot;NULL&quot;)                              = &quot;null&quot;
     * ArrayUtil.toString(new int[] {1, 2, 3}, &quot;null&quot;, &quot;NULL&quot;)               = &quot;[1, 2, 3]&quot;
     * ArrayUtil.toString(new boolean[] {true, false, true}, &quot;null&quot;, &quot;NULL&quot;) = &quot;[true, false, true]&quot;
     * ArrayUtil.toString(new Object[] {
     *                       {1, 2, 3},  // 嵌套数组
     *                       hello,      // 嵌套非数组
     *                       null,       // 嵌套null
     *                       {},         // 嵌套空数组
     *                       {2, 3, 4}   // 嵌套数组
     *                    }, &quot;null&quot;, &quot;NULL&quot;)                                 = &quot;[[1, 2, 3], hello, NULL, [], [2, 3, 4]]&quot;
     * </pre>
     *
     * </div>
     *
     * @param array          要转换的数组
     * @param nullArrayStr   如果数组是<code>null</code>，则返回此字符串
     * @param nullElementStr 如果数组中的元素为<code>null</code>，则返回此字符串
     * @return 字符串表示，或返回指定字符串表示<code>null</code>
     */
    public static String toString(Object array, String nullArrayStr, String nullElementStr) {
        if (array == null) {
            return nullArrayStr;
        }

        StringBuilder builder = new StringBuilder();
        toString(builder, array, nullArrayStr, nullElementStr);
        return builder.toString();
    }

    /**
     * 将数组转换成易于阅读的字符串表示。<code>null</code>将被看作空数组。 支持多维数组。FIXME
     *
     * @param builder        将转换后的字符串加入到这个<code>StringBuilder</code>中
     * @param array          要转换的数组
     * @param nullArrayStr   如果数组是<code>null</code>，则返回此字符串
     * @param nullElementStr 如果数组中的元素为<code>null</code>，则返回此字符串
     */
    private static void toString(StringBuilder builder, Object array, String nullArrayStr, String nullElementStr) {
        if (array == null) {
            builder.append(nullElementStr);
            return;
        }

        if (!array.getClass().isArray()) {
            builder.append(ObjectUtil.toString(array, nullElementStr));
            return;
        }

        builder.append('[');

        // array为数组
        if (array instanceof long[]) {
            long[] longArray = (long[]) array;
            int length = longArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }

                builder.append(longArray[i]);
            }
        } else if (array instanceof int[]) {
            int[] intArray = (int[]) array;
            int length = intArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }

                builder.append(intArray[i]);
            }
        } else if (array instanceof short[]) {
            short[] shortArray = (short[]) array;
            int length = shortArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }

                builder.append(shortArray[i]);
            }
        } else if (array instanceof byte[]) {
            byte[] byteArray = (byte[]) array;
            int length = byteArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    builder.append(", ");
                } else {
                    builder.append("0x");
                }

                String hexStr = Integer.toHexString(0xFF & byteArray[i]).toUpperCase();

                if (hexStr.length() == 0) {
                    builder.append("00");
                } else if (hexStr.length() == 1) {
                    builder.append("0");
                }

                builder.append(hexStr);
            }
        } else if (array instanceof double[]) {
            double[] doubleArray = (double[]) array;
            int length = doubleArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }

                builder.append(doubleArray[i]);
            }
        } else if (array instanceof float[]) {
            float[] floatArray = (float[]) array;
            int length = floatArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }

                builder.append(floatArray[i]);
            }
        } else if (array instanceof boolean[]) {
            boolean[] booleanArray = (boolean[]) array;
            int length = booleanArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }

                builder.append(booleanArray[i]);
            }
        } else if (array instanceof char[]) {
            char[] charArray = (char[]) array;
            int length = charArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }

                builder.append(charArray[i]);
            }
        } else {
            Object[] objectArray = (Object[]) array;
            int length = objectArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }

                toString(builder, objectArray[i], nullArrayStr, nullElementStr);
            }
        }

        builder.append(']');
    }

    /**
     * 判断两个数组的相容性, 如果一个被另一个包含，即为相容
     *
     * @param <T>
     * @param arrayA 数组A
     * @param arrayB 数组B
     * @return 如果相容则返回<code>true</code>，否则返回<code>false</code>
     */
    public static <T> boolean isCompatible(T[] arrayA, T[] arrayB) {
        if (arrayA == null) {
            return arrayB == null;
        }
        if (arrayB == null) {
            return false;
        }
        if (arrayA.length > arrayB.length) {
            final T[] tmp = arrayA;
            arrayA = arrayB;
            arrayB = tmp;
        }
        boolean flag;
        for (final T a : arrayA) {
            flag = false;
            for (final T b : arrayB) {
                if (a.equals(b)) {
                    flag = true;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }


    // ==========================================================================
    // primitive to String
    // ==========================================================================

    public static String[] floatToString(float[] values) {
        if (values == null) {
            return null;
        }

        String[] results = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = String.valueOf(values[i]);
        }
        return results;
    }

    public static String[] intToString(int[] values) {
        if (values == null) {
            return null;
        }

        String[] results = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = String.valueOf(values[i]);
        }
        return results;
    }

    public static String[] charToString(char[] values) {
        if (values == null) {
            return null;
        }

        String[] results = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = String.valueOf(values[i]);
        }
        return results;
    }

    public static String[] doubleToString(double[] values) {
        if (values == null) {
            return null;
        }

        String[] results = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = String.valueOf(values[i]);
        }
        return results;
    }

    public static String[] longToString(long[] values) {
        if (values == null) {
            return null;
        }

        String[] results = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = String.valueOf(values[i]);
        }
        return results;
    }

    public static String[] booleanToString(boolean[] values) {
        if (values == null) {
            return null;
        }

        String[] results = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = String.valueOf(values[i]);
        }
        return results;
    }

    public static String[] byteToString(byte[] values) {
        if (values == null) {
            return null;
        }

        String[] results = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = String.valueOf(values[i]);
        }
        return results;
    }

    public static String[] shortToString(short[] values) {
        if (values == null) {
            return null;
        }

        String[] results = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = String.valueOf(values[i]);
        }
        return results;
    }
}
