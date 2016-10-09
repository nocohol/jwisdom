/**
 * 
 */
package com.caronic.jwisdom.service.config;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author caronic created at 2016年10月9日
 */
@JsonRootName("serviceDetails")
public class RestServiceDetails {

    private String version;
    
    public RestServiceDetails(){
        
    }
    
    public RestServiceDetails(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
}
