package com.jfaq.passmanager.util;

import com.jfaq.passmanager.data.entities.CredentialCategory;
import com.jfaq.passmanager.data.entities.CredentialInfo;

import java.util.Arrays;
import java.util.List;

public interface AppConstant {

    int PASS_LENGTH = 6;

    List<CredentialCategory> credentialCategory = Arrays.asList(
            new CredentialCategory(1, "Website"),
            new CredentialCategory(2, "Bank")
    );

}