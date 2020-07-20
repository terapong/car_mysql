package toto.car.ejb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="car")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, name="machine_no", length = 50)
    private String machineNo;
    
    @Column(nullable = false, name="vehicle_registration", length = 50)
    private String vehicleRegistration;
    
    @Column(nullable = false, name="province", length = 50)
    private String province;
    
    @Column(name="pic1", length = 50)
    private String pic1;
    
    @Column(name="pic2", length = 50)
    private String pic2;
    
    @Column(name="car_status")
    private boolean carStatus = false;
    
    @Column(name="create_user")
    private String createUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", length=19)
    private Date createDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date", length=19)
    private Date updateDate;
    
    @OneToOne
    private Color color;
    
    @OneToOne
    private Bland bland;
    
    @OneToOne
    private Model model;

    @ManyToOne
   	@JoinColumn(name = "shop_id")
   	private Shop shop;
    
    @ManyToOne
 	@JoinColumn(name = "customer_id")
 	private Customer customer;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public String getVehicleRegistration() {
		return vehicleRegistration;
	}

	public void setVehicleRegistration(String vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Bland getBland() {
		return bland;
	}

	public void setBland(Bland bland) {
		this.bland = bland;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public boolean isCarStatus() {
		return carStatus;
	}

	public void setCarStatus(boolean carStatus) {
		this.carStatus = carStatus;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}