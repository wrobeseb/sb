package net.wrobeseb.sqlbuilder.module.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.wrobeseb.sqlbuilder.model.ListItem;
import net.wrobeseb.sqlbuilder.module.command.constants.BuilderConstants;

public class BuilderCommand implements Serializable {

	private static final long serialVersionUID = -7749676620529130513L;
	
	private String query;
	
	private List<ListItem> orderFields;
	
	private List<ListItem> orderInteractionFields;
	
	private List<ListItem> orderContractsFields;
	
	private List<ListItem> orderHistoryChangesFields;
	
	private List<ListItem> orderHistorySysChanges;
	
	private List<String> selectedOrderFields;
	
	private List<String> selectedOrderInteractionFields;
	
	private List<String> selectedOrderContractsFields;
	
	private List<String> selectedOrderHistoryChangesFields;
	
	private List<String> selectedOrderHistorySysChanges;
	
	public BuilderCommand() {
		orderFields = new ArrayList<ListItem>(BuilderConstants.orderFields);
		orderInteractionFields = new ArrayList<ListItem>(BuilderConstants.orderInteractionFields);
		orderContractsFields = new ArrayList<ListItem>(BuilderConstants.orderContractsFields);
		orderHistoryChangesFields = new ArrayList<ListItem>(BuilderConstants.orderHistoryChangesFields);
		orderHistorySysChanges = new ArrayList<ListItem>(BuilderConstants.orderHistorySysChanges);
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<ListItem> getOrderFields() {
		return orderFields;
	}

	public void setOrderFields(List<ListItem> orderFields) {
		this.orderFields = orderFields;
	}

	public List<ListItem> getOrderInteractionFields() {
		return orderInteractionFields;
	}

	public void setOrderInteractionFields(List<ListItem> orderInteractionFields) {
		this.orderInteractionFields = orderInteractionFields;
	}

	public List<ListItem> getOrderContractsFields() {
		return orderContractsFields;
	}

	public void setOrderContractsFields(List<ListItem> orderContractsFields) {
		this.orderContractsFields = orderContractsFields;
	}

	public List<ListItem> getOrderHistoryChangesFields() {
		return orderHistoryChangesFields;
	}

	public void setOrderHistoryChangesFields(
			List<ListItem> orderHistoryChangesFields) {
		this.orderHistoryChangesFields = orderHistoryChangesFields;
	}

	public List<ListItem> getOrderHistorySysChanges() {
		return orderHistorySysChanges;
	}

	public void setOrderHistorySysChanges(List<ListItem> orderHistorySysChanges) {
		this.orderHistorySysChanges = orderHistorySysChanges;
	}

	public List<String> getSelectedOrderFields() {
		return selectedOrderFields;
	}

	public void setSelectedOrderFields(List<String> selectedOrderFields) {
		this.selectedOrderFields = selectedOrderFields;
	}

	public List<String> getSelectedOrderInteractionFields() {
		return selectedOrderInteractionFields;
	}

	public void setSelectedOrderInteractionFields(
			List<String> selectedOrderInteractionFields) {
		this.selectedOrderInteractionFields = selectedOrderInteractionFields;
	}

	public List<String> getSelectedOrderContractsFields() {
		return selectedOrderContractsFields;
	}

	public void setSelectedOrderContractsFields(
			List<String> selectedOrderContractsFields) {
		this.selectedOrderContractsFields = selectedOrderContractsFields;
	}

	public List<String> getSelectedOrderHistoryChangesFields() {
		return selectedOrderHistoryChangesFields;
	}

	public void setSelectedOrderHistoryChangesFields(
			List<String> selectedOrderHistoryChangesFields) {
		this.selectedOrderHistoryChangesFields = selectedOrderHistoryChangesFields;
	}

	public List<String> getSelectedOrderHistorySysChanges() {
		return selectedOrderHistorySysChanges;
	}

	public void setSelectedOrderHistorySysChanges(
			List<String> selectedOrderHistorySysChanges) {
		this.selectedOrderHistorySysChanges = selectedOrderHistorySysChanges;
	}


}
