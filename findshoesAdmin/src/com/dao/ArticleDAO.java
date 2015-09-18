package com.dao;

import com.model.Article;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for Article entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.model.Article
  * @author MyEclipse Persistence Tools 
 */

public class ArticleDAO extends BaseHibernateDAO  {
	private static final Logger log = LoggerFactory.getLogger(ArticleDAO.class);
		//property constants
	public static final String TITLE = "title";
	public static final String LEAD = "lead";
	public static final String KEYWORDS = "keywords";
	public static final String STATUS = "status";
	public static final String IMG_URL = "imgUrl";
	public static final String TOP_LEVEL = "topLevel";
	public static final String TYPE = "type";
	public static final String AUTHOR = "author";
	public static final String CONTENT = "content";
	public static final String HEIGHT = "height";
	public static final String WEIGHT = "weight";
	public static final String BWH = "bwh";
	public static final String SHOE_URL = "shoeUrl";
	public static final String DEFUNCT = "defunct";
	public static final String FOOT_TYPE = "footType";
	public static final String FOOT_LENGTH = "footLength";
	public static final String FOOT_WIDTH = "footWidth";
	public static final String IMG_PATH = "imgPath";

    public void save(Article transientInstance) {
        log.debug("saving Article instance");
        try {
        	Transaction ts = getSession().beginTransaction();
            getSession().save(transientInstance);
            ts.commit();
			getSession().close();
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Article persistentInstance) {
        log.debug("deleting Article instance");
        try {
        	Transaction ts = getSession().beginTransaction();
            getSession().delete(persistentInstance);
            ts.commit();
			getSession().close();
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Article findById( java.lang.Integer id) {
        log.debug("getting Article instance with id: " + id);
        try {
            Article instance = (Article) getSession()
                    .get("com.model.Article", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Article instance) {
        log.debug("finding Article instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.model.Article")
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
      log.debug("finding Article instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Article as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTitle(Object title
	) {
		return findByProperty(TITLE, title
		);
	}
	
	public List findByLead(Object lead
	) {
		return findByProperty(LEAD, lead
		);
	}
	
	public List findByKeywords(Object keywords
	) {
		return findByProperty(KEYWORDS, keywords
		);
	}
	
	public List findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	public List findByImgUrl(Object imgUrl
	) {
		return findByProperty(IMG_URL, imgUrl
		);
	}
	
	public List findByTopLevel(Object topLevel
	) {
		return findByProperty(TOP_LEVEL, topLevel
		);
	}
	
	public List findByType(Object type
	) {
		return findByProperty(TYPE, type
		);
	}
	
	public List findByAuthor(Object author
	) {
		return findByProperty(AUTHOR, author
		);
	}
	
	public List findByContent(Object content
	) {
		return findByProperty(CONTENT, content
		);
	}
	
	public List findByHeight(Object height
	) {
		return findByProperty(HEIGHT, height
		);
	}
	
	public List findByWeight(Object weight
	) {
		return findByProperty(WEIGHT, weight
		);
	}
	
	public List findByBwh(Object bwh
	) {
		return findByProperty(BWH, bwh
		);
	}
	
	public List findByShoeUrl(Object shoeUrl
	) {
		return findByProperty(SHOE_URL, shoeUrl
		);
	}
	
	public List findByDefunct(Object defunct
	) {
		return findByProperty(DEFUNCT, defunct
		);
	}
	
	public List findByFootType(Object footType
	) {
		return findByProperty(FOOT_TYPE, footType
		);
	}
	
	public List findByFootLength(Object footLength
	) {
		return findByProperty(FOOT_LENGTH, footLength
		);
	}
	
	public List findByFootWidth(Object footWidth
	) {
		return findByProperty(FOOT_WIDTH, footWidth
		);
	}
	
	public List findByImgPath(Object imgPath
	) {
		return findByProperty(IMG_PATH, imgPath
		);
	}
	

	public List findAll() {
		log.debug("finding all Article instances");
		try {
			String queryString = "from Article";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Article merge(Article detachedInstance) {
        log.debug("merging Article instance");
        try {
            Article result = (Article) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Article instance) {
        log.debug("attaching dirty Article instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Article instance) {
        log.debug("attaching clean Article instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}