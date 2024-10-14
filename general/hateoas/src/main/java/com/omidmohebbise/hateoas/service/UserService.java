package com.omidmohebbise.hateoas.service;

import com.omidmohebbise.hateoas.model.UserEO;
import com.omidmohebbise.hateoas.repository.UsersJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements CommandLineRunner {

    private final UsersJpaRepository repository;

    public UserService(UsersJpaRepository repository) {
        this.repository = repository;
    }

    public List<UserEO> findAll(){
        return repository.findAll();
    }
    public UserEO findById(int id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("The user not found"));
    }
    public void delete(int id){
        repository.deleteById(id);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new UserEO("Omid Mohebbi"));
        repository.save(new UserEO("Taraneh Khalaili"));
        repository.save(new UserEO("Sara Ahmadi"));
        repository.save(new UserEO("Ali Saedghlo"));
    }
}
