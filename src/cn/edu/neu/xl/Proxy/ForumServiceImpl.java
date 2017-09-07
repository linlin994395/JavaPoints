package cn.edu.neu.xl.Proxy;

/**
 * @author xl
 * @created 2017_09_07
 *
 */
public class ForumServiceImpl implements ForumService {

	@Override
	public void removeTopic(int topicId) {
		// ①-1开始对该方法进行性能监视
		PerformanceMonitor.begin("cn.edu.neu.xl.Proxy.ForumServiceImpl.removeTopic");
		System.out.println("模拟删除Topic记录:" + topicId);
		try {
			Thread.currentThread().sleep(20);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// ①-2结束对该方法进行性能监视
		PerformanceMonitor.end();
	}

	@Override
	public void removeForum(int forumId) {
		//②-1开始对该方法进行性能监视  
        PerformanceMonitor.begin("cn.edu.neu.xl.Proxy.ForumServiceImpl.removeForum");  
        System.out.println("模拟删除Forum记录:"+forumId);  
        try {  
            Thread.currentThread().sleep(40);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }         
  
         //②-2结束对该方法进行性能监视  
        PerformanceMonitor.end(); 
	}

}
