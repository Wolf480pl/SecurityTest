/*
 * This file is part of SecurityTest
 * 
 * Copyright (c) 2012-2013 Wolf480pl (wolf480@interia.pl)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.wolf480pl.test.app;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.Policy;

public class AppClass {

    /**
     * @param args
     * @throws MalformedURLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        boolean restrict= false;
        if (args.length > 1) {
            restrict = Boolean.parseBoolean(args[1]);
        }
        Policy.setPolicy(new MyPolicy(restrict));
        System.setSecurityManager(new SecurityManager());
        File pluginFile = new File(args[0]);
        String className;
        if (args.length > 2) {
            className = args[2];
        } else {
            className = "com.github.wolf480pl.test.plugin.PluginClass";
        }
        URLClassLoader loader = new URLClassLoader(new URL[]{pluginFile.toURI().toURL()}, AppClass.class.getClassLoader());
        try {
            Class.forName(className, true, loader).newInstance();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        new SecurityManager();	//Check if we are still allowed to do this.
    }

}
