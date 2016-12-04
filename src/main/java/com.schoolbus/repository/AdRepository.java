package com.schoolbus.repository;

import com.schoolbus.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by t on 2016/12/2.
 */
public interface AdRepository extends JpaRepository<Ad,Long>{
    @Query("SELECT a from Ad a order by a.id desc ")
    List<Ad> findAll();
}
