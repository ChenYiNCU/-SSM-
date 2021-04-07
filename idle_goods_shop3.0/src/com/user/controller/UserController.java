package com.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.biz.UserBiz;
import com.user.entity.RandomValidateCode;
import com.user.entity.User;


@Controller
@RequestMapping("user")
public class UserController{
	@Autowired
	private UserBiz userBiz;
	
	@RequestMapping(value="/findByuId.do")
	@ResponseBody
	public User findById(Integer se_id,HttpSession session) {
		String id="";
		if(se_id==1){
			id = session.getAttribute("se_id").toString();
		}
		else{
			id = session.getAttribute("u_id").toString();
		}
		Integer id1=Integer.valueOf(id);
		int u_id=id1.intValue();
		User user = userBiz.findByuId(u_id);
		return user;
	}
	
	@RequestMapping(value="/findById.do")
	@ResponseBody
	public int findById(String u_sid){	
		User u= null;
		int flag=0;
		u= userBiz.findById(u_sid);
		if(u!=null){   //�˻��Ѵ���
			flag=1;
		}
		return flag;	
	}
	
	@RequestMapping(value="/reg.do")
	@ResponseBody
	public String registUser(String u_name,String u_pwd,String u_sid,String u_class,String u_margin){
		User u=new User();
		u.setU_name(u_name);
		u.setU_pwd(u_pwd);
		u.setU_sid(u_sid);
		u.setU_class(u_class);
		u.setU_margin(u_margin);
		u.setU_flag(0);
		userBiz.registUser(u);
		return "ok";
	}
	
	@RequestMapping(value="/login.do")
	@ResponseBody
	public int login(String u_sid,String u_pwd,String checkNum,HttpSession session,ModelMap map){
		int flag=-1;
		int u_flag=-1;
		User u1=null;
		u1=userBiz.findById(u_sid);
		User u2=null;
		u2=userBiz.login(u_sid, u_pwd);
		String key=session.getAttribute("RANDOMCODEKEY").toString();
		//System.out.println(checkNum+' '+key);
		if(u2!=null){
			u_flag=u2.getU_flag();
		}
		if(checkNum.toLowerCase().equals(key.toLowerCase())){
			if(u1!= null){  //�ҵ��˻����˻�����
				if(u2!=null){ //�˻�������֤�ɹ�
					if(u_flag==1){ //��ǰ�˻����Ե�½
						String name=u2.getU_name();
						int id=u2.getU_id();
						session.setAttribute("u_name", name);
						session.setAttribute("u_id", id);
						//session.setAttribute("u_flag", u_flag);
						flag=1;
					}
					if(u_flag==0){  //��ǰ�˻���������У����ɵ�½
						flag=2;
					}
					if(u_flag==2){  //��ǰ�˻�����У����ɵ�½
						flag=3;   
					}
				}else{ //�˺Ŵ��ڣ����벻��ȷ
					flag=4;
				}
			}else{
				flag=5; //�˺Ų�����
			}
		}else{
			flag=0;  //��֤�벻��ȷ
		}
		return flag;
	}
	
	@RequestMapping(value="/findAllUsers.do")
	public String findAllUsers(Integer index,ModelMap map){
		if(index==null){
			index=1;
		}
		int size=5;
		int count=userBiz.countUsers();
		int total=count%size==0?count/size:count/size+1;
		List<User> list=userBiz.findAllUsers(index, size);
		//��list����������
		map.put("list",list);
		map.put("index",index);
		map.put("total", total);
		return "manageUsers";
	}
	@RequestMapping(value="/examine.do")
	public String examine(int u_id){
		userBiz.examine(u_id);
		return "forward:findAllUsers.do";
	}
	
	@RequestMapping(value="/reopen.do")
	public String reopen(int u_id){
		userBiz.reopen(u_id);
		return "forward:findAllUsers.do";
	}
	
	@RequestMapping(value="/freeze.do")
	public String freeze(int u_id){
		userBiz.freeze(u_id);
		return "forward:findAllUsers.do";
	}
	
	@RequestMapping(value="/findUserById.do")
	@ResponseBody
	public User findUserById(int u_id){
		User u=userBiz.findUserById(u_id);
		return u;
	}
	
