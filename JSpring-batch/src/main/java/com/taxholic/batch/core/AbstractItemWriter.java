package com.taxholic.batch.core;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public abstract class AbstractItemWriter<I> implements ItemWriter<I> {

	abstract protected void doWrite(List<? extends I> input) throws Exception;

	public void write(List<? extends I> list) throws Exception {
		doWrite(list);
	}

}
