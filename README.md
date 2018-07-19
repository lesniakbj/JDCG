# JDCG
JDCG - Java DCS Campaign Generator 

JDCG is a Dynamic Campaign Simulation and Mission Generator for the Flight Simulator DCS: World. The goal of this project is to bring the feeling
of the Dynamic Campaign from BMS into the modern simulator that is DCS: World. This project involves three different parts, which all work together
to get the feeling of a BMS Dynamic Campaign:
1) Air Campaign Generator
2) Air Campaign Simulator
3) DCS Mission Generator

The Air Campaign Generator runs outside of DCS, and will generate a starting situation in which the campaign will be played.
This can create various scenarios from Offensive to Defensive campaigns, and supports all of the maps available in DCS: World.

The Air Campaign Simulator runs outside of DCS, and will simulate commanders and missions that might be expected to happen during an Air Campaign.
The Simulator will alter the campaign state, allowing for the feeling of a Dynamic war in which the user is taking part of. Finally, the Simulator
will incorporate results from missions that are played in DCS: World, allowing the user to have an effect on the outcome of the Campaign.

Finally, the DCS Mission Generator also runs outside of DCS, and is used to generate DCS missions. These missions are generated using parameters
from the Air Campaign, and will allow the user to feel like they are taking part in the conflict.

The following shows the general gameplay loop that JDCG requires:


Here is an example of a Campaign Generation:
![Example map / campaign generation](https://raw.githubusercontent.com/lesniakbj/JDCG/master/src/main/resources/examples/example_generations.png)
