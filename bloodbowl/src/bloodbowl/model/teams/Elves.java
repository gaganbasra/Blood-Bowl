package bloodbowl.model.teams;

import bloodbowl.model.players.PlayerType;
import bloodbowl.model.players.Skills;

/**
 * Class for Elves team, extending the Team class
 * @author Rishinder
 * @version 1.0.0
 */
public class Elves extends Team {

    /**
     * Team Constructor
     * @param id To determine if the team is A or B
     * @param chosenName The name of the created team
     */
        public Elves(int id,String chosenName)
	{
		maxLineman=16;
		maxCatcher=4;
		maxThrower=2;
		maxBlitzer=2;
                name=chosenName;
                teamID=id;
                teamType=TeamType.ELF_TYPE;
                typeOfAvailablePlayers = new PlayerType[] {PlayerType.LINEMAN, PlayerType.CATCHER, PlayerType.THROWER, PlayerType.BLITZER};

                 //Player Characteristics
                 linemanMA=6;linemanST=3;linemanAG=4;linemanAV=7;
                 blitzerMA=7;blitzerST=3;blitzerAG=4;blitzerAV=8;
                 catcherMA=8;catcherST=3;catcherAG=4;catcherAV=7;
                 throwerMA=6;throwerST=3;throwerAG=4;throwerAV=7;

                 //Player Skills

                 blitzerSkills.add(Skills.BLOCK);
                 blitzerSkills.add(Skills.SIDESTEP);

                 catcherSkills.add(Skills.CATCH);
                 catcherSkills.add(Skills.NERVESOFSTEEL);

                 throwerSkills.add(Skills.PASS);
	}
}
