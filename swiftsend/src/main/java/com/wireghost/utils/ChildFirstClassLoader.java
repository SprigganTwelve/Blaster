package com.wireghost;


import java.net.URL;
import java.net.URLClassLoader;

public class ChildFirstClassLoader extends URLClassLoader {

    public ChildFirstClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    /**
        * This method is called to load a class named ‘name’.
        * The default behavior (in URLClassLoader) is parent-first:
        * first ask the parent, then load locally if not found.
        * Here we reverse this: we search locally first, then the parent.
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // Synchronize on a lock specific to the requested class,
        // to prevent two threads from loading the same class in parallel.
        synchronized (getClassLoadingLock(name)) {

            // Check if the class has already been loaded (avoids reloading the same class)
            Class<?> c = findLoadedClass(name);

            if (c == null) {
                try {
                    // Try to load the class locally, i.e. in the child classloader's URLs
                    c = findClass(name);
                } catch (ClassNotFoundException e) {
                    // If the class is not found locally, ask the parent (delegation)
                    c = super.loadClass(name, resolve);
                }
            }

            if (resolve) {
                // Resolve the class (connect its symbols, check dependencies)
                resolveClass(c);
            }

            return c;
        }
    }
}


