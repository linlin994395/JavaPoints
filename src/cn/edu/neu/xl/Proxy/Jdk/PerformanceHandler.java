package cn.edu.neu.xl.Proxy.Jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import cn.edu.neu.xl.Proxy.PerformanceMonitor;

/**
 * @author xl
 * @created 2017_09_07
 *
 */
public class PerformanceHandler implements InvocationHandler {// ①实现InvocationHandler
	private Object target;

	public PerformanceHandler(Object target) { // ②target为目标的业务类
		this.target = target;
	}

	/**
	 * @param proxy:最终生成的代理实例，一般不会用到
	 * @param method:被代理目标实例的某个具体方法，通过它可以发起目标实例方法的反射调用
	 * @param args:通过被代理实例某一个方法的入参，在方法反射调用时使用
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		PerformanceMonitor.begin(target.getClass().getName()+"."+ method. getName());//③-1  
        Object obj = method.invoke(target, args);// ③-2通过反射方法调用业务类的目标方法  
        PerformanceMonitor.end();//③-1  
        return obj; 
	}
}
