/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.IMyConstant;

/**
 *
 * @author uinic
 */
public class AdminDispatcherServlet extends HttpServlet {

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
            String url = IMyConstant.PAGE_LOGIN;
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            Staff staff = (Staff) session.getAttribute("staff");

            if (action == null) {
                action = "";
            }

            //Neu hanh dong la service thi dan no vao service servlet
            if (action.contains("viewservice")) {
                action = IMyConstant.AD_ACTION_CONTROLLER_SERVICE;
            }

            if (action.contains("viewrequest")) {
                action = IMyConstant.AD_ACTION_CONTROLLER_REQUEST;
            }

            if (action.contains("viewstaff")) {
                action = IMyConstant.AD_ACTION_CONTROLLER_STAFF;
            }

            if (action.contains("viewcustomer")) {
                action = IMyConstant.AD_ACTION_CONTROLLER_CUSTOMER;
            }

            if (action.contains("viewsetting")) {
                action = IMyConstant.AD_ACTION_CONTROLLER_SETTING;
            }

            switch (action) {

                // Case nay de quan li quan li nhan vien
                // Case nay dan den AdminViewStaffServlet
                case IMyConstant.AD_ACTION_CONTROLLER_STAFF: {
                    url = IMyConstant.AD_CONTROLLER_STAFF;
                    break;
                }
                // Case nay dan den trang ad_page_staff.jsp
                case IMyConstant.AD_ACTION_PAGE_STAFF: {
                    url = IMyConstant.AD_PAGE_STAFF;
                    break;
                }

                // Case nay de quan li quan li khach hang
                // Case nay dan den AdminViewCustomerServlet
                case IMyConstant.AD_ACTION_CONTROLLER_CUSTOMER: {
                    url = IMyConstant.AD_CONTROLLER_CUSTOMER;
                    break;
                }
                // Case nay dan den trang ad_page_costumer.jsp
                case IMyConstant.AD_ACTION_PAGE_CUSTOMER: {
                    url = IMyConstant.AD_PAGE_CUSTOMER;
                    break;
                }

                // Case nay de quan li quan li request
                // Case nay dan den AdminViewCustomerServlet
                case IMyConstant.AD_ACTION_CONTROLLER_REQUEST: {
                    url = IMyConstant.AD_CONTROLLER_REQUEST;
                    break;
                }
                // Case nay dan den trang ad_page_costumer.jsp
                case IMyConstant.AD_ACTION_PAGE_REQUEST: {
                    
                    url = IMyConstant.AD_PAGE_REQUEST;
                    break;
                }

                // Case nay de quan li quan li service
                // Case nay dan den AdminViewServiceServlet
                case IMyConstant.AD_ACTION_CONTROLLER_SERVICE: {
                    url = IMyConstant.AD_CONTROLLER_SERVICE;
                    break;
                }
                // Case nay dan den trang ad_page_service.jsp
                case IMyConstant.AD_ACTION_PAGE_SERVICE: {
                    url = IMyConstant.AD_PAGE_SERVICE;
                    break;
                }

                // Case nay de quan li quan li setting
                // Case nay dan den AdminViewCustomerServlet
                case IMyConstant.AD_ACTION_CONTROLLER_SETTING: {
                    url = IMyConstant.AD_CONTROLLER_SETTING;
                    break;
                }
                // Case nay dan den trang ad_page_costumer.jsp
                case IMyConstant.AD_ACTION_PAGE_SETTING: {
                    url = IMyConstant.AD_PAGE_SETTING;
                    break;
                }

            }
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
