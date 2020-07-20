package toto.car.jsf.view;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Calendar;

import toto.car.ejb.entity.*;

@Named("indexbean")
//@SessionScoped
@ApplicationScoped
public class IndexBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(IndexBean.class);
	
//	private ArrayList<Role> roles;
//	private Role selectedRole;
//	private Long selectedRoleID;
	
//	private ArrayList<Employee> employees;
//	private Employee selectedEmployee;
//	private Long selectEmployeeID;
	
	private Shop shop;
	
	private User user;
	
	private String contentCenter = "customer.xhtml";
	
	private String userName;
	private String password;
	private Calendar cal;
	
	@PostConstruct
	private void init() {
		logger.debug("init");
		cal = Calendar.getInstance();
		
//		roles = setDummyRole();
//		selectedRole = roles.get(0);
//		logger.debug("Role select: " + selectedRole.getName());
		
//		employees = setDummyEmployee();
//		selectedEmployee = employees.get(0);
//		logger.debug("Employee select: " + selectedEmployee.getUserName());
		
		user = setDummyUser();
		shop = setDummyShop();
	}
	
	@PreDestroy
	private void destroy() {
		logger.debug("destroy");
	}
	
	private User setDummyUser() {
		User emp1 = new User();
		emp1.setId(0L);
		emp1.setUserName("terapong");
		emp1.setPassword("ying2002");
		emp1.setCreateUser("admin");
		emp1.setCreateDate(cal.getTime());
		emp1.setUpdateDate(cal.getTime());
		
		return emp1;
	}
	
	private Shop setDummyShop() {
		Shop shop = new Shop();
		shop.setAddress("54/10 ซอยเริงรมณ์ ต.หัวหิน อ.หัวหิน จ.ประจวบคีรีขันธ์ 77120");
		shop.setBusinessRegistration("123456789");
		shop.setCreateDate(cal.getTime());
		shop.setCreateUser("admin");
		shop.setId(0L);
		shop.setIdcard("3770600334048");
		shop.setOwnerNameEN("Terapong");
		shop.setOwnerNameTH("ธีระพงษ์");
		shop.setOwnerSurNameEN("Potisuwan");
		shop.setOwnerSurNameTH("โพธิสุวรรณ");
		shop.setPhoneFax("087-4349902");
		shop.setPhoneMobile("087-4349902");
		shop.setPhoneOffice("087-4349902");
		shop.setShopName("ร้านพี่แขก car rent");
		shop.setShopStatus(true);
		shop.setUpdateDate(cal.getTime());
		return shop;
	}
	
//	private ArrayList<Role> setDummyRole() {
//		ArrayList<Role> roles = new ArrayList<Role>();
//		Role r1 = new Role();
//		r1.setId(0L);
//		r1.setName("admin");
//		r1.setCreateUser("admin");
//		r1.setCreateDate(cal.getTime());
//		r1.setUpdateDate(cal.getTime());
//		roles.add(r1);
//		
//		Role r2 = new Role();
//		r2.setId(01L);
//		r2.setName("ying");
//		r2.setCreateUser("admin");
//		r2.setCreateDate(cal.getTime());
//		r2.setUpdateDate(cal.getTime());
//		roles.add(r2);
//		
//		return roles;
//	}
	
	public String loginClick() {
		logger.debug("Login click");
		logger.debug("Login userName: " + userName);
		logger.debug("Login password: " + password);
		
		return "main.xhtml";
		
//		if((user.getUserName().equals(userName)) && (user.getPassword().equals(password))) {
//			logger.debug("Login true");
//			return "main.xhtml";
//		} else {
//			logger.debug("Login false");
//			return null;
//		}
	}
	
	public String logout() {
		//employee = vasessionbean.getEmployee();
		FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            logger.debug("Logout from system");
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed!"));
        } 
        context.getExternalContext().invalidateSession();
        return "index?facesRedirect=true";
    }
	
	public void RegisterClick() {
		logger.debug("Register click");
	}
	
//	public void selEmployeeChange() {
//		logger.debug("Employee Id: " + selectEmployeeID);
//		logger.debug("Employee select: " + employees.get(selectEmployeeID.intValue()).getUserName());
//	}
	
//	public void selRoleChange() {
//		logger.debug("Role Id: " + selectedRoleID);
//		logger.debug("Role select: " + roles.get(selectedRoleID.intValue()).getName());
//	}
	
//	public void selUserChange() {
//		logger.debug("User Id: " + selectUserID);
//		logger.debug("User select: " + users.get(selectUserID.intValue()).getUserName());
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContentCenter() {
		return contentCenter;
	}

	public void setContentCenter(String contentCenter) {
		this.contentCenter = contentCenter;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
