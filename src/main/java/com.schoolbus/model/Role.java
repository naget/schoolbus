package com.schoolbus.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by t on 2016/11/23.
 */
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue
    Long id;
    String name;
//    @ManyToMany(mappedBy = "roles")
//    //role是关系被维护端
//    @LazyCollection(LazyCollectionOption.TRUE)
//    @JsonIgnore
//    private List<User> users = new ArrayList<User>();

}
