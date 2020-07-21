package toto.car.jsf.view;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import toto.car.ejb.entity.*;
import toto.car.ejb.session.CarSession;

@Named("blandbean")
@ViewScoped
public class BlandBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(BlandBean.class);

	private String mode;
	private Calendar cal;
	
	private List<Bland> blands;
	private Bland selectedBland;
	private Long selectBlandID;
	private Bland bland;
	
	private Shop shop;
	
	private List<Model> models;
	
	@Inject
	private IndexBean indexBean;
	
	@Inject
	private CarSession s;

	@PostConstruct
	private void init() {
		logger.debug("init");
		cal = Calendar.getInstance();
		
		shop = indexBean.getShop();
		blands = s.getAllBlandByShopId(shop.getId());
	}
	
	@PreDestroy
	private void destroy() {
		logger.debug("destroy");
	}
	
	public void getModelByBland(Long id) {
		models = blands.get(id.intValue()).getModels();
		logger.debug("getModelByBland: " + id);
	}
	
	public void addModelToBland(Long id, Model m) {
		if(blands.get(id.intValue()).getModels() != null) {
			blands.get(id.intValue()).getModels().add(m);
		} else {
			blands.get(id.intValue()).setModels(new ArrayList<Model>()); 
			blands.get(id.intValue()).getModels().add(m);
		}
		
		logger.debug("addModelToBland ID: " + id + " Model ID: " + m.getId() + " Model Name: " + m.getName());
	}
	
	public void updateModelToBland(Long id, Model m) {
		//Bland bi = m.getBland(); //ลบ ตัวเก่าออก ก่อน
		//blands.get(id.intValue()).getModels().remove(m.getId().intValue());
		//blands.get(id.intValue()).getModels().add(m);
		logger.debug("updateModelToBland ID: " + id + " Model ID: " + m.getId() + " Model Name: " + m.getName());
	}
	
	public void deleteModelFomeBland(Long id, Model m) {
		blands.get(id.intValue()).getModels().remove(m.getId().intValue());
		logger.debug("deleteModelFomeBland ID: " + id + " Model ID: " + m.getId() + " Model Name: " + m.getName());
	}
	
	public void btnNewClick() {
		bland = new Bland();
		bland.setCreateUser(indexBean.getUserName());
		bland.setCreateDate(cal.getTime());
		bland.setUpdateDate(cal.getTime());
		mode = "insert";
		logger.debug("btnNewClick");
	}
	
	public void btnEditClick(Bland r) {
		selectedBland = r;
		bland = r;
		mode = "edit";
		logger.debug("btnEditClick");
	}
	
	public void btnSaveClick() {
		if(mode.equals("insert")) {
			bland.setId(Long.valueOf(blands.size()));
			blands.add(bland);
		} else {
			///save to database
		}
		logger.debug("btnSaveClick");
	}
	
	public void btnDeleteClick(Bland r) {
		selectedBland = r;
		logger.debug("btnDeleteClick");
	}
	
	public void confirmDeleteClick() {
		try {
			blands.remove(selectedBland);
		} catch(Exception ex) {
			FacesMessage msg = new FacesMessage();
			msg.setSummary("ไม่สามารถ ลบ ได้");
			msg.setDetail("ไม่สามารถ ลบ ได้ เพราะมี ข้อมูลที่เกี่ยวข้อง");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public Bland getSelectedBland() {
		return selectedBland;
	}

	public void setSelectedBland(Bland selectedBland) {
		this.selectedBland = selectedBland;
	}

	public Long getSelectBlandID() {
		return selectBlandID;
	}

	public void setSelectBlandID(Long selectBlandID) {
		this.selectBlandID = selectBlandID;
	}

	public Bland getBland() {
		return bland;
	}

	public void setBland(Bland bland) {
		this.bland = bland;
	}

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

	public List<Bland> getBlands() {
		return blands;
	}

	public void setBlands(List<Bland> blands) {
		this.blands = blands;
	}
}
