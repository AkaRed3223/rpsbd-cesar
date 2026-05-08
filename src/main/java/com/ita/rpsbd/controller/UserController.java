package com.ita.rpsbd.controller;

import com.ita.rpsbd.dto.request.CreateUserRequest;
import com.ita.rpsbd.dto.request.UpdateUserRequest;
import com.ita.rpsbd.dto.response.PagedResponse;
import com.ita.rpsbd.dto.response.UserResponse;
import com.ita.rpsbd.model.Device;
import com.ita.rpsbd.model.User;
import com.ita.rpsbd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<PagedResponse<UserResponse>> getAll(Pageable pageable) {
        Page<User> page = userService.findAll(pageable);
        return ResponseEntity.ok(new PagedResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent().stream().map(UserResponse::new).toList()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(UserResponse::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody CreateUserRequest req) {
        User user = new User(req.name(), new Device(req.deviceImei(), req.deviceType()));
        return ResponseEntity
                .status(201)
                .body(new UserResponse(userService.create(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id, @RequestBody UpdateUserRequest req) {
        return userService.update(id, req)
                .map(UserResponse::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return userService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
