package org.apple.db.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import org.apple.db.BaseEntity;

@Entity
public class UserEntity extends BaseEntity {

    @Column
    private String name;
    @Column
    private String email;
    @Embedded
    private Address address;
}
