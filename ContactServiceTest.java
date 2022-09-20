import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ContactServiceTest {

	private static final int CONTACT_PHONENUM_LENGTH = 10;
	private static final byte CONTACT_ID_LENGTH = 10;
	private static final byte CONTACT_FNAME_LENGTH = 10;
	private static final byte CONTACT_LNAME_LENGTH = 10;
	private static final byte CONTACT_ADDRESS_LENGTH = 30;
	private static final String INITIALIZER = "INITIAL";
	private static final String INITIALIZER_NUM = "1235559999";

	String contactId = "abc1234567";
	String firstName = "Joe";
	String lastName = "Williams";
	String phoneNumber = "1234567890";
	String address = "123, First Ave, NEW YORK.";

	@Test
	void testNewContact() {
		ContactService cs = new ContactService();
		cs.newContact();

		Contact c = cs.getContactList().get(0);

		assertEquals(INITIALIZER, c.getFirstName());
		assertEquals(INITIALIZER, c.getLastName());
		assertEquals(INITIALIZER_NUM, c.getPhoneNumber());
		assertEquals(INITIALIZER, c.getAddress());
	}

	@Test
	void testNewContactString() {
		ContactService cs = new ContactService();
		cs.newContact(firstName);

		Contact c = cs.getContactList().get(0);

		assertEquals(firstName, c.getFirstName());
		assertEquals(INITIALIZER, c.getLastName());
		assertEquals(INITIALIZER_NUM, c.getPhoneNumber());
		assertEquals(INITIALIZER, c.getAddress());
	}

	@Test
	void testNewContactStringString() {
		ContactService cs = new ContactService();
		cs.newContact(firstName, lastName);

		Contact c = cs.getContactList().get(0);

		assertEquals(firstName, c.getFirstName());
		assertEquals(lastName, c.getLastName());
		assertEquals(INITIALIZER_NUM, c.getPhoneNumber());
		assertEquals(INITIALIZER, c.getAddress());
	}

	@Test
	void testNewContactStringStringString() {
		ContactService cs = new ContactService();
		cs.newContact(firstName, lastName, phoneNumber);

		Contact c = cs.getContactList().get(0);

		assertEquals(firstName, c.getFirstName());
		assertEquals(lastName, c.getLastName());
		assertEquals(phoneNumber, c.getPhoneNumber());
		assertEquals(INITIALIZER, c.getAddress());
	}

	@Test
	void testNewContactStringStringStringString() {

		ContactService cs = new ContactService();
		cs.newContact(firstName, lastName, phoneNumber, address);

		Contact c = cs.getContactList().get(0);

		assertEquals(firstName, c.getFirstName());
		assertEquals(lastName, c.getLastName());
		assertEquals(phoneNumber, c.getPhoneNumber());
		assertEquals(address, c.getAddress());
	}

	@Test
	void testDeleteContact() {
		ContactService cs = new ContactService();
		cs.newContact(firstName, lastName, phoneNumber, address);
		cs.newContact(firstName + "X", lastName + "X", phoneNumber, address + "X");

		assertEquals(2, cs.getContactList().size());

		Contact c = cs.getContactList().get(0);
		assertNotNull(c);

		try {
			cs.deleteContact(c.getContactId());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(1, cs.getContactList().size());
		Contact c2 = cs.getContactList().get(0);
		assertNotNull(c2);
		assertEquals(firstName + "X", c2.getFirstName());
	}

	@Test
	void testUpdateFirstName() {
		ContactService cs = new ContactService();
		cs.newContact(firstName, lastName, phoneNumber, address);

		Contact c = cs.getContactList().get(0);
		assertEquals(firstName, c.getFirstName());

		// check error message when firstName is null
		Exception except_null = assertThrows(IllegalArgumentException.class, () -> {
			cs.updateFirstName(c.getContactId(), null);
		});
		assertEquals("First name cannot be empty", except_null.getMessage());

		// check error message when length of firstName is longer than 10
		Exception except_longer = assertThrows(IllegalArgumentException.class, () -> {
			cs.updateFirstName(c.getContactId(), "longer first name.");
		});
		assertEquals("First name cannot be longer than " + CONTACT_FNAME_LENGTH + " characters",
				except_longer.getMessage());

		try {
			cs.updateFirstName(c.getContactId(), firstName + "X");
		} catch (Exception e) {
			fail(e.getMessage());
		}

		Contact c2 = cs.getContactList().get(0);
		assertEquals(firstName + "X", c2.getFirstName());

	}

	@Test
	void testUpdateLastName() {
		ContactService cs = new ContactService();
		cs.newContact(firstName, lastName, phoneNumber, address);

		Contact c = cs.getContactList().get(0);
		assertEquals(lastName, c.getLastName());

		try {
			cs.updateLastName(c.getContactId(), lastName + "X");
		} catch (Exception e) {
			fail(e.getMessage());
		}

		Contact c2 = cs.getContactList().get(0);
		assertEquals(lastName + "X", c2.getLastName());
	}

	@Test
	void testUpdatePhoneNumber() {
		ContactService cs = new ContactService();
		cs.newContact(firstName, lastName, phoneNumber, address);

		Contact c = cs.getContactList().get(0);
		assertEquals(phoneNumber, c.getPhoneNumber());

		// check error message when phone is null
		Exception except_null = assertThrows(IllegalArgumentException.class, () -> {
			cs.updatePhoneNumber(c.getContactId(), null);
		});
		assertEquals("Phone number cannot be empty.", except_null.getMessage());

		// check error message when length of phone is longer than 10
		Exception except_longer = assertThrows(IllegalArgumentException.class, () -> {
			cs.updatePhoneNumber(c.getContactId(), "12345678901");
			new Contact("");
		});
		assertEquals("Phone number length invalid. Ensure it is " + CONTACT_PHONENUM_LENGTH + " digits.",
				except_longer.getMessage());

		// check error message when phone has non numeric characters.
		Exception except_nonNumeric = assertThrows(IllegalArgumentException.class, () -> {
			c.updatePhoneNumber("1234567abc");
			new Contact("");
		});
		assertEquals("Phone number cannot have anything but numbers", except_nonNumeric.getMessage());

		try {
			cs.updatePhoneNumber(c.getContactId(), "1122334455");
		} catch (Exception e) {
			fail(e.getMessage());
		}

		Contact c2 = cs.getContactList().get(0);
		assertEquals("1122334455", c2.getPhoneNumber());
	}

	@Test
	void testUpdateAddress() {
		ContactService cs = new ContactService();
		cs.newContact(firstName, lastName, phoneNumber, address);

		Contact c = cs.getContactList().get(0);
		assertEquals(address, c.getAddress());

		// check error message when address is null
		Exception except_null = assertThrows(IllegalArgumentException.class, () -> {
			cs.updateAddress(c.getContactId(), null);
		});
		assertEquals("Address cannot be empty", except_null.getMessage());

		// check error message when length of address is longer than 50
		Exception except_longer = assertThrows(IllegalArgumentException.class, () -> {
			cs.updateAddress(c.getContactId(), "very long address and this is invalid. this must throw exception.");
		});
		assertEquals("Address cannot be longer than " + CONTACT_ADDRESS_LENGTH + " characters",
				except_longer.getMessage());

		try {
			cs.updateAddress(c.getContactId(), address + "X");
		} catch (Exception e) {
			fail(e.getMessage());
		}

		Contact c2 = cs.getContactList().get(0);
		assertEquals(address + "X", c2.getAddress());
	}

	@Test
	void testGetContactList() {
		ContactService cs = new ContactService();
		cs.newContact(firstName, lastName, phoneNumber, address);
		cs.newContact(firstName + "X", lastName + "X", phoneNumber, address + "X");

		assertEquals(2, cs.getContactList().size());

		Contact c = cs.getContactList().get(0);
		assertNotNull(c);

		try {
			cs.deleteContact(c.getContactId());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(1, cs.getContactList().size());
		Contact c2 = cs.getContactList().get(0);
		assertNotNull(c2);
		assertEquals(firstName + "X", c2.getFirstName());

		try {
			cs.deleteContact(c2.getContactId());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(0, cs.getContactList().size());

	}

}
