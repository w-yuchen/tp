package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_MEET_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_REMARK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_MEET_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_MEET_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MEET_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MEET_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_MEET_ALEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAppointments.MEET_ALEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.name.Name;
import seedu.address.model.remark.Remark;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandParserTest {

    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_optionalFieldsMissing_success() {
        Appointment expectedAppointment = new AppointmentBuilder(MEET_ALEX).build();
        assertParseSuccess(parser, NAME_DESC_MEET_ALEX + REMARK_DESC_MEET_ALEX + DATE_DESC_MEET_ALEX,
                new AddAppointmentCommand(expectedAppointment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_MEET_ALEX + REMARK_DESC_MEET_ALEX + DATE_DESC_MEET_ALEX,
                expectedMessage);

        // missing remark prefix
        assertParseFailure(parser, NAME_DESC_MEET_ALEX + VALID_REMARK_MEET_ALEX + DATE_DESC_MEET_ALEX,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_MEET_ALEX + REMARK_DESC_MEET_ALEX + VALID_DATE_MEET_ALEX,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_MEET_ALEX + VALID_REMARK_MEET_ALEX + VALID_DATE_MEET_ALEX,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_APPOINTMENT_NAME_DESC + REMARK_DESC_MEET_ALEX + DATE_DESC_MEET_ALEX,
                Name.MESSAGE_CONSTRAINTS);

        // invalid remark
        assertParseFailure(parser, NAME_DESC_MEET_ALEX + INVALID_APPOINTMENT_REMARK_DESC + DATE_DESC_MEET_ALEX,
                Remark.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_MEET_ALEX + REMARK_DESC_MEET_ALEX + INVALID_APPOINTMENT_DATE_DESC,
                Date.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_APPOINTMENT_NAME_DESC + REMARK_DESC_MEET_ALEX
                + INVALID_APPOINTMENT_DATE_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_MEET_ALEX
                + REMARK_DESC_MEET_ALEX + DATE_DESC_MEET_ALEX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
    }
}
