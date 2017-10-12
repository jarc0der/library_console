package com.botscrew.library.app;

import com.botscrew.library.utils.HibernateUtil;

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException {
        HibernateUtil.getSessionFactory();
        new LibraryManager().run();
    }
}
