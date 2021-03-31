package mplan.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 天真无邪
 * @createdate 2020年3月22日上午10:56:14
 * @Desciption 寻月 通过 BlockingQueue 实现消费者，生产者模式
 * 					BlockingQueue 是线程安全的,内部使用可重入锁与 condition 实现 ,
 * 					若是执行 take 的线程时,queue为null,阻塞当前的线程,执行 put时,若是queue为空 ，则阻塞当前的线程 
 **/
public class Demo03 {

	private final static int SIZE = 5;

	private static BlockingQueue<Object> bq = new LinkedBlockingQueue<>(SIZE);

	static class Product extends Thread {
		Product(String name) {
			super(name);
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				try {
					bq.put(new Object());
					System.out.println(Thread.currentThread().getName() + ":添加元素");
					Thread.sleep(500);// 睡眠是为了给其它线程执行的机会
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class Consumer extends Thread {
		Consumer(String name) {
			super(name);
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				try {
					bq.take();
					System.out.println(Thread.currentThread().getName() + ":获取元素");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new Product("生产者01").start();
		new Product("生产者02").start();

		new Consumer("消费者01").start();
		new Consumer("消费者02").start();
	}
}
