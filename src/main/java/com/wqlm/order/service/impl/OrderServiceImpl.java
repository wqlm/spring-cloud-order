package com.wqlm.order.service.impl;


import com.wqlm.order.dataobject.OrderMaster;
import com.wqlm.order.dto.OrderDTO;
import com.wqlm.order.enums.OrderStatusEnum;
import com.wqlm.order.enums.PayStatusEnum;
import com.wqlm.order.repository.OrderDetailRepository;
import com.wqlm.order.repository.OrderMasterRepository;
import com.wqlm.order.service.OrderService;
import com.wqlm.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

       //TODO 查询商品信息(调用商品服务)
       //TODO 计算总价
       //TODO 扣库存(调用商品服务)

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
