package tripTracker;

import java.io.Serializable;

/**
 * @author Lawrence Coles
 *
 * A Student container class to store student information in.
 * Additional attributes can be added but the equals method 
 * should be maintained.
 */
public class Student implements Serializable {
	
	/**
	 * Appease the gods of serialisation
	 */
	private static final long serialVersionUID = -2624232937188221366L;
	
	private String firstName;
	private String secondName;
	private String phoneNumber;
	
	/**
	 * @param firstName The first name of the student.
	 * @param secondName The second name of the student.
	 * @param phoneNumber The phone number of the student.
	 */
	public Student(String firstName, String secondName, String phoneNumber) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @param firstName The first name of the student.
	 * @param secondName The second name of the student.
	 */
	public Student(String firstName, String secondName) {
		this(firstName, secondName, StringConstants.PHONE_NO_NOT_SUPPLIED);
	}
	
	/**
	 * @return The first name of the student.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The first name of the student.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return The second name of the student.
	 */
	public String getSecondName() {
		return secondName;
	}
	
	/**
	 * @param secondName The second name of the student.
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	/**
	 * @return the phone number stored.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * @param phoneNumber : The phone number to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * @param o The Student to compare against.
	 * A student is equal when:
	 *  First Name, Second Name and Phone Number are the same.
	 */
	@Override
	public boolean equals(Object o) {
	    if (o == null) {
	        return false;
	    }
	    
	    final Student other = (Student) o;
	    if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
	        return false;
	    }
	    if ((this.secondName == null) ? (other.secondName != null) : !this.secondName.equals(other.secondName)) {
	        return false;
	    }
	    if ((this.phoneNumber == null) ? (other.phoneNumber != null) : !this.phoneNumber.equals(other.phoneNumber)) {
	        return false;
	    }
	    
	    return true;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return firstName + " " + secondName;
	}
}
