package bloodbowl.model.teams;

import bloodbowl.model.players.PlayerType;
import bloodbowl.model.players.Skills;

/**
 * Class for Humans team, extending the Team class
 * @author Rishinder
 * @version 1.0.0
 */
public class Humans extends Team {
    /**
     * Team Constructor
     * @param id To determine if the team is A or B
     * @param chosenName The name of the created team
     */
	public Humans(int id,String chosenName)
	{
		maxLineman=16; 
		maxCatcher=4; 
		maxThrower=2;
		maxBlitzer=4;
		maxOgre=1;
                name=chosenName;
                teamID=id;
                teamType=TeamType.HUMAN_TYPE;
                typeOfAvailablePlayers = new PlayerType[] { PlayerType.LINEMAN, PlayerType.CATCHER, PlayerType.THROWER, PlayerType.BLITZER, PlayerType.OGRE };

                 //Player Characteristics
                 linemanMA=6;linemanST=3;linemanAG=3;linemanAV=8;
                 blitzerMA=7;blitzerST=3;blitzerAG=3;blitzerAV=8;
                 catcherMA=8;catcherST=2;catcherAG=3;catcherAV=7;
                 ogreMA=5;ogreST=5;ogreAG=2;ogreAV=9;
                 throwerMA=6;throwerST=3;throwerAG=3;throwerAV=8;

                 //Player Skills

                 blitzerSkills.add(Skills.BLOCK);

                 catcherSkills.add(Skills.CATCH);
                 catcherSkills.add(Skills.DODGE);

                 ogreSkills.add(Skills.LONER);
                 ogreSkills.add(Skills.BONEHEAD);
                 ogreSkills.add(Skills.MIGHTYBLOW);
                 ogreSkills.add(Skills.TICKSKULL);
                 ogreSkills.add(Skills.THROWTEAMMATE);

                 throwerSkills.add(Skills.SUREHANDS);
                 throwerSkills.add(Skills.PASS);
	}       
}
