package cn.edu.neu.xl.Proxy.Jdk;

import java.lang.reflect.Proxy;

import cn.edu.neu.xl.Proxy.ForumService;

/**
 * @author xl
 * @created 2017_09_07
 *
 */
public class TestForumService {
	public static void main(String[] args) {
		// ①希望被代理的目标业务类
		ForumService target = new ForumServiceImpl();

		// ②将目标业务类和横切代码编织到一起
		PerformanceHandler handler = new PerformanceHandler(target);

		/**
		 * loader:一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
		 * interfaces:一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，
		 * 如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
		 * h:一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
		 */
		/**
		 * System.out.println(proxy.getClass().getName());
		 * $Proxy0
		 * 通过 Proxy.newProxyInstance 创建的代理对象是在jvm运行时动态生成的一个对象，它并不是我们的InvocationHandler类型，
		 * 也不是我们定义的那组接口的类型，而是在运行是动态生成的一个对象，并且命名方式都是这样的形式，以$开头，proxy为中，最后一个数字表示对象的标号。
		 */
		// ③根据编织了目标业务类逻辑和性能监视横切逻辑的InvocationHandler实例创建代理实例
		ForumService proxy = (ForumService) Proxy.newProxyInstance(target
				.getClass().getClassLoader(),
				target.getClass().getInterfaces(), handler);

		// ④调用代理实例
		proxy.removeForum(10);
		proxy.removeTopic(1012);
	}

}
