package com.config.parameter.process;

import com.config.parameter.utility.DefaultHelper;
import com.config.parameter.utility.IHelper;

public class DefaultParamProcessManager extends AbstractParamProcessManager {

	@Override
	public IParamProcess getParamProcess() {
		return new DefaultParamProcess();
	}

	@Override
	public IHelper getHelper() {
		return new DefaultHelper();
	}

}
