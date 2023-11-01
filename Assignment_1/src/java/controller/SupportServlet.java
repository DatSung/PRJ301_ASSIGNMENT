/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RequestDAO;
import dto.Request;
import dto.RequestType;
import dto.Staff;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.GetParam;
import utils.IMyConstant;

/**
 *
 * @author 84859
 */
public class SupportServlet extends HttpServlet {

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
//            StaffDAO st = new StaffDAO();
            RequestDAO rd = new RequestDAO();

//            ServletContext context = request.getServletContext();
//            ArrayList<Staff> temp = (ArrayList<Staff>) context.getAttribute("TEStaffList");
//            ArrayList<Staff> reTemp = st.getStaffsByRole("TE");
//            if (temp == null || temp.size() != reTemp.size()) {
//                context.setAttribute("TEStaffList", reTemp);
//            }
//            temp = (ArrayList<Staff>) context.getAttribute("TEStaffList");
//            Collections.shuffle(reTemp);
            //lấy rtid -> lấy RequestType
            int id = Integer.parseInt(request.getParameter("rtid"));
            RequestType rtid = rd.getRequestType(id);
            //lấy description <-> nhớ chuyển thành kiểu utf-8
            String des = (String) GetParam.getStringParam(request, "description", "Description", null);
            if (des == null) {
                request.getRequestDispatcher("DispatcherServlet?action=" + IMyConstant.ACTION_PAGE_SUPPORT).forward(request, response);
            } else {
                LocalDate now = LocalDate.now();

                int rstatus = Integer.parseInt(request.getParameter("rstatus"));

                //các nhân viên kỹ thuật sẽ dc sắp xếp ngẫu nhiên
//            Staff random = reTemp.get(new Random().nextInt(reTemp.size()));
                HttpSession session = request.getSession();
                User cid = (User) session.getAttribute("user");
                Staff random = (Staff) session.getAttribute("TEStaff");

                Request add = new Request(rtid, des, now, rstatus, cid, random);

                if (rd.addRequest(add)) {
                    response.sendRedirect(IMyConstant.PAGE_SUPPORT);
                } else {
                    response.sendRedirect(IMyConstant.PAGE_ERROR);

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
