package com.mindtree.shoppingcart.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private LocalDateTime timestamp;
	private String message;
	private String requestUri;
	
}
