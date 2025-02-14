package com.example.twitterclone.service;

import com.example.twitterclone.model.Role;
import com.example.twitterclone.model.User;
import com.example.twitterclone.repository.UserRepository;
import com.example.twitterclone.repository.RoleRepository; // Для работы с ролями
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository; // Использование репозитория для получения ролей

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User saveUser(User user) {
        // Получаем роль по имени из базы данных
        Role defaultRole = roleRepository.findByName("ROLE_USER") // Можно сделать поиск роли по имени
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        // Присваиваем роль пользователю
        user.setRoles(Set.of(defaultRole));

        // Хешируем пароль перед сохранением
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
