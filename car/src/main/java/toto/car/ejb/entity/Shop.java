package toto.car.ejb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="shop")
public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, name="shopname", length = 50)
    private String shopName;
    
    @Column(nullable = false, name="ownername_th", length = 50)
    private String ownerNameTH;
    
    @Column(nullable = false, name="ownersurname_th", length = 50)
    private String ownerSurNameTH;
    
    @Column(nullable = false, name="ownername_en", length = 50)
    private String ownerNameEN;
    
    @Column(nullable = false, name="ownersurname_en", length = 50)
    private String ownerSurNameEN;
    
    @Column(name="phone_office", length = 20)
    private String phoneOffice;
    
    @Column(name="phone_mobile", length = 20)
    private String phoneMobile;
    
    @Column(name="phone_fax", length = 20)
    private String phoneFax;
    
    @Column(name="business_registration", length = 20)
    private String businessRegistration;
    
    @Column(name="idcard", length = 20)
    private String idcard;
    
    @Column(name="shop_status")
    private boolean shopStatus;
    
    @Column(nullable = false, name="address") @Lob
    private @NotNull String address;
    
    @Column(name="create_user")
    private String createUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", length=19)
    private Date createDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date", length=19)
    private Date updateDate;
    
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
	private List<Customer> customers;
    
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
	private List<Color> colors;
    
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
	private List<Model> models;
    
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
	private List<Bland> blands;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getOwnerNameTH() {
		return ownerNameTH;
	}

	public void setOwnerNameTH(String ownerNameTH) {
		this.ownerNameTH = ownerNameTH;
	}

	public String getOwnerSurNameTH() {
		return ownerSurNameTH;
	}

	public void setOwnerSurNameTH(String ownerSurNameTH) {
		this.ownerSurNameTH = ownerSurNameTH;
	}

	public String getOwnerNameEN() {
		return ownerNameEN;
	}

	public void setOwnerNameEN(String ownerNameEN) {
		this.ownerNameEN = ownerNameEN;
	}

	public String getOwnerSurNameEN() {
		return ownerSurNameEN;
	}

	public void setOwnerSurNameEN(String ownerSurNameEN) {
		this.ownerSurNameEN = ownerSurNameEN;
	}

	public String getPhoneOffice() {
		return phoneOffice;
	}

	public void setPhoneOffice(String phoneOffice) {
		this.phoneOffice = phoneOffice;
	}

	public String getPhoneMobile() {
		return phoneMobile;
	}

	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}

	public String getPhoneFax() {
		return phoneFax;
	}

	public void setPhoneFax(String phoneFax) {
		this.phoneFax = phoneFax;
	}

	public String getBusinessRegistration() {
		return businessRegistration;
	}

	public void setBusinessRegistration(String businessRegistration) {
		this.businessRegistration = businessRegistration;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public boolean isShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(boolean shopStatus) {
		this.shopStatus = shopStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Color> getColors() {
		return colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}

	public List<Bland> getBlands() {
		return blands;
	}

	public void setBlands(List<Bland> blands) {
		this.blands = blands;
	}
}
