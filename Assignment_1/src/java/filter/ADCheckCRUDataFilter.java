/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import dao.StaffDAO;
import dto.Staff;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author uinic
 */
public class ADCheckCRUDataFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public ADCheckCRUDataFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("ADCheckCRUDataFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
        String action = request.getParameter("action");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession ses = req.getSession();

        if (ses.getAttribute("staff") == null) {
            req.getRequestDispatcher("page_login.jsp").forward(request, response);
        }
        switch (action) {

            //
            // SERVICE
            //
            case "actioncreateviewservice": {
                String svname = request.getParameter("svname");
                String svdes = request.getParameter("svdes");
                String svstatus = request.getParameter("svstatus");
                String svcost = request.getParameter("svcost");
                if (svname == null || svdes == null || svstatus == null || svcost == null) {
                    request.setAttribute("svname", svname);
                    request.setAttribute("svdes", svdes);
                    request.setAttribute("svcost", svcost);
                    request.setAttribute("svstatus", svstatus);
                    request.setAttribute("createserviceresult", "Some fields was empty!");
                    request.getRequestDispatcher("ad_page_service_create").forward(request, response);
                }
                if (Integer.parseInt(svcost) < 0) {
                    request.setAttribute("svname", svname);
                    request.setAttribute("svdes", svdes);
                    request.setAttribute("svcost", svcost);
                    request.setAttribute("svstatus", svstatus);
                    request.setAttribute("createserviceresult", "Service cost cannot be negative!");
                    request.getRequestDispatcher("ad_page_service_create.jsp").forward(request, response);
                }
                if (svdes.length() > 50) {
                    request.setAttribute("svname", svname);
                    request.setAttribute("svdes", svdes);
                    request.setAttribute("svcost", svcost);
                    request.setAttribute("svstatus", svstatus);
                    request.setAttribute("createserviceresult", "Description must be less than 50 characters!");
                    request.getRequestDispatcher("ad_page_service_create.jsp").forward(request, response);
                }
                break;
            }

            case "viewserviceupdate": {
                String svname = request.getParameter("svname");
                String svdes = request.getParameter("svdes");
                String svstatus = request.getParameter("svstatus");
                String svcost = request.getParameter("svcost");
                if (svname == null || svdes == null || svstatus == null || svcost == null) {
                    request.setAttribute("svname", svname);
                    request.setAttribute("svdes", svdes);
                    request.setAttribute("svcost", svcost);
                    request.setAttribute("svstatus", svstatus);
                    request.setAttribute("updateserviceresult", "Some fields was empty!");
                    request.getRequestDispatcher("ad_page_service.jsp").forward(request, response);
                }
                if (Integer.parseInt(svcost) < 0) {
                    request.setAttribute("svname", svname);
                    request.setAttribute("svdes", svdes);
                    request.setAttribute("svcost", svcost);
                    request.setAttribute("svstatus", svstatus);
                    request.setAttribute("updateserviceresult", "Service cost cannot be negative!");
                    request.getRequestDispatcher("ad_page_service.jsp").forward(request, response);
                }
                if (svdes.length() > 50) {
                    request.setAttribute("svname", svname);
                    request.setAttribute("svdes", svdes);
                    request.setAttribute("svcost", svcost);
                    request.setAttribute("svstatus", svstatus);
                    request.setAttribute("updateserviceresult", "Description must be less than 50 characters!");
                    request.getRequestDispatcher("ad_page_service.jsp").forward(request, response);
                }
                break;
            }

            case "viewserviceremove": {
                break;
            }

            //
            // STAFF
            //
            case "actioncreateviewstaff": {
                StaffDAO staffDAO = new StaffDAO();
                ArrayList<Staff> staffList = new ArrayList<>();
                try {
                    staffList = staffDAO.getListStaffs();
                } catch (Exception e) {
                    request.getRequestDispatcher("error.html").forward(request, response);
                }
                String stemail = request.getParameter("stemail");
                String stpass = request.getParameter("stpass");
                String stpassconfirm = request.getParameter("stpassconfirm");
                String strole = request.getParameter("strole");
                String stname = request.getParameter("stname");
                if (!stemail.contains("@fpt.edu.vn")) {
                    request.setAttribute("stemail", stemail);
                    request.setAttribute("stpass", stpass);
                    request.setAttribute("stpassconfirm", stpassconfirm);
                    request.setAttribute("strole", strole);
                    request.setAttribute("stname", stname);
                    request.setAttribute("createstaffresult", "The email must be '@fpt.edu.vn'!");
                    request.getRequestDispatcher("ad_page_staff_create.jsp").forward(request, response);
                }
                if (!stpass.equals(stpassconfirm)) {
                    request.setAttribute("stemail", stemail);
                    request.setAttribute("stpass", stpass);
                    request.setAttribute("stpassconfirm", stpassconfirm);
                    request.setAttribute("strole", strole);
                    request.setAttribute("stname", stname);
                    request.setAttribute("createstaffresult", "Password was not the same!");
                    request.getRequestDispatcher("ad_page_staff_create.jsp").forward(request, response);
                }
                for (Staff staff : staffList) {
                    if (staff.getEmail().equalsIgnoreCase(stemail)) {
                        request.setAttribute("stemail", stemail);
                        request.setAttribute("stpass", stpass);
                        request.setAttribute("stpassconfirm", stpassconfirm);
                        request.setAttribute("strole", strole);
                        request.setAttribute("stname", stname);
                        request.setAttribute("createstaffresult", "Email was using by another Staff!");
                        request.getRequestDispatcher("ad_page_staff_create.jsp").forward(request, response);
                    }
                }
                break;
            }

            case "viewstaffupdate": {
                StaffDAO staffDAO = new StaffDAO();
                ArrayList<Staff> staffList = new ArrayList<>();
                try {
                    staffList = staffDAO.getListStaffs();
                } catch (Exception e) {
                    request.getRequestDispatcher("error.html").forward(request, response);
                }
                BigDecimal stid = new BigDecimal(request.getParameter("stid"));
                String stemail = request.getParameter("stemail");
                String stpass = request.getParameter("stpass");
                String stpassconfirm = request.getParameter("stpassconfirm");
                String strole = request.getParameter("strole");
                String stname = request.getParameter("stname");
                if (!stemail.contains("@fpt.edu.vn")) {
                    request.setAttribute("stemail", stemail);
                    request.setAttribute("stpass", stpass);
                    request.setAttribute("stpassconfirm", stpassconfirm);
                    request.setAttribute("strole", strole);
                    request.setAttribute("stname", stname);
                    request.setAttribute("updatestaffresult", "The email must be '@fpt.edu.vn'!");
                    request.getRequestDispatcher("ad_page_staff.jsp").forward(request, response);
                }
                for (Staff staff : staffList) {
                    int comparisonResult = staff.getSid().compareTo(stid);
                    if (staff.getEmail().equalsIgnoreCase(stemail) && comparisonResult != 0) {
                        request.setAttribute("stemail", stemail);
                        request.setAttribute("stpass", stpass);
                        request.setAttribute("stpassconfirm", stpassconfirm);
                        request.setAttribute("strole", strole);
                        request.setAttribute("stname", stname);
                        request.setAttribute("updatestaffresult", "Email was using by another Staff!");
                        request.getRequestDispatcher("ad_page_staff.jsp").forward(request, response);
                    }
                }
                break;
            }
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("ADCheckCRUDataFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("ADCheckCRUDataFilter:doFilter()");
        }

        doBeforeProcessing(request, response);

        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("ADCheckCRUDataFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("ADCheckCRUDataFilter()");
        }
        StringBuffer sb = new StringBuffer("ADCheckCRUDataFilter(");
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
