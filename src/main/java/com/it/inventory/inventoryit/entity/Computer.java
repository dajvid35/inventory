package com.it.inventory.inventoryit.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="computer")
public class Computer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="serial_number")
	private String serialNumber;
	
	@Column(name="inventory_number")
	private String inventoryNumber;
	
	@Column(name="comment", length=500)
	@Length(max=500)
	private String comment;
	
	@OneToMany(mappedBy="computer",
				cascade= {
						CascadeType.ALL
						/*CascadeType.DETACH,
						CascadeType.MERGE,
						CascadeType.PERSIST,
						CascadeType.REFRESH*/
				}
			)
	private List <ComputerReleases> computerReleases;
	
	@ManyToOne(
			cascade = {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH
			}
		)
	@JoinColumn(name="model_id")
	private DeviceModel model;
		
	public Computer() {
		
	}

	

	public Computer(String name, String serialNumber, String inventoryNumber, @Length(max = 500) String comment,
			List<ComputerReleases> computerReleases) {
		
		this.name = name;
		this.serialNumber = serialNumber;
		this.inventoryNumber = inventoryNumber;
		this.comment = comment;
		this.computerReleases = computerReleases;
	}

	

	public Computer(int id, String name, String serialNumber, String inventoryNumber, @Length(max = 500) String comment,
			List<ComputerReleases> computerReleases, DeviceModel model) {
		this.id = id;
		this.name = name;
		this.serialNumber = serialNumber;
		this.inventoryNumber = inventoryNumber;
		this.comment = comment;
		this.computerReleases = computerReleases;
		this.model = model;
	}



	public DeviceModel getModel() {
		return model;
	}



	public void setModel(DeviceModel model) {
		this.model = model;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(String inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}

	

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<ComputerReleases> getComputerReleases() {
		return computerReleases;
	}

	public void setComputerReleases(List<ComputerReleases> computerReleases) {
		this.computerReleases = computerReleases;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", title=" + name + ", serialNumber=" + serialNumber + ", inventoryNumber="
				+ inventoryNumber + ", comment=" + comment + ", computerReleases=" + computerReleases + "]";
	}

	
	
	

}
