package net.wrobeseb.sqlbuilder.module.command.constants;

import java.util.ArrayList;
import java.util.List;

import net.wrobeseb.sqlbuilder.model.ListItem;

public class BuilderConstants {

	public static List<ListItem> orderFields;
	
	public static List<ListItem> orderInteractionFields;
	
	public static List<ListItem> orderContractsFields;
	
	public static List<ListItem> orderHistoryChangesFields;
	
	public static List<ListItem> orderHistorySysChanges;
	
	static {
		orderFields = new ArrayList<ListItem>();
		orderFields.add(new ListItem("PRODUCT_ID","p.NAME AS \"Produkt\"", true, "LEFT JOIN products p ON (p.product_id = o.product_id)"));
		orderFields.add(new ListItem("STATUS_ID","statusDict.VALUE AS \"Status zamowienia\"", true, "LEFT JOIN sys_dictionary_items statusDict ON (statusDict.code = o.status_id) AND statusDict.dictionary_group_id = 15"));
		orderFields.add(new ListItem("RESP_EMPLOYEE_ID","respEmplUser.LASTNAME || ' ' || respEmplUser.FIRSTNAME AS \"Osoba\"", true, "LEFT JOIN sys_users respEmplUser ON (respEmplUser.user_id = o.resp_employee_id)"));
		orderFields.add(new ListItem("RESP_UNIT_ID","ou.NAME AS \"Sekcja\"", true, "LEFT JOIN organizational_units ou ON (ou.unit_id = o.RESP_UNIT_ID)"));
		orderFields.add(new ListItem("SOURCE_ID","sourceDict.VALUE AS \"Kanal wplywu\"", true, "LEFT JOIN sys_dictionary_items sourceDict ON (sourceDict.code = o.SOURCE_ID) AND sourceDict.dictionary_group_id = 10"));
		orderFields.add(new ListItem("CANCEL_SUBREASON_ID","cancelSubDict.VALUE AS \"Powod anulacji\"", true, "LEFT JOIN sys_dictionary_items cancelSubDict ON (cancelSubDict.code = o.CANCEL_SUBREASON_ID) AND cancelSubDict.dictionary_group_id = 242"));
		orderFields.add(new ListItem("SALES_EMPLOYEE_ID","salesEmplUser.LASTNAME || ' ' || salesEmplUser.FIRSTNAME AS \"Sprzedawca\"", true, "LEFT JOIN sys_users salesEmplUser ON (salesEmplUser.user_id = o.SALES_EMPLOYEE_ID)"));
		orderFields.add(new ListItem("CAMPAIGN_ID","campaignDict.VALUE AS \"Kampania\"", true, "LEFT JOIN sys_dictionary_items campaignDict ON (campaignDict.code = o.CAMPAIGN_ID) AND campaignDict.dictionary_group_id = 17"));
		orderFields.add(new ListItem("FSTUDY_RESULT_ID","fstudyResultDict.VALUE AS \"Status wywiadu\"", true, "LEFT JOIN sys_dictionary_items fstudyResultDict ON (fstudyResultDict.code = o.FSTUDY_RESULT_ID) AND fstudyResultDict.dictionary_group_id = 29"));
		orderFields.add(new ListItem("EXTERNAL_SYSTEM_ID","extSysDict.VALUE AS \"Obcy system\"", true, "LEFT JOIN sys_dictionary_items extSysDict ON (extSysDict.code = o.EXTERNAL_SYSTEM_ID) AND extSysDict.dictionary_group_id = 103"));
		orderFields.add(new ListItem("CREATEDBY"));
		orderFields.add(new ListItem("MODIFIEDBY"));
		orderFields.add(new ListItem("APPLICATION_DT"));
		orderFields.add(new ListItem("RECEIPT_DT"));
		orderFields.add(new ListItem("CANCEL_DT"));
		orderFields.add(new ListItem("ARCHIVE_DT"));
		orderFields.add(new ListItem("BILLING_DT"));
		orderFields.add(new ListItem("DOCS_ARCHIVE_DT"));
		orderFields.add(new ListItem("REQUEST_DT"));
		orderFields.add(new ListItem("FSTUDY_SEND_DT"));
		orderFields.add(new ListItem("FSTUDY_RETURN_DT"));
		orderFields.add(new ListItem("WRKORD_SEND_DT"));
		orderFields.add(new ListItem("WRKORD_RETURN_DT"));
		orderFields.add(new ListItem("WRKORD_COMPLETE_DT"));
		orderFields.add(new ListItem("PEOPLESOFT_CASE_NUM"));
		orderFields.add(new ListItem("PEOPLESOFT_ORDER_NUM"));
		orderFields.add(new ListItem("WRKORD_NUM"));
		orderFields.add(new ListItem("FSTUDY_NUM"));
		orderFields.add(new ListItem("EXTERNAL_NUM"));
		orderFields.add(new ListItem("BILLING_NUM"));
		orderFields.add(new ListItem("REGISTER_REMARKS"));
		orderFields.add(new ListItem("PROCESS_REMARKS"));
		orderFields.add(new ListItem("CREATED_DT"));
		orderFields.add(new ListItem("MODIFIED_DT"));
		orderFields.add(new ListItem("BILLING_TASK_DT"));
		orderFields.add(new ListItem("PRODUCT_NAME"));
		orderFields.add(new ListItem("PRODUCT_PARAMETERS"));
		orderFields.add(new ListItem("FSTUDY_ALTER_DT"));
		orderFields.add(new ListItem("SYSTEM_REMARKS"));
		orderFields.add(new ListItem("BILLING_NUM2"));
		orderFields.add(new ListItem("DESIGNATION_NUM2"));
		orderFields.add(new ListItem("DEINSTALL_REASON_ID","deinstallDict.VALUE AS \"Powod likwidacji\"", true, "LEFT JOIN sys_dictionary_items deinstallDict ON (deinstallDict.code = o.DEINSTALL_REASON_ID) AND deinstallDict.dictionary_group_id = 185"));
		orderFields.add(new ListItem("PEOPLESOFT_ORDER_ID"));
		orderFields.add(new ListItem("PRODUCT_PROMOTION_ID","pp.NAME AS \"Promocja\"", true, "LEFT JOIN product_promotions pp ON (pp.promotion_id = o.PRODUCT_PROMOTION_ID)"));
		orderFields.add(new ListItem("APPROVAL_DT"));
		orderFields.add(new ListItem("DESIGNATION_NUM"));
		orderFields.add(new ListItem("CANCEL_REASON_ID","cancelDict.VALUE AS \"Domena anulacji\"", true, "LEFT JOIN sys_dictionary_items cancelDict ON (cancelDict.code = o.CANCEL_REASON_ID) AND cancelDict.dictionary_group_id = 22"));
		orderFields.add(new ListItem("SUBSTATUS_ID","substatusDict.VALUE AS \"SubStatus\"", true, "LEFT JOIN sys_dictionary_items substatusDict ON (substatusDict.code = o.SUBSTATUS_ID) AND substatusDict.dictionary_group_id = 359"));
		orderFields.add(new ListItem("PEOPLESOFT_SUBORDER_NUM"));
		
		orderInteractionFields = new ArrayList<ListItem>();
		orderInteractionFields.add(new ListItem("INTERACTION_ID"));
		orderInteractionFields.add(new ListItem("CREATED_DT"));
		orderInteractionFields.add(new ListItem("CREATEDBY"));
		orderInteractionFields.add(new ListItem("REMARKS"));
		orderInteractionFields.add(new ListItem("ORDER_ID"));
		orderInteractionFields.add(new ListItem("FLD_PHONE"));
		orderInteractionFields.add(new ListItem("FLD_EMAIL"));
		orderInteractionFields.add(new ListItem("INTERACTION_TYPE_ID","interTypeDict.VALUE AS \"Typ interakcji\"", true, "LEFT JOIN sys_dictionary_items interTypeDict ON (interTypeDict.code = oi.INTERACTION_TYPE_ID) AND interTypeDict.dictionary_group_id = 184"));
		orderInteractionFields.add(new ListItem("MODIFIED_DT"));
		orderInteractionFields.add(new ListItem("MODIFIEDBY"));
		orderInteractionFields.add(new ListItem("FLD_INFO1"));
		orderInteractionFields.add(new ListItem("FLD_INFO2"));
		orderInteractionFields.add(new ListItem("OPEN_DT"));
		orderInteractionFields.add(new ListItem("CLOSE_DT"));
		orderInteractionFields.add(new ListItem("FLD_DICT_ITEM1_ID"));
		orderInteractionFields.add(new ListItem("FLD_DICT_ITEM2_ID"));
		orderInteractionFields.add(new ListItem("FLD_DICT_ITEM3_ID"));
		orderInteractionFields.add(new ListItem("FLD_CHBOX1"));
		orderInteractionFields.add(new ListItem("FLD_CHBOX2"));
		orderInteractionFields.add(new ListItem("FLD_CHBOX3"));
		orderInteractionFields.add(new ListItem("FLD_CHBOX4"));
		orderInteractionFields.add(new ListItem("FLD_CHBOX5"));
		orderInteractionFields.add(new ListItem("FLD_CHBOX6"));
		orderInteractionFields.add(new ListItem("FLD_DATE1"));
		orderInteractionFields.add(new ListItem("PEOPLESOFT_SEND_FLG"));
		orderInteractionFields.add(new ListItem("PEOPLESOFT_STATUS_ID"));
		orderInteractionFields.add(new ListItem("QUANTITY"));
		
		orderContractsFields = new ArrayList<ListItem>();
		orderContractsFields.add(new ListItem("CONTRACT_ID"));
		orderContractsFields.add(new ListItem("CONTRACT_NUM"));
		orderContractsFields.add(new ListItem("SEND_DT"));
		orderContractsFields.add(new ListItem("RETURN_DT"));
		orderContractsFields.add(new ListItem("SIGN_DT"));
		orderContractsFields.add(new ListItem("CREATED_DT"));
		orderContractsFields.add(new ListItem("VERIFY_DT"));
		orderContractsFields.add(new ListItem("ORDER_ID"));
		orderContractsFields.add(new ListItem("STATUS_ID"));
		orderContractsFields.add(new ListItem("CONTRACT_TYPE_ID","contractTypeDict.VALUE AS \"Typ umowy\"", true, "LEFT JOIN sys_dictionary_items contractTypeDict ON (contractTypeDict.code = oc.CONTRACT_TYPE_ID) AND contractTypeDict.dictionary_group_id = 383"));
		orderContractsFields.add(new ListItem("RESEND_DT"));
		orderContractsFields.add(new ListItem("DUE_DT"));
		orderContractsFields.add(new ListItem("ORIG_RETURN_DT"));
		orderContractsFields.add(new ListItem("BARCODE_NUM"));
		orderContractsFields.add(new ListItem("ORIG_RETURN_REG_DT"));
		orderContractsFields.add(new ListItem("MODIFIED_DT"));
		orderContractsFields.add(new ListItem("MODIFIEDBY"));
		orderContractsFields.add(new ListItem("CREATEDBY"));
		orderContractsFields.add(new ListItem("RETURN_REGISTEREDBY"));
		orderContractsFields.add(new ListItem("ORIG_PREVENTION_MONIT_DT"));
		orderContractsFields.add(new ListItem("ORIG_INTERVENTION_MONIT_DT"));
		orderContractsFields.add(new ListItem("REMINDER_MONIT_DT"));
		orderContractsFields.add(new ListItem("PREVENTION_MONIT_DT"));
		orderContractsFields.add(new ListItem("INTERVENTION_MONIT_DT"));
		orderContractsFields.add(new ListItem("SMS_SEND_DT"));
		
		orderHistoryChangesFields = new ArrayList<ListItem>();
		orderHistoryChangesFields.add(new ListItem("HISTORY_CHANGE_ID"));
		orderHistoryChangesFields.add(new ListItem("ORDER_ID"));
		orderHistoryChangesFields.add(new ListItem("DESCRIPTION"));
		orderHistoryChangesFields.add(new ListItem("MODIFIEDBY"));
		orderHistoryChangesFields.add(new ListItem("MODIFIED_DT"));
		orderHistoryChangesFields.add(new ListItem("FIELD_NAME"));
		orderHistoryChangesFields.add(new ListItem("OLD_VALUE"));
		orderHistoryChangesFields.add(new ListItem("NEW_VALUE"));
		
		orderHistorySysChanges = new ArrayList<ListItem>();
		orderHistorySysChanges.add(new ListItem("ORDER_ID"));
		orderHistorySysChanges.add(new ListItem("FSTUDY_SEND_REG_DT"));
		orderHistorySysChanges.add(new ListItem("FSTUDY_RETURN_REG_DT"));
		orderHistorySysChanges.add(new ListItem("WRKORD_SEND_REG_DT"));
		orderHistorySysChanges.add(new ListItem("WRKORD_RETURN_REG_DT"));
		orderHistorySysChanges.add(new ListItem("BILLING_REG_DT"));
	}
}
