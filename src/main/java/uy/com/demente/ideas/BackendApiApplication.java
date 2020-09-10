package uy.com.demente.ideas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendApiApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BackendApiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApiApplication.class, args);
    }


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Solamente a modo de ejemplo, se encriptan passwords
        String passwordAdmin = "admin";
        String passwordUser = "user";

        String bCryptPassword = passwordEncoder.encode(passwordAdmin);
        System.out.println("BCryptPasswordEncoder - Clave encriptada para ADMIN: " + bCryptPassword);

        bCryptPassword = passwordEncoder.encode(passwordUser);
        System.out.println("BCryptPasswordEncoder - Clave encriptada para USER: " + bCryptPassword);

        System.out.printf("-----------------------------------------");
        System.out.printf("--- INIT SpringSecurityJwtApplication ---");
        System.out.printf("-----------------------------------------");
    }
}
