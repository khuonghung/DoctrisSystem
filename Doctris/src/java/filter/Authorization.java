/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Khuong Hung
 */
public class Authorization implements Filter {

    private static final String ERROR401 = "/401.jsp";
    private static final String LOGIN = "/user?action=login";
    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    public Authorization() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        String url = request.getRequestURI() + "?" + request.getQueryString();

        if (url.contains("manage") || url.contains("setting") || url.contains("account") || url.contains("dashboard")) {
            if (user != null) {
                if (url.contains("patientmanage") || url.contains("doctormanage")
                        || url.contains("servicemanage") || url.contains("blogmanage")) {
                    if (user.getRole().getRole_id() == 5 || user.getRole().getRole_id() == 1) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        response.sendRedirect(request.getContextPath() + ERROR401);
                    }
                } else if (url.contains("appointmentmanage") || url.contains("reservationmanage")) {
                    if (user.getRole().getRole_id() == 4 || user.getRole().getRole_id() == 1) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        response.sendRedirect(request.getContextPath() + ERROR401);
                    }
                } else {
                    if (user.getRole().getRole_id() == 1) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        response.sendRedirect(request.getContextPath() + ERROR401);
                    }
                }
            } else {
                response.sendRedirect(request.getContextPath() + LOGIN);
            }
        } else if (url.contains("user?action=history")) {
            if (user != null) {
                if (user.getRole().getRole_id() == 2) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    response.sendRedirect(request.getContextPath() + ERROR401);
                }
            } else {
                response.sendRedirect(request.getContextPath() + LOGIN);
            }
        } else if (url.contains("doctor?action=myfeedback") || url.contains("doctor?action=mypatient")
                || url.contains("doctor?action=myappointment")) {
            if (user != null) {
                if (user.getRole().getRole_id() == 3) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    response.sendRedirect(request.getContextPath() + ERROR401);
                }
            } else {
                response.sendRedirect(request.getContextPath() + LOGIN);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("Authorization:Initializing filter");
            }
        }
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("Authorization()");
        }
        StringBuffer sb = new StringBuffer("Authorization(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
