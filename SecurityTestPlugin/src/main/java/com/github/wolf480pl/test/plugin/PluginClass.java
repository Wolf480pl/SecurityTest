package com.github.wolf480pl.test.plugin;

import java.io.File;

public class PluginClass {
	public PluginClass() {
		File f = new File("./blahblah/");
		f.mkdirs();
		new SecurityManager();
	}

}
