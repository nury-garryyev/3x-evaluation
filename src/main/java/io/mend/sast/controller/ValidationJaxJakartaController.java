package io.mend.sast.controller;

import io.mend.sast.conf.JDBCConfiguration;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.sql.Statement;

@Path("/jax_validation")
public class ValidationJaxJakartaController {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_HTML)
    public void test(@QueryParam("input") String input) throws SQLException {
        try (Statement statement = JDBCConfiguration.getJDBCConnection().createStatement()) {
            statement.executeQuery(input);
        }
    }

    @GET
    @Path("number")
    @Produces(MediaType.TEXT_HTML)
    public void number(@Min(1) @QueryParam("input1") String input1,
                       @Max(10) @QueryParam("input2") String input2,
                       @DecimalMin(value = "0.1", inclusive = false) @QueryParam("input3") String input3,
                       @DecimalMax(value = "1000000", inclusive = false) @QueryParam("input4") String input4,
                       @Negative @QueryParam("input5") String input5,
                       @NegativeOrZero @QueryParam("input6") String input6,
                       @Positive @QueryParam("input7") String input7,
                       @PositiveOrZero @QueryParam("input8") String input8,
                       @Size(min = 2, max = 10) @QueryParam("input9") String input9,
                       @Digits(integer=9, fraction=2) @QueryParam("input10") String input10,
                       @Email @QueryParam("input11") String input11,
                       @Pattern(regexp = "\\d+") @QueryParam("input12") String input12,
                       @Future @QueryParam("input13") String input13,
                       @FutureOrPresent @QueryParam("input14") String input14,
                       @Past @QueryParam("input15") String input15,
                       @PastOrPresent @QueryParam("input16") String input16) throws SQLException {

        // inputs are validated in method argument declaration.
        String input =  input1 + " " +
                        input2 + " " +
                        input3 + " " +
                        input4 + " " +
                        input5 + " " +
                        input6 + " " +
                        input7 + " " +
                        input8 + " " +
                        input9 + " " +
                        input10 + " " +
                        input11 + " " +
                        input12 + " " +
                        input13 + " " +
                        input14 + " " +
                        input15 + " " +
                        input16;

        try (Statement statement = JDBCConfiguration.getJDBCConnection().createStatement()) {
            statement.executeQuery(input);
        }
    }
}
