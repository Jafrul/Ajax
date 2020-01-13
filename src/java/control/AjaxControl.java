/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;
import service.Repository;

/**
 *
 * @author Faysal
 */
@WebServlet(name = "AjaxControl", urlPatterns = {"/ajaxcontrol"})
public class AjaxControl extends HttpServlet {

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
        
        
        Repository rp = new Repository();
        
        
        
        try {
            if(request.getParameter("action")!=null&&request.getParameter("action").equals("add")){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        Student stu = new Student();
        stu.setId(id);
        stu.setName(name);
        stu.setGender(gender);
        stu.setAddress(address);
        rp.addData(stu);
            }
        } catch (Exception e) {
        }
        
        
        List<Student> list = rp.GetFromDatabase();
        
        String json = "[";
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                json += "{\"id\":\"" + list.get(i).getId()+ "\",\"name\":\"" + list.get(i).getName() + "\",\"gender\":\"" + list.get(i).getGender() + "\",\"address\":\"" + list.get(i).getAddress()+ "\"}";
            } else {
                json += ",{\"id\":\"" + list.get(i).getId() + "\",\"name\":\"" + list.get(i).getName() + "\",\"gender\":\"" + list.get(i).getGender() + "\",\"address\":\"" + list.get(i).getAddress() + "\"}";
            }
        }
        json += "]";
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
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
        Repository rp = new Repository();
       
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
