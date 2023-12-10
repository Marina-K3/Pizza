package pizza.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.urfu.pizzaSite.RestApiPizzaApplication.security.JWTUtil;
import ru.urfu.pizzaSite.RestApiPizzaApplication.services.Client.ClientDetailService;

import java.io.IOException;
import java.util.Collections;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final ClientDetailService clientDetailService;

    @Autowired
    public JWTFilter(JWTUtil jwtUtil, ClientDetailService clientDetailService) {
        this.jwtUtil = jwtUtil;
        this.clientDetailService = clientDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);
            if (jwt.isBlank()){
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            } else {
                try {

                    String phoneNumber = jwtUtil.validateTokenAndRetrieveClaim(jwt);
                    UserDetails userDetails = clientDetailService.loadUserByUsername(phoneNumber);
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), Collections.emptyList());
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                } catch (JWTVerificationException e){
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
