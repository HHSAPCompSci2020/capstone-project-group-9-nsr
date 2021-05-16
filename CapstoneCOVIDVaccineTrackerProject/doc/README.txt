AP Computer Science Final Project - README Template

Instructions:
The first step in creating an excellent APCS final project is to write up a README. At this stage, this README file acts as your project proposal. Once you’ve filled in all components, Shelby will read through it and suggest edits. Ultimately, you need a document that adequately describes your project idea and we must agree on this plan.

Have one member of your group make a copy of this Google Doc. Then, they should share it with all other members so that every group member has edit permissions.

There’s a lot of parts of this document that you might not have full answers for yet. Because you haven’t written the program yet, it’s difficult to think about the instructions or which group members will do which parts. Even though this is hard to think about, you must have something in these sections that acts as your current plan. However, during the course of the project, you’ll continuously update this document. This means that you will not be held to exactly what you put here - components of this document can change (and it’s pretty common!).

There is one exception: the Features List section. Once Shelby OKs your README, the Features List section cannot be modified. For this reason, it is most important that you get a solid idea of what you want to make and the primary features it will have now.

Talk with your group. Consider drawing some pictures of what you think your project might look like. Be precise. When you’re ready, fill this out together. Each component in brackets below ( [these things] ) should be replaced with your ideas. Note that there are several sample READMEs posted on this assignment for you to use as guidance.
-------------------When README is finalized, remove everything above this line--------------------

COVID Vaccine Data Tracker
Authors: Roopa Srinivas, Sophie Lin, Nodoka Shibasaki
Revision: 05/15/2021

Introduction: 
[In a few paragraphs totaling about ½ page, introduce the high-level concept of your program. What this looks like depends a lot on what type of thing you are making. An introduction for an application will look different than one for a game. In general, your introduction should address questions like these:
What does your program do?
What problem does it solve? Why did you write it?
What is the story?
What are the rules? What is the goal?
Who would want to use your program?
What are the primary features of your program?]

Our project is a COVID vaccination tracking map. It utilizes an interactive map that displays statistics about the COVID vaccine in various states in the United States. Our program reads data from the CDC website and constantly updates the statistics and other information. Some examples of these statistics are whether the number of COVID cases in that state has increased or decreased from the previous day to the current day (and by how much), the number of COVID vaccines administered in that state, the percent of vaccines given from each company, and just some basic facts about each company’s vaccine. The user will be able to select a state or region in the United States, then our program will read live data from the CDC website, and display it to the user in a presentable way. 
Our program is meant to spread information about the COVID vaccines and it is meant to be used by anybody who wants to learn more about the actual numbers and information about the vaccine. Since a lot of misinformation is spreading around on the internet about COVID, this program will compile information and statistics from multiple government official sources to make sure the information is accurate.
About 1 in 4 people living in the United States don’t believe in the COVID vaccine and in wearing masks. Because these people don’t believe in neither receiving the COVID vaccine, nor wearing masks and following proper COVID guidelines, they spend time with their friends who also might not follow these guidelines. This causes more and more people to get sick with the virus causing herd immunity to be put at risk. At least 70-80% of people have to gain immunity, or else herd immunity will be very difficult to obtain within the country. We hope this project sheds some light on this enormous issue and keeps people informed about the vaccine and encourages them to get it as well.

Instructions:
[Explain how to use the program. This needs to be specific: 
Which keyboard keys will do what? 
Where will you need to click? 
Will you have menus that need to be navigated? What will they look like? 
Do actions need to be taken in a certain order?]

When the user runs the COVID Vaccine Data Tracker, the map appears on the screen with directions on the top of the graphic interface. The user will need to click on the drop down menu on the top right to go to different pages such as facts about vaccines, country wide statistics and facts about different states. All facts and statistics will be retrieved from the CDC website. If a user goes to a state page, the user will see the name of the state on top, followed by a graph based on CDC statistics, percentages, and number of vaccinated people in the state. To go back to the home page (the map), the user will need to select the “home” page from the drop down page. To zoom in and out, the user will need to click on the up and down arrows. 

Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
[These are features that we agree you will definitely have by the project due date. A good final project would have all of these completed. At least 5 are required. Each feature should be fully described (at least a few full sentences for each)]
[DONE] Something that allows us to read data live from a website
The information should be updated consistently and frequently from the website.
[DONE] Facts about the vaccines
The page will include chemicals of each vaccinations and their success rate and eligibility (16 and 17 is only allowed to get Pfizer vaccines).
[DONE] Information for individual states 
Find a way to bring the user to individual states with information. 
Each state will include facts and statistics on the state.
[DONE] Takes user to another page with statistics about the single state
The statistics page will include different types of information like the percentage of vaccines out of the whole country that have been given, actual number of vaccines given, and number of vaccines.
 [DONE] Country-wide statistics
