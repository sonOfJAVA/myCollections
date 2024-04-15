package com.vbiik;

import java.util.function.Consumer;

/**
 * @ClassName DamyticArray
 * @Description 自定义动态数组(ArrayList)
 * @Author vbqiik
 * @Create 2024/04/15 14:18
 * @Version 1.0
 */
public class DynamicArray<E> implements MyList<E> {

    private static final int DEFAULT_FACILITY = 10;

    private Object[] data = new Object[DEFAULT_FACILITY];
    /**
     * 数组元素个数
     */
    private int size;


    /**
     * 向集合中添加一个元素。
     *
     * @param element 要添加的元素。
     *                该方法没有返回值。
     */
    @Override
    public void add(E element) {
        if (size == data.length) {
            // 当集合达到当前容量的极限时，自动扩展容量
            resize();
        }
        data[size++] = element;
    }


    /**
     * 在指定位置插入一个元素。
     *
     * @param index   插入位置的索引。
     * @param element 要插入的元素。
     * @throws ArrayIndexOutOfBoundsException 如果索引超出范围。
     */
    @Override
    public void add(int index, E element) {
        // 检查索引是否在有效范围内
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index + "out of " + (size));
        }

        // 如果当前容量已满，则进行容量扩展
        if (size == data.length) {
            resize();
        }

        // 将插入位置之后的元素依次后移，为新元素腾出空间
        for (int i = size - 1; i > index; i--) {
            data[i + 1] = data[i];
        }
        // 在指定位置插入元素
        data[index] = element;
        ++size; // 更新集合大小
    }

    /**
     * 从集合中指定位置移除一个元素。
     *
     * @param index 要移除的元素的位置索引。
     * @return 被移除的元素。
     * @throws IndexOutOfBoundsException 如果索引超出范围。
     */
    @Override
    public E remove(int index) {
        // 检查索引是否在有效范围内
        checkArrayIndexBounds(index);
        // 保存将要移除的元素
        Object removingElement = data[index];
        // 将指定位置的元素置为null
        data[index] = null;
        // 将后续元素向前移动覆盖空位
        System.arraycopy(data, index + 1, data, index, size - index);
        // 更新集合大小
        --size;
        // 返回被移除的元素
        return (E) removingElement;
    }


    /**
     * 将指定索引位置的元素替换为新的元素，并返回被替换的旧元素。
     *
     * @param index   需要被替换的元素的索引位置。
     * @param element 新的元素。
     * @return 旧的元素。
     */
    @Override
    public E set(int index, E element) {
        // 检查索引是否在有效范围内
        checkArrayIndexBounds(index);
        // 保存旧元素的值
        Object oldValue = data[index];
        // 将新元素设置到指定索引位置
        data[index] = element;
        // 返回旧元素
        return (E) oldValue;
    }


    /**
     * 获取指定索引位置的元素。
     *
     * @param index 索引值，指定要获取的元素的位置。
     * @return 返回存储在指定索引位置的元素。
     * @throws IndexOutOfBoundsException 如果索引超出有效范围，则抛出异常。
     */
    @Override
    public E get(int index) {
        // 检查索引是否在有效范围内
        checkArrayIndexBounds(index);
        return (E) data[index];
    }


    /**
     * 获取当前列表中元素的个数。
     *
     * @return 当前列表中元素的个数。
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * 遍历集合中的每个元素，并将其传递给提供的消费者接口进行处理。
     * @param consumer 消费者接口，是一个接受类型为E的单个参数的函数式接口。
     *                 对集合中的每个元素，都会调用此接口的accept方法。
     */
    @Override
    public void foreach(Consumer<E> consumer) {
        // 遍历集合data中的每个元素，使用提供的consumer处理每个元素
        for (Object o : data) {
            consumer.accept((E) o);
        }
    }


    /**
     * 调整数据数组的大小，将其容量扩大为当前容量的两倍。
     * 该方法不接受参数，也不返回任何值。
     * 它首先创建一个新的容量为原容量两倍的Object数组，然后将原数组中的元素复制到新数组中。
     * 完成后，原数据数组被新数组替代，以便为后续添加更多元素提供空间。
     */
    private void resize() {
        // 创建一个新的对象数组，其大小为当前大小的两倍
        Object[] objects = new Object[size * 2];
        // 将原数组中的元素复制到新数组中
        System.arraycopy(data, 0, objects, 0, size);
        // 使用新数组替换原数据数组
        data = objects;
    }

    /**
     * 检查给定的索引是否在数组的有效范围内。
     * 如果索引小于0或大于等于数组大小，则抛出ArrayIndexOutOfBoundsException。
     *
     * @param index 要检查的索引值。
     * @throws ArrayIndexOutOfBoundsException 如果索引不在有效范围内。
     */
    private void checkArrayIndexBounds(int index) {
        // 检查索引是否超出范围
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index + "out of " + (size - 1));
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
