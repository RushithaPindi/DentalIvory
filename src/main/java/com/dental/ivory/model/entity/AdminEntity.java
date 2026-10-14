package com.dental.ivory.model.entity;

import com.dental.ivory.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="admin_rigts")
@Data
@NoArgsConstructor
public class AdminEntity extends BaseEntity {
   @Column(name = "user_name")
    private String userName;
   @Column(name="pasword")
    private String password;
   @Column(name="email")
    private String email;
}
