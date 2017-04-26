import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A piano that can be played with the computer keyboard.
 * 
 * @author Omar Nakhleh
 * @teacher Scott Hardman
 * @Assigment Assignment 4
 * @version (4/25/2017)
 */
public class Piano extends World
{
    private String[] whiteKeys = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\" } ;
    
    private String[] whiteNotes = {"2c", "2d", "2e", "2f", "2g", "2a", "2b", "3c", "3d", "3e", "3f", "3g", "3a" } ;
    
    private String[] blackKeys = { "2", "3", "", "5", "6", "7", "", "9", "0", "", "=" } ;
    
    private String[] blackNotes = {"3c#", "3d#", "", "3f#", "3g#", "3a#", "", "4c#", "4d#", "", "4f#" } ;
    
    
    
    /**
     * Make the piano.
     */
    public Piano() 
    {
        super(900, 340, 1);
        
        makeKeys(); 
    }
    
    /**
     * makeKeys adds key objects to the world
     * @param There is no paramters
     * @return Nothing is returned 
     */
    private void makeKeys()
    
    {
      Key currentKey;  
      for( int i = 0; i < whiteKeys.length; i++ )
      {
        currentKey = new Key( whiteKeys[i], whiteNotes[i], "white-key", "white-key-down");
        addObject( currentKey, ( i*67)+30, 250 );
      }
      
      for( int i = 0; i < blackKeys.length; i++ )
      {
        if( blackKeys[i] != "" )
        {
            currentKey = new Key( blackKeys[i], blackNotes[i], "black-key", "black-key-down" );
            addObject( currentKey, ( i*67)+65, 195 );
            
        }
      }
    }
    
}
