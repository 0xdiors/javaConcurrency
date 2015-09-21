package com.concurrency.Exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 生产者
 * @Project:javaConcurrency
 * @file:Producer.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : http://cmsblogs.com
 * @qq : 122448894
 *
 * @data:2015年9月21日
 */
public class Producer implements Runnable{
	
	/**
	 * 生产者和消费者进行交换的数据结构
	 */
	private List<String> buffer;
	
	/**
	 * 同步生产者和消费者的交换对象
	 */
	private final Exchanger<List<String>> exchanger;
	
	Producer(List<String> buffer,Exchanger<List<String>> exchanger){
		this.buffer = buffer;
		this.exchanger = exchanger;
	}
	
	@Override
	public void run() {
		int cycle = 1;
		for(int i = 0 ; i < 10 ; i++){
			System.out.println("Producer : Cycle :" + cycle);
			for(int j = 0 ; j < 10 ; j++){
				String message = "Event " + ((i * 10 ) + j);
				System.out.println("Producer : " + message);
				buffer.add(message);
			}
			
			//调用exchange()与消费者进行数据交换
			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Producer :" + buffer.size());
			cycle++ ;
		}
	}
}
