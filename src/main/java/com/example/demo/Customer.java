// tag::sample[]
package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Item> itemList = new ArrayList<>();

	public Customer(final String firstName, final String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	protected Customer() {
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Long getId() {
		return this.id;
	}

	public List<Item> getItemList() {
		return this.itemList;
	}

	public String getLastName() {
		return this.lastName;
	}

	public List<Item> setItemList(final List<Item> itemList) {
		return this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "CustomerToString [id=" + this.id + ", first Name=" + this.firstName + ", lastName=" + this.lastName
				+ ", itemList=" + this.itemList + "]";
	}

}
