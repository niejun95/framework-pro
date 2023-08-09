package org.example.service.base;

import org.example.entities.Account;

public interface AccountService {
    Account queryAccountInfoByNameForResultMap(String name);
}
