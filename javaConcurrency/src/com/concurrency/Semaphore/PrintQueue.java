package com.concurrency.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * 打印任务
 * @Project:javaConcurrency
 * @file:PrintQueue.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : http://cmsblogs.com
 * @qq : 122448894
 *
 * @data:2015年9月6日
 */
public class PrintQueue {
	private final Semaphore semaphore;   //声明信号量
	
	public PrintQueue(){
		semaphore = new Semaphore(1);
	}
	
	public void printJob(Object document){
		try {
			semaphore.acquire();//调用acquire获取信号量
			long duration = (long) (Math.random() * 10);
			System.out.println( Thread.currentThread().getName() + 
					"PrintQueue : Printing a job during " + duration);
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  finally{
			semaphore.release();  //释放信号量
		}
	}
}
