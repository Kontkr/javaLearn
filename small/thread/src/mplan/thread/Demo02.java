package mplan.thread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 天真无邪
 * @createdate 2020年3月22日上午10:09:53
 * @Desciption 寻月 生产者，消费者的 ReentrantLock实现
 **/
public class Demo02 {

	private final static int SIZE = 5;

	private final Lock lock = new ReentrantLock();

	// 获取生产者的对应的 Condition
	private final Condition product = lock.newCondition();

	// 获取消费者对应的 Condition
	private final Condition consumer = lock.newCondition();

	private LinkedList<Object> list = new LinkedList<>();

	public void set() {
		try {
			lock.lock();// 获取锁
			while (list.size() == SIZE) {
				System.out.println("生产者 " + Thread.currentThread().getName() + ":容器已满,无法生产.");
				product.await();// 释放 lock
			}
			list.add(new Object());
			System.out.println("生产者 " + Thread.currentThread().getName() + ":生产商品,库存:" + list.size());
			consumer.signalAll();// 唤醒等待的消费者线程
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void get() {
		try {
			lock.lock();
			while (list.size() == 0) {
				System.out.println("消费者 " + Thread.currentThread().getName() + ":容器已空,无法消費.");
				consumer.await();
			}
			list.remove();
			System.out.println("消费者" + Thread.currentThread().getName() + ":消费商品,库存:" + list.size());
			product.signalAll();// 唤醒等待的生产者线程
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	static class Product extends Thread {
		private Demo02 demo;

		Product(Demo02 demo, String name) {
			super(name);
			this.demo = demo;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					demo.set();
					Thread.sleep(700);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class Consumer extends Thread {

		private Demo02 demo;

		Consumer(Demo02 demo, String name) {
			super(name);
			this.demo = demo;
		}

		@Override
		public void run() {
			for (int i = 0; i <10 ; i++) {
				try {
					demo.get();
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Demo02 demo = new Demo02();

		new Product(demo, "生产者01").start();
		new Product(demo, "生产者02").start();

		new Consumer(demo, "消费者01").start();
		new Consumer(demo, "消费者02").start();
	}

}
