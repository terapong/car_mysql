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
import java.util.*;

import toto.car.ejb.entity.*;
import toto.car.ejb.session.CarSession;

@Named("colorbean")
@ViewScoped
public class ColorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ColorBean.class);
	

	private String mode;
	private Calendar cal;
	
	private List<Color> colors;
	private Color selectedColor;
	private Long selectColorID;
	private Color color;
	
	private Shop shop;
	
	@Inject
	private IndexBean indexBean;
	
	@Inject
	private CarSession s;

	@PostConstruct
	private void init() {
		logger.debug("init");
		cal = Calendar.getInstance();
		//colors = setDummyColor();
		shop = indexBean.getShop();
		colors = s.getAllColorByShopId(shop.getId());
	}
	
	@PreDestroy
	private void destroy() {
		logger.debug("destroy");
	}
	
	public void btnNewClick() {
		color = new Color();
		color.setCreateUser(indexBean.getUserName());
		color.setCreateDate(cal.getTime());
		color.setUpdateDate(cal.getTime());
		color.setShop(shop);
		mode = "insert";
		logger.debug("btnNewClick");
	}
	
	public void btnEditClick(Color r) {
		selectedColor = r;
		color = r;
		mode = "edit";
		logger.debug("btnEditClick");
	}
	
	public void btnSaveClick() {
		if(mode.equals("insert")) {
			s.insertColorByShopId(shop.getId(), color);
		} else {
			s.updateColorByShopId(shop.getId(), color);
		}
		logger.debug("btnSaveClick");
		init();
	}
	
	public void btnDeleteClick(Color r) {
		selectedColor = r;
		logger.debug("btnDeleteClick");
	}
	
	public void confirmDeleteClick() {
		try {
			s.deleteColorByShopId(shop.getId(), selectedColor);
			init();
		} catch(Exception ex) {
			FacesMessage msg = new FacesMessage();
			msg.setSummary("ไม่สามารถ ลบ ได้");
			msg.setDetail("ไม่สามารถ ลบ ได้ เพราะมี ข้อมูลที่เกี่ยวข้อง");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public Color getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
	}

	public Long getSelectColorID() {
		return selectColorID;
	}

	public void setSelectColorID(Long selectColorID) {
		this.selectColorID = selectColorID;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<Color> getColors() {
		return colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}
}
