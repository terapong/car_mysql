package toto.car.jsf.view;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
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

@Named("blandbean")
//@ViewScoped
@ApplicationScoped
public class BlandBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(BlandBean.class);

	private String mode;
	private Calendar cal;
	
	private ArrayList<Bland> blands;
	private Bland selectedBland;
	private Long selectBlandID;
	private Bland bland;
	
	private List<Model> models;
	
	@Inject
	private IndexBean indexBean;

	@PostConstruct
	private void init() {
		logger.debug("init");
		cal = Calendar.getInstance();
		blands = setDummyBland();
	}
	
	@PreDestroy
	private void destroy() {
		logger.debug("destroy");
	}
	
	private ArrayList<Bland> setDummyBland() {
		ArrayList<Bland> blands = new ArrayList<Bland>();
		Bland c1 = new Bland();
		c1.setCreateDate(cal.getTime());
		c1.setCreateUser("admin");
		c1.setId(0L);
		c1.setName("Honda");
		c1.setUpdateDate(cal.getTime());
		
		ArrayList<Model> models_1 = new ArrayList<Model>();
		Model m1 = new Model();
		m1.setCreateDate(cal.getTime());
		m1.setCreateUser("admin");
		m1.setId(0L);
		m1.setName("Fino");
		m1.setUpdateDate(cal.getTime());
		m1.setBland(c1);
		models_1.add(m1);
		
		Model m2 = new Model();
		m2.setCreateDate(cal.getTime());
		m2.setCreateUser("admin");
		m2.setId(1L);
		m2.setName("Jazz");
		m2.setUpdateDate(cal.getTime());
		m2.setBland(c1);
		models_1.add(m2);
		
		c1.setModels(models_1);
		
		blands.add(c1);
		
		Bland c2 = new Bland();
		c2.setCreateDate(cal.getTime());
		c2.setCreateUser("admin");
		c2.setId(1L);
		c2.setName("Kavasaki");
		c2.setUpdateDate(cal.getTime());
		
		ArrayList<Model> models_2 = new ArrayList<Model>();
		Model m3 = new Model();
		m3.setCreateDate(cal.getTime());
		m3.setCreateUser("admin");
		m3.setId(2L);
		m3.setName("Dream");
		m3.setUpdateDate(cal.getTime());
		m3.setBland(c1);
		models_2.add(m3);
		
		c2.setModels(models_2);
		
		blands.add(c2);
		
		Bland c3 = new Bland();
		c3.setCreateDate(cal.getTime());
		c3.setCreateUser("admin");
		c3.setId(2L);
		c3.setName("Dream");
		c3.setUpdateDate(cal.getTime());
		blands.add(c3);
		
		return blands;
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
	
	public ArrayList<Bland> getBlands() {
		return blands;
	}

	public void setBlands(ArrayList<Bland> blands) {
		this.blands = blands;
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
}
