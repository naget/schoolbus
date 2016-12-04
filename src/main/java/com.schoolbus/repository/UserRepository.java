package com.schoolbus.repository;

import com.schoolbus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by t on 2016/11/23.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByName(String name);
    User findByPhoneNumber(String phoneNumber);

    @Modifying
    @Transactional
    @Query("update User u set u.status=1 where u=?1")
    int setStatus1(User user);

    @Modifying
    @Transactional
    @Query("update User u set u.status=0 where u=?1")
    int setStatus0(User user);
}
