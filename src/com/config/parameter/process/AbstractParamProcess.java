package com.config.parameter.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.android.exception.InvalidCommandOptionException;
import com.android.exception.InvalidConfigItemException;
import com.android.exception.InvalidFileConfigException;
import com.config.parameter.BaseConfiguration;

public abstract class AbstractParamProcess implements IParamProcess {
	 
	protected abstract boolean setCustomOption(String option, String value);
	
	protected abstract void initCustomPath();
	
	@Override
	public void processCommandLine(String[] args) throws InvalidCommandOptionException, InvalidConfigItemException {
		for(int i = 0; i < args.length; i += 2) {
			if( args[i].charAt(0) != '-' ) {
				throw new InvalidCommandOptionException("Invalid command option " + args[i] + ", " + InvalidCommandOptionException.helpMessage);
			}
			String configureItem = args[i].substring(1);
			if (setOption(configureItem, args[i + 1]) || setCustomOption(configureItem, args[i + 1])) {
				continue;
			}
			else {
				throw new InvalidCommandOptionException("Invalid command option " + args[i] + ", " + InvalidCommandOptionException.helpMessage);
			}
		}
	}
	
	@Override
	public void processConfigFile() throws InvalidFileConfigException, InvalidConfigItemException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(BaseConfiguration.CONFIGURE_FILE)));
			String strLine = null;
			while ((strLine = br.readLine()) != null) {
				String lineArray[] = strLine.split("\\s+");
				if (lineArray.length != 2) {
					continue;
				}
				if (!setOption(lineArray[0], lineArray[1])) {
					setCustomOption(lineArray[0], lineArray[1]);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initPath() {
		File jimpleFolder = new File(BaseConfiguration.OUTPUT_BASE_PATH);
		if (!jimpleFolder.exists())
			jimpleFolder.mkdir();
		
		BaseConfiguration.OUTPUT_BASE_PATH = BaseConfiguration.OUTPUT_BASE_PATH + File.separator + 
				BaseConfiguration.APK_NAME.substring(0, BaseConfiguration.APK_NAME.indexOf("."));
		this.initFileFolder(new File(BaseConfiguration.OUTPUT_BASE_PATH));
		
		BaseConfiguration.ERROR_FOLDER = BaseConfiguration.OUTPUT_BASE_PATH + File.separator + BaseConfiguration.ERROR_FOLDER; 
		this.initFileFolder(new File(BaseConfiguration.ERROR_FOLDER));
		
		this.initCustomPath();
	}
	
	private void initFileFolder(File fileFolder) {	
		if (fileFolder.exists()) {
			for (String s : fileFolder.list()) {
				String filePath = fileFolder + File.separator + s;
				File currentFile = new File(filePath);
				currentFile.delete();
			}
		} else {
			fileFolder.mkdirs();
		}
	}
	
	private boolean setOption(String option, String value) throws InvalidConfigItemException {
		switch(option) {
			case "configureFile":
				BaseConfiguration.CONFIGURE_FILE = value;
				break;
			case "isJimpleOutput":
				BaseConfiguration.IS_JIMPLE_OUTPUT = Integer.parseInt(value);
				if(BaseConfiguration.IS_JIMPLE_OUTPUT != 1 && BaseConfiguration.IS_JIMPLE_OUTPUT != 0 )
					throw new InvalidConfigItemException("The config item 'isJimpleOutput' can only be 0 or 1");
				break;
			case "apkName":
				BaseConfiguration.APK_NAME = value;
				break;
			case "apkBasePath":
				BaseConfiguration.APK_BASE_PATH = value;
				break;
			case "outputBasePath":
				BaseConfiguration.OUTPUT_BASE_PATH = value;
				break;
			case "androidPath":
				BaseConfiguration.ANDROID_PLATFORM_PATH = value;
				break;
			case "javaHome":
				BaseConfiguration.JAVA_HOME = value;
				break;
			case "help":
			case "h":
				break;
			default:
				return false;
		}
		return true;
	}
}
