package com.taxholic.batch.core;

import org.springframework.batch.item.ItemProcessor;
public abstract class AbstractItemProcessor<I, O>  implements ItemProcessor<I, O> {
	
	public O process(I input) throws Exception {
		return doProcess(input);
	}

	abstract protected O doProcess(I input) throws Exception;

}
