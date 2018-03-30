package cn.edu.neu.xl.Singleton;

/*
 * 初始化需要消费资源的，若对资源十分在意，选这种方法
 */
public class InnerSingleton {
	private InnerSingleton() {  
    }  
  
    private static class InnerSingletonHolder {  
        static InnerSingleton instance = new InnerSingleton();  
    }  
  
  
    public static InnerSingleton getInstance() {  
        return InnerSingletonHolder.instance;  
    }  

}
