package cn.edu.neu.xl.Concurrent;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
 * http://blog.csdn.net/heyutao007/article/details/5672799
 */
/*
 * 使用了java5中的线程池，阻塞队列，可重入锁，实践了Callable， Future等接口，还使用了java5的新特性泛型
 */
/*
 * 网络服务器模型：一旦有客户端连接到该服务器，则启动一个新线程为该连接服务。
 * 1.建立监听端口
 * 2.发现有新连接，接受连接，启动线程，执行服务线程。
 * 3.服务完毕，关闭线程
 *  
 * 问题：频繁的处理用户请求而每次请求需要的服务又是简短的时候，系统会将大量的时间花费在线程的创建销毁。Java 5的线程池克服了这些缺点。
 * 通过对重用线程来执行多个任务，避免了频繁线程的创建与销毁开销，使得服务器的性能方面得到很大提高。
 * 
 * 1.建立监听端口，创建线程池
 * 2.发现有新连接，使用线程池来执行服务任务
 * 3.服务完毕，释放线程到线程池
 */
/*
 *  创建线程池可以通过调用java.util.concurrent.Executors类里的静态方法newChahedThreadPool或是 newFixedThreadPool来创建，
 *  也可以通过新建一个java.util.concurrent.ThreadPoolExecutor实例 来执行任务。
 */
public class Server {
	private static int produceTaskSleepTime = 100;
	private static int consumeTaskSleepTime = 1200;
	private static int produceTaskMaxNumber = 100;
	private static final int CORE_POOL_SIZE = 2;
	private static final int MAX_POOL_SIZE = 100;
	private static final int KEEPALIVE_TIME = 3;
	private static final int QUEUE_CAPACITY = (CORE_POOL_SIZE + MAX_POOL_SIZE) / 2;
	private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 19527;
	private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(
			QUEUE_CAPACITY);
	// private ThreadPoolExecutor serverThreadPool = null;

	private ExecutorService pool = null;
	private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.DiscardOldestPolicy();
	private ServerSocket serverListenSocket = null;
	private int times = 5;

	public void start() {
		// You can also init thread pool in this way.
		/*
		 * serverThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,
		 * MAX_POOL_SIZE, KEEPALIVE_TIME, TIME_UNIT, workQueue,
		 * rejectedExecutionHandler);
		 */
		//线程池的大小为10  创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。
		pool = Executors.newFixedThreadPool(10);
		try {
			serverListenSocket = new ServerSocket(PORT);
			serverListenSocket.setReuseAddress(true);
			System.out.println("I'm listening");
			//初始创建5个线程供客户端请求
			while (times-- > 0) {
				Socket socket = serverListenSocket.accept();
				String welcomeString = "hello";
				// serverThreadPool.execute(new ServiceThread(socket,
				// welcomeString));
				//使用线程池对象来执行线程，减少了每次线程创建和销毁的开销。任务执行完毕，线程释放到线程池。
				pool.execute(new ServiceThread(socket));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cleanup();
	}

	public void cleanup() {
		if (null != serverListenSocket) {
			try {
				serverListenSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// serverThreadPool.shutdown();
		pool.shutdown();
	}

	public static void main(String args[]) {
		Server server = new Server();
		server.start();
	}
}

//另外的类---服务线程
/*
 * 由于可能多个线程存在竞争，同时访问count，因此需要加锁机制，
 * 在Java 5之前，我们只能使用synchronized来锁定。Java 5中引入了性能更加粒度更细的重入锁ReentrantLock。
 */
/*
 * 使用ExecutorService的submit方法提 交一个Callable的任务，返回一个Future接口的引用。
 * 这种做法对费时的任务非常有效，submit任务之后可以继续执行下面的代码，然后在适 当的位置可以使用Future的get方法来获取结果，
 * 如果这时候该方法已经执行完毕，则无需等待即可获得结果，如果还在执行，则等待到运行完毕。
 */
class ServiceThread implements Runnable, Serializable {
	private static final long serialVersionUID = 0;
	//服务端接受后返回的连接socket
	private Socket connectedSocket = null;
	private String helloString = null;
	//已接受客户端连接的个数
	private static int count = 0;
	//可重入锁
	private static ReentrantLock lock = new ReentrantLock();

	ServiceThread(Socket socket) {
		connectedSocket = socket;
	}

	public void run() {
		//涉及到数据的代码就要加锁
		//增加个数
		increaseCount();
		//得到个数
		int curCount = getCount();
		helloString = "hello, id = " + curCount + "/r/n";
		//创建一个执行线程去做另一件事情
		ExecutorService executor = Executors.newSingleThreadExecutor();
		//提交一个Callable的任务  Future 表示异步计算的结果
		Future<String> future = executor.submit(new TimeConsumingTask());
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(connectedSocket.getOutputStream());
			dos.write(helloString.getBytes());
			try {
				dos.write("let's do soemthing other./r/n".getBytes());
				String result = future.get();
				dos.write(result.getBytes());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != connectedSocket) {
				try {
					connectedSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != dos) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			executor.shutdown();
		}
	}

	private int getCount() {
		int ret = 0;
		try {
			lock.lock();
			ret = count;
		} finally {
			lock.unlock();
		}
		return ret;
	}

	private void increaseCount() {
		try {
			lock.lock();
			++count;
		} finally {
			lock.unlock();
		}
	}
}

//一个新的类
/*
 * 随时可偿还的
 */
/*
 * 声明TimeConsumingTask的时候使用了String做为类型参数。必须实现Callable接口的call函数， 
 * 其作用类似与Runnable中的run函数，在call函数里写入要执行的代码，其返回值类型等同于在类声明中传入的类型值。
 * 在这段程序中，我们提交了 一个Callable的任务，然后程序不会堵塞，而是继续执行dos.write("let's do soemthing other".getBytes());
 * 当程序执行到String result = future.get()时如果call函数已经执行完毕，则取得返回值，如果还在执行，则等待其执行完毕。 
 */
class TimeConsumingTask implements Callable<String> {
	public String call() throws Exception {
		System.out.println("It's a 	time-consuming task, you'd better retrieve your result in the furture");
		return "ok, here's the result: It takes me lots of time to produce this result";
	}
	
}

