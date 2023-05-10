package org.hobbit.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.hobbit.core.jwt.JwtUtil;
import org.hobbit.core.test.HobbitBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@HobbitBootTest(enableLoader = true)
@SpringBootTest(classes = AuthApplication.class)
class AuthApplicationTests {

  @Test
  void contextLoads() {
  }

  @Test
  void jwt() {

    Map<String, Object> claims = new HashMap<>();
    claims.put("username", "admin");
    claims.put("password", "010203");
    JwtBuilder jwtBuilder = Jwts.builder()
        .setClaims(claims)
        .setId(UUID.randomUUID().toString())
        .setIssuedAt(new Date())
        .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(JwtUtil.getBase64Security())));

    Claims claims1 = JwtUtil.parseJWT(jwtBuilder.compact());
    System.out.println(claims1);
  }
}
