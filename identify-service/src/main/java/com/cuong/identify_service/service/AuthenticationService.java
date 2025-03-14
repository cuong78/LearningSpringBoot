package com.cuong.identify_service.service;

import com.cuong.identify_service.dto.request.AuthenticationRequest;
import com.cuong.identify_service.dto.request.IntrospectRequest;
import com.cuong.identify_service.dto.response.AuthenticationResponse;
import com.cuong.identify_service.dto.response.Introspectresponse;
import com.cuong.identify_service.entity.User;
import com.cuong.identify_service.exception.AppException;
import com.cuong.identify_service.exception.ErrorCode;
import com.cuong.identify_service.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal // ko tao contructor
    @Value("${jwt.signerKey}")
    protected  String SIGNER_KEY;

    public Introspectresponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified =  signedJWT.verify(verifier);

        return Introspectresponse.builder()
                .valid( verified && expiration.after(new Date()))
                .build();
    }
    public AuthenticationResponse authenticate (AuthenticationRequest authenticationRequest) {
        User user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(()  -> new AppException(ErrorCode.USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.USER_NOT_EXSITTED);
        }

        var token = generatetoken(authenticationRequest.getUsername());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(authenticated)
                .build();
    }
    private String generatetoken(String username) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("cuong.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli())
                )
                .claim("customClaim","Custom")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        try{
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();

        }catch (JOSEException e) {
            log.error("cannot generate token", e);
            throw new RuntimeException(e);
        }

    }
}
