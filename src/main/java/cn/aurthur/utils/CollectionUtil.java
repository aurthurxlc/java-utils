package cn.aurthur.utils;

import com.bqteam.basetool.sdk.core.lang.able.Keyable;
import com.bqteam.basetool.sdk.core.lang.collection.ArrayHashMap;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.*;

/**
 * 有关集合处理的工具类，通过静态方法消除泛型编译警告。<br>
 * hasItems 是否有元素 <br>
 * asList 创建List实例 <br>
 * createArrayList 创建ArrayList实例 <br>
 * createLinkedList 创建LinkedList实例 <br>
 * createHashSet 创建HashSet实例 <br>
 * createLinkedHashSet 创建LinkedHashSet实例 <br>
 * createTreeSet 创建TreeSet实例 <br>
 * createHashMap 创建HashMap实例 <br>
 * createLinkedHashMap 创建LinkedHashMap实例 <br>
 * createArrayHashMap 创建ArrayHashMap实例（自己实现） <br>
 * createConcurrentMap 创建ConcurrentMap实例 <br>
 * createEnumSet 创建EnumSet实例 <br>
 * createTreeMap 创建TreeMap实例 <br>
 * createIdentityHashMap 创建IdentityHashMap实例 <br>
 * createEnumMap 创建EnumMap实例 <br>
 * createPriorityQueue 创建PriorityQueue实例 <br>
 * createArrayDeque 创建ArrayDeque实例 <br>
 * createBitSet 创建BitSet实例 <br>
 * createConcurrentSkipListMap 创建ConcurrentSkipListMap实例 <br>
 * createConcurrentSkipListSet 创建ConcurrentSkipListSet实例 <br>
 * createConcurrentLinkedQueue 创建ConcurrentLinkedQueue实例 <br>
 * createCopyOnWriteArrayList 创建CopyOnWriteArrayList实例 <br>
 * createCopyOnWriteArraySet 创建CopyOnWriteArraySet实例 <br>
 * createBlockingQueue 创建BlockingQueue实例 <br>
 * list2Map List 转 Map（需实现 Keyable） <br>
 * collectId 收集ID（需实现 Keyable） <br>
 * merge 集合合并 <br>
 * intersection 集合交集 <br>
 * subtract 补集（set1 - set2） <br>
 * union 并集 <br>
 * concatSuper 连接 <br>
 * concat 连接
 */
public abstract class CollectionUtil {
    public CollectionUtil() {
        throw new AssertionError("工具类不允许实例化");
    }
    // ==========================================================================
    // 是否有元素
    // ==========================================================================

    /**
     * 判断<code>Enumeration</code>是否有元素
     *
     * @param enums ## @see Enumeration
     * @return 如果有元素, 则返回<code>true</code>
     */
    public static boolean hasItems(Enumeration<?> enums) {
        return (enums != null) && (enums.hasMoreElements());
    }

    /**
     * 判断<code>Iterator</code>是否有元素
     *
     * @param items ## @see Iterator
     * @return 如果有元素, 则返回<code>true</code>
     */
    public static boolean hasItems(Iterator<?> items) {
        return (items != null) && (items.hasNext());
    }

    /**
     * 创建一个<code>List</code>。
     * <div>
     * 和{@code createArrayList(args)}不同，本方法会返回一个不可变长度的列表，且性能高于 {@code createArrayList(args)}。
     * </div>
     */
    public static <T> List<T> asList(T... args) {
        if (args == null || args.length == 0) {
            return Collections.emptyList();
        }

        return Arrays.asList(args);
    }

    // ==========================================================================
    // 创建ArrayList实例
    // ==========================================================================

    /**
     * 创建<code>ArrayList</code>实例
     *
     * @param <E>
     * @return <code>ArrayList</code>实例
     */
    public static <E> ArrayList<E> createArrayList() {
        return new ArrayList<>();
    }

    /**
     * 创建<code>ArrayList</code>实例
     *
     * @param <E>
     * @param initialCapacity 初始化容量
     * @return <code>ArrayList</code>实例
     */
    public static <E> ArrayList<E> createArrayList(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
    }

