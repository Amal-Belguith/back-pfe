package com.auth.Authentication.Auth;

import com.auth.Authentication.Config.JwtService;
import com.auth.Authentication.User.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService; // Utilisé pour générer un token
    private final EmailService emailService; // Service pour envoyer des emails

    /*public void requestPasswordReset(String email) {
        var user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Générer un token de réinitialisation de mot de passe
        String token = jwtService.generatePasswordResetToken(user);

        // Envoyer un email à l'utilisateur avec le lien de réinitialisation
        String resetLink = "http://localhost:8091/api/v1/auth/reset-password?token=" + token;
        emailService.sendEmail(email, "Password Reset Request", "Click the link to reset your password: " + resetLink);
    }

    public void resetPassword(String token, String newPassword) {
        String email = jwtService.extractUsername(token);
        var user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (jwtService.isTokenValid(token, user)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepo.save(user);
        } else {
            throw new RuntimeException("Invalid or expired token");
        }
    }*/
}

