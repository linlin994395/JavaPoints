package cn.edu.neu.xl.Proxy;

/**
 * @author xl
 * @created 2017_09_07
 *
 */
public class TestForumService {

	public static void main(String[] args) {
		ForumService forumService = new ForumServiceImpl();
		forumService.removeForum(10);
		forumService.removeTopic(1012);
	}

}
