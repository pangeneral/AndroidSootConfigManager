package com.config.parameter.process;

import com.android.exception.InvalidCommandOptionException;
import com.android.exception.InvalidConfigItemException;
import com.android.exception.InvalidFileConfigException;

public interface IParamProcess {
	
	public void initPath();
	
	public void processCommandLine(String[] args) throws InvalidCommandOptionException, InvalidConfigItemException;
	
	public void processConfigFile() throws InvalidFileConfigException, InvalidConfigItemException;
}
