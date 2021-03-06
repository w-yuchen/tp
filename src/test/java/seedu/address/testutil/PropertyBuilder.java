package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;

/**
 * A utility class to help with building Property objects.
 */
public class PropertyBuilder {

    public static final String DEFAULT_NAME = "Mayfair";
    public static final String DEFAULT_TYPE = "condo";
    public static final String DEFAULT_ADDRESS = "1 Jurong East Street 32, #08-111";
    public static final String DEFAULT_POSTAL = "609477";
    public static final LocalDate DEFAULT_DEADLINE = LocalDate.parse("2021-12-31");

    private Name name;
    private Type type;
    private Address address;
    private PostalCode postal;
    private Deadline deadline;

    /**
     * Creates a {@code PropertyBuilder} with the default details.
     */
    public PropertyBuilder() {
        name = new Name(DEFAULT_NAME);
        type = new Type(DEFAULT_TYPE);
        address = new Address(DEFAULT_ADDRESS);
        postal = new PostalCode(DEFAULT_POSTAL);
        deadline = new Deadline(DEFAULT_DEADLINE);
    }

    /**
     * Initializes the PropertyBuilder with the data of {@code propertyToCopy}.
     */
    public PropertyBuilder(Property propertyToCopy) {
        name = propertyToCopy.getName();
        type = propertyToCopy.getPropertyType();
        address = propertyToCopy.getAddress();
        postal = propertyToCopy.getPostalCode();
        deadline = propertyToCopy.getDeadline();
    }

    /**
     * Sets the {@code Name} of the {@code Property} that we are building.
     */
    public PropertyBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code Property} that we are building.
     */
    public PropertyBuilder withType(String type) {
        this.type = new Type(type);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Property} that we are building.
     */
    public PropertyBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code PostalCode} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPostal(String postal) {
        this.postal = new PostalCode(postal);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Property} that we are building.
     */
    public PropertyBuilder withDeadline(LocalDate deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    public Property build() {
        return new Property(name, type, address, postal, deadline);
    }
}
