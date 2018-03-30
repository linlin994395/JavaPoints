package cn.edu.neu.xl.Synchronized;

import java.util.List;

/*
 * http://blog.csdn.net/heyutao007/article/details/5672542
 */
/*
 * 一个线程有如下几种状态
 * 1.创建状态：生成线程对象，没有调用start()方法
 * 2.就绪状态：调用start()方法，但此线程并不是当前线程
 * 3.运行状态：开始执行run()方法
 * 4.等待/阻塞状态：线程正在运行的时候，被暂停。通常是资源不足。sleep,suspend，wait等方法都可以导致线程阻塞
 * 5.死亡状态：一个线程的run()方法执行结束或者调用stop()方法后，线程就会死亡，对于已经死亡的线程，无法在使用start()方法令其进入就绪状态
 */
/*
 * wait(),notify(),notifyAll()不属于Thread类,而是属于Object基础类,也就是说每个对像都有wait(),notify(),notifyAll()的功能，因为每个对象都有锁。
 * wait()：当前线程放弃对象锁，进入等待状态。只有在其他线程通过notify()唤醒后，在进入就绪状态。
 * wait()方法和nitify()方法必须在同步块内执行，即synchronized(obj之内)。
 */
/*
 * Monitor是 Java中用以实现线程之间的互斥与协作的主要手段，它可以看成是对象或者 Class的锁。每一个对象都有，也仅有一个 monitor。
 * 每个 Monitor在某个时刻，只能被一个线程拥有。
 */
/*
 * 被 synchronized保护起来的代码段为临界区。当一个线程申请进入临界区时，它就进入了就绪状态。
 * 临界区的设置，是为了保证其内部的代码执行的原子性和完整性。但是因为临界区在任何时间只允许线程串行通过，这 和我们多线程的程序的初衷是相反的。
 */
public class Consume implements Runnable {
	private List container = null;  
    private int count;  
    
    public Consume(List lst){  
     this.container = lst;  
    }  
    
	 public void run() {  
	  while(true){  
		  
	   synchronized (container) {  
		   
		    if(container.size()== 0)
		    {  
			     try 
			     {  
			      container.wait();//放弃锁
			      System.out.println("资源不满足，从运行态进入阻塞态");
			     } 
			     catch (InterruptedException e) 
			     {  
			      e.printStackTrace();  
			     }  
		    }  
		    try 
		    {  
		     Thread.sleep(100);  
		    } 
		    catch (InterruptedException e) 
		    {  
		     e.printStackTrace();  
		    }  
		    container.remove(0);  
		    container.notify();  
		    System.out.println("我吃了"+(++count)+"个");  
	   }//同步块结束  
	 }//while结束  
   }//run()方法结束  

}
