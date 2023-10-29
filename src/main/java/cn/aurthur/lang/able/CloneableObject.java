package cn.aurthur.lang.able;

public interface CloneableObject<T> extends Cloneable {

    /**
     * Performs instance cloning.
     * @return <T> T
     * @throws CloneNotSupportedException 不支持Clone
     *
     * @see Object#clone()
     */
    T clone() throws CloneNotSupportedException;
}
