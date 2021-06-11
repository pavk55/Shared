package com.exam.pavk55.web;

import com.exam.pavk55.configs.ComparatorMap;
import com.exam.pavk55.models.entities.Category;
import com.exam.pavk55.models.entities.Order;
import com.exam.pavk55.models.entities.User;
import com.exam.pavk55.models.enums.CategoryEnum;
import com.exam.pavk55.repos.OrderRepo;
import com.exam.pavk55.services.CategoryService;
import com.exam.pavk55.services.OrderService;
import com.exam.pavk55.services.service_impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final OrderService orderService;
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public HomeController(OrderService orderService,
                          CategoryService categoryService, UserService userService) {
        this.orderService = orderService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(HttpSession httpSession,
                       Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
//        all ordertimes
        Integer orderTimes = orderService.AllOrderTimes();
        model.addAttribute("timeToPrepare", orderTimes);
//        listed orders

        Category cake = categoryService.findByEnum(CategoryEnum.CAKE);
        Category coffee = categoryService.findByEnum(CategoryEnum.COFFEE);
        Category drink = categoryService.findByEnum(CategoryEnum.DRINK);
        Category other = categoryService.findByEnum(CategoryEnum.OTHER);

        List<Order> listedCakes = orderService.getAllOrders(cake);
        List<Order> listedCoffees = orderService.getAllOrders(coffee);
        List<Order> listedDrinks = orderService.getAllOrders(drink);
        List<Order> listedOthers = orderService.getAllOrders(other);

        model.addAttribute("allCakes", listedCakes);
        model.addAttribute("allCoffees", listedCoffees);
        model.addAttribute("allDrinks", listedDrinks);
        model.addAttribute("allOthers", listedOthers);

//        Map of orders per user
        Map<String, Integer> ordersPerUser = new LinkedHashMap<>();
        List<User> userList = userService.getAllUsers();

        userList.forEach(user -> {
            Integer userOrderCount = userService.countOrders(user);
            ordersPerUser.put(user.getFirstName(), userOrderCount);
        });

        LinkedHashMap<String, Integer> collect = ordersPerUser
                .entrySet()
                .stream()
                .sorted(new ComparatorMap())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        model.addAttribute("usersOrders", collect);

        return "home";
    }

}
