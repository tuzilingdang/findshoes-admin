package com.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.model.Users;

public class PageDAO extends BaseHibernateDAO{

	private static final Logger log = LoggerFactory.getLogger(PageDAO.class);
	//分页查询
	public List<Users> queryPage(int pageSize,int pageNow){
		List<Users> list = new ArrayList<Users>();
		if(pageSize>0 && pageNow>0){
			String hql = "from Users as u order by u.userId";
			Query query = getSession().createQuery(hql);
			query.setFirstResult(pageNow*pageSize-pageSize);
			query.setMaxResults(pageSize);
			//query.setInteger(0, pageNow*pageSize-pageSize);
			//query.setInteger(1, pageSize);
			list = query.list();
		}
		return list;
	}	
	
	//返回记录总条数	
	public int TotalRows(){
		List<Users> list = new ArrayList<Users>();
		String hql = "from Users";
		Query query = getSession().createQuery(hql);
		list = query.list();
		return list.size();
	}
	
	//返回关键字搜索的记录总条数
	public int SearchTotalRows(List searchValue){
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
		return criteria.list().size();
	}
}
