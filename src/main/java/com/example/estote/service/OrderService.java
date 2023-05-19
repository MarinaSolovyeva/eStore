package com.example.estote.service;

import com.example.estote.entity.*;
import com.example.estote.entityDetail.OrderDetail;
import com.example.estote.entityDetail.OrderIdComparator;
import com.example.estote.repositories.GoodRepository;
import com.example.estote.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final GoodRepository goodRepository;
    private final GeneralService generalService;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, UserService userService,
                        GoodRepository goodRepository, GeneralService generalService, CartService cartService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.goodRepository = goodRepository;
        this.generalService = generalService;
        this.cartService = cartService;
    }

    //создание заказа, добавление товаров
    @Transactional
    public void saveOrder(Order order) {
        List<Good> goodList = getCollectRefGoodById(order.getGoodIDs());
        order.setGoods(goodList);
        order.setDateOfCreation(generalService.getCurrentDate());
        orderRepository.save(order);
    }

    @Transactional
    public Order getOrder (Long id) {
        Optional <Order> foundOrder = orderRepository.findById(id);
        return foundOrder.orElse(null);
    }

    //get orders by user
    public List<Order> getAllOrdersByUser(String name) {
        User user = userService.findByName(name);
        List<Order> orderList = orderRepository.findAllOrdersByUserId(user);

        Collections.sort(orderList, new OrderIdComparator());
        Collections.reverse(orderList);

        return orderList;}



    //get references on goods
    private List<Good> getCollectRefGoodById(List<Long> goodIds) {
        return goodIds
                .stream()
                .map(goodRepository::getOne)
                .collect(Collectors.toList()); }

    @Transactional
    public void addToUserOrder(String username, Order order) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("User not found");}
        order.setUserId(user);
        saveOrder(order);
        cartService.clearCart(user.getCart());
    }

    //get IDs for goods in order
    public List<Long> getGoodID(Order order) {
        List<Good> goods = order.getGoods();
        List<Long> goodIds = new ArrayList<>();
        for (Good good : goods) {
            goodIds.add(good.getId());
        }
        return goodIds;
    }

    //copy details to new order
    public Order copyAllDetailsToOrder(Order newOrder, Order oldOrder, Address address) {
        setDeliveryDetails(newOrder, oldOrder, address);
        oldOrder = null;
        newOrder.getOrderDetails().clear();

        newOrder.setSumOrder(newOrder.getSum());
        newOrder.setGoodIDs(getGoodID(newOrder));
        return newOrder;
    }

    public Order setDeliveryDetails(Order newOrder, Order oldOrder, Address address) {
        newOrder.setCommentForOrder(oldOrder.getCommentForOrder());
        newOrder.setPhoneOrder(oldOrder.getPhoneOrder());
        newOrder.setAddress(address);
        return newOrder;
    }

    @Transactional
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //aggregate info for order view
    public Order getOrderByUser(String name) {
        User user = userService.findByName(name);
        Order order = new Order();
        Map<Long, OrderDetail> mapByGoodId = new HashMap<>();
        List<Good> goodsOrder = user.getCart().getGoods();
        for (Good good : goodsOrder) {
            OrderDetail detail = mapByGoodId.get(good.getId());
            if (detail == null) {
                mapByGoodId.put(good.getId(), new OrderDetail(good));
            } else {
                detail.setAmount(detail.getAmount().add(new BigDecimal(1.0)));
                Integer price = good.getCostBeforeSale() * good.getSale() / 100;
                detail.setSum(detail.getSum() + Double.valueOf(price.toString()));
                order.setSum(detail.getSum());
            }
        }
        order.setOrderDetails(new ArrayList<>(mapByGoodId.values()));
        order.aggregate();

        order.setGoods(goodsOrder);
        return order;
    }
}

