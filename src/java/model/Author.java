/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author THevY
 */
public class Author {
    private String authorId;
    private String name;
    private Date dayOfBirth;
    private String bio;

    public Author() {
    }

    public Author(String authorId, String name, Date dateOfBirth, String bio) {
        this.authorId = authorId;
        this.name = name;
        this.dayOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dateOfBirth) {
        this.dayOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(authorId, other.authorId);
	}

    @Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", name=" + name + ", dateOfBirth=" + dayOfBirth + ", bio=" + bio + '}';
    }
        
}
