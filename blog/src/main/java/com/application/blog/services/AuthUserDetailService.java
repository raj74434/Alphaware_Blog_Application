package com.application.blog.services;

import com.application.blog.models.Users;
import com.application.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

        System.out.println(phone +" userdetail service" );
        Users opt= userRepo.findByPhone(phone);

        if(opt!=null) {

            Users customer= opt;

            List<GrantedAuthority> authorities= new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customer.getUserType()));


            return new org.springframework.security.core.userdetails.User(customer.getPhone(), customer.getPassword(), authorities);



        }else
            throw new BadCredentialsException("User Details not found with this phone");


    }
}
