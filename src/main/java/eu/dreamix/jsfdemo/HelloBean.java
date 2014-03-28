package eu.dreamix.jsfdemo;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "helloBean")
public class HelloBean {

	public String getGreeting() {
		return "Hello";
	}

}
