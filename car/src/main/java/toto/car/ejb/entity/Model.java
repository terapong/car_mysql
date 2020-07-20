package toto.car.ejb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="model")
public class Model implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, name="name", length = 50)
    private String name;
    
    @Column(name="create_user")
    private String createUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", length=19)
    private Date createDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date", length=19)
    private Date updateDate;
    
    @ManyToOne
	@JoinColumn(name = "bland_id")
	private Bland bland;
    
    @ManyToOne
	@JoinColumn(name = "shop_id")
	private Shop shop;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Bland getBland() {
		return bland;
	}

	public void setBland(Bland bland) {
		this.bland = bland;
	}
}