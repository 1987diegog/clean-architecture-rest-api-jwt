package uy.com.demente.ideas;

import uy.com.demente.ideas.user.domain.UsuarioClient;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConfigTestRestController {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Value("${unitTest.url}")
    protected String rootUrl;

    @Value("${server.servlet.context-path}")
    protected String contextPath;

    @LocalServerPort
    public int port;

    @MockBean
    protected UsuarioClient userClient;

    protected String getRootUrlWithPort() {
        return rootUrl + ":" + port + "/" + contextPath;
    }
}

