package org.example.service;

import org.example.entities.Account;

public interface AccountService {
    Account queryAccountInfoByNameForResultMap(String name);
}
