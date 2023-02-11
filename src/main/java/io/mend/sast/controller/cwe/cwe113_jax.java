package io.mend.sast.controller.cwe;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;

@Path("/cwe113")
public class cwe113_jax {

    @GET
    @Path("/jax/1/{input}")
    @Produces("application/json")
    public Response unsafe1(@PathParam("input") String input) {
        return Response
                .status(Response.Status.OK)
                .header("input", input) // CWE113 SINK
                .entity("unsafe")
                .build();
    }

    @GET
    @Path("/jax/2/{input}")
    @Produces("application/json")
    public Response unsafe2(@PathParam("input") String input) {

        MultivaluedMap<String, Object> map = new MultivaluedHashMap<>();
        map.add("input", input);

        return Response
                .status(Response.Status.OK)
                .replaceAll(map) // CWE113 SINK
                .entity("unsafe").build();
    }

    @GET
    @Path("/jax/3/{input}")
    @Produces("application/json")
    public Response unsafe3(@PathParam("input") String input) {

        NewCookie cookie = new NewCookie("input", input);

        return Response
                .status(Response.Status.OK)
                .cookie(cookie) // CWE113 SINK
                .entity("unsafe").build();
    }

    @GET
    @Path("/jax/4/{input}")
    @Produces("application/json")
    public Response unsafe4(@PathParam("input") String input) {

        Response.ResponseBuilder builder = new OutboundJaxrsResponse.Builder(new OutboundMessageContext());

        builder.entity("unsafe");

        builder.allow(input); // CWE113 SINK
        builder.language(input); // CWE113 SINK
        builder.encoding(input); // CWE113 SINK
        builder.link(input, input ); // CWE113 SINK
        builder.tag(input); // CWE113 SINK

        return builder.build();
    }
}
