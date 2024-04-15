package com.vbiik;

import java.util.function.Consumer;

/**
 * @ClassName MyLinkedList
 * @Description 自定义链表
 * @Author vbqiik
 * @Create 2024/04/15 16:35
 * @Version 1.0
 */
public class MyLinkedList<E> {
    private int size = 0;

    private Node<E> head = null;

    private Node<E> tail = null;

    /**
     * 获取集合的大小。
     *
     * @return 集合中元素的数量。
     */
    public int size() {
        return size;
    }

    /**
     * 检查集合是否为空。
     *
     * @return 如果集合为空，则返回true；否则返回false。
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向集合中添加一个元素。
     *
     * @param element 要添加到集合中的元素。
     */
    public void add(E element) {
        // 进行非空判断
        if (null == element) {
            throw new NullPointerException("要添加的元素不能为空");
        }
        // 当集合为空时，添加的第一个元素同时作为头和尾节点
        if (this.isEmpty()) {
            this.head = new Node<>(element, null);
            this.tail = head;
        } else {
            // 为尾节点添加一个新的后继节点，并更新尾节点指向新节点
            Node<E> node = new Node<>(element, null);
            tail.setNext(node);
            tail = node;
        }
        ++size; // 更新集合大小
    }

    /**
     * 根据指定索引获取集合中的元素。
     *
     * @param index 需要获取元素的索引位置。
     * @return 返回位于指定索引位置的元素。
     * @throws IndexOutOfBoundsException 如果索引超出集合范围，则抛出异常。
     */
    public E get(int index) {
        // 检查索引是否在有效范围内
        checkIndexBound(index);

        // 用于计数和存储结果的临时数组
        int[] cnt = new int[1];
        Object[] obj = new Object[1];

        // 遍历集合，找到对应索引位置的元素
        this.foreach(node -> {
            if (++cnt[0] == index)
                obj[0] = node;
        });

        // 将找到的元素转换为泛型类型并返回
        return (E) obj[0];
    }


    /**
     * 在指定位置插入一个元素。
     *
     * @param index   插入位置的索引。
     * @param element 要插入的元素。
     * @throws IndexOutOfBoundsException 如果索引超出范围，则抛出异常。
     */
    public void add(int index, E element) {
        // 检查索引是否超出范围
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index + "out of " + (size - 1));
        }
        // 当索引等于当前size时，在末尾添加元素
        if (index == size) {
            this.add(element);
        }
        // 当索引为0时，插入元素到链表头部
        if (index == 0) {
            head = new Node<>(element, head);
        }
        // 定位前驱节点
        Node pre = this.getNode(index - 1);
        // 定位旧节点
        Node oldNode = pre.getNext();
        // 在指定位置插入新节点
        pre.setNext(new Node<>(element, oldNode));

        ++size; // 更新链表大小
    }


    /**
     * 从链表中移除指定索引的元素。
     *
     * @param index 要移除的元素的索引。
     * @return 被移除元素的值。
     * @throws IndexOutOfBoundsException 如果索引超出范围。
     */
    public E remove(int index) {
        // 检查索引是否在有效范围内
        checkIndexBound(index);

        Node removingNode;
        if (index == 0) { // 如果索引为0，移除头节点
            Node<E> next = head.getNext();
            removingNode = head;
            head = next;
        } else {
            // 如果索引大于0，找到要移除节点的前一个节点和要移除的节点
            Node pre = this.getNode(index - 1);
            removingNode = pre.getNext();
            // 获取要移除节点的后一个节点
            Node next = removingNode.getNext();
            // 将前一个节点的next指向后一个节点，跳过要移除的节点
            pre.setNext(next);
        }
        // 断开要移除节点的后续连接，防止出现孤立节点
        removingNode.setNext(null);
        // 返回被移除节点的元素值
        return (E) removingNode.getElement();
    }

    /**
     * 将指定索引位置的元素替换为新的元素。
     *
     * @param index 要替换的元素的索引位置。
     * @param element 新的元素。
     * @return 返回被替换的旧元素。
     * @throws IndexOutOfBoundsException 如果索引超出范围，则抛出异常。
     */
    public E set(int index, E element) {
        // 进行非空判断
        if (null == element) {
            throw new NullPointerException("要添加的元素不能为空");
        }
        // 检查索引是否在有效范围内
        checkIndexBound(index);
        // 获取指定索引位置的节点
        Node node = this.getNode(index);
        // 保存当前节点的旧元素
        Object oldValue = node.getElement();
        // 将新元素设置到节点中
        node.setElement(element);
        // 返回旧元素
        return (E) oldValue;
    }

    /**
     * 遍历当前数据结构中的所有元素，并对每个元素应用提供的消费者接口。
     *
     * @param consumer 一个实现了Consumer接口的函数式接口，它接受一个类型为E的参数，并对其进行操作。这里用来对每个元素执行特定操作。
     */
    public void foreach(Consumer<E> consumer) {
        // 如果链表不为空，则调用head的foreach
        if (!isEmpty()) {
            head.foreach(consumer);
        }
    }

    /**
     * 检查给定的索引是否在有效范围内。
     * 如果索引小于0或大于等于集合大小，则抛出IndexOutOfBoundsException。
     *
     * @param index 需要检查的索引值。
     * @throws IndexOutOfBoundsException 如果索引不在有效范围内。
     */
    private void checkIndexBound(int index) {
        // 检查索引是否超出范围
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index + "out of " + (size - 1));
        }
    }

    /**
     * 根据索引获取链表中的节点。
     *
     * @param index 需要获取的节点的索引，从1开始。
     * @return 返回链表中对应索引的节点，如果索引无效，返回null。
     */
    private Node getNode(int index) {
        Node node = head; // 从链表头开始查找
        for (int i = 1; i <= index; i++) {
            node = node.next; // 逐个遍历节点，直到找到对应索引的节点
        }
        return node; // 返回找到的节点
    }


    private static class Node<E> {
        private E element;
        private Node<E> next;

        /**
         * 遍历当前数据结构中的所有元素，并对每个元素应用提供的消费者接口。
         * 这个方法通过一个给定的消费者接口，对当前数据结构中的每个元素执行特定操作。
         * 消费者接口的操作内容由调用者定义。
         *
         * @param consumer 一个实现了Consumer接口的函数式接口，它接受一个元素并对其进行操作。
         *                 消费者接口的具体操作由调用者在传递给此方法时定义。
         */
        public void foreach(Consumer<E> consumer) {
            Node<E> node = this; // 从当前节点开始遍历
            while (node != null) {
                consumer.accept(element); // 对当前节点的元素应用消费者接口的操作
                node = node.next; // 移动到下一个节点
            }
        }


        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node() {
        }

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
}
