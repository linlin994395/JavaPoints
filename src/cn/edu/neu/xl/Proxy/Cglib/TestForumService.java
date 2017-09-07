package cn.edu.neu.xl.Proxy.Cglib;

import cn.edu.neu.xl.Proxy.ForumServiceImpl;

/**
 * @author xl
 * @created 2017_09_07
 *
 */
public class TestForumService {
	public static void main(String[] args) {  
	      CglibProxy proxy = new CglibProxy(); 
	      //通过CglibProxy为ForumServiceImpl动态创建了一个织入性能监视逻辑的代理对象，并调用代理类的业务方法
	      ForumServiceImpl forumService = (ForumServiceImpl )proxy.getProxy(ForumServiceImpl.class);  
	      forumService.removeForum(10);  
	      forumService.removeTopic(1023);  
	    }  
}
