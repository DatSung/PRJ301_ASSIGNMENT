/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StaffDAO;
import dao.UserDAO;
import dto.Staff;
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
import utils.GetParam;
import utils.IMyConstant;

/**
 *
 * @author 84859
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    public final String isLogin = "false";

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
            HttpSession session = request.getSession();
            //default value để null để Th quay lại loginpage nếu sai thì ko có dữ liệu gì
            String email = GetParam.getStringParam(request, "email", "Email", null);
            String pass = GetParam.getStringParam(request, "password", "Password", null);

            if (email == null || pass == null) {
                request.getRequestDispatcher("/DispatcherServlet?action=" + IMyConstant.ACTION_PAGE_NOTHING).forward(request, response);
            } else {
                String encodedpass = (String) Utils.encodeString(pass);
                UserDAO cd = new UserDAO();
                User user = cd.checkLogin(email, encodedpass);
                if (user == null) {
                    StaffDAO sd = new StaffDAO();
                    Staff staff = sd.checkLogin(email, pass);
                    if (staff == null) {
                        request.setAttribute("msg", "User name or Password is incorrect");
                        request.getRequestDispatcher("/DispatcherServlet?action=" + IMyConstant.ACTION_PAGE_NOTHING).forward(request, response);
                    } else {
                        session.setAttribute("staff", staff);
                        //lưu mk để sử dụng trong setting
                        session.setAttribute("staffpass", pass);
                        response.sendRedirect(IMyConstant.PAGE_LOGIN);
                    }

                } else {
                    session.setAttribute("user", user);
                    //lưu mk để sử dụng trong setting
                    session.setAttribute("userpass", pass);
                    response.sendRedirect(IMyConstant.PAGE_WELCOME  );
                }
            }

        } catch (Exception e) {
            e.getStackTrace();
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
