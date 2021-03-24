package com.ecommerce.rooms.controller;

import com.ecommerce.rooms.dto.UserDto;
import com.ecommerce.rooms.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/users")
  public String list(Model model) {
    List<UserDto> users = userService.findUsers();
    model.addAttribute("users", users);
    return "/users/listUser";
  }

  @GetMapping("/user/new")
  public String createForm(Model model) {
    model.addAttribute("userForm", new UserDto());
    return "users/createUserForm";
  }

  @PostMapping("/user/new")
  public String create(@Valid UserDto userDto, BindingResult result) {

    if (result.hasErrors()) {
      return "users/createUserForm";
    }
    userService.createUser(userDto);
    return "redirect:/";
  }

}
