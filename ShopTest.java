import static org.junit.Assert.*;
import org.junit.Test;

public class ShopTest {

    // add your tests here 
    // test whether the constructor correctly sets the memory and price attributes
    @Test
    public void testComputerConstructor() {
        Computer c = new Computer("Test", "Intel", 512, 8, "Windows", 2020, 1500);
        assertEquals("Description should match constructor input", "Test", c.description);
        assertEquals("ProcessorType should match constructor input", "Intel", c.processorType);
        assertEquals("HardDirveCapacity should match constructor input", 512, c.hardDriveCapacity);
        assertEquals("Memory should match constructor input", 8, c.memory);
        assertEquals("OperatingSystem should match constructor input", "Windows", c.operatingSystem);
        assertEquals("YearMade should match constructor input", 2020, c.yearMade);
        assertEquals("Price should match constructor input", 1500, c.price);
    }
    
    // test whether the setPrice method correctly updates the price attribute
    @Test
    public void testSetPrice() {
        Computer c = new Computer("Test", "Intel", 512, 8, "Windows", 2020, 1500);
        c.setPrice(2000);
        assertEquals("Price should be updated to 2000", 2000, c.price);
    }

    // test whether the setOS method correctly updates the operatingSystem attribute
    @Test
    public void testSetOS() {
        Computer c = new Computer("Test", "Intel", 512, 8, "Windows", 2020, 1500);
        c.setOS("newVersion");
        assertEquals("setOS should update the computer version to newOS","newVersion", c.operatingSystem);
    }

    // test whether getYear return the proper year of the computer
    @Test
    public void testGetYear(){
        Computer c = new Computer("Test", "Intel", 512, 8, "Windows", 2020, 1500);
        assertEquals("The returned number should be same as the yearMade of the computer", 2020, c.getYear());
    }

    // test whether toString gives proper string
    @Test
    public void testToString(){
        Computer c = new Computer("Test", "Intel", 512, 8, "Windows", 2020, 1500);
        String expected = "Test\nIntel\n512\n8\nWindows\n2020\n1500";
        assertEquals("The string should be based on the information of computer", expected, c.toString());
    }

    // test the constructors of resaleShop
    @Test
    public void testResaleShopConstructor() {
        ResaleShop shop = new ResaleShop();
        assertNotNull("Inventory should be initialized", shop.inventory);
        assertEquals("Inventory should contain 1 computer after constructor", 1, shop.inventory.size());
        Computer comp = shop.inventory.get(0);
        assertEquals("2019 MacBook Pro", comp.description);
        assertEquals("Intel", comp.processorType);
        assertEquals(256, comp.hardDriveCapacity);
        assertEquals(16, comp.memory);
        assertEquals("High Sierra", comp.operatingSystem);
        assertEquals(2019, comp.yearMade);
        assertEquals(1000, comp.price);
    }

    //test the buy method - if the buying compputer is already in the inventory
    @Test
    public void testBuy() {
        ResaleShop shop = new ResaleShop(); 
        Computer duplicateComp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2019, 1000);
        try {
            boolean alreadyExists = false;
            for (int i = 0; i < shop.inventory.size(); i++) {
                Computer comp = shop.inventory.get(i);
                if (comp.toString().equals(duplicateComp.toString())) { 
                    alreadyExists = true; 
                    break; 
                }
            }
        if (alreadyExists) {
            shop.buy(duplicateComp); 
            fail("Expected Exception for buying a duplicate computer, but none was thrown.");
        } else {
            shop.buy(duplicateComp);
            assertTrue("New computer should be added.", shop.inventory.contains(duplicateComp));
        }
        } catch (Exception e) {
            assertTrue("Exception message should indicate duplicate entry",
                e.getMessage().toLowerCase().contains("already") || e.getMessage().toLowerCase().contains("exist"));
        }
    }
    
    // test if there isn't computer matches the one which is selling
    @Test
    public void testSellNonExistingComputer() {
        ResaleShop shop = new ResaleShop();
        Computer fakeComp = new Computer("Fake Model", "AMD", 512, 8, "Linux", 2020, 800);
        try {
            boolean found = false;
            for (int i = 0; i < shop.inventory.size(); i++) {
                Computer comp = shop.inventory.get(i);
                if (comp.toString().equals(fakeComp.toString())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                shop.sell(fakeComp); 
                fail("Expected Exception when selling a computer not in inventory, but none was thrown.");
            } else {
                fail("Test setup issue: fake computer somehow exists in inventory!");
            }
        } catch (Exception e) {
            assertTrue("Exception message should indicate computer not found.",
                e.getMessage().toLowerCase().contains("not") ||
                e.getMessage().toLowerCase().contains("inventory"));
        }
    }
    
    //test whether the printInventory method prints the right content (or ampty)
    @Test
    public void testPrintInventory() {
        ResaleShop shop = new ResaleShop();
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        java.io.PrintStream originalOut = System.out;
        System.setOut(new java.io.PrintStream(outContent));
        //when inventory is empty
        shop.inventory.clear();
        shop.printInventory();
        System.out.flush();
        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Inventory is empty"));
    }
    
    // test if the reufrbish method gives the correct results and the refurbishing computer is in the inventory
    @Test
    public void testRefurbish(){      
        ResaleShop shop = new ResaleShop();
        boolean found = false;
        for (int i = 0; i < shop.inventory.size(); i++) {
            Computer comp = shop.inventory.get(i);
            if (comp.description.equals("2019 MacBook Pro")) {
                found = true;
                try {
                    shop.refurbish(comp, "macOS Monterey");
                    assertEquals("Price should be set to 1000 for 2019 model", 1000, comp.price);
                    assertEquals("Operating system should be updated", "macOS Monterey", comp.operatingSystem);
                } catch (Exception e) {
                    fail("No exception should be thrown for computer in inventory");
                }
            }
        }
        assertTrue("The 2019 MacBook Pro should exist in inventory", found);
        Computer otherComp = new Computer("2025MacBook Air", "M4", 256, 16, "macOS", 2025, 2500);
        try {
            shop.refurbish(otherComp, "macOS");
            fail("Expected exception for computer not in inventory");
        } catch (Exception e) {
            assertTrue("Exception message should indicate not in inventory",
                e.getMessage().toLowerCase().contains("not in inventory"));
        }
    }
}
