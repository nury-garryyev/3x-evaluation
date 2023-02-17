package io.mend.sast.controller;

import io.mend.sast.model.GreetingMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.nio.charset.StandardCharsets;

@Path("/xss-jax")
public class XssJaxController {

    @GET
    //@POST
    //@PUT
    //@DELETE
    //@PATCH
    //@HEAD
    //@OPTIONS
    @Path("/all/{pathParam}")
    @Produces(MediaType.TEXT_HTML)
    public String all(@PathParam("pathParam") String pathParam,             // SOURCE: pathParam
                          @QueryParam("queryParam") String queryParam,      // SOURCE: queryParam
                          @MatrixParam("matrixParam") String matrixParam,   // SOURCE: matrixParam
                          @CookieParam("cookieParam") String cookieParam,   // SOURCE: cookieParam
                          //@FormParam("formParam") String formParam,       // SOURCE: formParam
                          @HeaderParam("headerParam") String headerParam) { // SOURCE: headerParam

        // SINK: XSS
        return "Path Param: " + pathParam + "\n" +
                "Query Param: " + queryParam + "\n" +
                "Matrix Param: " + matrixParam + "\n" +
                "Cookie Param: " + cookieParam + "\n" +
                //"Form Param: " + formParam + "\n" +
                "Header Param: " + headerParam;
    }

    @POST
    @Path("/body/text")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_HTML)
    public String testBodyText(String message) { // SOURCE: message

        // SINK: XSS
        return message;
    }

    @POST
    @Path("/body/json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public String testBodyJson(GreetingMessage message) { // SOURCE: message

        // SINK: XSS
        return message.getMessage();
    }

    @POST
    @Path("/body/xml")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_HTML)
    public String testBodyXml(GreetingMessage message) { // SOURCE: message

        // SINK: XSS
        return message.getMessage();
    }

    @POST
    @Path("/body/form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String testBodyForm(String message) { // SOURCE: message

        // SINK: XSS
        return message;
    }

    @POST
    @Path("/body/form2")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String testBodyForm2(Form form) { // SOURCE: form

        // SINK: XSS
        return form.asMap().getFirst("message");
    }

    @POST
    @Path("/body/binary")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String testBodyBinary(byte[] data) { // SOURCE: data

        // SINK: XSS
        return new String(data, StandardCharsets.UTF_8);
    }

    @Context
    private HttpServletRequest httpRequest;  // SOURCE

    @GET
    @Path("/request/injection")
    @Produces(MediaType.TEXT_HTML)
    public String request() { // SOURCE: data

        // SINK: XSS
        return httpRequest.getParameter("input");
    }
}
