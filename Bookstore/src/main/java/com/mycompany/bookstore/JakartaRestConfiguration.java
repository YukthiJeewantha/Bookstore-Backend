package com.mycompany.bookstore;

import com.mycompany.bookstore.storage.DataStore;
import com.mycompany.bookstore.util.DataInitializer;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class JakartaRestConfiguration extends Application {
    
    private static final DataStore DATA_STORE = new DataStore();
    
    public JakartaRestConfiguration() {
        // Initialize sample data
        DataInitializer.initializeData(DATA_STORE);
    }
    
    public static DataStore getDataStore() {
        return DATA_STORE;
    }
}