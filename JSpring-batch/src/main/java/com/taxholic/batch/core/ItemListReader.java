package com.taxholic.batch.core;


import java.util.List;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;

public abstract class ItemListReader<T> extends AbstractItemReader<T> implements InitializingBean {
	
	private List<T> list;

	@Override
	protected T doRead() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (list != null && !list.isEmpty()) {
			return list.remove(0);
		}
		return null;
	}

	@Override 
	public void afterPropertiesSet() throws Exception {
		list = setList();
	}

	abstract public List<T> setList();
}
