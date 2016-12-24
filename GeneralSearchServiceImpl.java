package cn.edu.bjtu.weibo.impl;//新建一个impl包，放service包的实现类


import java.util.List;

import cn.edu.bjtu.weibo.model.*;
import cn.edu.bjtu.weibo.service.GeneralSearchService;


public class GeneralSearchServiceImpl implements GeneralSearchService{

	

	private static final EntityType Weibo = null;
	private static final EntityType Comment = null;
	private static final EntityType User = null;

	@SuppressWarnings("null")
	@Override
	public List<GeneralSR> getGeneralSearchedResult(String keyword,
			int pageIndex, int numberPerPage) {
		// TODO Auto-generated method stub
		

		//搜索微博内容，假设SearchContentService实现类为SearchContentServiceImpl，不是请改正
		SearchContentServiceImpl Content = new SearchContentServiceImpl();
		List<BaseContentSR> SearchedWeiboList = Content.getSearchedWeiboList(keyword, pageIndex, numberPerPage);
		
		//搜索话题，假设SearchTopicService实现类为SearchTopicServiceImpl，不是请改正
		SearchTopicServiceImpl Topic = new SearchTopicServiceImpl();
		List<BaseContentSR> SearchTopicList = Topic.getSearchedContentWithTopicList(keyword, pageIndex, numberPerPage);
				
		//搜索用户名，假设SearchUserService实现类为SearchUserServiceImpl，不是请改正
		SearchUserServiceImpl Users = new SearchUserServiceImpl();
		List<UserSR> SearchUserList = Users.getSearchedUserList(keyword, pageIndex, numberPerPage);
		
		List<GeneralSR> GeneralList = null;//建立GeneralSR列表，存储搜索结果
		GeneralSR a=null;
		a.setResult(SearchedWeiboList);
		a.setType(Weibo);//与model包里EntityType类相一致，微博类型
		GeneralList.add(a);
		
		GeneralSR b=null;
		b.setResult(SearchTopicList);
		b.setType(Comment);//与model包里EntityType类相一致，话题类型，感觉model单词写错了
		GeneralList.add(b);
		
		GeneralSR c=null;
		c.setResult(SearchUserList);
		c.setType(User);//与model包里EntityType类相一致，用户类型
		GeneralList.add(c);
		
		return GeneralList;
	}

}
