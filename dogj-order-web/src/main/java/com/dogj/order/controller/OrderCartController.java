package com.dogj.order.controller;

import com.dogj.common.pojo.DogjResult;
import com.dogj.common.utils.CookieUtils;
import com.dogj.common.utils.JsonUtil;
import com.dogj.order.pojo.OrderInfo;
import com.dogj.order.service.OrderService;
import com.dogj.pojo.DogjItem;
import com.dogj.pojo.DogjUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderCartController {

    @Value("${CART_KEY}")
    private String CART_KEY;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request) {
        DogjUser user = (DogjUser) request.getAttribute("user");
        System.out.println(user.getUsername());
        List<DogjItem> cartList = getCartItemList(request);
        request.setAttribute("cartList", cartList);
        return "order-cart";
    }

    private List<DogjItem> getCartItemList(HttpServletRequest request) {
        String json = CookieUtils.getCookieValue(request, CART_KEY, true);
        if (StringUtils.isBlank(json)) {
            return new ArrayList<>();
        }
        List<DogjItem> list = JsonUtil.jsonToList(json, DogjItem.class);
        return list;
    }

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public DogjResult createOrder(OrderInfo orderInfo) {
        DogjResult result = orderService.createOrder(orderInfo);
        return result;
    }
}
