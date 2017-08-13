package cn.edu.neu.xl.Concurrent;

/**
 * @description 
 *              多线程卖票的第二种实现方法，资源（Ticket）只是普通的实体类，没有实现多线程的体现。在实际操作的时候引入Runnable并在其run函数里操作资源
 *              。 注意： synchronized锁住方法（run函数）和
 *              synchronized锁住共享变量ticket代码块，最后执行结果是不一样的。
 *              前者从开始到结束只有一个线程执行；后者多个线程交替执行。
 *              如果去掉 while(true){} 两种情况则一样，原因在于若存在 while(true){}，则前者执行方法是原子的，而方法会一直运行；后者不会出现这种情况。
 * @author xl
 * @created 2017_08_12
 *
 */
class Ticket_Sell {
	private int ticket = 60;

	public void sellTicket() {
		while (true) {
			//ticket是共享变量，所以有关ticket的操作都应该加锁
			synchronized (this) {
				if (ticket == 0) {
					System.out.println("线程" + Thread.currentThread().getName()
							+ ": 抱歉，票已售完");
					return;
				}
				
				System.out.println("线程" + Thread.currentThread().getName()
						+ "： ticket = " + ticket--);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class TicketDemo_Two {
	public static void main(String[] args) {
		final Ticket_Sell ticket = new Ticket_Sell();
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					ticket.sellTicket();
				}
			}, i + 1 + "").start();
		}

		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { ticket.sellTicket(); } }, "2").start();
		 * 
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { ticket.sellTicket(); } }, "3").start();
		 */
	}
}
