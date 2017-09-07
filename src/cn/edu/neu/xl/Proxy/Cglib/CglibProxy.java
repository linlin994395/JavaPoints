package cn.edu.neu.xl.Proxy.Cglib;

import java.lang.reflect.Method;

import cn.edu.neu.xl.Proxy.PerformanceMonitor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author xl
 * @created 2017_09_07
 *
 */
public class CglibProxy implements MethodInterceptor {
	private Enhancer enhancer = new Enhancer();  
    public Object getProxy(Class clazz) {  
        enhancer.setSuperclass(clazz); //① 设置需要创建子类的类  
        enhancer.setCallback(this);   
        return enhancer.create(); //②通过字节码技术动态创建子类实例  
   
    }  
    
    //③拦截父类所有方法的调用
    /**
     * @param obj:目标类的实例
     * @param method:目标类方法的反射对象
     * @param args:方法的动态入参
     * @param proxy:代理类实例
     */
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		PerformanceMonitor.begin(obj.getClass().getName()+"."+method. getName());//③-1  
        Object result=proxy.invokeSuper(obj, args);//③-2   
        PerformanceMonitor.end();//③-1通过代理类调用父类中的方法  
        return result; 
	}

}
