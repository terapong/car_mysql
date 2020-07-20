package toto.car.jsf.view;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import toto.car.ejb.entity.*;

@Named("customerbean")
//@ViewScoped
@ApplicationScoped
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CustomerBean.class);
	
	private ArrayList<Customer> customers;
	private Customer selectedCustomer;
	private Long selectCustomerID;
	private Customer customer;
	
	private String mode;
	private Calendar cal;
	
	@Inject
	private IndexBean indexBean;

	@PostConstruct
	private void init() {
		logger.debug("init");
		cal = Calendar.getInstance();
		customers = setDummyCustomer();
	}
	
	@PreDestroy
	private void destroy() {
		logger.debug("destroy");
	}
	
	private ArrayList<Customer> setDummyCustomer() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Customer u1 = new Customer();
		u1.setAddress("Address");
		u1.setCountry("Thailand");
		u1.setCreateDate(cal.getTime());
		u1.setCreateUser("admin");
		u1.setId(0L);
		u1.setIdCard("3770600334048");
		u1.setLicenseNo("123456789");
		u1.setNameEN("Terapong");
		u1.setSurnameEN("Potisuwan");
		u1.setNameTH("ธีระพงษ์");
		u1.setSurnameTH("โพธิสุวรรณ");
		u1.setPassportNo("987654321");
		u1.setPic("xxxxxx");
		u1.setUpdateDate(cal.getTime());
		u1.setShop(indexBean.getShop());
		customers.add(u1);
		
		Customer u2 = new Customer();
		u2.setAddress("Address");
		u2.setCountry("Thailand");
		u2.setCreateDate(cal.getTime());
		u2.setCreateUser("admin");
		u2.setId(0L);
		u2.setIdCard("3770600334048");
		u2.setLicenseNo("123456789");
		u2.setNameEN("Ying");
		u2.setSurnameEN("Potisuwan");
		u2.setNameTH("หญิง");
		u2.setSurnameTH("โพธิสุวรรณ");
		u2.setPassportNo("987654321");
		u2.setPic("xxxxxx");
		u2.setUpdateDate(cal.getTime());
		u2.setShop(indexBean.getShop());
		customers.add(u2);
		
		return customers;
	}

	public IndexBean getIndexBean() {
		return indexBean;
	}

	public void setIndexBean(IndexBean indexBean) {
		this.indexBean = indexBean;
	}
	
	public void btnNewClick() {
		customer = new Customer();
		customer.setCreateUser(indexBean.getUserName());
		customer.setCreateDate(cal.getTime());
		customer.setUpdateDate(cal.getTime());
		mode = "insert";
		logger.debug("btnNewClick");
	}
	
	public void btnEditClick() {
		mode = "update";
		logger.debug("btnEditClick");
	}
	
	public void btnSaveClick() {
		if(mode.equals("insert")) {
			customers.add(customer);
		} else {
			
		}
		logger.debug("btnSaveClick");
	}
	
	public void btnDeleteClick() {
		logger.debug("btnDeleteClick");
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public Long getSelectCustomerID() {
		return selectCustomerID;
	}

	public void setSelectCustomerID(Long selectCustomerID) {
		this.selectCustomerID = selectCustomerID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
