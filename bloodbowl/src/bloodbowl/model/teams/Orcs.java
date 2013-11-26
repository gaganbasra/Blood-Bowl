package bloodbowl.model.teams;

import bloodbowl.model.players.PlayerType;
import bloodbowl.model.players.Skills;

/**
 * Class for Orcs team, extending the Team class
 * @author singh
 * @version 1.0.0
 */

public class Orcs extends Team {
    /**
     * Team Constructor
     * @param id To determine if the team is A or B
     * @param chosenName The name of the created team
     */
	public Orcs(int id, String chosenName)
	{
		maxLineman=16; 
		maxGoblin=4; 
		maxThrower=2;
		maxBlackorc=4;
		maxBlitzer=4;
		maxTroll=1;
                name=chosenName;
                teamID=id;
                teamType=TeamType.ORC_TYPE;
                typeOfAvailablePlayers = new PlayerType[] { PlayerType.LINEMAN, PlayerType.GOBLIN, PlayerType.THROWER, PlayerType.BLITZER, PlayerType.BLACKORC, PlayerType.TROLL };

                 //Player Characteristics
                 linemanMA=5;linemanST=3;linemanAG=3;linemanAV=9;
                 blackorcMA=4;blackorcST=4;blackorcAG=2;blackorcAV=9;
                 blitzerMA=9;blitzerST=3;blitzerAG=3;blitzerAV=9;
                 goblinMA=6;goblinST=2;goblinAG=3;goblinAV=7;
                 throwerMA=5;throwerST=3;throwerAG=3;throwerAV=8;
                 trollMA=4;trollST=5;trollAG=1;trollAV=9;

                 //Player Skills
                 trollSkills.add(Skills.MIGHTYBLOW);
                 trollSkills.add(Skills.REALLYSTUPID);
                 trollSkills.add(Skills.REGENERATION);
                 trollSkills.add(Skills.THROWTEAMMATE);

                 blitzerSkills.add(Skills.BLOCK);

                 goblinSkills.add(Skills.RIGHTSTUFF);
                 goblinSkills.add(Skills.DODGE);
                 goblinSkills.add(Skills.STUNTY);

                 throwerSkills.add(Skills.SUREHANDS);
                 throwerSkills.add(Skills.PASS);
	}
}
