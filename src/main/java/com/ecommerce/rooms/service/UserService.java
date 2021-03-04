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
    return new UserDto(user);
  }

  public void createUser(UserDto userDto) {
    User user = new User(userDto);
    userRepository.save(user);
  }

  public void deleteUser(Long userId) {
    User user = userRepository.findById(userId).orElseThrow();
    userRepository.delete(user);
  }



}
