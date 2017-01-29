/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author scott_000
 */
public class Assignment3Servlet extends HttpServlet {

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
            
            int hit = 0;
            int errorFirstName = 0;
            int errorLastName = 0;
            int errorState = 0;
            int errorZip = 0;
            int errorED = 0;
            int errorSC = 0;
            boolean error = false;
            
            //receive credit card information
            String lastName = request.getParameter("lastName").toString();
            String firstName = request.getParameter("firstName").toString();
            String streetAddress = request.getParameter("streetAddress").toString();
            String city = request.getParameter("city").toString();
            String state = request.getParameter("state").toString();
            String zip = request.getParameter("zip").toString();
            String ccNumber = request.getParameter("ccNumber").toString();
            String securityCode = request.getParameter("securityCode").toString();
            String expirationDate = request.getParameter("expirationDate").toString();
            
            
            //Validation: First Name
            char [] firstNameArray = firstName.toCharArray();
            for (int i = 0; i < firstNameArray.length; i++){
                if (Character.isDigit(firstNameArray[i])) {
                    errorFirstName = 1;
                    error = true;
                }
            }
            
            //Validation: Last Name
            char [] lastNameArray = lastName.toCharArray();
            for (int i = 0; i < lastNameArray.length; i++){
                if (Character.isDigit(lastNameArray[i])) {
                    errorLastName = 1;

                }
            }
            
            //Validation:State
            char [] stateArray = state.toCharArray();
            for (int i = 0; i < stateArray.length; i++) {
                if (Character.isDigit(stateArray[i])) {
                    errorState = 1;

                }
            }
            
            //Validation: Zip
            char [] zipArray = zip.toCharArray();
            for (int i = 0; i < zipArray.length; i++) {
                if (Character.isLetter(zipArray[i])){
                    errorZip = 1;
                }
            }
            
            //Validate: Expiration Date
            boolean checkFormat = true;
            if (!expirationDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
                checkFormat=false;

            }
            
            //Validate: Security Number
            if (!securityCode.matches("([0-9]{3})")){
                errorSC = 1;
                

            }
            
            
            //Credit Card Numbers
            String[] ccNumbersArray = {"1234567891234567", 
                               "3434343434343434", 
                               "5353535353535353",
                               "2323232323232323",
                               "1212121212121212",
                               "4545454545454545",
                               "8686868686868686",
                               "3333333333333333",
                               "2222222222222222",
                               "1111111111111111"};
           
           
            //Check CCNumbers
           if (error != true){
            for (int i = 0; i < 10; i++) {
                if (ccNumber.equals(ccNumbersArray[i])) {
                    hit = 1;
                }
            }
           }
                
            
           

            //confirm receipt of credit card information
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Assignment3Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Assignment3Servlet at " + request.getContextPath() + "</h1>");
            
            //print error message
            if (errorLastName == 1) {
                out.println("<p>Format Error: Last Name</p>");
            }
            if (errorFirstName == 1) {
                out.println("<p>Format Error: First Name</p>");
            }
            if (errorState == 1) {
                out.println("<p>Format Error: State</p>");
            }
            if (errorZip == 1){
                out.println("<p>Format Error: Zip</p>");
            }
            if (checkFormat == false){
               out.println("<p>Format Error: Date Format (MM-dd-YYYY)</p>");
            }
            if (errorSC == 1){
                out.println("<p>Format Error: Security Code</p>");
            }
            
            if (error != true) {  
                if (hit == 0) {
                    out.println("<p>Credit Card Not Found</p>");
                } else {
                    out.println("<p>Thank you!</p>");
                }
            }
                     
            out.println("</body>");
            out.println("</html>");
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
