import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ContactTest {
	private static final String INITIALIZER = "INITIAL";
	private static final String INITIALIZER_NUM = "1235559999";

	String contactId = "abc1234567";
	String firstName = "Joe";
	String lastName = "Williams";
	String phoneNumber = "1234567890";
	String address = "123, First Ave, NEW YORK.";

	@Test
	void testContact() {
		Contact c = new Contact();
		assertEquals(INITIALIZER, c.getContactId());
		assertEquals(INITIALIZER, c.getFirstName());
		assertEquals(INITIALIZER, c.getLastName());
		assertEquals(INITIALIZER_NUM, c.getPhoneNumber());
		assertEquals(INITIALIZER, c.getAddress());
	}

	@Test
	void testContactString() {

		Contact c = new Contact(contactId);
		assertEquals(contactId, c.getContactId());
		assertEquals(INITIALIZER, c.getFirstName());
		assertEquals(INITIALIZER, c.getLastName());
		assertEquals(INITIALIZER_NUM, c.getPhoneNumber());
		assertEquals(INITIALIZER, c.getAddress());
	}

	@Test
	void testContactStringString() {

		Contact c = new Contact(contactId, firstName);
		assertEquals(contactId, c.getContactId());
		assertEquals(firstName, c.getFirstName());
		assertEquals(INITIALIZER, c.getLastName());
		assertEquals(INITIALIZER_NUM, c.getPhoneNumber());
		assertEquals(INITIALIZER, c.getAddress());
	}

	@Test
	void testContactStringStringString() {

		Contact c = new Contact(contactId, firstName, lastName);
		assertEquals(contactId, c.getContactId());
		assertEquals(firstName, c.getFirstName());
		assertEquals(lastName, c.getLastName());
		assertEquals(INITIALIZER_NUM, c.getPhoneNumber());
		assertEquals(INITIALIZER, c.getAddress());
	}

	@Test
	void testContactStringStringStringString() {

		Contact c = new Contact(contactId, firstName, lastName, phoneNumber);
		assertEquals(contactId, c.getContactId());
		assertEquals(firstName, c.getFirstName());
		assertEquals(lastName, c.getLastName());
		assertEquals(phoneNumber, c.getPhoneNumber());
		assertEquals(INITIALIZER, c.getAddress());
	}

	@Test
	void testContactStringStringStringStringString() {

		Contact c = new Contact(contactId, firstName, lastName, phoneNumber, address);
		assertEquals(contactId, c.getContactId());
		assertEquals(firstName, c.getFirstName());
		assertEquals(lastName, c.getLastName());
		assertEquals(phoneNumber, c.getPhoneNumber());
		assertEquals(address, c.getAddress());
	}

	@Test
	void testContactStringStringStringStringString_invalidContactId() {
		String contactId = "1234567890123";

		assertThrows(IllegalArgumentException.class,
				() -> new Contact(contactId, firstName, lastName, phoneNumber, address));
	}

	@Test
	void testContactStringStringStringStringString_invalidFirstName() {
		String firstName = "Joe more letters longer";
		assertThrows(IllegalArgumentException.class,
				() -> new Contact(contactId, firstName, lastName, phoneNumber, address));
	}

	@Test
	void testContactStringStringStringStringString_invalidLastName() {
		String lastName = "Williams long last name";

		assertThrows(IllegalArgumentException.class,
				() -> new Contact(contactId, firstName, lastName, phoneNumber, address));
	}

	@Test
	void testContactStringStringStringStringString_invalidPhoneNumber() {
		String phoneNumber = "99alphaletters";

		assertThrows(IllegalArgumentException.class,
				() -> new Contact(contactId, firstName, lastName, phoneNumber, address));
	}

	@Test
	void testContactStringStringStringStringString_invalidAddress() {
		String address = "123, First Ave, NEW YORK. Such a long address. Should fail.";

		assertThrows(IllegalArgumentException.class,
				() -> new Contact(contactId, firstName, lastName, phoneNumber, address));
	}

//	@Test
//	void testGetContactId() {
//		
//	}
//
//	@Test
//	void testGetFirstName() {
//		
//	}
//
//	@Test
//	void testGetLastName() {
//		
//	}
//
//	@Test
//	void testGetPhoneNumber() {
//		
//	}
//
//	@Test
//	void testGetAddress() {
//		
//	}
//
	@Test
	void testUpdateFirstName() {
		String contactId = "abc1234567";
		String firstName = "Joe";
		String lastName = "Williams";
		String phoneNumber = "1234567890";
		String address = "123, First Ave, NEW YORK.";

		Contact c = new Contact(contactId, firstName, lastName, phoneNumber, address);
		assertThrows(IllegalArgumentException.class, () -> c.updateFirstName("Joe more letters longer"));
	}

	@Test
	void testUpdateLastName() {
		String contactId = "abc1234567";
		String firstName = "Joe";
		String lastName = "Williams";
		String phoneNumber = "1234567890";
		String address = "123, First Ave, NEW YORK.";

		Contact c = new Contact(contactId, firstName, lastName, phoneNumber, address);
		assertThrows(IllegalArgumentException.class, () -> c.updateLastName("Last name more letters longer"));

	}

	@Test
	void testUpdatePhoneNumber() {
		String contactId = "abc1234567";
		String firstName = "Joe";
		String lastName = "Williams";
		String phoneNumber = "1234567890";
		String address = "123, First Ave, NEW YORK.";

		Contact c = new Contact(contactId, firstName, lastName, phoneNumber, address);
		assertThrows(IllegalArgumentException.class, () -> c.updatePhoneNumber("Non numeric"));

	}

	@Test
	void testUpdateAddress() {
		String contactId = "abc1234567";
		String firstName = "Joe";
		String lastName = "Williams";
		String phoneNumber = "1234567890";
		String address = "123, First Ave, NEW YORK.";

		Contact c = new Contact(contactId, firstName, lastName, phoneNumber, address);
		assertThrows(IllegalArgumentException.class,
				() -> c.updateAddress("123, First Ave, NEW YORK. Such a long address. Should fail."));

	}

	@Test
	void testUpdateContactId() {
		String contactId = "abc1234567";
		String firstName = "Joe";
		String lastName = "Williams";
		String phoneNumber = "1234567890";
		String address = "123, First Ave, NEW YORK.";

		Contact c = new Contact(contactId, firstName, lastName, phoneNumber, address);
		assertThrows(IllegalArgumentException.class, () -> c.updateContactId("123abc1234567"));

	}

}
