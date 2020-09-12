import POJO.Discounts;
import io.restassured.http.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.*;
import java.util.*;
import static io.restassured.RestAssured.*;

public class Task01 {
    Discounts discounts = new Discounts();
    private Cookies cookies;
    private String id;
    private String randomDescription;

    @BeforeClass
    public void initialization() {
        baseURI = "https://test.basqar.techno.study";

        Map<String, String> credentials = new HashMap<>();
        credentials.put( "username", "daulet2030@gmail.com" );
        credentials.put( "password", "TechnoStudy123@" );

        cookies = given()
                .contentType( ContentType.JSON )
                .body( credentials )
                .when()
                .post( "/auth/login" )
                .then()
                .statusCode( 200 )
                .extract().response().detailedCookies();
    }

    @Test
    public void createDiscountTest() {
        randomDescription = randomText( 10 );
        discounts.setCode( randomText( 3 ) );
        discounts.setDescription( randomDescription );
        discounts.setPriority( 10 );
        discounts.setActive( true );

        id = given()
                .cookies( cookies )
                .body( discounts )
                .contentType( ContentType.JSON )
                .when()
                .post( "/school-service/api/discounts" )
                .then()
                .statusCode( 201 )
                .extract().jsonPath().getString( "id" );

        System.out.println( randomDescription );
    }

    @Test(dependsOnMethods = "createDiscountTest")
    public void negative_CreateDiscountTest() {

        given()
                .cookies( cookies )
                .body( discounts )
                .contentType( ContentType.JSON )
                .when()
                .post( "/school-service/api/discounts" )
                .then()
                .statusCode( 400 );
    }

    @Test(dependsOnMethods = "createDiscountTest", priority = 1)
    public void editingDiscountTest() {
        discounts.setId( id );
        discounts.setDescription( randomDescription + "--01--" );
        discounts.setCode( randomText( 3 ) );

        given()
                .cookies( cookies )
                .contentType( ContentType.JSON )
                .body( discounts )
                .when()
                .put( "/school-service/api/discounts" )
                .then()
                .statusCode( 200 );
    }

    @Test(dependsOnMethods = "editingDiscountTest")
    public void deletingDiscountTest() {
        given()
                .cookies( cookies )
                .when()
                .delete( "/school-service/api/discounts/" + id )
                .then()
                .statusCode( 200 )
        ;
    }

    @Test(dependsOnMethods = "deletingDiscountTest")
    public void negative_DeletingDiscountTest() {
        given()
                .cookies( cookies )
                .when()
                .delete( "/school-service/api/discounts/" + id )
                .then()
                .statusCode( 404 )
        ;
    }

    private String randomText(int num) {
        return RandomStringUtils.randomAlphabetic( num );
    }
}
