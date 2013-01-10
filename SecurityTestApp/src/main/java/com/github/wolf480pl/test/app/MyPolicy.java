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
        if (this.restrictPlugins) {
            if (domain.getClassLoader() != AppClass.class.getClassLoader()) {
                System.out.println("Nope, you are not allowed!");
                return false;
            }
        }
        return true;
    }
}
