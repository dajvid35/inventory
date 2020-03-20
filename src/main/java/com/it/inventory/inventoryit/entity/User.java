package com.it.inventory.inventoryit.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@OneToMany(
				mappedBy="user",
				cascade= {
						CascadeType.ALL
						/*CascadeType.DETACH,
						CascadeType.MERGE,
						CascadeType.PERSIST,
						CascadeType.REFRESH*/
						
				}
			
			)
	private List<ComputerReleases> computerReleases;
	
	public User () {
		
	}

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	

	public User(String firstName, String lastName, List<ComputerReleases> computerReleases) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.computerReleases = computerReleases;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

	public List<ComputerReleases> getComputerReleases() {
		return computerReleases;
	}

	public void setComputerReleases(List<ComputerReleases> computerReleases) {
		this.computerReleases = computerReleases;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
