package com.goods.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.goods.entity.Goods;
import com.goods.entity.Notice;
import com.goods.entity.Type;


public interface GoodsMapper {
	public List<Type> findAllType();
	public List<Notice> findAllNotices();
	public List<Goods> findAllGoods(Map<String, Object> map);
	public List<Goods> findGoods(Map<String, Object> map);
	public List<Goods> findGoodsByType(int t_id);
	public int count();
	
	public Goods findByGId(int g_id);
	public Goods checkGoods(@Param("g_id") int g_id,@Param("u_id") int u_id);
	public void inCard(@Param("g_id") int g_id,@Param("u_id") int u_id);
	public List<Goods> card(int u_id);
	public  int cardNum(int u_id);
	public void cardDelete(int g_id);
	public void pay(int g_id);
	//��ѯ�ҵķ�������
	public List<Goods> findGoodsBySeller(Map<String, Object> map);
	//��ѯ������������
	public int countGoods(int u_id);
	//��ѯ���򶩵�����
	public int countBuyGoods(int u_id);
	//ȡ��/ɾ���ҷ����Ķ���
	public void delGoods(int g_id);
	//����Ʒ��Ų�ѯ�ҵķ�����Ϣ
	public Goods findGoodsById(int g_id);
	//�޸��ҵķ�����Ϣ����
	public void updateGoods(Goods g);
	//����ҵķ���
	public void addGoods(Goods goods);
	//��ѯ��������
	public List<Goods> findGoodsLike(Map<String,Object> map);
	//ģ����ѯ���򶩵������ձ��/��Ʒ����/��Ʒ���
	public List<Goods> findGoodsLike2(Map<String,Object> map);
	//��ѯ������Ʒ�Ķ�������
	public int countByFind(Map<String,Object> map);
	//��ѯ������Ʒ�Ĺ��򶩵�����
	public int countByBuyFind(Map<String,Object> map);
	//��ѯ���򵽵�
	public List<Goods> findGoodsByBuyer(Map<String, Object> map);
	//��ѯ��������
	public List<Goods> findGoodsBySelled(Map<String, Object> map);
	//��ѯ�ҵĳ��۶���
	public List<Goods> findGoodsBySeller2(Map<String, Object> map);
}
