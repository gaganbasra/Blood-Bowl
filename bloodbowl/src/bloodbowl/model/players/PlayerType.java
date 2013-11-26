

package bloodbowl.model.players;

/**
 * Enumeration of Player types that can possibly be the part of the game's teams.
 * Each one of the type has it's overloaded toString() to get its type name as a string.
 * This is used in the GUI to populate the combo-box of the player editor.
 * @author Rishinder
 * @version 1.0.0
 */
public enum PlayerType {
    BLACKORC { @Override public String toString() { return "Black Orc"; }},
    BLITZER { @Override public String toString() { return "Blitzer"; }},
    CATCHER { @Override public String toString() { return "Catcher"; }},
    GOBLIN { @Override public String toString() { return "Goblin"; }},
    LINEMAN { @Override public String toString() { return "Lineman"; }},
    OGRE { @Override public String toString() { return "Ogre"; }},
    THROWER { @Override public String toString() { return "Thrower"; }},
    TROLL { @Override public String toString() { return "Troll"; }}
}
