package com.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.biz.AdminBiz;
import com.admin.entity.Admin;
import com.admin.entity.Goods;
import com.admin.entity.Type;


@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private AdminBiz adminBiz;
	
	@RequestMapping(value="/login.do")
	@ResponseBody
	public int login(String a_name,String a_pwd,String checkNum,HttpSession session){
		int flag=-1;
		Admin a1=null;
		a1=adminBiz.findByName(a_name);
		Admin a2=null;
		a2=adminBiz.login(a_name, a_pwd);
		session.setAttribute("a_name",a_name);
		String key=session.getAttribute("RANDOMCODEKEY").toString();
		//System.out.println(checkNum+' '+key);
		if(checkNum.toLowerCase().equals(key.toLowerCase())){
			if(a1!= null){  //�ҵ��˻����˻�����
				if(a2!=null){ //�˻�������֤�ɹ����û��ɵ�¼
					flag=1;
				}if(a2==null){ //�˺Ŵ��ڣ����벻��ȷ
					flag=2;
				}
			}else{
				flag=3; //�˺Ų�����
			}
		}else{  //��֤�����
			flag=0;
		}
		
		return flag;
	}
	
	@RequestMapping(value="/manageGoods.do")
	public String manageGoods(Integer index1,Integer index2,ModelMap map){
		if (index1 == null) {
			index1 = 1;
		}
		if (index2 == null) {
			index2 = 1;
		}
		int size=4;
		
		int sumG=adminBiz.goodsCount();
		int sumT=adminBiz.typeCount();
		int totalGP = sumG % size == 0 ? sumG / size : sumG / size + 1;
		int totalTP=sumT%size==0?sumT/size:sumT/size+1;
		
		List<Goods> list1=adminBiz.checkGoods(index1,size);
		List<Type> list2 =adminBiz.findAllType(index2,size);
		
		map.put("list1", list1);
		map.put("list2", list2);
		map.put("index1", index1);
		map.put("index2", index2);
		map.put("totalGP", totalGP);
		map.put("totalTP", totalTP);
		map.put("sumG", sumG);
		//System.out.println(list1);
		
		
		
		return "manageGoods";
	}
	
	@RequestMapping(value="/pass.do")
	public 	String pass(int g_id){
		adminBiz.pass(g_id);
		return "forward:manageGoods.do";
		
	}
	
	
	@RequestMapping(value="/changeType.do")
	@ResponseBody
	public int  changType(String t_name, int t_id){
		int flag=0;
		Type type=null;
		type=adminBiz.checkType(t_name);
		if(type !=null){
			flag=0;     //���ִ��ڣ����ܸ�
		}else {
			
			adminBiz.changTypeName(t_name, t_id);
			flag=1;   //��
		}
		return flag;		
	}
	@RequestMapping(value="/addType.do")
	@ResponseBody
	public int addType(String t_name){
		System.out.println(t_name);
		int flag=0;
		Type type=null;
		type=adminBiz.checkType(t_name);
		if(type !=null){
			flag=0;     //���ִ��ڣ��������
		}else {
			System.out.println("����");
			adminBiz.addType(t_name);     
			flag=1;  //�������
		}
		return flag;
		
	}
	
	
	
}
