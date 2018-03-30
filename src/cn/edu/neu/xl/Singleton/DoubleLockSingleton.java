package cn.edu.neu.xl.Singleton;

/*
 * 双重检查锁定背后的理论是完美的。不幸地是，现实完全不同。双重检查锁定的问题是：并不能保证它会在单处理器或多处理器计算机上顺利运行。
        双重检查锁定失败的问题并不归咎于 JVM 中的实现 bug，而是归咎于 Java 平台内存模型。内存模型允许所谓的“无序写入”，这也是这些习语失败的一个主要原因。
 */
/*
 * 无序写入：3步代码创建了一个 Singleton 对象并初始化变量 instance 来引用此对象。这行代码的问题是：在 Singleton 构造函数体执行之前，变量 instance 可能成为非 null 的。
 */
public class DoubleLockSingleton {
	private static DoubleLockSingleton instance = null;   
	
	private DoubleLockSingleton() { }   
    
  
    public static DoubleLockSingleton getInstance() {   
        if (instance == null) {   
            synchronized (DoubleLockSingleton.class) {   
                if (instance == null)    //此处第二次检查使，使创建两个不同的 Singleton 对象成为不可能
                    instance = new DoubleLockSingleton();     //3步
            }   
        }   
        return instance;   
    }   

}