    /**
     * 创建<code>ArrayList</code>实例
     *
     * @param <E>
     * @param collection 集合 @see Collection
     * @return <code>ArrayList</code>实例
     */
    public static <E> ArrayList<E> createArrayList(Collection<? extends E> collection) {
        if (collection == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(collection);
    }

    /**
     * 创建<code>ArrayList</code>实例
     *
     * @param iter 迭代器 @see Iterable
     * @return <code>ArrayList</code>实例
     */
    @SuppressWarnings("unchecked")
    public static <E> ArrayList<E> createArrayList(Iterable<? extends E> iter) {
        if (iter instanceof Collection<?>) {
            return new ArrayList<>((Collection<? extends E>) iter);
        }
        ArrayList<E> list = new ArrayList<>();
        iterableToCollection(iter, list);
        list.trimToSize();
        return list;
    }

    /**
     * 创建<code>ArrayList</code>实例
     *
     * @param args 构建对象集
     * @return <code>ArrayList</code>实例
     */
    public static <T, V extends T> ArrayList<T> createArrayList(V... args) {
        if (args == null || args.length == 0) {
            return new ArrayList<>();
        }

        ArrayList<T> list = new ArrayList<>(args.length);
        list.addAll(Arrays.asList(args));

        return list;

    }

    // ==========================================================================
    // 创建LinkedList实例
    // ==========================================================================

    /**
     * 创建<code>LinkedList</code>实例
     *
     * @param <E>
     * @return <code>LinkedList</code>实例
     */
    public static <E> LinkedList<E> createLinkedList() {
        return new LinkedList<>();
    }

    /**
     * 创建<code>LinkedList</code>实例
     *
     * @param collection 集合 @see Collection
     * @return <code>LinkedList</code>实例
     */
    public static <E> LinkedList<E> createLinkedList(Collection<? extends E> collection) {
        if (collection == null) {
            return new LinkedList<>();
        }

        return new LinkedList<>(collection);
    }

    /**
     * 创建<code>LinkedList</code>实例
     *
     * @param c 迭代器 @see Iterable
     * @return <code>LinkedList</code>实例
     */
    public static <T> LinkedList<T> createLinkedList(Iterable<? extends T> c) {
        LinkedList<T> list = new LinkedList<>();

        iterableToCollection(c, list);

        return list;
    }

    /**
     * 创建<code>LinkedList</code>实例
     *
     * @param args 构建对象集
     * @return <code>LinkedList</code>实例
     */
    public static <T, V extends T> LinkedList<T> createLinkedList(V... args) {
        LinkedList<T> list = new LinkedList<>();

        if (args != null) {
            list.addAll(Arrays.asList(args));
        }

        return list;
    }


    // ==========================================================================
    // 创建HashSet实例
    // ==========================================================================

    /**
     * 创建<code>HashSet</code>实例
     *
     * @param <E>
     * @return <code>HashSet</code>实例
     */
    public static <E> HashSet<E> createHashSet() {
        return new HashSet<>();
    }

    /**
     * 创建<code>HashSet</code>实例
     *
     * @param <E>
     * @param initialCapacity 初始化容量
     * @return <code>HashSet</code>实例
     */
    public static <E> HashSet<E> createHashSet(int initialCapacity) {
        return new HashSet<>(initialCapacity);
    }

    /**
     * 创建<code>HashSet</code>实例
     *
     * @param collection 集合
     * @return <code>HashSet</code>实例
     */
    public static <E> HashSet<E> createHashSet(Collection<? extends E> collection) {
        if (collection == null) {
            return new HashSet<>();
        }
        return new HashSet<>(collection);
    }

    /**
     * 创建<code>HashSet</code>实例
     *
     * @param args 传入参数
     * @return <code>HashSet</code>实例
     */
    public static <E, O extends E> HashSet<E> createHashSet(O... args) {
        if (args == null || args.length == 0) {
            return new HashSet<>();
        }

        HashSet<E> set = new HashSet<>(args.length);
        set.addAll(Arrays.asList(args));

        return set;
    }

    /**
     * 创建一个<code>HashSet</code>。
     *
     * @param iter 迭代器 @see Iterable
     * @return <code>HashSet</code>实例
     */
    @SuppressWarnings("unchecked")
    public static <T> HashSet<T> createHashSet(Iterable<? extends T> iter) {
        HashSet<T> set;

        if (iter instanceof Collection<?>) {
            set = new HashSet<>((Collection<? extends T>) iter);
        } else {
            set = new HashSet<>();
            iterableToCollection(iter, set);
        }

        return set;
    }

    // ==========================================================================
    // 创建LinkedHashSet实例
    // ==========================================================================

    /**
     * 创建<code>LinkedHashSet</code>实例
     *
     * @param <E>
     * @return <code>LinkedHashSet</code>实例
     */
    public static <E> LinkedHashSet<E> createLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    /**
     * 创建一个<code>LinkedHashSet</code>。
     */
    public static <T, V extends T> LinkedHashSet<T> createLinkedHashSet(V... args) {
        if (args == null || args.length == 0) {
            return new LinkedHashSet<>();
        }
        LinkedHashSet<T> set = new LinkedHashSet<>(args.length);

        set.addAll(Arrays.asList(args));

        return set;
    }

    /**
     * 创建一个<code>LinkedHashSet</code>。
     */
    @SuppressWarnings("unchecked")
    public static <T> LinkedHashSet<T> createLinkedHashSet(Iterable<? extends T> iter) {
        LinkedHashSet<T> set;

        if (iter instanceof Collection<?>) {
            set = new LinkedHashSet<>((Collection<? extends T>) iter);
        } else {
            set = new LinkedHashSet<>();
            iterableToCollection(iter, set);
        }

        return set;
    }

    // ==========================================================================
    // 创建TreeSet实例
    // ==========================================================================

    /**
     * 创建一个<code>TreeSet</code>。
     */
    @SuppressWarnings("unchecked")
    public static <T, V extends T> TreeSet<T> createTreeSet(V... args) {
        return (TreeSet<T>) createTreeSet(null, args);
    }

    /**
     * 创建一个<code>TreeSet</code>。
     */
    public static <T> TreeSet<T> createTreeSet(Iterable<? extends T> c) {
        return createTreeSet(null, c);
    }

    /**
     * 创建一个<code>TreeSet</code>。
     */
    public static <T> TreeSet<T> createTreeSet(Comparator<? super T> comparator) {
        return new TreeSet<>(comparator);
    }

    /**
     * 创建一个<code>TreeSet</code>。
     */
    public static <T, V extends T> TreeSet<T> createTreeSet(Comparator<? super T> comparator, V... args) {
        TreeSet<T> set = new TreeSet<>(comparator);

        if (args != null) {
            set.addAll(Arrays.asList(args));
        }

        return set;
    }

    /**
     * 创建一个<code>TreeSet</code>。
     */
    public static <T> TreeSet<T> createTreeSet(Comparator<? super T> comparator, Iterable<? extends T> c) {
        TreeSet<T> set = new TreeSet<>(comparator);

        iterableToCollection(c, set);

        return set;
    }

    /**
     * 创建<code>TreeSet</code>实例
     *
     * @param <E>
     * @param set 排序的散列 @see SortedSet
     * @return <code>TreeSet</code>实例
     */
    public static <E> TreeSet<E> createTreeSet(SortedSet<E> set) {
        if (set == null) {
            return new TreeSet<>();
        }

        return new TreeSet<>(set);
    }


    // ==========================================================================
    // 创建HashMap实例
    // ==========================================================================

    /**
     * 创建<code>HashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @return <code>HashMap</code>实例
     */
    public static <K, V> HashMap<K, V> createHashMap() {
        return new HashMap<>();
    }

    /**
     * 创建<code>HashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param initialCapacity 初始化容量
     * @return <code>HashMap</code>实例
     */
    public static <K, V> HashMap<K, V> createHashMap(int initialCapacity) {
        return new HashMap<>(initialCapacity);
    }

    /**
     * 创建<code>HashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param initialCapacity 初始化容量
     * @param loadFactor      加载因子
     * @return <code>HashMap</code>实例
     */
    public static <K, V> HashMap<K, V> createHashMap(int initialCapacity, float loadFactor) {
        return new HashMap<>(initialCapacity, loadFactor);
    }

    public static <K, V> HashMap<K, V> synchronizedMap() {
        return (HashMap<K, V>) Collections.synchronizedMap(new HashMap<K, V>());
    }

    /**
     * 创建<code>HashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param map 映射表 @see Map
     * @return <code>HashMap</code>实例
     */
    public static <K, V> HashMap<K, V> createHashMap(Map<? extends K, ? extends V> map) {
        return new HashMap<>(map);
    }


    // ==========================================================================
    // 创建LinkedHashMap实例
    // ==========================================================================

    /**
     * 创建<code>LinkedHashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @return <code>LinkedHashMap</code>实例
     */
    public static <K, V> LinkedHashMap<K, V> createLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    /**
     * 创建<code>LinkedHashMap</code>实例
     *
     * @param initialCapacity 初始化容量
     * @return <code>LinkedHashMap</code>实例
     */
    public static <K, V> LinkedHashMap<K, V> createLinkedHashMap(int initialCapacity) {
        return new LinkedHashMap<>(initialCapacity);
    }

    /**
     * 创建<code>LinkedHashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param initialCapacity 初始化容量
     * @param loadFactor      加载因子
     * @return <code>LinkedHashMap</code>实例
     */
    public static <K, V> LinkedHashMap<K, V> createLinkedHashMap(int initialCapacity, float loadFactor) {
        return new LinkedHashMap<>(initialCapacity, loadFactor);
    }

    /**
     * 创建<code>LinkedHashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param map 映射表 @see Map
     * @return <code>HashMap</code>实例
     */
    public static <K, V> LinkedHashMap<K, V> createLinkedHashMap(Map<? extends K, ? extends V> map) {
        if (map == null) {
            return new LinkedHashMap<>();
        }

        return new LinkedHashMap<>(map);
    }

    // ==========================================================================
    // 创建ArrayHashMap实例
    // ==========================================================================

    /**
     * 创建一个<code>ArrayHashMap</code>。
     */
    public static <K, V> ArrayHashMap<K, V> createArrayHashMap() {
        return new ArrayHashMap<>();
    }

    /**
     * 创建一个<code>ArrayHashMap</code>。
     */
    public static <K, V> ArrayHashMap<K, V> createArrayHashMap(int initialCapacity) {
        return new ArrayHashMap<>(initialCapacity);
    }

    // ==========================================================================
    // 创建ConcurrentMap实例
    // ==========================================================================

    /**
     * 创建<code>ConcurrentMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @return <code>ConcurrentMap</code>实例
     */
    public static <K, V> ConcurrentMap<K, V> createConcurrentMap() {
        return new ConcurrentHashMap<>();
    }

    /**
     * 创建<code>ConcurrentMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param map 映射表 @see Map
     * @return <code>ConcurrentMap</code>实例
     */
    public static <K, V> ConcurrentMap<K, V> createConcurrentMap(Map<? extends K, ? extends V> map) {
        if (map == null) {
            return null;
        }

        return new ConcurrentHashMap<>(map);
    }

    /**
     * 创建<code>ConcurrentMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param initialCapacity 初始化容量
     * @return <code>ConcurrentMap</code>实例
     */
    public static <K, V> ConcurrentMap<K, V> createConcurrentMap(int initialCapacity) {
        return new ConcurrentHashMap<>(initialCapacity);
    }

    /**
     * 创建<code>ConcurrentMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param initialCapacity 初始化容量
     * @param loadFactor      加载因子
     * @return <code>ConcurrentMap</code>实例
     */
    public static <K, V> ConcurrentMap<K, V> createConcurrentMap(int initialCapacity, float loadFactor) {
        return new ConcurrentHashMap<>(initialCapacity, loadFactor);
    }

    // ==========================================================================
    // 创建EnumSet实例
    // ==========================================================================

    /**
     * 创建<code>EnumSet</code>实例
     *
     * @return <code>EnumSet</code>实例
     */
    public static <E extends Enum<E>> EnumSet<E> createEnumSet(Collection<E> c) {
        if (c == null) {
            return null;
        }

        return EnumSet.copyOf(c);
    }

    /**
     * 创建<code>EnumSet</code>实例
     *
     * @return <code>EnumSet</code>实例
     */
    public static <E extends Enum<E>> EnumSet<E> createEnumSet(Class<E> elementType) {
        if (elementType == null) {
            return null;
        }

        return EnumSet.allOf(elementType);
    }

    // ==========================================================================
    // 创建TreeMap实例
    // ==========================================================================

    /**
     * 创建<code>TreeMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @return <code>TreeMap</code>实例
     */
    public static <K, V> TreeMap<K, V> createTreeMap() {
        return new TreeMap<>();
    }

    /**
     * 创建<code>TreeMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param comparator 比较器 @see Comparator
     * @return <code>TreeMap</code>实例
     */
    public static <K, V> TreeMap<K, V> createTreeMap(Comparator<? super K> comparator) {
        if (comparator == null) {
            return null;
        }

        return new TreeMap<>(comparator);
    }

    /**
     * 创建<code>TreeMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param map 映射表 @see Map
     * @return <code>TreeMap</code>实例
     */
    public static <K, V> TreeMap<K, V> createTreeMap(Map<? extends K, ? extends V> map) {
        if (map == null) {
            return null;
        }

        return new TreeMap<>(map);
    }

    /**
     * 创建<code>TreeMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param map 排序的映射表 @see Map
     * @return <code>TreeMap</code>实例
     */
    public static <K, V> TreeMap<K, V> createTreeMap(SortedMap<K, ? extends V> map) {
        if (map == null) {
            return null;
        }

        return new TreeMap<>(map);
    }

    // ==========================================================================
    // 创建WeakHashMap实例
    // ==========================================================================

    /**
     * 创建<code>WeakHashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @return <code>WeakHashMap</code>实例
     */
    public static <K, V> WeakHashMap<K, V> createWeakHashMap() {
        return new WeakHashMap<>();
    }

    /**
     * 创建<code>WeakHashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param initialCapacity 初始化容量
     * @return <code>WeakHashMap</code>实例
     */
    public static <K, V> WeakHashMap<K, V> createWeakHashMap(int initialCapacity) {
        return new WeakHashMap<>(initialCapacity);
    }

    /**
     * 创建<code>WeakHashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param map 映射表 @see Map
     * @return <code>WeakHashMap</code>实例
     */
    public static <K, V> WeakHashMap<K, V> createWeakHashMap(Map<? extends K, ? extends V> map) {
        if (map == null) {
            return new WeakHashMap<>();
        }

        return new WeakHashMap<>(map);
    }

    /**
     * 创建<code>WeakHashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param initialCapacity 初始化容量
     * @param loadFactor      加载因子
     * @return <code>WeakHashMap</code>实例
     */
    public static <K, V> WeakHashMap<K, V> createWeakHashMap(int initialCapacity, float loadFactor) {
        return new WeakHashMap<>(initialCapacity, loadFactor);
    }

    // ==========================================================================
    // 创建IdentityHashMap实例
    // ==========================================================================

    /**
     * 创建<code>IdentityHashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @return <code>IdentityHashMap</code>实例
     */
    public static <K, V> IdentityHashMap<K, V> createIdentityHashMap() {
        return new IdentityHashMap<>();
    }

    /**
     * 创建<code>IdentityHashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param initialCapacity 初始化容量
     * @return <code>IdentityHashMap</code>实例
     */
    public static <K, V> IdentityHashMap<K, V> createIdentityHashMap(int initialCapacity) {
        return new IdentityHashMap<>(initialCapacity);
    }

    /**
     * 创建<code>IdentityHashMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param map 映射表 @see Map
     * @return <code>IdentityHashMap</code>实例
     */
    public static <K, V> IdentityHashMap<K, V> createIdentityHashMap(Map<? extends K, ? extends V> map) {
        if (map == null) {
            return null;
        }

        return new IdentityHashMap<>(map);
    }

    // ==========================================================================
    // 创建EnumMap实例
    // ==========================================================================


    /**
     * 创建<code>EnumMap</code>实例
     *
     * @return <code>EnumMap</code>实例
     */
    public static <K extends Enum<K>, V> EnumMap<K, V> createEnumMap(Class<K> keyType) {
        if (keyType == null) {
            return null;
        }

        return new EnumMap<>(keyType);
    }

    /**
     * 创建<code>EnumMap</code>实例
     *
     * @return <code>EnumMap</code>实例
     */
    public static <K extends Enum<K>, V> EnumMap<K, V> createEnumMap(Map<K, ? extends V> map) {
        if (map == null) {
            return null;
        }

        return new EnumMap<>(map);
    }

    // ==========================================================================
    // 创建PriorityQueue实例
    // ==========================================================================

    /**
     * 创建<code>PriorityQueue</code>实例
     *
     * @param <E>
     * @return <code>PriorityQueue</code>实例
     */
    public static <E> PriorityQueue<E> createPriorityQueue() {
        return new PriorityQueue<>();
    }

    /**
     * 创建<code>PriorityQueue</code>实例
     *
     * @param <E>
     * @param initialCapacity 初始化容量
     * @return <code>PriorityQueue</code>实例
     */
    public static <E> PriorityQueue<E> createPriorityQueue(int initialCapacity) {
        return new PriorityQueue<>(initialCapacity);
    }

    /**
     * 创建<code>PriorityQueue</code>实例
     *
     * @param <E>
     * @param collection 集合 @see Collection
     * @return <code>PriorityQueue</code>实例
     */
    public static <E> PriorityQueue<E> createPriorityQueue(Collection<? extends E> collection) {
        if (collection == null) {
            return null;
        }

        return new PriorityQueue<>(collection);
    }

    /**
     * 创建<code>PriorityQueue</code>实例
     *
     * @param <E>
     * @param initialCapacity 初始化容量
     * @param comparator      比较器 @see Comparator
     * @return <code>PriorityQueue</code>实例
     */
    public static <E> PriorityQueue<E> createPriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        if (comparator == null) {
            return new PriorityQueue<>(initialCapacity);
        }

        return new PriorityQueue<>(initialCapacity, comparator);
    }

