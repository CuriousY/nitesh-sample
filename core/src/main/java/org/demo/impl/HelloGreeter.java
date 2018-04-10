package org.demo.impl;

import org.demo.service.IGreeter;
import org.osgi.service.component.annotations.Component;

@Component(service=IGreeter.class,name="Greeter Service",immediate=true)
public class HelloGreeter implements IGreeter {

	@Override
	public String sayHello() {
		// TODO Auto-generated method stub
		return "Hello there user";
	}

}
