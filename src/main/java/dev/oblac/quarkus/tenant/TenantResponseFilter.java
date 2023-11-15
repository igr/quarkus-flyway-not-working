package dev.oblac.quarkus.tenant;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TenantResponseFilter implements ContainerResponseFilter {
        @Override
        public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) {
            TenantContext.clear();
        }
}
