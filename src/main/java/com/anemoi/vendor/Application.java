package com.anemoi.vendor;

import java.util.List;

import com.anemoi.vendor.configuration.DatabaseName;
import com.anemoi.vendor.databaseConfiguration.VendorDatabase;

import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
        List<String> tenantList = DatabaseName.getAllTenant();
        for (String tenant : tenantList) {
         String dataBaseName = DatabaseName.dataBaseName(tenant);        
          VendorDatabase.createDataBases(dataBaseName);
        }   
    }
}
