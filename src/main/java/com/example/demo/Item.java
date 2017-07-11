package com.example.demo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String itemName;
	private String itemDescription;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Customer customer;

	public Item(final Long id, final String itemName, final String itemDescription) {
		this.id = id;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
	}

	protected Item() {
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public Long getId() {
		return this.id;
	}

	public String getItemDescription() {
		return this.itemDescription;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	public void setItemDescription(final String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "Item [id=" + this.id + ", itemName=" + this.itemName + ", itemDescription=" + this.itemDescription
				+ "]";
	}

}
