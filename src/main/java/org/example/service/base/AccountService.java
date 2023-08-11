package org.example.service.base;

import org.example.entity.Account;

public interface AccountService {
    Account queryAccountInfoByNameForResultMap(String name);
}
