package com.sebn.pfe.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import com.sebn.pfe.repository.RoleRepository;
import com.sebn.pfe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.sebn.pfe.message.response.JwtResponse;

import com.sebn.pfe.message.request.LoginForm;
import com.sebn.pfe.message.request.SignUpForm;
import com.sebn.pfe.message.response.ResponseMessage;
import com.sebn.pfe.model.Role;
import com.sebn.pfe.model.RoleName;
import com.sebn.pfe.model.User;
import com.sebn.pfe.security.jwt.JwtProvider;
import com.sebn.pfe.security.services.UserPrinciple;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
        
        @Autowired
	JwtProvider jwtUtils;
        
        //Logger logger = LoggerFactory.getLogger();

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
            
            Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

    String jwt = jwtUtils.generateJwtToken(authentication);

    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(Collectors.toList());


    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),userDetails.getUsername(),
            userDetails.getNom(),userDetails.getPrenom(),userDetails.getEmail(), roles,userDetails.getTelephone(),
    userDetails.getSkills(),userDetails.getCv(),userDetails.getAdresse(),userDetails.getDate_nais(),
    userDetails.getAboutme()));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getNom(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		String strRoles = signUpRequest.getRole();
		System.out.println(signUpRequest.getRole());
		Set<Role> roles = new HashSet<>();
		

		switch (strRoles) {
			case "admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(adminRole);
				break;
			case "stage":
				Role stageRole = roleRepository.findByName(RoleName.ROLE_RESP_STAGE)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(stageRole);
				break;
			case "Recru":
				Role recruRole = roleRepository.findByName(RoleName.ROLE_RESP_RECRU)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(recruRole);
				break;
			case "Even":
				Role evenRole = roleRepository.findByName(RoleName.ROLE_RESP_EVEN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(evenRole);
				break;
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
		}

		
		
		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage(strRoles), HttpStatus.OK);
	}
}