package com.notice.biz;

import java.util.List;

import com.notice.entity.Notice;

public interface NoticeBiz {
	//��
	public void addNotice(Notice notice); 
	
	//ɾ
	public void delNotice(int id);
	
	//��ҳ������
	public List<Notice> findNoPage(int index,int size);
	
	//�ܼ�¼��
	public int countPage();
	public int count(String n_content);
	
	//ģ����ѯ
	public List<Notice> findBycontent (int index,int size,String n_content);
	
	//��
	public void updateNotice(Notice notice);

	public Notice findByNoId(int n_id);
}
