package com.bloodcare.config;

import com.bloodcare.model.*;
import com.bloodcare.model.Models.*;
import com.bloodcare.repository.DonorRepo;
import com.bloodcare.repository.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.SecureRandom;
import java.util.HexFormat;

@Configuration
public class SampleDataBootstrap {
    private record Sample(String name, String email, String city, String state, BloodGroup group, int age, String gender, String phone) {}

    @Bean ApplicationRunner seedSampleDonors(UserRepo users, DonorRepo donors, PasswordEncoder encoder,
                                              @Value("${app.sample-data.enabled:false}") boolean enabled) {
        return args -> {
            if (!enabled) return;
            Sample[] samples = {
                new Sample("Aarav Demo", "donor.a-positive@example.test", "Jaipur", "Rajasthan", BloodGroup.A_POS, 27, "Male", "+91 555-0101"),
                new Sample("Mira Demo", "donor.a-negative@example.test", "Pune", "Maharashtra", BloodGroup.A_NEG, 31, "Female", "+91 555-0102"),
                new Sample("Kabir Demo", "donor.b-positive@example.test", "Delhi", "Delhi", BloodGroup.B_POS, 25, "Male", "+91 555-0103"),
                new Sample("Isha Demo", "donor.b-negative@example.test", "Kochi", "Kerala", BloodGroup.B_NEG, 29, "Female", "+91 555-0104"),
                new Sample("Reyansh Demo", "donor.ab-positive@example.test", "Mumbai", "Maharashtra", BloodGroup.AB_POS, 33, "Male", "+91 555-0105"),
                new Sample("Anaya Demo", "donor.ab-negative@example.test", "Chennai", "Tamil Nadu", BloodGroup.AB_NEG, 26, "Female", "+91 555-0106"),
                new Sample("Vivaan Demo", "donor.o-positive@example.test", "Bengaluru", "Karnataka", BloodGroup.O_POS, 30, "Male", "+91 555-0107"),
                new Sample("Diya Demo", "donor.o-negative@example.test", "Hyderabad", "Telangana", BloodGroup.O_NEG, 28, "Female", "+91 555-0108")
            };
            SecureRandom random = new SecureRandom();
            for (Sample sample : samples) {
                if (users.findByEmailIgnoreCase(sample.email()).isPresent()) continue;
                byte[] secret = new byte[32]; random.nextBytes(secret);
                User user = new User();
                user.fullName = sample.name(); user.email = sample.email(); user.phone = sample.phone();
                user.passwordHash = encoder.encode(HexFormat.of().formatHex(secret)); user.role = Role.DONOR;
                users.save(user);
                Donor donor = new Donor(); donor.user = user; donor.age = sample.age(); donor.gender = sample.gender();
                donor.bloodGroup = sample.group(); donor.city = sample.city(); donor.state = sample.state();
                donor.address = "Demo address"; donor.available = true;
                donors.save(donor);
            }
        };
    }
}
