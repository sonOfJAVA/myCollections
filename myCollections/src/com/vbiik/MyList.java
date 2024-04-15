package com.vbiik;

import java.util.function.Consumer;

/**
 * @ClassName MyList
 * @Description 自定义的列表接口
 * @Author vbqiik
 * @Create 2024/04/15 14:03
 * @Version 1.0
 */
public interface MyList<E> {
    /**
     * 添加一个元素
     * @param element
     */
    void add(E element);

    /**
     * 在指定位置添加一个元素
     * @param index
     * @param element
     */
    void add(int index, E element);

    /**
     * 删除指定位置的元素
     * @param index
     * @return 被删除的元素
     */
    E remove(int index);

    /**
     * 修改指定位置的元素
     * @param index
     * @param element
     * @return 修改前的元素
     */
    E set(int index,E element);

    /**
     * 查询指定位置的元素
     * @param index
     * @return 该位置的元素
     */
    E get(int index);

    /**
     * 查询列表中元素的个数
     * @return
     */
    int size();

    /**
     * 对集合进行批量操作
     * @param consumer
     */
    void foreach(Consumer<E> consumer);

    boolean isEmpty();
}
