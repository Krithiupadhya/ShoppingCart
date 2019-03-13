package com.mindtree.shoppingcart.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	private LocalDateTime timestamp;
	private String message;
	private String requestUri;
	
}