package org.openshift.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.openshift.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
