package pl.polsl.moviecollectionmanagement.configuration;

public class SecurityConstants {

    public static final String SECRET = "MY_TREASURE";
    public static final long EXPIRATION_TIME = 900_000; // 15 min
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
