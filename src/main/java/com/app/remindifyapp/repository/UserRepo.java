/**
 * Author : rasintha_j
 * Date : 5/14/2024
 * Time : 11:47 AM
 * Project Name : remindifyapp
 */

package com.app.remindifyapp.repository;

import com.app.remindifyapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail (String email);
}
