package org.julius.quarkus.starting;

import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.julius.quarkus.starting.numbers.IsbnThirteen;
import org.julius.quarkus.starting.numbers.NumberProxy;

@Mock
@RestClient
public class MockNumberProxy implements NumberProxy {
    @Override
    public IsbnThirteen generateRandomIsbnThirteen() {
        return new IsbnThirteen("13-mock");
    }
}
