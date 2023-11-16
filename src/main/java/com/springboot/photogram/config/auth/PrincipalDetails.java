package com.springboot.photogram.config.auth;

import com.springboot.photogram.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    //권한이 한개가 아닐 수 있기 때문에 Collection 타입 (ArrayList도 Collection 타입)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add((GrantedAuthority) () -> user.getRole());  //람다
        /* 람다식 안쓰면?
            collector.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return user.getRole();
                }
            });
         */
        return collector;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;

    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
