package com.taxholic.batch.core;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public abstract class AbstractItemReader<T> implements ItemReader<T>  {
	
	public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return doRead();
	}

	abstract protected T doRead() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException;


}
