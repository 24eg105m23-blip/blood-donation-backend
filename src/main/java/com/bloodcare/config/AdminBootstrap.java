package com.bloodcare.config;
import com.bloodcare.model.*;
import com.bloodcare.model.Models.*;
import com.bloodcare.repository.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration public class AdminBootstrap {
 @Bean ApplicationRunner bootstrapAdmin(UserRepo users,PasswordEncoder encoder,@Value("${app.admin.email:}") String email,@Value("${app.admin.password:}") String password){return args->{if(email==null||email.isBlank()||password==null||password.length()<12)return;if(users.findByEmailIgnoreCase(email).isEmpty()){User u=new User();u.fullName="System Administrator";u.email=email.toLowerCase();u.phone="0000000000";u.passwordHash=encoder.encode(password);u.role=Role.ADMIN;users.save(u);}};}
}
