package com.exam.pavk55.web;

import com.exam.pavk55.models.binding.OrderAddBindingModel;
import com.exam.pavk55.models.service.OrderServiceModel;
import com.exam.pavk55.repos.OrderRepo;
import com.exam.pavk55.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService,
                           ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(HttpSession httpSession,
                      Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
        if (!model.containsAttribute("orderAddBindingModel")) {
            model.addAttribute("orderAddBindingModel", new OrderAddBindingModel());
        }
        return "order-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid OrderAddBindingModel orderAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel",
                    bindingResult);
            return "redirect:add";
        }

        orderService.
                addOrder(modelMapper.
                        map(orderAddBindingModel, OrderServiceModel.class));

        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        orderService.removeOrder(id);
        return "redirect:/home";
    }



}
