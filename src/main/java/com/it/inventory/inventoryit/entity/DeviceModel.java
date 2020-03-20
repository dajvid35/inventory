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

@Entity
@Table(name="model")
public class DeviceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="model_name")
	private String modelName;
	
	@OneToMany(
			mappedBy="model",
			cascade= {
				
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH
					
			}
		
		)
	private List<Computer> computer;
	
	@ManyToOne(
			cascade = {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH
			}
		)
	@JoinColumn(name="manufacturer_id")
	private Manufacturer manufacturer;

	public String getModelName() {
		return modelName;
	}
	
	
	
	public DeviceModel() {
	}

	

	public Manufacturer getManufacturer() {
		return manufacturer;
	}



	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}



	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public DeviceModel(String modelName) {
		this.modelName = modelName;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Model [id=" + id + ", modelName=" + modelName + "]";
	}
	
	
}
