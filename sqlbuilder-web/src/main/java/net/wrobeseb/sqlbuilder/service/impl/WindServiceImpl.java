package net.wrobeseb.sqlbuilder.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.wrobeseb.sqlbuilder.dao.ISqlBuilderDao;
import net.wrobeseb.sqlbuilder.model.ResultRow;
import net.wrobeseb.sqlbuilder.service.IWindService;

@Service(value="windService")
public class WindServiceImpl implements IWindService {

	@Autowired(required=true)
	private ISqlBuilderDao sqlBuilderDao;
	
	@Override
	@Transactional(value="dynamic", propagation=Propagation.SUPPORTS, readOnly=true)
	public Hashtable<String, ArrayList<Integer>> createRecoveryExcludeDeviceHash() {
		Hashtable<String, ArrayList<Integer>> deviceHash = new Hashtable<String, ArrayList<Integer>>();

		List<ResultRow> results = sqlBuilderDao.executeQuery("SELECT o.* " +
															 "FROM orders o " +
															 "WHERE ((o.status_id NOT IN (14,6,8,1,2,7,9,5) AND o.substatus_id IN (13,12,11,28,29,30)) " +
																	"OR (o.status_id IN (14,6,8,1,2,7,9,5))) " +
																	"AND o.order_type_id IN (30,7,23,9,28,25,10,15) " +
																	"AND o.source_id NOT IN (10,36) " +
																	"AND o.billing_dt IS NULL " +
																	"AND (sysdate - o.application_dt) > 31 " +
																	"AND (sysdate - CAST(o.created_dt AS DATE)) > 31 " +
																	"AND sysdate > o.request_dt");
	
		if (results != null && results.size() != 0) {
			Integer deviceNoIndex = results.get(0).getColumnNames().indexOf("DESIGNATION_NUM");
			Integer orderIdIndex = results.get(0).getColumnNames().indexOf("ORDER_ID");
			for (ResultRow resultRow : results) {
				String deviceNo = (String) resultRow.getValues().get(deviceNoIndex);
				BigDecimal orderId = (BigDecimal) resultRow.getValues().get(orderIdIndex);

				if (deviceNo != null) {
					if (deviceHash.containsKey(deviceNo)) {
						ArrayList<Integer> tempArray = (ArrayList<Integer>)deviceHash.get(deviceNo);
						tempArray.add(Integer.valueOf(orderId.intValue()));
						deviceHash.remove(deviceNo);
						deviceHash.put(deviceNo, tempArray);
					}
					else {
						ArrayList<Integer> tempArray = new ArrayList<Integer>();
						tempArray.add(Integer.valueOf(orderId.intValue()));
						deviceHash.put(deviceNo, tempArray);
					}
				}
			}
		}
		
		return deviceHash;
	}
	
}
