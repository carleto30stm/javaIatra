package medic.iatro.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import medic.iatro.api.domain.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private  TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         var bearerToken = request.getHeader("Authorization");
      if (bearerToken != null) {
          var token = bearerToken.replace("Bearer", "").trim();
          var subject = tokenService.getSubjet(token);
          if (subject != null){
              var user = userRepository.findByUsername(subject);
              var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
              SecurityContextHolder.getContext().setAuthentication(authentication);
          }

      }
        filterChain.doFilter(request,response);
    }

}
