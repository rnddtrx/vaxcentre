//Exception d'authentification
package be.ipam.vaxcentre.security;
public class AuthenticationException extends RuntimeException {

	public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}

