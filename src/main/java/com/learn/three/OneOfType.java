package com.learn.three;

import com.learn.grpc.three.Credential;
import com.learn.grpc.three.Email;
import com.learn.grpc.three.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OneOfType {
    private static final Logger log = LoggerFactory.getLogger(OneOfType.class);


    public static void main(String[] args) {
        var phone = Phone.newBuilder().setNumber(4567890).setCode(1234).build();
        var email = Email.newBuilder().setAddress("sim@gmail.com").setPassword("simson").build();
        var credential = Credential.newBuilder().setEmail(email).build();
//        login(credential);
        login(Credential.newBuilder().setEmail(email).setPhone(phone).build()); //  on assigning both, last assigned will be consider(taken)
    }

    public static void login(Credential credential){
        switch (credential.getTypeCase()){
            case EMAIL -> log.info("Email : {}",credential.getEmail());
            case PHONE -> log.info("Phone: {}",credential.getPhone());
        }
    }
}
