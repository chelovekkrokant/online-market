package com.github.chelovekkrokant.managerapp.repository;

import com.github.chelovekkrokant.managerapp.entity.MarketUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MarketUserRepository extends CrudRepository<MarketUser, Integer> {

    Optional<MarketUser> findByUsername(String username);
}
