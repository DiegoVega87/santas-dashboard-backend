package com.team2ed8back.santas_dashboard_backend.controller;

import com.team2ed8back.santas_dashboard_backend.entity.address.Address;
import com.team2ed8back.santas_dashboard_backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // This means that the controller will accept requests from any origin
@RestController
@RequestMapping(path = "api/v1/addresses")
public class AddressController {

    @Autowired
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getLast5(){
        return addressService.getLast5Addresses();
    }

    @PostMapping
    public Address save(@RequestBody Address address){
        return addressService.saveAddress(address);
    }

}
