package com.example.demo.stub;

import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;

public class CarroStub {

    public static Carro create(TipoCarro tipoCarro){
        return new Carro ("FERRARI", "SPIDER", tipoCarro);
    }
}
