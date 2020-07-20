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

import toto.car.ejb.entity.*;

@Named("modelbean")
//@ViewScoped
@ApplicationScoped
public class ModelBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ModelBean.class);

	private String mode;
	private Calendar cal;
	
	private ArrayList<Model> models;
	private Model selectedModel;
	private Long selectModelID;
	private Model model;
	
	private Long selectedBlandID;
	
	@Inject
	private IndexBean indexBean;
	
	@Inject
	private BlandBean blandBean;

	@PostConstruct
	private void init() {
		logger.debug("init");
		cal = Calendar.getInstance();
		models = setDummyModel();
	}
	
	@PreDestroy
	private void destroy() {
		logger.debug("destroy");
	}
	
	public void selectBlandChang() {
		model.setBland(blandBean.getBlands().get(selectedBlandID.intValue()));
		logger.debug("selectedBlandID: " + selectedBlandID);
		logger.debug("selectBlandChang: " + blandBean.getBlands().get(selectedBlandID.intValue()).getName());
	}
	
	private ArrayList<Model> setDummyModel() {
		ArrayList<Model> models = new ArrayList<Model>();
		Model c1 = new Model();
		c1.setCreateDate(cal.getTime());
		c1.setCreateUser("admin");
		c1.setId(0L);
		c1.setName("Fino");
		c1.setUpdateDate(cal.getTime());
		c1.setBland(blandBean.getBlands().get(0));
		models.add(c1);
		
		Model c2 = new Model();
		c2.setCreateDate(cal.getTime());
		c2.setCreateUser("admin");
		c2.setId(1L);
		c2.setName("Jazz");
		c2.setUpdateDate(cal.getTime());
		c2.setBland(blandBean.getBlands().get(0));
		models.add(c2);
		
		Model c3 = new Model();
		c3.setCreateDate(cal.getTime());
		c3.setCreateUser("admin");
		c3.setId(2L);
		c3.setName("Dream");
		c3.setUpdateDate(cal.getTime());
		c3.setBland(blandBean.getBlands().get(1));
		models.add(c3);
		
		return models;
	}
	
	public void btnNewClick() {
		model = new Model();
		model.setCreateUser(indexBean.getUserName());
		model.setCreateDate(cal.getTime());
		model.setUpdateDate(cal.getTime());
		mode = "insert";
		
		//edit after connect database
		selectedBlandID = 0L;
		
		logger.debug("btnNewClick");
	}
	
	public void btnEditClick(Model r) {
		selectedModel = r;
		selectedBlandID = r.getBland().getId();
		model = r;
		mode = "edit";
		logger.debug("btnEditClick" + " selectedBlandName: " + r.getBland().getName());
	}
	
	public void btnSaveClick() {
		if(mode.equals("insert")) {
			model.setId(Long.valueOf(models.size()));
			blandBean.addModelToBland(selectedBlandID, model);
			model.setBland(blandBean.getBlands().get(selectedBlandID.intValue()));
			models.add(model);
		} else {
			blandBean.updateModelToBland(selectedBlandID, model);
			//selectedModel.setBland(blandBean.getBlands().get(selectedBlandID.intValue()));
		}
		logger.debug("btnSaveClick");
	}
	
	public void btnDeleteClick(Model r) {
		selectedModel = r;
		logger.debug("btnDeleteClick");
	}
	
	public void confirmDeleteClick() {
		try {
			models.remove(selectedModel);
		} catch(Exception ex) {
			FacesMessage msg = new FacesMessage();
			msg.setSummary("ไม่สามารถ ลบ ได้");
			msg.setDetail("ไม่สามารถ ลบ ได้ เพราะมี ข้อมูลที่เกี่ยวข้อง");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public ArrayList<Model> getModels() {
		return models;
	}

	public void setModels(ArrayList<Model> models) {
		this.models = models;
	}

	public Model getSelectedModel() {
		return selectedModel;
	}

	public void setSelectedModel(Model selectedModel) {
		this.selectedModel = selectedModel;
	}

	public Long getSelectModelID() {
		return selectModelID;
	}

	public void setSelectModelID(Long selectModelID) {
		this.selectModelID = selectModelID;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public BlandBean getBlandBean() {
		return blandBean;
	}

	public void setBlandBean(BlandBean blandBean) {
		this.blandBean = blandBean;
	}

	public Long getSelectedBlandID() {
		return selectedBlandID;
	}

	public void setSelectedBlandID(Long selectedBlandID) {
		this.selectedBlandID = selectedBlandID;
	}

}
