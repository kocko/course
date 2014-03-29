package eu.dreamix.jsfdemo;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "helloBean")
public class HelloBean {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGreeting() {
		return "Hello";
	}

	public void test(ActionEvent e) {

	}

	public String getSayWelcome() {
		if ("".equals(name) || name == null) {
			return "";
		} else {
			return "Ajax message : Welcome " + name;
		}
	}
}
