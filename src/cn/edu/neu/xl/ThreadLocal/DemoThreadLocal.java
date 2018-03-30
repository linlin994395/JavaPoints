package cn.edu.neu.xl.ThreadLocal;

/*
 * http://blog.csdn.net/heyutao007/article/details/5950759
 */
/*
 * 自定义ThreadLocal
 * 使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
 * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 * ThreadLocal是如何做到为每一个线程维护变量的副本的呢？其实实现的思路很简单：
 * 在ThreadLocal类中有一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值对应线程的变量副本。
 */
public class DemoThreadLocal extends ThreadLocal<Integer> {

	/*
	 * (non-Javadoc)
	 * @see java.lang.ThreadLocal#initialValue()
	 * 返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的。
	 * 这个方法是一个延迟调用方法，在线程第1次调用get()或set(Object)时才执行，并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null。
	 */
	@Override
	protected Integer initialValue() {
		return  new Integer(0);
	}

}