    /**
     * 创建<code>PriorityQueue</code>实例
     *
     * @param <E>
     * @param queue 队列 @see PriorityQueue
     * @return <code>PriorityQueue</code>实例
     */
    public static <E> PriorityQueue<E> createPriorityQueue(PriorityQueue<? extends E> queue) {
        if (queue == null) {
            return null;
        }

        return new PriorityQueue<>(queue);
    }

    /**
     * 创建<code>PriorityQueue</code>实例
     *
     * @param <E>
     * @param set 排序的散列 @see SortedSet
     * @return <code>PriorityQueue</code>实例
     */
    public static <E> PriorityQueue<E> createPriorityQueue(SortedSet<? extends E> set) {
        if (set == null) {
            return null;
        }

        return new PriorityQueue<>(set);
    }

    // ==========================================================================
    // 创建ArrayDeque实例
    // ==========================================================================

    /**
     * 创建<code>ArrayDeque</code>实例
     *
     * @param <E>
     * @return <code>ArrayDeque</code>实例
     */
    public static <E> ArrayDeque<E> createArrayDeque() {
        return new ArrayDeque<>();
    }

    /**
     * 创建<code>ArrayDeque</code>实例
     *
     * @param <E>
     * @param collection 集合 @see Collection
     * @return <code>ArrayDeque</code>实例
     */
    public static <E> ArrayDeque<E> createArrayDeque(Collection<? extends E> collection) {
        if (collection == null) {
            return null;
        }

        return new ArrayDeque<>(collection);
    }

