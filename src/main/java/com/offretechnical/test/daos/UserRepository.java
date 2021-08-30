package com.offretechnical.test.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offretechnical.test.models.User;
/**
 * User Repository
 * 
 * @author bn
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
