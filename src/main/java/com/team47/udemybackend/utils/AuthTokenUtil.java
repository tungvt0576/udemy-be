package com.team47.udemybackend.utils;

import com.team47.udemybackend.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AuthTokenUtil {
    private AuthTokenUtil() {
    }
    public static int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
                User user = (User) authentication.getPrincipal();
                return user.getId();
        }
        return 0;
    }
}
