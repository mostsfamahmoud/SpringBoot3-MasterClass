// Define the package for the entity class
package com.luv2Code.springboot.cruddemo.entity;

// Import JPA annotations for entity and table mapping

import jakarta.persistence.*;

import java.util.Collection;

/**
 * Represents a User entity that maps to a database table.
 * <p>
 * This class is annotated with JPA annotations to define the database
 * table structure and the relationship between the class fields and table columns.
 */
@Entity
@Table(name = "user") // Maps this entity to the "user" table in the database
public class User {

    /**
     * The primary key of the User entity.
     * <p>
     * This field is annotated with @Id to mark it as the primary key.
     * The @GeneratedValue annotation with strategy IDENTITY indicates that
     * the database generates the value automatically (auto-increment).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    @Column(name = "id") // Maps this field to the "id" column in the "user" table
    private long id;

    /**
     * Represents the username of the user.
     * <p>
     * This field is mapped to the "username" column in the database table.
     */
    @Column(name = "username") // Maps this field to the "username" column
    private String userName;

    /**
     * Represents the password of the user.
     * <p>
     * This field is mapped to the "password" column in the database table.
     */
    @Column(name = "password") // Maps this field to the "password" column
    private String password;

    /**
     * Indicates whether the user is enabled.
     * <p>
     * This field is mapped to the "enabled" column in the database table.
     */
    @Column(name = "enabled") // Maps this field to the "enabled" column
    private boolean enabled;

    /**
     * Defines a many-to-many relationship between User and Role entities.
     * <p>
     *
     * - FetchType.EAGER: Roles are fetched immediately when a User is retrieved.
     * When you retrieve a User with FetchType.EAGER, all associated Role objects are also retrieved immediately.
     * <p>
     *
     * - CascadeType.ALL: Operations on User are cascaded to the associated Roles.
     * NOTE: Why CascadeType.ALL ?
     * Persisting Data -> When you save a User object with associated Role objects,
     * the users_roles table is automatically populated because of the cascade = CascadeType.ALL.
     * <p>
     *
     * - JoinTable: Specifies the join table "users_roles" for this relationship.
     * - joinColumns: Refers to the foreign key column "user_id" in the join table.
     * - inverseJoinColumns: Refers to the foreign key column "role_id" in the join table.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles", // Name of the join table
            joinColumns = @JoinColumn(name = "user_id"), // Foreign key to the User entity
            inverseJoinColumns = @JoinColumn(name = "role_id") // Foreign key to the Role entity
    )
    private Collection<Role> roles;

    /**
     * Default constructor for the User entity.
     * <p>
     * A no-argument constructor is required by JPA for creating instances of the entity.
     */
    public User() {
        // Default constructor for JPA
    }

    /**
     * Constructs a User with specified username, password, and enabled status.
     *
     * @param userName The username of the user.
     * @param password The password of the user.
     * @param enabled  Whether the user is enabled.
     */
    public User(String userName, String password, boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    /**
     * Constructs a User with specified username, password, enabled status, and roles.
     *
     * @param userName The username of the user.
     * @param password The password of the user.
     * @param enabled  Whether the user is enabled.
     * @param roles    The roles associated with the user.
     */
    public User(String userName, String password, boolean enabled, Collection<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    /**
     * Getter for the ID field.
     *
     * @return The ID of the user.
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for the ID field.
     *
     * @param id The ID to set for the user.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for the username field.
     *
     * @return The username of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for the username field.
     *
     * @param userName The username to set for the user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for the password field.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password field.
     *
     * @param password The password to set for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for the enabled field.
     *
     * @return True if the user is enabled, false otherwise.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Setter for the enabled field.
     *
     * @param enabled The enabled status to set for the user.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Getter for the roles field.
     *
     * @return The roles associated with the user.
     */
    public Collection<Role> getRoles() {
        return roles;
    }

    /**
     * Setter for the roles field.
     *
     * @param roles The roles to set for the user.
     */
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    /**
     * Provides a string representation of the User entity.
     *
     * @return A string representation of the User entity.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
