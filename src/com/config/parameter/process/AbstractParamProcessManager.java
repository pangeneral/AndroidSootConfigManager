package com.config.parameter.process;

import com.android.exception.InvalidCommandOptionException;
import com.android.exception.InvalidConfigItemException;
import com.android.exception.InvalidFileConfigException;
import com.config.parameter.utility.IHelper;

public abstract class AbstractParamProcessManager {
	
	private IHelper helper;
	
	private IParamProcess paramProcess;

	public void setParamProcess(IParamProcess paramProcess) {
		this.paramProcess = paramProcess;
	}
	
	public abstract IHelper getHelper();

	public void setHelper(IHelper helper) {
		this.helper = helper;
	}

	public abstract IParamProcess getParamProcess();
	
	public void paramProcess(String[] args) {
		try {
			this.setHelper(this.getHelper());
			this.setParamProcess(this.getParamProcess());
			
			if (args.length == 0 || args[0].equals("-h") || args[0].equals("-help")) {
				helper.printHelpMessage();
				System.exit(0);
			}
			
			if (!isArgLengthValid(args)) {
				throw new InvalidCommandOptionException();
			}
			
			paramProcess.processCommandLine(args);
			paramProcess.processConfigFile();
			paramProcess.initPath();
			
		} catch (InvalidCommandOptionException e) {
			e.printStackTrace();
		} catch (InvalidConfigItemException e) {
			e.printStackTrace();
		} catch (InvalidFileConfigException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isArgLengthValid(String[] args) {
		return args.length % 2 == 0; 
	}
}
