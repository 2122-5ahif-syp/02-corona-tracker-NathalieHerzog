package at.htl.boundary;

import at.htl.entity.Person;
import at.htl.repository.PersonRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;

@Path("api")
public class PersonService {
    @Inject
    PersonRepository personRepository;

    public PersonService() {
    }

    @POST
    @Path("/")
    public Response addUser(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("eMail") String eMail,
            @FormParam("phoneNumber") String phoneNumber) {

        return Response.status(200)
                .entity("User added:" + firstName + " " + lastName)
                .build();

    }
}
