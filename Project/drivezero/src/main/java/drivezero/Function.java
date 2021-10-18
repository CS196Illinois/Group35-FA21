package drivezero;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.BindingName;

import drivezero.models.User;
import drivezero.logic.Routes;

import java.io.IOException;
import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("HttpExample")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS)
                    HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        final String query = request.getQueryParameters().get("name");
        final String name = request.getBody().orElse(query);

        if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
        } else {
            return request
                    .createResponseBuilder(HttpStatus.OK)
                    .body("Hello, " + name)
                    .header("Content-Type", "text/html")
                    .build();
        }
    }

    // /api/index
    @FunctionName("index") // change me
    public HttpResponseMessage example( // change me
                                        @HttpTrigger(
                                                name = "req",
                                                methods = {HttpMethod.GET},
                                                authLevel = AuthorizationLevel.ANONYMOUS)
                                                HttpRequestMessage<Optional<String>> request,
                                        final ExecutionContext context) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/handlebars", ".html");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("index"); // change me

        // TODO
        User person = new User();
        person.setName("test user");
        person.setId(1234);

        String templateString = template.apply(person);

        return request.createResponseBuilder(HttpStatus.OK)
                .body(templateString)
                .header("Content-Type", "text/html; charset=UTF-8")
                .build();
    }

    @FunctionName("TriggerStringRoute")
    public HttpResponseMessage routeParam(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "trigger/{id}/{name=EMPTY}") // name is optional and defaults to EMPTY
                    HttpRequestMessage<Optional<String>> request,
            @BindingName("id") String id,
            @BindingName("name") String name,
            final ExecutionContext context) {

        // Item list
        context.getLogger().info("Route parameters are: " + id);

        // Convert and display
        if (id == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Document not found.")
                    .build();
        } else {
            // return JSON from to the client
            // Generate document
            final String jsonDocument = "{\"id\":\"" + id + "\", " +
                    "\"description\": \"" + name + "\"}";
            return request.createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(jsonDocument)
                    .build();
        }
    }

    @FunctionName("usersurvey") // change me
    public HttpResponseMessage userSurvey( // change me
                                           @HttpTrigger(
                                                   name = "req",
                                                   methods = {HttpMethod.GET},
                                                   authLevel = AuthorizationLevel.ANONYMOUS)
                                                   HttpRequestMessage<Optional<String>> request,
                                           final ExecutionContext context) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/handlebars", ".html");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("usersurvey"); // change me

        // TODO
        User person = new User();
        person.setName("test user");
        person.setId(1234);

        String templateString = template.apply(person);

        return request.createResponseBuilder(HttpStatus.OK)
                .body(templateString)
                .header("Content-Type", "text/html; charset=UTF-8")
                .build();
    }
}