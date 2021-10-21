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

    @Operation(
            summary = "Gets one Person id",
            description = "Gets the id of the Person that is searched for with the PathParam"
    )
    @GET
    @Path("person/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findPerson(@PathParam int id) {
        return Response.ok(personRepository.getById((long) id))
                .build();
    }

    @Operation(
            summary = "Updates a person",
            description = "For updating the attributes of a specific Person"
    )
    @PATCH
    @Path("person/name/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateName(@PathParam int id, JsonObject eventJson) {
        long now = System.currentTimeMillis();
        Instant testTimeStamp = Instant.ofEpochSecond(now, 345000000);

        Person person = new Person(eventJson.getString("newFirstName"), eventJson.getString("newLastName"), eventJson.getString("newEmail"), eventJson.getString("newPhoneNumber"), testTimeStamp);
        person.setId((long) id);
        personRepository.update(person);
        return Response.ok().build();
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
