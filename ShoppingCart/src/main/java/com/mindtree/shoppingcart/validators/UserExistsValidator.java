package com.mindtree.shoppingcart.validators;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mindtree.shoppingcart.entity.User;
import com.mindtree.shoppingcart.repository.UserRepository;

@Transactional
public class UserExistsValidator implements ConstraintValidator<UserExists, User>{

	private static Logger logger = LoggerFactory.getLogger(UserExistsValidator.class);
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public boolean isValid(User value, ConstraintValidatorContext context) {
		logger.debug("User value sent:"+value);
		Optional<User> user= userRepository.findById(value.getUsername());
		
		return user.isPresent();
	}

}
