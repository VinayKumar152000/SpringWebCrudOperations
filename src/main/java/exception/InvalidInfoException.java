package exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvalidInfoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus status;
		
}