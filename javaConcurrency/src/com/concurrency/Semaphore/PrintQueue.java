package com.concurrency.Semaphore;

import java.util.concurrent.Semaphore;

public class PrintQueue {
	private final Semaphore semaphore;   //�����ź���
	
	public PrintQueue(){
		semaphore = new Semaphore(1);
	}
	
	public void printJob(Object document){
		try {
			semaphore.acquire();//����acquire��ȡ�ź���
			long duration = (long) (Math.random() * 10);
			System.out.println( Thread.currentThread().getName() + 
					"PrintQueue : Printing a job during " + duration);
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  finally{
			semaphore.release();  //�ͷ��ź���
		}
	}
}
