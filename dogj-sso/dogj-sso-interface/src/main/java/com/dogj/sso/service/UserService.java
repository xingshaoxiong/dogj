package com.dogj.sso.service;

import com.dogj.common.pojo.DogjResult;
import com.dogj.pojo.DogjUser;

public interface UserService {
    DogjResult checkData(String data, int type);
    DogjResult register(DogjUser user);
    DogjResult login(String username, String password);
    DogjResult getUserByToken(String token);
}
