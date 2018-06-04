package com.entor.hrm.interceptor;

import com.entor.hrm.po.User;
import com.entor.hrm.to.CommonMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static com.entor.hrm.util.common.HrmConstants.USER_SESSION;

/**
 * 权限访问拦截器
 */
public class AuthorizedInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LogManager.getLogger(AuthorizedInterceptor.class);
    private static final String[] IGNORE_URI = {"index", "login"};

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("preHandle invoked!");

        boolean flag = false;
        String servletPath = request.getServletPath();

        LOGGER.info("SERVLET_PATH:【{}】", servletPath);

        for (String uri : IGNORE_URI) {
            if (servletPath.contains(uri)) {
                flag = true;
                LOGGER.info("IGNORE_URI:【{}】", uri);
                break;
            }
        }
        if (!flag) {
            User user = (User) request.getSession().getAttribute(USER_SESSION);
            LOGGER.info("USER_SESSION:【{}】", user);
            if (user != null) {
                flag = true;
            } else {
                request.setAttribute("commonMessage", new CommonMessage("请登录..."));
                request.getRequestDispatcher("/index").forward(request, response);
                return flag;
            }
        }
        return flag;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("postHandle invoked!");
    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("afterCompletion invoked!");
    }
}
