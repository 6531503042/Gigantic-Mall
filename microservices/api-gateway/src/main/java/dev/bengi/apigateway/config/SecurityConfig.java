//package dev.bengi.apigateway.config;
//
//import jdk.jfr.Enabled;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
//        serverHttpSecurity
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchange ->
//                        exchange.pathMatchers("/eureka/**")
//                                .permitAll()
//                                .anyExchange()
//                                .authenticated())
//                .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));
//        return serverHttpSecurity.build();
//    }
//}
