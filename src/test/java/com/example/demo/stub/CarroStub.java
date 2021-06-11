package com.example.demo.stub;

import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import com.example.demo.util.MyCustomHttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CarroStub {

    public static Carro create(TipoCarro tipoCarro){
        return new Carro ("FERRARI", "SPIDER", tipoCarro);
    }

    public static Carro carroBasico(){
        return new Carro ("FERRARI", "SPIDER");
    }

    public static ResponseEntity carroNotFound(){
        ResponseEntity expectedResponse = new ResponseEntity<>(
                new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value()
                        , "Nenhum carro foi encontrado")
                , HttpStatus.NOT_FOUND);
        return expectedResponse;
    }
}
