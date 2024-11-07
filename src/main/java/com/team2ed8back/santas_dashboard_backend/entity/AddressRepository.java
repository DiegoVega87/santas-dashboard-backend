package com.team2ed8back.santas_dashboard_backend.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


    @Query(value= "SELECT a FROM Address a ORDER BY a.id DESC LIMIT 6")
    public List<Address> findLast6();

}
