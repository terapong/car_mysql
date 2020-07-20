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

@Named("colorbean")
//@ViewScoped
@ApplicationScoped
public class ColorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ColorBean.class);

	private String mode;
	private Calendar cal;
	
	private ArrayList<Color> colors;
	private Color selectedColor;
	private Long selectColorID;
	private Color color;
	
	@Inject
	private IndexBean indexBean;

	@PostConstruct
	private void init() {
		logger.debug("init");
		cal = Calendar.getInstance();
		colors = setDummyColor();
	}
	
	@PreDestroy
	private void destroy() {
		logger.debug("destroy");
	}
	
	private ArrayList<Color> setDummyColor() {
		ArrayList<Color> colors = new ArrayList<Color>();
		Color c1 = new Color();
		c1.setCreateDate(cal.getTime());
		c1.setCreateUser("admin");
		c1.setId(0L);
		c1.setName("แดง");
		c1.setUpdateDate(cal.getTime());
		colors.add(c1);
		
		Color c2 = new Color();
		c2.setCreateDate(cal.getTime());
		c2.setCreateUser("admin");
		c2.setId(1L);
		c2.setName("ดำ");
		c2.setUpdateDate(cal.getTime());
		colors.add(c2);
		
		Color c3 = new Color();
		c3.setCreateDate(cal.getTime());
		c3.setCreateUser("admin");
		c3.setId(2L);
		c3.setName("น้ำเงิน");
		c3.setUpdateDate(cal.getTime());
		colors.add(c3);
		
		return colors;
	}
	
	public void btnNewClick() {
		color = new Color();
		color.setCreateUser(indexBean.getUserName());
		color.setCreateDate(cal.getTime());
		color.setUpdateDate(cal.getTime());
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
			color.setId(Long.valueOf(colors.size()));
			colors.add(color);
		} else {
			///save to database
		}
		logger.debug("btnSaveClick");
	}
	
	public void btnDeleteClick(Color r) {
		selectedColor = r;
		logger.debug("btnDeleteClick");
	}
	
	public void confirmDeleteClick() {
		try {
			colors.remove(selectedColor);
		} catch(Exception ex) {
			FacesMessage msg = new FacesMessage();
			msg.setSummary("ไม่สามารถ ลบ ได้");
			msg.setDetail("ไม่สามารถ ลบ ได้ เพราะมี ข้อมูลที่เกี่ยวข้อง");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public ArrayList<Color> getColors() {
		return colors;
	}

	public void setColors(ArrayList<Color> colors) {
		this.colors = colors;
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
}
