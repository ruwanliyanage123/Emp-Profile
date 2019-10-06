package controller;

import Bean.Employer;
import services.EmployerService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("employees")
public class EmpoyerController {
    private EmployerService employerService;
    EmpoyerController(){
        employerService = new EmployerService();
    }

    @GET
    @Path("employee")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Employer> getAllEmployers(){
        return employerService.getAllEmployers();
    }

    @GET
    @Path("employee/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Employer getEmployer(@PathParam("param") int employerId){
        return employerService.getEmployer(employerId);
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addEmployer(Employer employer){
        employerService.addEmployer(employer);
    }

    @DELETE
    @Path("delete/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteEmployer(@PathParam("param") int employerId){
        employerService.deleteEmployer(employerId);
    }

    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateEmployer(Employer employer){
        employerService.updateEmployer(employer);
    }
}
