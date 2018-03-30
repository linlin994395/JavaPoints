package cn.edu.neu.xl.ThreadLocal;

/*
 * 使用ThreadLocal的数字生成器 
 */
public class NumCreator {
	
	private static DemoThreadLocal threadLocal=new DemoThreadLocal();  
     
	public NumCreator() { }  
	 
	//该方法返回当前线程所对应的线程局部变量。threadLocal.get()
    public int getNextNum() {          
        int num= ((Integer)threadLocal.get()).intValue();  
        threadLocal.set(new Integer(num+1));  
        return num;  
    }  

}