    /**
     * 创建<code>ArrayDeque</code>实例
     *
     * @param <E>
     * @param initialCapacity 初始化容量
     * @return <code>ArrayDeque</code>实例
     */
    public static <E> ArrayDeque<E> createArrayDeque(int initialCapacity) {
        return new ArrayDeque<>(initialCapacity);
    }

    // ==========================================================================
    // 创建BitSet实例
    // ==========================================================================

    /**
     * 创建<code>BitSet</code>实例
     *
     * @param <E>
     * @return <code>BitSet</code>实例
     */
    public static <E> BitSet createBitSet() {
        return new BitSet();
    }

    /**
     * 创建<code>BitSet</code>实例
     *
     * @param <E>
     * @param initialCapacity 初始化容量
     * @return <code>BitSet</code>实例
     */
    public static <E> BitSet createBitSet(int initialCapacity) {
        return new BitSet();
    }

    // ==========================================================================
    // 创建ConcurrentSkipListMap实例
    // ==========================================================================

    /**
     * 创建<code>ConcurrentSkipListMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @return <code>ConcurrentSkipListMap</code>实例
     */
    public static <K, V> ConcurrentSkipListMap<K, V> createConcurrentSkipListMap() {
        return new ConcurrentSkipListMap<>();
    }

    /**
     * 创建<code>ConcurrentSkipListMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param comparator 比较器 @see Comparator
     * @return <code>ConcurrentSkipListMap</code>实例
     */
    public static <K, V> ConcurrentSkipListMap<K, V> createConcurrentSkipListMap(Comparator<? super K> comparator) {
        if (comparator == null) {
            return new ConcurrentSkipListMap<>();
        }

        return new ConcurrentSkipListMap<>(comparator);
    }

