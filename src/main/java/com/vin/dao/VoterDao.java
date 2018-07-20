package com.vin.dao;

import com.vin.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterDao extends JpaRepository<Voter, Long> {
    public Voter findByPhoneAndEmail(String phone, String email);
    public Voter findByVin(String vin);
}
