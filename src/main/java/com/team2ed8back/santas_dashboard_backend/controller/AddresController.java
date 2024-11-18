package com.team2ed8back.santas_dashboard_backend.controller;

import com.team2ed8back.santas_dashboard_backend.entity.address.Address;
import com.team2ed8back.santas_dashboard_backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/addresses")
public class AddresController {

    @Autowired
    private final AddressService addressService;

    public AddresController(AddressService addressService) {
        this.addressService = addressService;
    }


//    @GetMapping
//    public List<Address> getAddresses() {
//        return List.of(
//                new Address(1L, "Regocijos", 14, "Almeria", "Andalucia", "04005", "Spain"),
//                new Address(2L, "West Dewey Ave", 154, "Wharton", "NJ", "07885", "USA")
//        );
//    }

//    @GetMapping
//    public List<Address> getAll(){
//        return addressService.getAddresses();
//    }

    @GetMapping
    public List<Address> getLast6(){
        return addressService.getLast5Addresses();
    }

    @PostMapping
    public void save(@RequestBody Address address){
        addressService.saveAddress(address);
    }

}
