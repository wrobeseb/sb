package net.wrobeseb.sqlbuilder.module;

import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.mvc.servlet.MvcExternalContext;

import net.wrobeseb.sqlbuilder.module.command.SokxCommand;

@Controller
public class SokxController {

	public SokxCommand initCommand() {
		return new SokxCommand();
	}
	
	public void search(SokxCommand sokxCommand, MessageContext messageContext, MvcExternalContext externalContext) {
		
	}
}
