package cn.edu.neu.xl.Concurrent;

/**
 * @description  每个线程单独卖了5张票，即独立地完成了卖票的任务，但实际应用中，比如火车站售票，需要多个线程去共同完成任务，
 * @author leo
 *
 */
class Ticket_Thread extends Thread {
	private int ticket = 5;

	@Override
	public void run() {
		while (true) {
			if (ticket > 0) {
				System.out.println("线程" + Thread.currentThread().getName()
						+ ": ticket = " + ticket--);
			}
		}
	}
}

public class TicketDemo_Three {
	public static void main(String[] args){  
        new Ticket_Thread().start();  
        new Ticket_Thread().start();  
        new Ticket_Thread().start();  
    }  
}
