package com.example.demo.stub;

import com.example.demo.model.Vendor;
import com.example.demo.util.MyCustomHttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class VendorStub {

    public static Vendor create() {
        return new Vendor("João da Silva", "99999", "22483611060", "vendedor@hypeflame.com.br");
    }

    public static ResponseEntity vendorNotFound(){
        ResponseEntity expectedResponse = new ResponseEntity<>(
                new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value()
                        , "No vendors found")
                , HttpStatus.NOT_FOUND);
        return expectedResponse;
    }
}
