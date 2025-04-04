package org.example.todojpa.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.todojpa.common.Const;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/users/signup", "/logout", "/session-login", "/check"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 화이트리스트에 없는 요청에 대해 로그인 체크
        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute(Const.LOGIN_USER) == null) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.getWriter().write("message : 로그인 해주세요");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * 요청 URI가 화이트리스트에 포함되어 있는지 확인합니다.
     *
     * @param requestURI 요청 URI
     * @return 화이트리스트에 포함되어 있으면 true, 그렇지 않으면 false
     */
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
