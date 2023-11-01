/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


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
 * @author 84859
 */
public class DispatcherServlet extends HttpServlet {

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
            String url = IMyConstant.PAGE_WELCOME;
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
//            User us = (User) session.getAttribute("user");

            if (action == null) {
                action = "";
            }

            switch (action) {
                //register - đăng ký
                case IMyConstant.ACTION_PAGE_REGISTER: {
                    url = IMyConstant.PAGE_REGISTER;
                    break;
                }
                case IMyConstant.ACTION_PAGE_SERVICE: {
                    url = IMyConstant.PAGE_SERVICES;
                    break;
                }
                case IMyConstant.ACTION_PAGE_CONTRACT: {
                    url = IMyConstant.PAGE_CONTRACT;
                    break;
                }
                case IMyConstant.ACTION_PAGE_SETTING: {
                    url = IMyConstant.PAGE_SETTING;
                    break;
                }
                case IMyConstant.ACTION_PAGE_EDIT: {
                    url = IMyConstant.PAGE_EDIT;
                    break;
                }
                case IMyConstant.ACTION_PAGE_SUPPORT: {
                    url = IMyConstant.PAGE_SUPPORT;
                    break;
                }
                //action dẫn tới Servlet
                case IMyConstant.ACTION_PAGE_REGISTER_SERVICE: {
                    url = IMyConstant.PAGE_REGISTER_SERVICE;
                    break;
                }

                case IMyConstant.ACTION_CONTROLLER_LOGIN: {
                    url = IMyConstant.CONTROLLER_LOGIN;
                    break;
                }
                case IMyConstant.ACTION_CONTROLLER_REGISTER_SERVICE: {
                    url = IMyConstant.CONTROLLER_REGISTER_SERVICE;
                    break;
                }
                case IMyConstant.ACTION_CONTROLLER_EDIT: {
                    request.setAttribute("target", request.getParameter("target"));
                    url = IMyConstant.CONTROLLER_EDIT;
                    break;
                }
                case IMyConstant.ACTION_CONTROLLER_REGISTER: {
                    url = IMyConstant.CONTROLLER_REGISTER;
                    break;
                }
                case IMyConstant.ACTION_CONTROLLER_SUPPORT: {
                    url = IMyConstant.CONTROLLER_SUPPORT;
                    break;
                }
                case IMyConstant.ACTION_CONTROLLER_DELETE_REQUEST: {
                    url = IMyConstant.CONTROLLER_DELETE_REQUEST;
                    break;
                }
            }
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            //System.out.println(e.getMessage());
            response.sendRedirect(IMyConstant.PAGE_ERROR);
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
