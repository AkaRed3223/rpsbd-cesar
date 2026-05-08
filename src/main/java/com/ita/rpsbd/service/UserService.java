package com.ita.rpsbd.service;

import com.ita.rpsbd.dto.request.UpdateUserRequest;
import com.ita.rpsbd.model.Device;
import com.ita.rpsbd.model.User;
import com.ita.rpsbd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public Optional<User> update(UUID id, UpdateUserRequest req) {
        return userRepository.findById(id).map(existing -> {
            Device updatedDevice = existing.getDevice() != null
                    ? new Device(existing.getDevice().getId(), req.deviceImei(), req.deviceType())
                    : new Device(req.deviceImei(), req.deviceType());
            return userRepository.save(new User(id, req.name(), updatedDevice));
        });
    }

    public boolean delete(UUID id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }
}
