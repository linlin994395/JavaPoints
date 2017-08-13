package cn.edu.neu.xl.Concurrent;

/**
 * @description 多线程卖票的第一种实现方法，在资源（Ticket）上实现Runnable接口。 注意：
 *              synchronized锁住方法（run函数）和
 *              synchronized锁住共享变量ticket代码块，最后执行结果是不一样的。
 *              前者从开始到结束只有一个线程执行；后者多个线程交替执行。
 *              如果去掉 while(true){} 两种情况则一样，原因在于若存在 while(true){}，则前者执行方法是原子的，而方法会一直运行；后者不会出现这种情况。
 * @author xl
 * @created 2017_08_12
 *
 */
class Ticket implements Runnable {
	private int ticket = 60;

	public void run() {
		while (true) {
			synchronized (this) {
				//ticket是共享变量，所以有关ticket的操作都应该加锁
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

public class TicketDemo_One {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(ticket, "1").start();
		new Thread(ticket, "2").start();
		new Thread(ticket, "3").start();
	}
}
