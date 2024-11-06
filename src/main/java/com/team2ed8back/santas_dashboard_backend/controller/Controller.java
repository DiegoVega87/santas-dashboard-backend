package com.team2ed8back.santas_dashboard_backend.controller;

import com.team2ed8back.santas_dashboard_backend.entity.Address;
import com.team2ed8back.santas_dashboard_backend.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/addresses")
public class Controller {

    @Autowired
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }


//    @GetMapping
//    public List<Address> getAddresses() {
//        return List.of(
//                new Address(1L, "Regocijos", 14, "Almeria", "Andalucia", "04005", "Spain"),
//                new Address(2L, "West Dewey Ave", 154, "Wharton", "NJ", "07885", "USA")
//        );
//    }

    @GetMapping
    public List<Address> getAll(){
        return service.getAddresses();
    }

    @PostMapping
    public void save(@RequestBody Address address){
        service.saveAddress(address);
    }

}
