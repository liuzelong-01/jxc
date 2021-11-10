package org.example.admin.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.example.admin.model.KaptchaImageModel;
import org.example.admin.model.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Component
public class KaptchaCodeFilter extends OncePerRequestFilter {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        // 只有在登录请求时才有验证码校验
        if(StringUtils.equals("/login",request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(),"post")){
            try{
                //验证谜底与用户输入是否匹配
                this.validate(new ServletWebRequest(request));
            }catch (AuthenticationException e){
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(
                        RespBean.error(e.getMessage())));
                //catch异常后,之后的过滤器就不再执行了
                return;
            }

        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {

        HttpSession session = servletWebRequest.getRequest().getSession();
        String codeInRequestion= ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"kaptchaCode");
        if (StringUtils.isEmpty(codeInRequestion)){
            throw new SessionAuthenticationException("验证码不能为空");
        }

        KaptchaImageModel kaptchaInSession = (KaptchaImageModel) session.getAttribute("kaptcha_key");
        if (Objects.isNull(kaptchaInSession)){
            throw new SessionAuthenticationException("验证码不存在");
        }

        if (kaptchaInSession.isExpired()){
            throw new SessionAuthenticationException("验证码已过期");
        }

        if (!StringUtils.equals(kaptchaInSession.getCode(),codeInRequestion)){
         throw new SessionAuthenticationException("验证码不匹配");
        }

    }
}
