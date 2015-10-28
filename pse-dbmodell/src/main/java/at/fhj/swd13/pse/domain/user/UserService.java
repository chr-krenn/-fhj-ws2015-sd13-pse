package at.fhj.swd13.pse.domain.user;

import java.util.List;

import javax.mail.MessagingException;

import at.fhj.swd13.pse.db.EntityNotFoundException;
import at.fhj.swd13.pse.db.entity.Person;
import at.fhj.swd13.pse.db.entity.PersonRelation;
import at.fhj.swd13.pse.dto.UserDTO;

public interface UserService {

	/**
	 * Get a user and log the user in
	 * 
	 * @param username
	 *            username of the user to log in (case sensitive)
	 * @param plainPassword
	 *            plaintext password (case sensitive)
	 * 
	 * @return instance of a person or null if it could not be found or logged in or may not log in
	 */
	Person loginUser(String username, String plainPassword);

	/**
	 * logout the user in the current session
	 */
	void logoutCurrentUser();
	
	/**
	 * get the currently logged in user from the session
	 * 
	 * @return the logged in user
	 */
	public Person getLoggedInUser();

	/**
	 * Get the user with the given username
	 * 
	 * @param username
	 *            of the person to retrieve
	 * 
	 * @return instance of the user with the given username
	 * @throws EntityNotFoundException 
	 */
	Person getUser(final String username) throws EntityNotFoundException;

	/**
	 * get a list of all known usrs
	 * 
	 * @return a list of all currently known users
	 */
	List<Person> getUsers();
	
	/**
	 * get a list of all known usrs with the given department
	 * @param deparment
	 *           of the persons to retrieve
	 * @return a list of all currently known users
	 */
	List<Person> getUsersWithDepartment(String department);
	
	/**
	 * searc users by firstname, lastname, email 
	 * 
	 * @return a list of all users mathcing the search
	 */
	public List<Person> findUsers(String search);
	

	/**
	 * Update all passwords that are null or '--' with 12345678
	 * 
	 * @return the number of passwords changed
	 */
	int updateNullPasswords();

	/**
	 * Set a new instance that checks the strength of the password
	 * 
	 * @param passwordStrengthValidator
	 *            new instance of the passwordStrengthValidator
	 * 
	 * 
	 */
	void setPasswordStrengthValidator(PasswordStrengthValidator passwordStrengthValidator);

	/**
	 * Check whether the given password matches the current password
	 * 
	 * @param username
	 *            the username to check
	 * 
	 * @param plainPassword
	 *            the password to check
	 * 
	 * @return true when the password matches, false in any other case
	 */
	boolean isMatchingPassword(final String username, final String plainPassword);

	/**
	 * 
	 * Set a new password for the user
	 * 
	 * @param username
	 *            username of for whom to change the password
	 * @param newPlainPassword
	 *            plaintext of the new password
	 * 
	 * @throws WeakPasswordException
	 *             password does not meet strength criteria
	 * @throws EntityNotFoundException
	 *             when the username is not associated with any existing user
	 */
	void setPassword(String username, String newPlainPassword) throws WeakPasswordException, EntityNotFoundException;

	/**
	 * Set a new password for the user but verify the current
	 * 
	 * @param loggedInUsername username of for whom to change the password
	 * 
	 * @param password current password that will be verified
	 *
	 * @param passwordNew plaintext of the new password
	 * 
	 * @return true when the password has been changed, false in any other case (user not found, weak pw, unmatching pw)
     *
	 */
	boolean changePassword(String loggedInUsername, String password, String passwordNew);
	/**
	 * Update the user data base on the contents of the userDTO
	 * !! Warning: permissions are not checked!
	 * 
	 * @param userDTO
	 *            the UserDTO from which ALL of the data is taken and updated into the person
	 * 
	 * @throws EntityNotFoundException
	 *             when the username was not given/ found
	 */
	void update(final UserDTO userDTO) throws EntityNotFoundException;

	/**
	 * Set or clear the image of a user
	 * 
	 * @param username
	 *            username of the user for which to change/ clear the image
	 * 
	 * @param documentId
	 *            the id of the document to set as the users image. If 0 the image is cleared
	 * 
	 * @throws EntityNotFoundException
	 *             when the username was not given/ found
	 *             when the document was not found via the documentId
	 */
	void setUserImage(final String username, final Integer documentId) throws EntityNotFoundException;
	
	/**
	 * Create a relation between two persons
	 * 
	 * @param the person who initiates the relation
	 * @param the person who is added to the relation
	 * @return a relation object
	 */	
	PersonRelation createRelation(Person sourcePerson, Person targetPerson);
	
	
	void removeRelation(Person sourcePerson, Person targetPerson);
	
	
	/**
	 * Resets the password of the user with a given E-Mail-Address to a random value
	 * @param emailAddress the E-Mail-Address as String
	 * @throws InvalidEmailAddressException
	 */
	
	void resetPassword(String emailAddress) throws InvalidEmailAddressException, MessagingException;

	
}