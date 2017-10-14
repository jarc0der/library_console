package com.botscrew.library.app;

import com.botscrew.library.utils.HibernateUtil;

import java.util.logging.Level;

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        HibernateUtil.getSessionFactory();
        new LibraryManager().run();

        System.exit(0);
    }
}
