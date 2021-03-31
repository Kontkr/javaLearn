package mplan.thread;

import java.util.LinkedList;

/**
 * @author 天真无邪
 * @createdate 2020年3月21日下午11:32:29
 * @Desciption 寻月 生产者,消费者模式
 **/
public class Demo01 {

	public static final int SIZE = 5;

	private LinkedList<Object> list = new LinkedList<>();

	// 生产
	public void product() {
		synchronized (list) {
			while (list.size() == SIZE) {
				try {
					System.out.println("生产者 "+Thread.currentThread().getName()+":容器已满,无法生产.");
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.add(new Object());
			System.out.println("生产者 "+Thread.currentThread().getName()+":生产商品,库存:"+list.size());
			list.notifyAll();
		}
	}

	public void consumer() {
		synchronized (list) {
			while (list.size() == 0) {
				try {
					System.out.println("消费者 "+Thread.currentThread().getName()+":容器已空,无法消費.");
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.remove();
			System.out.println("消费者"+Thread.currentThread().getName()+":消费商品,库存:"+list.size());
			list.notifyAll();
		}
	}

	static class Product extends Thread {

		private Demo01 demo;

		Product(Demo01 demo,String name) {
			super(name);
			this.demo = demo;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				demo.product();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class Consumer extends Thread {
		private Demo01 demo;

		Consumer(Demo01 demo,String name) {
			super(name);
			this.demo = demo;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				demo.consumer();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Demo01 demo = new Demo01();
		new Demo01.Product(demo,"prod01").start();
		new Demo01.Product(demo,"prod02").start();

		new Demo01.Consumer(demo,"consumer01").start();
		new Demo01.Consumer(demo,"consumer02").start();
	}
}
