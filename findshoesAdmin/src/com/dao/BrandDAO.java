package com.dao;

import com.model.Brand;
import com.model.BrandId;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Brand
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.model.Brand
 * @author MyEclipse Persistence Tools
 */

public class BrandDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(BrandDAO.class);
	// property constants
	public static final String BRAND_BASE_URL = "brandBaseUrl";
	public static final String IS_VISITED = "isVisited";
	public static final String ONLINE = "online";

	public void save(Brand transientInstance) {
		log.debug("saving Brand instance");
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

	public void delete(Brand persistentInstance) {
		log.debug("deleting Brand instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Brand findById(com.model.BrandId id) {
		log.debug("getting Brand instance with id: " + id);
		try {
			Brand instance = (Brand) getSession().get("com.model.Brand", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Brand instance) {
		log.debug("finding Brand instance by example");
		try {
			List results = getSession().createCriteria("com.model.Brand")
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
		log.debug("finding Brand instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Brand as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBrandBaseUrl(Object brandBaseUrl) {
		return findByProperty(BRAND_BASE_URL, brandBaseUrl);
	}

	public List findByIsVisited(Object isVisited) {
		return findByProperty(IS_VISITED, isVisited);
	}

	public List findByOnline(Object online) {
		return findByProperty(ONLINE, online);
	}

	public List findAll() {
		log.debug("finding all Brand instances");
		try {
			String queryString = "from Brand";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Brand merge(Brand detachedInstance) {
		log.debug("merging Brand instance");
		try {
			Brand result = (Brand) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Brand instance) {
		log.debug("attaching dirty Brand instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Brand instance) {
		log.debug("attaching clean Brand instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}