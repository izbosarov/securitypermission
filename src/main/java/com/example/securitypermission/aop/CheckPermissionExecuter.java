package com.example.securitypermission.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.example.securitypermission.entity.User;

@Component
@Aspect
public class CheckPermissionExecuter {

    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethon(CheckPermission checkPermission) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.value())) {
                exist = true;
                break;
            }
        }

        if (!exist)

            throw new UsernameNotFoundException("Xatolik!");
    }
}
