package com.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.user.entity.User;

public interface UserMapper {	
	//�޸�
	public void updateUser(User user);
	//ͨ��id��
	public User findByuId(int id);
	//ע��
	public void registUser(User u);
	//����
	public User login(@Param("u_sid") String u_sid,@Param("u_pwd") String u_pwd);
	//ͨ��sid��
	public User findById(String u_sid);
	//��������
	public List<User> findAllUsers(Map<String,Object> map);
	
	public void examine(int u_id);	
	public int countUsers();
	public void reopen(int u_id);
	public void freeze(int u_id);
	//�����û���Ų����û�
	public User findUserById(int u_id);
	//�޸��û���Ϣ
	public void update(User u);
	//�޸�����
	public void updatePwd(Map<String,Object> map);
	//ɾ���û�
	public void del(int u_id);
	//ģ����ѯ�û�
	public List<User> findLike(Map<String,Object> map);
	public int countUsersBySid(String find);
	//���˺����Ͳ�ѯ�û�
	public List<User> findFlag(Map<String,Object> map);
	public int countUsersByFlag(int u_flag);
}
