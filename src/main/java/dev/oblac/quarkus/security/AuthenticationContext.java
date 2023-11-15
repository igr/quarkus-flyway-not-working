package dev.oblac.quarkus.security;

public interface AuthenticationContext {

    User getUser();

    String getTenantId();

}
