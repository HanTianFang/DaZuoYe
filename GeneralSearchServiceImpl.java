package cn.edu.bjtu.weibo.impl;//�½�һ��impl������service����ʵ����


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
		

		//����΢�����ݣ�����SearchContentServiceʵ����ΪSearchContentServiceImpl�����������
		SearchContentServiceImpl Content = new SearchContentServiceImpl();
		List<BaseContentSR> SearchedWeiboList = Content.getSearchedWeiboList(keyword, pageIndex, numberPerPage);
		
		//�������⣬����SearchTopicServiceʵ����ΪSearchTopicServiceImpl�����������
		SearchTopicServiceImpl Topic = new SearchTopicServiceImpl();
		List<BaseContentSR> SearchTopicList = Topic.getSearchedContentWithTopicList(keyword, pageIndex, numberPerPage);
				
		//�����û���������SearchUserServiceʵ����ΪSearchUserServiceImpl�����������
		SearchUserServiceImpl Users = new SearchUserServiceImpl();
		List<UserSR> SearchUserList = Users.getSearchedUserList(keyword, pageIndex, numberPerPage);
		
		List<GeneralSR> GeneralList = null;//����GeneralSR�б��洢�������
		GeneralSR a=null;
		a.setResult(SearchedWeiboList);
		a.setType(Weibo);//��model����EntityType����һ�£�΢������
		GeneralList.add(a);
		
		GeneralSR b=null;
		b.setResult(SearchTopicList);
		b.setType(Comment);//��model����EntityType����һ�£��������ͣ��о�model����д����
		GeneralList.add(b);
		
		GeneralSR c=null;
		c.setResult(SearchUserList);
		c.setType(User);//��model����EntityType����һ�£��û�����
		GeneralList.add(c);
		
		return GeneralList;
	}

}
