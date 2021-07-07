package com.example.demo.stub;

import com.example.demo.enums.CarType;
import com.example.demo.model.Car;
import com.example.demo.util.MyCustomHttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CarStub {

    public static Car create(CarType carType){
        return new Car("FERRARI", "SPIDER", carType, 2021, 2022, 600000.00, 1);
    }

    public static Car basicCar(){
        return new Car("FERRARI", "SPIDER");
    }

    public static ResponseEntity carNotFound(){
        ResponseEntity expectedResponse = new ResponseEntity<>(
                new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value()
                        , "No cars found")
                , HttpStatus.NOT_FOUND);
        return expectedResponse;
    }
}
