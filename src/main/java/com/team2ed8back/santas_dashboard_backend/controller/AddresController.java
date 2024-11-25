package com.team2ed8back.santas_dashboard_backend.controller;

import com.team2ed8back.santas_dashboard_backend.entity.address.Address;
import com.team2ed8back.santas_dashboard_backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // This means that the controller will accept requests from any origin
@RestController
@RequestMapping(path = "api/v1/addresses")
public class AddresController {

    @Autowired
    private final AddressService addressService;

    public AddresController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getLast5(){
        return addressService.getLast5Addresses();
    }

    @PostMapping
    public void save(@RequestBody Address address){
        addressService.saveAddress(address);
    }

}
