package com.vbiik;

/**
 * @ClassName LinkedListQueue
 * @Description 使用链表实现队列
 * @Author vbqiik
 * @Create 2024/04/15 19:03
 * @Version 1.0
 */
public class LinkedListQueue<E> {
    private MyLinkedList<E> list;



    public LinkedListQueue() {
        this.list = new MyLinkedList<>();
    }

    /**
     * 获取列表的大小。
     *
     * @return 返回列表中元素的数量。
     */
    public int size() {
        return list.size();
    }


    /**
     * 检查列表是否为空。
     *
     * @return boolean - 如果列表为空则返回true，否则返回false。
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 将元素添加到队列的末尾。
     *
     * @param element 要添加到队列的元素，其类型为泛型E。
     * 该方法没有返回值。
     */
    public void enqueue(E element) {
        list.add(element); // 将元素添加到列表的末尾，模拟队列的入队操作
    }

    /**
     * 从列表中删除并返回第一个元素。
     * @return 返回被删除的元素，其类型为列表元素类型E。
     */
    public E delete() {
        // 从列表中移除并返回第一个元素
        return list.remove(0);
    }

    /**
     * 获取队列的前端元素。
     * 该方法不接受任何参数。
     *
     * @return 返回队列中第一个元素，其类型为E。
     */
    public E front() {
        return list.get(0); // 返回列表中的第一个元素
    }
}
