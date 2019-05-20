package com.example.serversentevents;

import org.springframework.stereotype.Service;

/**
 * Created by robson on 19/05/2019.
 */
@Service
public class TestService {
    private String msg;

    public String mensagem (String msg){
        return this.msg = msg;
    }

    public String mensagem (){
        return this.msg;
    }
}
