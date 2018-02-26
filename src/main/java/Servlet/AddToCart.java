/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entity.OrderPiece;
import DAO.DAO;
import Entity.Cupcake;
import Entity.CupcakePart;
import datasource.DataSource1;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wicktus
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet
{

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
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            DAO dao = new DAO(new DataSource1().getDataSource());

            CupcakePart top = dao.getCupcakeTop(Integer.parseInt(request.getParameter("top")));
            CupcakePart bottom = dao.getCupcakeBottom(Integer.parseInt(request.getParameter("bottom")));

            ArrayList<OrderPiece> orderList;

            HttpSession session = request.getSession();
            if (session.getAttribute("orderList") == null) {
                orderList = new ArrayList<>();
            } else {
                orderList = (ArrayList) session.getAttribute("orderList");
            }

            Cupcake cake = new Cupcake(top, bottom, Integer.parseInt(request.getParameter("amount")));
            orderList.add(new OrderPiece((top.getPrice() + bottom.getPrice()) * Integer.parseInt(request.getParameter("amount")), cake));

            session.setAttribute("orderList", orderList);

            //LOL
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Order added to cart');");
            out.println("location='order.jsp';");
            out.println("</script>");

            //response.sendRedirect("order.jsp");
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
