package com.notice.mapper;

import java.util.List;
import java.util.Map;

import com.notice.entity.Notice;
import com.user.entity.User;

public interface NoticeMapper {
	//��
	public void addNotice(Notice notice);
	//ɾ
	public void delNotice(int id);
	//��
	public void updateNotice(Notice notice);
	
	//��ҳ��ѯ
	public List<Notice> findNoPage(Map<String,Object> map);
	
	//ģ����ѯ
	public List<Notice> findBycontent(Map<String,Object> map);
	
	//�ܼ�¼��
	public int countPage();
	public int count(Map<String,Object> map);
	
	public Notice findByNoId(int id);
}
