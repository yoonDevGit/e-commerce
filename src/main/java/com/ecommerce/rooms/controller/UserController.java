package com.ecommerce.rooms.controller;

import com.ecommerce.rooms.dto.UserDto;
import com.ecommerce.rooms.service.UserService;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getUser(@PathVariable("userId") @Valid Long userId) {
    return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> createUser(@Valid UserDto userDto) {
    try {
      userService.createUser(userDto);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable("userId") @Valid Long userId) {
    try {
      userService.deleteUser(userId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }


}
