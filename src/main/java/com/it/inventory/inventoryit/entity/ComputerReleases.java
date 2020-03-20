package com.it.inventory.inventoryit.entity;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="computer_releases")
public class ComputerReleases {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="release_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date releaseDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="returning_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date returningDate;
	
	@ManyToOne(
				cascade = {
						CascadeType.DETACH,
						CascadeType.MERGE,
						CascadeType.PERSIST,
						CascadeType.REFRESH
				}
			)
	@JoinColumn(name="computer_id")
	private Computer computer;
	
	public ComputerReleases () {
		
	}
	
	@ManyToOne(
				cascade = {
						CascadeType.DETACH,
						CascadeType.MERGE,
						CascadeType.PERSIST,
						CascadeType.REFRESH
				}
			)
	@JoinColumn(name="user_id")
	private User user;
	
	

	public ComputerReleases(Date releaseDate, Date returningDate, Computer computer, User user) {
		
		this.releaseDate = releaseDate;
		this.returningDate = returningDate;
		this.computer = computer;
		this.user = user;
	}
	
	

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getReturningDate() {
		return returningDate;
	}

	public void setReturningDate(Date returningDate) {
		this.returningDate = returningDate;
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}



	@Override
	public String toString() {
		return "ComputerReleases [id=" + id + ", releaseDate=" + releaseDate + ", returningDate=" + returningDate
				+ ", computer=" + computer + ", user=" + user + "]";
	}
	
	

}
