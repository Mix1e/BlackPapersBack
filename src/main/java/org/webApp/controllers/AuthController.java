package org.webApp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webApp.enums.Role;
import org.webApp.models.Viewer;
import org.webApp.repos.ViewerRepo;
import org.webApp.security.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="http://localhost:4200")
@AllArgsConstructor
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ViewerRepo viewerRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        ViewerDetailsImpl viewerDetails = (ViewerDetailsImpl) authentication.getPrincipal();
        String role = viewerDetails.getAuthorities().toString();


        return ResponseEntity.ok(new JwtResponse(jwt,
                viewerDetails.getUsername(),
                viewerDetails.getDescription(), role
        ));
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {

        if (viewerRepo.existsViewerByNickName(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Данный псевдоним недоступен!"));
        }

        Viewer viewer = new Viewer();
        viewer.setNickName(signupRequest.getUsername());
        viewer.setDescription(signupRequest.getDescription());
        viewer.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        String reqRoles = signupRequest.getRole();
        Role role;
        if (reqRoles != null)
            role = reqRoles.toUpperCase() == "USER"? Role.USER : Role.ADMIN;
        else role = Role.USER;
        viewer.setRole(role);

        viewerRepo.save(viewer);
        return ResponseEntity.ok(new MessageResponse("Регистрация прошла успешно!"));
    }
}
