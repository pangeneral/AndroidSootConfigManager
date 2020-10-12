package com.soot.initialization;

import java.util.List;

import com.config.parameter.BaseConfiguration;
import soot.BodyTransformer;
import soot.Pack;
import soot.PackManager;
import soot.Scene;
import soot.Transform;
import soot.options.Options;

public abstract class AbstractSootConfig implements ISootConfig {
	
	protected abstract String getAndroidJars();
	
	protected abstract String getSootClasspath();
	
	protected abstract List<String> getProcessDir();
	
	protected abstract List<String> getExcludeList();
	
	protected abstract boolean getIsJimpleOutput();
	
	protected abstract String getOutputBasePath();
	
	protected abstract int getOutputFormat();
	
	protected abstract int getSrcPrec();
	
	protected abstract BodyTransformer getBodyTransformer();
	
	@Override
	public void sootInitialization() {
		Options.v().set_android_jars(this.getAndroidJars());
		Options.v().set_soot_classpath(this.getSootClasspath());
		Options.v().set_process_dir(this.getProcessDir());
		Options.v().set_no_bodies_for_excluded(true);
		Options.v().set_process_multiple_dex(true);
		Options.v().set_no_writeout_body_releasing(true); // must be set to true if we want to access method bodies after writing output to jimple
		if (!this.getIsJimpleOutput()) {
			Options.v().set_output_format(Options.output_format_none);
		}
		else {
			Options.v().set_output_dir(BaseConfiguration.OUTPUT_BASE_PATH);
			Options.v().set_output_format(Options.output_format_jimple);
		}
		Options.v().set_src_prec(Options.src_prec_apk);
		Options.v().allow_phantom_refs();
		Options.v().set_exclude(this.getExcludeList());
		
		Pack p1 = PackManager.v().getPack("jtp");
		String phaseName = "jtp.bt";
		
		Transform t1 = new Transform(phaseName, this.getBodyTransformer());
		
		p1.add(t1);
		
		soot.Main.v().autoSetOptions();
		
		Scene.v().loadNecessaryClasses();
		
		PackManager.v().runPacks();
		
		if (Options.v().output_format() != Options.output_format_none)
			PackManager.v().writeOutput();
	}
}
