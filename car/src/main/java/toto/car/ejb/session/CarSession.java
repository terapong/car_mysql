package toto.car.ejb.session;

import java.io.Serializable;
import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.*;

import org.apache.log4j.Logger;

import toto.car.ejb.entity.*;

@Stateless
public class CarSession implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CarSession.class);
	
	@PersistenceContext
    private EntityManager em;
	
	public User getUser(String username) {
		String sql = "select * from color where username = " + username;
		logger.debug("sql: " + sql);
		return (User) em.createNativeQuery(sql , User.class).getSingleResult(); 
	}
	
	public Shop getShopByUser(User u) {
		//String sql = "select * from shop where ";
		return null;
	}
	
	public void deleteUser(User u) {
		em.remove(u);
	}
	
	public void updateUser(User u) {
		em.merge(u);
	}
	
	public List<Color> getAllColorByShopId(Long id) {
		String sql = "select * from color where shop_id = " + id;
		logger.debug("sql: " + sql);
		return em.createNativeQuery(sql , Color.class).getResultList(); 
	}
	
	public void insertColorByShopId(Long id, Color c) {
		logger.debug("Shop ID: " + id + " Color: " + c.getName());
		em.merge(c);
	}
	
	public void updateColorByShopId(Long id, Color c) {
		logger.debug("Shop ID: " + id + " Color: " + c.getName());
		em.merge(c);
	}
	
	public void deleteColorByShopId(Long id, Color c) {
		logger.debug("Shop ID: " + id + " Color: " + c.getName());
		c = em.find(Color.class, c.getId());
		em.remove(c);
	}
	
	public List<Bland> getAllBlandByShopId(Long id) {
		String sql = "select * from bland where shop_id = " + id;
		logger.debug("sql: " + sql);
		return em.createNativeQuery(sql , Bland.class).getResultList();
	}
	
	public void insertBlandByShopId(Long id, Bland b) {
		logger.debug("Shop ID: " + id + " Bland: " + b.getName());
		em.merge(b);
	}
	
	public void updateBlandByShopId(Long id, Bland b) {
		logger.debug("Shop ID: " + id + " Bland: " + b.getName());
		em.merge(b);
	}
	
	public void deleteBlandByShopId(Long id, Bland b) {
		logger.debug("Shop ID: " + id + " Bland: " + b.getName());
		em.remove(b);
	}
}
