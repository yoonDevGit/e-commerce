package com.ecommerce.rooms.api.v1;

import com.ecommerce.rooms.dto.MailDto;
import com.ecommerce.rooms.dto.UserDto;
import com.ecommerce.rooms.service.MailService;
import com.ecommerce.rooms.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Tag;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Api(value = "UserApiController v1")
public class UserApiController {

  private final UserService userService;
  private final MailService mailService;

  @GetMapping("/{userId}")
  @ApiOperation(value="사용자 상세")
  public ResponseEntity<UserDto> getUser(@PathVariable("userId") @Valid Long userId) {
    return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
  }

  @GetMapping("/page")
  @ResponseStatus
  @ApiOperation(value="사용자 목록 페이지")
  public Page<UserDto> getPageAll(
      @PageableDefault(size = 5, sort = "username") Pageable pageable) {
    return userService.getPageAll(pageable);
  }

  @GetMapping("/slice")
  @ApiOperation(value="사용자 목록 슬라이스")
  public Slice<UserDto> getSliceAll(@PageableDefault(size = 5, sort = "username") Pageable pageable) {
    return userService.getSliceAll(pageable);
  }

  @PostMapping
  @ApiOperation(value="사용자 생성")
  public ResponseEntity<Void> createUser(@RequestBody @Valid UserDto userDto) {
    try {
      userService.createUser(userDto);
      MailDto mailDto = new MailDto(userDto.getEmail(), "Test Mail", "User create success");
      mailService.sendMail(mailDto);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/{userId}")
  @ApiOperation(value="사용자 삭제")
  public ResponseEntity<Void> deleteUser(@PathVariable("userId") @Valid Long userId) {
    try {
      userService.deleteUser(userId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }


}