    /**
     * 创建<code>ConcurrentSkipListMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param map 映射表 @see Map
     * @return <code>ConcurrentSkipListMap</code>实例
     */
    public static <K, V> ConcurrentSkipListMap<K, V> createConcurrentSkipListMap(Map<? extends K, ? extends V> map) {
        if (map == null) {
            return new ConcurrentSkipListMap<>();
        }

        return new ConcurrentSkipListMap<>(map);
    }

    /**
     * 创建<code>ConcurrentSkipListMap</code>实例
     *
     * @param <K>
     * @param <V>
     * @param map 排序的映射表 @see SortedMap
     * @return <code>ConcurrentSkipListMap</code>实例
     */
    public static <K, V> ConcurrentSkipListMap<K, V> createConcurrentSkipListMap(SortedMap<? extends K, ? extends V> map) {
        if (map == null) {
            return new ConcurrentSkipListMap<>();
        }

        return new ConcurrentSkipListMap<K, V>(map);
    }

    /**
     * 创建<code>ConcurrentSkipListSet</code>实例
     *
     * @return <code>ConcurrentSkipListSet</code>实例
     */
    public static <E> ConcurrentSkipListSet<E> createConcurrentSkipListSet() {
        return new ConcurrentSkipListSet<>();
    }

    // ==========================================================================
    // 创建ConcurrentSkipListSet实例
    // ==========================================================================

