package net.wrobeseb.sqlbuilder.module.validator;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

import net.wrobeseb.sqlbuilder.module.command.QueryCommand;

@Component(value="queryCommandValidator")
public class QueryValidator {
	public void validate(QueryCommand command, ValidationContext context) {
		if (context.getUserEvent().equals("save") || context.getUserEvent().equals("exportAction")) {
			if (command.getQuery().getName().equals("")) {
				context.getMessageContext().addMessage(new MessageBuilder().error().defaultText("Pole \"Nazwa\" nie mo¿e byæ puste...").build());
			}
		}
		if (context.getUserEvent().equals("export")) {
			//if (command.getExportInfo().getEmailAddresses().length == 0) {
			//	if(command.getExportInfo().getOtherEmails().equals("")) {
			//		context.getMessageContext().addMessage(new MessageBuilder().error().defaultText("Musisz wybraæ/wpisaæ co najmniej jeden adres email...").build());
			//	}
			//}
		}
		if (command.getQuery().getQuery().equals("")) {
			context.getMessageContext().addMessage(new MessageBuilder().error().defaultText("Pole \"Zapytanie\" nie mo¿e byæ puste...").build());
		}
	}
}
