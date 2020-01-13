package com.dogj.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class RabbitMqTest {
    @Autowired
    ItemService itemService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void mqTest() {
//        DogjItem item = new DogjItem();
//        item.setTitle("华为手机");
//        item.setPrice(998L);
//        item.setNum(9);
//        item.setSellPoint("牢厂251*7");
//        item.setCid(9L);
//        item.setBarcode("条形码");
//        item.setImage("图片");
//        itemService.addItem(item, "测试rabbitmq");
        String s = "15";
        System.out.println(s.getBytes());
        System.out.println(s.getBytes().toString());
        Long num = Long.parseLong(new String(s.getBytes()));
        System.out.println(num);
    }
}
