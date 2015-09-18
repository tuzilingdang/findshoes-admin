package com.dao;

import com.model.Image;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for Image entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.model.Image
  * @author MyEclipse Persistence Tools 
 */

public class ImageDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ImageDAO.class);
		//property constants
	public static final String GOODS_ID = "goodsId";



    
    public void save(Image transientInstance) {
        log.debug("saving Image instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Image persistentInstance) {
        log.debug("deleting Image instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Image findById( java.lang.String id) {
        log.debug("getting Image instance with id: " + id);
        try {
            Image instance = (Image) getSession()
                    .get("com.model.Image", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Image instance) {
        log.debug("finding Image instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.model.Image")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Image instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Image as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByGoodsId(Object goodsId
	) {
		return findByProperty(GOODS_ID, goodsId
		);
	}
	

	public List findAll() {
		log.debug("finding all Image instances");
		try {
			String queryString = "from Image";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Image merge(Image detachedInstance) {
        log.debug("merging Image instance");
        try {
            Image result = (Image) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Image instance) {
        log.debug("attaching dirty Image instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Image instance) {
        log.debug("attaching clean Image instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
	// 根据ID查找图片
	public Image get(String url) {
		log.debug("Get Image instance");
		try {
			return (Image) getSession().get(Image.class, url);
		} catch (RuntimeException e) {
			log.error("Get Image failed", e);
			throw e;
		}
	}


	public void save(List imgList) {
		// TODO Auto-generated method stub
		log.debug("Add some image instance");
		try {
			Transaction ts = getSession().beginTransaction();
			for(int i=0;i<imgList.size();i++){
			  Image image = (Image)imgList.get(i);
			  getSession().save(image);
			}
			ts.commit();
			getSession().close();
			log.debug("Add some image successful");
		} catch (RuntimeException e) {
			log.error("Add some image failed", e);
			throw e;
		}
	}
    
}