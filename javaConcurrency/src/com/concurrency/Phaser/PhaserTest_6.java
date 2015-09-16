package com.concurrency.Phaser;

import java.util.concurrent.Phaser;

public class PhaserTest_6 {
	public static void main(String[] args) {
		Phaser phaser = new Phaser(3){
			 protected boolean onAdvance(int phase, int registeredParties) {
				 System.out.println("------------------------请叫我分割军:phase=" + phase + ";registeredParties="
						 			+ registeredParties + "------------------------");
				 return registeredParties == 1;
			 }
		};
		
		for(int i = 0 ; i < 3 ; i++){
			 Thread thread = new Thread(new Task_06(phaser,i),"PhaserTest_" + i);
			 thread.start();
		 }
		
		do{
			phaser.arriveAndAwaitAdvance();		//主线程等待其他子任务完成..
		}while(!phaser.isTerminated());
		
		System.out.println("任务执行完毕.....");
	}
	
	static class Task_06 implements Runnable{
		private final Phaser phaser;
		private int i ;
		Task_06(Phaser phaser,int i ){
			this.phaser = phaser;
			this.i = i;
		}
		
		@Override
		public void run() {
			System.out.println("----执行子任务:" + Thread.currentThread().getName() + "; i = " + i);
			//定义随机数
			while(true){
				i++;
				if(i == 5){	//当i == 5时，子任务结束，Phaser管理线程 - 1
					phaser.arriveAndAwaitAdvance();
					break;
				}
				else{
					//等待其他线程到达阶段终点，再一起进入下一个阶段
					System.out.println(Thread.currentThread().getName() + "; i = " + i);
					phaser.arriveAndAwaitAdvance(); 
				}
			}
		}
		
	}
}
