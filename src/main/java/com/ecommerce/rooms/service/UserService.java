package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.User;
import com.ecommerce.rooms.dto.UserDto;
import com.ecommerce.rooms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDto getUser(Long userId) {
    User user = userRepository.findById(userId).orElseThrow();

    UserDto userDto = UserDto.builder()
        .account(user.getAccount())
        .email(user.getEmail())
        .phone(user.getPhone())
        .build();

    return userDto;
  }

  public void createUser(UserDto userDto) {
    User user = User.builder()
        .account(userDto.getAccount())
        .email(userDto.getEmail())
        .phone(userDto.getPhone())
        .build();

    userRepository.save(user);
  }

  public void deleteUser(Long userId) {
    User user = userRepository.findById(userId).orElseThrow();

    userRepository.delete(user);
  }



}
