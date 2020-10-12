package com.soot.initialization;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.config.parameter.BaseConfiguration;

import soot.Body;
import soot.BodyTransformer;
import soot.options.Options;

public class DefaultSootConfig extends AbstractSootConfig {
	
	@Override
	protected String getAndroidJars() {
		return BaseConfiguration.ANDROID_PLATFORM_PATH;
	}

	@Override
	protected String getSootClasspath() {
		return BaseConfiguration.JAVA_HOME + File.pathSeparator + this.getAndroidJarPath();
	}

	@Override
	protected List<String> getProcessDir() {
		return Collections.singletonList(BaseConfiguration.getApkPath());
	}

	@Override
	protected List<String> getExcludeList() {
		ArrayList<String> excludeList = new ArrayList<String>();
		excludeList.add("android.*");
		excludeList.add("org.*");
		excludeList.add("soot.*");
		excludeList.add("java.*");
		excludeList.add("sun.*");
		excludeList.add("javax.*");
		excludeList.add("com.sun.*");
		excludeList.add("com.ibm.*");
		excludeList.add("org.xml.*");
		excludeList.add("org.w3c.*");
		excludeList.add("apple.awt.*");
		excludeList.add("com.apple.*");
		return excludeList;
	}

	@Override
	protected boolean getIsJimpleOutput() {
		return BaseConfiguration.IS_JIMPLE_OUTPUT == 1;
	}

	@Override
	protected String getOutputBasePath() {
		return BaseConfiguration.OUTPUT_BASE_PATH;
	}

	@Override
	protected int getOutputFormat() {
		return Options.output_format_jimple;
	}

	@Override
	protected int getSrcPrec() {
		return Options.src_prec_apk;
	}
	
	private String getAndroidJarPath() {
		File file = new File(BaseConfiguration.ANDROID_PLATFORM_PATH);
		String fileArray[] = file.list();
		int max = 0;
		String path = "";
		for (int i = 0; i < fileArray.length; i++) {
			int version = Integer.parseInt(fileArray[i].substring(fileArray[i].indexOf("-") + 1));
			if (version > max) {
				max = version;
				path = fileArray[i];
			}
		}
		return BaseConfiguration.ANDROID_PLATFORM_PATH + File.separator + path + File.separator + "android.jar";
	}

	@Override
	protected BodyTransformer getBodyTransformer() {
		return new BodyTransformer() {
			@Override
			protected void internalTransform(Body b, String phase,
					Map<String, String> options) {
				b.getMethod().setActiveBody(b);
			}
		};
	}

}
