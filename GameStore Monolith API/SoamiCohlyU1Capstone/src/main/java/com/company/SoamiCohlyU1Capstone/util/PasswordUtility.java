package com.company.SoamiCohlyU1Capstone.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtility {

    public static void main (String [] args){
        PasswordEncoder enc = new BCryptPasswordEncoder();

        //the three roles we have with passwords for authentication
        String userPass = "staff";
        String userEnc = enc.encode(userPass);
        System.out.println("UserStaffEnc =   "+userEnc);

        String userManagerPass = "manager";
        String userManagerEnc = enc.encode(userManagerPass);
        System.out.println("UserManager =   "+userManagerEnc);

        String userAdminPass = "admin";
        String userAdminEnc = enc.encode(userAdminPass);
        System.out.println("UserAdmin =    "+userAdminEnc);
    }
}
