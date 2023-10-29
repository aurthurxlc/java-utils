package cn.aurthur.lang.able;

/**
 * 具有主键性质的接口，类似<code>DB</code>表中的<code>id</code>
 *
 * @param <T> T
 */
public interface Keyable<T> {

    public T getId();

}
