package se.torgammelgard;

/**
 * Jackson (with JsonView) uses this class for view hierarchy
 */
public class Views {

    public interface Public {
    }
    
    public interface Internal extends Public {
    }
}
