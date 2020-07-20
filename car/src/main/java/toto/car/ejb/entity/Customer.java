package toto.car.ejb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, name="name_th", length = 50)
    private String nameTH;
    
    @Column(nullable = false, name="surname_th", length = 50) 
    private String surnameTH;
    
    @Column(nullable = false, name="name_en", length = 50)
    private String nameEN;
    
    @Column(nullable = false, name="surname_en", length = 50)
    private String surnameEN;
    
    @Column(nullable = false, name="id_card", length = 20)
    private String idCard;
    
    @Column(nullable = false, name="license_no", length = 50)
    private String licenseNo;
    
    @Column(nullable = false, name="passport_no", length = 50)
    private String passportNo;
    
    @Column(nullable = false, name="phone_mobile", length = 50)
    private String phoneMobile;
    
    @Column(nullable = false, name="country", length = 50)
    private String country;
    
    @Column(name="pic", length = 50)
    private String pic;
    
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
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="rent_date", length=19)
    private Date rentDate;
    
    @Column(name="rent_status")
    private boolean rentStatus = false;
    
    @Column(name="comment") @Lob
    private String comment;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Car> cars; 
    
    @ManyToOne
   	@JoinColumn(name = "shop_id")
   	private Shop shop;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameTH() {
		return nameTH;
	}

	public void setNameTH(String nameTH) {
		this.nameTH = nameTH;
	}

	public String getSurnameTH() {
		return surnameTH;
	}

	public void setSurnameTH(String surnameTH) {
		this.surnameTH = surnameTH;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getSurnameEN() {
		return surnameEN;
	}

	public void setSurnameEN(String surnameEN) {
		this.surnameEN = surnameEN;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public boolean isRentStatus() {
		return rentStatus;
	}

	public void setRentStatus(boolean rentStatus) {
		this.rentStatus = rentStatus;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPhoneMobile() {
		return phoneMobile;
	}

	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}
