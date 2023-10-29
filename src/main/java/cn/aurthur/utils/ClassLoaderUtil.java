package cn.aurthur.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class ClassLoaderUtil {
    public ClassLoaderUtil() {
        throw new AssertionError("工具类不允许实例化");
    }
    /*
     * ========================================================================== ==
     */
    /* 取得 context class loader的方法。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 取得当前线程的<code>ClassLoader</code>。
     *
     * @return 当前线程的<code>ClassLoader</code>
     */
    public static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 取得当前的<code>Classpath</code>
     *
     * @return 当前的<code>Classpath</code>
     */
    public static String getClasspath() {
        return ClassLoaderUtil.getResource("").getPath();
    }

    /**
     * <div>
     * 从<code>ClassLoader</code>取得resource URL。按如下顺序查找:
     * </div>
     *
     * <ol>
     * <li>
     * 在当前线程的<code>ClassLoader</code>中查找。</li>
     * <li>
     * 在装入自己的<code>ClassLoader</code>中查找。</li>
     * <li>
     * 通过<code>ClassLoader.getSystemResource</code>方法查找。</li>
     * </ol>
     *
     * @param resourceName 要查找的资源名，就是以&quot;/&quot;分隔的标识符字符串
     * @return resource的URL
     */
    public static URL getResource(String resourceName) {
        if (resourceName == null) {
            return null;
        }

        ClassLoader classLoader = null;
        URL url = null;

        // 首先试着从当前线程的ClassLoader中查找。
        classLoader = getContextClassLoader();

        if (classLoader != null) {
            url = classLoader.getResource(resourceName);

            if (url != null) {
                return url;
            }
        }

        // 如果没找到，试着从装入自己的ClassLoader中查找。
        classLoader = ClassLoaderUtil.class.getClassLoader();

        if (classLoader != null) {
            url = classLoader.getResource(resourceName);

            if (url != null) {
                return url;
            }
        }

        // 最后的尝试: 在系统ClassLoader中查找(JDK1.2以上)，
        // 或者在JDK的内部ClassLoader中查找(JDK1.2以下)。
        return ClassLoader.getSystemResource(resourceName);
    }

    /**
     * 从指定调用者所属的<code>ClassLoader</code>取得resource URL。
     *
     * @param resourceName 要查找的资源名，就是以&quot;/&quot;分隔的标识符字符串
     * @param referrer     调用者类，如果为<code>null</code>，表示在<code>ClassLoaderUtil</code> 的class loader中找。
     * @return resource URL，如果没找到，则返回<code>null</code>
     */
    public static URL getResource(String resourceName, Class<?> referrer) {
        if (resourceName == null) {
            return null;
        }

        ClassLoader classLoader = __getReferrerClassLoader(referrer);

        return (classLoader == null) ? ClassLoaderUtil.class.getClassLoader().getResource(resourceName) : classLoader
                .getResource(resourceName);
    }

    /**
     * 从指定的<code>ClassLoader</code>取得resource URL。
     *
     * @param resourceName 要查找的资源名，就是以&quot;/&quot;分隔的标识符字符串
     * @param classLoader  在指定classLoader中查找，如果为<code>null</code>，表示在 <code>ClassLoaderUtil</code>的class loader中找。
     * @return resource URL，如果没找到，则返回<code>null</code>
     */
    public static URL getResource(String resourceName, ClassLoader classLoader) {
        if (StringUtil.isBlank(resourceName)) {
            return null;
        }

        return (classLoader == null) ? ClassLoaderUtil.class.getClassLoader().getResource(resourceName) : classLoader
                .getResource(resourceName);
    }

    /**
     * 从<code>ClassLoader</code>取得resource的输入流。 相当于 <code>getResource(resourceName).openStream()</code>。
     *
     * @param resourceName 要查找的资源名，就是以"/"分隔的标识符字符串
     * @return resource的输入流
     */
    public static InputStream getResourceAsStream(String resourceName) {
        URL url = getResource(resourceName);

        try {
            if (url != null) {
                return url.openStream();
            }
        } catch (IOException ignore) {
            // 打开URL失败。can ignore
        }

        return null;
    }

    /**
     * 从<code>ClassLoader</code>取得resource的输入流。 相当于 <code>getResource(resourceName,
     * referrer).openStream()</code>。
     *
     * @param resourceName 要查找的资源名，就是以"/"分隔的标识符字符串
     * @param referrer     调用者类，如果为<code>null</code>，表示在<code>ClassLoaderUtil</code> 的class loader中找。
     * @return resource的输入流
     */
    public static InputStream getResourceAsStream(String resourceName, Class<?> referrer) {
        URL url = getResource(resourceName, referrer);

        try {
            if (url != null) {
                return url.openStream();
            }
        } catch (IOException ignore) {
            // 打开URL失败。can ignore
        }

        return null;
    }

    /**
     * 从<code>ClassLoader</code>取得resource的输入流。 相当于 <code>getResource(resourceName,
     * classLoader).openStream()</code>。
     *
     * @param resourceName 要查找的资源名，就是以"/"分隔的标识符字符串
     * @param classLoader  在指定classLoader中查找，如果为<code>null</code>，表示在 <code>ClassLoaderUtil</code>的class loader中找。
     * @return resource的输入流
     */
    public static InputStream getResourceAsStream(String resourceName, ClassLoader classLoader) {
        URL url = getResource(resourceName, classLoader);

        try {
            if (url != null) {
                return url.openStream();
            }
        } catch (IOException ignore) {
            // 打开URL失败。 can ignore
        }

        return null;
    }


    /*
     * ========================================================================== ==
     */
    /* 装入类的方法。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 从当前线程的<code>ClassLoader</code>装入类。对于JDK1.2以下，则相当于 <code>Class.forName</code>。
     *
     * @param className 要装入的类名
     * @return 已装入的类
     * @throws ClassNotFoundException 如果类没找到
     */
    public static <T> Class<T> loadClass(String className) throws ClassNotFoundException {
        return loadClass(className, getContextClassLoader());
    }

    /**
     * 从指定的调用者的<code>ClassLoader</code>装入类。
     *
     * @param className 要装入的类名
     * @param referrer  调用者类，如果为<code>null</code>，则该方法相当于<code>Class.forName</code>
     * @return 已装入的类
     * @throws ClassNotFoundException 如果类没找到
     */
    public static <T> Class<T> loadClass(String className, Class<?> referrer) throws ClassNotFoundException {
        ClassLoader classLoader = __getReferrerClassLoader(referrer);

        // 如果classLoader为null，表示从ClassLoaderUtil所在的classloader中装载，
        // 这个classloader未必是System class loader。
        return loadClass(className, classLoader);
    }

    /**
     * 从指定的<code>ClassLoader</code>中装入类。如果未指定<code>ClassLoader</code>， 则从装载 <code>ClassLoaderUtil</code>的
     * <code>ClassLoader</code>中装入。
     *
     * @param className   要装入的类名
     * @param classLoader 从指定的<code>ClassLoader</code>中装入类，如果为<code>null</code>，表示从 <code>ClassLoaderUtil</code>所在的class
     *                    loader中装载
     * @return 已装入的类
     * @throws ClassNotFoundException 如果类没找到
     */
    public static <T> Class<T> loadClass(String className, ClassLoader classLoader) throws ClassNotFoundException {
        if (StringUtil.isBlank(className)) {
            return null;
        }

        if (classLoader == null) {
            @SuppressWarnings("unchecked")
            Class<T> clazz = (Class<T>) Class.forName(className);
            return clazz;
        }
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) Class.forName(className, true, classLoader);
        return clazz;
    }

    /*
     * ========================================================================== ==
     */
    /* 装入和查找资源文件的方法。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 从<code>ClassLoader</code>取得所有resource URL。按如下顺序查找:
     *
     * <ol>
     * <li>
     * 在当前线程的<code>ClassLoader</code>中查找。</li>
     * <li>
     * 在装入自己的<code>ClassLoader</code>中查找。</li>
     * <li>
     * 通过<code>ClassLoader.getSystemResource</code>方法查找。</li>
     * </ol>
     *
     * @param resourceName 要查找的资源名，就是以&quot;/&quot;分隔的标识符字符串
     * @return resource的URL数组，如果没找到，则返回空数组。数组中保证不包含重复的URL。
     */
    public static URL[] getResources(String resourceName) {
        List<URL> urls = CollectionUtil.createLinkedList();
        boolean found = false;

        // 首先试着从当前线程的ClassLoader中查找。
        found = __getResources(urls, resourceName, getContextClassLoader(), false);

        // 如果没找到，试着从装入自己的ClassLoader中查找。
        if (!found) {
            __getResources(urls, resourceName, ClassLoaderUtil.class.getClassLoader(), false);
        }

        // 最后的尝试: 在系统ClassLoader中查找(JDK1.2以上)，
        // 或者在JDK的内部ClassLoader中查找(JDK1.2以下)。
        if (!found) {
            __getResources(urls, resourceName, null, true);
        }

        // 返回不重复的列表。
        return __getDistinctURLs(urls);
    }

    /**
     * 从指定调用者所属的<code>ClassLoader</code>取得所有resource URL。
     *
     * @param resourceName 要查找的资源名，就是以&quot;/&quot;分隔的标识符字符串
     * @param referrer     调用者类，如果为<code>null</code>，表示在<code>ClassLoaderUtil</code> 的class loader中找
     * @return resource的URL数组，如果没找到，则返回空数组。数组中保证不包含重复的URL。
     */
    public static URL[] getResources(String resourceName, Class<?> referrer) {
        ClassLoader classLoader = __getReferrerClassLoader(referrer);
        List<URL> urls = CollectionUtil.createLinkedList();

        __getResources(urls, resourceName, classLoader, classLoader == null);

        // 返回不重复的列表。
        return __getDistinctURLs(urls);
    }

    /**
     * 从指定的<code>ClassLoader</code>中取得所有resource URL。如果未指定 <code>ClassLoader</code>， 则从装载<code>ClassLoaderUtil</code>的
     * <code>ClassLoader</code>中取得所有resource URL。
     *
     * @param resourceName 要查找的资源名，就是以&quot;/&quot;分隔的标识符字符串
     * @param classLoader  从指定的<code>ClassLoader</code>中查找
     * @return resource的URL数组，如果没找到，则返回空数组。数组中保证不包含重复的URL。
     */
    public static URL[] getResources(String resourceName, ClassLoader classLoader) {
        List<URL> urls = CollectionUtil.createLinkedList();

        __getResources(urls, resourceName, classLoader, classLoader == null);

        // 返回不重复的列表。
        return __getDistinctURLs(urls);
    }

    /**
     * 在指定class loader中查找指定名称的resource，把所有找到的resource的URL放入指定的集合中。
     *
     * @param urlSet         存放resource URL的集合
     * @param resourceName   资源名
     * @param classLoader    类装入器
     * @param sysClassLoader 是否用system class loader装载资源
     * @return 如果找到，则返回<code>true</code>
     */
    private static boolean __getResources(List<URL> urlSet, String resourceName, ClassLoader classLoader,
                                          boolean sysClassLoader) {
        if (StringUtil.isBlank(resourceName)) {
            return false;
        }

        Enumeration<URL> enums = null;

        try {
            if (classLoader != null) {
                enums = classLoader.getResources(resourceName);
            } else if (sysClassLoader) {
                enums = ClassLoader.getSystemResources(resourceName);
            }
        } catch (IOException ignore) {
            // can ignore
        }

        if (!CollectionUtil.hasItems(enums)) {
            return false;
        }

        while (enums.hasMoreElements()) {
            urlSet.add(enums.nextElement());
        }

        return true;
    }

    /**
     * 去除URL列表中的重复项。
     *
     * @param urls URL列表
     * @return 不重复的URL数组，如果urls为<code>null</code>，则返回空数组
     */
    private static URL[] __getDistinctURLs(List<URL> urls) {
        if (urls == null || urls.size() == 0) {
            return new URL[0];
        }

        Set<URL> urlSet = CollectionUtil.createHashSet();

        for (Iterator<URL> i = urls.iterator(); i.hasNext(); ) {
            URL url = i.next();

            if (urlSet.contains(url)) {
                i.remove();
            } else {
                urlSet.add(url);
            }
        }

        return urls.toArray(new URL[0]);
    }

    /**
     * 取得调用者的class loader。
     *
     * @param referrer 调用者类
     * @return 调用者的class loader，如果referrer为<code>null</code>，则返回 <code>null</code>
     */
    private static <T> ClassLoader __getReferrerClassLoader(Class<T> referrer) {
        if (referrer == null) {
            return null;
        }

        ClassLoader classLoader = referrer.getClassLoader();

        // classLoader为null，说明referrer类是由bootstrap classloader装载的，
        // 例如：java.lang.String
        return (classLoader == null) ? ClassLoader.getSystemClassLoader() : classLoader;

    }

}
