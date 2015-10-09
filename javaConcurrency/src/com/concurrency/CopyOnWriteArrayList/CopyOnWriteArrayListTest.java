package com.concurrency.CopyOnWriteArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList 为线程安全的动态数组,ArrayList是线程不安全的<br>
 * 在并发条件下，多个线程操作list时:<br>
 * --当list为ArrayList，可能会产生ConcurrentModification异常<br>
 * --当list为CopyWriteArrayList，则不会产生异常
 * @Project:javaConcurrency
 * @file:CopyOnWriteArrayListTest.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : http://cmsblogs.com
 * @qq : 122448894
 *
 * @data:2015年10月9日
 */
public class CopyOnWriteArrayListTest {
//	private static List<String> list = new ArrayList<>();
	private static List<String> list = new CopyOnWriteArrayList<String>();
	
	public static void main(String[] args) {
		new MyThread("testA").start();
		new MyThread("testB").start();
	}
	
	private static class MyThread extends Thread{
		public MyThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			for(int i = 0 ; i < 10 ; i++){
				list.add(Thread.currentThread().getName() + "-" + i );
				printAll();
			}
		}

	}
	
	private static void printAll() {
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			String value = iterator.next();
			System.out.print(value + "     ");
		}
		System.out.println();
	}

}
