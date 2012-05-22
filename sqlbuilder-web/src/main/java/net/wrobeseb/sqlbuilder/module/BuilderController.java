package net.wrobeseb.sqlbuilder.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.wrobeseb.sqlbuilder.model.ListItem;
import net.wrobeseb.sqlbuilder.module.command.BuilderCommand;
import net.wrobeseb.sqlbuilder.module.command.constants.BuilderConstants;
import net.wrobeseb.sqlbuilder.module.command.enums.BuilderTableEnum;
import net.wrobeseb.sqlbuilder.service.ISqlBuilderService;

@Controller
public class BuilderController {

	@Autowired(required=true)
	private ISqlBuilderService sqlBuilderService;

	public BuilderCommand initCommand() {
		return new BuilderCommand();
	}

	public void buildQuery(BuilderCommand command) {
		StringBuffer query = new StringBuffer();
		StringBuffer dictJoins = new StringBuffer();
		query.append(renderSelect(command, dictJoins)); query.append("\n");
		query.append(renderFrom(command));
		query.append(dictJoins);
		
		command.setQuery(query.toString());
	}
	
	private String renderSelect(BuilderCommand command, StringBuffer dictJoins) {
		StringBuffer select = new StringBuffer("SELECT ");
		if (command.getSelectedOrderFields() != null && command.getSelectedOrderFields().size() != 0) {
			select.append(getSelectFields(command.getSelectedOrderFields(), dictJoins, BuilderTableEnum.ORDERS));
		}
		if (command.getSelectedOrderInteractionFields() != null && command.getSelectedOrderInteractionFields().size() != 0) {
			select.append(getSelectFields(command.getSelectedOrderInteractionFields(), dictJoins, BuilderTableEnum.ORDER_INTERACTIONS));
		}
		if (command.getSelectedOrderContractsFields() != null && command.getSelectedOrderContractsFields().size() != 0) {
			select.append(getSelectFields(command.getSelectedOrderContractsFields(), dictJoins, BuilderTableEnum.ORDER_CONTRACTS));
		}
		if (command.getSelectedOrderHistoryChangesFields() != null && command.getSelectedOrderHistoryChangesFields().size() != 0) {
			select.append(getSelectFields(command.getSelectedOrderHistoryChangesFields(), dictJoins, BuilderTableEnum.ORDER_HISTORY_CHANGES));
		}
		if (command.getSelectedOrderHistorySysChanges() != null && command.getSelectedOrderHistorySysChanges().size() != 0) {
			select.append(getSelectFields(command.getSelectedOrderHistorySysChanges(), dictJoins, BuilderTableEnum.ORDER_HISTORY_SYS_CHANGES));
		}
		if (select.length() > 7) {
			return select.toString().substring(0, select.toString().length() - 2);
		}
		else {
			return "";
		}
	}
	
	private String renderFrom(BuilderCommand command) {
		StringBuffer from = new StringBuffer("FROM ");
		if (command.getSelectedOrderFields() != null && command.getSelectedOrderFields().size() != 0) {
			from.append("orders o ");
		}
		if (command.getSelectedOrderInteractionFields() != null && command.getSelectedOrderInteractionFields().size() != 0) {
			if (command.getSelectedOrderFields() != null && command.getSelectedOrderFields().size() != 0) {
				from.append("\n\t").append("INNER JOIN order_interactions oi ON (o.order_id = oi.order_id) ");
			}
			else {
				from.append("order_interactions oi ");
			}
		}
		if (command.getSelectedOrderContractsFields() != null && command.getSelectedOrderContractsFields().size() != 0) {
			if (command.getSelectedOrderFields() != null && command.getSelectedOrderFields().size() != 0) {
				from.append("\n\t").append("INNER JOIN order_contracts oc ON (o.order_id = oc.order_id) ");
			}
			else {
				from.append("order_contracts oc ");
			}
		}
		if (command.getSelectedOrderHistoryChangesFields() != null && command.getSelectedOrderHistoryChangesFields().size() != 0) {
			if (command.getSelectedOrderFields() != null && command.getSelectedOrderFields().size() != 0) {
				from.append("\n\t\t").append("INNER JOIN order_history_changes ohc ON (o.order_id = ohc.order_id) ");
			}
			else {
				from.append("order_history_changes ohc ");
			}
		}
		if (command.getSelectedOrderHistorySysChanges() != null && command.getSelectedOrderHistorySysChanges().size() != 0) {
			if (command.getSelectedOrderFields() != null && command.getSelectedOrderFields().size() != 0) {
				from.append("\n\t").append("INNER JOIN order_history_sys_changes ohsc ON (o.order_id = ohsc.order_id) ");
			}
			else {
				from.append("order_history_sys_changes ohsc ");
			}
		}
		return from.toString();
	}
	
	private String getSelectFields(List<String> list, StringBuffer dictJoins, BuilderTableEnum builderTableEnum) {
		StringBuffer buffer = new StringBuffer();
		String prefix = builderTableEnum.getPrefix();
		for (String value : list) {
			ListItem listItem = getListItemByValue(value, builderTableEnum);
			if (listItem != null) {
				if (listItem.getDictionaryValue()) {
					buffer.append(listItem.getValue1() + ", ");
					dictJoins.append(listItem.getJoinClause() + "\n");
				}
				else {
					buffer.append(prefix + "." + listItem.getValue() + ", ");
				}
			}
		}
		return buffer.toString();
	}
	
	private ListItem getListItemByValue(String value, BuilderTableEnum builderTableEnum) {
		List<ListItem> list = null;
		switch (builderTableEnum) {
			case ORDERS:
				list = BuilderConstants.orderFields;
				break;
			case ORDER_INTERACTIONS:
				list = BuilderConstants.orderInteractionFields;
				break;
			case ORDER_CONTRACTS:
				list = BuilderConstants.orderContractsFields;
				break;
			case ORDER_HISTORY_CHANGES:
				list = BuilderConstants.orderHistoryChangesFields;
				break;
			case ORDER_HISTORY_SYS_CHANGES:
				list = BuilderConstants.orderHistorySysChanges;
				break;
		}
		for (ListItem listItem : list) {
			if (listItem.getValue().equals(value)) {
				return listItem;
			}
		}
		return null;
	}
 
}
