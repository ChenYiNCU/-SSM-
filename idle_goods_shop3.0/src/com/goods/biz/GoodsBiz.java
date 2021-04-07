package com.goods.biz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.goods.entity.Goods;
import com.goods.entity.Notice;
import com.goods.entity.Type;

public interface GoodsBiz {
	public List<Type> findAllType();
	public List<Notice> findAllNotices();
	public List<Goods> findAllGoods(int index,int size);
	public List<Goods> findGoods(String g_name);
	public List<Goods> findGoodsByType(int t_id);
	public int count();
	public Goods findByGId(int g_id);
	public Goods checkGoods(int g_id,int u_id);
	public void inCard(int g_id,int u_id);
	public List<Goods> card(int u_id);
	
	public  int cardNum(int u_id);
	
	public void cardDelete(int g_id);
	public void pay(int g_id);
	//��ѯ�ҵķ�������
	public List<Goods> findGoodsBySeller(int u_id,int index,int size);
	//��ѯ������������
	public int countGoods(int u_id);
	//��ѯ���򶩵�����
	public int countBuyGoods(int u_id);
	//ɾ���ҵķ�������
	public void delGoods(int g_id);
	//���ݱ�Ų�ѯ�ҷ�������Ʒ��Ϣ
	public Goods findGoodsById(int g_id);
	//�޸��ҷ�������Ʒ��Ϣ
	public void updateGoods(Goods g);
	//����ҵķ���
	public void addGoods(Goods goods);
	//ģ����ѯ
	public List<Goods> findGoodsLike(int find_id,String find,int u_id,int index,int size);
	//ģ����ѯ���򶩵�
	public List<Goods> findGoodsLike2(int find_id,String find,int u_id,int index,int size);
	//��ѯ�ҹ���Ķ���
	public List<Goods> findGoodsByBuyer(int u_id,int index,int size);
	//�����ķ�����¼��
	public int countByFind(int find_id,String find,int u_id);
	//�����ķ�����¼��
	public int countByBuyFind(int find_id,String find,int u_id);
	//��ѯ�������Ķ���
	public List<Goods> findGoodsBySelled(int u_id,int index,int size);
	//��ѯ�ҵķ�������
	public List<Goods> findGoodsBySeller2(int u_id,int index,int size);
}