    /**
     * 创建<code>ConcurrentSkipListSet</code>实例
     *
     * @param collection 集合 @see Collection
     * @return <code>ConcurrentSkipListSet</code>实例
     */
    public static <E> ConcurrentSkipListSet<E> createConcurrentSkipListSet(Collection<? extends E> collection) {
        if (collection == null) {
            return new ConcurrentSkipListSet<>();
        }

        return new ConcurrentSkipListSet<>(collection);
    }

    /**
     * 创建<code>ConcurrentSkipListSet</code>实例
     *
     * @param comparator 比较器 @see Comparator
     * @return <code>ConcurrentSkipListSet</code>实例
     */
    public static <E> ConcurrentSkipListSet<E> createConcurrentSkipListSet(Comparator<? super E> comparator) {
        if (comparator == null) {
            return new ConcurrentSkipListSet<>();
        }

        return new ConcurrentSkipListSet<>(comparator);
    }

    /**
     * 创建<code>ConcurrentSkipListSet</code>实例
     *
     * @param set 可排序的散列 @see SortedSet
     * @return <code>ConcurrentSkipListSet</code>实例
     */
    public static <E> ConcurrentSkipListSet<E> createConcurrentSkipListSet(SortedSet<E> set) {
        if (set == null) {
            return new ConcurrentSkipListSet<>();
        }

        return new ConcurrentSkipListSet<>(set);
    }

