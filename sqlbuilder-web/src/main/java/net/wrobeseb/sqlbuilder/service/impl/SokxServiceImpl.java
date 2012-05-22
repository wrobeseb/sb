package net.wrobeseb.sqlbuilder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import net.wrobeseb.sqlbuilder.dao.ISqlBuilderDao;
import net.wrobeseb.sqlbuilder.service.ISokxService;

public class SokxServiceImpl implements ISokxService {
	
	@Autowired(required=true)
	private ISqlBuilderDao sqlBuilderDao;
	
	
}