The project will include total number of vaccines administered, total number of vaccines distributed, breakdown of vaccine companies’ vaccinations (percentage wise) and more.

Want-to-have Features:
[These are features that you would like to have by the project due date, but you’re unsure whether you’ll hit all of them. A good final project would have perhaps half of these completed. At least 5 are required. Again, fully describe each.]
 [DONE] Retrieve graphs and statistics from CDC data sets
We would use buttons to the particular forms of statistics and it would take you to another page with the current data and graphs.
 Mouse interactions
We would allow the user to hover over a certain state and it will show a brief version of the statistics and the user can also click on the state itself, which would bring them to another page that has more detailed information. This would require us (group members) to calculate the actual coordinates of each state (which would be irregularly shaped).
 Interactive map has at least 4 different options
Our map would have statistics state-wise and also region-wise (West, Southwest, Midwest, Northeast, and Southeast). Both these maps would include information about whether the number of cases has increased or decreased since the previous day, the percentage of vaccines that have been administered, and which type of vaccine (which company) is the most “popular” in that particular state/region.
Vaccination sites
We would create another map that would allow the user to view all the vaccination sites in the state selected.
We would show statistics based on the number of people who have received their first dose of the vaccine vs. the second dose of the vaccine vs. people who haven’t received the vaccine at all.
A larger map
We would like to include the whole of North America in our map if possible (not just the United States)
Color each state based on percentage of vaccinated people in that state
We would color-code each state on a scale of light-green to dark-green based on the percentage of vaccinated people in that state
Interactive key
The user would be able to press certain buttons to change the information displayed on the map. The user could change the color of the map, or view statistics from previous days, weeks, or months.
Statistics based on each month
The user would be able to choose a certain month within the duration of the vaccines being available until the present and then they would be able to view the statistics of the vaccines given during that month.

Stretch Features:
[These are features that we agree a fully complete version of this program would have, but that you probably will not have time to implement. A good final project does not necessarily need to have any of these completed at all. At least 3 are required. Again, fully describe each.]
 Use a larger map (world map) and include statistics for all the countries.
For countries besides North America, statistics only for the country as a whole and not for every state.
 On the drop down menu, include a new page called “COVID rules”
What places are open?
Restaurants, arcades, amusement parks etc.
What are the mask and social distancing requirements?
The rule would be the national wide rule.
If there are any exceptions amongst states, those will be stated as an exception.
Publish it into a website
We would build a website in HTML and CSS and “publish” our project there. We would integrate our map and all the information included in our project onto a website.

Class List:
[This section lists the Java classes that make up the program and very briefly describes what each represents. It’s totally fine to put this section in list format and not to use full sentences.]
Frame- the frame of the State and Map class 
Country - the interactive country map
State - each state with their information (50 objects for this)
Stats - fetches data from website
StatesGraphics - organizes the data into graphs
DrawingSurface-draws the main page and transitions between pages, also detects user interactions
Main - main class to call the drawing surface
MoreInfo - displays information about vaccinations, links to cdc webpages for more specific info about each vaccine


Credits:
[Gives credit for project components. This includes both internal credit (your group members) and external credit (other people, websites, libraries). To do this:
List the group members and describe how each member contributed to the completion of the final program. This could be classes written, art assets created, leadership/organizational skills exercises, or other tasks. Initially, this is how you plan on splitting the work.
Give credit to all outside resources used. This includes downloaded images or sounds, external java libraries, parent/tutor/student coding help, etc.]

Sophie
DrawingSurface class
Frame class
Part of Country class
Transitions between all pages 
Buttons and user interaction- open dropdown button, back button, map button, more information button
Designed graphics and layout of pages
Main menu graphics
Roopa
Get and format state and country map images
State class
MoreInfo class
Link buttons to webpage
Display information in text form
Frame class
Main class
DrawingSurface class
Made DropDown
Nodoka
All StatesGraphics and Stats methods and constructors
WriteInfo method in country class
statistics and graph
Find the information and statistics
Design and code the page
Getters/reader of the retrieved data
Create and statistics and graphs
Wrote recent information 
links the website information
link the information so it consistently updates
Get the data to be saved

All of us will work on the frame and graphics together since that involves all of our parts. 
Buttons or drop down menu 
Implementing the starting screen
Transitions between each page
The design of the program
We all led part of the project, but also helped each other when needed.
Sources:
Research:
https://www.cdc.gov/coronavirus/2019-ncov/cases-updates/index.html
https://covid.cdc.gov/covid-data-tracker/#datatracker-home
https://covid.cdc.gov/covid-data-tracker/#testing_testsper100k30day
Vaccines by states
Linking buttons to webpage
Images:
https://suncatcherstudio.com/patterns/us-states/
