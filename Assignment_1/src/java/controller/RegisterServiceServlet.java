/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContractDAO;
import dao.ServiceDAO;
import dto.Contract;
import dto.Service;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class RegisterServiceServlet extends HttpServlet {

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

            int serviceNo = Integer.parseInt(request.getParameter("serviceNo"));
            ServiceDAO sd = new ServiceDAO();
            Service s = sd.getService(serviceNo);

            //lấy customer
            HttpSession session = request.getSession();
            User c = (User) session.getAttribute("user");

            ContractDAO cd = new ContractDAO();
            String duration = request.getParameter("duration");
            //kiểm tra xem có bị trùng hay không đã
            if (cd.isDuplicateContract(c.getCid(), s.getId())) {
                request.setAttribute("msg", "You already have " + s.getSname() + " service. Choose another");
                request.getRequestDispatcher("/DispatcherServlet?action=" + IMyConstant.ACTION_PAGE_REGISTER_SERVICE).forward(request, response);
            } else {
                //tính toán thời gian đăng ký với thời hạn khách hàng muốn đăng ký
                //lưu dưới dạng java.util.Date cho Contact với format yyyy-MM-dd
                //lấy thời gian hiện hành, localDate sẽ trả về yyyy/MM/dd -> ko cần format nhiều
                LocalDate startDate = LocalDate.now();
                //localDate có các hàm tính toán thời gian lun -> ko cần sử dụng Calendar
                LocalDate expDate = startDate.plusMonths(Long.parseLong(duration));

                //tính giá tiền trên n tháng
                BigDecimal totalMoney = s.getCostpermonth().multiply((new BigDecimal(duration)));

                Contract temp = new Contract(c, s, startDate, expDate, 0, totalMoney);

                //insert đối tượng
                int status = cd.addContract(temp);
                //chỉ cần khác 0 là đã add rồi
                if (status != 0) {
                    request.setAttribute("msg", s.getSname() + " is added succesfull. Waiting for admin process");
                    request.getRequestDispatcher(IMyConstant.CONTROLLER_DISPATCHER + "?action=" + IMyConstant.ACTION_PAGE_CONTRACT).forward(request, response);
                }
            }

//        } catch (SQLException sqe) {
//            System.out.println(sqe.getMessage());
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
