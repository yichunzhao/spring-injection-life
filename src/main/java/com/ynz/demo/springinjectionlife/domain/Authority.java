package com.ynz.demo.springinjectionlife.domain;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Authorities")
@NoArgsConstructor
@Setter
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    private String authority;

    @ManyToOne
    @JoinColumn
    private SecurityUser user;

    @Override
    public String getAuthority() {
        return this.authority;
    }

}