	@RequestMapping(value="/update.do")
	public String update(int u_id,String u_name,String u_pwd,String u_sid,String u_margin,String u_class){
		User u=new User();
		u.setU_id(u_id);
		u.setU_name(u_name);
		u.setU_pwd(u_pwd);
		u.setU_sid(u_sid);
		u.setU_class(u_class);
		u.setU_margin(u_margin);
		userBiz.update(u);
		return "forward:findAllUsers.do";
	}
	
	//�޸�����
	
	@RequestMapping(value="/updataPwd1.do")
	@ResponseBody
	public int updataPwd1(String old_pdw,String new_pwd,HttpSession session){
		String id=session.getAttribute("u_id").toString();
		Integer id1=Integer.valueOf(id);
		int u_id=id1.intValue();
		User u = userBiz.findByuId(u_id);
		if(!u.getU_pwd().equals(old_pdw))
			return 0;
		u.setU_pwd(new_pwd);
		userBiz.updateUser(u);
		return 1;
	}
	
	@RequestMapping(value="/updatePwd.do")
	@ResponseBody
	public int updatePwd(String u_sid,String u_pwd,String checkNum,HttpSession session){
		int flag=-1;
		String key=session.getAttribute("RANDOMCODEKEY").toString();
		//System.out.println(checkNum+' '+key);
		User u1=userBiz.findById(u_sid);
		if(checkNum.toLowerCase().equals(key.toLowerCase())){  //��֤��������ȷ
			if(u1!=null){//���˻�����
				int u_flag=u1.getU_flag();
				if(u_flag==1){  //�˻���������״̬�������޸�����
					userBiz.updatePwd(u_sid, u_pwd);
					flag=1;
				}
				if(u_flag==0){  //�˻�������ˣ������޸�
					flag=2;
				}
				if(u_flag==2){  //�˻������״̬�����������޸�
					flag=3;
				}
			}else{//�˻�������
				flag=4;
			}
		}else{
			flag=0;    //��֤�����벻��ȷ
		}
		return flag;
	}
	
	@RequestMapping(value="/del.do")
	public String del(int u_id){
		userBiz.del(u_id);
		return "forward:findAllUsers.do";
	}
	
	//������֤��
	@RequestMapping(value="/checkCode.do")
	public void checkCode(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	    //������Ӧ����,������������������ΪͼƬ
	    response.setContentType("image/jpeg");

	    //������Ӧͷ��Ϣ�������������Ҫ���������
	    response.setHeader("pragma", "no-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expire", 0);
	    RandomValidateCode randomValidateCode = new RandomValidateCode();
	    try {
	        randomValidateCode.getRandcode(request, response);//���ͼƬ����
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//ģ����ѯ
		@RequestMapping(value="/findLike.do")
		public String findLike(String find,Integer index,ModelMap map){
			if(index==null){
				index=1;
			}
			int size=5;
			int count=userBiz.countUsersBySid(find);
			int total=count%size==0?count/size:count/size+1;
			List<User> list=userBiz.findLike(find, index, size);
			//��list����������
			map.put("list",list);
			map.put("index",index);
			map.put("total", total);
			map.put("find", find);
			return "manageUsers";
		}
		
		@RequestMapping(value="/findFlag.do")
		public String findFlag(int u_flag,Integer index,ModelMap map){
			if(index==null){
				index=1;
			}
			int size=5;
			int count=userBiz.countUsersByFlag(u_flag);
			int total=count%size==0?count/size:count/size+1;
			List<User> list=userBiz.findFlag(u_flag, index, size);
			//��list����������
			map.put("list",list);
			map.put("index",index);
			map.put("total", total);
			map.put("find_id",u_flag);
			return "manageUsers";
		}
		@RequestMapping(value="/addUser.do")
		public String addUser(String u_name,String u_pwd,String u_sid,String u_class,String u_margin){
			User u=new User();
			u.setU_name(u_name);
			u.setU_pwd(u_pwd);
			u.setU_sid(u_sid);
			u.setU_class(u_class);
			u.setU_margin(u_margin);
			u.setU_flag(1);
			userBiz.registUser(u);
			return "forward:findAllUsers.do";
		}
	
}
