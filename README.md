# JDCG - Java DCS Campaign Generator
JDCG is a Dynamic Campaign Simulation and Mission Generator for the Flight Simulator DCS: World. The goal of this project is to bring the feeling
of the Dynamic Campaign from BMS into the modern simulator that is DCS: World. This project involves five different parts, which all work together
to get the feeling of a BMS Dynamic Campaign:
1) Air Campaign Generator (sim.gen.*)
2) Air Campaign Simulator (sim.*)
3) Air Campaign Mission Simulator (sim.*)
4) DCS Mission Generator (dcsgen.*)
5) DCS Mission Results Interpreter (dcslog.*)

## Air Campaign Generator
The Air Campaign Generator runs outside of DCS, and will generate a starting situation in which the campaign will be played.
It has the following features:
* Configuration:
    * Selection of Map (All DCS maps are supported, there is limited support for Normandy and WWII campaigns, but this will be rectified)
    * Selection of Coalition Factions
    * Selection of Campaign Type
        - Offensive, Defensive or All Out War
    * Selection of Campaign Era
        - Modern, Gulf War, Vietnam, Korea, WWII
    * Selection of Squadron (dictates what aircraft are available to the user; subject HEAVILY to changing [ie. being removed]).
    * Selection of Coalition
* Generation:
    * Generation of starting Airbases
        - Airbases are populated with Munitions, with a heavier distribution to their home airbase
    * Generation of starting Ground Troops
        - Generates both point defence (Airbase) and frontline troops
    * Generation of AAA and SAM Groups
    * Generation of Air Forces for each side
        - The percentages of each type of aircraft generated was derived from the following worksheet: https://docs.google.com/spreadsheets/d/1HbPD-TihUdf-2mBNxT3FOgnmfvZOr8KkzmonjtcMqAk/edit?usp=sharing

## Air Campaign Simulator
The Air Campaign Simulator will simulate commanders planning missions that might be expected to happen during an Air Campaign.
It has the following features:
* Configuration:
    * Selection of Simulation Step Length in Minutes
    * Selection of Mission Generation Settings
        - Generation of Missions when a planned mission gets to the "Mission" waypoint
* Simulation:

## Air Campaign Mission Simulator
The Air Campaign Mission Simulator will simulate missions that the player does not choose to play, and will be invoked by
the Air Campaign Simulator whenever it detects that a Mission is within it's objective area.
It has the following features:
* Configuration:
* Simulation:

## DCS Mission Generator
The DCS Mission Generator is used to generate DCS missions (as the name implies...). These missions are generated using parameters
from the Air Campaign Simulator, and will allow the user to feel like they are taking part in the conflict. It will be invoked
whenever the Air Campaign Simulator detects a player mission, or the player elects to step in.
It has the following features:
* Configuration:
* Generation:

## DCS Mission Results Interpreter
The DCS Mission Results Interpreter is used to interpret the log file that results after a DCS mission is played. This is
crucial for integrating the results of the player's missions into the running campaign.
It has the following features:
* Configuration:
* Interpretation:


## Example of Campaign Configuration
![Example basic configuration](https://raw.githubusercontent.com/lesniakbj/JDCG/master/src/main/resources/examples/example_config1.png)
![Example faction configuration](https://raw.githubusercontent.com/lesniakbj/JDCG/master/src/main/resources/examples/example_config2.png)

## stExample of Campaign Generation
![Example map / campaign generation](https://raw.githubusercontent.com/lesniakbj/JDCG/master/src/main/resources/examples/example_generations.png)
![Example flight generations](https://raw.githubusercontent.com/lesniakbj/JDCG/master/src/main/resources/examples/example_generation_flights.png)
