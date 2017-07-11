package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	CustomerRepository customerRepository;

	// http://localhost:8080/customers/add
	@RequestMapping(value = "/customers/add", method = RequestMethod.GET)
	public Iterable<Customer> addcustomer() {
		Customer customer = new Customer("Jack", "BauerTim");

		this.customerRepository.save(customer);
		return this.customerRepository.findAll();
	}

	// @RequestMapping(value = "/customers/addcustomer")

	@RequestMapping(value = "/customers/additem/{id}", method = RequestMethod.GET)
	public Iterable<Customer> addItemToCustomerById(@PathVariable(value = "id") final Long id) {
		Optional<Customer> optional = this.customerRepository.findById(id);
		Customer customer = optional.get();
		customer.getItemList().add(new Item(null, "Fork", "Forks"));
		this.customerRepository.save(customer);
		return this.customerRepository.findAll();
	}

	// http://localhost:8080/customers/deleteallcustomers
	@RequestMapping(value = "/customers/deleteallcustomers", method = RequestMethod.GET)
	public boolean deleteAll() {
		this.customerRepository.deleteAll();
		return true;
	}

	// http://localhost:8080/customers/deletebyid?id=23
	@RequestMapping(value = "/customers/deletebyid", method = RequestMethod.GET)
	public boolean deleteCustomerById(@RequestParam(value = "id") final long id) {
		System.out.println("deleteCustomerById @RequestParam with id " + id);
		if (this.customerRepository.existsById(id)) {
			this.customerRepository.deleteById(id);

			return true;
		}
		System.out.println("No Customer_ID Like:" + id);
		return false;
	}

	// http://localhost:8080/customers/3
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
	public boolean deleteCustomerByIdPathVariable(@PathVariable(value = "id") final long id) {
		System.out.println("deleteCustomerById @PathVariable with id " + id);
		if (this.customerRepository.existsById(id)) {
			this.customerRepository.deleteById(id);
			return true;
		}
		System.out.println("No Customer_ID Like:" + id);
		return false;
	}

	// http://localhost:8080/customers
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public Iterable<Customer> getAllCustomer() {

		return this.customerRepository.findAll();
	}

	// http://localhost:8080/customers/1
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
	public Optional<Customer> getById(@PathVariable(value = "id") final long id) {
		return this.customerRepository.findById(id);
	}

	// TODO firstname lastname
	// http://localhost:8080/customers?firstName='Jack'
	// @RequestParam
	// this.customerRepository.findByFirstNameAndLastName(firstName, lastName)
	@RequestMapping(value = "/customers/getbylastname", method = RequestMethod.GET)
	public List<Customer> getByLastName(@RequestParam(value = "lastName") final String lastName) {
		return this.customerRepository.findByLastName(lastName);
	}

	// http://localhost:8080/customers?firstName='Jack'&lastName='BauerTim'
	@RequestMapping(value = "/customers/getbylastnamefistname", method = RequestMethod.GET)
	public List<Customer> getByLastNameAndFistName(
			@RequestParam(value = "lastName" + "firstName") final String lastName, final String firstName) {
		return this.customerRepository.findByLastNameAndFirstName(lastName, firstName);
	}

	@RequestMapping(value = "/customers/save", method = RequestMethod.POST)
	// erstellen neuer Customer mittels Post-Request
	public boolean saveCustomer(@RequestBody final Customer customer) {
		this.customerRepository.save(customer);
		return true;
	}

}
