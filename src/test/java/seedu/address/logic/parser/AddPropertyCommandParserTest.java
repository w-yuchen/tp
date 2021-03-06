package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_POSTAL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.POSTAL_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSTAL_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_MAYFAIR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalProperties.MAYFAIR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.testutil.PropertyBuilder;

public class AddPropertyCommandParserTest {

    private AddPropertyCommandParser parser = new AddPropertyCommandParser();

    @Test
    public void parse_optionalFieldsMissing_success() {
        Property expectedProperty = new PropertyBuilder(MAYFAIR).build();
        assertParseSuccess(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                        + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR, new AddPropertyCommand(expectedProperty));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR + POSTAL_DESC_MAYFAIR
                + DEADLINE_DESC_MAYFAIR, expectedMessage);

        // missing type prefix
        assertParseFailure(parser, NAME_DESC_MAYFAIR + VALID_TYPE_MAYFAIR + ADDRESS_DESC_MAYFAIR + POSTAL_DESC_MAYFAIR
                + DEADLINE_DESC_MAYFAIR, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + VALID_ADDRESS_MAYFAIR + POSTAL_DESC_MAYFAIR
                + DEADLINE_DESC_MAYFAIR, expectedMessage);

        // missing postal prefix
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR + VALID_POSTAL_MAYFAIR
                + DEADLINE_DESC_MAYFAIR, expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR + POSTAL_DESC_MAYFAIR
                + VALID_DEADLINE_MAYFAIR, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_MAYFAIR + VALID_TYPE_MAYFAIR + VALID_ADDRESS_MAYFAIR
                        + VALID_POSTAL_MAYFAIR + VALID_DEADLINE_MAYFAIR, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_PROPERTY_NAME_DESC + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR, Name.MESSAGE_CONSTRAINTS);

        // invalid type
        assertParseFailure(parser, NAME_DESC_MAYFAIR + INVALID_PROPERTY_TYPE_DESC + ADDRESS_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR, Type.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + INVALID_PROPERTY_ADDRESS_DESC
                + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR, Address.MESSAGE_CONSTRAINTS);

        // invalid postal
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + INVALID_PROPERTY_POSTAL_DESC + DEADLINE_DESC_MAYFAIR, PostalCode.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + INVALID_PROPERTY_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_PROPERTY_NAME_DESC + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                        + INVALID_PROPERTY_POSTAL_DESC + DEADLINE_DESC_MAYFAIR, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR
                + ADDRESS_DESC_MAYFAIR + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));
    }
}
