package org.julius.quarkus.starting;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        externalDocs = @ExternalDocumentation(url = "https://github.com/Julius278/quarkus-rest-book-service"),
        info = @Info(
            title = "Books REST Microservice",
            description = "a microservice for books",
            contact = @Contact(name = "Julius", email = "quarkus@juliuslauterbach.de"),
            version = "1.0"
        )
)
public class BookMicroservice extends Application {
}
