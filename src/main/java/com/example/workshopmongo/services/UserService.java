package com.example.workshopmongo.services;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.UserDTO;
import com.example.workshopmongo.exception.ObjectNotFoundException;
import com.example.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll()
	{
		return repo.findAll();
	}
	
	public User findById(String id)
	{
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(id)) ;
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	public User fromDTO(UserDTO obJDTO)
	{
		return new User(obJDTO.getId(),obJDTO.getName(),obJDTO.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj)
	{
		Optional<User> newUser = repo.findById(obj.getId());
		User user = newUser.get();
		updateData(user, obj);
		return repo.save(user);
	}

	private void updateData(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		
	}
}
