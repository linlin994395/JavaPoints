package cn.edu.neu.xl.Singleton;

/*
 * 能在多线程中很好的工作，遗憾的是，效率很低，99%情况下不需要同步
 */
public class LazySingleton {
	//属性是静态的
	private static LazySingleton instance;
	//构造方法是私有的，不允许通过 new 来实例化
    private LazySingleton (){}  
  
   //获得实例的方法是静态的，与属性是静态的相对应
    public static synchronized LazySingleton getInstance() {  
    if (instance == null) {  
        instance = new LazySingleton();  
    }  
    return instance;  
    }  

}
