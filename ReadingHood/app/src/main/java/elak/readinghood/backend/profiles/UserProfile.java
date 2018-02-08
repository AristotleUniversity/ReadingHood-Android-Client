package elak.readinghood.backend.profiles;

import elak.readinghood.backend.server.ServerUpdate;

/**
 * @author Spiros
 */
public class UserProfile extends Profile {

    private String email, password;

    /**
     * trivial constructor
     */
    public UserProfile() {

    }

    /**
     * trivial contructor
     *
     * @param id         the id of the user
     * @param reputation the reputation of the user
     * @param email      the user given email
     * @param username   the user given username
     * @param password   the user given password
     * @param name       the user given name
     * @param surname    the user given surname
     * @param department the user given department
     */
    public UserProfile(int id, int reputation, String email, String username, String password, String name, String surname, String department) {
        this.id = id;
        this.reputation = reputation;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.department = department;
    }

    /**
     * YOU NEVER USE THIS FUNCTION as front end developer.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * YOU NEVER USE THIS FUNCTION as front end developer.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * This function edits the name of the user.
     * <p>
     * Error0 = "Success" (which means that the name was changed successfully)
     * Error1 = "Error connecting with server"
     * Error404 = "Fill every field" (which means that the user must fill all the given fields)
     *
     * @param newName the user given newName
     * @return an error text
     */
    public String editName(String newName) {
        if (newName.isEmpty()) {
            return "Fill every field";
        }

        if (ServerUpdate.editName(newName)) {
            this.name = newName;
            return "Success";
        } else {
            return "Error connecting with server";
        }
    }

    /**
     * This function edits the surname of the user.
     * <p>
     * Error0 = "Success" (which means that the surname was changed successfully)
     * Error1 = "Error connecting with server"
     * Error404 = "Fill every field" (which means that the user must fill all the given fields)
     *
     * @param newSurname the user given newName
     * @return an error text
     */
    public String editSurname(String newSurname) {
        if (newSurname.isEmpty()) {
            return "Fill every field";
        }
        if (ServerUpdate.editSurname(newSurname)) {
            this.surname = newSurname;
            return "Success";
        } else {
            return "Error connecting with server";
        }
    }
}