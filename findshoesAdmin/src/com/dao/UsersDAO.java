package com.dao;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.model.Users;
import com.util.MD5Utils;

public class UsersDAO extends BaseHibernateDAO {

	private static final Logger log = LoggerFactory.getLogger(UsersDAO.class);
	
	//添加用户
	public void save(Users user){
		 log.debug("Add User instance");
		 try {
	        	Transaction ts = getSession().beginTransaction();
	            getSession().save(user);
	            ts.commit();
				getSession().close();
	            log.debug("add user successful");
	        } catch (RuntimeException re) {
	            log.error("add user failed", re);
	            throw re;
	        }
	 }
	
	//登陆校验
	public Users check(String name,String pass){
		log.debug("check user login");
		try {
			String pass_md5 = MD5Utils.md5(pass);
			//System.out.println(name);
			String hql = "from Users as u where u.userId=? and u.password=?";
			Query query = getSession().createQuery(hql);
			query.setString(0, name);
			query.setString(1, pass_md5);
			List result = query.list();
			if(result.size()>0)
				return (Users)(result.get(0));
			else{
				return null;
			}
		} catch (RuntimeException e) {
			log.error("user login error", e);
			throw e;
		}
	}
	
	//查找所有用户
	public List<Users> findAll(){
		 log.debug("Find all users");
		 try {
		    String sql = "from Users";
			Query query = getSession().createQuery(sql);
			return query.list();
		} catch (RuntimeException e) {
			log.error("Find shoes failed",e);
			throw e;
		}
	 }
	
	//根据关键字搜索用户
	@SuppressWarnings("deprecation")
	public List<Users> searchUser(List searchValue,int pageSize,int pageNow){
		log.debug("Search users");
		try {
			Criteria criteria = getSession().createCriteria(Users.class);
			if(!"".equals(searchValue.get(0))){
				Criterion criterionId = Expression.like("userId", "%"+searchValue.get(0)+"%");
				criteria = criteria.add(criterionId);
			}
			if(!"".equals(searchValue.get(1))){
				Criterion criterionNick = Expression.like("nick", "%"+searchValue.get(1)+"%");
				criteria = criteria.add(criterionNick);
			}
			if(!"".equals(searchValue.get(2))){
				Criterion criterionTel = Expression.like("telephone", "%"+searchValue.get(2)+"%");
				criteria = criteria.add(criterionTel);
			}
			if(!"".equals(searchValue.get(3))){
				Criterion criterionAddr = Expression.like("address", "%"+searchValue.get(3)+"%");
				criteria = criteria.add(criterionAddr);
			}
			if(!"".equals(searchValue.get(4))){
				Criterion criterionBal = Expression.eq("balance",Double.parseDouble(searchValue.get(4).toString()));
				criteria = criteria.add(criterionBal);
			}
			if(!"".equals(searchValue.get(5))){
				Criterion criterionEmail = Expression.like("email", "%"+searchValue.get(5)+"%");
				criteria = criteria.add(criterionEmail);
			}
			if(!"".equals(searchValue.get(6))){
				Criterion criterionVip = Expression.like("vip", "%"+searchValue.get(6)+"%");
				criteria = criteria.add(criterionVip);
			}
			if(!"".equals(searchValue.get(7))){
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Criterion criterionTime;
				try {
					criterionTime = Expression.like("regTime", format.parse(searchValue.get(7).toString()));
					criteria = criteria.add(criterionTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			criteria.setFirstResult(pageNow*pageSize-pageSize);
			criteria.setMaxResults(pageSize);
			
			
           return criteria.list();
		} catch (RuntimeException e) {
			log.error("Search users failed",e);
			throw e;
		}
	}
	
	
	
	//根据ID查找用户
	 public Users get(String id){
		 log.debug("Get User instance");
		 try {						
			 return (Users)getSession().get(Users.class, id);
		} catch (RuntimeException e) {
			log.error("get user failed",e);
			throw e;
		}
	 }
	 
	 //根据用户ID查找用户
	 public Users findById(String id){
		 log.debug("Find User instance");
		 try {
			
			String sql = "from Users as u where u.userId=:ID";
			Query query = getSession().createQuery(sql);
			query.setString("ID", id);
			List<Users> users = query.list();
			return users.get(0);
		} catch (RuntimeException e) {
			log.error("Find user failed",e);
			throw e;
		}
	 }
	 
	 //根据用户ID查找用户
	 public List<Users> findUsersById(String id){
		 log.debug("Find User instance");
		 try {
			 //System.out.println(id);
			String sql = "from Users as u where u.userId=:ID";
			Query query = getSession().createQuery(sql);
			query.setString("ID", id);
			List<Users> users = query.list();
			//System.out.println(users == null);
			//System.out.println(users);
			return users;
		} catch (RuntimeException e) {
			log.error("Find user failed",e);
			throw e;
		}
	 }
	 
	 //查看邮箱是否唯一
	 public boolean checkEmail(String email){
		
		try {
			String sql = "from Users as u where u.email=:uemail";
			Query query = getSession().createQuery(sql);
			query.setString("uemail", email);
			List<Users> users = query.list();
			if(users.size() > 0)
				return false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return true;
	 }
	 
	 //检查联系方式是否唯一
	 public boolean checkTel(String tel){
			
			try {
				String sql = "from Users as u where u.telephone=:tel";
				Query query = getSession().createQuery(sql);
				query.setString("tel", tel);
				List<Users> users = query.list();
				if(users.size() > 0)
					return false;
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			return true;
	}
	 
	//检查联系方式是否唯一
	 public boolean checkId(String id){
			
			try {
				String sql = "from Users as u where u.userId=:id";
				Query query = getSession().createQuery(sql);
				query.setString("id", id);
				List<Users> users = query.list();
				if(users.size() > 0)
					return false;
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			return true;
	}
	 
	// 获取ip（用于填写用户注册IP）
		public String getIp() {
			HttpServletRequest req = ServletActionContext.getRequest();
			return req.getRemoteAddr();
		}
	
	 
	//根据ID删除用户
	 public void delete(String id){
		 log.debug("Delete user instance");
		 try {
			Transaction transaction = getSession().beginTransaction();
			Users user = get(id); 
			//getSession().flush();
			getSession().delete(user);
			//删除图片			
			deleteImg(user.getIcon());	
			
			transaction.commit();			
			getSession().close();
			log.debug("delte user successful");
		} catch (RuntimeException e) {
			log.error("delete user failed",e);
			throw e;
		}
	 }
	 
	 //删除用户时同时删除头像
	 public void deleteImg(String path){
		 
		 if(path != null && path != ""){
		   File file = new File(path);
		   file.delete();		 
		 }
	 }
	 
	 
	//选择批量删除用户
	 public void delSelect(String[] ids){
		 log.debug("DelSelect users instance");
		 try {
			for(int i=0;i<ids.length;i++){
				delete(ids[i]);
			}
			log.debug("delselect users successful");
		} catch (RuntimeException e) {
			log.error("delselect uses failed",e);
			throw e;
		}
	 }
	 
	 //更新用户信息
	 public void updateUserInfo(Users user) {
			log.debug("Update user info");
			try {
				//getSession().saveOrUpdate(user);
				getSession().save(user);
				getSession().flush();
				log.debug("Update user successful");
			} catch (RuntimeException e) {
				log.error("Update user failed",e);
				throw e;
			}
		}
	
}
