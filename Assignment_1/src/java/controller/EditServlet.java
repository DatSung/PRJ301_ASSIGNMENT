/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Utils;
import utils.IMyConstant;

/**
 *
 * @author 84859
 */

public class EditServlet extends HttpServlet {

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
            String target = request.getParameter("target"); //lấy ra loại muốn edit
            String update = request.getParameter("check");
            String recheck = request.getParameter("recheck");
            String msg = "info confirm is not the same as info";
            if (target != null && update != null) {
                if (!update.trim().equals(recheck.trim())) { //ko giống
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("DispatcherServlet?action=" + IMyConstant.ACTION_PAGE_EDIT).forward(request, response);
                } else if ((update.length() < 6 || update.length() > 15) && "password".equals(target)) {
                    msg = "Password character must be from 6 to 15";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("DispatcherServlet?action=" + IMyConstant.ACTION_PAGE_EDIT).forward(request, response);
                } else {
                    HttpSession session = request.getSession();
                    //do có thể ng dùng là người việt nên phải có bước xử lý thành UTF-8 khi lấy từ jsp xuống servlet
                    update = new String(update.getBytes("iso-8859-1"), "UTF-8");
                    recheck = new String(recheck.getBytes("iso-8859-1"), "UTF-8");
                    //mã hóa
                    if (target.equalsIgnoreCase("password")) {
                        //set lại userpass
                        session.setAttribute("userpass", update);
                        update = Utils.encodeString(update.trim());
                    }
                    
                    User user = (User) session.getAttribute("user");

                    UserDAO cd = new UserDAO();

                    //cập nhật trong sql
                    if (cd.updateUserInfo(user.getCid(), target, update)) {
                        switch(target){
                            case "cname":{
                                user.setCname(update);
                                break;
                            }
                            case "address":{
                                user.setAddress(update);
                                break;
                            }
                            case "password":{
                                user = cd.checkLogin(user.getEmail(), update);
                                break;
                            }
                        }
                        session.setAttribute("user", user );
                        request.getRequestDispatcher("DispatcherServlet?action=" + IMyConstant.ACTION_PAGE_SETTING).forward(request, response);
                    }
                }
            }

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
