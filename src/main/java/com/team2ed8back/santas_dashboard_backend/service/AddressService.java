package com.team2ed8back.santas_dashboard_backend.service;

import com.team2ed8back.santas_dashboard_backend.entity.Address;
import com.team2ed8back.santas_dashboard_backend.entity.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class AddressService {

    @Autowired
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getLast5Addresses() {
        return this.addressRepository.findLast5();
    }

    public void saveAddress(Address address) {
        this.addressRepository.save(address);
    }


}
