package aero.icarus2020.services;

import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class tests that the apis are available to the application.
 * Each method runs only if the application is compiled with the command "-Dtest-groups=integration-tests"
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@IfProfileValue(name = "test-groups", values = {"integration-tests"})
public class AsyncServiceIntegrationTest {

    @Autowired
    private AsyncService asyncService;

    @Value("${icarus.api.authAPI}")
    private String authAPI;
    @Value("${icarus.api.adminAuthAPI}")
    private String adminAuthAPI;
    @Value("${icarus.api.countAPI}")
    private String countAPI;
    @Value("${icarus.api.allDataAssetsAPI}")
    private String allDataAssetsAPI;
    @Value("${icarus.api.userDataAssetsAPI}")
    private String userDatassetAPI;
    @Value("${icarus.api.allOrganizationAPI}")
    private String allOrganizationAPI;
    @Value("${icarus.api.socketsAPI}")
    private String socketsAPI;

    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() {
        testRestTemplate = new TestRestTemplate();
    }

    @Test
    public void getICARUSCountsTest() {
        ResponseEntity<String> response = testRestTemplate.
                getForEntity(countAPI, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getICARUSAllDataAssetTest() {
        ResponseEntity<String> response = testRestTemplate.
                getForEntity(allDataAssetsAPI, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getICARUSUserDataAssetTest() {
        ResponseEntity<String> response = testRestTemplate.
                getForEntity(userDatassetAPI, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void getICARUSAllOrganizationTest() {
        ResponseEntity<String> response = testRestTemplate.
                getForEntity(allOrganizationAPI, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getSocketConnectionsTest() {
        ResponseEntity<String> response = testRestTemplate.
                getForEntity(socketsAPI, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Ignore
    public void checkAdminAuthTest() {
        String token = "*";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "auth_token=" + token);
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
        ResponseEntity<String> response = testRestTemplate.exchange(adminAuthAPI, HttpMethod.GET, requestEntity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Ignore
    public void checkTokenTest() {
        String token = "*";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "auth_token=" + token);
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
        ResponseEntity<String> response = testRestTemplate.exchange(authAPI, HttpMethod.GET, requestEntity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}