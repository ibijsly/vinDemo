package com.vin.service;

import com.vin.dao.VoterDao;
import com.vin.model.ResponseModel;
import com.vin.model.Voter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class VoterService {
    private final VoterDao voterDao;

    public VoterService(VoterDao voterDao) {
        this.voterDao = voterDao;
    }

    public ResponseEntity<ResponseModel> addUser(Voter voter){
        try {
            voter.setPhoto(Base64.getDecoder().decode(voter.getPix().getBytes("UTF-8")));
            voterDao.save(voter);
            return new ResponseEntity<>(new ResponseModel("00", "Success", voter), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseModel("99", "Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ResponseModel> findByPhoneAndEmail(String phone, String email){
        try {
            Voter voter = voterDao.findByPhoneAndEmail(phone, email);
            if (voter != null)
                return new ResponseEntity<ResponseModel>(new ResponseModel("00", "Success", voter), HttpStatus.OK);
            return new ResponseEntity<ResponseModel>(new ResponseModel("01", "Not Found", voter), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return null;
        }
    }

    public Voter findByVin(String vin){
        return voterDao.findByVin(vin);
    }

    public void shoutAndReturnNothing(){
        System.out.println("I am really shouting !!!");
    }
}
