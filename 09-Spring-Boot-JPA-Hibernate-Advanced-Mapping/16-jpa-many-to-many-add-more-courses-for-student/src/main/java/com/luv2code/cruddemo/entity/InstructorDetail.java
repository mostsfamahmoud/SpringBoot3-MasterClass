// Define the package for the entity class
package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

/**
 * Represents the InstructorDetail entity.
 * This class maps to the "instructor_detail" table in the database.
 * It contains additional information about an instructor, such as their YouTube channel and hobby.
 */
@Entity // Indicates that this class is a JPA entity mapped to a database table.
@Table(name = "instructor_detail") // Maps this entity to the "instructor_detail" table in the database.
public class InstructorDetail {

    /**
     * Primary key for the "instructor_detail" table.
     * The ID is auto-generated using the database's identity column.
     */
    @Id // Marks this field as the primary key for the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Specifies that the ID value will be auto-generated by the database.
    @Column(name = "id") // Maps this field to the "id" column in the table.
    private int id;

    /**
     * Represents the YouTube channel associated with the instructor.
     * This maps to the "youtube_channel" column in the database.
     */
    @Column(name = "youtube_channel") // Maps this field to the "youtube_channel" column in the table.
    private String youtubeChannel;

    /**
     * Represents the hobby of the instructor.
     * This maps to the "hobby" column in the database.
     */
    @Column(name = "hobby") // Maps this field to the "hobby" column in the table.
    private String hobby;

    /**
     * Represents the associated Instructor entity.
     * <p>
     * This field is mapped using a one-to-one bidirectional relationship.
     * The cascading behavior excludes the DELETE operation to prevent
     * accidental deletion of the associated Instructor object.
     * </p>
     */
    @OneToOne(mappedBy = "instructorDetail",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private Instructor instructor;

    /**
     * Default no-argument constructor.
     * This is required by JPA for creating entity objects.
     */
    public InstructorDetail() {
    }

    /**
     * Parameterized constructor to initialize an InstructorDetail object with specific attributes.
     *
     * @param youtubeChannel The YouTube channel associated with the instructor.
     * @param hobby          The hobby of the instructor.
     */
    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    // Getter and Setter methods for each field

    /**
     * Gets the ID of the instructor detail.
     *
     * @return The unique identifier for the instructor detail.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the instructor detail.
     *
     * @param id The unique identifier to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the YouTube channel associated with the instructor.
     *
     * @return The YouTube channel as a string.
     */
    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    /**
     * Sets the YouTube channel associated with the instructor.
     *
     * @param youtubeChannel The YouTube channel to set.
     */
    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    /**
     * Gets the hobby of the instructor.
     *
     * @return The hobby of the instructor as a string.
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * Sets the hobby of the instructor.
     *
     * @param hobby The hobby to set.
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /**
     * Gets the associated Instructor entity.
     *
     * @return The Instructor object linked to this InstructorDetail.
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Sets the associated Instructor entity.
     *
     * @param instructor The Instructor object to associate with this InstructorDetail.
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Provides a string representation of the InstructorDetail object.
     * Includes all fields for easy debugging and logging.
     *
     * @return A string representation of the instructor detail.
     */
    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
