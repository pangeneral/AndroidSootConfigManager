package com.android;

import com.config.parameter.process.AbstractParamProcessManager;
import com.config.parameter.process.DefaultParamProcessManager;
import com.soot.initialization.DefaultSootConfig;
import com.soot.initialization.ISootConfig;

public class Main {
	
	private ISootConfig sootConfig;
	
	public ISootConfig getSootConfig() {
		return sootConfig;
	}

	public void setSootConfig(ISootConfig sootConfig) {
		this.sootConfig = sootConfig;
	}
	
	private AbstractParamProcessManager processManager;

	public AbstractParamProcessManager getProcessManager() {
		return processManager;
	}

	public void setProcessManager(AbstractParamProcessManager processManager) {
		this.processManager = processManager;
	}

	public void sootInit(String[] args) {
		if (this.getProcessManager() == null) {
			this.setProcessManager(new DefaultParamProcessManager());
		}
		processManager.paramProcess(args);
		
		if (this.getSootConfig() == null) {
			this.setSootConfig(new DefaultSootConfig());
		}		
		this.sootConfig.sootInitialization();
	}
	
	public static void main(String[] args) {
		Main mainInstance = new Main();
		mainInstance.sootInit(args);
	}
}
