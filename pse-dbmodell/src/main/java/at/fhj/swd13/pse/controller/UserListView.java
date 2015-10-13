package at.fhj.swd13.pse.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import at.fhj.swd13.pse.db.entity.Person;
import at.fhj.swd13.pse.domain.user.UserService;
import at.fhj.swd13.pse.dto.UserDTO;

@ManagedBean
@ViewScoped
public class UserListView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	public List<UserDTO> getUsers() {

		List<UserDTO> result = new ArrayList<UserDTO>();

		for (Person person : userService.getUsers()) {

			result.add(new UserDTO(person));

		}

		return result;
	}
}