    // ==========================================================================
    // 创建ConcurrentLinkedQueue实例
    // ==========================================================================

    /**
     * 创建<code>ConcurrentLinkedQueue</code>实例
     *
     * @param <E>
     * @return <code>ConcurrentLinkedQueue</code>实例
     */
    public static <E> ConcurrentLinkedQueue<E> createConcurrentLinkedQueue() {
        return new ConcurrentLinkedQueue<>();
    }

    /**
     * 创建<code>ConcurrentLinkedQueue</code>实例
     *
     * @param <E>
     * @param collection 集合 @see Collection
     * @return <code>ConcurrentLinkedQueue</code>实例
     */
    public static <E> ConcurrentLinkedQueue<E> createConcurrentLinkedQueue(Collection<? extends E> collection) {
        if (collection == null) {
            return new ConcurrentLinkedQueue<>();
        }

        return new ConcurrentLinkedQueue<>(collection);
    }

    // ==========================================================================
    // 创建CopyOnWriteArrayList实例
    // ==========================================================================

    /**
     * 创建<code>CopyOnWriteArrayList</code>实例
     *
     * @param <E>
     * @return <code>CopyOnWriteArrayList</code>实例
     */
    public static <E> CopyOnWriteArrayList<E> createCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<>();
    }

    /**
     * 创建<code>CopyOnWriteArrayList</code>实例
     *
     * @param <E>
     * @param collection 集合 @see Collection
     * @return <code>CopyOnWriteArrayList</code>实例
     */
    public static <E> CopyOnWriteArrayList<E> createCopyOnWriteArrayList(Collection<? extends E> collection) {
        if (collection == null) {
            return new CopyOnWriteArrayList<>();
        }

        return new CopyOnWriteArrayList<>();
    }

    /**
     * 创建<code>CopyOnWriteArrayList</code>实例
     *
     * @param <E>
     * @param toCopyIn 创建一个保存给定数组的副本的数组
     * @return <code>CopyOnWriteArrayList</code>实例
     */
    public static <E> CopyOnWriteArrayList<E> createCopyOnWriteArrayList(E[] toCopyIn) {
        if (toCopyIn == null) {
            return new CopyOnWriteArrayList<>();
        }

        return new CopyOnWriteArrayList<>(toCopyIn);
    }

    // ==========================================================================
    // 创建CopyOnWriteArraySet实例
    // ==========================================================================

    /**
     * 创建<code>CopyOnWriteArraySet</code>实例
     *
     * @param <E>
     * @return <code>CopyOnWriteArraySet</code>实例
     */
    public static <E> CopyOnWriteArraySet<E> createCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet<>();
    }

    /**
     * 创建<code>CopyOnWriteArraySet</code>实例
     *
     * @param <E>
     * @param collection 集合 @see Collection
     * @return <code>CopyOnWriteArraySet</code>实例
     */
    public static <E> CopyOnWriteArraySet<E> createCopyOnWriteArraySet(Collection<? extends E> collection) {
        return new CopyOnWriteArraySet<>();
    }

    // ==========================================================================
    // 创建BlockingQueue实例
    // ==========================================================================

    /**
     * 创建<code>LinkedBlockingQueue</code>实例
     */
    public static <E> BlockingQueue<E> createLinkedBlockingQueue() {
        return new LinkedBlockingQueue<>();
    }

    /**
     * 创建<code>LinkedBlockingQueue</code>实例
     */
    public static <E> BlockingQueue<E> createLinkedBlockingQueue(int capacity) {
        return new LinkedBlockingQueue<>(capacity);
    }

    /**
     * 创建<code>LinkedBlockingQueue</code>实例
     */
    public static <E> BlockingQueue<E> createLinkedBlockingQueue(Collection<? extends E> collection) {
        if (collection == null) {
            return new LinkedBlockingQueue<>();
        }
        return new LinkedBlockingQueue<>(collection);
    }

    // ==========================================================================
    // 常用转换。
    // ==========================================================================

    /**
     * List 转 Map
     */
    public static <T extends Keyable<K>, K> Map<K, T> list2Map(List<T> list) {
        if (list == null) {
            return null;
        }

        Map<K, T> result = createHashMap(list.size());
        for (T data : list) {
            result.put(data.getId(), data);
        }
        return result;
    }

    /**
     * 收集ID
     */
    public static <T extends Keyable<K>, K> Collection<K> collectId(List<T> list) {
        if (list == null) {
            return null;
        }

        Collection<K> result = createHashSet(list.size());
        for (T data : list) {
            result.add(data.getId());
        }
        return result;
    }

    /**
     * 集合合并
     */
    public static <T extends Keyable<K>, K, O extends Keyable<K>> void merge(List<T> list, Map<K, O> map,
                                                                             String... fields) {
        if (EmptyUtil.isEmpty(list) || EmptyUtil.isEmpty(map) || EmptyUtil.isEmpty(fields)) {
            return;
        }

        for (T data : list) {
            O from = map.get(data.getId());
            if (from == null) {
                continue;
            }
            for (String field : fields) {
                Object value = ReflectionUtil.readField(field, from);
                ReflectionUtil.writeField(data, field, value);
            }
        }

    }

    /**
     * 集合合并
     */
    public static <T extends Keyable<K>, K, O extends Keyable<K>> void merge(List<T> list, Map<K, O> map,
                                                                             Field... fields) {
        if (EmptyUtil.isEmpty(list) || EmptyUtil.isEmpty(map) || EmptyUtil.isEmpty(fields)) {
            return;
        }

        for (T data : list) {
            O from = map.get(data.getId());
            if (from == null) {
                continue;
            }
            for (Field field : fields) {
                Object value = ReflectionUtil.readField(field, from);
                ReflectionUtil.writeField(data, field.getName(), value);
            }
        }

    }

    /**
     * 集合合并
     */
    public static <T extends Keyable<K>, K, O extends Keyable<K>> void merge(Class<T> clazz, List<T> list,
                                                                             Map<K, O> map, Field... fields) {
        if (EmptyUtil.isEmpty(list) || EmptyUtil.isEmpty(map) || EmptyUtil.isEmpty(fields) || clazz == null) {
            return;
        }

        Field[] allFields = ReflectionUtil.getAllInstanceFields(clazz);
        Set<Field> firstFieldSet = createHashSet(fields);
        Set<Field> secFieldSet = createHashSet(allFields);
        Set<Field> intersection = intersection(firstFieldSet, secFieldSet);

        for (T data : list) {
            O from = map.get(data.getId());
            if (from == null) {
                continue;
            }

            for (Field field : intersection) {
                Object value = ReflectionUtil.readField(field, from);
                ReflectionUtil.writeField(data, field.getName(), value);
            }
        }

    }

    // ==========================================================================
    // 集合运算。
    // ==========================================================================

    /**
     * 集合交集
     */
    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        if (set1 == null || set2 == null) {
            return null;
        }

        if (set1.isEmpty() || set2.isEmpty()) {
            return Collections.emptySet();
        }

        Set<T> result = CollectionUtil.createHashSet();
        Set<T> smaller = (set1.size() > set2.size()) ? set2 : set1;
        Set<T> bigger = (smaller == set2) ? set1 : set2;

        for (T value : smaller) {
            if (bigger.contains(value)) {
                result.add(value);
            }
        }
        return result;
    }

    /**
     * 补集 set1 - set2
     */
    public static <T> Set<T> subtract(Set<T> set1, Set<T> set2) {
        if (set1 == null || set2 == null) {
            return null;
        }
        Set<T> result = createHashSet(set1);
        result.removeAll(set2);
        return result;
    }

    /**
     * 并集
     */
    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        if (EmptyUtil.isEmpty(set1)) {
            return set2;
        }
        if (EmptyUtil.isEmpty(set2)) {
            return set1;
        }

        Set<T> result = createHashSet(set1);
        result.addAll(set2);
        return result;
    }

    /**
     * 连接
     */
    public static <T> List<? extends T> concatSuper(List<? extends T> collection1, List<? extends T> collection2) {
        if (EmptyUtil.isEmpty(collection1)) {
            return collection2;
        }
        if (EmptyUtil.isEmpty(collection2)) {
            return collection1;
        }
        List<T> result = createArrayList(collection1.size() + collection2.size());
        result.addAll(collection1);
        result.addAll(collection2);
        return result;
    }

    /**
     * 连接
     */
    public static <T> List<T> concat(List<T> collection1, List<T> collection2) {
        if (EmptyUtil.isEmpty(collection1)) {
            return collection2;
        }
        if (EmptyUtil.isEmpty(collection2)) {
            return collection1;
        }

        collection1.addAll(collection2);
        return collection1;
    }

    // ==========================================================================
    // private
    // ==========================================================================

    private static <E> void iterableToCollection(Iterable<? extends E> iter, Collection<E> list) {
        if (iter == null) {
            return;
        }

        for (E element : iter) {
            list.add(element);
        }
    }

}
