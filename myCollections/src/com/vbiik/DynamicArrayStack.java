package com.vbiik;

/**
 * @ClassName DynamicArrayStack
 * @Description
 *  自定义栈的实现
 * @Author vbqiik
 * @Create 2024/04/15 18:55
 * @Version 1.0
 */
public class DynamicArrayStack<E>{

    private DynamicArray<E> array;

    public DynamicArrayStack() {
        this.array = new DynamicArray<> ();
    }

    /**
     * 获取数组的大小。
     * 该方法不接受参数。
     *
     * @return 返回数组的当前大小，类型为int。
     */
    public int size() {
        return array.size();
    }


    /**
     * 检查当前数据结构是否为空。
     *
     * @return boolean - 如果数据结构为空则返回true，否则返回false。
     */
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 将元素添加到数组的末尾。
     *
     * @param elemeant 要添加到数组的元素，其类型为泛型 E。
     * 该方法没有返回值。
     */
    public void push(E elemeant) {
        array.add(elemeant); // 将元素添加到数组中
    }

    /**
     * 从集合中移除最后一个元素并返回该元素。
     * 该方法不需要参数。
     *
     * @return 返回集合中最后一个元素的值，其类型为E。
     */
    public E pop() {
        // 从集合中移除并返回最后一个元素
        return array.remove(array.size() - 1);
    }


    /**
     * 从集合中获取最后一个元素，但不移除它。
     *
     * @return 返回集合中的最后一个元素，如果没有元素，则返回null。
     */
    public E peek() {
        // 获取集合中最后一个元素
        return array.get(array.size() - 1);
    }
}
