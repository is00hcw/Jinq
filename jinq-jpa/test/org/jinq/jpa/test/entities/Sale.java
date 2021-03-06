package org.jinq.jpa.test.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SALES database table.
 * 
 */
@Entity
@Table(name="SALES")
@NamedQuery(name="Sale.findAll", query="SELECT s FROM Sale s")
public class Sale implements Serializable {
	private static final long serialVersionUID = 1L;
	private int saleid;
	private String date;
	private List<Lineorder> lineorders;
	private Customer customer;

	public Sale() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	public int getSaleid() {
		return this.saleid;
	}

	public void setSaleid(int saleid) {
		this.saleid = saleid;
	}


	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	//bi-directional many-to-one association to Lineorder
	@OneToMany(mappedBy="sale")
	public List<Lineorder> getLineorders() {
		return this.lineorders;
	}

	public void setLineorders(List<Lineorder> lineorders) {
		this.lineorders = lineorders;
	}

	public Lineorder addLineorder(Lineorder lineorder) {
		getLineorders().add(lineorder);
		lineorder.setSale(this);

		return lineorder;
	}

	public Lineorder removeLineorder(Lineorder lineorder) {
		getLineorders().remove(lineorder);
		lineorder.setSale(null);

		return lineorder;
	}


	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="CUSTOMERID")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}