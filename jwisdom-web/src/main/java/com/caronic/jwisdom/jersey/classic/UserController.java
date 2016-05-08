package com.caronic.jwisdom.jersey.classic;

import com.caronic.jwisdom.data.domain.User;
import com.caronic.jwisdom.data.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Created by caronic on 2016/5/8.
 */
@Profile("web")
@Component
@Path("user")
@Produces("application/json")
public class UserController {

    @Inject
    private UserRepository userRepository;

    @Context
    private UriInfo uriInfo;

    @GET
    public Page<User> findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size,
            @QueryParam("sort") @DefaultValue("name") List<String> sort,
            @QueryParam("direction") @DefaultValue("asc") String direction) {

        return userRepository.findAll(
                new PageRequest(page, size, Sort.Direction.fromString(direction), sort.toArray(new String[0]))
        );

    }

    @GET
    @Path("{id}")
    public User findOne(@PathParam("id") Long id) {
        return userRepository.findOne(id);
    }

    @POST
    @Consumes(value = {"application/json"})
    public Response save(User user) {
        user = userRepository.save(user);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .resolveTemplate("id", user.getId())
                .build();

        return Response.created(location).build();
    }

    @DELETE
    public Response delete(@PathParam("id") Long id) {
        userRepository.delete(id);
        return Response.accepted().build();
    }

}
