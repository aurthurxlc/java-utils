package cn.aurthur.lang.collection;

import com.bqteam.basetool.sdk.core.lang.able.CloneableObject;
import com.bqteam.basetool.sdk.core.utils.CollectionUtil;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * 从<code>LinkedList</code>继承的stack，避免<code>java.util.Stack</code> 中synchronized的代价。
 */
public class Stack<T> implements Serializable, CloneableObject<Stack<T>> {

    private static final long serialVersionUID = 1L;

    private LinkedList<T> list = CollectionUtil.createLinkedList();

    public T peek() {
        if (list.isEmpty()) {
            throw new EmptyStackException();
        }

        return list.getLast();
    }

    public void push(T object) {
        list.addLast(object);
    }

    public T pop() {
        if (list.isEmpty()) {
            throw new EmptyStackException();
        }

        return list.removeLast();
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public String toString() {
        return list.toString();
    }

    public Stack<T> clone() throws CloneNotSupportedException {
        Stack<T> stack = new Stack<T>();
        @SuppressWarnings("unchecked")
        LinkedList<T> newList = (LinkedList<T>) list.clone();

        stack.list = newList;
        return stack;
    }

}