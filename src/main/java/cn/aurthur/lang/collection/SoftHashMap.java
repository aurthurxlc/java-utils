package cn.aurthur.lang.collection;

import cn.aurthur.utils.CollectionUtil;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.*;

/**
 * A hashtable-based <tt>Map</tt> implementation with <em>weak keys</em>. An
 * entry in a <tt>WeakHashMap</tt> will
 * automatically be removed when its key is no longer in ordinary use. More
 * precisely, the presence of a mapping for a
 * given key will not prevent the key from being discarded by the garbage
 * collector, that is, made finalizable,
 * finalized, and then reclaimed. When a key has been discarded its entry is
 * effectively removed from the map, so this
 * class behaves somewhat differently from other <tt>Map</tt> implementations.
 *
 * <br>
 * Both null values and the null key are supported. This class has performance
 * characteristics similar to those of the
 * <tt>HashMap</tt> class, and has the same efficiency parameters of <em>initial
 * capacity</em> and <em>load factor</em>.
 *
 * <br>
 * Like most collection classes, this class is not synchronized. A synchronized
 * <tt>WeakHashMap</tt> may be constructed
 * using the {@link Collections#synchronizedMap Collections.synchronizedMap}
 * method.
 *
 * <br>
 * This class is intended primarily for use with key objects whose
 * <tt>equals</tt> methods test for object identity
 * using the <tt>==</tt> operator. Once such a key is discarded it can never be
 * recreated, so it is impossible to do a
 * lookup of that key in a <tt>WeakHashMap</tt> at some later time and be
 * surprised that its entry has been removed.
 * This class will work perfectly well with key objects whose <tt>equals</tt>
 * methods are not based upon object
 * identity, such as <tt>String</tt> instances. With such recreatable key
 * objects, however, the automatic removal of
 * <tt>WeakHashMap</tt> entries whose keys have been discarded may prove to be
 * confusing.
 *
 * <br>
 * The behavior of the <tt>WeakHashMap</tt> class depends in part upon the
 * actions of the garbage collector, so several
 * familiar (though not required) <tt>Map</tt> invariants do not hold for this
 * class. Because the garbage collector may
 * discard keys at any time, a <tt>WeakHashMap</tt> may behave as though an
 * unknown thread is silently removing entries.
 * In particular, even if you synchronize on a <tt>WeakHashMap</tt> instance and
 * invoke none of its mutator methods, it
 * is possible for the <tt>size</tt> method to return smaller values over time,
 * for the <tt>isEmpty</tt> method to
 * return <tt>false</tt> and then <tt>true</tt>, for the <tt>containsKey</tt>
 * method to return <tt>true</tt> and later
 * <tt>false</tt> for a given key, for the <tt>get</tt> method to return a value
 * for a given key but later return
 * <tt>null</tt>, for the <tt>put</tt> method to return <tt>null</tt> and the
 * <tt>remove</tt> method to return
 * <tt>false</tt> for a key that previously appeared to be in the map, and for
 * successive examinations of the key set,
 * the value collection, and the entry set to yield successively smaller numbers
 * of elements.
 *
 * <br>
 * Each key object in a <tt>WeakHashMap</tt> is stored indirectly as the
 * referent of a weak reference. Therefore a key
 * will automatically be removed only after the weak references to it, both
 * inside and outside of the map, have been
 * cleared by the garbage collector.
 *
 * <br>
 * <strong>Implementation note:</strong> The value objects in a
 * <tt>WeakHashMap</tt> are held by ordinary strong
 * references. Thus care should be taken to ensure that value objects do not
 * strongly refer to their own keys, either
 * directly or indirectly, since that will prevent the keys from being
 * discarded. Note that a value object may refer
 * indirectly to its key via the <tt>WeakHashMap</tt> itself; that is, a value
 * object may strongly refer to some other
 * key object whose associated value object, in turn, strongly refers to the key
 * of the first value object. One way to
 * deal with this is to wrap values themselves within <tt>SoftReferences</tt>
 * before inserting, as in:
 * <tt>m.put(key, new SoftReference(value))</tt>, and then unwrapping upon each
 * <tt>get</tt>.
 *
 * <br>
 * The iterators returned by the <tt>iterator</tt> method of the collections
 * returned by all of this class's
 * "collection view methods" are <i>fail-fast</i>: if the map is structurally
 * modified at any time after the iterator is
 * created, in any way except through the iterator's own <tt>remove</tt> method,
 * the iterator will throw a
 * {@link ConcurrentModificationException}. Thus, in the face of concurrent
 * modification, the iterator fails quickly and
 * cleanly, rather than risking arbitrary, non-deterministic behavior at an
 * undetermined time in the future.
 *
 * <br>
 * Note that the fail-fast behavior of an iterator cannot be guaranteed as it
 * is, generally speaking, impossible to make
 * any hard guarantees in the presence of unsynchronized concurrent
 * modification. Fail-fast iterators throw
 * <tt>ConcurrentModificationException</tt> on a best-effort basis. Therefore,
 * it would be wrong to write a program that
 * depended on this exception for its correctness: <i>the fail-fast behavior of
 * iterators should be used only to detect
 * bugs.</i>
 *
 * <br>
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 * @see HashMap
 * @see SoftReference
 */
