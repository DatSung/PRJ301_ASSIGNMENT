/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ServiceDAO;
import dto.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author uinic
 */
public class AdServiceUpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            int svid = Integer.parseInt(request.getParameter("svid"));
            String svname = request.getParameter("svname");
            String svdes = request.getParameter("svdes");
            boolean svstatus = false;
            if ("true".equals(request.getParameter("svstatus"))) {
                svstatus = true; // false thanh 1 thanh working
            } else if ("false".equals(request.getParameter("svstatus"))) {
                svstatus = false; // true thanh 0 thanh not working
            }
            BigDecimal svcost = new BigDecimal(request.getParameter("svcost"));
            Service service = new Service(svid, svname, svdes, svstatus, svcost);
            out.print(service.toString());
            if (service != null) {
                ServiceDAO serviceDAO = new ServiceDAO();
                try {
                    if (serviceDAO.updateService(service)) {
                        request.setAttribute("updateserviceresult", "Update Service Success !");
                    } else {
                        request.setAttribute("updateserviceresult", "Update Service Fail !");
                    }
                } catch (Exception e) {
                    out.print(e.getMessage());
                }
            }
            request.getRequestDispatcher("ad_page_service.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
