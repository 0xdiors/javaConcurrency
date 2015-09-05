package com.concurrency.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest_1 {
	private static CyclicBarrier barrier;
	
	static class threadTest1 extends Thread{
		public void run() {
			System.out.println(Thread.currentThread().getName() + "达到...");
			try {
				barrier.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "执行完成...");
		}
	}
	
	public static void main(String[] args) {
		barrier = new CyclicBarrier(5);
		for(int i = 1 ; i <= 5 ; i++){
			new threadTest1().start();
		}
	}
}
