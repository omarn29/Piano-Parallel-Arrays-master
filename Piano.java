import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A piano that can be played with the computer keyboard.
 * 
 * @author Omar Nakhleh
 * @teacher Scott Hardman
 * @Assigment Assignment 4
 * @version (5/08/2017)
 */
public class Piano extends World
{
    private String[] whiteKeys = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\" } ;

    private String[] whiteNotes = {"2c", "2d", "2e", "2f", "2g", "2a", "2b", "3c", "3d", "3e", "3f", "3g", "3a" } ;

    private String[] blackKeys = { "2", "3", "", "5", "6", "7", "", "9", "0", "", "=" } ;

    private String[] blackNotes = {"3c#", "3d#", "", "3f#", "3g#", "3a#", "", "4c#", "4d#", "", "4f#" } ;

    private Key[] whiteKeyObjects = new Key[whiteKeys.length];
    private Key[] blackKeyObjects  = new Key[blackKeys.length];
    private Key[] allKeyObjects  = new Key[whiteKeys.length + blackKeys.length];
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

            whiteKeyObjects[i] = currentKey;
        }

        for( int i = 0; i < blackKeys.length; i++ )
        {
            if( blackKeys[i] != "" )
            {
                currentKey = new Key( blackKeys[i], blackNotes[i], "black-key", "black-key-down" );
                addObject( currentKey, ( i*67)+65, 195 );

                blackKeyObjects[i] = currentKey;
            }
            else
            {
                blackKeyObjects[i] = null;
            }

            makeAllKeysArray(); 
        }
    }

    /**
     * makeAllKeysArray determins that the white keys are even and the black keys are odd
     * @param There is no paramters
     * @return Nothing is returned 
     */
    private void makeAllKeysArray() 
    {
        for(int i = 0; i < allKeyObjects.length-2; i++)
        {
            if( i % 2 == 0 )
            {
                allKeyObjects[i] = whiteKeyObjects[i/2];
            }

            if( i % 2 != 0 )
            {
                allKeyObjects[i] = blackKeyObjects[i/2];
            }
        }
        allKeyObjects[allKeyObjects.length - 2] = whiteKeyObjects[whiteKeyObjects.length - 2];
        allKeyObjects[allKeyObjects.length - 1] = whiteKeyObjects[whiteKeyObjects.length - 1];
    }

    public void act()
    {
        int numAllDown = 0;
        int numNulls = 0;

        int[] keyDownLocations = new int[20]; 

        for(int i = 0; i < allKeyObjects.length; i++)
        {
            if( allKeyObjects[i] == null )
            {
                numNulls ++;
            }
            else 
            {
                if( allKeyObjects[i].checkDown() == true )
                {
                    keyDownLocations[numAllDown] = i - numNulls;
                    numAllDown ++;
                }
            }
        }

        if( numAllDown == 2 )
        {
            checkForSeconds(keyDownLocations );
        }
        else if( numAllDown == 3 )
        {
            checkForTriads(keyDownLocations );
        }
        else if( numAllDown == 4 )
        {
            checkForSevenths(keyDownLocations );
        }
        else
        {
            showText( "", getWidth()/2, getHeight()-50 );
        }
    }

    /**
     * checkForSeconds determins if the user is playing a second to display "You have made a second"
     * @param is an array that Recognizes the location of the pressed key 
     * @return Nothing is returned 
     */
    private void checkForSeconds(int[] downKeys)
    {
        if(downKeys[0] + 1 == downKeys[1] || downKeys[0] + 2 == downKeys[1] )
        {
            showText( "You have made a second", getWidth()/2, getHeight()-50 ); 
        }
    }

    /**
     * checkForTriads determins if the user is playing a triad to display "You have made a triad"
     * @param is an array that Recognizes the location of the pressed key 
     * @return Nothing is returned 
     */
    private void checkForTriads(int[] downKeys)
    {
        if( downKeys[0] + 3 == downKeys[1] && downKeys[1] + 4 == downKeys[2] ||
        downKeys[0] + 4 == downKeys[1] && downKeys[1] + 3 == downKeys[2] ||
        downKeys[0] + 3 == downKeys[1] && downKeys[1] + 3 == downKeys[2] )
        {
            showText( "You have made a triad", getWidth()/2, getHeight()-50 ); 
        }
    }

    /**
     * checkForSevenths determins if the user is playing a seventh to display "You have made a seventh"
     * @param is an array that Recognizes the location of the pressed key 
     * @return Nothing is returned 
     */
    private void checkForSevenths(int[] downKeys)
    {
        if( downKeys[0] + 3 == downKeys[1] && downKeys[1] + 4 == downKeys[2] && downKeys[2] + 3 == downKeys[3] ||
        downKeys[0] + 4 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 4 == downKeys[3] ||
        downKeys[0] + 3 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 3 == downKeys[3] )
        {
            showText( "You have made a seventh", getWidth()/2, getHeight()-50 ); 
        }
    }
}

