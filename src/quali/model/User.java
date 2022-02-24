package quali.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String address;

	private String phone;

	private String picture;

	private String birthDay;

	private boolean isAdmin;

	public User(String firstName, String lastName, String email, String password, String address, String phone,
			String picture, String birthDay, boolean isAdmin) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.picture = picture;
		this.birthDay = birthDay;
		this.isAdmin = isAdmin;
	}

	public String getFirstName() {
		return firstName;
	}

	public StringProperty getFirstNameProperty() {
		return new SimpleStringProperty(firstName);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public StringProperty getLastNameProperty() {
		return new SimpleStringProperty(lastName);
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public StringProperty getEmailProperty() {
		return new SimpleStringProperty(email);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public StringProperty getPasswordProperty() {
		return new SimpleStringProperty(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public StringProperty getAddressProperty() {
		return new SimpleStringProperty(address);
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public StringProperty getPhoneProperty() {
		return new SimpleStringProperty(phone);
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public StringProperty getBirthdayProperty() {
		return new SimpleStringProperty(birthDay);
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public StringProperty getAdminProperty() {
		return new SimpleStringProperty(String.valueOf(isAdmin));
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


}
