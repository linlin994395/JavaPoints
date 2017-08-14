package cn.edu.neu.xl.Concurrent;

/**
 * @description  每个线程单独卖了5张票，即独立地完成了卖票的任务，但实际应用中，比如火车站售票，需要多个线程去共同完成任务，
 * @author xl
 *
 */
class Ticket_Thread extends Thread {
	private int ticket = 60;

	//构造方法默认只有一个，想按照Thread类实现多个需要重载
	public Ticket_Thread() {
	}
	
	public Ticket_Thread(String name) {
		super(name);
	}
	
	public Ticket_Thread(Runnable target) {
		super(target);
	}
	
	public Ticket_Thread(ThreadGroup group, String name) {
		super(group, name);
	}
	
	public Ticket_Thread(ThreadGroup group, Runnable target) {
		super(group, target);
	}
	
	public Ticket_Thread(Runnable target, String name) {
		super(target, name);
	}
	
	public Ticket_Thread(ThreadGroup group, Runnable target, String name) {
		super(group,target, name);
	}
	public Ticket_Thread(ThreadGroup group, Runnable target, String name,long stackSize) {
		super(group,target, name,stackSize);
	}

	@Override
	public void run() {
		while (true) {
			//ticket是共享变量，所以有关ticket的操作都应该加锁
			if (ticket == 0) {
				System.out.println("线程" + Thread.currentThread().getName()
						+ ": 抱歉，票已售完");
				return;
			}
			
			System.out.println("线程" + Thread.currentThread().getName()
						+ ": ticket = " + ticket--);
		}
	}
}

public class TicketDemo_Three {
	public static void main(String[] args){
		//因为Ticket_Thread类中已经对构造方法按照Thread类的种类进行重载，可以按照Thread的风格进行函数构造
        new Ticket_Thread().start();  
        new Ticket_Thread().start();  
        new Ticket_Thread().start();  
    }  
}
