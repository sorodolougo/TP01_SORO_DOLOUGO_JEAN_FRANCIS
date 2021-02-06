/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dolou
 */
public class EtudiantsServlet extends HttpServlet {
    
    private String lienFichier="lalist.csv";

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
         afficheetu(request, response);
    }
    
    private void afficheetu(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        PrintWriter out = response.getWriter();
        String line="";
        String chemi = this.lienFichier;
        
        try
        {
             /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            
            out.println("<title>ListeEtudiantsServlet</title>"); 
           
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='Formetudiant.html'><h1 style='color:yellow' >Aller au formulaire " + request.getContextPath() + "</h1> <a/> <br/>");
            out.println("<table> ");
            out.println("<tr> <th>Nom<th> <th>PRENOMs <th/> <th>Email<th/> </tr>");
            
            
            
            BufferedReader br = new BufferedReader(new FileReader(chemi));
            while ((line = br.readLine())!= null )
            {
                String etu[] = line.split(",");
                out.println(" <tr><td>"+etu[0]+"<td/><td>"+etu[1]+"<td/> <td>"+etu[2]+"<td/> <tr/></body>");
                
            }
                  
            out.println(" <table/> </body>");
            out.println("</html>");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
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
       //processRequest(request, response);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("mail");
        
        
        try
        {
            FileWriter fw = new FileWriter(lienFichier,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(nom+","+prenom+","+email);
            pw.flush();
            pw.close();
        }
        catch(Exception e)
        {
            
        }
        
        afficheetu(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
}
