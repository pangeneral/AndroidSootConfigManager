package com.config.parameter;

import java.io.File;

public class BaseConfiguration {
	
//	public static final String EXCEPTION_OUTPUT_FOLDER = "./output/";
	
//	public static final String EXCEPTION_FILE_NAME = "Exception.txt";
	
	public static String ERROR_FOLDER = "error";
	
	public static int IS_JIMPLE_OUTPUT = 1;
	
	public static String JAVA_HOME = getJavaHome();
	
	public static String OUTPUT_BASE_PATH = "";
	
	public static String getTargetUnderAnalysisExceptionFilePath() {
		return ERROR_FOLDER + File.separator + "error.txt";
	}

	public static String getErrorFolder() {
		return ERROR_FOLDER;
	}

	public static String CONFIGURE_FILE = null;

	public static String APK_BASE_PATH;

	/**
	 * Current apk name under analysis.
	 */
	public static String APK_NAME;

	public static String ANDROID_PLATFORM_PATH = null;

	public static String getApkPath() {
		return APK_BASE_PATH + File.separator + APK_NAME;
	}

	private static String getJavaHome() {
		return System.getProperty("java.home") + File.separator + "lib"
				+ File.separator + "rt.jar";
	}
	
}
