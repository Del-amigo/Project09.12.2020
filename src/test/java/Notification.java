import POJO.Notificat;
import io.restassured.http.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.*;
import java.util.*;
import static io.restassured.RestAssured.*;

public class Notification {
    Notificat notificat = new Notificat();
    Cookies cookies;
    private String id;
    private String randomName;

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
    public void CreateNotification() {
        randomName = randomText( 5 );
        notificat.setName( randomName );
        notificat.setDescription( randomText( 10 ) );
        notificat.setType( "STUDENT_PAYMENT_TIME" );
        notificat.setSchoolId( "5c5aa8551ad17423a4f6ef1d" );

        id = given()
                .cookies( cookies )
                .body( notificat )
                .contentType( ContentType.JSON )
                .when()
                .post( "/school-service/api/notifications" )
                .then()
                .statusCode( 201 )
                .extract().jsonPath().getString( "id" );

        System.out.println( randomName );
    }

    @Test(dependsOnMethods = "CreateNotification")
    public void Negative_Creation(){
        given()
                .cookies( cookies )
                .body( notificat )
                .contentType( ContentType.JSON )
                .when()
                .post( "/school-service/api/notifications" )
                .then()
                .statusCode( 400 );
    }

    @Test(dependsOnMethods = "CreateNotification", priority = 1)
    public void edit_Notification(){
        notificat.setId( id );
        notificat.setDescription( randomName + "--01--" );
        notificat.setName( randomText( 3 ) );

        given()
                .cookies( cookies )
                .contentType( ContentType.JSON )
                .body( notificat )
                .when()
                .put( "/school-service/api/notifications" )
                .then()
                .statusCode( 200 );
    }

    @Test(dependsOnMethods = "edit_Notification")
    public void deletingNotification(){
        given()
                .cookies( cookies )
                .when()
                .delete( "/school-service/api/notifications/" + id )
                .then()
                .statusCode( 200 );
    }

    @Test(dependsOnMethods = "deletingNotification")
    public void Negative_delNotification(){
        given()
                .cookies( cookies )
                .when()
                .delete( "/school-service/api/notifications/" + id )
                .then()
                .statusCode( 404 );
    }

    private String randomText(int num) {
        return RandomStringUtils.randomAlphabetic( num );
    }
}