public class SoftHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {

    /**
     * The default initial capacity -- MUST be a power of two.
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * The maximum capacity, used if a higher value is implicitly specified by
     * either of the constructors with
     * arguments. MUST be a power of two <= 1<<30.
     */
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load fast used when none specified in constructor.
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The table, resized as necessary. Length MUST Always be a power of two.
     */
    private Entry<K, V>[] table;

    /**
     * The number of key-value mappings contained in this weak hash map.
     */
    private int size;

    /**
     * The next size value at which to resize (capacity * load factor).
     */
    private int threshold;

    /**
     * The load factor for the hash table.
     */
    private final float loadFactor;

    /**
     * Reference queue for cleared WeakEntries
     */
    private final ReferenceQueue<K> queue = new ReferenceQueue<K>();

    /**
     * The number of times this WeakHashMap has been structurally modified.
     * Structural modifications are those that
     * change the number of mappings in the map or otherwise modify its internal
     * structure (e.g., rehash). This field is
     * used to make iterators on Collection-views of the map fail-fast.
     *
     * @see ConcurrentModificationException
     */
    private volatile int modCount;

    transient volatile Set<K> keySet = null;

    transient volatile Collection<V> values = null;

    /**
     * Constructs a new, empty <tt>WeakHashMap</tt> with the given initial capacity
     * and the given load factor.
     *
     * @param initialCapacity The initial capacity of the <tt>WeakHashMap</tt>
     * @param loadFactor      The load factor of the <tt>WeakHashMap</tt>
     * @throws IllegalArgumentException if the initial capacity is negative, or if
     *                                  the load factor is nonpositive.
     */
    @SuppressWarnings("unchecked")
    public SoftHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Initial Capacity: " + initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;

        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal Load factor: " + loadFactor);
        int capacity = 1;
        while (capacity < initialCapacity)
            capacity <<= 1;
        table = new Entry[capacity];
        this.loadFactor = loadFactor;
        threshold = (int) (capacity * loadFactor);
    }

    /**
     * Constructs a new, empty <tt>WeakHashMap</tt> with the given initial capacity
     * and the default load factor (0.75).
     *
     * @param initialCapacity The initial capacity of the <tt>WeakHashMap</tt>
     * @throws IllegalArgumentException if the initial capacity is negative
     */
    public SoftHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs a new, empty <tt>WeakHashMap</tt> with the default initial
     * capacity (16) and load factor (0.75).
     */
    @SuppressWarnings("unchecked")
    public SoftHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        threshold = (int) (DEFAULT_INITIAL_CAPACITY);
        table = new Entry[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * Constructs a new <tt>WeakHashMap</tt> with the same mappings as the specified
     * map. The <tt>WeakHashMap</tt> is
     * created with the default load factor (0.75) and an initial capacity
     * sufficient to hold the mappings in the
     * specified map.
     *
     * @param m the map whose mappings are to be placed in this map
     * @throws NullPointerException if the specified map is null
     * @since 1.3
     */
    public SoftHashMap(Map<? extends K, ? extends V> m) {
        this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1, 16), DEFAULT_LOAD_FACTOR);
        putAll(m);
    }

    // internal utilities

    /**
     * Value representing null keys inside tables.
     */
    private static final Object NULL_KEY = new Object();

    /**
     * Use NULL_KEY for key if it is null.
     */
    private static Object maskNull(Object key) {
        return (key == null ? NULL_KEY : key);
    }

    /**
     * Returns internal representation of null key back to caller as null.
     */
    private static <K> K unmaskNull(K key) {
        return (key == NULL_KEY ? null : key);
    }

    /**
     * Checks for equality of non-null reference x and possibly-null y. By default
     * uses Object.equals.
     */
    static boolean eq(Object x, Object y) {
        return x == y || x.equals(y);
    }

    /**
     * Returns index for hash code h.
     */
    static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    /**
     * Expunges stale entries from the table.
     */
    private void expungeStaleEntries() {
        Entry<?, ?> e;
        while ((e = (Entry<?, ?>) queue.poll()) != null) {
            int h = e.hash;
            int i = indexFor(h, table.length);

            Entry<K, V> prev = table[i];
            Entry<K, V> p = prev;
            while (p != null) {
                Entry<K, V> next = p.next;
                if (p == e) {
                    if (prev == e)
                        table[i] = next;
                    else
                        prev.next = next;
                    e.next = null; // Help GC
                    e.value = null; // " "
                    size--;
                    break;
                }
                prev = p;
                p = next;
            }
        }
    }

    /**
     * Returns the table after first expunging stale entries.
     */
    private Entry<K, V>[] getTable() {
        expungeStaleEntries();
        return table;
    }

    /**
     * Returns the number of key-value mappings in this map. This result is a
     * snapshot, and may not reflect unprocessed
     * entries that will be removed before next attempted access because they are no
     * longer referenced.
     */
    public int size() {
        if (size == 0)
            return 0;
        expungeStaleEntries();
        return size;
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings. This result
     * is a snapshot, and may not reflect
     * unprocessed entries that will be removed before next attempted access because
     * they are no longer referenced.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the value to which the specified key is mapped, or {@code null} if
     * this map contains no mapping for the
     * key.
     *
     * <br>
     * More formally, if this map contains a mapping from a key {@code k} to a value
     * {@code v} such that
     * {@code (key==null ? k==null : key.equals(k))}, then this method returns
     * {@code v}; otherwise it returns
     * {@code null}. (There can be at most one such mapping.)
     *
     * <br>
     * A return value of {@code null} does not <i>necessarily</i> indicate that the
     * map contains no mapping for the key;
     * it's also possible that the map explicitly maps the key to {@code null}. The
     * {@link #containsKey containsKey}
     * operation may be used to distinguish these two cases.
     *
     * @see #put(Object, Object)
     */
    public V get(Object key) {
        Object k = maskNull(key);
        int h = hash(k.hashCode());
        Entry<K, V>[] tab = getTable();
        int index = indexFor(h, tab.length);
        Entry<K, V> e = tab[index];
        while (e != null) {
            if (e.hash == h && eq(k, e.get()))
                return e.value;
            e = e.next;
        }
        return null;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified key.
     *
     * @param key The key whose presence in this map is to be tested
     * @return <tt>true</tt> if there is a mapping for <tt>key</tt>; <tt>false</tt>
     *         otherwise
     */
    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    /**
     * Returns the entry associated with the specified key in this map. Returns null
     * if the map contains no mapping for
     * this key.
     */
    Entry<K, V> getEntry(Object key) {
        Object k = maskNull(key);
        int h = hash(k.hashCode());
        Entry<K, V>[] tab = getTable();
        int index = indexFor(h, tab.length);
        Entry<K, V> e = tab[index];
        while (e != null && !(e.hash == h && eq(k, e.get())))
            e = e.next;
        return e;
    }

    /**
     * Associates the specified value with the specified key in this map. If the map
     * previously contained a mapping for
     * this key, the old value is replaced.
     *
     * @param key   key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if
     *         there was no mapping for
     *         <tt>key</tt>. (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt>
     *         with <tt>key</tt>.)
     */
    public V put(K key, V value) {
        @SuppressWarnings("unchecked")
        K k = (K) maskNull(key);
        int h = hash(k.hashCode());
        Entry<K, V>[] tab = getTable();
        int i = indexFor(h, tab.length);

        for (Entry<K, V> e = tab[i]; e != null; e = e.next) {
            if (h == e.hash && eq(k, e.get())) {
                V oldValue = e.value;
                if (value != oldValue)
                    e.value = value;
                return oldValue;
            }
        }

        modCount++;
        Entry<K, V> e = tab[i];
        tab[i] = new Entry<K, V>(k, value, queue, h, e);
        if (++size >= threshold)
            resize(tab.length * 2);
        return null;
    }

    /**
     * Rehashes the contents of this map into a new array with a larger capacity.
     * This method is called automatically
     * when the number of keys in this map reaches its threshold.
     * <br>
     * If current capacity is MAXIMUM_CAPACITY, this method does not resize the map,
     * but sets threshold to
     * Integer.MAX_VALUE. This has the effect of preventing future calls.
     *
     * @param newCapacity the new capacity, MUST be a power of two; must be greater
     *                    than current capacity unless current
     *                    capacity is MAXIMUM_CAPACITY (in which case value is
     *                    irrelevant).
     */
    void resize(int newCapacity) {
        Entry<K, V>[] oldTable = getTable();
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        @SuppressWarnings("unchecked")
        Entry<K, V>[] newTable = new Entry[newCapacity];
        transfer(oldTable, newTable);
        table = newTable;

        /*
         * If ignoring null elements and processing ref queue caused massive shrinkage,
         * then restore old table. This
         * should be rare, but avoids unbounded expansion of garbage-filled tables.
         */
        if (size >= threshold / 2) {
            threshold = (int) (newCapacity * loadFactor);
        } else {
            expungeStaleEntries();
            transfer(newTable, oldTable);
            table = oldTable;
        }
    }

    /**
     * Transfers all entries from src to dest tables
     */
    private void transfer(Entry<K, V>[] src, Entry<K, V>[] dest) {
        for (int j = 0; j < src.length; ++j) {
            Entry<K, V> e = src[j];
            src[j] = null;
            while (e != null) {
                Entry<K, V> next = e.next;
                Object key = e.get();
                if (key == null) {
                    e.next = null; // Help GC
                    e.value = null; // " "
                    size--;
                } else {
                    int i = indexFor(e.hash, dest.length);
                    e.next = dest[i];
                    dest[i] = e;
                }
                e = next;
            }
        }
    }

    /**
     * Copies all of the mappings from the specified map to this map. These mappings
     * will replace any mappings that this
     * map had for any of the keys currently in the specified map.
     *
     * @param m mappings to be stored in this map.
     * @throws NullPointerException if the specified map is null.
     */
    public void putAll(Map<? extends K, ? extends V> m) {
        int numKeysToBeAdded = m.size();
        if (numKeysToBeAdded == 0)
            return;

        /*
         * Expand the map if the map if the number of mappings to be added is greater
         * than or equal to threshold. This
         * is conservative; the obvious condition is (m.size() + size) >= threshold, but
         * this condition could result in
         * a map with twice the appropriate capacity, if the keys to be added overlap
         * with the keys already in this map.
         * By using the conservative calculation, we subject ourself to at most one
         * extra resize.
         */
        if (numKeysToBeAdded > threshold) {
            int targetCapacity = (int) (numKeysToBeAdded / loadFactor + 1);
            if (targetCapacity > MAXIMUM_CAPACITY)
                targetCapacity = MAXIMUM_CAPACITY;
            int newCapacity = table.length;
            while (newCapacity < targetCapacity)
                newCapacity <<= 1;
            if (newCapacity > table.length)
                resize(newCapacity);
        }

        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getValue());
    }

    /**
     * Removes the mapping for a key from this weak hash map if it is present. More
     * formally, if this map contains a
     * mapping from key <tt>k</tt> to value <tt>v</tt> such that
     * <code>(key==null ?  k==null :
     * key.equals(k))</code>, that mapping is removed. (The map can contain at most
     * one such mapping.)
     *
     * <br>
     * Returns the value to which this map previously associated the key, or
     * <tt>null</tt> if the map contained no
     * mapping for the key. A return value of <tt>null</tt> does not
     * <i>necessarily</i> indicate that the map contained
     * no mapping for the key; it's also possible that the map explicitly mapped the
     * key to <tt>null</tt>.
     *
     * <br>
     * The map will not contain a mapping for the specified key once the call
     * returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if
     *         there was no mapping for
     *         <tt>key</tt>
     */
    public V remove(Object key) {
        Object k = maskNull(key);
        int h = hash(k.hashCode());
        Entry<K, V>[] tab = getTable();
        int i = indexFor(h, tab.length);
        Entry<K, V> prev = tab[i];
        Entry<K, V> e = prev;

        while (e != null) {
            Entry<K, V> next = e.next;
            if (h == e.hash && eq(k, e.get())) {
                modCount++;
                size--;
                if (prev == e)
                    tab[i] = next;
                else
                    prev.next = next;
                return e.value;
            }
            prev = e;
            e = next;
        }

        return null;
    }

    /**
     * Special version of remove needed by Entry set
     */
    Entry<K, V> removeMapping(Object o) {
        if (!Map.Entry.class.isInstance(o))
            return null;
        Entry<K, V>[] tab = getTable();
        Map.Entry<?, ?> entry = (Map.Entry<?, ?>) o;
        Object k = maskNull(entry.getKey());
        int h = hash(k.hashCode());
        int i = indexFor(h, tab.length);
        Entry<K, V> prev = tab[i];
        Entry<K, V> e = prev;

        while (e != null) {
            Entry<K, V> next = e.next;
            if (h == e.hash && e.equals(entry)) {
                modCount++;
                size--;
                if (prev == e)
                    tab[i] = next;
                else
                    prev.next = next;
                return e;
            }
            prev = e;
            e = next;
        }

        return null;
    }

    /**
     * Removes all of the mappings from this map. The map will be empty after this
     * call returns.
     */
    public void clear() {
        // clear out ref queue. We don't need to expunge entries
        // since table is getting cleared.
        while (queue.poll() != null)
            ;

        modCount++;
        Entry<K, V>[] tab = table;
        for (int i = 0; i < tab.length; ++i)
            tab[i] = null;
        size = 0;

        // Allocation of array may have caused GC, which may have caused
        // additional entries to go stale. Removing these entries from the
        // reference queue will make them eligible for reclamation.
        while (queue.poll() != null)
            ;
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the specified
     * value.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the specified
     *         value
     */
    public boolean containsValue(Object value) {
        if (value == null)
            return containsNullValue();

        Entry<K, V>[] tab = getTable();
        for (int i = tab.length; i-- > 0;)
            for (Entry<K, V> e = tab[i]; e != null; e = e.next)
                if (value.equals(e.value))
                    return true;
        return false;
    }

    /**
     * Special-case code for containsValue with null argument
     */
    private boolean containsNullValue() {
        Entry<K, V>[] tab = getTable();
        for (int i = tab.length; i-- > 0;)
            for (Entry<K, V> e = tab[i]; e != null; e = e.next)
                if (e.value == null)
                    return true;
        return false;
    }

    static int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * The entries in this hash table extend SoftReference, using its main ref field
     * as the key.
     */
    private static class Entry<K, V> extends SoftReference<K> implements Map.Entry<K, V> {
        private V value;
        private final int hash;
        private Entry<K, V> next;

        /**
         * Creates new entry.
         */
        Entry(K key, V value, ReferenceQueue<K> queue, int hash, Entry<K, V> next) {
            super(key, queue);
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        public K getKey() {
            return SoftHashMap.<K>unmaskNull(get());
        }

        public V getValue() {
            return value;
        }

        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (!Map.Entry.class.isInstance(o))
                return false;
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2)))
                    return true;
            }
            return false;
        }

        public int hashCode() {
            Object k = getKey();
            Object v = getValue();
            return ((k == null ? 0 : k.hashCode()) ^ (v == null ? 0 : v.hashCode()));
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    private abstract class HashIterator<T> implements Iterator<T> {
        int index;
        Entry<K, V> entry = null;
        Entry<K, V> lastReturned = null;
        int expectedModCount = modCount;

        /**
         * Strong reference needed to avoid disappearance of key between hasNext and
         * next
         */
        Object nextKey = null;

        /**
         * Strong reference needed to avoid disappearance of key between nextEntry() and
         * any use of the entry
         */
        Object currentKey = null;

        HashIterator() {
            index = (size() != 0 ? table.length : 0);
        }

        public boolean hasNext() {
            Entry<K, V>[] t = table;

            while (nextKey == null) {
                Entry<K, V> e = entry;
                int i = index;
                while (e == null && i > 0)
                    e = t[--i];
                entry = e;
                index = i;
                if (e == null) {
                    currentKey = null;
                    return false;
                }
                nextKey = e.get(); // hold on to key in strong ref
                if (nextKey == null)
                    entry = entry.next;
            }
            return true;
        }

        /**
         * The common parts of next() across different types of iterators
         */
        protected Entry<K, V> nextEntry() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (nextKey == null && !hasNext())
                throw new NoSuchElementException();

            lastReturned = entry;
            entry = entry.next;
            currentKey = nextKey;
            nextKey = null;
            return lastReturned;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();

            SoftHashMap.this.remove(currentKey);
            expectedModCount = modCount;
            lastReturned = null;
            currentKey = null;
        }

    }

    private class ValueIterator extends HashIterator<V> {
        public V next() {
            return nextEntry().value;
        }
    }

    private class KeyIterator extends HashIterator<K> {
        public K next() {
            return nextEntry().getKey();
        }
    }

    private class EntryIterator extends HashIterator<Map.Entry<K, V>> {
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    // Views

    private transient Set<Map.Entry<K, V>> entrySet = null;

    /**
     * Returns a {@link Set} view of the keys contained in this map. The set is
     * backed by the map, so changes to the map
     * are reflected in the set, and vice-versa. If the map is modified while an
     * iteration over the set is in progress
     * (except through the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined. The
     * set supports element removal, which removes the corresponding mapping from
     * the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt>, and <tt>clear</tt>
     * operations. It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     */
    public Set<K> keySet() {
        Set<K> ks = keySet;
        return (ks != null ? ks : (keySet = new KeySet()));
    }

    private class KeySet extends AbstractSet<K> {
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        public int size() {
            return SoftHashMap.this.size();
        }

        public boolean contains(Object o) {
            return containsKey(o);
        }

        public boolean remove(Object o) {
            if (containsKey(o)) {
                SoftHashMap.this.remove(o);
                return true;
            } else
                return false;
        }

        public void clear() {
            SoftHashMap.this.clear();
        }
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map. The
     * collection is backed by the map, so
     * changes to the map are reflected in the collection, and vice-versa. If the
     * map is modified while an iteration
     * over the collection is in progress (except through the iterator's own
     * <tt>remove</tt> operation), the results of
     * the iteration are undefined. The collection supports element removal, which
     * removes the corresponding mapping
     * from the map, via the <tt>Iterator.remove</tt>, <tt>Collection.remove</tt>,
     * <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations. It does not support the
     * <tt>add</tt> or <tt>addAll</tt>
     * operations.
     */
    public Collection<V> values() {
        Collection<V> vs = values;
        return (vs != null ? vs : (values = new Values()));
    }

    private class Values extends AbstractCollection<V> {
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        public int size() {
            return SoftHashMap.this.size();
        }

        public boolean contains(Object o) {
            return containsValue(o);
        }

        public void clear() {
            SoftHashMap.this.clear();
        }
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map. The set is
     * backed by the map, so changes to the
     * map are reflected in the set, and vice-versa. If the map is modified while an
     * iteration over the set is in
     * progress (except through the iterator's own <tt>remove</tt> operation, or
     * through the <tt>setValue</tt> operation
     * on a map entry returned by the iterator) the results of the iteration are
     * undefined. The set supports element
     * removal, which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt> , <tt>retainAll</tt> and
     * <tt>clear</tt> operations. It does not support
     * the <tt>add</tt> or <tt>addAll</tt> operations.
     */
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es = entrySet;
        return es != null ? es : (entrySet = new EntrySet());
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        public boolean contains(Object o) {
            if (!Map.Entry.class.isInstance(o))
                return false;
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            Entry<K, V> candidate = getEntry(e.getKey());
            return candidate != null && candidate.equals(e);
        }

        public boolean remove(Object o) {
            return removeMapping(o) != null;
        }

        public int size() {
            return SoftHashMap.this.size();
        }

        public void clear() {
            SoftHashMap.this.clear();
        }

        private List<Map.Entry<K, V>> deepCopy() {
            List<Map.Entry<K, V>> list = CollectionUtil.createArrayList(size());
            for (Map.Entry<K, V> e : this)
                list.add(new SimpleEntry<K, V>(e));
            return list;
        }

        public Object[] toArray() {
            return deepCopy().toArray();
        }

        public <T> T[] toArray(T[] a) {
            return deepCopy().toArray(a);
        }
    }
}
