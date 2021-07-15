package com.igorlucas.validation.constraintvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.igorlucas.validation.NotEmptyList;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List>{

	@Override
	public boolean isValid(List list, ConstraintValidatorContext context) {
		return list != null && !list.isEmpty();
	}

}
