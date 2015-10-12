package com.concurrency.CopyOnWriteArraySet;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * CopyOnWriteArraySet是“线程安全”的集合，而HashSet是非线程安全的<br>
 * 在并发条件下，多个线程操作Set时:<br>
 * --当list为HashSet，可能会产生ConcurrentModification异常<br>
 * --当list为CopyOnWriteArraySet，则不会产生异常
 * @Project:javaConcurrency
 * @file:CopyOnWriteArraySetTest.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : http://cmsblogs.com
 * @qq : 122448894
 *t
 * @data:2015年10月12日
 */
public class CopyOnWriteArraySetTest {
//	private static Set<String> setes = new HashSet<String>();
	
	private static Set<String> setes = new CopyOnWriteArraySet<String>();
	
	public static void main(String[] args) {
		new MyThread("testA").start();
		new MyThread("testB").start();
	}
	
	private static class MyThread extends Thread{
		MyThread(String name){
			super(name);
		}
		public void run(){
			for(int i = 0 ; i < 5 ; i++){
				setes.add(Thread.currentThread().getName() + "--" + i);
				printAll();
			}
		}
	}

	public static void printAll() {
		Iterator<String> iterator = setes.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next() + "   ");
		}
		System.out.println("");
	}
}
