package net.usefulbits;

import org.junit.Ignore;
import static org.junit.Assert.*;
import org.junit.Test;

public class PersonTest {

    private static String TEAMCITY_VERSION = "TEAMCITY_VERSION";

    @Test
    public void Test1() {
        System.out.println("##teamcity[blockOpened name='<CreateConnection>']");
        System.out.println("##teamcity[blockClosed name='<CreateConnection>']");
        
        System.out.println("##teamcity[blockClosed name='<RiskReasonsTests.DeleteRiskReason>']");
        System.out.println("##teamcity[blockOpened name='<UserRolesTests.BulkImportUsersFromFile>']");
        System.out.println("##teamcity[blockOpened name='<ImportUsersFromFile>']");
       
        System.out.println("##teamcity[blockClosed name='some_block']");


        System.out.println("word word");
        System.err.println("word");

        System.out.println("http://user:word@jetbrains.com");
        System.err.println("http://user:word@jetbrains.com");

        System.out.println("##teamcity[blockOpened name='some_block_2']");
        System.out.println("Durchführungvermögen🫥🎃"); 

        assertEquals(3, 3);

        assertEquals("Name not saved correctly", "David", "David😦");
        assertEquals("Age not saved correctly 123456", 22, 22);
        System.out.println("##teamcity[blockClosed name='some_block_3']");
    }

    @Test
    public void TestFails() {
        System.out.println("Compare 3 and 5");
        assertEquals(3, 5);
    }

    @Test
    public void TestFailsWithMinus() {
        System.out.println("Compare -3 and -5");
        assertEquals(-3, -5);
    }

    @Test
    public void TestBlock() {

        System.out.println("##teamcity[blockOpened name='<Login>']");

        System.out.println("Login with User: Administrator");
        System.out.println("Logged in as: Name: 'test'");
        System.out.println("##teamcity[blockClosed name='<Login>']");

        System.out.println("##teamcity[blockOpened name='<CancelReviewInProgressIfAny>']");

        System.out.println("##teamcity[blockClosed name='<CancelReviewInProgressIfAny>']");

        System.out.println("##teamcity[blockOpened name='<CreateConnection>']");

        System.out.println("Logged in as: Name: 'test'");
        System.out.println("##teamcity[blockClosed name='<CreateConnection>']");

        System.out.println("##teamcity[blockClosed name='<UserRolesTests.CreateAndDeleteUser>']");

        System.out.println("##teamcity[blockOpened name='<UserRolesTests.CreateAndDeleteUserRole>']");

        System.out.println("##teamcity[blockOpened name='<Login>']");

        System.out.println("Login with User: test");
        System.out.println("Logged in as 💩: Name: 'administrator'");
        System.out.println("##teamcity[blockClosed name='<Login>']");

        System.out.println("##teamcity[blockOpened name='<CreateNewUserRole>']");

        System.out.println("##teamcity[blockClosed name='<CreateNewUserRole>']");


        try{
            if (System.getenv(TEAMCITY_VERSION) != null || !System.getenv("TEAMCITY_VERSION").isEmpty()){
                Thread.sleep(100);
            }
        } catch (InterruptedException ie){
            System.out.println("Something went wrong");
        }
        System.out.println("##teamcity[blockClosed name='some_block']");


        System.out.println("word 👽");
        System.err.println("word 🤖");

        System.out.println("http://user:word@jetbrains.com");
        System.err.println("http://user:word@jetbrains.com");

        System.out.println("##teamcity[blockOpened name='some_block_2']");


        assertEquals(2, 2);

        assertEquals("Name not saved correctly", "David", "David");
        assertEquals("Age not saved correctly 123456", 22, 22);
        System.out.println("##teamcity[blockClosed name='some_block_3']");
    }

    @Test
    public void TestDifferentLogic() {

        try{
            if (System.getenv(TEAMCITY_VERSION) != null || !System.getenv("TEAMCITY_VERSION").isEmpty()){
                Thread.sleep(100);
            }
        } catch (InterruptedException ie){
            System.out.println("Something went wrong");
        }
        System.out.println("##teamcity[blockClosed name='some_block']");


        System.out.println("word");
        System.err.println("word");

        System.out.println("http://user:word@jetbrains.com🦷");
        System.err.println("http://user:word@jetbrains.com");

        System.out.println("##teamcity[blockOpened name='some_block_2']");


        assertEquals(4, 4);


        assertEquals("Name not saved correctly", "David", "David");
        assertEquals("Age not saved correctly 123456", 22, 22);
        System.out.println("##teamcity[blockClosed name='some_block_3']");
    }

    @Test
    public void TestCalculator() {
        try{
            if (System.getenv(TEAMCITY_VERSION) != null || !System.getenv("TEAMCITY_VERSION").isEmpty()){
                Thread.sleep(10000);
            }
        } catch (InterruptedException ie){
            System.out.println("Something went wrong");
        }
        System.out.println("##teamcity[blockClosed name='some_block']");

        System.out.println("##teamcity[blockOpened name='some_block_2']");


        assertEquals(2, 2);

        assertEquals("Name not saved correctly", "David", "David");
        assertEquals("Age not saved correctly 123456", 22, 22);
        System.out.println("##teamcity[blockClosed name='some_block_3']");
    }

    @Test
    public void TestBackup() {
        System.out.println("##teamcity[blockOpened name='some_block_2']");

        assertEquals(2, 2);

        assertEquals("Name not saved correctly", "David", "David");
        assertEquals("Age not saved correctly 123456", 22, 22);
        System.out.println("##teamcity[blockClosed name='some_block_3']");
    }

    @Test
    //@Ignore("long name u")
    public void Test2() {
        System.out.println("##teamcity[blockOpened name='some_block']");

        assertEquals("David", "David");
        assertEquals(28, 28);
    }

    @Test
    @Ignore("long name a")
    public void Test3() {
        System.out.println("##teamcity[blockClosed name='some_block']");

        assertEquals("Deleting person failed.", 1, 1);
    }

    @Test
    public void Test4(){
        assertFalse(false);
    }
    
    @Test
    public void OneMoreTest(){
        System.out.println("##teamcity[blockOpened name='some_block']");
        
        assertTrue(true);
    }

    @Test
    @Ignore("ignore test with short name")
    public void shortTestName(){
        System.out.println("##teamcity[blockOpened name='some_block']");
        
        assertFalse (true);
    }

    @Test
    //@Ignore("ignore test with simple name")
    public void shouldBeIgnored(){
        assertTrue(false);
    }
    
   
    @Test
    @Ignore("ignore test with simple name")
    public void shouldBeIgnored2(){
        assertTrue(false);
    }
}
