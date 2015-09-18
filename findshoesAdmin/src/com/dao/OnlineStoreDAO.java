package com.dao;

import com.model.OnlineStore;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * OnlineStore entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.OnlineStore
 * @author MyEclipse Persistence Tools
 */

public class OnlineStoreDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(OnlineStoreDAO.class);
	// property constants
	public static final String GOODS_ID = "goodsId";
	public static final String PRICE = "price";
	public static final String LIKE = "like";
	public static final String IMG_URL = "imgUrl";
	public static final String STORE_NAME = "storeName";
	public static final String STORE_IMG = "storeImg";
	public static final String FROM_EWEB = "fromEWeb";
	public static final String DEFUNCT = "defunct";
	public static final String IS_VISITED = "isVisited";

	public void save(OnlineStore transientInstance) {
		log.debug("saving OnlineStore instance");
		try {
        	Transaction tran = getSession().beginTransaction();
            getSession().save(transientInstance);
            tran.commit();
            getSession().close();
            log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OnlineStore persistentInstance) {
		log.debug("deleting OnlineStore instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OnlineStore findById(java.lang.String id) {
		log.debug("getting OnlineStore instance with id: " + id);
		try {
			OnlineStore instance = (OnlineStore) getSession().get(
					"com.model.OnlineStore", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OnlineStore instance) {
		log.debug("finding OnlineStore instance by example");
		try {
			List results = getSession().createCriteria("com.model.OnlineStore")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding OnlineStore instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from OnlineStore as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGoodsId(Object goodsId) {
		return findByProperty(GOODS_ID, goodsId);
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findByLike(Object like) {
		return findByProperty(LIKE, like);
	}

	public List findByImgUrl(Object imgUrl) {
		return findByProperty(IMG_URL, imgUrl);
	}

	public List findByStoreName(Object storeName) {
		return findByProperty(STORE_NAME, storeName);
	}

	public List findByStoreImg(Object storeImg) {
		return findByProperty(STORE_IMG, storeImg);
	}

	public List findByFromEWeb(Object fromEWeb) {
		return findByProperty(FROM_EWEB, fromEWeb);
	}

	public List findByDefunct(Object defunct) {
		return findByProperty(DEFUNCT, defunct);
	}

	public List findByIsVisited(Object isVisited) {
		return findByProperty(IS_VISITED, isVisited);
	}

	public List findAll() {
		log.debug("finding all OnlineStore instances");
		try {
			String queryString = "from OnlineStore";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OnlineStore merge(OnlineStore detachedInstance) {
		log.debug("merging OnlineStore instance");
		try {
			OnlineStore result = (OnlineStore) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OnlineStore instance) {
		log.debug("attaching dirty OnlineStore instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OnlineStore instance) {
		log.debug("attaching clean OnlineStore instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	//只找到第一个，用于显示
	public OnlineStore findTopOneByGoodsId(java.lang.String id) {
		log.debug("finding the top one OnlineStore instance with goodsId");
		try {
			String queryString = "from OnlineStore as model where model."
					+ GOODS_ID + "= '"+ id +"' order by model.price desc";
			System.out.println(queryString);
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(0); //设置首行记录
			queryObject.setFetchSize(1); //设置要查询的记录数
			List<OnlineStore> tmpList = queryObject.list();
			if(tmpList.size() == 0)
				return null;
			return tmpList.get(0);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
    
	//鏍规嵁ID鏌ユ壘OnlineStore
	 public OnlineStore get(String url){
		 log.debug("Get OnlineStore instance");
		 try {						
			 return (OnlineStore)getSession().get(OnlineStore.class, url);
		} catch (RuntimeException e) {
			log.error("Get OnlineStore failed",e);
			throw e;
		}
	 }

}