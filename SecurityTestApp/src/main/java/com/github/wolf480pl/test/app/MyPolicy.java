package com.github.wolf480pl.test.app;

import java.security.Permission;
import java.security.Policy;
import java.security.ProtectionDomain;

public class MyPolicy extends Policy {
	private boolean restrictPlugins = false;
	public MyPolicy(boolean restrictPlugins) {
		this.restrictPlugins = restrictPlugins;
	}

	@Override
	public boolean implies(ProtectionDomain domain, Permission permission) {
		System.out.println("Class from " + domain.getCodeSource().getLocation() + " tries to do " + permission.toString());
		if (restrictPlugins) {
			if (domain.getClassLoader() != AppClass.class.getClassLoader()) {
				System.out.println("Nope, you are not allowed!");
				return false;
			}
		}
		return true;
	}
}
