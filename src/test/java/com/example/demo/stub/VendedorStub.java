package com.example.demo.stub;

import com.example.demo.model.Vendedor;
import com.example.demo.util.MyCustomHttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class VendedorStub {

    public static Vendedor create() {
        return new Vendedor("João da Silva", "99999", "22483611060", "vendedor@hypeflame.com.br");
    }

    public static ResponseEntity vendedorNotFound(){
        ResponseEntity expectedResponse = new ResponseEntity<>(
                new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value()
                        , "Nenhum vendedor foi encontrado.")
                , HttpStatus.NOT_FOUND);
        return expectedResponse;
    }
}
