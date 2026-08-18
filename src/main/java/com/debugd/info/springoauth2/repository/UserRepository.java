package com.debugd.info.springoauth2.repository;

import com.debugd.info.springoauth2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByProviderAndProviderSubject(String provider, String providerSubject);
}
