package com.dogj.sso.service.impl;

import com.dogj.common.pojo.DogjResult;
import com.dogj.common.utils.JsonUtil;
import com.dogj.dao.DogjUserDao;
import com.dogj.jedis.JedisClient;
import com.dogj.pojo.DogjUser;
import com.dogj.pojo.DogjUserQuery;
import com.dogj.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DogjUserDao dogjUserDao;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public DogjResult checkData(String data, int type) {
        DogjUserQuery query = new DogjUserQuery();
        DogjUserQuery.Criteria criteria = query.createCriteria();
        if (type == 1) {
            criteria.andUsernameEqualTo(data);
        } else if (type == 2) {
            criteria.andPhoneEqualTo(data);
        } else if (type == 3) {
            criteria.andEmailEqualTo(data);
        } else {
            return DogjResult.build(400, "参数包含非法数据");
        }
        List<DogjUser> list = dogjUserDao.selectByExample(query);
        if (list != null && list.size() > 0) {
            return DogjResult.ok(false);
        }
        return DogjResult.ok(true);
    }

    @Override
    public DogjResult register(DogjUser user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return DogjResult.build(400, "用户名不能为空");
        }
        DogjResult dogjResult = checkData(user.getUsername(), 1);
        if (!(boolean)dogjResult.getData()) {
            return dogjResult.build(400, "用户名重复");
        }
        //密码和电话号码类似
        user.setCreated(new Date());
        user.setUpdated(new Date());
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        dogjUserDao.insert(user);
        return DogjResult.ok();
    }

    @Override
    public DogjResult login(String username, String password) {
        DogjUserQuery query = new DogjUserQuery();
        DogjUserQuery.Criteria criteria = query.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<DogjUser> list = dogjUserDao.selectByExample(query);
        if (list == null || list.size() == 0) {
            return DogjResult.build(400, "用户名不存在");
        }
        DogjUser user = list.get(0);
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return DogjResult.build(400, "密码不正确");
        }
        String token = UUID.randomUUID().toString();
        user.setPassword(null);
        jedisClient.set("USER_SESSION:" + token, JsonUtil.objectToJson(user));
        jedisClient.expire("USER_SESSION:" + token, 2000);
        return DogjResult.ok(token);
    }

    @Override
    public DogjResult getUserByToken(String token) {
        String json = jedisClient.get("USER_SESSION:" + token);
        if (StringUtils.isBlank(json)) {
            return DogjResult.build(400, "用户登录已过期");
        }
        jedisClient.expire("USER_SESSION:" + token, 2000);
        DogjUser user = JsonUtil.jsonToPojo(json, DogjUser.class);
        return DogjResult.ok(user);
    }
}
