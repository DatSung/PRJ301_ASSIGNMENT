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
import java.util.ArrayList;
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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            //lấy dữ liệu nhập client -> check xem có nhập gì không
            String id = request.getParameter("id");
            String name = GetParam.getStringParam(request, "name", "Name", null);
            String address = GetParam.getStringParam(request, "address", "Address", null);
            String email = GetParam.getStringParam(request, "email", "Email", null);
            //lấy pass user đã nhập
            String cilentPassword = (String) GetParam.getStringParam(request, "check", "Password", null);
            String clientRePassword = (String) GetParam.getStringParam(request, "recheck", "Password Confirm", null);
            String cstatus = request.getParameter("status");
            if (name == null
                    || address == null
                    || email == null
                    || cilentPassword == null
                    || clientRePassword == null) {
                request.getRequestDispatcher("DispatcherServlet?action=" + IMyConstant.ACTION_PAGE_REGISTER).forward(request, response);
            } else if (!cilentPassword.equals(clientRePassword)) {
                request.setAttribute("msg", "Confirm password is not the same as password ");
                request.getRequestDispatcher("DispatcherServlet?action=" + IMyConstant.ACTION_PAGE_REGISTER).forward(request, response);
            } else {
                //lấy id encode
                id = (String) Utils.encodeString(id);
                //lấy pass đã encoded 
                String password = Utils.encodeString(cilentPassword);

                //đề phòng tên là kiểu có dấu, ép sang UTF-8 cho chắc
                name = new String(name.getBytes("iso-8859-1"), "UTF-8");
                address = new String(address.getBytes("iso-8859-1"), "UTF-8");

                User add = new User(id, address, name, cstatus, email, password);
                UserDAO cd = new UserDAO();
                //kiểm tra xem có trùng user không ?
                ArrayList list = cd.getUserByPurpose("email");

                if (list != null && !list.contains(email)) {
                    //nếu giá trị trả về khác 0 thì là đúng -> add thành công
                    if (cd.addUser(add)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", add);
                        response.sendRedirect(IMyConstant.PAGE_LOGIN);
                    }
                } else {
                    request.setAttribute("msg", "User with email:" + email + " has been existed");
                    request.getRequestDispatcher("DispatcherServlet?action=" + IMyConstant.ACTION_PAGE_REGISTER).forward(request, response);
                }
            }
        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
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
