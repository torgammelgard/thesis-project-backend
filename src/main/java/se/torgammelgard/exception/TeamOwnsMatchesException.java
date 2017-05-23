package se.torgammelgard.exception;

/**
 * A runtime exception for when a user tries to delete a team which is connected to persisted matches.
 */
@SuppressWarnings("serial")
public class TeamOwnsMatchesException extends Exception {

}
