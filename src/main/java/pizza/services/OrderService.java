package pizza.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pizza.models.Order;
import pizza.models.User;
import pizza.repositories.OrderRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getByUser(User user){
        return orderRepository.findByUser(user);
    }
}
