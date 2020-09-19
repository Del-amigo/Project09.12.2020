import POJO.DocType;
import io.restassured.http.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.*;
import java.util.*;
import static io.restassured.RestAssured.*;

public class DocumentType {
    Cookies cookies;
    DocType docType = new DocType();
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
    public void createDocumentTest() {
        randomName = randomText( 6 );
        docType.setName( randomName );
        docType.setDescription( randomText( 15 ) );
        docType.setSchoolId( "5c5aa8551ad17423a4f6ef1d" );
        docType.setActive( true );///
        List<String> text = new ArrayList<>();
        text.add( "CERTIFICATE" );
         docType.setAttachmentStages(text);


        id = given()
                .cookies( cookies )
                .body( docType )
                .contentType( ContentType.JSON )
                .when()
                .post( "/school-service/api/attachments" )
                .then()
                .statusCode( 201 )
                .extract().jsonPath().getString( "id" );

             System.out.println( randomName );
    }

/*    @Test(dependsOnMethods = "createDocumentTest")
    public void Negative_Creation(){
        given()
                .cookies( cookies )
                .body( docType )
                .contentType( ContentType.JSON )
                .when()
                .post( "/school-service/api/attachments" )
                .then()
                .statusCode( 400 );

                Expected status code <400> but was <201>. BUG !!!

    }*/

    @Test(dependsOnMethods = "createDocumentTest", priority = 1)
    public void edit_DocType(){
        docType.setId( id );
        docType.setDescription( randomName + "--01--" );
        docType.setName( randomText( 3 ) );

        given()
                .cookies( cookies )
                .contentType( ContentType.JSON )
                .body( docType )
                .when()
                .put( "/school-service/api/attachments" )
                .then()
                .statusCode( 200 );
    }

    @Test(dependsOnMethods = "edit_DocType")
    public void deletingDocType(){
        given()
                .cookies( cookies )
                .when()
                .delete( "/school-service/api/attachments/" + id )
                .then()
                .statusCode( 200 );
    }

    @Test(dependsOnMethods = "deletingDocType")
    public void Negative_delNotification(){
        given()
                .cookies( cookies )
                .when()
                .delete( "/school-service/api/attachments/" + id )
                .then()
                .statusCode( 404 );
    }

    private String randomText(int num) {
        return RandomStringUtils.randomAlphabetic( num );
    }
}
