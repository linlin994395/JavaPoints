package cn.edu.neu.xl.ThreadLocal;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//三个线程共享同一个资源  
        NumCreator creator=new NumCreator();  
        DemoThread thread1=new DemoThread(creator);  
        DemoThread thread2=new DemoThread(creator);  
        DemoThread thread3=new DemoThread(creator);  
          
        thread1.start();  
        thread2.start();  
        thread3.start(); 
	}

}
