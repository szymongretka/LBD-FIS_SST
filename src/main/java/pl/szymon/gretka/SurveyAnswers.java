package pl.szymon.gretka;

public class SurveyAnswers {
	
	public String firstName;
	public String lastName;
	public String universityName;
	public String studyDegree;
	public String quality;
	public String contact;
	public String experience;
	
	
	@Override
	public String toString() {
		return "SurveyAnswers [firstName=" + firstName + ", lastName=" + lastName + ", universityName=" + universityName
				+ ", studyDegree=" + studyDegree + ", quality=" + quality + ", contact=" + contact + ", experience="
				+ experience + "]";
	}


	public SurveyAnswers(String firstName, String lastName, String universityName, String studyDegree, String quality,
			String contact, String experience) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.universityName = universityName;
		this.studyDegree = studyDegree;
		this.quality = quality;
		this.contact = contact;
		this.experience = experience;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUniversityName() {
		return universityName;
	}


	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}


	public String getStudyDegree() {
		return studyDegree;
	}


	public void setStudyDegree(String studyDegree) {
		this.studyDegree = studyDegree;
	}


	public String getQuality() {
		return quality;
	}


	public void setQuality(String quality) {
		this.quality = quality;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getExperience() {
		return experience;
	}


	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	

}
