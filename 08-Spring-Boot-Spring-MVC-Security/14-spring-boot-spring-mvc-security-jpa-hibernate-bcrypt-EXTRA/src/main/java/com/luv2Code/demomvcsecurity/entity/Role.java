// Define the package for the entity class
package com.luv2Code.demomvcsecurity.entity;

// Import JPA annotations for entity and table mapping
import jakarta.persistence.*;

/**
 * Represents a Role entity that maps to the "role" table in the database.
 * <p>
 * This class defines the structure of the Role entity and its relationship
 * with the corresponding database table. Each field in the class maps to a
 * column in the database table.
 */
@Entity
@Table(name = "role") // Maps this entity to the "role" table in the database
public class Role {

    /**
     * The primary key of the Role entity.
     * <p>
     * This field is annotated with @Id to indicate it is the primary key.
     * The @GeneratedValue annotation with strategy AUTO allows the database
     * or JPA provider to automatically generate the ID value.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically generates the ID
    @Column(name = "id") // Maps this field to the "id" column in the "role" table
    private long id;

    /**
     * Represents the name of the role.
     * <p>
     * This field is mapped to the "name" column in the database table and
     * holds the name of the role, such as "ADMIN," "MANAGER," or "EMPLOYEE."
     */
    @Column(name = "name") // Maps this field to the "name" column
    private String name;

    /**
     * Default constructor for the Role entity.
     * <p>
     * A no-argument constructor is required by JPA for creating instances of the entity.
     */
    public Role() {
        // Default constructor for JPA
    }

    /**
     * Parameterized constructor for creating a Role entity with a specific name.
     *
     * @param name The name of the role to be created.
     */
    public Role(String name) {
        this.name = name;
    }

    /**
     * Getter for the ID field.
     *
     * @return The ID of the role.
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for the ID field.
     *
     * @param id The ID to set for the role.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for the name field.
     *
     * @return The name of the role.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name field.
     *
     * @param name The name to set for the role.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Overrides the default toString method to provide a readable string representation of the Role object.
     *
     * @return A string representation of the Role object, including the ID and name.
     */
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
