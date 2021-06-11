package com.exam.pavk55.web;

import com.exam.pavk55.models.binding.UserLoginBindingModel;
import com.exam.pavk55.models.binding.UserRegisterBindingModel;
import com.exam.pavk55.models.entities.User;
import com.exam.pavk55.models.service.UserServiceModel;
import com.exam.pavk55.services.service_impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerAdd(@Valid UserRegisterBindingModel userRegisterBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);
            return "redirect:register";
        }
        if (!passwordMatching(userRegisterBindingModel)) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);
            return "redirect:register";
        }
        userService
                .addUser(modelMapper
                        .map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:/users/login";
    }

    private boolean passwordMatching(UserRegisterBindingModel userRegisterBindingModel) {
        return userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        model.addAttribute("validLogin", false);
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession,
                               Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                    bindingResult);
            model.addAttribute("validLogin", true);
            return "redirect:login";
        }

        Optional<User> userExist = userService
                .findUserAtLogon(userLoginBindingModel);

        if (userExist.isEmpty()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                    bindingResult);
            model.addAttribute("validLogin", true);
            return "redirect:login";
        }

        httpSession.setAttribute("user", userExist.get());

        return "redirect:/home";
    }


    @GetMapping("/logout")
    public String index(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }


}
