package dev.oblac.quarkus.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class User {
    private String username;
    private String email;
    private String tenantId;

    public boolean isAuthenticated() {
        return username != null && email != null && tenantId != null;
    }

    public static User UNAUTHENTICATED = new User(null, null, null);
}
