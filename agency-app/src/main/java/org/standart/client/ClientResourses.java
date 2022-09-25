package org.standart.client;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/client")
public class ClientResourses {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(Client.listAll()).build();
    }

    @GET
    @Path("findById")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@QueryParam("id") long id) {
        return Response.ok(Client.findById(id)).build();
    }

    @DELETE
    @Path("delete")
    @Transactional
    public Response deleteById(@QueryParam("id") long id) {
        Client.deleteById(id);
        return Response.status(Status.OK).build();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Client client) {
        client.id = null;
        client.persistAndFlush();
        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") long id, Client client) {

        if (Client.findById(id) != null) {
            client.persistAndFlush();
            return Response.status(Status.OK).build();
        }
        return Response.status(Status.NOT_FOUND).build();

    }

}
