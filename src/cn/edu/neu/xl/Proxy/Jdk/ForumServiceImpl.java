package cn.edu.neu.xl.Proxy.Jdk;

import cn.edu.neu.xl.Proxy.ForumService;

/**
 * @author xl
 * @created 2017_09_07
 *
 */
public class ForumServiceImpl implements ForumService {

	@Override
	public void removeTopic(int topicId) {
		System.out.println("模拟删除Topic记录:" + topicId);
		try {
			Thread.currentThread().sleep(20);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void removeForum(int forumId) {
		System.out.println("模拟删除Forum记录:" + forumId);
		try {
			Thread.currentThread().sleep(40);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
