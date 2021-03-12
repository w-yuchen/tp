package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.MEET_ALEX;
import static seedu.address.testutil.TypicalAppointments.MEET_BOB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.NameContainsKeywordsPredicate;
import seedu.address.testutil.AppointmentBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        //assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
        assertEquals(new AppointmentBook(), new AppointmentBook(modelManager.getAppointmentBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        //userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setAppointmentBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        //userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        userPrefs.setAppointmentBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAppointmentBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAppointmentBookFilePath(null));
    }

    @Test
    public void setAppointmentBookFilePath_validPath_setsAppointmentBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAppointmentBookFilePath(path);
        assertEquals(path, modelManager.getAppointmentBookFilePath());
    }

    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAppointment(null));
    }

    @Test
    public void hasAppointment_appointmentNotInAppointmentBook_returnsFalse() {
        assertFalse(modelManager.hasAppointment(MEET_ALEX));
    }

    @Test
    public void hasAppointment_appointmentInAppointmentBook_returnsTrue() {
        modelManager.addAppointment(MEET_ALEX);
        assertTrue(modelManager.hasAppointment(MEET_ALEX));
    }

    @Test
    public void getFilteredAppointmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAppointmentList().remove(0));
    }

    @Test
    public void equals() {
        /*AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();*/
        AppointmentBook appointmentBook = new AppointmentBookBuilder().withAppointment(MEET_ALEX)
                .withAppointment(MEET_BOB).build();
        AppointmentBook differentAppointmentBook = new AppointmentBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(appointmentBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(appointmentBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different appointmentBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAppointmentBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = MEET_ALEX.getName().name.split("\\s+");
        modelManager.updateFilteredAppointmentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(appointmentBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAppointmentBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(appointmentBook, differentUserPrefs)));
    }
}
