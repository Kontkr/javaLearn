package mplan.write.test;

import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 天真无邪
 * @createdate 2020年3月21日下午9:09:17
 * @Desciption 寻月 实现消息队列
 * 
 **/
public class MessageQueue {

	private final static int SIZE = 5;

	// 使用queue
	private ArrayDeque<Object> queue = new ArrayDeque<>(SIZE);

	private final Lock lock = new ReentrantLock();

	private final Condition product = lock.newCondition();

	private final Condition consumer = lock.newCondition();

	public void send() {
		try {
			lock.lock();
			while (queue.size() == SIZE) {
				System.out.println(Thread.currentThread().getName() + ":队列已满！");
				product.await();
			}
			queue.offer(new Object());// 队列如果没有大小限制,使用offer好一点
			System.out.println(Thread.currentThread().getName() + ":发送消息！");
			consumer.signalAll();// 唤醒消费者
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void consumer() {
		try {
			lock.lock();
			while (queue.size() == 0) {
				System.out.println(Thread.currentThread().getName() + ":队列已空！");
				consumer.await();
			}
			queue.poll();// 获取从队列的尾部的元素,并在该队列中刪除该元素
			System.out.println(Thread.currentThread().getName() + ":消费消息！");
			product.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	static class Product extends Thread {

		MessageQueue queue;

		Product(MessageQueue queue, String name) {
			super(name);
			this.queue = queue;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				queue.send();
				try {
					Thread.sleep(500);// 给其它线程执行权
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class Consumer extends Thread {

		MessageQueue queue;

		Consumer(MessageQueue queue, String name) {
			super(name);
			this.queue = queue;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				queue.consumer();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		MessageQueue mq = new MessageQueue();
		new MessageQueue.Product(mq, "生产者01").start();
		new MessageQueue.Product(mq, "生产者02").start();
		new MessageQueue.Product(mq, "生产者03").start();

		new MessageQueue.Consumer(mq, "消费者01").start();
		new MessageQueue.Consumer(mq, "消费者02").start();
	}
}
