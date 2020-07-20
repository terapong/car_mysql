package toto.car.jsf.view;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.event.CaptureEvent;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import toto.car.ejb.entity.*;

@Named("carbean")
//@ViewScoped
@ApplicationScoped
public class CarBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CarBean.class);

	private String mode;
	private Calendar cal;
	
	private ArrayList<Car> cars;
	private Car selectedCar;
	private Long selectCarID;
	private Car car;
	
	private Long selectedBlandID;
	private Long selectedColorID;
	private Long selectedModelID;
	
	private String filename;
	
	@Inject
	private IndexBean indexBean;
	
	@Inject
	private BlandBean blandBean;
	
	@Inject
	private ModelBean modelBean;
	
	@Inject
	private ColorBean colorBean;

	@PostConstruct
	private void init() {
		logger.debug("init");
		cal = Calendar.getInstance();
		cars = setDummyCar();
		selectedBlandID = 0L;
		blandBean.getModelByBland(selectedBlandID);
		modelBean.setSelectModelID(0L);
		colorBean.setSelectColorID(0L);
	}
	
	@PreDestroy
	private void destroy() {
		logger.debug("destroy");
	}
	
	public void selectBlandChang() {
		blandBean.getModelByBland(selectedBlandID);
		selectedModelID = blandBean.getModels().get(0).getId();
		logger.debug("selectedBlandID: " + selectedBlandID);
		logger.debug("selectedModelID: " + selectedModelID);
		logger.debug("selectBlandChang: " + blandBean.getBlands().get(selectedBlandID.intValue()).getName());
	}
	
	public void selectModelChang() {
		logger.debug("selectedModelID: " + selectedModelID);
		logger.debug("selectModelChang: " + modelBean.getModels().get(selectedModelID.intValue()).getName());
	}
	
	private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);
         
        return String.valueOf(i);
    }
 
    public String getFilename() {
        return filename;
    }
	
	public void oncapture(CaptureEvent captureEvent) {
		filename = getRandomImageName();
		byte[] data = captureEvent.getData();

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo"
				+ File.separator + "images" + File.separator + "photocam" + File.separator + filename + ".jpeg";

		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(newFileName));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
		} catch (IOException e) {
			throw new FacesException("Error in writing captured image.", e);
		}
	}
	
	private ArrayList<Car> setDummyCar() {
		ArrayList<Car> cars = new ArrayList<Car>();
		Car c1 = new Car();
		c1.setCreateDate(cal.getTime());
		c1.setUpdateDate(cal.getTime());
		c1.setCreateUser("admin");
		c1.setId(0L);
		c1.setCarStatus(false);
		c1.setMachineNo("123456789_1");
		c1.setPic1("c://pic1.jpg");
		c1.setPic2("c://pic2.jpg");
		c1.setProvince("ประจวบคึรีขุนธ์");
		c1.setVehicleRegistration("987654321_1");
		c1.setColor(colorBean.getColors().get(0));
		c1.setBland(blandBean.getBlands().get(0));
		c1.setModel(modelBean.getModels().get(0));
		cars.add(c1);
		
		Car c2 = new Car();
		c2.setCreateDate(cal.getTime());
		c2.setUpdateDate(cal.getTime());
		c2.setCreateUser("admin");
		c2.setId(1L);
		c2.setCarStatus(false);
		c2.setMachineNo("123456789_2");
		c2.setPic1("c://pic1.jpg");
		c2.setPic2("c://pic2.jpg");
		c2.setProvince("ประจวบคึรีขุนธ์");
		c2.setVehicleRegistration("987654321_2");
		c2.setColor(colorBean.getColors().get(0));
		c2.setBland(blandBean.getBlands().get(1));
		c2.setModel(modelBean.getModels().get(1));
		cars.add(c2);
		
		Car c3 = new Car();
		c3.setCreateDate(cal.getTime());
		c3.setUpdateDate(cal.getTime());
		c3.setCreateUser("admin");
		c3.setId(2L);
		c3.setCarStatus(false);
		c3.setMachineNo("123456789_3");
		c3.setPic1("c://pic1.jpg");
		c3.setPic2("c://pic2.jpg");
		c3.setProvince("ประจวบคึรีขุนธ์");
		c3.setVehicleRegistration("987654321_3");
		c3.setColor(colorBean.getColors().get(0));
		c3.setBland(blandBean.getBlands().get(2));
		c3.setModel(modelBean.getModels().get(2));
		cars.add(c3);
		
		return cars;
	}
	
	public void btnNewClick() {
		car = new Car();
		car.setCreateUser(indexBean.getUserName());
		car.setCreateDate(cal.getTime());
		car.setUpdateDate(cal.getTime());
		mode = "insert";
		
		//edit after connect database
		selectedColorID = 0L;
		selectedBlandID = 0L;
		selectedModelID = 0L;
		
		colorBean.setSelectColorID(selectedColorID);
		blandBean.getModelByBland(selectedBlandID);
		modelBean.setSelectModelID(selectedModelID);
		
		logger.debug("btnNewClick");
	}
	
	public void btnEditClick(Car r) {
		selectedCar = r;
		car = r;
		selectedColorID = r.getColor().getId();
		selectedBlandID = r.getBland().getId();
		selectedModelID = r.getModel().getId();
		
		mode = "edit";
		logger.debug("btnEditClick");
	}
	
	public void btnSaveClick() {
		if(mode.equals("insert")) {
			car.setColor(colorBean.getColors().get(selectedColorID.intValue()));
			car.setBland(blandBean.getBlands().get(selectedBlandID.intValue()));
			car.setModel(modelBean.getModels().get(selectedModelID.intValue()));
			car.setId(Long.valueOf(cars.size()));
			cars.add(car);
		} else {
			selectedCar.setColor(colorBean.getColors().get(selectedColorID.intValue()));
			selectedCar.setBland(blandBean.getBlands().get(selectedBlandID.intValue()));
			selectedCar.setModel(modelBean.getModels().get(selectedModelID.intValue()));
		}
		logger.debug("btnSaveClick");
	}
	
	public void btnDeleteClick(Car r) {
		selectedCar = r;
		logger.debug("btnDeleteClick");
	}
	
	public void confirmDeleteClick() {
		try {
			cars.remove(selectedCar);
		} catch(Exception ex) {
			FacesMessage msg = new FacesMessage();
			msg.setSummary("ไม่สามารถ ลบ ได้");
			msg.setDetail("ไม่สามารถ ลบ ได้ เพราะมี ข้อมูลที่เกี่ยวข้อง");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public ArrayList<Car> getCars() {
		return cars;
	}

	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public Car getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(Car selectedCar) {
		this.selectedCar = selectedCar;
	}

	public Long getSelectCarID() {
		return selectCarID;
	}

	public void setSelectCarID(Long selectCarID) {
		this.selectCarID = selectCarID;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public BlandBean getBlandBean() {
		return blandBean;
	}

	public void setBlandBean(BlandBean blandBean) {
		this.blandBean = blandBean;
	}

	public ModelBean getModelBean() {
		return modelBean;
	}

	public void setModelBean(ModelBean modelBean) {
		this.modelBean = modelBean;
	}

	public ColorBean getColorBean() {
		return colorBean;
	}

	public void setColorBean(ColorBean colorBean) {
		this.colorBean = colorBean;
	}

	public Long getSelectedBlandID() {
		return selectedBlandID;
	}

	public void setSelectedBlandID(Long selectedBlandID) {
		this.selectedBlandID = selectedBlandID;
	}

	public Long getSelectedColorID() {
		return selectedColorID;
	}

	public void setSelectedColorID(Long selectedColorID) {
		this.selectedColorID = selectedColorID;
	}

	public Long getSelectedModelID() {
		return selectedModelID;
	}

	public void setSelectedModelID(Long selectedModelID) {
		this.selectedModelID = selectedModelID;
	}
}