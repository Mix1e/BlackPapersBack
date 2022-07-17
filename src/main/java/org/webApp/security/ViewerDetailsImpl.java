package org.webApp.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.webApp.models.Viewer;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ViewerDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nickName;
    @JsonIgnore
    private String password;
    private String description;

    private Collection<? extends GrantedAuthority> authorities;

    public ViewerDetailsImpl(String nickName, String password, String description, Collection<? extends GrantedAuthority> authorities) {
        this.nickName = nickName;
        this.password = password;
        this.description = description;
        this.authorities = authorities;
    }

    public static ViewerDetailsImpl build(Viewer viewer) {
        List<GrantedAuthority> authorities = List.of(viewer.getRole()).stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        return new ViewerDetailsImpl(
                viewer.getNickName(),
                viewer.getPassword(),
                viewer.getDescription(), (Collection<? extends GrantedAuthority>) authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nickName;
    }

    public String getDescription() {
        return description;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ViewerDetailsImpl other = (ViewerDetailsImpl) obj;
        if (nickName == null) {
            if (other.nickName != null)
                return false;
        } else if (!nickName.equals(other.nickName))
            return false;
        return true;
    }
}
