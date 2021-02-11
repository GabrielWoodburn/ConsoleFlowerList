package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class Flower {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="TYPE")
	private String type;
	@Column (name="COLOR")
	private String color;
	@Column (name="QUANTITY")
	private int quantity;
	
	public Flower() {
		super();
	}

	public Flower(String type, String color, int quantity) {
		super();
		this.type = type;
		this.color = color;
		this.quantity = quantity;
	}
	
	public String returnItemDetails() {
		return quantity + " " + color + " " + type + "s";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
