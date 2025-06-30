// ContactServiceTest.java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {
    private ContactService contactService;
    
    @BeforeEach
    void setUp() {
        contactService = new ContactService();
    }
    
    @Test
    void testAddContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        assertEquals(contact, contactService.getContact("1234567890"));
    }
    
    @Test
    void testAddDuplicateContact() {
        Contact contact1 = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        Contact contact2 = new Contact("1234567890", "Jane", "Doe", "0987654321", "456 Oak St");
        contactService.addContact(contact1);
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(contact2);
        });
    }
    
    @Test
    void testDeleteContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.deleteContact("1234567890");
        assertNull(contactService.getContact("1234567890"));
    }
    
    @Test
    void testUpdateContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.updateContact("1234567890", "Jane", "Smith", "0987654321", "456 Oak St");
        
        Contact updated = contactService.getContact("1234567890");
        assertEquals("Jane", updated.getFirstName());
        assertEquals("Smith", updated.getLastName());
        assertEquals("0987654321", updated.getPhone());
        assertEquals("456 Oak St", updated.getAddress());
    }
}