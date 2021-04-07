package com.evaluation.mapper;

import java.util.List;
import java.util.Map;

import com.evaluation.entity.Evaluation;

public interface EvaluationMapper {
	public List<Evaluation> findEvaBySeId(Map<String,Object> map);
	public List<Evaluation> findEvaByBuId(Map<String,Object> map);
	//�ܼ�¼��
	public int count(Map<String,Object> map);
	public int count1(Map<String,Object> map);
	
	//��������
	public void addEva(Evaluation eva);
	
	//ͨ��g_id��������
	public Evaluation findEvaByGid(int g_id);
	
}
