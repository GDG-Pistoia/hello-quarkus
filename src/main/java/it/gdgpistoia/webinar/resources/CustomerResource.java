package it.gdgpistoia.webinar.resources;

import it.gdgpistoia.webinar.controller.CustomerController;
import it.gdgpistoia.webinar.controller.CustomerNotFoundException;
import it.gdgpistoia.webinar.model.Customer;
import it.gdgpistoia.webinar.model.Product;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerResource {

    @Inject
    CustomerController customerController;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Customer customer) {
        return Response.ok(customerController.create(customer)).build();
    }

    @GET
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") Integer id) {
        return Response.ok(customerController.read(id)).build();
    }

    @POST
    @Path("/{id}/products")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(@PathParam("id") Integer id, Product product) {
        try {
            return Response.ok(customerController.addProduct(id, product)).build();
        } catch (CustomerNotFoundException e) {
            return Response.ok().status(404).build();
        }
    }

}