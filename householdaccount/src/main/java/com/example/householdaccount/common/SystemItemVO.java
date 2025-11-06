package com.example.householdaccount.common;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.jmolecules.ddd.annotation.ValueObject;


@ValueObject
@Embeddable
public class SystemItemVO {

		@Column(nullable = false)
		private LocalDateTime initialCreateDateAndTime = LocalDateTime.now();

		@Column(nullable = false)
		private LocalDateTime lastUpdateDateAndTime = LocalDateTime.now();

		@Column(nullable = false, length = 40)
		private String initialCreateUserCode="create";

		@Column(nullable = false, length = 40)
		private String lastUpdateUserCode="update";

}
