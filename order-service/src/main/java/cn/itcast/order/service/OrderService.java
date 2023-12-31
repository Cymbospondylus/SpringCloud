package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import site.bzyl.clients.UserClient;
import site.bzyl.pojo.User;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Resource
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2 通过Feign发送http请求进行远程调用
        User user = userClient.getById(order.getUserId());
        // 3. 将user对象封装到order对象中
        order.setUser(user);
        // 4.返回
        return order;
    }
}
